package com.twilio.sdk.http;

public enum HttpMethod {
    GET ("GET"),
    POST ("POST"),
    PUT ("PUT"),
    DELETE ("DELETE"),
    HEAD ("HEAD"),
    OPTIONS ("OPTIONS");

    private final String method;

    HttpMethod(String method) {
        this.method = method;
    }

    public String toString() {
        return this.method;
    }
}
