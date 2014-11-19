package com.twilio.sdk.readers;

import com.twilio.sdk.clients.TwilioRestClient;
import com.twilio.sdk.exceptions.ApiException;
import com.twilio.sdk.http.HttpMethod;
import com.twilio.sdk.http.Request;
import com.twilio.sdk.http.Response;
import com.twilio.sdk.resources.ConnectApp;
import com.twilio.sdk.resources.Page;
import com.twilio.sdk.resources.ResourceSet;

public class ConnectAppReader extends Reader<ConnectApp> {

    @Override
    public ResourceSet<ConnectApp> execute(final TwilioRestClient client) {
        Request request = new Request(HttpMethod.GET, "/ConnectApps.json");

        Page<ConnectApp> page = pageForRequest(client, request);

        return new ResourceSet<>(this, client, page);
    }

    @Override
    public Page<ConnectApp> nextPage(final String nextPageUri, final TwilioRestClient client) {
        Request request = new Request(HttpMethod.GET, nextPageUri);
        return pageForRequest(client, request);
    }

    protected Page<ConnectApp> pageForRequest(final TwilioRestClient client, final Request request) {
        Response response = client.request(request);

        if (response.getStatusCode() != TwilioRestClient.HTTP_STATUS_CODE_OK) {
            throw new ApiException("Unable to build page for ConnectApp");
        }

        Page<ConnectApp> result = new Page<>();
        result.deserialize("connect_apps", response.getContent(), ConnectApp.class, client.getObjectMapper());

        return result;
    }
}
