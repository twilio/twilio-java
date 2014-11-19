package com.twilio.sdk.deleters;

import com.twilio.sdk.clients.TwilioRestClient;
import com.twilio.sdk.exceptions.ApiConnectionException;
import com.twilio.sdk.exceptions.ApiException;
import com.twilio.sdk.http.HttpMethod;
import com.twilio.sdk.http.Request;
import com.twilio.sdk.http.Response;
import com.twilio.sdk.resources.Call;

public class CallDeleter extends Deleter<Call> {

    private final String sid;

    public CallDeleter(final String sid) {
        this.sid = sid;
    }

    public CallDeleter(final Call call) {
        this(call.getSid());
    }

    @Override
    public void execute(final TwilioRestClient client) {
        Request request = new Request(HttpMethod.DELETE, "/Accounts/{AccountSid}/Calls/" + sid + ".json");
        Response response = client.request(request);

        if (response == null) {
            throw new ApiConnectionException("Call delete failed: Unable to connect to server");
        } else if (response.getStatusCode() != TwilioRestClient.HTTP_STATUS_CODE_NO_CONTENT) {
            throw new ApiException("Call delete failed: [" + response.getStatusCode() + "] " + response.getContent());
        }
    }
}
