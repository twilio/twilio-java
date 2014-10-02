package com.twilio.sdk.creators;

import com.twilio.sdk.Twilio;
import com.twilio.sdk.http.Request;
import com.twilio.sdk.http.Response;
import com.twilio.sdk.resources.Call;

import java.net.URL;

public class CallCreator extends Creator<Call> {
    protected String to;
    protected String from;
    protected URL url;

    /**
     * @param to The phone number to dial
     * @param from The twilio number to originate the call from
     * @param url The url to fetch twiml from
     */
    public CallCreator(String to, String from, URL url) {
        this.to = to;
        this.from = from;
        this.url = url;
    }

    @Override
    public Call build() {
        Request request = new Request("POST", "/Accounts/{AccountSid}/Calls");
        Response response = Twilio.getRestClient().request(request);

        if (response == null) {
            throw new RuntimeException("Call creation failed: Unable to connect to server");
        } else if (response.getStatusCode() != 201) {
            throw new RuntimeException("Call creation failed: [" + response.getStatusCode() + "] " + response.getContent());
        }

        return Call.fromJson(response.getContent());
    }
}