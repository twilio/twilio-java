package com.twilio.sdk.readers;

import com.twilio.sdk.clients.TwilioRestClient;
import com.twilio.sdk.exceptions.ApiException;
import com.twilio.sdk.http.HttpMethod;
import com.twilio.sdk.http.Request;
import com.twilio.sdk.http.Response;
import com.twilio.sdk.resources.Page;
import com.twilio.sdk.resources.Queue;
import com.twilio.sdk.resources.ResourceSet;

public class QueueReader extends Reader<Queue> {

    @Override
    public ResourceSet<Queue> execute(final TwilioRestClient client) {
        Request request = new Request(HttpMethod.GET, "/Queues.json");

        Page<Queue> page = pageForRequest(client, request);

        return new ResourceSet<>(this, client, page);
    }

    @Override
    public Page<Queue> nextPage(final String nextPageUri, final TwilioRestClient client) {
        Request request = new Request(HttpMethod.GET, nextPageUri);
        return pageForRequest(client, request);
    }

    protected Page<Queue> pageForRequest(final TwilioRestClient client, final Request request) {
        Response response = client.request(request);

        if (response.getStatusCode() != TwilioRestClient.HTTP_STATUS_CODE_OK) {
            throw new ApiException("Unable to build page for Queue");
        }

        Page<Queue> result = new Page<>();
        result.deserialize("queues", response.getContent(), Queue.class, client.getObjectMapper());

        return result;
    }
}
