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

    @Getter
    @RequiredArgsConstructor
    public enum AuthType {
        NO_AUTH("noauth"),
        BASIC("basic"),
        TOKEN("token"),
        API_KEY("api_key"),
        CLIENT_CREDENTIALS("client_credentials");

        private final String value;
    }

    @Getter
    @RequiredArgsConstructor
    public enum ParameterType {
        QUERY("Query"),
        HEADER("Header"),
        URLENCODED("Urlencoded"),
        JSON("Json");

        private final String value;
    }
}
