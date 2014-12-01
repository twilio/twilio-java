package com.twilio.sdk.resources;

public enum UsageRecordSubResource {

    DAILY("Daily"),
    MONTHLY("Monthly"),
    YEARLY("Yearly"),
    ALL_TIME("AllTime"),
    TODAY("Today"),
    YESTERDAY("Yesterday"),
    THIS_MONTH("ThisMonth"),
    LAST_MONTH("LastMonth");

    private final String value;

    private UsageRecordSubResource(final String value) {
        this.value = value;
    }

    public String toString() {
        return value;
    }

}
