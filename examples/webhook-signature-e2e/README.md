# End-to-end test: HMAC-SHA256 webhook signatures

This checks that `RequestValidator` in this checkout validates real Twilio webhooks signed with a
Webhooks SharedKey using HMAC-SHA256, and that existing HMAC-SHA1 (Auth Token) validation still works.

`run.sh` does every step below against your Twilio account and cleans up afterwards.

## How a signed webhook reaches your app

```text
event on your account (call, message, ...)
  -> Twilio looks up the webhook URL for that event
  -> a Webhooks Rule matches the URL and picks a Setting
  -> the Setting signs with its SharedKey + algorithm (HMAC_SHA1 or HMAC_SHA256)
  -> POST to the URL with X-Twilio-Signature and
     X-Twilio-Webhooks-Signature-Settings: key=<sharedKeyId>,algorithm=<algorithm>
  -> your app rebuilds the signature with RequestValidator and compares
```

Without a matching Rule, Twilio signs with the account Auth Token using HMAC-SHA1 and sends no
`X-Twilio-Webhooks-Signature-Settings` header.

The signed string is the same for both algorithms: the full URL, then each POST parameter name and
value sorted by name. For JSON bodies Twilio appends `?bodySHA256=<hex>` to the URL, signs the URL,
and the receiver also checks the body hash.

Validating with the SDK:

```java
String settings = request.getHeader(RequestValidator.SIGNATURE_SETTINGS_HEADER);
String algorithm = RequestValidator.getSignatureAlgorithm(settings); // HMAC_SHA1 when the header is absent
RequestValidator validator = new RequestValidator(settings != null ? sharedKeySecret : authToken);

validator.validate(url, params, signature, algorithm);   // form body
validator.validate(url, jsonBody, signature, algorithm); // JSON body (bodySHA256)
validator.validate(url, params, signature);              // existing code: HMAC-SHA1, unchanged
```

## Prerequisites

- `TWILIO_ACCOUNT_SID` and `TWILIO_AUTH_TOKEN` in your shell, for an account with the Webhooks API.
- `java` 11 or later, `javac`, `mvn`, `python3`, `curl`.
- `cloudflared` (`brew install cloudflared`) for a public HTTPS URL. No Cloudflare account is needed.
- For the voice test, two Twilio numbers on the account:
  - `CALL_FROM` is only the caller ID.
  - `CALL_TO` receives the call. Its Voice URL is pointed at the receiver during the test and
    restored at the end, even if the script fails.

The voice test places one short call, which costs a few cents.

## Run

From the repository root:

```bash
export TWILIO_ACCOUNT_SID=AC...
export TWILIO_AUTH_TOKEN=...
CALL_FROM=+1XXXXXXXXXX CALL_TO=+1YYYYYYYYYY examples/webhook-signature-e2e/run.sh
```

Options:

| Variable | Effect |
|---|---|
| `SKIP_CALL=1` | Skip the voice call; `CALL_FROM` and `CALL_TO` aren't needed |
| `SKIP_JSON=1` | Skip the Event Streams JSON test |
| `KEEP_RESOURCES=1` | Don't delete the SharedKey, Setting, Rule and sink at the end |
| `PORT` | Local receiver port, default `5055` |

The script takes about four minutes, mostly waiting for the Setting and the Rule to propagate.

## What the script does

| Step | Action | Proves |
|---|---|---|
| 1 | Compiles this checkout and `WebhookReceiver.java` | The receiver uses the local `RequestValidator` |
| 2 | Creates a SharedKey. Its secret stays in memory and is passed to the receiver | |
| 3 | Creates a Setting: `SHARED_KEY` signature, `HMAC_SHA256` | |
| 4 | Starts a `cloudflared` quick tunnel to `localhost:$PORT` | |
| 5 | Starts the receiver with the tunnel URL as `PUBLIC_URL` | |
| 6 | `POST /v1/Webhooks/Tests` to `/hooks/test` with the Setting, retried until the Setting propagates | A Twilio-signed HMAC-SHA256 webhook validates |
| 7 | Creates a `PREFIX_MATCH` Rule for `<tunnel>/hooks/` and waits for its Operation to complete | |
| 8 | Points `CALL_TO` at `/hooks/voice-inbound`, calls it from `CALL_FROM` with a `/hooks/voice-outbound` TwiML URL and `/hooks/call-status` callbacks | Real event traffic is signed with HMAC-SHA256 and validates |
| 9 | Creates an Event Streams webhook sink at `/hooks/events` and sends its test event | A JSON webhook (`bodySHA256`) validates |
| | Restores `CALL_TO`, deletes the sink, Rule, Setting and SharedKey, stops the tunnel and receiver | |

## Expected output

```text
== Results
PASS  Tests API, form body, SharedKey HMAC_SHA256 (1 webhook(s))
        RESULT path=/hooks/test ... signed=SHARED_KEY algorithm=HMAC_SHA256 valid=true legacySha1Valid=false
PASS  Voice call TwiML fetches, SharedKey HMAC_SHA256 (2 webhook(s))
PASS  Voice call status callbacks, SharedKey HMAC_SHA256 (4 webhook(s))
PASS  Event Streams, JSON body (bodySHA256) (1 webhook(s))
        RESULT path=/hooks/events contentType=application/json bodySHA256InUrl=true signed=AUTH_TOKEN algorithm=HMAC_SHA1 valid=true ...
RESULT: all checks passed
```

How to read a `RESULT` line:

- `valid` is the result of the new `validate(..., algorithm)` overload with the algorithm from the header.
- `legacySha1Valid` is the old 3-argument `validate(...)` on the same request. It is `false` for
  HMAC-SHA256 webhooks, which is the gap the SDK change closes.
- `signed=AUTH_TOKEN` means the request had no `X-Twilio-Webhooks-Signature-Settings` header, so no
  Rule applied and Twilio signed with the Auth Token using HMAC-SHA1.

The full receiver log is written to `target/webhook-signature-e2e/receiver.log`.

## Known behavior (as of 2026-10-22)

- Voice webhooks (TwiML fetches and status callbacks) go through Webhooks Rules and are signed with the
  SharedKey.
- Event Streams sink deliveries don't go through Webhooks Rules. They're signed with the Auth Token
  using HMAC-SHA1, even when the sink URL matches a Rule. So the JSON test covers the `bodySHA256`
  path, but not JSON with HMAC-SHA256. The script reports that as a pass for any algorithm.

## Troubleshooting

| Symptom | Cause |
|---|---|
| `SharedKey creation failed` | The account can't use the Webhooks API, or the credentials are wrong |
| Step 6 shows `outcome=FAILED` or no outcome on every attempt | The Setting hasn't propagated yet (30 to 90 seconds), or the tunnel isn't reachable |
| `valid=false` with `signed=SHARED_KEY` | The secret doesn't belong to the key named in the header, or the URL the receiver rebuilt differs from the one Twilio called |
| Voice lines show `signed=AUTH_TOKEN` | The Rule hadn't propagated when the call was made. Rerun |
| `could not delete Setting` at cleanup | The Rule deletion was still propagating. Delete it later with `DELETE https://webhooks.twilio.com/v1/Webhooks/Settings/<id>` |
