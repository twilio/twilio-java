package com.twilio.sdk.fetchers;

import com.twilio.sdk.clients.TwilioRestClient;
import com.twilio.sdk.exceptions.ApiException;
import com.twilio.sdk.http.HttpMethod;
import com.twilio.sdk.http.Request;
import com.twilio.sdk.http.Response;
import com.twilio.sdk.resources.ShortCode;

public class ShortCodeFetcher extends Fetcher<ShortCode> {

    private final String sid;

    public ShortCodeFetcher(final String sid) {
        this.sid = sid;
    }

    @Override
    public ShortCode execute(final TwilioRestClient client) {
        Request request = new Request(HttpMethod.GET, "/ShortCodes/" + sid + ".json");
        Response response = client.request(request);

        if (response.getStatusCode() != TwilioRestClient.HTTP_STATUS_CODE_OK) {
            throw new ApiException("Unable to find ShortCode for Sid " + sid);
        }

        return ShortCode.fromJson(response.getStream(), client.getObjectMapper());
    }
}