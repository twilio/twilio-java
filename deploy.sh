#!/bin/bash

VERSION=$1
if [ -z "$VERSION" ]; then
    VERSION=`cat pom.xml | grep "<version>" | head -1 | awk -F ">" '{print $2}' | awk -F "-SNAPSHOT" '{print $1}'`
fi

echo "Bumping to version: $VERSION"
OLD=`grep VERSION src/main/java/com/twilio/Twilio.java | head -1 | awk -F "\"" '{print $2}'`
sed -i.bak -e "s/$OLD/$VERSION/g" src/main/java/com/twilio/Twilio.java

echo "Committing changes"
git commit -am "Bump version for release"

echo "Pushing to maven"
mvn -Pgpg release:clean
mvn -Pgpg release:prepare
mvn -Pgpg release:perform
