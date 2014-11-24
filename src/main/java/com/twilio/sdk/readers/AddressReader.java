package com.twilio.sdk.readers;


import com.twilio.sdk.clients.TwilioRestClient;
import com.twilio.sdk.exceptions.ApiException;
import com.twilio.sdk.http.HttpMethod;
import com.twilio.sdk.http.Request;
import com.twilio.sdk.http.Response;
import com.twilio.sdk.resources.Address;
import com.twilio.sdk.resources.Page;
import com.twilio.sdk.resources.ResourceSet;
import com.twilio.sdk.resources.RestException;


public class AddressReader extends Reader<Address> {
    private String isoCountry;
    private String friendlyName;
    private String customerName;

    @Override
    public ResourceSet<Address> execute(final TwilioRestClient client) {
        Request request = new Request(HttpMethod.GET, "/Addresses.json");
        addQueryParams(request);

        Page<Address> page = pageForRequest(client, request);

        return new ResourceSet<>(this, client, page);
    }

    @Override
    public Page<Address> nextPage(final String nextPageUri, final TwilioRestClient client) {
        Request request = new Request(HttpMethod.GET, nextPageUri);
        return pageForRequest(client, request);
    }

    protected Page<Address> pageForRequest(final TwilioRestClient client, final Request request) {
        Response response = client.request(request);

        if (response.getStatusCode() != TwilioRestClient.HTTP_STATUS_CODE_OK) {
            RestException restException = RestException.fromJson(response.getStream(), client.getObjectMapper());
            throw new ApiException(restException.getMessage(), restException.getCode(), restException.getMoreInfo(),
                                   restException.getStatus(), null);
        }

        Page<Address> result = new Page<>();
        result.deserialize("addresses", response.getContent(), Address.class, client.getObjectMapper());

        return result;
    }

    public AddressReader byIsoCountry(final String isoCountry) {
        this.isoCountry = isoCountry;
        return this;
    }

    public AddressReader byFriendlyName(final String friendlyName) {
        this.friendlyName = friendlyName;
        return this;
    }

    public AddressReader byCustomerName(final String customerName) {
        this.customerName = customerName;
        return this;
    }

    private void addQueryParams(final Request request) {
        if (isoCountry != null) {
                request.addQueryParam("IsoCountry", isoCountry);
                }
            if (friendlyName != null) {
                request.addQueryParam("FriendlyName", friendlyName);
                }
            if (customerName != null) {
                request.addQueryParam("CustomerName", customerName);
                }
            }
}