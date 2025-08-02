package com.twilio.http.bearertoken;

import com.twilio.http.HttpClient;
import com.twilio.http.Request;
import com.twilio.http.Response;

public abstract class BearerTokenHttpClient extends HttpClient {

    @Override
    public Response makeRequest(final Request request) {
        if (request instanceof BearerTokenRequest) {
            return makeRequest((BearerTokenRequest) request);
        }
        throw new IllegalArgumentException("BearerTokenHttpClient requires BearerTokenRequest");
    }

    public abstract Response makeRequest(final BearerTokenRequest request);
}
