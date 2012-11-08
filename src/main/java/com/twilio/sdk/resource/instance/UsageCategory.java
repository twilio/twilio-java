package com.twilio.sdk.resource.instance;


/**
 * The Enum UsageCategory.
 *
 * For more information see <a href="http://www.twilio.com/docs/api/rest/usage-records#usage-categories">http://www.twilio.com/docs/api/rest/usage-records#usage-categories</a>
 *
 */
public enum UsageCategory {
    calleridlookups,
    calls, calls_client, calls_inbound, calls_inbound_local, calls_inbound_tollfree, calls_outbound,
    phonenumbers_local, phonenumbers, phonenumbers_tollfree,
    shortcodes, shortcodes_customerowned, shortcodes_random, shortcodes_vanity,
    sms, sms_inbound, sms_inbound_longcode, sms_inbound_shortcode, sms_outbound, sms_outbound_longcode,
    sms_outbound_shortcode,
    recordings, recordingstorage, transcriptions, totalprice,
}
