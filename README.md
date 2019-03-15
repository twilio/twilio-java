# twilio-java

[![Build Status](https://travis-ci.org/twilio/twilio-java.png?branch=master)](https://travis-ci.org/twilio/twilio-java)
[![Maven Central](https://img.shields.io/maven-central/v/com.twilio.sdk/twilio.svg)](http://mvnrepository.com/artifact/com.twilio.sdk/twilio)

## Recent Update

As of release 7.14.0, Beta and Developer Preview products are now exposed via
the main `twilio-java` artifact. Releases of the `alpha` branch have been
discontinued.

If you were using the `alpha` release line, you should be able to switch back
to the normal release line without issue.

If you were using the normal release line, you should now see several new
product lines that were historically hidden from you due to their Beta or
Developer Preview status. Such products are explicitly documented as
Beta/Developer Preview both in the Twilio docs and console, as well as through
in-line code documentation here in the library.

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

The documentation for the Twilio API can be found [here.](http://twilio.com/docs/api)

## Docker Image

The `Dockerfile` present in this repository and its respective `twilio/twilio-java` Docker image are currently used by Twilio for testing purposes only.

# Getting help

If you need help installing or using the library, please check the [Twilio Support Help Center](https://support.twilio.com) first, and [file a support ticket](https://twilio.com/help/contact) if you don't find an answer to your question.

If you've instead found a bug in the library or would like new features added, go ahead and open issues or pull requests against this repo!
