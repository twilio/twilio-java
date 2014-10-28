package com.twilio.sdk.locators;

import com.twilio.sdk.clients.TwilioRestClient;
import com.twilio.sdk.http.Request;
import com.twilio.sdk.http.Response;
import com.twilio.sdk.resources.Call;

import java.net.URL;
import java.util.List;

public class CallLocator extends Locator<Call> {
    private String to;
    private String from;
    private URL url;


    @Override
    public List<Call> build(final TwilioRestClient client) {
        return null;
    }

    @Override
    public Call buildBySid(String sid, final TwilioRestClient client) {
        Request request = new Request("GET", "/Accounts/{AccountSid}/Calls/" + sid);
        Response response = client.request(request);

        if (response.getStatusCode() != 200) {
            throw new RuntimeException("Unable to find Call for Sid " + sid);
        }

        return Call.fromJson(response.getStream());
    }

    public CallLocator byTo(String to) {
        this.to = to;
        return this;
    }

    public CallLocator byFrom(String from) {
        this.from = from;
        return this;
    }

    public CallLocator byUrl(URL url) {
        this.url = url;
        return this;
    }
}