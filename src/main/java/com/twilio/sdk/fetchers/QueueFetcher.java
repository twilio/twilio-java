package com.twilio.sdk.fetchers;

import com.twilio.sdk.clients.TwilioRestClient;
import com.twilio.sdk.exceptions.ApiException;
import com.twilio.sdk.http.HttpMethod;
import com.twilio.sdk.http.Request;
import com.twilio.sdk.http.Response;
import com.twilio.sdk.resources.Queue;

public class QueueFetcher extends Fetcher<Queue> {

    private final String sid;

    public QueueFetcher(final String sid) {
        this.sid = sid;
    }

    @Override
    public Queue execute(final TwilioRestClient client) {
        Request request = new Request(HttpMethod.GET, "/Queues/" + sid + ".json");
        Response response = client.request(request);

        if (response.getStatusCode() != TwilioRestClient.HTTP_STATUS_CODE_OK) {
            throw new ApiException("Unable to find Queue for Sid " + sid);
        }

        return Queue.fromJson(response.getStream(), client.getObjectMapper());
    }
}