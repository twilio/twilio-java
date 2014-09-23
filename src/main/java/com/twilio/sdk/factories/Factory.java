package com.twilio.sdk.factories;

import com.google.common.util.concurrent.ListeningExecutorService;
import com.twilio.sdk.clients.TwilioRestClient;
import com.twilio.sdk.http.Request;
import com.twilio.sdk.http.Response;

import java.util.concurrent.ExecutorService;

public class Factory {

    protected TwilioRestClient client;

    public Factory(TwilioRestClient client) {
        this.client = client;
    }

    Response makeRequest(Request request) {
        return this.client.makeRequest(request);
    }

    public ListeningExecutorService getExecutor() {
        return this.client.getExecutor();
    }
}
