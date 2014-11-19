package com.twilio.sdk.fetchers;

import com.twilio.sdk.clients.TwilioRestClient;
import com.twilio.sdk.exceptions.ApiException;
import com.twilio.sdk.http.HttpMethod;
import com.twilio.sdk.http.Request;
import com.twilio.sdk.http.Response;
import com.twilio.sdk.resources.IncomingPhoneNumber;

public class IncomingPhoneNumberFetcher extends Fetcher<IncomingPhoneNumber> {

    private final String sid;

    public IncomingPhoneNumberFetcher(final String sid) {
        this.sid = sid;
    }

    @Override
    public IncomingPhoneNumber execute(final TwilioRestClient client) {
        Request request = new Request(HttpMethod.GET, "/IncomingPhoneNumbers/" + sid + ".json");
        Response response = client.request(request);

        if (response.getStatusCode() != TwilioRestClient.HTTP_STATUS_CODE_OK) {
            throw new ApiException("Unable to find IncomingPhoneNumber for Sid " + sid);
        }

        return IncomingPhoneNumber.fromJson(response.getStream(), client.getObjectMapper());
    }
}