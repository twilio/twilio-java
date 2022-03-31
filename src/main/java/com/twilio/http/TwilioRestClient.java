package com.twilio.http;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.function.Predicate;
import java.util.Map;
import java.util.List;

public class TwilioRestClient {

    public static final int HTTP_STATUS_CODE_CREATED = 201;
    public static final int HTTP_STATUS_CODE_NO_CONTENT = 204;
    public static final int HTTP_STATUS_CODE_OK = 200;
    public static final Predicate<Integer> SUCCESS = i -> i != null && i >= 200 && i < 400;

    private final ObjectMapper objectMapper;
    private final String username;
    private final String password;
    private final String accountSid;
    private final String region;
    private final String edge;
    private final HttpClient httpClient;
    private static final Logger logger = LoggerFactory.getLogger(TwilioRestClient.class);

    protected TwilioRestClient(Builder b) {
        this.username = b.username;
        this.password = b.password;
        this.accountSid = b.accountSid;
        this.region = b.region;
        this.edge = b.edge;
        this.httpClient = b.httpClient;
        this.objectMapper = new ObjectMapper();

        // This module configures the ObjectMapper to use
        // public API methods for manipulating java.time.*
        // classes. The alternative is to use reflection which
        // generates warnings from the module system on Java 9+
        objectMapper.registerModule(new JavaTimeModule());
    }

    /**
     * Make a request to Twilio.
     *
     * @param request request to make
     * @return Response object
     */
    public Response request(final Request request) {
        request.setAuth(username, password);

        if (region != null)
            request.setRegion(region);
        if (edge != null)
            request.setEdge(edge);

        logRequest(request);
        Response response = httpClient.reliableRequest(request);

        if (logger.isDebugEnabled()) {
            logger.debug("status code: {}", response.getStatusCode());
            org.apache.http.Header[] responseHeaders = response.getHeaders();
            logger.debug("response headers:");
            for (int i = 0; i < responseHeaders.length; i++) {
                logger.debug("responseHeader: {}", responseHeaders[i]);
            }
        }

        return response;
    }

    public String getAccountSid() {
        return accountSid;
    }

    public String getRegion() {
        return region;
    }

    public String getEdge() {
        return edge;
    }

    public ObjectMapper getObjectMapper() {
        return objectMapper;
    }

    public HttpClient getHttpClient() {
        return httpClient;
    }

    public static class Builder {
        private String username;
        private String password;
        private String accountSid;
        private String region;
        private String edge;
        private HttpClient httpClient;

        /**
         * Create a new Twilio Rest Client.
         *
         * @param username username to use
         * @param password password for the username
         */
        public Builder(final String username, final String password) {
            this.username = username;
            this.password = password;
            this.accountSid = username;
        }

        public Builder accountSid(final String accountSid) {
            this.accountSid = accountSid;
            return this;
        }

        public Builder region(final String region) {
            this.region = region;
            return this;
        }

        public Builder edge(final String edge) {
            this.edge = edge;
            return this;
        }

        public Builder httpClient(final HttpClient httpClient) {
            this.httpClient = httpClient;
            return this;
        }

        /**
         * Build new TwilioRestClient.
         *
         * @return TwilioRestClient instance
         */
        public TwilioRestClient build() {
            if (this.httpClient == null) {
                this.httpClient = new NetworkHttpClient();
            }
            return new TwilioRestClient(this);
        }
    }

    /**
     * Logging debug information about HTTP request.
     */
    public void logRequest(final Request request) {
        if (logger.isDebugEnabled()) {
            logger.debug("-- BEGIN Twilio API Request --");
            logger.debug("request method: " + request.getMethod());
            logger.debug("request URL: " + request.constructURL().toString());
            final Map<String, List<String>> queryParams = request.getQueryParams();
            final Map<String, List<String>> headerParams = request.getHeaderParams();

            if (queryParams != null && !queryParams.isEmpty()) {
                logger.debug("query parameters: " + queryParams);
            }

            if (headerParams != null && !headerParams.isEmpty()) {
                logger.debug("header parameters: ");
                for (String key : headerParams.keySet()) {
                    if (!key.toLowerCase().contains("authorization")) {
                        logger.debug(key + ": " + headerParams.get(key));
                    }
                }
            }

            logger.debug("-- END Twilio API Request --");
        }
    }

}
