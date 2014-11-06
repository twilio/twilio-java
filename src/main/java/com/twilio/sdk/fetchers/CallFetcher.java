package com.twilio.sdk.fetchers;

import com.twilio.sdk.clients.TwilioRestClient;
import com.twilio.sdk.http.HttpMethod;
import com.twilio.sdk.http.Request;
import com.twilio.sdk.http.Response;
import com.twilio.sdk.resources.Call;

public class CallFetcher extends Fetcher<Call> {

    private final String sid;

    public CallFetcher(final String sid) {
        this.sid = sid;
    }

    @Override
    public Call execute(final TwilioRestClient client) {
        Request request = new Request(HttpMethod.GET, "/Accounts/{AccountSid}/Calls/" + sid + ".json");
        Response response = client.request(request);

        if (response.getStatusCode() != 200) {
            throw new RuntimeException("Unable to find Call for Sid " + sid);
        }

        return Call.fromJson(response.getStream());
    }
}
