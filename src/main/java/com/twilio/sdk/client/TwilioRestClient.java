package com.twilio.sdk.client;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.base.Predicate;
import com.twilio.sdk.http.HttpClient;
import com.twilio.sdk.http.NetworkHttpClient;
import com.twilio.sdk.http.Request;
import com.twilio.sdk.http.Response;

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


    public enum Domains {
        API("api"),
        CONVERSATIONS("conversations"),
        LOOKUPS("lookups"),
        MONITOR("monitor"),
        PRICING("pricing"),
        TASKROUTER("taskrouter"),
        TRUNKING("trunking"),
        IPMESSAGING("ip-messaging"),
        NOTIFICATIONS("notifications"),
        PREVIEW("preview");

        private final String domain;

        Domains(final String domain) {
            this.domain = domain;
        }

        public String toString() {
            return domain;
        }
    }


    private HttpClient httpClient;
    private final ObjectMapper objectMapper;
    private final String username;
    private final String password;
    private final String accountSid;

    /**
     * Create a Rest Client.
     *
     * @param username Twilio username
     * @param password Twilio password
     */
    public TwilioRestClient(final String username, final String password) {
        this(username, password, username, new NetworkHttpClient());
    }

    /**
     * Create a Rest Client using a custom HTTP Client.
     *
     * @param username Twilio username
     * @param password Twilio password
     * @param accountSid Twilio account sid
     */
    public TwilioRestClient(final String username, final String password, final String accountSid) {
        this(username, password, accountSid, new NetworkHttpClient());
    }

    /**
     * Create a Rest Client using a custom HTTP Client.
     *
     * @param username Twilio username
     * @param password Twilio password
     * @param accountSid Twilio account sid
     * @param httpClient Custom HTTP Client
     */
    public TwilioRestClient(
        final String username,
        final String password,
        final String accountSid,
        final HttpClient httpClient
    ) {
        this.username = username;
        this.password = password;
        this.accountSid = accountSid;
        this.httpClient = httpClient;
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

    public ObjectMapper getObjectMapper() {
        return objectMapper;
    }

    public HttpClient getHttpClient() {
        return httpClient;
    }

    public void setHttpClient(final HttpClient httpClient) {
        this.httpClient = httpClient;
    }
}
