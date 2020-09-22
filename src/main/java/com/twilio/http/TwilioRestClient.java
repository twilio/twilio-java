package com.twilio.http;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.function.Predicate;

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

    private TwilioRestClient(Builder b) {
        this.username = b.username;
        this.password = b.password;
        this.accountSid = b.accountSid;
        this.region = b.region;
        this.edge = b.edge;
        this.httpClient = b.httpClient;
        this.objectMapper = new ObjectMapper();
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

        return httpClient.reliableRequest(request);
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

}
