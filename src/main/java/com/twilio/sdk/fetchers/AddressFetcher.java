package com.twilio.sdk.fetchers;

import com.twilio.sdk.clients.TwilioRestClient;
import com.twilio.sdk.exceptions.ApiException;
import com.twilio.sdk.http.HttpMethod;
import com.twilio.sdk.http.Request;
import com.twilio.sdk.http.Response;
import com.twilio.sdk.resources.Address;
import com.twilio.sdk.resources.RestException;

public class AddressFetcher extends Fetcher<Address> {

    private final String sid;

    public AddressFetcher(final String sid) {
        this.sid = sid;
    }

    @Override
    public Address execute(final TwilioRestClient client) {
        Request request = new Request(HttpMethod.GET, "/Addresses/" + sid + ".json");
        Response response = client.request(request);

        if (response.getStatusCode() != TwilioRestClient.HTTP_STATUS_CODE_OK) {
            RestException restException = RestException.fromJson(response.getStream(), client.getObjectMapper());
            throw new ApiException(restException.getMessage(), restException.getCode(), restException.getMoreInfo(),
                                   restException.getStatus(), null);
        }

        return Address.fromJson(response.getStream(), client.getObjectMapper());
    }
}