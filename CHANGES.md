twilio-java changelog
=====================

[2023-07-27] Version 9.9.1
--------------------------
**Api**
- Added `voice-intelligence`, `voice-intelligence-transcription` and `voice-intelligence-operators` to `usage_record` API.
- Added `tts-google` to `usage_record` API.

**Lookups**
- Add new `disposable_phone_number_risk` package to the lookup response

**Verify**
- Documentation of list attempts API was improved by correcting `date_created_after` and `date_created_before` expected date format.
- Documentation was improved by correcting `date_created_after` and `date_created_before` expected date format parameter on attempts summary API.
- Documentation was improved by adding `WHATSAPP` as optional valid parameter on attempts summary API.

**Twiml**
- Added support for he-il inside of ssm_lang.json that was missing
- Added support for he-il language in say.json that was missing
- Add `statusCallback` and `statusCallbackMethod` attributes to `<Siprec>`.


[2023-07-14] Version 9.9.0
--------------------------
**Flex**
- Adding `interaction_context_sid` as optional parameter in Interactions API

**Messaging**
- Making visiblity public for tollfree_verification API

**Numbers**
- Remove Sms capability property from HNO creation under version `/v2` of HNO API. **(breaking change)**
- Update required properties in LOA creation under version `/v2` of Authorization document API. **(breaking change)**

**Taskrouter**
- Add api to fetch task queue statistics for multiple TaskQueues

**Verify**
- Add `RiskCheck` optional parameter on Verification creation.

**Twiml**
- Add Google Voices and languages


[2023-06-28] Version 9.8.0
--------------------------
**Library - Chore**
- [PR #754](https://github.com/twilio/twilio-java/pull/754): Add content type enum to request. Thanks to [@sbansla](https://github.com/sbansla)!
- [PR #753](https://github.com/twilio/twilio-java/pull/753): added content type enum. Thanks to [@sbansla](https://github.com/sbansla)!

**Lookups**
- Add `reassigned_number` package to the lookup response

**Numbers**
- Add hosted_number_order under version `/v2`.
- Update properties in Porting and Bulk Porting APIs. **(breaking change)**
- Added bulk Portability API under version `/v1`.
- Added Portability API under version `/v1`.


[2023-06-15] Version 9.7.0
--------------------------
**Api**
- Added `content_sid` as conditional parameter
- Removed `content_sid` as optional field **(breaking change)**

**Insights**
- Added `annotation` to list summary output


[2023-06-01] Version 9.6.2
--------------------------
**Api**
- Add `Trim` to create Conference Participant API

**Intelligence**
- First public beta release for Voice Intelligence APIs with client libraries

**Messaging**
- Add new `errors` attribute to us_app_to_person resource. This attribute will provide additional information about campaign registration errors.


[2023-05-18] Version 9.6.1
--------------------------
**Library - Fix**
- [PR #743](https://github.com/twilio/twilio-java/pull/743): Removing org.json as this repo doesnot contain a direct dependency. Thanks to [@AsabuHere](https://github.com/AsabuHere)!

**Conversations**
- Added  `AddressCountry` parameter to Address Configuration endpoint, to support regional short code addresses
- Added query parameters `start_date`, `end_date` and `state` in list Conversations resource for filtering

**Insights**
- Added annotations parameters to list summary api

**Messaging**
- Add GET domainByMessagingService endpoint to linkShortening service
- Add `disable_https` to link shortening domain_config properties

**Numbers**
- Add bulk_eligibility api under version `/v1`.


[2023-05-04] Version 9.6.0
--------------------------
**Conversations**
- Remove `start_date`, `end_date` and `state` query parameters from list operation on Conversations resource **(breaking change)**

**Twiml**
- Add support for new Amazon Polly voices (Q1 2023) for `Say` verb


[2023-04-19] Version 9.5.0
--------------------------
**Library - Docs**
- [PR #739](https://github.com/twilio/twilio-java/pull/739): consolidate. Thanks to [@stern-shawn](https://github.com/stern-shawn)!

**Messaging**
- Remove `messaging_service_sids` and `messaging_service_sid_action` from domain config endpoint **(breaking change)**
- Add error_code and rejection_reason properties to tollfree verification API response

**Numbers**
- Added the new Eligibility API under version `/v1`.


[2023-04-05] Version 9.4.0
--------------------------
**Library - Docs**
- [PR #738](https://github.com/twilio/twilio-java/pull/738): provide more detailed debug logging example. Thanks to [@stern-shawn](https://github.com/stern-shawn)!

**Conversations**
- Expose query parameters `start_date`, `end_date` and `state` in list operation on Conversations resource for sorting and filtering

**Insights**
- Added answered by filter in Call Summaries

**Lookups**
- Remove `disposable_phone_number_risk` package **(breaking change)**

**Messaging**
- Add support for `SOLE_PROPRIETOR` brand type and `SOLE_PROPRIETOR` campaign use case.
- New Sole Proprietor Brands should be created with `SOLE_PROPRIETOR` brand type. Brand registration requests with `STARTER` brand type will be rejected.
- New Sole Proprietor Campaigns should be created with `SOLE_PROPRIETOR` campaign use case. Campaign registration requests with `STARTER` campaign use case will be rejected.
- Add Brand Registrations OTP API


[2023-03-22] Version 9.3.0
--------------------------
**Library - Chore**
- [PR #735](https://github.com/twilio/twilio-java/pull/735): protected NetworkHttpClient field. Thanks to [@bcanseco](https://github.com/bcanseco)!

**Api**
- Revert Corrected the data type for `friendly_name` in Available Phone Number Local, Mobile and TollFree resources
- Corrected the data type for `friendly_name` in Available Phone Number Local, Mobile and TollFree resources **(breaking change)**

**Messaging**
- Add `linkshortening_messaging_service` resource
- Add new endpoint for GetDomainConfigByMessagingServiceSid
- Remove `validated` parameter and add `cert_in_validation` parameter to Link Shortening API **(breaking change)**


[2023-03-09] Version 9.2.5
--------------------------
**Api**
- Add new categories for whatsapp template

**Lookups**
- Remove `validation_results` from the `default_output_properties`

**Supersim**
- Add ESimProfile's `matching_id` and `activation_code` parameters to libraries


[2023-02-22] Version 9.2.4
--------------------------
**Api**
- Remove `scheduled_for` property from message resource
- Add `scheduled_for` property to message resource


[2023-02-08] Version 9.2.3
--------------------------
**Library - Fix**
- [PR #731](https://github.com/twilio/twilio-java/pull/731): add request null check. Thanks to [@charan678](https://github.com/charan678)!
- [PR #730](https://github.com/twilio/twilio-java/pull/730): add null string check. Thanks to [@charan678](https://github.com/charan678)!
- [PR #729](https://github.com/twilio/twilio-java/pull/729): set correct Parameter name. Thanks to [@isha689](https://github.com/isha689)!
- [PR #727](https://github.com/twilio/twilio-java/pull/727): add non null values to param list. Thanks to [@charan678](https://github.com/charan678)!

**Lookups**
- Add `disposable_phone_number_risk` package to the lookup response
- Add `sms_pumping_risk` package to the lookup response


[2023-01-25] Version 9.2.2
--------------------------
**Api**
- Add `public_application_connect_enabled` param to Application resource

**Messaging**
- Add new tollfree verification API property (ExternalReferenceId)]

**Verify**
- Add `device_ip` parameter and channel `auto` for sna/sms orchestration

**Twiml**
- Add support for `<Application>` noun and `<ApplicationSid>` noun, nested `<Parameter>` to `<Hangup>` and `<Leave>` verb


[2023-01-11] Version 9.2.1
--------------------------
**Conversations**
- Add support for creating Multi-Channel Rich Content Messages

**Lookups**
- Changed the no data message for match postal code from `no_data` to `data_not_available` in identity match package

**Messaging**
- Add update/edit tollfree verification API


[2022-12-14] Version 9.2.0
--------------------------
**Library - Fix**
- [PR #725](https://github.com/twilio/twilio-java/pull/725): Fixing Flaky Tests. Thanks to [@priyanka-28](https://github.com/priyanka-28)!
- [PR #722](https://github.com/twilio/twilio-java/pull/722): fromXml for self-closing child twiml. Thanks to [@bcanseco](https://github.com/bcanseco)!

**Api**
- Add `street_secondary` param to address create and update
- Make `method` optional for user defined message subscription **(breaking change)**

**Flex**
- Flex Conversations is now Generally Available
- Adding the ie1 mapping for authorization api, updating service base uri and base url response attribute **(breaking change)**
- Change web channels to GA and library visibility to public
- Changing the uri for authorization api from using Accounts to Insights **(breaking change)**

**Media**
- Gate Twilio Live endpoints behind beta_feature for EOS

**Messaging**
- Mark `MessageFlow` as a required field for Campaign Creation **(breaking change)**

**Oauth**
- updated openid discovery endpoint uri **(breaking change)**
- Added device code authorization endpoint

**Supersim**
- Allow filtering the SettingsUpdates resource by `status`

**Twiml**
- Add new Polly Neural voices
- Add tr-TR, ar-AE, yue-CN, fi-FI languages to SSML `<lang>` element.
- Add x-amazon-jyutping, x-amazon-pinyin, x-amazon-pron-kana, x-amazon-yomigana alphabets to SSML `<phoneme>` element.
- Rename `character` value for SSML `<say-as>` `interpret-as` attribute to `characters`. **(breaking change)**
- Rename `role` attribute to `format` in SSML `<say-as>` element. **(breaking change)**


[2022-11-30] Version 9.1.4
--------------------------
**Flex**
- Adding new `assessments` api in version `v1`

**Lookups**
- Add `identity_match` package to the lookup response

**Messaging**
- Added `validated` parameter to Link Shortening API

**Serverless**
- Add node16 as a valid Build runtime
- Add ie1 and au1 as supported regions for all endpoints.


[2022-11-16] Version 9.1.3
--------------------------
**Library - Chore**
- [PR #721](https://github.com/twilio/twilio-java/pull/721): bump jackson to 2.14.0. Thanks to [@JenniferMah](https://github.com/JenniferMah)!
- [PR #720](https://github.com/twilio/twilio-java/pull/720): bump jackson-databind to latest 2.13.4.2. Thanks to [@jacobdanner](https://github.com/jacobdanner)!
- [PR #719](https://github.com/twilio/twilio-java/pull/719): upgrade GitHub Actions dependencies. Thanks to [@childish-sambino](https://github.com/childish-sambino)!

**Api**
- Set the Content resource to have public visibility as Preview

**Flex**
- Adding new parameter `base_url` to 'gooddata' response in version `v1`

**Insights**
- Added `answered_by` field in List Call Summary
- Added `answered_by` field in call summary


[2022-11-10] Version 9.1.2
--------------------------
**Flex**
- Adding two new authorization API 'user_roles' and 'gooddata' in version `v1`

**Messaging**
- Add new Campaign properties (MessageFlow, OptInMessage, OptInKeywords, OptOutMessage, OptOutKeywords, HelpMessage, HelpKeywords)

**Twiml**
- Add new speech models to `Gather`.


[2022-11-02] Version 9.1.1
--------------------------
**Api**
- Added `contentSid` and `contentVariables` to Message resource with public visibility as Beta
- Add `UserDefinedMessageSubscription` and `UserDefinedMessage` resource

**Proxy**
- Remove FailOnParticipantConflict param from Proxy Session create and update and Proxy Participant create

**Supersim**
- Update SettingsUpdates resource to remove PackageSid

**Taskrouter**
- Add `Ordering` query parameter to Workers and TaskQueues for sorting by
- Add `worker_sid` query param for list reservations endpoint

**Twiml**
- Add `url` and `method` attributes to `<Conversation>`


[2022-10-19] Version 9.1.0
--------------------------
**Library - Fix**
- [PR #717](https://github.com/twilio/twilio-java/pull/717): update reserved words. Thanks to [@childish-sambino](https://github.com/childish-sambino)!

**Library - Chore**
- [PR #715](https://github.com/twilio/twilio-java/pull/715): Fix for 1 vulnerabilities. Thanks to [@twilio-product-security](https://github.com/twilio-product-security)!

**Library - Miscellaneous**
- [PR #716](https://github.com/twilio/twilio-java/pull/716): remove the duplicate header comments. Thanks to [@childish-sambino](https://github.com/childish-sambino)!
- [PR #714](https://github.com/twilio/twilio-java/pull/714): remove dependency on javax jaxb-api. Thanks to [@codylerum](https://github.com/codylerum)!

**Api**
- Make link shortening parameters public **(breaking change)**

**Oauth**
- added oauth JWKS endpoint
- Get userinfo resource
- OpenID discovery resource
- Add new API for token endpoint

**Supersim**
- Add SettingsUpdates resource

**Verify**
- Update Verify Push endpoints to `ga` maturity
- Verify BYOT add Channels property to the Get Templates response

**Twiml**
- Add `requireMatchingInputs` attribute and `input-matching-failed` errorType to `<Prompt>`


[2022-10-05] Version 9.0.1
--------------------------
**Library - Miscellaneous**
- [PR #712](https://github.com/twilio/twilio-java/pull/712): ignore openapi generation relics. Thanks to [@childish-sambino](https://github.com/childish-sambino)!

**Library - Docs**
- [PR #710](https://github.com/twilio/twilio-java/pull/710): updating contribution docs. Thanks to [@kridai](https://github.com/kridai)!

**Api**
- Added `virtual-agent` to `usage_record` API.
- Add AMD attributes to participant create request

**Twiml**
- Add AMD attributes to `Number` and `Sip`


[2022-09-21] Version 9.0.0
--------------------------
**Note:** This release contains breaking changes, check our [upgrade guide](./UPGRADE.md#2022-09-21-8xx-to-9xx) for detailed migration notes.

**Library - Feature**
- [PR #709](https://github.com/twilio/twilio-java/pull/709): merging 9.0.0-rc to main. Thanks to [@kridai](https://github.com/kridai)! **(breaking change)**


[2022-09-07] Version 8.36.0
---------------------------
**Library - Fix**
- [PR #706](https://github.com/twilio/twilio-java/pull/706): fix repeated get content calls issue. Thanks to [@kridai](https://github.com/kridai)!

**Flex**
- Removed redundant `close` status from Flex Interactions flow **(breaking change)**
- Adding `debugger_integration` and `flex_ui_status_report` to Flex Configuration

**Messaging**
- Add create, list and get tollfree verification API

**Verify**
- Verify SafeList API endpoints added.

**Video**
- Add `Anonymize` API

**Twiml**
- Update `event` value `call-in-progress` to `call-answered`


[2022-08-24] Version 8.35.0
---------------------------
**Library - Test**
- [PR #704](https://github.com/twilio/twilio-java/pull/704): add test-docker rule. Thanks to [@beebzz](https://github.com/beebzz)!

**Api**
- Remove `beta feature` from scheduling params and remove optimize parameters. **(breaking change)**

**Routes**
- Remove Duplicate Create Method - Update Method will work even if Inbound Processing Region is currently empty/404. **(breaking change)**

**Twiml**
- Add new Polly Neural voices
- Add new languages to SSML `<lang>`.


[2022-08-10] Version 8.34.1
---------------------------
**Library - Docs**
- [PR #702](https://github.com/twilio/twilio-java/pull/702): updated readme.md for java release candidate. Thanks to [@kridai](https://github.com/kridai)!

**Routes**
- Inbound Proccessing Region API - Public GA

**Supersim**
- Allow updating `DataLimit` on a Fleet


[2022-07-21] Version 8.34.0
---------------------------
**Flex**
- Add `status`, `error_code`, and `error_message` fields to Interaction `Channel`
- Adding `messenger` and `gbm` as supported channels for Interactions API

**Messaging**
- Update alpha_sender docs with new valid characters

**Verify**
- Reorder Verification Check parameters so `code` stays as the first parameter **(breaking change)**
- Rollback List Attempts API V2 back to pilot stage.


[2022-07-13] Version 8.33.0
---------------------------
**Library - Test**
- [PR #699](https://github.com/twilio/twilio-java/pull/699): Adding misc as PR type. Thanks to [@rakatyal](https://github.com/rakatyal)!

**Conversations**
- Allowed to use `identity` as part of Participant's resource **(breaking change)**

**Lookups**
- Remove `enhanced_line_type` from the lookup response **(breaking change)**

**Supersim**
- Add support for `sim_ip_addresses` resource to helper libraries

**Verify**
- Changed summary param `service_sid` to `verify_service_sid` to be consistent with list attempts API **(breaking change)**
- Make `code` optional on Verification check to support `sna` attempts. **(breaking change)**


[2022-06-29] Version 8.32.0
---------------------------
**Api**
- Added `amazon-polly` to `usage_record` API.

**Insights**
- Added `annotation` field in call summary
- Added new endpoint to fetch/create/update Call Annotations

**Verify**
- Remove `api.verify.totp` beta flag and set maturity to `beta` for Verify TOTP properties and parameters. **(breaking change)**
- Changed summary param `verify_service_sid` to `service_sid` to be consistent with list attempts API **(breaking change)**

**Twiml**
- Add `maxQueueSize` to `Enqueue`


[2022-06-15] Version 8.31.1
---------------------------
**Library - Chore**
- [PR #677](https://github.com/twilio/twilio-java/pull/677): bump jackson-databind from 2.12.6 to 2.13.3. Thanks to [@dependabot](https://github.com/dependabot)!

**Lookups**
- Adding support for Lookup V2 API

**Studio**
- Corrected PII labels to be 30 days and added context to be PII

**Twiml**
- Add `statusCallbackMethod` attribute, nested `<Config` and `<Parameter>` elements to `<VirtualAgent>` noun.
- Add support for new Amazon Polly voices (Q2 2022) for `Say` verb
- Add support for `<Conversation>` noun


[2022-06-01] Version 8.31.0
---------------------------
**Library - Chore**
- [PR #693](https://github.com/twilio/twilio-java/pull/693): use Docker 'rc' tag for release candidate images. Thanks to [@childish-sambino](https://github.com/childish-sambino)!

**Library - Fix**
- [PR #692](https://github.com/twilio/twilio-java/pull/692): reset account sid when using username and password only. Thanks to [@aarya-brex](https://github.com/aarya-brex)!
- [PR #688](https://github.com/twilio/twilio-java/pull/688): support setting null account sid. Thanks to [@aarya-brex](https://github.com/aarya-brex)!

**Library - Feature**
- [PR #683](https://github.com/twilio/twilio-java/pull/683): modify user agent string. Thanks to [@sbansla](https://github.com/sbansla)!


[2022-05-18] Version 8.30.1
---------------------------
**Api**
- Add property `media_url` to the recording resources

**Verify**
- Include `silent` as a channel type in the verifications API.


[2022-05-04] Version 8.30.0
---------------------------
**Conversations**
- Expose query parameter `type` in list operation on Address Configurations resource

**Supersim**
- Add `data_total_billed` and `billed_units` fields to Super SIM UsageRecords API response.
- Change ESimProfiles `Eid` parameter to optional to enable Activation Code download method support **(breaking change)**

**Verify**
- Deprecate `push.include_date` parameter in create and update service.


[2022-04-20] Version 8.29.1
---------------------------
**Library - Chore**
- [PR #678](https://github.com/twilio/twilio-java/pull/678): update testcase to remove dependency from generated files. Thanks to [@shrutiburman](https://github.com/shrutiburman)!


[2022-04-06] Version 8.29.0
---------------------------
**Library - Feature**
- [PR #676](https://github.com/twilio/twilio-java/pull/676): support shortcode as an InboundSmsPrice type. Thanks to [@beebzz](https://github.com/beebzz)!

**Api**
- Updated `provider_sid` visibility to private

**Verify**
- Verify List Attempts API summary endpoint added.
- Update PII documentation for `AccessTokens` `factor_friendly_name` property.

**Voice**
- make annotation parameter from /Calls API private


[2022-03-23] Version 8.28.0
---------------------------
**Api**
- Change `stream` url parameter to non optional
- Add `verify-totp` and `verify-whatsapp-conversations-business-initiated` categories to `usage_record` API

**Chat**
- Added v3 Channel update endpoint to support Public to Private channel migration

**Flex**
- Private Beta release of the Interactions API to support the upcoming release of Flex Conversations at the end of Q1 2022.
- Adding `channel_configs` object to Flex Configuration

**Media**
- Add max_duration param to PlayerStreamer

**Supersim**
- Remove Commands resource, use SmsCommands resource instead **(breaking change)**

**Taskrouter**
- Add limits to `split_by_wait_time` for Cumulative Statistics Endpoint

**Video**
- Change recording `status_callback_method` type from `enum` to `http_method` **(breaking change)**
- Add `status_callback` and `status_callback_method` to composition
- Add `status_callback` and `status_callback_method` to recording


[2022-03-09] Version 8.27.1
---------------------------
**Library - Chore**
- [PR #672](https://github.com/twilio/twilio-java/pull/672): push Datadog Release Metric upon deploy success. Thanks to [@eshanholtz](https://github.com/eshanholtz)!

**Api**
- Add optional boolean include_soft_deleted parameter to retrieve soft deleted recordings

**Chat**
- Add `X-Twilio-Wehook-Enabled` header to `delete` method in UserChannel resource

**Numbers**
- Expose `failure_reason` in the Supporting Documents resources

**Verify**
- Add optional `metadata` parameter to "verify challenge" endpoint, so the SDK/App can attach relevant information from the device when responding to challenges.
- remove beta feature flag to list atempt api operations.


[2022-02-23] Version 8.27.0
---------------------------
**Library - Chore**
- [PR #671](https://github.com/twilio/twilio-java/pull/671): archunit 0.23.0. Thanks to [@sullis](https://github.com/sullis)!

**Api**
- Add `uri` to `stream` resource
- Add A2P Registration Fee category (`a2p-registration-fee`) to usage records
- Detected a bug and removed optional boolean include_soft_deleted parameter to retrieve soft deleted recordings. **(breaking change)**
- Add optional boolean include_soft_deleted parameter to retrieve soft deleted recordings.

**Numbers**
- Unrevert valid_until and sort filter params added to List Bundles resource
- Revert valid_until and sort filter params added to List Bundles resource
- Update sorting params added to List Bundles resource in the previous release

**Preview**
- Moved `web_channels` from preview to beta under `flex-api` **(breaking change)**

**Taskrouter**
- Add `ETag` as Response Header to List of Task, Reservation & Worker

**Verify**
- Add `ttl` and `date_created` properties to `AccessTokens`.
- Remove outdated documentation commentary to contact sales. Product is already in public beta.
- Add optional `metadata` to factors.

**Twiml**
- Add new Polly Neural voices


[2022-02-09] Version 8.26.0
---------------------------
**Library - Chore**
- [PR #668](https://github.com/twilio/twilio-java/pull/668): Fix for 1 vulnerabilities. Thanks to [@twilio-product-security](https://github.com/twilio-product-security)!
- [PR #669](https://github.com/twilio/twilio-java/pull/669): added sonarcloud integration. Thanks to [@BrimmingDev](https://github.com/BrimmingDev)!

**Library - Fix**
- [PR #670](https://github.com/twilio/twilio-java/pull/670): set socket config with blocking operation timeout. Thanks to [@childish-sambino](https://github.com/childish-sambino)!

**Api**
- Add `stream` resource

**Conversations**
- Fixed DELETE request to accept "sid_like" params in Address Configuration resources **(breaking change)**
- Expose Address Configuration resource for `sms` and `whatsapp`

**Fax**
- Removed deprecated Programmable Fax Create and Update methods **(breaking change)**

**Insights**
- Rename `call_state` to `call_status` and remove `whisper` in conference participant summary **(breaking change)**

**Numbers**
- Expose valid_until filters as part of provisionally-approved compliance feature on the List Bundles resource

**Supersim**
- Fix typo in Fleet resource docs
- Updated documentation for the Fleet resource indicating that fields related to commands have been deprecated and to use sms_command fields instead.
- Add support for setting and reading `ip_commands_url` and `ip_commands_method` on Fleets resource for helper libraries
- Changed `sim` property in requests to create an SMS Command made to the /SmsCommands to accept SIM UniqueNames in addition to SIDs

**Verify**
- Update list attempts API to include new filters and response fields.


[2022-01-26] Version 8.25.1
---------------------------
**Insights**
- Added new endpoint to fetch Conference Participant Summary
- Added new endpoint to fetch Conference Summary

**Messaging**
- Add government_entity parameter to brand apis

**Verify**
- Add Access Token fetch endpoint to retrieve a previously created token.
- Add Access Token payload to the Access Token creation endpoint, including a unique Sid, so it's addressable while it's TTL is valid.


[2022-01-12] Version 8.25.0
---------------------------
**Library - Feature**
- [PR #666](https://github.com/twilio/twilio-java/pull/666): add GitHub release step during deploy. Thanks to [@childish-sambino](https://github.com/childish-sambino)!

**Library - Chore**
- [PR #665](https://github.com/twilio/twilio-java/pull/665): migrate sonatype hosts. Thanks to [@eshanholtz](https://github.com/eshanholtz)!

**Api**
- Make fixed time scheduling parameters public **(breaking change)**

**Messaging**
- Add update brand registration API

**Numbers**
- Add API endpoint for List Bundle Copies resource

**Video**
- Enable external storage for all customers


[2021-12-15] Version 8.24.0
---------------------------
**Library - Feature**
- [PR #662](https://github.com/twilio/twilio-java/pull/662): run tests before deploying. Thanks to [@childish-sambino](https://github.com/childish-sambino)!

**Api**
- Add optional boolean send_as_mms parameter to the create action of Message resource **(breaking change)**
- Change team ownership for `call` delete

**Conversations**
- Change wording for `Service Webhook Configuration` resource fields

**Insights**
- Added new APIs for updating and getting voice insights flags by accountSid.

**Media**
- Add max_duration param to MediaProcessor

**Video**
- Add `EmptyRoomTimeout` and `UnusedRoomTimeout` properties to a room; add corresponding parameters to room creation

**Voice**
- Add endpoint to delete archived Calls


[2021-12-01] Version 8.23.0
---------------------------
**Library - Feature**
- [PR #661](https://github.com/twilio/twilio-java/pull/661): add fromXml method on builders (twiml). Thanks to [@bcanseco](https://github.com/bcanseco)!

**Library - Chore**
- [PR #660](https://github.com/twilio/twilio-java/pull/660): setup for XML deserialization. Thanks to [@bcanseco](https://github.com/bcanseco)!

**Conversations**
- Add `Service Webhook Configuration` resource

**Flex**
- Adding `flex_insights_drilldown` and `flex_url` objects to Flex Configuration

**Messaging**
- Update us_app_to_person endpoints to remove beta feature flag based access

**Supersim**
- Add IP Commands resource

**Verify**
- Add optional `factor_friendly_name` parameter to the create access token endpoint.

**Video**
- Add maxParticipantDuration param to Rooms

**Twiml**
- Unrevert Add supported SSML children to `<emphasis>`, `<lang>`, `<p>`, `<prosody>`, `<s>`, and `<w>`.
- Revert Add supported SSML children to `<emphasis>`, `<lang>`, `<p>`, `<prosody>`, `<s>`, and `<w>`.


[2021-11-17] Version 8.22.1
---------------------------
**Library - Chore**
- [PR #658](https://github.com/twilio/twilio-java/pull/658): lombok 1.18.16. Thanks to [@sullis](https://github.com/sullis)!
- [PR #643](https://github.com/twilio/twilio-java/pull/643): maven-enforcer-plugin 3.0.0. Thanks to [@sullis](https://github.com/sullis)!
- [PR #654](https://github.com/twilio/twilio-java/pull/654): archunit 0.22.0. Thanks to [@sullis](https://github.com/sullis)!

**Library - Fix**
- [PR #657](https://github.com/twilio/twilio-java/pull/657): git log retrieval issues. Thanks to [@shwetha-manvinkurke](https://github.com/shwetha-manvinkurke)!

**Frontline**
- Added `is_available` to User's resource

**Messaging**
- Added GET vetting API

**Verify**
- Add `WHATSAPP` to the attempts API.
- Allow to update `config.notification_platform` from `none` to `apn` or `fcm` and viceversa for Verify Push
- Add `none` as a valid `config.notification_platform` value for Verify Push

**Twiml**
- Add supported SSML children to `<emphasis>`, `<lang>`, `<p>`, `<prosody>`, `<s>`, and `<w>`.


[2021-11-03] Version 8.22.0
---------------------------
**Library - Chore**
- [PR #655](https://github.com/twilio/twilio-java/pull/655): migrate from travis ci to gh actions. Thanks to [@eshanholtz](https://github.com/eshanholtz)!

**Api**
- Updated `media_url` property to be treated as PII

**Messaging**
- Added a new enum for brand registration status named DELETED **(breaking change)**
- Add a new K12_EDUCATION use case in us_app_to_person_usecase api transaction
- Added a new enum for brand registration status named IN_REVIEW

**Serverless**
- Add node14 as a valid Build runtime

**Verify**
- Fix typos in Verify Push Factor documentation for the `config.notification_token` parameter.
- Added `TemplateCustomSubstitutions` on verification creation
- Make `TemplateSid` parameter public for Verification resource and `DefaultTemplateSid` parameter public for Service resource. **(breaking change)**


[2021-10-18] Version 8.21.0
---------------------------
**Library - Chore**
- [PR #653](https://github.com/twilio/twilio-java/pull/653): bump jackson dependency version. Thanks to [@shwetha-manvinkurke](https://github.com/shwetha-manvinkurke)!
- [PR #651](https://github.com/twilio/twilio-java/pull/651): upgrade maven-javadoc-plugin. Thanks to [@sullis](https://github.com/sullis)!

**Library - Feature**
- [PR #652](https://github.com/twilio/twilio-java/pull/652): Add PlaybackGrant. Thanks to [@miguelgrinberg](https://github.com/miguelgrinberg)!

**Api**
- Corrected enum values for `emergency_address_status` values in `/IncomingPhoneNumbers` response. **(breaking change)**
- Clarify `emergency_address_status` values in `/IncomingPhoneNumbers` response.

**Messaging**
- Add PUT and List brand vettings api
- Removes beta feature flag based visibility for us_app_to_person_registered and usecase field.Updates test cases to add POLITICAL usecase. **(breaking change)**
- Add brand_feedback as optional field to BrandRegistrations

**Video**
- Add `AudioOnly` to create room


[2021-10-06] Version 8.20.0
---------------------------
**Library - Fix**
- [PR #649](https://github.com/twilio/twilio-java/pull/649): System Information Leak. Thanks to [@JenniferMah](https://github.com/JenniferMah)!

**Api**
- Add `emergency_address_status` attribute to `/IncomingPhoneNumbers` response.
- Add `siprec` resource

**Conversations**
- Added attachment parameters in configuration for `NewMessage` type of push notifications

**Flex**
- Adding `flex_insights_hr` object to Flex Configuration

**Numbers**
- Add API endpoint for Bundle ReplaceItems resource
- Add API endpoint for Bundle Copies resource

**Serverless**
- Add domain_base field to Service response

**Taskrouter**
- Add `If-Match` Header based on ETag for Worker Delete **(breaking change)**
- Add `If-Match` Header based on Etag for Reservation Update
- Add `If-Match` Header based on ETag for Worker Update
- Add `If-Match` Header based on ETag for Worker Delete
- Add `ETag` as Response Header to Worker

**Trunking**
- Added `transfer_caller_id` property on Trunks.

**Verify**
- Document new pilot `whatsapp` channel.


[2021-09-22] Version 8.19.2
---------------------------
**Events**
- Add segment sink

**Messaging**
- Add post_approval_required attribute in GET us_app_to_person_usecase api response
- Add Identity Status, Russell 3000, Tax Exempt Status and Should Skip SecVet fields for Brand Registrations
- Add Should Skip Secondary Vetting optional flag parameter to create Brand API


[2021-09-08] Version 8.19.1
---------------------------
**Library - Fix**
- [PR #644](https://github.com/twilio/twilio-java/pull/644): deploy issues. Thanks to [@shwetha-manvinkurke](https://github.com/shwetha-manvinkurke)!

**Api**
- Revert adding `siprec` resource
- Add `siprec` resource

**Messaging**
- Add 'mock' as an optional field to brand_registration api
- Add 'mock' as an optional field to us_app_to_person api
- Adds more Use Cases in us_app_to_person_usecase api transaction and updates us_app_to_person_usecase docs

**Verify**
- Verify List Templates API endpoint added.


[2021-08-25] Version 8.19.0
---------------------------
**Library - Chore**
- [PR #641](https://github.com/twilio/twilio-java/pull/641): integrate with sonarcloud. Thanks to [@shwetha-manvinkurke](https://github.com/shwetha-manvinkurke)!

**Api**
- Add Programmabled Voice SIP Refer call transfers (`calls-transfers`) to usage records
- Add Flex Voice Usage category (`flex-usage`) to usage records
- Corrected the `price`, `call_sid_to_coach`, and `uri` data types for Conference, Participant, and Recording **(breaking change)**
- Made documentation for property `time_limit` in the call api public. **(breaking change)**
- Added `domain_sid` in sip_credential_list_mapping and sip_ip_access_control_list_mapping APIs **(breaking change)**

**Conversations**
- Add `Order` query parameter to Message resource read operation

**Insights**
- Added `partial` to enum processing_state_request
- Added abnormal session filter in Call Summaries
- Added new endpoint to fetch Call Summaries

**Messaging**
- Add brand_registration_sid as an optional query param for us_app_to_person_usecase api
- Add brand_type field to a2p brand_registration api
- Revert brand registration api update to add brand_type field
- Add brand_type field to a2p brand_registration api

**Pricing**
- add trunking_numbers resource (v2)
- add trunking_country resource (v2)

**Taskrouter**
- Add `X-Rate-Limit-Limit`, `X-Rate-Limit-Remaining`, and `X-Rate-Limit-Config` as Response Headers to all TaskRouter endpoints

**Verify**
- Changed to private beta the `TemplateSid` optional parameter on Verification creation.
- Added the optional parameter `Order` to the list Challenges endpoint to define the list order.
- Add `TemplateSid` optional parameter on Verification creation.
- Include `whatsapp` as a channel type in the verifications API.


[2021-07-28] Version 8.18.0
---------------------------
**Library - Feature**
- [PR #640](https://github.com/twilio/twilio-java/pull/640): add new constructor to ValidationClient. Thanks to [@sullis](https://github.com/sullis)!

**Conversations**
- Expose ParticipantConversations resource

**Taskrouter**
- Adding `links` to the activity resource

**Verify**
- Added a `Version` to Verify Factors `Webhooks` to add new fields without breaking old Webhooks.


[2021-07-14] Version 8.17.0
---------------------------
**Conversations**
- Changed `last_read_message_index` and `unread_messages_count` type in User Conversation's resource **(breaking change)**
- Expose UserConversations resource

**Messaging**
- Add brand_score field to brand registration responses


[2021-06-30] Version 8.16.0
---------------------------
**Conversations**
- Read-only Conversation Email Binding property `binding`

**Supersim**
- Add Billing Period resource for the Super Sim Pilot
- Add List endpoint to Billing Period resource for Super Sim Pilot
- Add Fetch endpoint to Billing Period resource for Super Sim Pilot

**Taskrouter**
- Update `transcribe` & `transcription_configuration` form params in Reservation update endpoint to have private visibility **(breaking change)**
- Add `transcribe` & `transcription_configuration` form params to Reservation update endpoint

**Twiml**
- Add `modify` event to `statusCallbackEvent` for `<Conference>`.


[2021-06-16] Version 8.15.0
---------------------------
**Library - Chore**
- [PR #637](https://github.com/twilio/twilio-java/pull/637): archunit 0.19.0. Thanks to [@sullis](https://github.com/sullis)!

**Api**
- Update `status` enum for Messages to include 'canceled'
- Update `update_status` enum for Messages to include 'canceled'

**Trusthub**
- Corrected the sid for policy sid in customer_profile_evaluation.json and trust_product_evaluation.json **(breaking change)**


[2021-06-02] Version 8.14.0
---------------------------
**Library - Feature**
- [PR #636](https://github.com/twilio/twilio-java/pull/636): Update WorkflowRuleTarget to support Known Agent Routing. Thanks to [@akallimani](https://github.com/akallimani)!

**Library - Chore**
- [PR #634](https://github.com/twilio/twilio-java/pull/634): equalsverifier 3.6.1. Thanks to [@sullis](https://github.com/sullis)!

**Events**
- join Sinks and Subscriptions service

**Verify**
- Improved the documentation of `challenge` adding the maximum and minimum expected lengths of some fields.
- Improve documentation regarding `notification` by updating the documentation of the field `ttl`.


[2021-05-19] Version 8.13.0
---------------------------
**Events**
- add query param to return types filtered by Schema Id
- Add query param to return sinks filtered by status
- Add query param to return sinks used/not used by a subscription

**Messaging**
- Add fetch and delete instance endpoints to us_app_to_person api **(breaking change)**
- Remove delete list endpoint from us_app_to_person api **(breaking change)**
- Update read list endpoint to return a list of us_app_to_person compliance objects **(breaking change)**
- Add `sid` field to Preregistered US App To Person response

**Supersim**
- Mark `unique_name` in Sim, Fleet, NAP resources as not PII

**Video**
- [Composer] GA maturity level


[2021-05-05] Version 8.12.0
---------------------------
**Library - Feature**
- [PR #559](https://github.com/twilio/twilio-java/pull/559): allow conference participant to be any endpoint. Thanks to [@JaymoKang](https://github.com/JaymoKang)!

**Library - Fix**
- [PR #632](https://github.com/twilio/twilio-java/pull/632): log correct URL in TwilioRestClient. Thanks to [@sullis](https://github.com/sullis)!

**Api**
- Corrected the data types for feedback summary fields **(breaking change)**
- Update the conference participant create `from` and `to` param to be endpoint type for supporting client identifier and sip address

**Bulkexports**
- promoting API maturity to GA

**Events**
- Add endpoint to update description in sink
- Remove beta-feature account flag

**Messaging**
- Update `status` field in us_app_to_person api to `campaign_status` **(breaking change)**

**Verify**
- Improve documentation regarding `push` factor and include extra information about `totp` factor.


[2021-04-21] Version 8.11.0
---------------------------
**Library - Chore**
- [PR #631](https://github.com/twilio/twilio-java/pull/631): protected TwilioRestClient constructor. Thanks to [@bcanseco](https://github.com/bcanseco)!

**Api**
- Revert Update the conference participant create `from` and `to` param to be endpoint type for supporting client identifier and sip address
- Update the conference participant create `from` and `to` param to be endpoint type for supporting client identifier and sip address

**Bulkexports**
- moving enum to doc root for auto generating documentation
- adding status enum and default output properties

**Events**
- Change schema_versions prop and key to versions **(breaking change)**

**Messaging**
- Add `use_inbound_webhook_on_number` field in Service API for fetch, create, update, read

**Taskrouter**
- Add `If-Match` Header based on ETag for Task Delete

**Verify**
- Add `AuthPayload` parameter to support verifying a `Challenge` upon creation. This is only supported for `totp` factors.
- Add support to resend the notifications of a `Challenge`. This is only supported for `push` factors.

**Twiml**
- Add Polly Neural voices.


[2021-04-07] Version 8.10.0
---------------------------
**Library - Fix**
- [PR #629](https://github.com/twilio/twilio-java/pull/629): handle case where XML transformer attributes are not supported. Thanks to [@codylerum](https://github.com/codylerum)!

**Api**
- Added `announcement` event to conference status callback events
- Removed optional property `time_limit` in the call create request. **(breaking change)**
- Added optional parameter `CallToken` for create calls api
- Add optional property `time_limit` in the call create request.

**Bulkexports**
- adding two new fields with job api queue_position and estimated_completion_time

**Events**
- Add new endpoints to manage subscribed_events in subscriptions

**Messaging**
- Add rate_limits field to Messaging Services US App To Person API
- Add usecase field in Service API for fetch, create, update, read
- Add us app to person api and us app to person usecase api as dependents in service
- Add us_app_to_person_registered field in service api for fetch, read, create, update
- Add us app to person api
- Add us app to person usecase api
- Add A2P external campaign api
- Add Usecases API

**Numbers**
- Remove feature flags for RegulatoryCompliance endpoints

**Supersim**
- Add Create endpoint to Sims resource
- Add SmsCommands resource
- Add fields `SmsCommandsUrl`, `SmsCommandsMethod` and `SmsCommandsEnabled` to a Fleet resource

**Taskrouter**
- Add `If-Match` Header based on ETag for Task Update
- Add `ETag` as Response Headers to Tasks and Reservations

**Verify**
- The `Binding` field is now returned when creating a `Factor`. This value won't be returned for other endpoints.

**Video**
- [Rooms] max_concurrent_published_tracks has got GA maturity
- Recording rule beta flag **(breaking change)**
- [Rooms] Add RecordingRules param to Rooms

**Twiml**
- Add `announcement` event to `statusCallbackEvent` for `<Conference>`.


[2021-03-15] Version 8.9.0
--------------------------
**Library - Fix**
- [PR #625](https://github.com/twilio/twilio-java/pull/625): date time conversion to String. Thanks to [@eshanholtz](https://github.com/eshanholtz)!

**Library - Chore**
- [PR #624](https://github.com/twilio/twilio-java/pull/624): Enable maven depedency caching for travis. Thanks to [@Taher-Ghaleb](https://github.com/Taher-Ghaleb)!
- [PR #623](https://github.com/twilio/twilio-java/pull/623): protect against XML external entity injection. Thanks to [@eshanholtz](https://github.com/eshanholtz)!

**Events**
- Set maturity to beta

**Messaging**
- Adjust A2P brand registration status enum **(breaking change)**

**Studio**
- Remove internal safeguards for Studio V2 API usage now that it's GA

**Verify**
- Add support for creating and verifying totp factors. Support for totp factors is behind the `api.verify.totp` beta feature.

**Twiml**
- Add support for `<VirtualAgent>` noun


[2021-02-24] Version 8.8.0
--------------------------
**Library - Chore**
- [PR #622](https://github.com/twilio/twilio-java/pull/622): bump dependencies. Thanks to [@sullis](https://github.com/sullis)!
- [PR #621](https://github.com/twilio/twilio-java/pull/621): update jackson dependency. Thanks to [@thinkingserious](https://github.com/thinkingserious)!

**Events**
- Update description of types in the create sink resource

**Messaging**
- Add WA template header and footer
- Remove A2P campaign and use cases API **(breaking change)**
- Add number_registration_status field to read and fetch campaign responses

**Trusthub**
- Make all resources public

**Verify**
- Verify List Attempts API endpoints added.


[2021-02-11] Version 8.7.0
--------------------------
**Library - Chore**
- [PR #617](https://github.com/twilio/twilio-java/pull/617): archunit 0.16.0. Thanks to [@sullis](https://github.com/sullis)!
- [PR #618](https://github.com/twilio/twilio-java/pull/618): disallow Log4j. Thanks to [@sullis](https://github.com/sullis)!
- [PR #616](https://github.com/twilio/twilio-java/pull/616): equalsverifier 3.5.2. Thanks to [@sullis](https://github.com/sullis)!

**Library - Fix**
- [PR #620](https://github.com/twilio/twilio-java/pull/620): temporarily remove log test. Thanks to [@thinkingserious](https://github.com/thinkingserious)!
- [PR #619](https://github.com/twilio/twilio-java/pull/619): no longer using log4j test config. Thanks to [@thinkingserious](https://github.com/thinkingserious)!
- [PR #610](https://github.com/twilio/twilio-java/pull/610): switch from log4j to SLF4J. Thanks to [@sullis](https://github.com/sullis)!

**Api**
- Revert change that conference participant create `from` and `to` param to be endpoint type for supporting client identifier and sip address
- Update the conference participant create `from` and `to` param to be endpoint type for supporting client identifier and sip address

**Events**
- Documentation should state that no fields are PII

**Flex**
- Adding `notifications` and `markdown` to Flex Configuration

**Messaging**
- Add A2P use cases API
- Add Brand Registrations API
- Add Campaigns API

**Serverless**
- Add runtime field to Build response and as an optional parameter to the Build create endpoint.
- Add @twilio/runtime-handler dependency to Build response example.

**Sync**
- Remove If-Match header for Document **(breaking change)**

**Twiml**
- Add `refer_url` and `refer_method` to `Dial`.


[2021-01-27] Version 8.6.1
--------------------------
**Studio**
- Studio V2 API is now GA

**Supersim**
- Allow updating `CommandsUrl` and `CommandsMethod` on a Fleet

**Twiml**
- Add `status_callback` and `status_callback_method` to `Stream`.


[2021-01-13] Version 8.6.0
--------------------------
**Api**
- Add 'Electric Imp v1 Usage' to usage categories

**Conversations**
- Changed `last_read_message_index` type in Participant's resource **(breaking change)**

**Insights**
- Added `created_time` to call summary.

**Sync**
- Remove HideExpired query parameter for filtering Sync Documents with expired **(breaking change)**

**Video**
- [Rooms] Expose maxConcurrentPublishedTracks property in Room resource


[2020-12-16] Version 8.5.1
--------------------------
**Api**
- Updated `call_event` default_output_properties to request and response.

**Conversations**
- Added `last_read_message_index` and `last_read_timestamp` to Participant's resource update operation
- Added `is_notifiable` and `is_online` to User's resource
- Added `reachability_enabled` parameters to update method for Conversation Service Configuration resource

**Messaging**
- Added WA template quick reply, URL, and phone number buttons

**Twiml**
- Add `sequential` to `Dial`.


[2020-12-08] Version 8.5.0
--------------------------
**Library - Chore**
- [PR #609](https://github.com/twilio/twilio-java/pull/609): [Snyk] Security upgrade org.apache.httpcomponents:httpclient from 4.5.12 to 4.5.13. Thanks to [@snyk-bot](https://github.com/snyk-bot)!

**Api**
- Added optional `RecordingTrack` parameter for create calls, create participants, and create call recordings
- Removed deprecated Programmable Chat usage record categories **(breaking change)**

**Twiml**
- Add `recordingTrack` to `Dial`.


[2020-12-02] Version 8.4.0
--------------------------
**Library - Feature**
- [PR #606](https://github.com/twilio/twilio-java/pull/606): add http logging for Java. Thanks to [@JenniferMah](https://github.com/JenniferMah)!
- [PR #602](https://github.com/twilio/twilio-java/pull/602): Regional support for access token. Thanks to [@charliesantos](https://github.com/charliesantos)!

**Api**
- Remove `RecordingTrack` parameter for create calls, create participants, and create call recordings **(breaking change)**
- Added `RecordingTrack` parameter for create calls and create call recordings
- Add optional property `recording_track` in the participant create request

**Lookups**
- Changed `caller_name` and `carrier` properties type to object **(breaking change)**

**Trunking**
- Added dual channel recording options for Trunks.

**Twiml**
- Add `jitterBufferSize` and `participantLabel` to `Conference`.


[2020-11-18] Version 8.3.0
--------------------------
**Api**
- Add new call events resource - GET /2010-04-01/Accounts/{account_sid}/Calls/{call_sid}/Events.json

**Conversations**
- Fixed default response property issue for Service Notifications Configuration

**Insights**
- Removing call_sid from participant summary. **(breaking change)**

**Serverless**
- Allow Service unique name to be used in path (in place of SID) in Service update request

**Sync**
- Added HideExpired query parameter for filtering Sync Documents with expired

**Verify**
- Challenge `Details` and `HiddenDetails` properties are now marked as `PII`
- Challenge `expiration_date` attribute updated to set a default value of five (5) minutes and to allow max dates of one (1) hour after creation.
- Entity `identity` attribute updated to allow values between 8 and 64 characters.
- Verify Service frinedly_name attribute updated from 64 max lenght to 30 characters.


[2020-11-05] Version 8.2.0
--------------------------
**Api**
- Added `verify-push` to `usage_record` API

**Bulkexports**
- When creating a custom export the StartDay, EndDay, and FriendlyName fields were required but this was not reflected in the API documentation.  The API itself failed the request without these fields. **(breaking change)**
- Added property descriptions for Custom Export create method
- Clarified WebhookUrl and WebhookMethod must be provided together for Custom Export

**Insights**
- Added video room and participant summary apis.

**Ip_messaging**
- Create separate definition for ip-messaging
- Restore v2 endpoints for ip-messaging

**Verify**
- Verify Push madurity were updated from `preview` to `beta`
- `twilio_sandbox_mode` header was removed from Verify Push resources **(breaking change)**

**Video**
- [Rooms] Add Recording Rules API


[2020-10-14] Version 8.1.0
--------------------------
**Library - Feature**
- [PR #573](https://github.com/twilio/twilio-java/pull/573): Add RecordingRule and RecordingRulesUpdate. Thanks to [@FMV1491](https://github.com/FMV1491)!

**Library - Chore**
- [PR #598](https://github.com/twilio/twilio-java/pull/598): bump junit from 4.13 to 4.13.1. Thanks to [@dependabot](https://github.com/dependabot)!
- [PR #591](https://github.com/twilio/twilio-java/pull/591): upgrade Jackson to 2.11.3. Thanks to [@sullis](https://github.com/sullis)!

**Library - Fix**
- [PR #596](https://github.com/twilio/twilio-java/pull/596): drop reflective access warnings to java.time.* classes. Thanks to [@mjg123](https://github.com/mjg123)!
- [PR #595](https://github.com/twilio/twilio-java/pull/595): serialize ZonedDateTime parameters to ISO-8601. Thanks to [@childish-sambino](https://github.com/childish-sambino)!

**Library - Test**
- [PR #594](https://github.com/twilio/twilio-java/pull/594): change RequestTest to use flexapi instead of ip-messaging. Thanks to [@mgmuscari](https://github.com/mgmuscari)!
- [PR #590](https://github.com/twilio/twilio-java/pull/590): use ArchUnit to detect JodaTime usage. Thanks to [@sullis](https://github.com/sullis)!

**Ai**
- Add `Annotation Project` and `Annotation Task` endpoints
- Add `Primitives` endpoints
- Add `meta.total` to the search endpoint

**Conversations**
- Mutable Conversation Unique Names

**Insights**
- Added `trust` to summary.

**Preview**
- Simplified `Channels` resource. The path is now `/BrandedChannels/branded_channel_sid/Channels` **(breaking change)**

**Verify**
- Changed parameters (`config` and `binding`) to use dot notation instead of JSON string (e.i. Before: `binding={"alg":"ES256", "public_key": "xxx..."}`, Now: `Binding.Alg="ES256"`, `Binding.PublicKey="xxx..."`). **(breaking change)**
- Changed parameters (`details` and `hidden_details`) to use dot notation instead of JSON string (e.i. Before: `details={"message":"Test message", "fields": "[{\"label\": \"Action 1\", \"value\":\"value 1\"}]"}`, Now: `details.Message="Test message"`, `Details.Fields=["{\"label\": \"Action 1\", \"value\":\"value 1\"}"]`). **(breaking change)**
- Removed `notify_service_sid` from `push` service configuration object. Add `Push.IncludeDate`, `Push.ApnCredentialSid` and `Push.FcmCredentialSid` service configuration parameters. **(breaking change)**


[2020-09-28] Version 8.0.0
--------------------------
**Note:** This release contains breaking changes, check our [upgrade guide](./UPGRADE.md#2020-09-28-7xx-to-8xx) for detailed migration notes.

**Library - Chore**
- [PR #584](https://github.com/twilio/twilio-java/pull/584): remove Guava Range(). Thanks to [@thinkingserious](https://github.com/thinkingserious)! **(breaking change)**
- [PR #585](https://github.com/twilio/twilio-java/pull/585): finish dropping Guava 'MoreObjects' usage. Thanks to [@childish-sambino](https://github.com/childish-sambino)!
- [PR #572](https://github.com/twilio/twilio-java/pull/572): remove joda-time dependency. Thanks to [@thinkingserious](https://github.com/thinkingserious)! **(breaking change)**
- [PR #574](https://github.com/twilio/twilio-java/pull/574): remove guava functional interfaces. Thanks to [@thinkingserious](https://github.com/thinkingserious)!
- [PR #576](https://github.com/twilio/twilio-java/pull/576): replace Guava toString(). Thanks to [@thinkingserious](https://github.com/thinkingserious)!
- [PR #575](https://github.com/twilio/twilio-java/pull/575): replace guava concurrency, hashing, and charstreams. Thanks to [@childish-sambino](https://github.com/childish-sambino)! **(breaking change)**
- [PR #578](https://github.com/twilio/twilio-java/pull/578): remove deprecated twiml methods. Thanks to [@eshanholtz](https://github.com/eshanholtz)! **(breaking change)**

**Library - Feature**
- [PR #588](https://github.com/twilio/twilio-java/pull/588): make static 'Twilio' access synchronized. Thanks to [@childish-sambino](https://github.com/childish-sambino)!
- [PR #586](https://github.com/twilio/twilio-java/pull/586): add support for environment variables. Thanks to [@childish-sambino](https://github.com/childish-sambino)!

**Library - Fix**
- [PR #587](https://github.com/twilio/twilio-java/pull/587): replace ICE Server URL properties with String types. Thanks to [@childish-sambino](https://github.com/childish-sambino)! **(breaking change)**

**Library - Docs**
- [PR #579](https://github.com/twilio/twilio-java/pull/579): Remove Java7 from supported languages and add upgrade guide. Thanks to [@eshanholtz](https://github.com/eshanholtz)!

**Api**
- Add optional property `call_reason` in the participant create request
- Make sip-domain-service endpoints available in stage-au1 and prod-au1

**Messaging**
- Removed beta feature gate from WhatsApp Templates API

**Serverless**
- Add Build Status endpoint

**Video**
- [Rooms] Add new room type "go" for WebRTC Go


[2020-09-21] Version 7.55.3
---------------------------
**Library - Fix**
- [PR #583](https://github.com/twilio/twilio-java/pull/583): allow API redirect responses. Thanks to [@childish-sambino](https://github.com/childish-sambino)!
- [PR #571](https://github.com/twilio/twilio-java/pull/571): paging breaks with + sign on phone number. Thanks to [@thinkingserious](https://github.com/thinkingserious)!

**Library - Chore**
- [PR #582](https://github.com/twilio/twilio-java/pull/582): upgrade jackson and HTTP dependencies. Thanks to [@childish-sambino](https://github.com/childish-sambino)!

**Library - Docs**
- [PR #581](https://github.com/twilio/twilio-java/pull/581): convert markdown links to href formatted links. Thanks to [@JenniferMah](https://github.com/JenniferMah)!

**Accounts**
- Add Auth Token rotation API

**Conversations**
- Change resource path for Webhook Configuration

**Events**
- Schemas API get all Schemas names and versions


[2020-09-16] Version 7.55.2
---------------------------
**Conversations**
- Expose Configuration and Service Configuration resources
- Add Unique Name support for Conversations
- Add Services Push Notification resource
- Add Service scoped Conversation resources
- Support Identity in Users resource endpoint

**Messaging**
- GA Deactivation List API
- Add domain cert API's(fetch, update, create) for link tracker

**Numbers**
- Add API endpoint for Supporting Document deletion

**Proxy**
- Updated usage of FailOnParticipantConflict param to apply only to accounts with ProxyAllowParticipantConflict account flag

**Supersim**
- Add `AccountSid` parameter to Sim resource update request
- Add `ready` status as an available status for a Sim resource


[2020-09-02] Version 7.55.1
---------------------------
**Ai**
- Initial release

**Bulkexports**
- removing public beta feature flag from BulkExports Jobs API

**Messaging**
- Add Deactivation List API
- Added page token parameter for fetch in WhatsApp Templates API

**Numbers**
- Add API endpoint for End User deletion

**Routes**
- Add Resource Route Configurations API
- Add Route Configurations API
- Initial Release

**Trunking**
- Added `transfer_mode` property on Trunks.


[2020-08-19] Version 7.55.0
---------------------------
**Library - Feature**
- [PR #568](https://github.com/twilio/twilio-java/pull/568): add support for custom HTTP headers. Thanks to [@eshanholtz](https://github.com/eshanholtz)!

**Library - Chore**
- [PR #567](https://github.com/twilio/twilio-java/pull/567): drop some of the Guava usage which is easily replaced. Thanks to [@childish-sambino](https://github.com/childish-sambino)!
- [PR #565](https://github.com/twilio/twilio-java/pull/565): update GitHub branch references to use HEAD. Thanks to [@thinkingserious](https://github.com/thinkingserious)!

**Conversations**
- Allow Identity addition to Participants

**Events**
- Sinks API Get all Sinks

**Proxy**
- Clarified usage of FailOnParticipantConflict param as experimental
- Add FailOnParticipantConflict param to Proxy Session create and Proxy Participant create

**Supersim**
- Add fleet, network, and isoCountryCode to the UsageRecords resource
- Change sort order of UsageRecords from ascending to descending with respect to start time field, records are now returned newest to oldest

**Wireless**
- Removed `Start` and `End` parameters from the Data Sessions list endpoint. **(breaking change)**


[2020-08-05] Version 7.54.2
---------------------------
**Messaging**
- Add rejection reason support to WhatsApp API
- Removed status parameter for create and update in WhatsApp Templates API

**Proxy**
- Add FailOnParticipantConflict param to Proxy Session update

**Verify**
- Add `CustomFriendlyName` optional parameter on Verification creation.
- Changes in `Challenge` resource to update documentation of both `details` and `hidden_details` properties.


[2020-07-22] Version 7.54.1
---------------------------
**Library - Fix**
- [PR #558](https://github.com/twilio/twilio-java/pull/558): encode path parameters. Thanks to [@eshanholtz](https://github.com/eshanholtz)!

**Api**
- Add optional Click Tracking and Scheduling parameters to Create action of Message resource

**Supersim**
- Add callback_url and callback_method parameters to Sim resource update request


[2020-07-08] Version 7.54.0
---------------------------
**Library - Feature**
- [PR #555](https://github.com/twilio/twilio-java/pull/555): add fax capability to deserialized phone number capabilities. Thanks to [@childish-sambino](https://github.com/childish-sambino)!
- [PR #553](https://github.com/twilio/twilio-java/pull/553): include API response headers in 'Last Response'. Thanks to [@childish-sambino](https://github.com/childish-sambino)!

**Library - Chore**
- [PR #554](https://github.com/twilio/twilio-java/pull/554): bump archunit from 0.13.0 to 0.14.1. Thanks to [@dependabot](https://github.com/dependabot)!
- [PR #550](https://github.com/twilio/twilio-java/pull/550): bump cobertura-maven-plugin from 2.2 to 2.7. Thanks to [@dependabot](https://github.com/dependabot)!
- [PR #551](https://github.com/twilio/twilio-java/pull/551): bump maven-assembly-plugin from 2.3 to 3.3.0. Thanks to [@dependabot](https://github.com/dependabot)!
- [PR #543](https://github.com/twilio/twilio-java/pull/543): bump joda-time from 2.5 to 2.10.6. Thanks to [@dependabot](https://github.com/dependabot)!
- [PR #545](https://github.com/twilio/twilio-java/pull/545): bump maven-surefire-plugin from 2.20 to 2.22.2. Thanks to [@dependabot](https://github.com/dependabot)!
- [PR #546](https://github.com/twilio/twilio-java/pull/546): bump spotbugs-maven-plugin from 3.1.12 to 4.0.4. Thanks to [@dependabot](https://github.com/dependabot)!
- [PR #547](https://github.com/twilio/twilio-java/pull/547): bump maven-checkstyle-plugin from 2.17 to 3.1.1. Thanks to [@dependabot](https://github.com/dependabot)!

**Conversations**
- Allow Address updates for Participants
- Message delivery receipts

**Events**
- Add account_sid to subscription and subscribed_events resources

**Flex**
- Changed `wfm_integrations` Flex Configuration key to private **(breaking change)**

**Messaging**
- Add error states to WhatsApp Sender status with failed reason **(breaking change)**
- Delete WhatsApp Template API
- Update WhatsApp Template API
- Add WhatsApp Template Get Api (fetch and read)

**Numbers**
- Add `valid_until` in the Bundles resource
- Add API for Bundle deletion

**Verify**
- Removed support for `sms`, `totp` and `app-push` factor types in Verify push **(breaking change)**


[2020-06-24] Version 7.53.0
---------------------------
**Library - Chore**
- [PR #535](https://github.com/twilio/twilio-java/pull/535): bump maven-javadoc-plugin from 2.10.4 to 3.2.0. Thanks to [@dependabot](https://github.com/dependabot)!
- [PR #542](https://github.com/twilio/twilio-java/pull/542): bump guava from 28.0-android to 29.0-android. Thanks to [@dependabot](https://github.com/dependabot)!
- [PR #540](https://github.com/twilio/twilio-java/pull/540): bump equalsverifier from 3.1.12 to 3.4.1. Thanks to [@dependabot](https://github.com/dependabot)!
- [PR #541](https://github.com/twilio/twilio-java/pull/541): bump junit from 4.11 to 4.13. Thanks to [@dependabot](https://github.com/dependabot)!
- [PR #539](https://github.com/twilio/twilio-java/pull/539): bump jjwt.version from 0.10.7 to 0.11.2. Thanks to [@dependabot](https://github.com/dependabot)!
- [PR #536](https://github.com/twilio/twilio-java/pull/536): bump jaxb-api from 2.2 to 2.3.1. Thanks to [@dependabot](https://github.com/dependabot)!
- [PR #538](https://github.com/twilio/twilio-java/pull/538): bump maven-source-plugin from 3.0.1 to 3.2.1. Thanks to [@dependabot](https://github.com/dependabot)!
- [PR #534](https://github.com/twilio/twilio-java/pull/534): add Dependabot. Thanks to [@sullis](https://github.com/sullis)!

**Api**
- Added optional `JitterBufferSize` parameter for creating conference participant
- Added optional `label` property for conference participants
- Added optional parameter `caller_id` for creating conference participant endpoint.

**Autopilot**
- Remove Export resource from Autopilot Assistant

**Conversations**
- Expose Conversation timers

**Monitor**
- Update start/end date filter params to support date-or-time format **(breaking change)**

**Numbers**
- Add `provisionally-approved` as a Supporting Document status

**Preview**
- Removed `Authy` resources. **(breaking change)**

**Supersim**
- Add ready state to the allowed transitions in the sim update call behind the feature flag supersim.ready-state.v1

**Verify**
- Webhook resources added to Verify services and put behind the `api.verify.push` beta feature

**Twiml**
- Add more supported locales for the `Gather` verb.


[2020-06-10] Version 7.52.0
---------------------------
**Library - Docs**
- [PR #533](https://github.com/twilio/twilio-java/pull/533): link to custom HTTP client instructions. Thanks to [@thinkingserious](https://github.com/thinkingserious)!
- [PR #532](https://github.com/twilio/twilio-java/pull/532): link to descriptive exception types. Thanks to [@thinkingserious](https://github.com/thinkingserious)!

**Library - Chore**
- [PR #531](https://github.com/twilio/twilio-java/pull/531): drop the region being passed to each request in the REST object. Thanks to [@childish-sambino](https://github.com/childish-sambino)!

**Library - Feature**
- [PR #530](https://github.com/twilio/twilio-java/pull/530): add regional and edge support. Thanks to [@childish-sambino](https://github.com/childish-sambino)!

**Api**
- Added `pstnconnectivity` to `usage_record` API

**Autopilot**
- Add dialogue_sid param to Query list resource

**Notify**
- delivery_callback_url and delivery_callback_enabled added

**Numbers**
- Add `provisionally-approved` as a Bundle status

**Preview**
- `BrandsInformation` endpoint now returns a single `BrandsInformation`
- Deleted phone number required field in the brand phone number endpoint from `kyc-api`
- Removed insights `preview API` from API Definitions **(breaking change)**
- Added `BrandsInformation` endpoint to query brands information stored in KYC

**Supersim**
- Require a Network Access Profile when creating a Fleet **(breaking change)**


[2020-05-27] Version 7.51.0
---------------------------
**Library - Fix**
- [PR #529](https://github.com/twilio/twilio-java/pull/529): Fix datetime filter when the DateTime instances have timezone other than UTC. Thanks to [@adrianboimvaser](https://github.com/adrianboimvaser)!

**Api**
- Added `reason_conference_ended` and `call_sid_ending_conference` to Conference read/fetch/update
- Fixed some examples to use the correct "TK" SID prefix for Trunk resources.

**Authy**
- Renamed `twilio_authy_sandbox_mode` headers to `twilio_sandbox_mode` **(breaking change)**
- Renamed `Twilio-Authy-*` headers to `Twilio-Veriry-*` **(breaking change)**

**Flex**
- Adding `flex_service_instance_sid` to Flex Configuration

**Preview**
- Removed insights preview API from API Definitions **(breaking change)**
- Added `Channels` endpoint to brand a phone number for BrandedCalls

**Serverless**
- Add Build Sid to Log results

**Supersim**
- Add Network Access Profile resource Networks subresource
- Allow specifying a Data Limit on Fleets

**Trunking**
- Fixed some examples to use the correct "TK" SID prefix for Trunk resources.


[2020-05-13] Version 7.50.1
---------------------------
**Library - Chore**
- [PR #526](https://github.com/twilio/twilio-java/pull/526): bump jackson 2.10.4. Thanks to [@sullis](https://github.com/sullis)!

**Api**
- Add optional `emergency_caller_sid` parameter to SIP Domain
- Updated `call_reason` optional property to be treated as PII
- Added optional BYOC Trunk Sid property to Sip Domain API resource

**Autopilot**
- Add Restore resource to Autopilot Assistant

**Contacts**
- Added contacts Create API definition

**Events**
- Subscriptions API initial release

**Numbers**
- Add Evaluations API

**Supersim**
- Allow filtering the Fleets resource by Network Access Profile
- Allow assigning a Network Access Profile when creating and updating a Fleet
- Add Network Access Profiles resource

**Verify**
- Add `CustomCode` optional parameter on Verification creation.
- Add delete action on Service resource.

**Voice**
- Added endpoints for BYOC trunks, SIP connection policies and source IP mappings


[2020-04-29] Version 7.50.0
---------------------------
**Library - Feature**
- [PR #524](https://github.com/twilio/twilio-java/pull/524): add error details to rest and api exceptions. Thanks to [@ashish-s](https://github.com/ashish-s)!

**Preview**
- Added `Dispatch` version to `preview`

**Studio**
- Reroute Create Execution for V2 to the V2 downstream

**Supersim**
- Add Networks resource


[2020-04-15] Version 7.49.1
---------------------------
**Library - Fix**
- [PR #523](https://github.com/twilio/twilio-java/pull/523): adding new constructor. Thanks to [@Salil999](https://github.com/Salil999)!

**Library - Docs**
- [PR #522](https://github.com/twilio/twilio-java/pull/522): instructions on building your own local .jar. Thanks to [@thinkingserious](https://github.com/thinkingserious)!

**Library - Chore**
- [PR #521](https://github.com/twilio/twilio-java/pull/521): remove S3 URLs from test data. Thanks to [@childish-sambino](https://github.com/childish-sambino)!

**Api**
- Updated description for property `call_reason` in the call create request

**Contacts**
- Added Read, Delete All, and Delete by SID docs
- Initial Release

**Studio**
- Rename `flow_valid` to `flow_validate`
- Removed `errors` and `warnings` from flows error response and added new property named `details`
- Add Update Execution endpoints to v1 and v2 to end execution via API
- Add new `warnings` attribute v2 flow POST api

**Twiml**
- Add enhanced attribute to use with `speech_model` for the `Gather` verb


[2020-04-01] Version 7.49.0
---------------------------
**Library - Fix**
- [PR #520](https://github.com/twilio/twilio-java/pull/520): add exclusions for dependency convergence checks. Thanks to [@eshanholtz](https://github.com/eshanholtz)!

**Api**
- Add optional 'secure' parameter to SIP Domain

**Authy**
- Added an endpoint to list the challenges of a factor
- Added optional parameter `Push` when updating a service to send the service level push factor configuration

**Bulkexports**
- exposing bulk exports (vault/slapchop) API as public beta API

**Flex**
- Adding `queue_stats_configuration` and `wfm_integrations` to Flex Configuration

**Serverless**
- Add Function Version Content endpoint
- Allow build_sid to be optional for deployment requests

**Supersim**
- Remove `deactivated` status for Super SIM which is replaced by `inactive` **(breaking change)**


[2020-03-18] Version 7.48.0
---------------------------
**Library - Chore**
- [PR #519](https://github.com/twilio/twilio-java/pull/519): upgrade jackson to 2.10.3. Thanks to [@sullis](https://github.com/sullis)!

**Library - Fix**
- [PR #502](https://github.com/twilio/twilio-java/pull/502): shut down executorService thread automatically to allow the JVM to shut down gracefully. Thanks to [@Salil999](https://github.com/Salil999)!

**Api**
- Add optional `emergency_calling_enabled` parameter to SIP Domain
- Add optional property `call_reason` in the call create request

**Authy**
- Added `friendly_name` and `config` as optional params to Factor update
- Added `config` param to Factor creation **(breaking change)**

**Preview**
- Renamed `SuccessRate` endpoint to `ImpressionsRate` for Branded Calls (fka. Verified by Twilio) **(breaking change)**


[2020-03-04] Version 7.47.6
---------------------------
**Library - Chore**
- [PR #518](https://github.com/twilio/twilio-java/pull/518): fix JDK Travis failures. Thanks to [@childish-sambino](https://github.com/childish-sambino)!

**Authy**
- Added the `configuration` property to services to return the service level configurations
- Added optional parameter `Push` when creating a service to send the service level push factor configuration
- Remove FactorStrength support for Factors and Challenges **(breaking change)**

**Messaging**
- Correct the alpha sender capabilities property type **(breaking change)**

**Preview**
- Removed `/Devices` register Branded Calls endpoint, as per iOS sample app deprecation **(breaking change)**
- Removed `Twilio-Sandbox-Mode` request header from the Branded Calls endpoints, as not officially supported **(breaking change)**
- Removed `Verify` version from `preview` subdomain in favor to `verify` subdomain. **(breaking change)**

**Serverless**
- Add UI-Editable field to Services

**Supersim**
- Add `inactive` status for Super SIM which is an alias for `deactivated`

**Taskrouter**
- Adding value range to `priority` in task endpoint

**Verify**
- Fix `SendCodeAttempts` type. It's an array of objects instead of a unique object. **(breaking change)**


[2020-02-19] Version 7.47.5
---------------------------
**Library - Fix**
- [PR #516](https://github.com/twilio/twilio-java/pull/516): Do not include null values in JWT payloads. Thanks to [@thinkingserious](https://github.com/thinkingserious)!

**Api**
- Make call create parameters `async_amd`, `async_amd_status_callback`, and `async_amd_status_callback_method` public
- Add `trunk_sid` as an optional field to Call resource fetch/read responses
- Add property `queue_time` to successful response of create, fetch, and update requests for Call
- Add optional parameter `byoc` to conference participant create.

**Authy**
- Added support for challenges associated to push factors

**Flex**
- Adding `ui_dependencies` to Flex Configuration

**Messaging**
- Deprecate Session API **(breaking change)**

**Numbers**
- Add Regulations API

**Studio**
- Add Execution and Step endpoints to v2 API
- Add webhook_url to Flow response and add new /TestUsers endpoint to v2 API

**Taskrouter**
- Adding `longest_relative_task_age_in_queue` and `longest_relative_task_sid_in_queue` to TaskQueue Real Time Statistics API.
- Add `wait_duration_in_queue_until_accepted` aggregations to TaskQueues Cumulative Statistics endpoint
- Add TaskQueueEnteredDate property to Tasks.

**Video**
- [Composer] Clarification for the composition hooks creation documentation: one source is mandatory, either the `audio_sources` or the `video_layout`, but one of them has to be provided
- [Composer] `audio_sources` type on the composer HTTP POST command, changed from `sid[]` to `string[]` **(breaking change)**
- [Composer] Clarification for the composition creation documentation: one source is mandatory, either the `audio_sources` or the `video_layout`, but one of them has to be provided


[2020-02-05] Version 7.47.4
---------------------------
**Library - Chore**
- [PR #514](https://github.com/twilio/twilio-java/pull/514): Update jjwt dependency version 0.10.7. Thanks to [@eager](https://github.com/eager)!

**Library - Test**
- [PR #506](https://github.com/twilio/twilio-java/pull/506): use [equalsverifier] library to verify 'equals' methods. Thanks to [@sullis](https://github.com/sullis)!

**Api**
- Making content retention and address retention public
- Update `status` enum for Messages to include 'partially_delivered'

**Authy**
- Added support for push factors

**Autopilot**
- Add one new property in Query i.e dialogue_sid

**Verify**
- Add `SendCodeAttempts` to create verification response.

**Video**
- Clarification in composition creation documentation: one source is mandatory, either `audio_sources` or `video_layout`, but on of them has to be provided

**Twiml**
- Add Polly Neural voices.


[2020-01-23] Version 7.47.3
---------------------------
**Library - Docs**
- [PR #510](https://github.com/twilio/twilio-java/pull/510): baseline all the templated markdown docs. Thanks to [@childish-sambino](https://github.com/childish-sambino)!

**Library - Chore**
- [PR #509](https://github.com/twilio/twilio-java/pull/509): Upgrade archunit to version 0.13.0. Thanks to [@sullis](https://github.com/sullis)!

**Api**
- Add payments public APIs
- Add optional parameter `byoc` to call create request.

**Flex**
- Updating a Flex Flow `creation_on_message` parameter documentation

**Preview**
-
- Removed Verify v2 from preview in favor of its own namespace as GA **(breaking change)**

**Studio**
- Flow definition type update from string to object

**Verify**
- Add `AppHash` parameter when creating a Verification.
- Add `DoNotShareWarningEnabled` parameter to the Service resource.

**Twiml**
- Add `track` attribute to siprec noun.
- Add attribute `byoc` to `<Number>`


[2020-01-08] Version 7.47.2
---------------------------
**Library - Chore**
- [PR #508](https://github.com/twilio/twilio-java/pull/508): update jjwt to v0.9.1. Thanks to [@saksham93](https://github.com/saksham93)!
- [PR #507](https://github.com/twilio/twilio-java/pull/507): upgrade jackson to 2.10.2. Thanks to [@sullis](https://github.com/sullis)!

**Numbers**
- Add Regulatory Compliance CRUD APIs

**Studio**
- Add parameter validation for Studio v2 Flows API

**Twiml**
- Add support for `speech_model` to `Gather` verb


[2019-12-18] Version 7.47.1
---------------------------
**Preview**
- Add `/Insights/SuccessRate` endpoint for Businesses Branded Calls (Verified by Twilio)

**Studio**
- StudioV2 API in beta

**Verify**
- Add `MailerSid` property to Verify Service resource.

**Wireless**
- Added `data_limit_strategy` to Rate Plan resource.


[2019-12-12] Version 7.47.0
---------------------------
**Library**
- [PR #504](https://github.com/twilio/twilio-java/pull/504): feat: add 'order_by' and 'skip_if' parameters in WorkflowRuleTarget. Thanks to [@nikhil-vk](https://github.com/nikhil-vk)!

**Api**
- Make `twiml` conditional for create. One of `url`, `twiml`, or `application_sid` is now required.
- Add `bundle_sid` parameter to /IncomingPhoneNumbers API
- Removed discard / obfuscate parameters from ContentRetention, AddressRetention **(breaking change)**

**Chat**
- Added `last_consumed_message_index` and `last_consumption_timestamp` parameters in update method for UserChannel resource **(breaking change)**

**Conversations**
- Add Participant SID to Message properties

**Messaging**
- Fix incorrectly typed capabilities property for ShortCodes. **(breaking change)**


[2019-12-04] Version 7.46.0
---------------------------
**Library**
- [PR #500](https://github.com/twilio/twilio-java/pull/500): chore: upgrade archunit to version 0.12.0. Thanks to [@sullis](https://github.com/sullis)!
- [PR #499](https://github.com/twilio/twilio-java/pull/499): docs: add supported language versions to README. Thanks to [@childish-sambino](https://github.com/childish-sambino)!
- [PR #489](https://github.com/twilio/twilio-java/pull/489): chore: upgrade jackson to version 2.10.1. Thanks to [@sullis](https://github.com/sullis)!
- [PR #498](https://github.com/twilio/twilio-java/pull/498): fix: Rename child twiml methods to be the tag name and deprecate old methods. Thanks to [@eshanholtz](https://github.com/eshanholtz)!
- [PR #496](https://github.com/twilio/twilio-java/pull/496): docs: add an example for handling exceptions. Thanks to [@childish-sambino](https://github.com/childish-sambino)!
- [PR #494](https://github.com/twilio/twilio-java/pull/494): fix: Add URI encoding for phone numbers. Thanks to [@eshanholtz](https://github.com/eshanholtz)!
- [PR #492](https://github.com/twilio/twilio-java/pull/492): fix: add new Twiml type to deal with constructor overloading issue. Thanks to [@craigsdennis](https://github.com/craigsdennis)!
- [PR #491](https://github.com/twilio/twilio-java/pull/491): Allow missing GPG env vars when running on a fork. Thanks to [@childish-sambino](https://github.com/childish-sambino)!
- [PR #490](https://github.com/twilio/twilio-java/pull/490): docs: add an example using a different HTTP client. Thanks to [@childish-sambino](https://github.com/childish-sambino)!

**Api**
- Add optional `twiml` parameter for call create

**Chat**
- Added `delete` method in UserChannel resource

**Conversations**
- Allow Messaging Service update

**Taskrouter**
- Support ReEvaluateTasks parameter on Workflow update

**Twiml**
- Remove unsupported `mixed_track` value from `<Stream>` **(breaking change)**
- Add missing fax `<Receive>` optional attributes


[2019-11-13] Version 7.45.1
---------------------------
**Library**
- [PR #487](https://github.com/twilio/twilio-java/pull/487): Bump jackson-databind from 2.9.9.1 to 2.9.10.1. Thanks to [@dependabot](https://github.com/dependabot)!
- [PR #486](https://github.com/twilio/twilio-java/pull/486): add TLS 1.2 warning. Thanks to [@eshanholtz](https://github.com/eshanholtz)!
- [PR #485](https://github.com/twilio/twilio-java/pull/485): Move generated docs to common location. Thanks to [@childish-sambino](https://github.com/childish-sambino)!
- [PR #484](https://github.com/twilio/twilio-java/pull/484): Auto-deploy via Travis CI upon tagged commit to main. Thanks to [@thinkingserious](https://github.com/thinkingserious)!

**Api**
- Make `persistent_action` parameter public
- Add `twiml` optional private parameter for call create

**Autopilot**
- Add Export resource to Autopilot Assistant.

**Flex**
- Added Integration.RetryCount attribute to Flex Flow
- Updating a Flex Flow `channel_type` options documentation

**Insights**
- Added edges to events and metrics
- Added new endpoint definitions for Events and Metrics

**Messaging**
- **create** support for sender registration
- **fetch** support for fetching a sender
- **update** support for sender verification

**Supersim**
- Add `Direction` filter parameter to list commands endpoint
- Allow filtering commands list by Sim Unique Name
- Add `Iccid` filter parameter to list sims endpoint

**Twiml**
- Add support for `<Refer>` verb


[2019-10-31] Version 7.45.0
---------------------------
**Api**
- Add new usage categories to the public api `sms-messages-carrierfees` and `mms-messages-carrierfees`

**Conversations**
- Add ProjectedAddress to Conversations Participant resource

**Preview**
- Implemented different `Sid` for Current Calls (Verified by Twilio), instead of relying in `Call.Sid` from Voice API team **(breaking change)**

**Supersim**
- Add List endpoint to Commands resource for Super Sim Pilot
- Add UsageRecords resource for the Super Sim Pilot
- Add List endpoint to UsageRecords resource for the Super Sim Pilot
- Allow assigning a Sim to a Fleet by Fleet SID or Unique Name for Super SIM Pilot
- Add Update endpoint to Fleets resource for Super Sim Pilot
- Add Fetch endpoint to Commands resource for Super Sim Pilot
- Allow filtering the Sims resource List endpoint by Fleet
- Add List endpoint to Fleets resource for Super Sim Pilot

**Wireless**
- Added `account_sid` to Sim update parameters.

**Twiml**
- Add new locales and voices for `Say` from Polly


[2019-10-16] Version 7.44.0
---------------------------
**Library**
- [PR #483](https://github.com/twilio/twilio-java/pull/483): VIDEO-2338 TrackPriority.STANDARD before GA. Thanks to [@innerverse](https://github.com/innerverse)!
- [PR #482](https://github.com/twilio/twilio-java/pull/482): breaking: Correct video composition date and callback types. Thanks to [@childish-sambino](https://github.com/childish-sambino)! **(breaking change)**

**Api**
- Add new property `attempt` to sms_messages
- Fixed a typo in the documentation for Feedback outcome enum **(breaking change)**
- Update the call price to be optional for deserializing **(breaking change)**

**Flex**
- Added `JanitorEnabled` attribute to Flex Flow
- Change `features_enabled` Flex Configuration key to private **(breaking change)**

**Supersim**
- Add Fetch endpoint to Fleets resource for Super Sim Pilot
- Allow assigning a Sim to a Fleet for Super Sim Pilot
- Add Create endpoint to Fleets resource for Super Sim Pilot

**Twiml**
- Update `<Conference>` rename "whisper" attribute to "coach" **(breaking change)**


[2019-10-02] Version 7.43.1
---------------------------
**Library**
- [PR #481](https://github.com/twilio/twilio-java/pull/481): added request validation for urls with and without ports. Thanks to [@eshanholtz](https://github.com/eshanholtz)!

**Conversations**
- Add media to Conversations Message resource

**Supersim**
- Add List endpoint to Sims resource for Super Sim Pilot


[2019-09-18] Version 7.43.0
----------------------------
**Numbers**
- Add v2 of the Identites API

**Preview**
- Changed authentication method for SDK Trusted Comms endpoints: `/CPS`, `/CurrentCall`, and `/Devices`. Please use `Authorization: Bearer <xCNAM JWT>` **(breaking change)**

**Voice**
- Add Recordings endpoints


[2019-09-04] Version 7.42.0
----------------------------
**Library**
- [PR #480](https://github.com/twilio/twilio-java/pull/480): fix Javadoc copyright year. Thanks to [@sullis](https://github.com/sullis)!
- [PR #478](https://github.com/twilio/twilio-java/pull/478): Upgrade maven-source-plugin to 3.1.0. Thanks to [@sullis](https://github.com/sullis)!
- [PR #479](https://github.com/twilio/twilio-java/pull/479): Upgrade maven-javadoc-plugin to 3.1.1. Thanks to [@sullis](https://github.com/sullis)!
- [PR #471](https://github.com/twilio/twilio-java/pull/471): Catch NPE when creating URI. Thanks to [@pulkitsethi](https://github.com/pulkitsethi)!
- [PR #410](https://github.com/twilio/twilio-java/pull/410): Add jsonToMap in converter. Thanks to [@igracia](https://github.com/igracia)!
- [PR #477](https://github.com/twilio/twilio-java/pull/477): ComplianceTest: resources haveOnlyPrivateConstructors(). Thanks to [@sullis](https://github.com/sullis)!
- [PR #476](https://github.com/twilio/twilio-java/pull/476): Upgrade archunit to version 0.11.0. Thanks to [@sullis](https://github.com/sullis)!

**Api**
-  Pass Twiml in call update request

**Conversations**
- Add attributes to Conversations resources

**Flex**
- Adding `features_enabled` and `serverless_service_sids` to Flex Configuration

**Messaging**
- Message API required params updated **(breaking change)**

**Preview**
- Added support for the optional `CallSid` to `/BrandedCalls` endpoint


[2019-08-21] Version 7.41.2
----------------------------
**Library**
- [PR #475](https://github.com/twilio/twilio-java/pull/475): Update the Flex domain name to be 'flex-api'. Thanks to [@childish-sambino](https://github.com/childish-sambino)!

**Conversations**
- Add Chat Conversation SID to conversation default output properties

**Flex**
- Adding `outbound_call_flows` object to Flex Configuration
- Adding read and fetch to channels API

**Supersim**
- Add Sims and Commands resources for the Super Sim Pilot

**Sync**
- Added configuration option for enabling webhooks from REST.

**Wireless**
- Added `usage_notification_method` and `usage_notification_url` properties to `rate_plan`.

**Twiml**
- Add support for `ach-debit` transactions in `Pay` verb


[2019-08-05] Version 7.41.1
----------------------------
**Library**
- [PR #472](https://github.com/twilio/twilio-java/pull/472): add [Sean Sullivan] to authors document. Thanks to [@sullis](https://github.com/sullis)!

**Preview**
- Added support for the header `Twilio-Sandbox-Mode` to mock all Voice dependencies

**Twiml**
- Add support for `<Siprec>` noun
- Add support for `<Stream>` noun
- Create verbs `<Start>` and `<Stop>`


[2019-07-31] Version 7.41.0
----------------------------
**Library**
- [PR #473](https://github.com/twilio/twilio-java/pull/473): Security vulnerability fix by updating jackson-databind version to 2.9.9.1. Thanks to [@krantitalluri](https://github.com/krantitalluri)!

**Insights**
- Added `properties` to summary.

**Preview**
- Added endpoint to brand a call without initiating it, so it can be initiated manually by the Customer

**Twiml**
- Update `<Conference>` recording events **(breaking change)**


[2019-07-10] Version 7.40.1
----------------------------
**Library**
- [PR #459](https://github.com/twilio/twilio-java/pull/459): Update library dependencies. Thanks to [@duttonw](https://github.com/duttonw)!
- [PR #469](https://github.com/twilio/twilio-java/pull/469): Add urls property in ice servers. Thanks to [@FMV1491](https://github.com/FMV1491)!
- [PR #467](https://github.com/twilio/twilio-java/pull/467): Remove VS Code settings. Thanks to [@thinkingserious](https://github.com/thinkingserious)!
- [PR #465](https://github.com/twilio/twilio-java/pull/465): Replace commons codec. Thanks to [@jasonsoooz](https://github.com/jasonsoooz)!
- [PR #466](https://github.com/twilio/twilio-java/pull/466): Ignore local settings. Thanks to [@thinkingserious](https://github.com/thinkingserious)!

**Api**
- Make `friendly_name` optional for applications create
- Add new property `as_of` date to Usage Record API calls

**Wireless**
- Added Usage Records resource.


[2019-06-26] Version 7.40.0
----------------------------
**Library**
- [PR #464](https://github.com/twilio/twilio-java/pull/464): Add [archunit] rules. Thanks to [@sullis](https://github.com/sullis)!

**Autopilot**
- Adds two new properties in Assistant i.e needs_model_build and development_stage

**Preview**
- Changed phone numbers from _URL|Path_ to `X-XCNAM-Sensitive` headers **(breaking change)**

**Verify**
- Add `MessagingConfiguration` resource to verify service


[2019-06-12] Version 7.39.0
----------------------------
**Autopilot**
- Add Webhooks resource to Autopilot Assistant.

**Flex**
- Added missing 'custom' type to Flex Flow
- Adding `integrations` to Flex Configuration

**Insights**
- Added attributes to summary.

**Messaging**
- Message API Create updated with conditional params **(breaking change)**

**Proxy**
- Document that Proxy will return a maximum of 100 records for read/list endpoints **(breaking change)**
- Remove non-updatable property parameters for Session update (mode, participants) **(breaking change)**

**Sync**
- Added reachability debouncing configuration options.

**Verify**
- Add `RateLimits` and `Buckets` resources to Verify Services
- Add `RateLimits` optional parameter on `Verification` creation.

**Twiml**
- Fix `<Room>` participantIdentity casing


[2019-05-29] Version 7.38.1
----------------------------
**Library**
- [PR #461](https://github.com/twilio/twilio-java/pull/461): Add SubscribeRule and SubscribeRulesUpdate. Thanks to [@innerverse](https://github.com/innerverse)!
- [PR #456](https://github.com/twilio/twilio-java/pull/456): Code cleanup. Thanks to [@ethanwood17](https://github.com/ethanwood17)!
- [PR #444](https://github.com/twilio/twilio-java/pull/444): maven-compiler-plugin 3.8.0. Thanks to [@sullis](https://github.com/sullis)!
- [PR #460](https://github.com/twilio/twilio-java/pull/460): jackson 2.9.9. Thanks to [@sullis](https://github.com/sullis)!
- [PR #457](https://github.com/twilio/twilio-java/pull/457): Update the call create 'from' param to be endpoint type for client identifier support. Thanks to [@childish-sambino](https://github.com/childish-sambino)!
- [PR #458](https://github.com/twilio/twilio-java/pull/458): Prefix client identifiers with 'client:' if not already prefixed. Thanks to [@childish-sambino](https://github.com/childish-sambino)!

**Verify**
- Add `approved` to status enum


[2019-05-15] Version 7.38.0
----------------------------
**Api**
- Make `method` optional for queue members update

**Chat**
- Removed `webhook.*.format` update parameters in Service resource from public library visibility in v1 **(breaking change)**

**Insights**
- Added client metrics as sdk_edge to summary.
- Added optional query param processing_state.

**Numbers**
- Add addtional metadata fields on a Document
- Add status callback fields and parameters

**Taskrouter**
- Added `channel_optimized_routing` attribute to task-channel endpoint

**Video**
- [Rooms] Add Video Subscription API

**Wireless**
- Added `imei` to Data Session resource.
- Remove `imeisv` from Data Session resource. **(breaking change)**


[2019-05-01] Version 7.37.4
----------------------------
**Serverless**
- Documentation

**Wireless**
- Added `imeisv` to Data Session resource.


[2019-04-24] Version 7.37.3
----------------------------
**Api**
- Add `verified` property to Addresses

**Numbers**
- Add API for Identites and documents

**Proxy**
- Add in use count on number instance


[2019-04-12] Version 7.37.2
----------------------------
**Serverless**
- Serverless scaffolding


[2019-04-12] Version 7.37.1
----------------------------
**Library**
- PR #454: Add more JDK versions to Travis CI config. Thanks to @childish-sambino!
- PR #453: Bump JMockit version for minimum compatibility with Java 11. Thanks to @childish-sambino!

**Flex**
- Adding PluginService to Flex Configuration

**Numbers**
- Add API for Proof of Addresses

**Proxy**
- Clarify documentation for Service and Session fetch


[2019-03-28] Version 7.37.0
----------------------------
**Api**
- Remove optional `if_machine` call create parameter from helper libraries **(breaking change)**
- Changed `call_sid` path parameter type on QueueMember fetch and update requests **(breaking change)**

**Voice**
- changed file names to dialing_permissions prefix **(breaking change)**

**Wireless**
- Added `ResetStatus` property to Sim resource to allow resetting connectivity via the API.


[2019-03-15] Version 7.36.2
----------------------------
**Library**
- PR #452: Add Help Center and Support Ticket links to the README. Thanks to @childish-sambino!

**Api**
- Add `machine_detection_speech_threshold`, `machine_detection_speech_end_threshold`, `machine_detection_silence_timeout` optional params to Call create request

**Flex**
- Adding Flex Channel Orchestration
- Adding Flex Flow


[2019-03-06] Version 7.36.1
----------------------------
**Twiml**
- Add `de1` to `<Conference>` regions


[2019-03-01] Version 7.36.0
----------------------------
**Api**
- Make conference participant preview parameters public

**Authy**
- Added support for FactorType and FactorStrength for Factors and Challenges

**Iam**
- First public release

**Verify**
- Add endpoint to update/cancel a Verification **(breaking change)**

**Video**
- [Composer] Make RoomSid mandatory **(breaking change)**
- [Composer] Add `enqueued` state to Composition

**Twiml**
- Update message body to not be required for TwiML `Dial` noun.


[2019-02-15] Version 7.35.1
----------------------------
**Library**
- PR #449: Allow POST requests that are void of a body or any params when using the ValidationClient. Thanks to @childish-sambino!

**Api**
- Add `force_opt_in` optional param to Messages create request
- Add agent conference category to usage records

**Flex**
- First public release

**Taskrouter**
- Adding `reject_pending_reservations` to worker update endpoint
- Added `event_date_ms` and `worker_time_in_previous_activity_ms` to Events API response
- Add ability to filter events by TaskChannel

**Verify**
- Add `EnablePsd2` optional parameter for PSD2 on Service resource creation or update.
- Add `Amount`, `Payee` optional parameters for PSD2.


[2019-02-04] Version 7.35.0
----------------------------
**Library**
- PR #448: Switch body validator to use hex instead of base64. Thanks to @cjcodes!
- PR #447: Upgrade jackson library to 2.9.8. Thanks to @mbichoffe!

**Video**
- [Recordings] Add media type filter to list operation
- [Composer] Filter Composition Hook resources by FriendlyName

**Twiml**
- Update `language` enum for `Gather` to fix language code for Filipino (Philippines) and include additional supported languages **(breaking change)**


[2019-01-11] Version 7.34.1
----------------------------
**Verify**
- Add `lookup` information in the response when creating a new verification (depends on the LookupEnabled flag being enabled at the service level)
- Add `VerificationSid` optional parameter on Verification check.


[2019-01-10] Version 7.34.0
----------------------------
**Chat**
- Mark Member attributes as PII

**Proxy**
- Remove unsupported query parameters **(breaking change)**
- Remove invalid session statuses in doc


[2019-01-02] Version 7.33.1
----------------------------
**Insights**
- Initial revision.


[2018-12-14] Version 7.33.0
----------------------------
**Authy**
- Reverted the change to `FactorType` and `FormType`, avoiding conflicts with Helper Libraries reserved words (`type`) **(breaking change)**

**Proxy**
- Remove incorrect parameter for Session List

**Studio**
- Support date created filtering on list of executions

**Taskrouter**
- Adding ability to Create, Modify and Delete Task Channels.

**Verify**
- Add `SkipSmsToLandlines`, `TtsName`, `DtmfInputRequired` optional parameters on Service resource creation or update.

**Wireless**
- Added delete action on Command resource.
- Added delete action on Sim resource.

**Twiml**
- Change `currency` from enum to string for `Pay` **(breaking change)**


[2018-11-30] Version 7.32.0
----------------------------
**Library**
- PR #445: Bump httpclient httpcore and jackson version. Thanks to @yannieyip!

**Api**
- Add `interactive_data` optional param to Messages create request

**Authy**
- Required authentication for `/v1/Forms/{type}` endpoint **(breaking change)**
- Removed `Challenge.reason` to `Challenge.responded_reason`
- Removed `verification_sid` from Challenge responses
- Removed `config` param from the Factor creation
- Replaced all occurrences of `FactorType` and `FormType` in favor of a unified `Type` **(breaking change)**

**Chat**
- Add Member attributes

**Preview**
- Removed `Authy` version from `preview` subdomain in favor to `authy` subdomain. **(breaking change)**

**Verify**
- Add `CustomCode` optional parameter on Verication creation.


[2018-11-16] Version 7.31.0
----------------------------
**Messaging**
- Session API

**Twiml**
- Change `master-card` to `mastercard` as `cardType` for `Pay` and `Prompt`, remove attribute `credential_sid` from `Pay` **(breaking change)**


[2018-10-28] Version 7.30.0
----------------------------
**Api**
- Add new Balance resource:
    - url: '/v1/Accounts/{account sid}/Balance'
    - supported methods: GET
    - returns the balance of the account

**Proxy**
- Add chat_instance_sid to Service

**Verify**
- Add `Locale` optional parameter on Verification creation.


[2018-10-15] Version 7.29.0
----------------------------
**Api**
- Add <Pay> Verb Transactions category to usage records

**Twiml**
- Add support for `Pay` verb


[2018-10-15] Version 7.28.0
----------------------------
**Library**
- PR #440: CLIENT-4598 | Fix null incomingAllow bug and add test. Thanks to @ryan-rowland!

**Api**
- Add `coaching` and `call_sid_to_coach` to participant properties, create and update requests.

**Authy**
- Set public library visibility, and added PII stanza
- Dropped support for `FactorType` param given new Factor prefixes **(breaking change)**
- Supported `DELETE` actions for Authy resources
- Move Authy Services resources to `authy` subdomain

**Autopilot**
- Introduce `autopilot` subdomain with all resources from `preview.understand`

**Preview**
- Renamed Understand intent to task **(breaking change)**
- Deprecated Authy endpoints from `preview` to `authy` subdomain

**Taskrouter**
- Allow TaskQueue ReservationActivitySid and AssignmentActivitySid to not be configured for MultiTask Workspaces

**Verify**
- Add `LookupEnabled` optional parameter on Service resource creation or update.
- Add `SendDigits` optional parameter on Verification creation.
- Add delete action on Service resourse.

**Twiml**
- Add custom parameters to TwiML `Client` noun and renamed the optional `name` field to `identity`. This is a breaking change in Ruby, and applications will need to transition from `dial.client ''` and `dial.client 'alice'` formats to `dial.client` and `dial.client(identity: alice)` formats. **(breaking change)**


[2018-10-04] Version 7.27.0
----------------------------
**Library**
- PR #439: Nest IOException inside APIException. Thanks to @yannieyip!

**Twiml**
- Add `debug` to `Gather`
- Add `participantIdentity` to `Room`


[2018-09-28] Version 7.26.0
----------------------------
**Api**
- Set `call_sid_to_coach` parameter in participant to be `preview`

**Preview**
- Renamed response headers for Challenge and Factors Signatures
- Supported `totp` in Authy preview endpoints
- Allowed `latest` in Authy Challenges endpoints

**Video**
- [Composer] Add Composition Hook resources

**Voice**
- changed path param name from parent_iso_code to iso_code for highrisk_special_prefixes api **(breaking change)**
- added geo permissions public api


[2018-09-20] Version 7.25.0
----------------------------
**Preview**
- Add `Form` resource to Authy preview given a `form_type`
- Add Authy initial api-definitions in the 4 main resources: Services, Entities, Factors, Challenges

**Pricing**
- add voice_numbers resource (v2)

**Verify**
- Move from preview to beta **(breaking change)**


[2018-08-31] Version 7.24.2
----------------------------
**Library**
- PR #438:  VCORE-3651 Add support for *for* attribute in twiml element. Thanks to @nmahure!

**Api**
- Add `call_sid_to_coach` parameter to participant create request
- Add `voice_receive_mode` param to IncomingPhoneNumbers create

**Video**
- [Recordings] Expose `offset` property in resource


[2018-08-23] Version 7.24.1
----------------------------
**Library**
- PR #436: Create new class, OutboundCallPriceWithOrigin. Thanks to @jbonner89!

**Chat**
- Add User Channel instance resource


[2018-08-17] Version 7.24.0
----------------------------
**Library**
- PR #434:  add type OutboundPrefixPriceWithOrigin. Thanks to @jbonner89!

**Api**
- Add Proxy Active Sessions category to usage records

**Preview**
- Add `Actions` endpoints and remove `ResponseUrl` from assistants on the Understand api

**Pricing**
- add voice_country resource (v2)


[2018-08-09] Version 7.23.1
----------------------------
**Preview**
- Add new Intent Statistics endpoint
- Remove `ttl` from Assistants

**Studio**
- Studio is now GA


[2018-08-03] Version 7.23.0
----------------------------
**Library**
- PR #426: Tag and push Docker latest image when deploying with TravisCI. Thanks to @jonatasbaldin!

**Chat**
- Make message From field updatable
- Add REST API webhooks

**Notify**
- Removing deprecated `segments`, `users`, `segment_memberships`, `user_bindings` classes from helper libraries. **(breaking change)**

**Twiml**
- Add `Connect` and `Room` for Programmable Video Rooms


[2018-07-27] Version 7.22.2
----------------------------
**Library**
- PR #433: Clone the headers object and add test for thread safety. Thanks to @cjcodes!


[2018-07-26] Version 7.22.1
----------------------------
**Api**
- Add support for sip domains to map credential lists for registrations

**Preview**
- Remove `ttl` from Assistants

**Proxy**
- Enable setting a proxy number as reserved

**Video**
- Add `group-small` room type

**Twiml**
- Add support for SSML lang tag on Say verb


[2018-07-16] Version 7.22.0
----------------------------
**Library**
- PR #427: Update Response to use Scanner hasNext(). Thanks to @cjcodes!
- PR #424: Add a method for validation of JSON body. Thanks to @cjcodes!

**Twiml**
- Add support for SSML on Say verb, the message body is changed to be optional **(breaking change)**


[2018-07-11] Version 7.21.8
----------------------------
**Library**
- PR #425: Add correct JDK version statement to deploy. Thanks to @jonatasbaldin!

**Api**
- Add `cidr_prefix_length` param to SIP IpAddresses API

**Studio**
- Add new /Execution endpoints to begin Engagement -> Execution migration

**Video**
- [Rooms] Allow deletion of individual recordings from a room


[2018-07-05] Version 7.21.7
----------------------------
**Library**
- PR #418: Add Dockerfile and related changes to build the Docker image. Thanks to @jonatasbaldin!

**Api**
- Release `Call Recording Controls` feature support in helper libraries
- Add Voice Insights sub-category keys to usage records


[2018-06-29] Version 7.21.6
----------------------------
**Library**
- PR #421: Tests for mixed TwiML content. Thanks to @ekarson!
- PR #420: Update tests with generated version. Thanks to @mbichoffe!
- PR #419: Allow adding TwiML children with generic tag names. Thanks to @mbichoffe!


[2018-06-21] Version 7.21.5
----------------------------
**Api**
- Add Fraud Lookups category to usage records

**Video**
- Allow user to set `ContentDisposition` when obtaining media URLs for Room Recordings and Compositions
- Add Composition Settings resource


[2018-06-15] Version 7.21.4
----------------------------
**Library**
- PR #416: Allow adding text nodes under any TwiML element. Thanks to @ekarson!
- PR #414: Convenience method for verifying your connection with the new SSL certificate. Thanks to @ekarson!

**Twiml**
- Add methods to helper libraries to inject arbitrary text under a TwiML node


[2018-06-04] Version 7.21.3
----------------------------
**Lookups**
- Add back support for `fraud` lookup type


[2018-05-29] Version 7.21.2
----------------------------
**Chat**
- Add Binding and UserBinding documentation

**Studio**
- Add endpoint to delete engagements

**Trunking**
- Added cnam_lookup_enabled parameter to Trunk resource.
- Added case-insensitivity for recording parameter to Trunk resource.


[2018-05-18] Version 7.21.1
----------------------------
**Api**
- Add more programmable video categories to usage records
- Add 'include_subaccounts' parameter to all variation of usage_record fetch


[2018-05-11] Version 7.21.0
----------------------------
**Library**
- PR #411: Update Jackson to version 2.8.11. Thanks to @jmctwilio!

**Chat**
- Add Channel Webhooks resource

**Monitor**
- Update event filtering to support date/time **(breaking change)**

**Wireless**
- Updated `maturity` to `ga` for all wireless apis


[2018-04-28] Version 7.20.0
----------------------------
**Library**
- PR #408: Strip default ports in RequestCanonicalizer. Thanks to @pkiv!

**Video**
- Redesign API by adding custom `VideoLayout` object. **(breaking change)**


[2018-04-20] Version 7.19.1
----------------------------
**Twiml**
- Gather input Enum: remove unnecessary "dtmf speech" value as you can now specify multiple enum values for this parameter and both "dtmf" and "speech" are already available.


[2018-04-13] Version 7.19.0
----------------------------
**Library**
- PR #404: Add incoming.allow to AccessToken VoiceGrant. Thanks to @ryan-rowland!

**Preview**
- Support for Understand V2 APIs - renames various resources and adds new fields

**Studio**
- Change parameters type from string to object in engagement resource

**Video**
- [Recordings] Change `size` type to `long`. **(breaking change)**


[2018-03-22] Version 7.18.0
----------------------------
**Lookups**
- Disable support for `fraud` lookups *(breaking change)*

**Preview**
- Add `BuildDuration` and `ErrorCode` to Understand ModelBuild

**Studio**
- Add new /Context endpoint for step and engagement resources.


[2018-03-12] Version 7.17.9
----------------------------
**Api**
- Add `caller_id` param to Outbound Calls API
- Release `trim` recording Outbound Calls API functionality in helper libraries

**Video**
- [composer] Add `room_sid` to Composition resource.


[2018-02-23] Version 7.17.8
----------------------------
**Api**
- Add `trim` param to Outbound Calls API

**Lookups**
- Add support for `fraud` lookup type

**Numbers**
- Initial Release

**Video**
- [composer] Add `SEQUENCE` value to available layouts, and `trim` and `reuse` params.


[2018-02-09] Version 7.17.7
----------------------------
**Api**
- Add `AnnounceUrl` and `AnnounceMethod` params for conference announce

**Chat**
- Add support to looking up user channels by identity in v1


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
