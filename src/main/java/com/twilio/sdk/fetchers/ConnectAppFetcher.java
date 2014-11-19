package com.twilio.sdk.fetchers;

import com.twilio.sdk.clients.TwilioRestClient;
import com.twilio.sdk.exceptions.ApiException;
import com.twilio.sdk.http.HttpMethod;
import com.twilio.sdk.http.Request;
import com.twilio.sdk.http.Response;
import com.twilio.sdk.resources.ConnectApp;

public class ConnectAppFetcher extends Fetcher<ConnectApp> {

    private final String sid;

    public ConnectAppFetcher(final String sid) {
        this.sid = sid;
    }

    @Override
    public ConnectApp execute(final TwilioRestClient client) {
        Request request = new Request(HttpMethod.GET, "/ConnectApps/" + sid + ".json");
        Response response = client.request(request);

        if (response.getStatusCode() != TwilioRestClient.HTTP_STATUS_CODE_OK) {
            throw new ApiException("Unable to find ConnectApp for Sid " + sid);
        }

        return ConnectApp.fromJson(response.getStream(), client.getObjectMapper());
    }
}