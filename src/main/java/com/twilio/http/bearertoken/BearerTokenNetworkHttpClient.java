package com.twilio.http.bearertoken;

import com.twilio.Twilio;
import com.twilio.http.BaseNetworkHttpClient;
import com.twilio.http.HttpUtility;
import com.twilio.http.IRequest;
import com.twilio.http.Response;
import org.apache.hc.client5.http.config.RequestConfig;
import org.apache.hc.client5.http.impl.classic.HttpClientBuilder;
import org.apache.hc.core5.http.HttpHeaders;
import org.apache.hc.core5.http.io.support.ClassicRequestBuilder;
import org.apache.hc.core5.http.message.BasicHeader;

import java.util.Arrays;
import java.util.Collection;

/**
 * Bearer Token Network HTTP Client implementation using Apache HttpClient 5.
 * This client handles bearer token authenticated requests with slightly different headers.
 */
public class BearerTokenNetworkHttpClient extends BearerTokenHttpClient {

    private final BaseNetworkHttpClient baseClient;

    /**
     * Create a new HTTP Client.
     */
    public BearerTokenNetworkHttpClient() {
        this(DEFAULT_REQUEST_CONFIG);
    }

    /**
     * Create a new HTTP Client with a custom request config.
     *
     * @param requestConfig a RequestConfig.
     */
    public BearerTokenNetworkHttpClient(final RequestConfig requestConfig) {
        baseClient = new BaseNetworkHttpClient(requestConfig) {
            @Override
            protected Collection<BasicHeader> getDefaultHeaders() {
                return Arrays.asList(
                    new BasicHeader("X-Twilio-Client", "java-" + Twilio.VERSION),
                    // Note: Orgs API has scim or json support, so we don't set Accept to application/json
                    new BasicHeader(HttpHeaders.ACCEPT_ENCODING, "utf-8")
                );
            }

            @Override
            protected void addAuthentication(ClassicRequestBuilder builder, IRequest request) {
                if (request instanceof BearerTokenRequest) {
                    BearerTokenRequest req = (BearerTokenRequest) request;
                    if (req.requiresAuthentication()) {
                        builder.addHeader(HttpHeaders.AUTHORIZATION, req.getAuthString());
                    }
                }
            }
        };
    }

    /**
     * Create a new HTTP Client using custom configuration.
     * @param clientBuilder an HttpClientBuilder.
     */
    public BearerTokenNetworkHttpClient(HttpClientBuilder clientBuilder) {
        baseClient = new BaseNetworkHttpClient(clientBuilder) {
            @Override
            protected Collection<BasicHeader> getDefaultHeaders() {
                return Arrays.asList(
                    new BasicHeader("X-Twilio-Client", "java-" + Twilio.VERSION),
                    // Note: Orgs API has scim or json support, so we don't set Accept to application/json
                    new BasicHeader(HttpHeaders.ACCEPT_ENCODING, "utf-8")
                );
            }

            @Override
            protected void addAuthentication(ClassicRequestBuilder builder, IRequest request) {
                if (request instanceof BearerTokenRequest) {
                    BearerTokenRequest req = (BearerTokenRequest) request;
                    if (req.requiresAuthentication()) {
                        builder.addHeader(HttpHeaders.AUTHORIZATION, req.getAuthString());
                    }
                }
            }
        };
    }

    /**
     * Make a request.
     *
     * @param request request to make
     * @return Response of the HTTP request
     */
    public Response makeRequest(final BearerTokenRequest request) {
        return baseClient.executeRequest(baseClient.buildHttpRequest(request));
    }
}
