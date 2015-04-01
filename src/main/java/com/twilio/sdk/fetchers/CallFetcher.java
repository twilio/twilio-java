package com.twilio.sdk.fetchers;

import com.twilio.sdk.clients.TwilioRestClient;
import com.twilio.sdk.exceptions.ApiException;
import com.twilio.sdk.http.HttpMethod;
import com.twilio.sdk.http.Request;
import com.twilio.sdk.http.Response;
import com.twilio.sdk.resources.Call;
import com.twilio.sdk.resources.RestException;

public class CallFetcher extends Fetcher<Call> {
    private final String sid;

    /**
     * Construct a new CallFetcher
     *
     * @param sid Call Sid that uniquely identifies the Call to fetch
     */
    public CallFetcher(final String sid) {
        this.sid = sid;
    }

    /**
     * Make the request to the Twilio API to perform the fetch
     *
     * @param client TwilioRestClient with which to make the request
     * @return Fetched Call
     */
    @Override
    public Call execute(final TwilioRestClient client) {
        Request request = new Request(
            HttpMethod.GET,
            "/2010-04-01/Accounts/{AccountSid}/Calls/" + sid + ".json",
            client.getAccountSid()
        );

        Response response = client.request(request);

        if (response.getStatusCode() != TwilioRestClient.HTTP_STATUS_CODE_OK) {
            RestException restException = RestException.fromJson(response.getStream(), client.getObjectMapper());
            throw new ApiException(
                restException.getMessage(),
                restException.getCode(),
                restException.getMoreInfo(),
                restException.getStatus(),
                null
            );
        }

        return Call.fromJson(response.getStream(), client.getObjectMapper());
    }
}
