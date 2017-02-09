package com.twilio.http;

import com.fasterxml.jackson.annotation.JsonCreator;

public enum HttpMethod {
    GET("GET"),
    POST("POST"),
    PUT("PUT"),
    DELETE("DELETE"),
    HEAD("HEAD"),
    OPTIONS("OPTIONS");

    private final String method;

    HttpMethod(final String method) {
        this.method = method;
    }

    public String toString() {
        return method;
    }

    @JsonCreator
    public static HttpMethod forValue(final String value) {
        return HttpMethod.valueOf(value.toUpperCase());
    }
}
