package com.twilio.http.noauth;

import com.twilio.http.HttpClient;
import com.twilio.http.IRequest;
import com.twilio.http.Request;
import com.twilio.http.Response;

public abstract class NoAuthHttpClient extends HttpClient {

    @Override
    public Response makeRequest(final IRequest request) {
        if (request instanceof NoAuthRequest) {
            return makeRequest((NoAuthRequest) request);
        }
        throw new IllegalArgumentException("NoAuthHttpClient requires NoAuthRequest");
    }

    public abstract Response makeRequest(NoAuthRequest request);
    
    // Also support reliableRequest for NoAuthRequest specifically
    public Response reliableRequest(final NoAuthRequest request) {
        return super.reliableRequest((IRequest) request);
    }

    public Response reliableRequest(final NoAuthRequest request, final int[] retryCodes, int retries,
                                    final long delayMillis) {
        return super.reliableRequest((IRequest) request, retryCodes, retries, delayMillis);
    }
}
