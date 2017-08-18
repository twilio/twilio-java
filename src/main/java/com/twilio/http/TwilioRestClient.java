package com.twilio.http;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.base.Predicate;

public class TwilioRestClient {

    public static final int HTTP_STATUS_CODE_CREATED = 201;
    public static final int HTTP_STATUS_CODE_NO_CONTENT = 204;
    public static final int HTTP_STATUS_CODE_OK = 200;
    public static final Predicate<Integer> SUCCESS = new Predicate<Integer>() {
        @Override
        public boolean apply(Integer i) {
            return i != null && i >= 200 && i < 300;
        }
    };

    private final ObjectMapper objectMapper;
    private final String username;
    private final String password;
    private final String accountSid;
    private final String region;
    private final HttpClient httpClient;

    private TwilioRestClient(Builder b) {
        this.username = b.username;
        this.password = b.password;
        this.accountSid = b.accountSid;
        this.region = b.region;
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
        return httpClient.reliableRequest(request);
    }

    public String getAccountSid() {
        return accountSid;
    }

    public String getRegion() {
        return region;
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
        private HttpClient httpClient;

        /**
         * Create a new Twilio Rest Client.
         *
         * @param username username to use
         * @param password password for the username
         */
        public Builder(String username, String password) {
            this.username = username;
            this.password = password;
            this.accountSid = username;
        }

        public Builder accountSid(String accountSid) {
            this.accountSid = accountSid;
            return this;
        }

        public Builder region(String region) {
            this.region = region;
            return this;
        }

        public Builder httpClient(HttpClient httpClient) {
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
