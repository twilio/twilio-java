# Upgrade Guide

_After `7.4.0` all `MINOR` and `MAJOR` version bumps will have upgrade notes 
posted here._

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
