package com.twilio.sdk.resources;

public enum PhoneNumberType {

    LOCAL("Local"),
    MOBILE("Mobile"),
    TOLL_FREE("TollFree");

    private final String type;

    private PhoneNumberType(final String type) {
        this.type = type;
    }

    public String toString() {
        return type;
    }
}
