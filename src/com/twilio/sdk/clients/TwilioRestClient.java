package com.twilio.sdk.clients;

import com.twilio.sdk.factories.CallFactory;
import com.twilio.sdk.http.Request;
import com.twilio.sdk.http.Response;

public class TwilioRestClient {

    protected String accountSid;
    protected String authToken;

    public CallFactory calls;

    public TwilioRestClient(String accountSid, String authToken) {
        this.accountSid = accountSid;
        this.authToken = authToken;

        this.calls = new CallFactory(this);
    }

    public Response makeRequest(Request request) {
        return request.getResponse();
    }

}
