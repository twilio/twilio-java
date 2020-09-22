# twilio-java

[![Build Status](https://travis-ci.org/twilio/twilio-java.png?branch=main)](https://travis-ci.org/twilio/twilio-java)
[![Maven Central](https://img.shields.io/maven-central/v/com.twilio.sdk/twilio.svg)](https://mvnrepository.com/artifact/com.twilio.sdk/twilio)
[![Learn with TwilioQuest](https://img.shields.io/static/v1?label=TwilioQuest&message=Learn%20to%20contribute%21&color=F22F46&labelColor=1f243c&style=flat-square&logo=data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAAIAAAACACAMAAAD04JH5AAAASFBMVEUAAAAZGRkcHBwjIyMoKCgAAABgYGBoaGiAgICMjIyzs7PJycnMzMzNzc3UoBfd3d3m5ubqrhfrMEDu7u739/f4vSb/3AD///9tbdyEAAAABXRSTlMAAAAAAMJrBrEAAAKoSURBVHgB7ZrRcuI6EESdyxXGYoNFvMD//+l2bSszRgyUYpFAsXOeiJGmj4NkuWx1Qeh+Ekl9DgEXOBwOx+Px5xyQhDykfgq4wG63MxxaR4ddIkg6Ul3g84vCIcjPBA5gmUMeXESrlukuoK33+33uID8TWeLAdOWsKpJYzwVMB7bOzYSGOciyUlXSn0/ABXTosJ1M1SbypZ4O4MbZuIDMU02PMbauhhHMHXbmebmALIiEbbbbbUrpF1gwE9kFfRNAJaP+FQEXCCTGyJ4ngDrjOFo3jEL5JdqjF/pueR4cCeCGgAtwmuRS6gDwaRiGvu+DMFwSBLTE3+jF8JyuV1okPZ+AC4hDFhCHyHQjdjPHUKFDlHSJkHQXMB3KpSwXNGJPcwwTdZiXlRN0gSp0zpWxNtM0beYE0nRH6QIbO7rawwXaBYz0j78gxjokDuv12gVeUuBD0MDi0OQCLvDaAho4juP1Q/jkAncXqIcCfd+7gAu4QLMACCLxpRsSuQh0igu0C9Svhi7weAGZg50L3IE3cai4IfkNZAC8dfdhsUD3CgKBVC9JE5ABAFzg4QL/taYPAAWrHdYcgfLaIgAXWJ7OV38n1LEF8tt2TH29E+QAoDoO5Ve/LtCQDmKM9kPbvCEBApK+IXzbcSJ0cIGF6e8gpcRhUDogWZ8JnaWjPXc/fNnBBUKRngiHgTUSivSzDRDgHZQOLvBQgf8rRt+VdBUUhwkU6VpJ+xcOwQUqZr+mR0kvBUgv6cB4+37hQAkXqE8PwGisGhJtN4xAHMzrsgvI7rccXqSvKh6jltGlrOHA3Xk1At3LC4QiPdX9/0ndHpGVvTjR4bZA1ypAKgVcwE5vx74ulwIugDt8e/X7JgfkucBMIAr26ndnB4UCLnDOqvteQsHlgX9N4A+c4cW3DXSPbwAAAABJRU5ErkJggg==)](https://twil.io/learn-open-source)

## Announcements

* **The default branch name for this repository has been changed to `main` as of 07/27/2020.**

## Documentation

The documentation for the Twilio API can be found [here][apidocs].

The Java library documentation can be found [here][libdocs].

## Versions

`twilio-java` uses a modified version of [Semantic Versioning](https://semver.org) for all changes. [See this document](VERSIONS.md) for details.

### TLS 1.2 Requirements

New accounts and subaccounts are now required to use TLS 1.2 when accessing the REST API. ["Upgrade Required" errors](https://www.twilio.com/docs/api/errors/20426) indicate that TLS 1.0/1.1 is being used.

### Supported Java Versions

This library supports the following Java implementations:

* OpenJDK 8
* OpenJDK 11
* OracleJDK 8
* OracleJDK 11

For Java 7 support, use `twilio-java` major version `7.X.X`.

## Installation

twilio-java uses Maven. At present the jars *are* available from a public [maven](https://mvnrepository.com/artifact/com.twilio.sdk/twilio) repository.

Use the following dependency in your project to grab via Maven:

```
       <dependency>
          <groupId>com.twilio.sdk</groupId>
          <artifactId>twilio</artifactId>
          <version>8.X.X</version>
          <scope>compile</scope>
       </dependency>
```

or Gradle:
```groovy
implementation "com.twilio.sdk:twilio:8.X.X"
```

If you want to compile it yourself, here's how:

    $ git clone git@github.com:twilio/twilio-java
    $ cd twilio-java
    $ mvn install       # Requires maven, download from https://maven.apache.org/download.html

If you want to build your own .jar, execute the following from within the cloned directory:

    $ mvn package

If you run into trouble with local tests, use:

    $ mvn package -Dmaven.test.skip=true

## Quickstart

### Initialize the Client

```java
// Find your Account SID and Auth Token at twilio.com/console
// DANGER! This is insecure. See http://twil.io/secure
String accountSid = "ACXXXXXX";
String authToken = "XXXXXXXX";

Twilio.init(accountSid, authToken);
```

### Specify Region and/or Edge

To take advantage of Twilio's [Global Infrastructure](https://www.twilio.com/docs/global-infrastructure), specify the target Region and/or Edge for the client:

```java
Twilio.init(accountSid, authToken);
Twilio.setRegion("au1");
Twilio.setEdge("sydney");
```

This will result in the `hostname` transforming from `api.twilio.com` to `api.sydney.au1.twilio.com`.

### Send an SMS

```java
Message message = Message.creator(
    new PhoneNumber("+15558881234"),  // To number
    new PhoneNumber("+15559994321"),  // From number
    "Hello world!"                    // SMS body
).create();

System.out.println(message.getSid());
```

### Make a Call

```java
Call call = Call.creator(
    new PhoneNumber("+15558881234"),  // To number
    new PhoneNumber("+15559994321"),  // From number

    // Read TwiML at this URL when a call connects (hold music)
    new URI("http://twimlets.com/holdmusic?Bucket=com.twilio.music.ambient")
).create();

System.out.println(call.getSid());
```

### Handling Exceptions

```java
import com.twilio.exception.ApiException;

try {
    Message message = Message.creator(
        new PhoneNumber("+15558881234"),  // To number
        new PhoneNumber("+15559994321"),  // From number
        "Hello world!"                    // SMS body
    ).create();
    
    System.out.println(message.getSid());
} catch (final ApiException e) {
    System.err.println(e);
}
```

For more descriptive exception types, please see the [Twilio documentation](https://www.twilio.com/docs/libraries/java/usage-guide#exceptions).

### Using a Different Client

```java
TwilioRestClient client = new TwilioRestClient.Builder(accountSid, authToken).build();

Message message = Message.creator(
    new PhoneNumber("+15558881234"),  // To number
    new PhoneNumber("+15559994321"),  // From number
    "Hello world!"                    // SMS body
).create(client);  // Pass the client here

System.out.println(message.getSid());
```

### Generating TwiML

To control phone calls, your application needs to output [TwiML][twiml].

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

## Using a Custom HTTP Client

To use a custom HTTP client with this helper library, please see the [Twilio documentation](https://www.twilio.com/docs/libraries/java/custom-http-clients-java).

## Docker Image

The `Dockerfile` present in this repository and its respective `twilio/twilio-java` Docker image are currently used by Twilio for testing purposes only.

## Getting Help

If you need help installing or using the library, please check the [Twilio Support Help Center](https://support.twilio.com) first, and [file a support ticket](https://twilio.com/help/contact) if you don't find an answer to your question.

If you've instead found a bug in the library or would like new features added, go ahead and open issues or pull requests against this repo!

[apidocs]: https://www.twilio.com/docs/api
[twiml]: https://www.twilio.com/docs/api/twiml
[libdocs]: https://twilio.github.io/twilio-java
