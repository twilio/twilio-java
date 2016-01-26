package com.twilio.sdk.fetchers.api;

import com.twilio.sdk.clients.TwilioRestClient;
import com.twilio.sdk.exceptions.ApiConnectionException;
import com.twilio.sdk.exceptions.ApiException;
import com.twilio.sdk.fetchers.Fetcher;
import com.twilio.sdk.http.HttpMethod;
import com.twilio.sdk.http.Request;
import com.twilio.sdk.http.Response;
import com.twilio.sdk.resources.RestException;
import com.twilio.sdk.resources.api.AuthorizedConnectApp;

public class AuthorizedConnectAppFetcher extends Fetcher<AuthorizedConnectApp> {
    private final String accountSid;
    private final String connectAppSid;

    /**
     * Construct a new AuthorizedConnectAppFetcher
     * 
     * @param accountSid The account_sid
     * @param connectAppSid The connect_app_sid
     */
    public AuthorizedConnectAppFetcher(final String accountSid, final String connectAppSid) {
        this.accountSid = accountSid;
        this.connectAppSid = connectAppSid;
    }

    /**
     * Make the request to the Twilio API to perform the fetch
     * 
     * @param client TwilioRestClient with which to make the request
     * @return Fetched AuthorizedConnectApp
     */
    @Override
    public AuthorizedConnectApp execute(final TwilioRestClient client) {
        Request request = new Request(
            HttpMethod.GET,
            "/2010-04-01/Accounts/" + this.accountSid + "/AuthorizedConnectApps/" + this.connectAppSid + ".json",
            client.getAccountSid()
        );
        
        Response response = client.request(request);
        
        if (response == null) {
            throw new ApiConnectionException("AuthorizedConnectApp fetch failed: Unable to connect to server");
        } else if (response.getStatusCode() != TwilioRestClient.HTTP_STATUS_CODE_OK) {
            RestException restException = RestException.fromJson(response.getStream(), client.getObjectMapper());
            throw new ApiException(
                restException.getMessage(),
                restException.getCode(),
                restException.getMoreInfo(),
                restException.getStatus(),
                null
            );
        }
        
        return AuthorizedConnectApp.fromJson(response.getStream(), client.getObjectMapper());
    }
}