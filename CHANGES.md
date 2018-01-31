twilio-java changelog
=====================

[2018-01-30] Version 7.17.6
----------------------------
**Api**
- Add `studio-engagements` usage key

**Preview**
- Remove Studio Engagement Deletion

**Studio**
- Initial Release

**Video**
- [omit] Beta: Allow updates to `SubscribedTracks`.
- Add `SubscribedTracks`.
- Add track name to Video Recording resource
- Add Composition and Composition Media resources


[2018-01-22] Version 7.17.1
----------------------------
**Api**
- Add `conference_sid` property on Recordings
- Add proxy and sms usage key

**Chat**
- Make user channels accessible by identity
- Add notifications logs flag parameter

**Fax**
- Added `ttl` parameter
  `ttl` is the number of minutes a fax is considered valid.

**Preview**
- Add `call_delay`, `extension`, `verification_code`, and `verification_call_sids`.
- Add `failure_reason` to HostedNumberOrders.
- Add DependentHostedNumberOrders endpoint for AuthorizationDocuments preview API.


[2017-12-15] Version 7.17.0
----------------------------
**Library**
- Autogenerate TwiML Resources *(breaking change)*.
- Improved consistency between TwiML classes and faster updates.
- Allow setting arbitrary attributes on any TwiML resource.
- Use proper Java types in TwiML resources *(breaking change)*.
- Restructure TwiML package *(breaking change)*.

**Api**
- Add `voip`, `national`, `shared_cost`, and `machine_to_machine` sub-resources to `/2010-04-01/Accounts/{AccountSid}/AvailablePhoneNumbers/{IsoCountryCode}/`
- Add programmable video keys

**Preview**
- Add `verification_type` and `verification_document_sid` to HostedNumberOrders.

**Proxy**
- Fixed typo in session status enum value


[2017-12-01] Version 7.16.1
----------------------------
**Api**
- Use the correct properties for Dependent Phone Numbers of an Address *(breaking change)*
- Update Call Recordings with the correct properties

**Preview**
- Add `status` and `email` query param filters for AuthorizationDocument list endpoint

**Proxy**
- Added DELETE support to Interaction
- Standardized enum values to dash-case
- Rename Service#friendly_name to Service#unique_name

**Video**
- Remove beta flag from `media_region` and `video_codecs`

**Wireless**
- Bug fix: Changed `operator_mcc` and `operator_mnc` in `DataSessions` subresource from `integer` to `string`


[2017-11-17] Version 7.16.0
----------------------------
**Sync**
- Add TTL support for Sync objects *(breaking change)*
  - The required `data` parameter on the following actions is now optional: "Update Document", "Update Map Item", "Update List Item"
  - New actions available for updating TTL of Sync objects: "Update List", "Update Map", "Update Stream"

**Video**
- [bi] Rename `RoomParticipant` to `Participant`
- Add Recording Settings resource
- Expose EncryptionKey and MediaExternalLocation properties in Recording resource


[2017-11-10] Version 7.15.6
----------------------------
**Accounts**
- Add AWS credential type

**Preview**
- Removed `iso_country` as required field for creating a HostedNumberOrder.

**Proxy**
- Added new fields to Service: geo_match_level, number_selection_behavior, intercept_callback_url, out_of_session_callback_url


[2017-11-03] Version 7.15.5
----------------------------
**Api**
- Add programmable video keys

**Video**
- Add `Participants`


[2017-10-27] Version 7.15.4
----------------------------
**Chat**
- Add Binding resource
- Add UserBinding resource


[2017-10-20] Version 7.15.3
----------------------------
**Api**
- Add `address_sid` param to IncomingPhoneNumbers create and update
- Add 'fax_enabled' option for Phone Number Search


[2017-10-13] Version 7.15.2
----------------------------
**Api**
- Add `smart_encoded` param for Messages
- Add `identity_sid` param to IncomingPhoneNumbers create and update

**Preview**
- Make 'address_sid' and 'email' optional fields when creating a HostedNumberOrder
- Add AuthorizationDocuments preview API.

**Proxy**
- Initial Release

**Wireless**
- Added `ip_address` to sim resource


[2017-10-06] Version 7.15.1
----------------------------
**Preview**
- Add `acc_security` (authy-phone-verification) initial api

**Taskrouter**
- Less verbose naming of cumulative and real time statistics *(breaking change)*


[2017-09-29] Version 7.15.0
----------------------------
**Chat**
- Make member accessible through identity
- Make channel subresources accessible by channel unique name
- Set get list 'max_page_size' parameter to 100
- Add service instance webhook retry configuration
- Add media message capability
- Make `body` an optional parameter on Message creation. *(breaking change)*

**Notify**
- `data`, `apn`, `gcm`, `fcm`, `sms` parameters in `Notifications` create resource are maps instead of strings. *(breaking change)*

**Taskrouter**
- Add new query ability by TaskChannelSid or TaskChannelUniqueName
- Move Events, Worker, Workers endpoint over to CPR
- Add new RealTime and Cumulative Statistics endpoints

**Video**
- Create should allow an array of video_codecs.
- Add video_codecs as a property of room to make it externally visible.


[2017-09-15] Version 7.14.6
----------------------------

- Add speechTimeout to Gather verb.

**Api**
- Add `sip_registration` property on SIP Domains
- Add new video and market usage category keys


[2017-09-01] Version 7.14.5
----------------------------
**Sync**
- Add support for Streams

**Wireless**
- Added DataSessions sub-resource to Sims.


[2017-08-25] Version 7.14.4
----------------------------
**Video**
- New `media_region` parameter when creating a room, which controls which region media will be served out of.


[2017-08-22] Version 7.14.3
----------------------------
**Library**
- Add `getLastRequest` and `getLastResponse` methods to HttpClient class to provide debugging information.

**Api**
- Update `status` enum for Recordings to include 'failed'
- Add `error_code` property on Recordings

**Chat**
- Add mutable parameters for channel, members and messages


[2017-08-18] Version 7.14.2
----------------------------
**Api**
- Add VoiceReceiveMode {'voice', 'fax'} option to IncomingPhoneNumber UPDATE requests

**Chat**
- Add channel message media information
- Add service instance message media information

**Preview**
- Add DeployedDevices.

**Sync**
- Add support for Service Instance unique names


[2017-08-11] Version 7.14.1
----------------------------
**Api**
- Add New wireless usage keys added
- Add `auto_correct_address` param for Addresses create and update
- Add .hashCode(), .equals(), and .toString() methods

**Preview**
- Removed 'email' from bulk_exports configuration api [bi]. No migration plan needed because api has not been used yet.
- Add AvailableNumbers resource.

**Video**
- Add `video_codec` enum and `video_codecs` parameter, which can be set to either `VP8` or `H264` during room creation.
- Restrict recordings page size to 100
- Add query parameters `SourceSid`, `Status`, `DateCreatedAfter` and `DateCreatedBefore` to the convenience method for retrieving Room recordings.


[2017-07-27] Version 7.14.0
----------------------------
This release adds Beta and Preview products to main artifact.

Previously, Beta and Preview products were only included in the `alpha`
artifact. They are now being included in the main artifact to ease product
discoverability and the collective operational overhead of maintaining multiple
artifacts per library.

**Api**
- Remove unused `encryption_type` property on Recordings *(breaking change)*

**Messaging**
- Fix incorrectly typed capabilities property for PhoneNumbers.

**Notify**
- Add `ToBinding` optional parameter on Notifications resource creation. Accepted values are json strings.

**Preview**
- Add `sms_application_sid` to HostedNumberOrders.

**Taskrouter**
- Fully support conference functionality in reservations.


[2017-07-19] Version 7.13.1
----------------------------
**Api**
- Update `status` enum for Messages to include 'accepted'

[2017-07-12] Version 7.13.0
----------------------------
- Change git tagging scheme to be consistent with other twilio libraries.
- Fix crashes on Google App Engine when using default NetworkHttpClient. Issue #377. Thanks to @katafractari for helping identify the bug and test the fix.

**Api**
- Fix incorrectly named `AnnounceUrlMethod` to `AnnounceMethod` parameter naming on Conference Participant Updating.
- Add `encryptionType` and `encryptionDetails` support to Call Recordings.
- Add new UsageRecord categories for Rooms and Speech Recognition.

**Notify**
- Add `ToBinding` optional parameter on Notifications resource creation. Accepted values are json strings.

**Preview**
- Add `verificationAttempts` to HostedNumberOrders.
- Add `statusCallbackUrl` and `statusCallbackMethod` to HostedNumberOrders.

**Video**
- Filter recordings by date using the parameters `DateCreatedAfter` and `DateCreatedBefore`.
- Override the default time-to-live of a recording's media URL through the `Ttl` parameter (in seconds, default value is 3600).
- Add query parameters `SourceSid`, `Status`, `DateCreatedAfter` and `DateCreatedBefore` to the convenience method for retrieving Room recordings.

**Wireless**
- Added national and international data limits to the RatePlans resource.


[2017-06-16] Version 7.12.0
--------------------------
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

[2017-05-24] Version 7.11.0
--------------------------
- Rename `Recording` to `RoomRecording` in Twilio Video

[2017-05-19] Version 7.10.0
--------------------------
- Add video.twilio.com
- Add additional usage categories
- Fix Client Validation URL encoding
- Close all connections in finally block
- Remove convenience method `getSid()` on records that do not have a sid

[2017-04-27] Version 7.9.1
--------------------------
- Remove conference participant `Beep` and `ConferenceRecord` enums, use `String` instead (backwards incompatible).
- Add `recordingChannels`, `recordingStatusCallback`, `recordingStatusCallbackMethod`, `sipAuthUsername`, `sipAuthPassword`, `region`, `conferenceRecordingStatusCallback`, `conferenceRecordingStatusCallbackMethod` parameter support to conference participant creation.
- Update missing categories in Usage Trigger enums.

[2017-04-18] Version 7.9.0
--------------------------
- Add Twilio Programmable Chat version 2
- Allow updating `AccountSid` on `IncomingPhoneNumber`s
- Include only populated fields in `VideoGrant`
- Deprecate `ConversationsGrant`

[2017-03-22] Version 7.8.1
--------------------------
- Add `validityPeriod` optional parameter to Message creation.
- Add TaskRouterGrant.
- Update VideoGrant.
    - Add `room` as preferred grant granularity.
    - Deprecate setting `configurationProfileSid` on grant.

[2017-03-24] Version 7.8.0
--------------------------
- Fix a bug where unexpected JSON properties caused exceptions.

[2017-03-22] Version 7.7.2
--------------------------
- Add Answering Machine Detection to Call creation
- Add `WRAPPING` entry to Status for Task

- **Twilio Chat**
  - Add `limits` map to Service
  - Add `limitsChannelMembers` and `limitsUserChannels` field to ServiceUpdater

[2017-03-21] Version 7.7.1
--------------------------
- Allow customizing configuration for NetworkHttpClient

[2017-03-10] Version 7.7.0
--------------------------
- Bump Jackson dependency to 2.8.7
- Fix bug in Enum serialization
- Delete `Sandbox`s

[2017-02-28] Version 7.6.0
--------------------------
- Add `ValidationToken`s for Client Validation
- Add `accounts.twilio.com` subdomain
    - Add `PublicKey` resource
- Namespace all path parameters

[2017-02-10] Version 7.5.0
--------------------------
- Add `validated` field to Addresses
- Add FCM Credential support
- Add `Order` parameter to Chat Message filterting
- Remove required updating of `friendlyName` parameter when updating TaskRouter Activities
- Accept multiple `assignmentStatus`'s when filtering `Tasks`
- Fix Workflow json generation
- Add TaskRouterGrant
- Add `recordingStatusCallback` and `recordingStatusCallbackMethod` to Record TwiML

[2017-01-10] Version 7.4.0
--------------------------
- Added new AddOnResults API.
- Allow undocumented TwiML voice parameters for Conference, Dial, and Pause PR #317.
- Fix dates being serialized to null in some locales PR #320.
- Add `emergencyEnabled` field to Addresses.
- Add support for emergency phone numbers.
- Add support for ending conferences via the API.
- Add `region` field to Conferences.

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

[2016-11-30] Version 7.3.0
--------------------------
- Add `recordingStatusCallback` and `recordingStatusCallbackMethod` to Conference TwiML
- Fix TaskRouter Capabaility generation
- Address potential NPE in `ApiException`

[2016-11-16] Version 7.2.0
--------------------------
- Use separate enum for Updating Call `status`. This enum only exposes the statuses that a Call can be updated to.
- Move the `body` parameter to be required in updating Messages.
- Move the `friendlyName` parameter to be required in creating Queues.
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

[2016-10-05] Version 7.1.0
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
