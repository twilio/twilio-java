package com.twilio.sdk.resources;

import com.fasterxml.jackson.annotation.JsonCreator;

public enum AddressRequirement {
    NONE("none"), ANY("any"), LOCAL("local"), FOREIGN("foreign");
    private final String addressRequirement;

    AddressRequirement(final String addressRequirement) {
        this.addressRequirement = addressRequirement;
    }

    public String toString() {
        return addressRequirement;
    }

    @JsonCreator
    public static AddressRequirement forValue(final String value) {
        String munged = value.replace("-", "_")
                             .toUpperCase();
        return AddressRequirement.valueOf(munged);
    }
}
