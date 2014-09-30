package com.twilio.sdk.http;

public class Response {
    private String content;
    private int statusCode;

    public Response(String content, int statusCode) {
        this.content = content;
        this.statusCode = statusCode;
    }

    public String getContent() {
        return content;
    }

    public int getStatusCode() {
        return statusCode;
    }
}
