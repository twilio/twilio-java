[![Build Status](https://travis-ci.org/twilio/twilio-java.png?branch=master)](https://travis-ci.org/twilio/twilio-java)
[![Maven Central](https://img.shields.io/maven-central/v/com.twilio.sdk/twilio.svg)](http://mvnrepository.com/artifact/com.twilio.sdk/twilio)


# Installing

twilio-java uses Maven.  At present the jars *are* available from a public [maven](http://mvnrepository.com/artifact/com.twilio.sdk/twilio) repository.

Use the following dependency in your project to grab via Maven:

       <dependency>
          <groupId>com.twilio.sdk</groupId>
          <artifactId>twilio</artifactId>
          <version>7.X.X</version>
          <scope>compile</scope>
       </dependency>

or Gradle:
```groovy
compile "com.twilio.sdk:twilio:7.X.X"
````
       
If you want to compile it yourself, here's how:

    $ git clone git@github.com:twilio/twilio-java
    $ cd twilio-java
    $ mvn install       # Requires maven, download from http://maven.apache.org/download.html

# Quickstart

### Send a SMS
```java
String accountSid = "ACXXXXXX"; // Your Account SID from www.twilio.com/user/account
String authToken = "XXXXXXXX"; // Your Auth Token from www.twilio.com/user/account

Twilio.init(accountSid, authToken);

Message message = Message.creator(
    new PhoneNumber("+15558881234"),  // To number
    new PhoneNumber("+15559994321"),  // From number
    "Hello world!"                    // SMS body
).create();

System.out.println(message.getSid());
```

### Make a call
```java
String accountSid = "ACXXXXXX"; // Your Account SID from www.twilio.com/user/account
String authToken = "XXXXXXXX"; // Your Auth Token from www.twilio.com/user/account

Twilio.init(accountSid, authToken);

Call call = Call.creator(
    new PhoneNumber("+15558881234"),  // To number
    new PhoneNumber("+15559994321"),  // From number
    
    // Read TwiML at this URL when a call connects (hold music)
    new URI("http://twimlets.com/holdmusic?Bucket=com.twilio.music.ambient")
).create();

System.out.println(call.getSid());
```

### Generating TwiML
To control phone calls, your application needs to output [TwiML](http://www.twilio.com/docs/api/twiml/). 
TwiML in twilio-java now use the builder pattern!
```java
TwiML twiml = new VoiceResponse.Builder()
    .say(new Say.Builder("Hello World!").build())
    .play(new Play.Builder("https://api.twilio.com/cowbell.mp3").loop(5).build())
    .build();
```

That will output XML that looks like this:
```
<Response>
    <Say>Hello World!</Say>
    <Play loop="5">https://api.twilio.com/cowbell.mp3</Play>
</Response>
```


# Documentation

The documentation for the Twilio API can be found [here.](http://twilio.com/docs)

# Getting help

If you need help installing or using the library, please contact Twilio Support at help@twilio.com first. Twilio's Support staff are well-versed in all of the Twilio Helper Libraries, and usually reply within 24 hours.

If you've instead found a bug in the library or would like new features added, go ahead and open issues or pull requests against this repo!
