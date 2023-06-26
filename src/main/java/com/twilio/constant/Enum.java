package com.twilio.constant;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

public class Enum {

    @Getter
    @RequiredArgsConstructor
    public enum ContentType {
        JSON("application/json"),
        FORM_URLENCODED("application/x-www-form-urlencoded");

        private final String value;
    }
}
