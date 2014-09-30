package com.twilio.sdk.clients;

import com.google.common.util.concurrent.ListeningExecutorService;
import com.google.common.util.concurrent.MoreExecutors;
import com.twilio.sdk.factories.CallFactory;
import com.twilio.sdk.factories.MessageFactory;
import com.twilio.sdk.http.*;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.Executors;

public class TwilioRestClient {

    protected HttpClient httpClient;
    protected String accountSid;
    protected String authToken;
    protected ListeningExecutorService executor;

    public CallFactory calls;
    public MessageFactory messages;

    public static TwilioRestClient mock() {
        return new TwilioRestClient("AC123", "AUTH", new MockHttpClient());
    }

    public static TwilioRestClient mock(ConsumableResponse... responses) {
        TwilioRestClient result = mock();
        for (ConsumableResponse response : responses) {
            ((MockHttpClient)result.getHttpClient()).addResponse(response);
        }
        return result;
    }

    public static TwilioRestClient mock(long delayLowerMillis, long delayUpperMillis) {
        TwilioRestClient result = mock();
        ((MockHttpClient)result.getHttpClient()).setDelay(delayLowerMillis, delayUpperMillis);
        return result;
    }

    public TwilioRestClient(String accountSid, String authToken) {
        this(accountSid, authToken, new NetworkHttpClient());
    }

    public TwilioRestClient(String accountSid, String authToken, HttpClient httpClient) {
        this(accountSid, authToken, httpClient, MoreExecutors.listeningDecorator(Executors.newCachedThreadPool()));
    }

    public TwilioRestClient(String accountSid, String authToken, ListeningExecutorService executorService) {
        this(accountSid, authToken, new NetworkHttpClient(), executorService);
    }

    public TwilioRestClient(String accountSid, String authToken, HttpClient httpClient, ListeningExecutorService executorService) {
        this.accountSid = accountSid;
        this.authToken = authToken;
        this.executor = executorService;
        this.httpClient = httpClient;

        this.calls = new CallFactory(this);
        this.messages = new MessageFactory(this);
    }



    public Response makeRequest(Request request) {
        return this.httpClient.reliableRequest(request);
    }

    public ListeningExecutorService getExecutor() {
        return this.executor;
    }

    public void setExecutor(ListeningExecutorService executor) {
        this.executor = executor;
    }

    public HttpClient getHttpClient() {
        return httpClient;
    }

    public void setHttpClient(HttpClient httpClient) {
        this.httpClient = httpClient;
    }
}
