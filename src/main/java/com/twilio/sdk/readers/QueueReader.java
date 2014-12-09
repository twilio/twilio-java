package com.twilio.sdk.readers;

import com.twilio.sdk.clients.TwilioRestClient;
import com.twilio.sdk.exceptions.ApiException;
import com.twilio.sdk.http.HttpMethod;
import com.twilio.sdk.http.Request;
import com.twilio.sdk.http.Response;
import com.twilio.sdk.resources.Page;
import com.twilio.sdk.resources.Queue;
import com.twilio.sdk.resources.ResourceSet;
import com.twilio.sdk.resources.RestException;

public class QueueReader extends Reader<Queue> {

    @Override
    public ResourceSet<Queue> execute(final TwilioRestClient client) {
        Request request = new Request(HttpMethod.GET, "/Accounts/{AccountSid}/Queues.json", client.getAccountSid());

        Page<Queue> page = pageForRequest(client, request);

        return new ResourceSet<>(this, client, page);
    }

    @Override
    public Page<Queue> nextPage(final String nextPageUri, final TwilioRestClient client) {
        Request request = new Request(HttpMethod.GET, nextPageUri, client.getAccountSid());
        return pageForRequest(client, request);
    }

    protected Page<Queue> pageForRequest(final TwilioRestClient client, final Request request) {
        Response response = client.request(request);

        if (response.getStatusCode() != TwilioRestClient.HTTP_STATUS_CODE_OK) {
            RestException restException = RestException.fromJson(response.getStream(), client.getObjectMapper());
            throw new ApiException(restException.getMessage(), restException.getCode(), restException.getMoreInfo(),
                                   restException.getStatus(), null);
        }

        Page<Queue> result = new Page<>();
        result.deserialize("queues", response.getContent(), Queue.class, client.getObjectMapper());

        return result;
    }
}
