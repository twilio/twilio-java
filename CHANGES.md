twilio-java changelog
=====================

[2017-07-12] Version 7.13.0-alpha-1
------------------------------------

- Change git tagging scheme to be consistent with other twilio libraries.
- Fix crashes on Google App Engine when using default NetworkHttpClient. Issue
  #377. Thanks to @katafractari for helping identify the bug and test the fix.

**Api**
- Rename incorrectly named `AnnounceUrlMethod` to `AnnounceMethod` on Participants.
- Update `AnnounceMethod` parameter naming for consistency
- Add new UsageRecord categoreies for Rooms and Speech Recognition.

**Messaging**
- Fix incorrectly typed capabilities property for PhoneNumbers.

**Notify**
- Add `ToBinding` optional parameter on Notifications resource creation. Accepted values are json strings.

**Preview**
- Add `verification_attempts` to HostedNumberOrders.
- Add `status_callback_url` and `status_callback_method` to HostedNumberOrders.

**Video**
- Filter recordings by date using the parameters `DateCreatedAfter` and `DateCreatedBefore`.
- Override the default time-to-live of a recording's media URL through the `Ttl` parameter (in seconds, default value is 3600).
- Add query parameters `SourceSid`, `Status`, `DateCreatedAfter` and `DateCreatedBefore` to the convenience method for retrieving Room recordings.

**Wireless**
- Added national and international data limits to the RatePlans resource.


[2017-06-16] Version 7.12.0-alpha-1
----------------------------------
- Remove client-side max page size validation.
- Add `locality` field to `AvailablePhoneNumbers`.
- Add `origin` field to `IncomingPhoneNumbers`.
- Add `inLocality` parameter to `AvailablePhoneNumbers`.
- Add `origin` parameter to `IncomingPhoneNumbers`.
- Add new sync categories to `UsageRecords`.
- Add `getPage()` method for reentrant paging.
- Add `input` to `<Gather>`.
- Remove mandatory url parameter in `<Play>`.
- Parallelize tests.
- Update `Language` enum.
- Add Alexa support to Notify.

[2017-05-24] Version 7.11.0-alpha-1
----------------------------------
- Add HostedNumbers preview support.
- Add Proxy preview support.
- Add BulkExports preview support.

[2017-05-22] Version 7.10.1-alpha-1
----------------------------------
- Add video.twilio.com
- Add additional usage categories
- Fix Client Validation URL encoding
- Close all connections in finally block
- Remove convenience method `getSid()` on records that do not have a sid
- *Alpha Changes:*
    - Add wireless.twilio.com
    - Add sync.twilio.com
    - Add ability to Delete Faxes

[2017-04-27] Version 7.9.1-alpha-1
----------------------------------
- Remove conference participant `Beep` and `ConferenceRecord` enums, use `String` instead (backwards incompatible).
- Add `recordingChannels`, `recordingStatusCallback`, `recordingStatusCallbackMethod`, `sipAuthUsername`, `sipAuthPassword`, `region`, `conferenceRecordingStatusCallback`, `conferenceRecordingStatusCallbackMethod` parameter support to conference participant creation.
- Update missing categories in Usage Trigger enums.
- Alpha Changes:
    - Add `smartEncoding` parameter to messaging Service.
    - Make `endpoint ` parameter optional for notify service bindings (backwards incompatible).
    - Add `segments` property to notify service notifications.
    - Add `logEnabled` property to notify services.
    - New Notify resources: Segment, SegmentMembership, UserBinding, User
    - Add ability to delete Wireless RatePlans
    - Add `groupingSid` parameter to Video Recordings.
    - Remove `startTime` property from Video Rooms (backwards incompatible).
    - Replace `startTimeBefore/After` filtering with `dateCreatedBefore/After` on Video Rooms (backwards incompatible).\

[2017-04-18] Version 7.9.0-alpha-2
--------------------------
- Add Twilio Programmable Chat version 2
- Allow updating `AccountSid` on `IncomingPhoneNumber`s
- Add `smartEncoding` to Messaging Services
- All delete's of `RatePlans`
- Include only populated fields in `VideoGrant`
- Deprecate `ConversationsGrant`

[2017-04-12] Version 7.8.1-alpha1
--------------------------
- Add TaskRouterGrant.
- Update VideoGrant.
    - Add `room` as preferred grant granularity.
    - Deprecate setting `configurationProfileSid` on grant.

[2017-04-01] Version 7.8.0-alpha-1
--------------------------
- Add Twilio Programmable Fax.
- Fix a bug where unexpected JSON properties caused exceptions.

[2017-03-22] Version 7.7.2-alpha-1
--------------------------
- Add Answering Machine Detection to Call creation
- Add `WRAPPING` entry to Status for Task
- Add `recordParticipantsOnConnect` to Rooms
- **Twilio Chat**
  - Add `limits` map to Service
  - Add `limitsChannelMembers` and `limitsUserChannels` field to ServiceUpdater

[2017-03-21] Version 7.7.1-alpha-1
--------------------------
- Allow customizing configuration for NetworkHttpClient

[2017-03-10] Version 7.7.0-alpha-1
--------------------------
- Fix bug in Enum parsing
- Bump Jackson to 2.8.7
- Remove `Sandbox`
- Add `Recordings` to `video.twilio.com`
- Add `messaging.twilio.com`

[2017-02-28] Version 7.6.0-alpha-1
--------------------------
- Namespace all path parameters
- Rename `devices` to `sims` in `wireless`
- Add `rooms` API

[2017-02-10] Version 7.5.0-alpha-2
--------------------------
- Add `validated` field to Addresses
- Add FCM Credential support
- Add `Order` parameter to Chat Message filterting
- Remove required updating of `friendlyName` parameter when updating TaskRouter Activities
- Accept multiple `assignmentStatus`'s when filtering `Tasks`
- Fix Workflow json generation
- Add TaskRouterGrant
- Add `recordingStatusCallback` and `recordingStatusCallbackMethod` to Record TwiML
- Add `AssignedAddOns` to `IncomingPhoneNumbers`
- Add `marketplace` to Preview
- Support FCM notification types
- **Sync**
    - Add `Documents`
    - Add `Permissions` for Lists and Maps

[2017-01-10] Version 7.4.0-alpha-1
--------------------------
- Added new AddOnResults API.
- Allow undocumented TwiML voice parameters for Conference, Dial, and Pause PR #317.
- Fix dates being serialized to null in some locales PR #320.
- Add `emergencyEnabled` field to Addresses.
- Add support for emergency phone numbers.
- Add support for ending conferences via the API.
- Add `region` field to Conferences.

- **Wireless**
    - Creating RatePlan now takes single `renewal` string instead of `renewalPeriod` and `renewalUnits` (backwards incompatible).
    - Change RatePlan `renewal` field to String (backwards incompatible).

- **Twilio Chat**
    - Add `membersCount` and `messagesCount` to Channel.
    - Add support for filtering on channel type when reading list of Channels.
    - Add `last_consumed_message_index` and `last_consumption_timestamp` fields to Member.
    - Remove ability to update Message body, was never supported by API (backwards incompatible).
    - Add support for new UserChannel Resource.

- **Taskrouter**
    - Add default worker fetch policy PR #313.
    - Add queue ordering for Workspace, TaskQueue.
    - Remove ability to specify `attributes` and `workflowSid` on Task creation (backwards incompatible).
    - Add `addons`, `taskQueueFriendlyName`, `workflowFriendlyName`, metdata to Task.
    - Use DateTimes for WorkspaceStatistics filtering (backwards incompatible).
    - Fix TaskQueueStatistics `cumulative` field incorrect type (backwards incompatible).
    - Fix TaskQueuesStatistics `cumulative` field incorrect type (backwards incompatible).
    - Remove ability to set `friendlyName` on TaskQueueStatistics (backwards incompatible).

[2016-11-30] Version 7.3.0-alpha-1
--------------------------
- Add `recordingStatusCallback` and `recordingStatusCallbackMethod` to Conference TwiML
- Fix TaskRouter Capabaility generation
- Address potential NPE in `ApiException`

[2016-11-16] Version 7.2.0-alpha-1
--------------------------
- Add PublicKey Credentials.
- Add create/update to RatePlans.
- Use separate enum for Updating  Call `status`. This enum only exposes the statuses that a Call can be updated to.
- Move the `body` parameter to be required in updating Messages.
- Move the `friendlyName` parameter to be r  equired in creating Queues.
- Update to latest Notify.
- Add Particpant creation to Conferences.
- Add filtering by `CallSid` to Recordings.
- Add missing fields to Call Recordings.
- Add missing fields to Conferences.
- Add missing fields to IncomingPhoneNumbers.
- Add missing fields to Messages.

- **Twilio Chat**
    - Add Invites
    - Add `reachabilityEnabled`, `preWebhookUrl`, `postWebhookUrl`, `webhookMethod`, `webhookFilters`, `notifications` to Services.
    - Add `attributes`, `friendlyName`, `isOnline`, `isNotifiable` to Users.
    - Add `lastConsumedMessageIndex`, `lastConsumptionTimestamp` to Members.
    - Add `attributes`, `index` to Messages.
    - Add ability to update Members.
    - Add filtering by `identity` on Members.
    - Add webhook related parameters to Service updates.
    - Remove updating of `type` on Channels.

[2016-10-05] Version 7.1.0-alpha-1
--------------------------
- Update Usage Records to use `LocalDate` instead of `DateTime`
- Update TwiML Generator to reflect latest docs:
    - Add `statusCallbackEvent`, `statusCallbackMethod`, `statusCallback` to `Conference`
    - Add `recordingStatusCallback`, `recordingStatusCallbackMethod` to `Dial`

[2016-10-04] Version 7.0.0
--------------------------
**New Major Version**

The newest version of the `twilio-java` helper library!

This version brings a host of changes to update and modernize the `twilio-java` helper library. It is auto-generated to produce a more consistent and correct product.

- [Migration Guide](https://www.twilio.com/docs/libraries/java/migration-guide)
- [Full API Documentation](https://twilio.github.io/twilio-java/)
- [General Documentation](https://www.twilio.com/docs/libraries/java)


[2016-01-26] Version 5.9.0
--------------------------
- Add support for WorkerReservations in TaskRouter
- Add support for filterFriendlyName in TaskRouter WorkflowRules
- Fix TaskRouter paging


[2015-12-16] Version 5.8.0
--------------------------
- Add support for IP Messaging


[2015-12-14] Version 5.7.1
--------------------------
- Add new getUsageRecords to support query by intervals
- Fix for ListResource's & NextGenListResource's ListIterator.next()


[2015-12-08] Version 5.7.0
--------------------------
- Change NBF to be optional parameter in AccessToken


[2015-12-03] Version 5.6.0
--------------------------
- Add access tokens


[2015-11-25] Version 5.5.1
--------------------------
- Exposed Recording's PriceUnit field


[2015-11-18] Version 5.5.0
--------------------------
- Fix authentication using key and secret


[2015-10-30] Version 5.4.0
--------------------------
- Add MessagingServiceSid


[2015-10-28] Version 5.3.0
--------------------------
- Add Keys endpoint


[2015-10-02] Version 5.2.1
--------------------------
- Normalized the Number type from spaces to underscore in Pricing Phone Number Country


[2015-09-24] Version 5.2.0
--------------------------
- Add support for TaskRouter reservations
- Add support for TaskRouter TwiML verbs


[2015-09-21] Version 5.1.0
--------------------------
- Add support for messaging in Twilio Pricing API
- Add support for Elastic SIP Trunking's API


[2015-09-14] Version 5.0.0
--------------------------
- Remove deprecated total and numpages from ListResource and response parsers
