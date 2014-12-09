package com.twilio.sdk.resources;

import com.fasterxml.jackson.annotation.JsonCreator;

public enum UsageCategory {
    CALLS("calls"),
    CALLS_INBOUND("calls-inbound"),
    CALLS_INBOUND_LOCAL("calls-inbound-local"),
    CALLS_INBOUND_TOLLFREE("calls-inbound-tollfree"),
    CALLS_OUTBOUND("calls-outbound"),
    CALLS_CLIENT("calls-client"),
    CALLS_SIP("calls-sip"),
    SMS("sms"),
    SMS_INBOUND("sms-inbound"),
    SMS_INBOUND_SHORTCODE("sms-inbound-shortcode"),
    SMS_INBOUND_LONGCODE("sms-inbound-longcode"),
    SMS_OUTBOUND("sms-outbound"),
    SMS_OUTBOUND_SHORTCODE("sms-outbound-shortcode"),
    SMS_OUTBOUND_LONGCODE("sms-outbound-longcode"),
    PHONENUMBERS("phonenumbers"),
    PHONENUMBERS_TOLL_FREE("phonenumbers-tollfree"),
    PHONENUMBERS_LOCAL("phonenumbers-local"),
    SHORTCODES("shortcodes"),
    SHORTCODES_VANITY("shortcodes-vanity"),
    SHORTCODES_RANDOM("shortcodes-random"),
    SHORTCODES_CUSTOMEROWNED("shortcodes-customerowned"),
    CALLERIDLOOKUPS("calleridlookups"),
    RECORDINGS("recordings"),
    TRANSCRIPTIONS("transcriptions"),
    RECORDINGSTORAGE("recordingstorage"),
    TOTALPRICE("totalprice");

    private final String category;

    UsageCategory(final String category) {
        this.category = category;
    }

    public String toString() {
        return category;
    }

    @JsonCreator
    public static UsageCategory forValue(final String value) {
        String munged = value.replace("-", "_")
                             .toUpperCase();
        return UsageCategory.valueOf(munged);
    }
}
