#!/bin/sh
mkdir build
javac -d build -cp lib/commons-codec-1.3.jar src/com/twilio/sdk/*.java
javac -d build -cp lib/commons-codec-1.3.jar src/com/twilio/sdk/verbs/*.java
jar cvf twiliorest.jar -C build/ . 
rm -rf build
