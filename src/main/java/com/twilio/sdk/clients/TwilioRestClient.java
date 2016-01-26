package com.twilio.sdk.clients;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.twilio.sdk.http.HttpClient;
import com.twilio.sdk.http.NetworkHttpClient;
import com.twilio.sdk.http.Request;
import com.twilio.sdk.http.Response;

public class TwilioRestClient {

    public static final int HTTP_STATUS_CODE_CREATED = 201;
    public static final int HTTP_STATUS_CODE_NO_CONTENT = 204;
    public static final int HTTP_STATUS_CODE_OK = 200;

    public enum Domains {
        API("api"),
        CONVERSATIONS("conversations"),
        LOOKUPS("lookups"),
        MONITOR("monitor"),
        PRICING("pricing"),
        TASKROUTER("taskrouter"),
        TRUNKING("trunking");

        private final String domain;

        Domains(final String domain) {
            this.domain = domain;
        }

        public String toString() {
            return domain;
        }
    }

    protected HttpClient httpClient;
    protected final ObjectMapper objectMapper;
    protected final String accountSid;
    protected final String authToken;

    public TwilioRestClient(final String accountSid, final String authToken) {
        this(accountSid, authToken, new NetworkHttpClient());
    }

    public TwilioRestClient(final String accountSid, final String authToken, final HttpClient httpClient) {
        this.accountSid = accountSid;
        this.authToken = authToken;
        this.httpClient = httpClient;
        this.objectMapper = new ObjectMapper();
    }

    public String getAccountSid() {
        return accountSid;
    }

    public Response request(final Request request) {
        request.setAuth(accountSid, authToken);

        return httpClient.reliableRequest(request);
    }

    public HttpClient getHttpClient() {
        return httpClient;
    }

    public ObjectMapper getObjectMapper() {
        return objectMapper;
    }

    public void setHttpClient(final HttpClient httpClient) {
        this.httpClient = httpClient;
    }
}
