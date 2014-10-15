package com.twilio.sdk.updaters;

import com.twilio.sdk.clients.TwilioRestClient;
import com.twilio.sdk.http.Request;
import com.twilio.sdk.http.Response;
import com.twilio.sdk.resources.Call;

public class CallUpdater extends Updater<Call> {
    private String friendlyName;

    public CallUpdater setFriendlyName(String friendlyName) {
        this.friendlyName = friendlyName;
        return this;
    }

    @Override
    public Call build(Call target, TwilioRestClient client) {
        Request request = new Request("POST", "/Accounts/{AccountSid}/Calls/" + target.getSid());
        Response response = client.request(request);

        if (response == null) {
            throw new RuntimeException("Call update failed: Unable to connect to server");
        } else if (response.getStatusCode() != 200) {
            throw new RuntimeException("Call update failed: [" + response.getStatusCode() + "] " + response.getContent());
        }

        return Call.fromJson(response.getContent());
    }
}