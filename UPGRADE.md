# Upgrade Guide

_`MAJOR` version bumps will have upgrade notes posted here._

[2020-09-28] 7.x.x to 8.x.x
-----------------------------

### Overview

Version `8.x.x` is the first version that officially drops support for Java 7. 

- Removal of dependencies offering functionality included in Java 8 and beyond:
    - [Guava functional interfaces](https://github.com/twilio/twilio-java/pull/574), for example:
        - `com.google.common.base.Joiner` -> `String.join()`
        - `com.google.common.collect.Lists` -> `Array.asList()`
        - `com.google.common.base.Function` -> `java.util.function.Function`
        - [`com.google.common.base.MoreObjects` -> `lombok.ToString`](https://github.com/twilio/twilio-java/pull/576)
    - [Guava concurrency, hashing, and charstreams](https://github.com/twilio/twilio-java/pull/575)
        - `com.google.common.hash.Hash*` -> `org.apache.commons.codec.digest.DigestUtils`
        - `com.google.common.io.CharStreams` -> `org.apache.commons.io.IOUtils`
        - `com.google.common.util.concurrent.*` -> `java.util.concurrent`
    - [Guava `com.google.common.collect.Range` removal]()
        - Example: `setMessageDate(final Range<LocalDate> rangeMessageDate)` -> `setMessageDate(final LocalDate messageDateBefore, final LocalDate messageDateAfter)`
    - [joda-time](https://github.com/twilio/twilio-java/pull/572)
        - `org.joda.time.DateTime` -> `java.time.ZonedDateTime`
        - `org.joda.time.LocalDate` -> `java.time.LocalDate`
- [Removal of deprecated classes and methods](https://github.com/twilio/twilio-java/pull/578):
    - `com.twilio.jwt.accesstoken.ConversationsGrant`
    - `com.twilio.jwt.accesstoken.IpMessagingGrant`
    - `com.twilio.jwt.accesstoken.VideoGrant.getConfigurationProfileSid()`
    - `com.twilio.jwt.accesstoken.VideoGrant.setConfigurationProfileSid()`
    - Renamed child TwiML methods:
        - `com.twilio.twiml.voice.Refer.Builder.referSip`
        - `com.twilio.twiml.voice.Say.Builder.ssml<Element>`


[2017-12-15] 7.16.x to 7.17.x
-----------------------------

### Overview

Version `7.17.x` is the first version containing our new auto-generated twiml classes. As such a fair amount of backwards incompatible changes were made to the twiml module to standardize it with the other twilio helper libraries as well as improve the experience of using TwiML. Some changes that affect all resources:

- Java types are now used when possible. 
    - Ie url parameters now take java's `URI` type, phone number parameters now take `PhoneNumber` types
    - This applies to both method parameters as well as fields on TwiML resources.
    - We have provided "upgrade" methods where possible to preserve backwards compatibility, for instance a method which takes a string url and upgrades it to a `URI` for the user.


### CHANGED - TwiML package structure reorganized

#### Rationale

Previously all TwiML objects lived in a single package. In `7.17.x` we've reorganized each TwiML resource into sub-packages such as `voice`, `messaging`, `fax` to reflect in which contexts they can be used. This is necessary as we add more and more TwiML objects.

#### Affected Resources
- com.twilio.twiml.Body -> com.twilio.twiml.messaging.Body
- com.twilio.twiml.Media -> com.twilio.twiml.messaging.Media
- com.twilio.twiml.Message -> com.twilio.twiml.messaging.Message
- com.twilio.twiml.Redirect -> com.twilio.twiml.messaging.Redirect
- com.twilio.twiml.Client -> com.twilio.twiml.voice.Client
- com.twilio.twiml.Conference -> com.twilio.twiml.voice.Conference
- com.twilio.twiml.Dial -> com.twilio.twiml.voice.Dial
- com.twilio.twiml.Echo -> com.twilio.twiml.voice.Echo
- com.twilio.twiml.Enqueue -> com.twilio.twiml.voice.Enqueue
- com.twilio.twiml.Gather -> com.twilio.twiml.voice.Gather
- com.twilio.twiml.Hangup -> com.twilio.twiml.voice.Hangup
- com.twilio.twiml.Leave -> com.twilio.twiml.voice.Leave
- com.twilio.twiml.Number -> com.twilio.twiml.voice.Number
- com.twilio.twiml.Pause -> com.twilio.twiml.voice.Pause
- com.twilio.twiml.Play -> com.twilio.twiml.voice.Play
- com.twilio.twiml.Queue -> com.twilio.twiml.voice.Queue
- com.twilio.twiml.Record -> com.twilio.twiml.voice.Record
- com.twilio.twiml.Redirect -> com.twilio.twiml.voice.Redirect
- com.twilio.twiml.Reject -> com.twilio.twiml.voice.Reject
- com.twilio.twiml.Say -> com.twilio.twiml.voice.Say
- com.twilio.twiml.Sim -> com.twilio.twiml.voice.Sim
- com.twilio.twiml.Sip -> com.twilio.twiml.voice.Sip
- com.twilio.twiml.Sms -> com.twilio.twiml.voice.Sms
- com.twilio.twiml.Task -> com.twilio.twiml.voice.Task

### CHANGED - Voice and Message Redirect TwiML resource requires `url` parameter

#### Affected Resources
- com.twilio.twiml.messaging.Redirect
- com.twilio.twiml.voice.Redirect

```java
// 7.16.x
Redirect redirect = new Redirect.Builder().url("http://example.com").build();
```

```java
// 7.17.x
Redirect redirect = new Redirect.Builder("https://example.com").build();
```

### CHANGED - ConferenceEvent TwiML enum renamed to Event

#### Affected Resources
- com.twilio.twiml.voice.Conference

```java
// 7.16.x
Conference conference = new Conference.Builder("my conference")
    .statusCallbackEvents(Lists.newArrayList(Conference.ConferenceEvent.END, Conference.ConferenceEvent.JOIN))
    .build();
```

```java
// 7.17.x
Conference conference = new Conference.Builder("my conference")
    .statusCallbackEvents(Lists.newArrayList(Conference.Event.END, Conference.Event.JOIN))
    .build();
```

### CHANGED Parameters with type `Method` now have type `HttpMethod` 

#### Rationale
This standardizes the type we use for Http methods with the rest of the library.

```java
// 7.16.x
import com.twilio.twiml.Method;
Conference conference = new Conference.Builder("my conference")
   .statusCallbackMethod(Method.GET)
   .build();
```

```java
// 7.17.x
import com.twilio.http.HttpMethod;
Conference conference = new Conference.Builder("my conference")
   .statusCallbackMethod(HttpMethod.GET)
   .build();
```

### CHANGED - Removed global Trim enum and move to within each resource that requires it

#### Rationale
This is a side effect of moving to generating the code, placing enums directly within resource that requires them is the pattern
used throughout the library.

```java
// 7.16.x
import com.twilio.twiml.Trim;
Conference conference = new Conference.Builder("my conference")
   .trim(Trim.DO_NOT_TRIM)
   .build();
```

```java
// 7.17.x
Conference conference = new Conference.Builder("my conference")
   .trim(Conference.Trim.DO_NOT_TRIM)
   .build();
```

### CHANGED - Task TwiML now requires body, `data` param renamed to `body`

```java
// 7.16.x
Task t = new Task.Builder().data("body").build();
```

```java
// 7.17.x
new Task.Builder("body").build()
```


### CHANGED - Enqueue TwiML `queueName` parameter changed to `name`

```java
// 7.16.x
Enqueue e = new Enqueue.Builder()
                       .queueName("queue")
                       .build();

System.out.println(e.queueName);
```

```java
// 7.17.x
Enqueue e = new Enqueue.Builder()
                       .name("queue")
                       .build();

System.out.println(e.name);
```

### REMOVED - EnqueueTask TwiML Resource

#### Rationale
`EnqueueTask` was a special case of the `Enqueue` TwiML resource to be able to nest a `Task` TwiML resource within an `Enqueue`, we've merged this functionality into `Enqueue`

```java
// 7.16.x
EnqueueTask enqueue = new EnqueueTask.Builder(new Task.Builder().build()).build()
```

```java
// 7.17.x
Enqueue enqueue = new Enqueue.Builder()
   .task(new Task.Builder().build())
   .build()
```

### CHANGED - TwiML Classes no longer directly constructible

#### Rationale
Some TwiML classes allowed direct construction of the resource in addition to using a Builder to build the resource. For consistency purposes we've standardized all resource construction through the Builder pattern.

```java
// 7.16.x
Hangup hangup = new Hangup();
Body body = new Body("This body!");
```

```java
// 7.17.x
Hangup hangup = new Hangup.Builder().build();
Body body = new Body.Builder("This body!").build();
```

### CHANGED - Global Event enum removed and moved to each resource which requires it

```java
// 7.16.x
import com.twilio.twiml.Event;
Number number = new Number.Builder("number")
    .statusCallbackEvents(Lists.newArrayList(Event.INITIATED))
    .build();
```

```java
// 7.17.x
Number number = new Number.Builder("number")
    .statusCallbackEvents(Lists.newArrayList(Number.Event.INITIATED))
    .build();
```

### CHANGED - TwiML resources which allowed freeform attributes via `options` now accept them via `option`

#### Affected Resources
- Pause
- Dial
- Conference

```java
// 7.16.x
Pause pause = new Pause.Builder()
       .options("foo", "bar")
       .build();
```

```java
// 7.17.x
Pause pause = new Pause.Builder()
       .option("foo", "bar")
       .build();
```

### CHANGED - Global TwiML Language enum moved to each resource which uses it

```java
// 7.16.x
Say say = new Say.Builder("I <3 Twilio")
   .language(Language.GB)
   .voice(Say.Voice.MAN)
   .build();
```

```java
// 7.17.x
Say say = new Say.Builder("I <3 Twilio")
   .language(Say.Language.EN_GB)
   .voice(Say.Voice.MAN)
   .build();
```

### CHANGED - Gather `input` parameter type changed from `String` to Gather.Input Enum

### Rationale
This was a rare instance where a parameter which had a small set of possible values did not have a proper Enum in `7.16.x`.

```java
// 7.16.x
Gather gather = new Gather.Builder()
   .input("dtmf")
   .build();
```

```java
// 7.17.x
Gather gather = new Gather.Builder()
   .input(Gather.Input.DTMF)
   .build();
```

### CHANGED - Gather `speechTimeout` parameter type changed from `int` to `String`

#### Rationale
SpeechTimeout accept either an integer or the string "auto". We didn't have a good way to model this in our code generator so had to leave the type as `String`.

```java
// 7.16.x
Gather gather = new Gather.Builder()
   .speechTimeout(5)
   .speechTimeout("auto")
   .build();
```

```java
// 7.17.x
Gather gather = new Gather.Builder()
   .input(Gather.Input.DTMF)
   .speechTimeout("5")
   .speechTimeout("auto")
   .build();
```


[2017-11-17] 7.15.x to 7.16.x
---------------------------

### CHANGED - Make `data` an optional parameter on Sync Document/List Item/Map Item update.

```java
// 7.15.x
Document.updater("IS123", "ET123", data).update();
SyncListItem.updater("IS123", "ES123", 1, data).update();
SyncMapItem.updater("IS123", "MP123", "myKey", data).update();
```

```java
// 7.16.x
Document.updater("IS123", "ET123").setData(data).update();
SyncListItem.updater("IS123", "ES123", 1).setData(data).update();
SyncMapItem.updater("IS123", "MP123", "myKey").setData(data).update();
```


[2017-05-24] 7.14.x to 7.15.x
---------------------------

### CHANGED - Make `body` an optional parameter on Chat Message creation.

```java
// 7.14.x
Message.creator("IS123", "CH123", "this is the body");
```

```java
// 7.15.x
MessageCreator creator = Message.creator("IS123", "CH123");
```

### CHANGED - `data`, `apn`, `gcm`, `fcm`, `sms` parameters in `Notifications` create resource are maps instead of strings.

```java
// 7.14.x
NotificationCreator notificationCreator = Notification.creator("IS123");
notificationCreator.setData("{\"key\": \"value\"}");
notificationCreator.setApn("{\"key\": \"value\"}");
```

```java
// 7.15.x
Map<String, Object> data = new HashMap<~>();
data.put("key", "value);

NotificationCreator notificationCreator = Notification.creator("IS123");
notificationCreator.setData(data);
notificationCreator.setApn(data);
```


[2017-05-24] 7.10.x to 7.11.x
---------------------------

### CHANGED - Rename `Recording` to `RoomRecording` in TwilioVideo

#### Affected Resources
    - `src/main/java/com/twilio/rest/video/v1/room/Recording.java`
    - `src/main/java/com/twilio/rest/video/v1/room/RecordingFetcher.java`
    - `src/main/java/com/twilio/rest/video/v1/room/RecordingReader.java`
    - `src/test/java/com/twilio/rest/video/v1/room/RecordingTest.java`

```java
// 7.10.x
Recording.fetcher();
Recording.reader();
```

```java
// 7.11.x
RoomRecording.fetcher();
RoomRecording.reader();
```


[2017-05-19] 7.9.x to 7.10.x
---------------------------

### CHANGED - Remove convenience method `getSid()` on records that do not have a sid

#### Rationale
There already exists a getter for the resource

#### Affected Resources
  - `src/main/java/com/twilio/rest/lookups/v1/PhoneNumber.java`

```java
// 7.9.x
resource.getSid();
```

```java
// 7.10.x
resource.getPhoneNumber().toString();
```

  - `src/main/java/com/twilio/rest/pricing/v1/messaging/Country.java`
  - `src/main/java/com/twilio/rest/pricing/v1/phonenumber/Country.java`
  - `src/main/java/com/twilio/rest/pricing/v1/voice/Country.java`

```java
// 7.9.x
resource.getSid();
```

```java
// 7.10.x
resource.getIsoCountry();
```

  - `src/main/java/com/twilio/rest/pricing/v1/voice/Number.java`

```java
// 7.9.x
resource.getSid();
```

```java
// 7.10.x
resource.getNumber().toString();
```


[2017-02-01] 7.4.x to 7.5.x
---------------------------

### CHANGED - Remove required updating of `friendlyName` parameter when updating TaskRouter Activities 
  - Updating `friendlyName` is now done through a setter instead of the constructor

#### Rationale
The `friendlyName` parameter is optional when updating TaskRouter Activities

#### Affected Resources
  - TaskRouter Activities

#### 7.4.x
```java
Activity.updater("WS123", "AC123", "friendlyName").update();

```

#### 7.5.x
```java
Activity.updater("WS123", "AC123").setFriendlyName("friendlyName").update();
```

### CHANGED - Filterting of Tasks by `assignmentStatus` now takes a list of statuses

#### Rationale
Filtering by Tasks exposes the ability to filter by multiple status. The library is being updated
to support this feature

#### 5.3.x
```java
Task.reader("WS123").setAssignmentStatus(Task.Status.ASSIGNED).read();
```

#### 5.4.x
```java
Task.reader("WS123").setAssignmentStatus(Lists.newArrayList(
    Task.Status.ASSIGNED.toString(), 
    Task.Status.CANCELED.toString()
)).read();
```
