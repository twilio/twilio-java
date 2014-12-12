package com.twilio.sdk.readers;

import com.twilio.sdk.clients.TwilioRestClient;
import com.twilio.sdk.exceptions.ApiException;
import com.twilio.sdk.http.HttpMethod;
import com.twilio.sdk.http.Request;
import com.twilio.sdk.http.Response;
import com.twilio.sdk.resources.IncomingPhoneNumber;
import com.twilio.sdk.resources.Page;
import com.twilio.sdk.resources.PhoneNumberType;
import com.twilio.sdk.resources.ResourceSet;
import com.twilio.sdk.resources.RestException;

public class IncomingPhoneNumberReader extends Reader<IncomingPhoneNumber> {

    private String phoneNumber;
    private String friendlyName;
    private PhoneNumberType type;

    @Override
    public ResourceSet<IncomingPhoneNumber> execute(final TwilioRestClient client) {
        String url =
                "/2010-04-01/Accounts/{AccountSid}/IncomingPhoneNumbers" + (type != null ? "/" + type.toString() : "") + ".json";
        Request request = new Request(HttpMethod.GET, url, client.getAccountSid());
        addQueryParams(request);

        Page<IncomingPhoneNumber> page = pageForRequest(client, request);

        return new ResourceSet<>(this, client, page);
    }

    @Override
    public Page<IncomingPhoneNumber> nextPage(final String nextPageUri, final TwilioRestClient client) {
        Request request = new Request(HttpMethod.GET, nextPageUri, client.getAccountSid());
        return pageForRequest(client, request);
    }

    protected Page<IncomingPhoneNumber> pageForRequest(final TwilioRestClient client, final Request request) {
        Response response = client.request(request);

        if (response.getStatusCode() != TwilioRestClient.HTTP_STATUS_CODE_OK) {
            RestException restException = RestException.fromJson(response.getStream(), client.getObjectMapper());
            throw new ApiException(restException.getMessage(), restException.getCode(), restException.getMoreInfo(),
                                   restException.getStatus(), null);
        }

        Page<IncomingPhoneNumber> result = new Page<>();
        result.deserialize("incoming_phone_numbers", response.getContent(), IncomingPhoneNumber.class, client.getObjectMapper());

        return result;
    }

    public IncomingPhoneNumberReader byPhoneNumber(final String phoneNumber) {
        this.phoneNumber = phoneNumber;
        return this;
    }

    public IncomingPhoneNumberReader byFriendlyName(final String friendlyName) {
        this.friendlyName = friendlyName;
        return this;
    }

    public IncomingPhoneNumberReader byType(final PhoneNumberType type) {
        this.type = type;
        return this;
    }

    private void addQueryParams(final Request request) {
        if (phoneNumber != null) {
            request.addQueryParam("PhoneNumber", phoneNumber);
        }
        if (friendlyName != null) {
            request.addQueryParam("FriendlyName", friendlyName);
        }
    }
}