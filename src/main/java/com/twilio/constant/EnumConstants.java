package com.twilio.constant;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

public class EnumConstants {

    @Getter
    @RequiredArgsConstructor
    public enum ContentType {
        JSON("application/json"),
        SCIM("application/scim"),
        FORM_URLENCODED("application/x-www-form-urlencoded");

        private final String value;
    }
}
