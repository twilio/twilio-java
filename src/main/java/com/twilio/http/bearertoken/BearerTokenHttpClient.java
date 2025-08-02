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
    
    // Also support reliableRequest for BearerTokenRequest specifically
    public Response reliableRequest(final BearerTokenRequest request) {
        return super.reliableRequest((Request) request);
    }

    public Response reliableRequest(final BearerTokenRequest request, final int[] retryCodes, int retries,
                                    final long delayMillis) {
        return super.reliableRequest((Request) request, retryCodes, retries, delayMillis);
    }
}
