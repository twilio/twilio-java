#!/usr/bin/env bash
# End-to-end test: real Twilio webhooks signed with a SharedKey (HMAC-SHA256), validated by the
# RequestValidator in this checkout. See README.md in this directory for what each step does.
#
# Required env: TWILIO_ACCOUNT_SID, TWILIO_AUTH_TOKEN
# For the voice test: CALL_FROM and CALL_TO, two Twilio numbers on the account (E.164).
#   CALL_TO's Voice URL is pointed at the receiver for the duration of the test, then restored.
# Optional: SKIP_CALL=1, SKIP_JSON=1, KEEP_RESOURCES=1 (skip cleanup), PORT (default 5055)

set -euo pipefail

: "${TWILIO_ACCOUNT_SID:?set TWILIO_ACCOUNT_SID}"
: "${TWILIO_AUTH_TOKEN:?set TWILIO_AUTH_TOKEN}"
PORT="${PORT:-5055}"
SKIP_CALL="${SKIP_CALL:-0}"
SKIP_JSON="${SKIP_JSON:-0}"
KEEP_RESOURCES="${KEEP_RESOURCES:-0}"
if [ "$SKIP_CALL" != 1 ]; then
  : "${CALL_FROM:?set CALL_FROM (a Twilio number) or SKIP_CALL=1}"
  : "${CALL_TO:?set CALL_TO (a Twilio number whose Voice URL is borrowed) or SKIP_CALL=1}"
fi

for tool in curl python3 java javac mvn cloudflared; do
  command -v "$tool" >/dev/null || { echo "missing tool: $tool"; exit 1; }
done

SCRIPT_DIR="$(cd "$(dirname "$0")" && pwd)"
REPO_DIR="$(cd "$SCRIPT_DIR/../.." && pwd)"
WORK_DIR="$REPO_DIR/target/webhook-signature-e2e"
RECEIVER_LOG="$WORK_DIR/receiver.log"
TUNNEL_LOG="$WORK_DIR/cloudflared.log"
mkdir -p "$WORK_DIR"
: > "$RECEIVER_LOG"

WEBHOOKS=https://webhooks.twilio.com/v1/Webhooks
API="https://api.twilio.com/2010-04-01/Accounts/$TWILIO_ACCOUNT_SID"
EVENTS=https://events.twilio.com/v1

SHARED_KEY_ID="" SETTING_ID="" RULE_ID="" SINK_SID=""
TO_PN_SID="" TO_OLD_VOICE_URL="" TO_OLD_VOICE_METHOD=""
RECEIVER_PID="" TUNNEL_PID=""
FAILURES=0

step() { printf '\n== %s\n' "$*"; }
twilio() { curl -s -u "$TWILIO_ACCOUNT_SID:$TWILIO_AUTH_TOKEN" "$@"; }
# json <field.path> : read a field from JSON on stdin ("" if absent)
json() { python3 -c 'import json,sys
d=json.load(sys.stdin)
for k in sys.argv[1].split("."):
    d = d.get(k) if isinstance(d, dict) else None
print("" if d is None else d)' "$1"; }

cleanup() {
  set +e
  step "Cleanup"
  if [ -n "$TO_PN_SID" ]; then
    twilio -o /dev/null -X POST "$API/IncomingPhoneNumbers/$TO_PN_SID.json" \
      --data-urlencode "VoiceUrl=$TO_OLD_VOICE_URL" --data-urlencode "VoiceMethod=${TO_OLD_VOICE_METHOD:-POST}"
    echo "restored $CALL_TO Voice URL: ${TO_OLD_VOICE_URL:-<empty>}"
  fi
  [ -n "$RECEIVER_PID" ] && kill "$RECEIVER_PID" 2>/dev/null
  [ -n "$TUNNEL_PID" ] && kill "$TUNNEL_PID" 2>/dev/null
  if [ "$KEEP_RESOURCES" = 1 ]; then
    echo "KEEP_RESOURCES=1, left in place: rule=$RULE_ID setting=$SETTING_ID sharedKey=$SHARED_KEY_ID sink=$SINK_SID"
    return
  fi
  [ -n "$SINK_SID" ] && twilio -o /dev/null -X DELETE "$EVENTS/Sinks/$SINK_SID" && echo "deleted sink $SINK_SID"
  [ -n "$RULE_ID" ] && twilio -o /dev/null -X DELETE "$WEBHOOKS/Rules/$RULE_ID" && echo "deleted rule $RULE_ID"
  # A Setting can't be deleted while a Rule still points at it, and a SharedKey while a Setting uses it,
  # so retry while the Rule deletion propagates.
  local id code i
  for id in "$SETTING_ID:Settings" "$SHARED_KEY_ID:SharedKeys"; do
    [ "${id%%:*}" = "" ] && continue
    for i in 1 2 3 4 5 6 7 8 9 10 11 12; do
      code=$(twilio -o /dev/null -w '%{http_code}' -X DELETE "$WEBHOOKS/${id#*:}/${id%%:*}")
      case "$code" in 2*|404) echo "deleted ${id#*:} ${id%%:*} ($code)"; break;; esac
      sleep 10
    done
    case "$code" in 2*|404) ;; *) echo "could not delete ${id%%:*} (HTTP $code); delete it by hand";; esac
  done
}
trap cleanup EXIT

wait_for_log() { # wait_for_log <grep pattern> <seconds>
  local i
  for i in $(seq 1 "$2"); do grep -q "$1" "$RECEIVER_LOG" && return 0; sleep 1; done
  return 1
}

check() { # check <description> <grep pattern for the RESULT lines> <expected algorithm or ANY>
  local lines bad
  lines=$(grep "^RESULT" "$RECEIVER_LOG" | grep -- "$2" || true)
  if [ -z "$lines" ]; then
    echo "FAIL  $1: no webhook received"; FAILURES=$((FAILURES + 1)); return
  fi
  bad=$(echo "$lines" | grep -v "valid=true" || true)
  if [ "$3" != ANY ]; then
    bad="$bad$(echo "$lines" | grep -v "algorithm=$3" || true)"
  fi
  if [ -n "$bad" ]; then
    echo "FAIL  $1:"; echo "$lines" | sed 's/^/        /'; FAILURES=$((FAILURES + 1))
  else
    echo "PASS  $1 ($(echo "$lines" | wc -l | tr -d ' ') webhook(s))"; echo "$lines" | sed 's/^/        /'
  fi
}

step "1. Build twilio-java from this checkout and compile the receiver"
(cd "$REPO_DIR" && mvn -q compile -Dmaven.test.skip=true \
  && mvn -q dependency:build-classpath -Dmdep.includeScope=runtime -Dmdep.outputFile="$WORK_DIR/classpath.txt")
CP="$REPO_DIR/target/classes:$(cat "$WORK_DIR/classpath.txt")"
javac -proc:none -cp "$CP" -d "$WORK_DIR" "$SCRIPT_DIR/WebhookReceiver.java"
echo "ok"

step "2. Create a SharedKey (its secret is kept in memory only)"
SK_JSON=$(twilio -X POST "$WEBHOOKS/SharedKeys" -H 'Content-Type: application/json' -d '{"friendlyName":"sdk-e2e-signing-key"}')
SHARED_KEY_ID=$(echo "$SK_JSON" | json id)
SHARED_KEY_SECRET=$(echo "$SK_JSON" | json secret)
unset SK_JSON
[ -n "$SHARED_KEY_ID" ] && [ -n "$SHARED_KEY_SECRET" ] || { echo "SharedKey creation failed (does the account have the Webhooks API?)"; exit 1; }
echo "sharedKey=$SHARED_KEY_ID"

step "3. Create a Setting that signs with HMAC_SHA256 using that key"
SETTING_ID=$(twilio -X POST "$WEBHOOKS/Settings" -H 'Content-Type: application/json' -d '{
  "friendlyName":"sdk-e2e-hmac-sha256",
  "auth":{"source":"INLINE","value":{"scheme":"NONE"}},
  "signature":{"type":"SHARED_KEY","sharedKeyId":"'"$SHARED_KEY_ID"'","algorithm":"HMAC_SHA256"}}' | json id)
[ -n "$SETTING_ID" ] || { echo "Setting creation failed"; exit 1; }
echo "setting=$SETTING_ID"

step "4. Start a public tunnel to localhost:$PORT"
cloudflared tunnel --no-autoupdate --url "http://localhost:$PORT" > "$TUNNEL_LOG" 2>&1 &
TUNNEL_PID=$!
PUBLIC_URL=""
for i in $(seq 1 30); do
  PUBLIC_URL=$(grep -Eo 'https://[a-z0-9-]+\.trycloudflare\.com' "$TUNNEL_LOG" | head -1 || true)
  [ -n "$PUBLIC_URL" ] && break; sleep 1
done
[ -n "$PUBLIC_URL" ] || { echo "no tunnel URL, see $TUNNEL_LOG"; exit 1; }
echo "public url=$PUBLIC_URL"

step "5. Start the receiver"
PORT="$PORT" PUBLIC_URL="$PUBLIC_URL" SHARED_KEY_SECRET="$SHARED_KEY_SECRET" TWILIO_AUTH_TOKEN="$TWILIO_AUTH_TOKEN" \
  java -cp "$WORK_DIR:$CP" WebhookReceiver >> "$RECEIVER_LOG" 2>&1 &
RECEIVER_PID=$!
wait_for_log "listening on" 20 || { echo "receiver did not start, see $RECEIVER_LOG"; exit 1; }
for i in $(seq 1 60); do
  [ "$(curl -s -o /dev/null -w '%{http_code}' "$PUBLIC_URL/hooks/ping")" = 200 ] && break; sleep 2
done
echo "receiver reachable through the tunnel (the unsigned /hooks/ping probe is ignored by the checks)"

step "6. Webhooks Tests API: one signed webhook to /hooks/test (retries until the Setting propagates)"
for i in $(seq 1 18); do
  OUTCOME=$(twilio -X POST "$WEBHOOKS/Tests" -H 'Content-Type: application/json' \
    -d '{"url":"'"$PUBLIC_URL"'/hooks/test","settingId":"'"$SETTING_ID"'","method":"POST"}' | json result.outcome)
  echo "attempt $i: outcome=${OUTCOME:-<none>}"
  [ "$OUTCOME" = DELIVERED ] && break; sleep 10
done
wait_for_log "path=/hooks/test" 15 || true

step "7. Create a Rule so real traffic to $PUBLIC_URL/hooks/ is signed by the Setting"
RULE_JSON=$(twilio -X POST "$WEBHOOKS/Rules" -H 'Content-Type: application/json' -d '{
  "friendlyName":"sdk-e2e-rule","matchType":"PREFIX_MATCH","prefix":"'"$PUBLIC_URL"'/hooks/",
  "settings":[{"settingId":"'"$SETTING_ID"'","percentage":100}]}')
RULE_ID=$(echo "$RULE_JSON" | json rule.id)
OP_URL=$(echo "$RULE_JSON" | json operation.statusUrl)
[ -n "$RULE_ID" ] || { echo "Rule creation failed: $RULE_JSON"; exit 1; }
echo "rule=$RULE_ID, waiting for it to propagate"
for i in $(seq 1 24); do
  STATUS=$(twilio "$OP_URL" | json status)
  [ "$STATUS" = COMPLETED ] && break
  [ "$STATUS" = FAILED ] && { echo "Rule operation failed"; exit 1; }
  sleep 10
done
echo "rule status=$STATUS"
sleep 15 # small margin after COMPLETED before sending real traffic

if [ "$SKIP_CALL" != 1 ]; then
  step "8. Real traffic: call $CALL_FROM -> $CALL_TO (TwiML fetches + status callbacks, form-encoded)"
  TO_JSON=$(twilio -G "$API/IncomingPhoneNumbers.json" --data-urlencode "PhoneNumber=$CALL_TO")
  TO_PN_SID=$(echo "$TO_JSON" | python3 -c 'import json,sys; n=json.load(sys.stdin)["incoming_phone_numbers"]; print(n[0]["sid"] if n else "")')
  [ -n "$TO_PN_SID" ] || { echo "$CALL_TO is not a number on this account"; exit 1; }
  TO_OLD_VOICE_URL=$(echo "$TO_JSON" | python3 -c 'import json,sys; print(json.load(sys.stdin)["incoming_phone_numbers"][0]["voice_url"] or "")')
  TO_OLD_VOICE_METHOD=$(echo "$TO_JSON" | python3 -c 'import json,sys; print(json.load(sys.stdin)["incoming_phone_numbers"][0]["voice_method"] or "POST")')
  echo "borrowing $CALL_TO Voice URL (was: ${TO_OLD_VOICE_URL:-<empty>})"
  twilio -o /dev/null -X POST "$API/IncomingPhoneNumbers/$TO_PN_SID.json" \
    --data-urlencode "VoiceUrl=$PUBLIC_URL/hooks/voice-inbound" --data-urlencode "VoiceMethod=POST"
  CALL_SID=$(twilio -X POST "$API/Calls.json" \
    --data-urlencode "From=$CALL_FROM" --data-urlencode "To=$CALL_TO" \
    --data-urlencode "Url=$PUBLIC_URL/hooks/voice-outbound" --data-urlencode "Method=POST" \
    --data-urlencode "StatusCallback=$PUBLIC_URL/hooks/call-status" --data-urlencode "StatusCallbackMethod=POST" \
    --data-urlencode "StatusCallbackEvent=initiated" --data-urlencode "StatusCallbackEvent=ringing" \
    --data-urlencode "StatusCallbackEvent=answered" --data-urlencode "StatusCallbackEvent=completed" | json sid)
  [ -n "$CALL_SID" ] || { echo "call creation failed"; exit 1; }
  echo "call=$CALL_SID"
  for i in $(seq 1 30); do
    CALL_STATUS=$(twilio "$API/Calls/$CALL_SID.json" | json status)
    case "$CALL_STATUS" in completed|failed|busy|no-answer|canceled) break;; esac
    sleep 3
  done
  echo "call status=$CALL_STATUS"
  sleep 10 # the 'completed' status callback can trail the call ending
fi

if [ "$SKIP_JSON" != 1 ]; then
  step "9. JSON body: Event Streams webhook sink test event"
  SINK_SID=$(twilio -X POST "$EVENTS/Sinks" --data-urlencode "Description=sdk-e2e" --data-urlencode "SinkType=webhook" \
    --data-urlencode 'SinkConfiguration={"destination":"'"$PUBLIC_URL"'/hooks/events","method":"POST","batch_events":false}' | json sid)
  [ -n "$SINK_SID" ] || { echo "sink creation failed"; exit 1; }
  echo "sink=$SINK_SID"
  twilio -o /dev/null -X POST "$EVENTS/Sinks/$SINK_SID/Test"
  wait_for_log "path=/hooks/events" 60 || true
fi

step "Results"
check "Tests API, form body, SharedKey HMAC_SHA256" "path=/hooks/test " HMAC_SHA256
if [ "$SKIP_CALL" != 1 ]; then
  check "Voice call TwiML fetches, SharedKey HMAC_SHA256" "path=/hooks/voice-" HMAC_SHA256
  check "Voice call status callbacks, SharedKey HMAC_SHA256" "path=/hooks/call-status" HMAC_SHA256
fi
if [ "$SKIP_JSON" != 1 ]; then
  # Event Streams currently ignores Webhooks Rules and signs with the Auth Token (HMAC-SHA1), so any algorithm passes here.
  check "Event Streams, JSON body (bodySHA256)" "path=/hooks/events" ANY
fi
echo
echo "legacySha1Valid=false on SHA-256 lines shows the old 3-arg validate() rejects them; the new overload accepts them."
echo "receiver log: $RECEIVER_LOG"
if [ "$FAILURES" -gt 0 ]; then echo "RESULT: $FAILURES check(s) failed"; exit 1; fi
echo "RESULT: all checks passed"
