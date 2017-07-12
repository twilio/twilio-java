# Upgrade Guide

_After `7.4.0` all `MINOR` and `MAJOR` version bumps will have upgrade notes 
posted here._


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
