package com.twilio.sdk.readers;

import com.twilio.sdk.clients.TwilioRestClient;
import com.twilio.sdk.exceptions.ApiException;
import com.twilio.sdk.http.HttpMethod;
import com.twilio.sdk.http.Request;
import com.twilio.sdk.http.Response;
import com.twilio.sdk.resources.Address;
import com.twilio.sdk.resources.DependentPhoneNumber;
import com.twilio.sdk.resources.Page;
import com.twilio.sdk.resources.ResourceSet;
import com.twilio.sdk.resources.RestException;

public class DependentPhoneNumberReader extends Reader<DependentPhoneNumber> {

    private final String addressSid;

    public DependentPhoneNumberReader(final String addressSid) {
        this.addressSid = addressSid;
    }

    public DependentPhoneNumberReader(final Address target) {
        this(target.getSid());
    }

    @Override
    public ResourceSet<DependentPhoneNumber> execute(final TwilioRestClient client) {
        Request request = new Request(HttpMethod.GET,
                                      "/Accounts/{AccountSid}/Addresses/" + addressSid + "/DependentPhoneNumbers.json",
                                      client.getAccountSid());

        Page<DependentPhoneNumber> page = pageForRequest(client, request);

        return new ResourceSet<>(this, client, page);
    }

    @Override
    public Page<DependentPhoneNumber> nextPage(final String nextPageUri, final TwilioRestClient client) {
        Request request = new Request(HttpMethod.GET, nextPageUri, client.getAccountSid());
        return pageForRequest(client, request);
    }

    protected Page<DependentPhoneNumber> pageForRequest(final TwilioRestClient client, final Request request) {
        Response response = client.request(request);

        if (response.getStatusCode() != TwilioRestClient.HTTP_STATUS_CODE_OK) {
            RestException restException = RestException.fromJson(response.getStream(), client.getObjectMapper());
            throw new ApiException(restException.getMessage(), restException.getCode(), restException.getMoreInfo(),
                                   restException.getStatus(), null);
        }

        Page<DependentPhoneNumber> result = new Page<>();
        result.deserialize("dependent_phone_numbers", response.getContent(), DependentPhoneNumber.class,
                           client.getObjectMapper());

        return result;
    }
}
