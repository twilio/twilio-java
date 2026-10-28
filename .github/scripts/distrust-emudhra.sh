#!/usr/bin/env bash
# Removes the emSign roots behind the compromised eMudhra intermediates (CVE-2026-111943) from the
# active JDK's cacerts, then fails if any remain. Temurin 17.0.21+ and 21.0.13+ already ship without
# them, so this is a no-op there; Temurin 8 and 11 have no fixed release yet.
#
# Usage: distrust-emudhra.sh [cacerts]   (defaults to the cacerts of $JAVA_HOME)
set -euo pipefail

CACERTS="${1:-}"
if [ -z "$CACERTS" ]; then
  CACERTS="$JAVA_HOME/lib/security/cacerts"
  [ -f "$CACERTS" ] || CACERTS="$JAVA_HOME/jre/lib/security/cacerts" # Java 8 layout
fi

# Same set Temurin 17.0.21 removed. emSign Root CA - G1 is not part of the compromised chains.
SUBJECTS='^Owner: CN=emSign (ECC Root CA - G3|ECC Root CA - C3|Root CA - C1),'

distrusted_aliases() {
  keytool -list -v -keystore "$CACERTS" -storepass changeit 2>/dev/null |
    awk -v subjects="$SUBJECTS" '/^Alias name: /{alias=substr($0, 13)} $0 ~ subjects {print alias}'
}

distrusted_aliases | while IFS= read -r alias; do
  echo "Removing \"$alias\" from $CACERTS"
  keytool -delete -alias "$alias" -keystore "$CACERTS" -storepass changeit
done

if [ -n "$(distrusted_aliases)" ]; then
  echo "::error::$CACERTS still trusts the compromised eMudhra roots"
  exit 1
fi
echo "$CACERTS does not trust the compromised eMudhra roots"
