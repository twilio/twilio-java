package com.twilio.constant;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

public class EnumConstants {

    @Getter
    @RequiredArgsConstructor
    public enum ContentType {
        JSON("application/json"),
        FORM_URLENCODED("application/x-www-form-urlencoded"),
        MULTIPART_FORM_DATA("multipart/form-data");

        private final String value;
    }
}
