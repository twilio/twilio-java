package com.twilio.sdk.readers;

import com.twilio.sdk.clients.TwilioRestClient;
import com.twilio.sdk.exceptions.ApiException;
import com.twilio.sdk.http.HttpMethod;
import com.twilio.sdk.http.Request;
import com.twilio.sdk.http.Response;
import com.twilio.sdk.resources.AuthorizedConnectApp;
import com.twilio.sdk.resources.Page;
import com.twilio.sdk.resources.ResourceSet;

public class AuthorizedConnectAppReader extends Reader<AuthorizedConnectApp> {

    @Override
    public ResourceSet<AuthorizedConnectApp> execute(final TwilioRestClient client) {
        Request request = new Request(HttpMethod.GET, "/AuthorizedConnectApps.json");

        Page<AuthorizedConnectApp> page = pageForRequest(client, request);

        return new ResourceSet<>(this, client, page);
    }

    @Override
    public Page<AuthorizedConnectApp> nextPage(final String nextPageUri, final TwilioRestClient client) {
        Request request = new Request(HttpMethod.GET, nextPageUri);
        return pageForRequest(client, request);
    }

    protected Page<AuthorizedConnectApp> pageForRequest(final TwilioRestClient client, final Request request) {
        Response response = client.request(request);

        if (response.getStatusCode() != TwilioRestClient.HTTP_STATUS_CODE_OK) {
            throw new ApiException("Unable to build page for AuthorizedConnectApp");
        }

        Page<AuthorizedConnectApp> result = new Page<>();
        result.deserialize("authorized_connect_apps", response.getContent(), AuthorizedConnectApp.class,
                           client.getObjectMapper());

        return result;
    }
}
