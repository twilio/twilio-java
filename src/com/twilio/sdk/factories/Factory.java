package com.twilio.sdk.factories;

import com.twilio.sdk.clients.TwilioRestClient;
import com.twilio.sdk.http.Request;
import com.twilio.sdk.http.Response;

public class Factory {

    protected TwilioRestClient client;

    public Factory(TwilioRestClient client) {
        this.client = client;
    }

    Response makeRequest(Request request) {
        return this.client.makeRequest(request);
    }

}
