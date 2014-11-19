package com.twilio.sdk.fetchers;

import com.twilio.sdk.clients.TwilioRestClient;
import com.twilio.sdk.exceptions.ApiException;
import com.twilio.sdk.http.HttpMethod;
import com.twilio.sdk.http.Request;
import com.twilio.sdk.http.Response;
import com.twilio.sdk.resources.AuthorizedConnectApp;

public class AuthorizedConnectAppFetcher extends Fetcher<AuthorizedConnectApp> {

    private final String sid;

    public AuthorizedConnectAppFetcher(final String sid) {
        this.sid = sid;
    }

    @Override
    public AuthorizedConnectApp execute(final TwilioRestClient client) {
        Request request = new Request(HttpMethod.GET, "/AuthorizedConnectApps/" + sid + ".json");
        Response response = client.request(request);

        if (response.getStatusCode() != TwilioRestClient.HTTP_STATUS_CODE_OK) {
            throw new ApiException("Unable to find AuthorizedConnectApp for Sid " + sid);
        }

        return AuthorizedConnectApp.fromJson(response.getStream(), client.getObjectMapper());
    }
}