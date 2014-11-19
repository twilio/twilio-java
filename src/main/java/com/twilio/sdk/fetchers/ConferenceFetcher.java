package com.twilio.sdk.fetchers;

import com.twilio.sdk.clients.TwilioRestClient;
import com.twilio.sdk.exceptions.ApiException;
import com.twilio.sdk.http.HttpMethod;
import com.twilio.sdk.http.Request;
import com.twilio.sdk.http.Response;
import com.twilio.sdk.resources.Conference;

public class ConferenceFetcher extends Fetcher<Conference> {

    private final String sid;

    public ConferenceFetcher(final String sid) {
        this.sid = sid;
    }

    @Override
    public Conference execute(final TwilioRestClient client) {
        Request request = new Request(HttpMethod.GET, "/Conferences/" + sid + ".json");
        Response response = client.request(request);

        if (response.getStatusCode() != TwilioRestClient.HTTP_STATUS_CODE_OK) {
            throw new ApiException("Unable to find Conference for Sid " + sid);
        }

        return Conference.fromJson(response.getStream(), client.getObjectMapper());
    }
}