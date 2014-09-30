package com.twilio.sdk.factories;

import com.google.common.util.concurrent.ListeningExecutorService;
import com.twilio.sdk.clients.TwilioRestClient;
import com.twilio.sdk.http.HttpClient;
import com.twilio.sdk.http.Request;
import com.twilio.sdk.http.Response;

import java.util.concurrent.ExecutorService;

public class Factory {

    protected TwilioRestClient client;

    public Factory(TwilioRestClient client) {
        this.client = client;
    }

    public Response reliableRequest(Request request) {
        return this.client.reliableRequest(request);
    }

    public Response reliableRequest(Request request, int[] retryCodes, int retries, long delayMillis) {
        return this.client.reliableRequest(request, retryCodes, retries, delayMillis);
    }

    public Response makeRequest(Request request) {
        return this.client.makeRequest(request);
    }

    public ListeningExecutorService getExecutor() {
        return this.client.getExecutor();
    }
}
