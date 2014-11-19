package com.twilio.sdk.fetchers;

import com.twilio.sdk.clients.TwilioRestClient;
import com.twilio.sdk.exceptions.ApiException;
import com.twilio.sdk.http.HttpMethod;
import com.twilio.sdk.http.Request;
import com.twilio.sdk.http.Response;
import com.twilio.sdk.resources.Message;

public class MessageFetcher extends Fetcher<Message> {

    private final String sid;

    public MessageFetcher(final String sid) {
        this.sid = sid;
    }

    @Override
    public Message execute(final TwilioRestClient client) {
        Request request = new Request(HttpMethod.GET, "/Messages/" + sid + ".json");
        Response response = client.request(request);

        if (response.getStatusCode() != TwilioRestClient.HTTP_STATUS_CODE_OK) {
            throw new ApiException("Unable to find Message for Sid " + sid);
        }

        return Message.fromJson(response.getStream(), client.getObjectMapper());
    }
}