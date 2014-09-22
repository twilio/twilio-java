package com.twilio.sdk.clients;

import com.twilio.sdk.factories.CallFactory;
import com.twilio.sdk.factories.MessageFactory;
import com.twilio.sdk.http.Request;
import com.twilio.sdk.http.Response;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class TwilioRestClient {

    protected String accountSid;
    protected String authToken;
    protected ExecutorService executor;

    public CallFactory calls;
    public MessageFactory messages;

    public TwilioRestClient(String accountSid, String authToken) {
        this.accountSid = accountSid;
        this.authToken = authToken;
        this.executor = Executors.newCachedThreadPool();

        this.calls = new CallFactory(this);
        this.messages = new MessageFactory(this);
    }

    public Response makeRequest(Request request) {
        return request.getResponse();
    }

    public ExecutorService getExecutor() {
        return this.executor;
    }
}
