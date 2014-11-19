package com.twilio.sdk.fetchers;

import com.twilio.sdk.clients.TwilioRestClient;
import com.twilio.sdk.exceptions.ApiException;
import com.twilio.sdk.http.HttpMethod;
import com.twilio.sdk.http.Request;
import com.twilio.sdk.http.Response;
import com.twilio.sdk.resources.Recording;

public class RecordingFetcher extends Fetcher<Recording> {

    private final String sid;

    public RecordingFetcher(final String sid) {
        this.sid = sid;
    }

    @Override
    public Recording execute(final TwilioRestClient client) {
        Request request = new Request(HttpMethod.GET, "/Recordings/" + sid + ".json");
        Response response = client.request(request);

        if (response.getStatusCode() != TwilioRestClient.HTTP_STATUS_CODE_OK) {
            throw new ApiException("Unable to find Recording for Sid " + sid);
        }

        return Recording.fromJson(response.getStream(), client.getObjectMapper());
    }
}