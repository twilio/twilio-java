package com.twilio.http.noauth;

import com.twilio.http.BaseNetworkHttpClient;
import com.twilio.http.IRequest;
import com.twilio.http.Request;
import com.twilio.http.Response;
import org.apache.hc.client5.http.config.RequestConfig;
import org.apache.hc.core5.http.io.support.ClassicRequestBuilder;

/**
 * No-Auth Network HTTP Client implementation using Apache HttpClient 5.
 * This client handles requests that don't require authentication.
 */
public class NoAuthNetworkHttpClient extends NoAuthHttpClient {

    private final NoAuthBaseClient baseClient;

    public NoAuthNetworkHttpClient() {
        this(DEFAULT_REQUEST_CONFIG);
    }

    public NoAuthNetworkHttpClient(final RequestConfig requestConfig) {
        baseClient = new NoAuthBaseClient(requestConfig);
    }

    @Override
    public Response makeRequest(NoAuthRequest request) {
        return baseClient.executeRequest(baseClient.buildHttpRequest(request));
    }

    // Inner class to avoid the anonymous class issue
    private static class NoAuthBaseClient extends BaseNetworkHttpClient {
        public NoAuthBaseClient(RequestConfig requestConfig) {
            super(requestConfig);
        }

        @Override
        protected void addAuthentication(ClassicRequestBuilder builder, IRequest request) {
            // No authentication needed for NoAuth clients
        }

        @Override
        public Response makeRequest(IRequest request) {
            // This shouldn't be called directly, but just in case
            return executeRequest(buildHttpRequest(request));
        }
    }
}
