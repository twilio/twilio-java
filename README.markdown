## Java Twilio SDK

### DESCRIPTION
The Twilio SDK serves two purposes
    - simplifies the process of makes calls to the Twilio REST.
    - facilitates creation of TwiML with a Response Library
    
The Twilio REST API lets to you initiate outgoing calls, list previous call,
and much more.  

See http://www.twilio.com/docs for more information.


### BUILDING LIBRARY
First, review the TwilioRestExample.java file, entering in your AcountSid,
AccountToken, and the other test data you would like to use (callSid's, phone
numbers, etc). Next, you will need to build the twiliorest.jar file. If you
are using ANT, use the 'jar' target to generate the twiliorest.jar file. There
is also a build.sh file to compile the source and generate the jar file
without requiring ant.

### USING REST API =====
Example code can be found in TwilioRestExample.java
To run the example code type: 
java -cp twiliorest.jar:lib/commons-codec-1.3.jar com.twilio.sdk.TwilioRestExample

### USING TWIML RESPONSE LIBRARY =====
Example code can be found in TwiMLResponseExample.java
To run the example code type: 
java -cp twiliorest.jar:lib/commons-codec-1.3.jar com.twilio.sdk.verbs.TwiMLResponseExample

### FILES ===== 
* **src**: the java source files for the example and Twilio rest api.
* **lib**: the library directory with a copy of the apache commons codec library 
* **build.xml**: ANT build file for building the twiliorest.jar file
* **build.sh**: alternate build script for users without ANT.  

### LICENSE =====
The Twilio SDK is distributed under the MIT License. 
Please see the included license
