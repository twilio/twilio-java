package com.twilio.sdk.http;

public abstract class HttpClient {
    public abstract Response makeRequest(Request request);
}
