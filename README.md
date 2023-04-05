# twilio-java

[![Tests](https://github.com/twilio/twilio-java/actions/workflows/test-and-deploy.yml/badge.svg)](https://github.com/twilio/twilio-java/actions/workflows/test-and-deploy.yml)
[![Maven Central](https://img.shields.io/maven-central/v/com.twilio.sdk/twilio.svg)](https://mvnrepository.com/artifact/com.twilio.sdk/twilio)
[![Learn with TwilioQuest](https://img.shields.io/static/v1?label=TwilioQuest&message=Learn%20to%20contribute%21&color=F22F46&labelColor=1f243c&style=flat-square&logo=data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAAIAAAACACAMAAAD04JH5AAAASFBMVEUAAAAZGRkcHBwjIyMoKCgAAABgYGBoaGiAgICMjIyzs7PJycnMzMzNzc3UoBfd3d3m5ubqrhfrMEDu7u739/f4vSb/3AD///9tbdyEAAAABXRSTlMAAAAAAMJrBrEAAAKoSURBVHgB7ZrRcuI6EESdyxXGYoNFvMD//+l2bSszRgyUYpFAsXOeiJGmj4NkuWx1Qeh+Ekl9DgEXOBwOx+Px5xyQhDykfgq4wG63MxxaR4ddIkg6Ul3g84vCIcjPBA5gmUMeXESrlukuoK33+33uID8TWeLAdOWsKpJYzwVMB7bOzYSGOciyUlXSn0/ABXTosJ1M1SbypZ4O4MbZuIDMU02PMbauhhHMHXbmebmALIiEbbbbbUrpF1gwE9kFfRNAJaP+FQEXCCTGyJ4ngDrjOFo3jEL5JdqjF/pueR4cCeCGgAtwmuRS6gDwaRiGvu+DMFwSBLTE3+jF8JyuV1okPZ+AC4hDFhCHyHQjdjPHUKFDlHSJkHQXMB3KpSwXNGJPcwwTdZiXlRN0gSp0zpWxNtM0beYE0nRH6QIbO7rawwXaBYz0j78gxjokDuv12gVeUuBD0MDi0OQCLvDaAho4juP1Q/jkAncXqIcCfd+7gAu4QLMACCLxpRsSuQh0igu0C9Svhi7weAGZg50L3IE3cai4IfkNZAC8dfdhsUD3CgKBVC9JE5ABAFzg4QL/taYPAAWrHdYcgfLaIgAXWJ7OV38n1LEF8tt2TH29E+QAoDoO5Ve/LtCQDmKM9kPbvCEBApK+IXzbcSJ0cIGF6e8gpcRhUDogWZ8JnaWjPXc/fNnBBUKRngiHgTUSivSzDRDgHZQOLvBQgf8rRt+VdBUUhwkU6VpJ+xcOwQUqZr+mR0kvBUgv6cB4+37hQAkXqE8PwGisGhJtN4xAHMzrsgvI7rccXqSvKh6jltGlrOHA3Xk1At3LC4QiPdX9/0ndHpGVvTjR4bZA1ypAKgVcwE5vx74ulwIugDt8e/X7JgfkucBMIAr26ndnB4UCLnDOqvteQsHlgX9N4A+c4cW3DXSPbwAAAABJRU5ErkJggg==)](https://twil.io/learn-open-source)

## Documentation

The documentation for the Twilio API can be found [here][apidocs].

The Java library documentation can be found [here][libdocs].

## Versions

`twilio-java` uses a modified version of [Semantic Versioning](https://semver.org) for all changes. [See this document](VERSIONS.md) for details.

### TLS 1.2 Requirements

New accounts and subaccounts are now required to use TLS 1.2 when accessing the REST API. ["Upgrade Required" errors](https://www.twilio.com/docs/api/errors/20426) indicate that TLS 1.0/1.1 is being used.

### Supported Java Versions

This library supports the following Java implementations:

- OpenJDK 8
- OpenJDK 11
- OpenJDK 17
- OracleJDK 8
- OracleJDK 11
- OracleJDK 17

For Java 7 support, use `twilio-java` major version `7.X.X`.

## Installation

`twilio-java` uses Maven. At present the jars _are_ available from a public [maven](https://mvnrepository.com/artifact/com.twilio.sdk/twilio) repository.

Use the following dependency in your project to grab via Maven:

```xml
<dependency>
  <groupId>com.twilio.sdk</groupId>
  <artifactId>twilio</artifactId>
  <version>9.X.X</version>
  <scope>compile</scope>
</dependency>
```

or Gradle:

```groovy
implementation "com.twilio.sdk:twilio:9.X.X"
```

If you want to compile it yourself, here's how:

```shell
git clone git@github.com:twilio/twilio-java
cd twilio-java
mvn install       # Requires maven, download from https://maven.apache.org/download.html
```

If you want to build your own .jar, execute the following from within the cloned directory:

```shell
mvn package
```

If you run into trouble with local tests, use:

```shell
mvn package -Dmaven.test.skip=true
```

### Test your installation

Try sending yourself an SMS message, like this:

```java
import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;

public class Example {

  // Find your Account Sid and Token at console.twilio.com
  public static final String ACCOUNT_SID = "ACXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX";
  public static final String AUTH_TOKEN = "your_auth_token";

  public static void main(String[] args) {
    Twilio.init(ACCOUNT_SID, AUTH_TOKEN);

    Message message = Message
      .creator(
        new PhoneNumber("+15558675309"),
        new PhoneNumber("+15017250604"),
        "This is the ship that made the Kessel Run in fourteen parsecs?"
      )
      .create();

    System.out.println(message.getSid());
  }
}
```

> **Warning**
> It's okay to hardcode your credentials when testing locally, but you should use environment variables to keep them secret before committing any code or deploying to production. Check out [How to Set Environment Variables](https://www.twilio.com/blog/2017/01/how-to-set-environment-variables.html) for more information.

## Usage

### Initialize the Client

```java
import com.twilio.Twilio;
import com.twilio.exception.AuthenticationException;

public class Example {

  private static final String ACCOUNT_SID =
    "ACXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX";
  private static final String AUTH_TOKEN = "your_auth_token";

  public static void main(String[] args) throws AuthenticationException {
    Twilio.init(ACCOUNT_SID, AUTH_TOKEN);
  }
}
```

### Environment Variables

`twilio-java` supports the credentials, region, and edge values stored in the following environment variables:

- `TWILIO_ACCOUNT_SID`
- `TWILIO_AUTH_TOKEN`
- `TWILIO_REGION`
- `TWILIO_EDGE`

If using these variables, the above client initialization can be skipped.

### Make a Call

```java
import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Call;
import com.twilio.type.PhoneNumber;
import java.net.URI;
import java.net.URISyntaxException;

public class Example {

  public static final String ACCOUNT_SID = "ACXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX";
  public static final String AUTH_TOKEN = "your_auth_token";

  public static void main(String[] args) throws URISyntaxException {
    Twilio.init(ACCOUNT_SID, AUTH_TOKEN);

    Call call = Call
      .creator(
        new PhoneNumber("+14155551212"),
        new PhoneNumber("+15017250604"),
        new URI("http://demo.twilio.com/docs/voice.xml")
      )
      .create();

    System.out.println(call.getSid());
  }
}
```

### Get an existing Call

```java
import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Call;

public class Example {

  public static final String ACCOUNT_SID = "ACXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX";
  public static final String AUTH_TOKEN = "your_auth_token";

  public static void main(String[] args) {
    Twilio.init(ACCOUNT_SID, AUTH_TOKEN);

    Call call = Call.fetcher("CA42ed11f93dc08b952027ffbc406d0868").fetch();

    System.out.println(call.getTo());
  }
}
```

### Iterate through records

The library automatically handles paging for you. With the `read` method, you can specify the number of records you want to receive (`limit`) and the maximum size you want each page fetch to be (`pageSize`). The library will then handle the task for you, fetching new pages under the hood as you iterate over the records.

For more information, view the [auto-generated library docs](https://www.twilio.com/docs/libraries/reference/twilio-java/).

#### Use the `read` method

```java
import com.twilio.Twilio;
import com.twilio.base.ResourceSet;
import com.twilio.rest.api.v2010.account.Call;

public class Example {

  public static final String ACCOUNT_SID = "ACXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX";
  public static final String AUTH_TOKEN = "your_auth_token";

  public static void main(String[] args) {
    Twilio.init(ACCOUNT_SID, AUTH_TOKEN);

    ResourceSet<Call> calls = Call.reader().read();

    for (Call call : calls) {
      System.out.println(call.getDirection());
    }
  }
}
```

### Specify Region and/or Edge

To take advantage of Twilio's [Global Infrastructure](https://www.twilio.com/docs/global-infrastructure), specify the target Region and/or Edge for the client:

```java
Twilio.init(accountSid, authToken);
Twilio.setRegion("au1");
Twilio.setEdge("sydney");
```

This will result in the `hostname` transforming from `api.twilio.com` to `api.sydney.au1.twilio.com`.

### Enable Debug Logging

This library uses SLF4J for logging. Consult the [SFL4J documentation](http://slf4j.org/docs.html) for information about logging configuration.

For example, if you are using `log4j`:

- Make sure you have `log4j-slf4j-impl`, `log4j-core` and `log4j-api` in your `pom.xml` file
- Define the logging level for the Twilio HTTP client in your configuration. For example, in `src/main/resources/log4j2.xml`:

  ```xml
  <?xml version="1.0" encoding="UTF-8"?>
  <Configuration status="WARN">
      <Appenders>
          <Console name="Console" target="SYSTEM_OUT">
              <PatternLayout pattern="%d{HH:mm:ss.SSS} %-5level - %msg%n"/>
          </Console>
      </Appenders>
      <Loggers>
          <!--Your Twilio logging configuration goes here-->
          <Logger name="com.twilio.http" level="debug" additivity="false">
              <AppenderRef ref="Console"/>
          </Logger>
          <Root level="info">
              <AppenderRef ref="Console"/>
          </Root>
      </Loggers>
  </Configuration>
  ```

### Handle Exceptions

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

### Use a Client With PKCV Authentication

Additional documentation here: https://twilio.com/docs/iam/pkcv/quickstart

```java
ValidationClient httpClient = new ValidationClient(ACCOUNT_SID, key.getSid(), signingKey.getSid(), pair.getPrivate());
TwilioRestClient client = new TwilioRestClient.Builder(signingKey.getSid(), signingKey.getSecret())
    .accountSid(ACCOUNT_SID)
    .httpClient(httpClient)
    .build();
```

### Generate TwiML

To control phone calls, your application needs to output [TwiML][twiml].

TwiML in twilio-java now use the builder pattern!

```java
TwiML twiml = new VoiceResponse.Builder()
    .say(new Say.Builder("Hello World!").build())
    .play(new Play.Builder("https://api.twilio.com/cowbell.mp3").loop(5).build())
    .build();
```

That will output XML that looks like this:

```xml
<Response>
    <Say>Hello World!</Say>
    <Play loop="5">https://api.twilio.com/cowbell.mp3</Play>
</Response>
```

### Use a custom HTTP Client

To use a custom HTTP client with this helper library, please see the [advanced example of how to do so](./advanced-examples/custom-http-client.md).

## Docker image

The `Dockerfile` present in this repository and its respective `twilio/twilio-java` Docker image are currently used by Twilio for testing purposes only.

## Getting Help

If you need help installing or using the library, please check the [Twilio Support Help Center](https://support.twilio.com) first, and [file a support ticket](https://twilio.com/help/contact) if you don't find an answer to your question.

If you've instead found a bug in the library or would like new features added, go ahead and open issues or pull requests against this repo!

[apidocs]: https://www.twilio.com/docs/api
[twiml]: https://www.twilio.com/docs/api/twiml
[libdocs]: https://twilio.github.io/twilio-java
