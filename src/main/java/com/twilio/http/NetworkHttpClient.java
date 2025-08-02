package com.twilio.http;

import org.apache.hc.client5.http.config.RequestConfig;
import org.apache.hc.client5.http.impl.classic.HttpClientBuilder;
import org.apache.hc.core5.http.io.support.ClassicRequestBuilder;
import org.apache.hc.core5.http.HttpHeaders;

/**
 * Network HTTP Client implementation using Apache HttpClient 5.
 * This client handles standard authenticated requests.
 */
public class NetworkHttpClient extends BaseNetworkHttpClient {

    /**
     * Create a new HTTP Client.
     */
    public NetworkHttpClient() {
        super();
    }

    /**
     * Create a new HTTP Client with a custom request config.
     *
     * @param requestConfig a RequestConfig.
     */
    public NetworkHttpClient(final RequestConfig requestConfig) {
        super(requestConfig);
    }

    /**
     * Create a new HTTP Client using custom configuration.
     * @param clientBuilder an HttpClientBuilder.
     */
    public NetworkHttpClient(HttpClientBuilder clientBuilder) {
        super(clientBuilder);
    }

    /**
     * Make a request.
     *
     * @param request request to make
     * @return Response of the HTTP request
     */
    public Response makeRequest(final Request request) {
        return executeRequest(buildHttpRequest(request));
    }

    /**
     * Add authentication to the request.
     */
    @Override
    protected void addAuthentication(ClassicRequestBuilder builder, IRequest request) {
        if (request instanceof Request) {
            Request req = (Request) request;
            if (req.requiresAuthentication()) {
                builder.addHeader(HttpHeaders.AUTHORIZATION, req.getAuthString());
            }
        }
    }
}
