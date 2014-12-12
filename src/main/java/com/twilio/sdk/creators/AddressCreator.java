package com.twilio.sdk.creators;

import com.twilio.sdk.clients.TwilioRestClient;
import com.twilio.sdk.exceptions.ApiConnectionException;
import com.twilio.sdk.exceptions.ApiException;
import com.twilio.sdk.http.HttpMethod;
import com.twilio.sdk.http.Request;
import com.twilio.sdk.http.Response;
import com.twilio.sdk.resources.Address;
import com.twilio.sdk.resources.RestException;

public class AddressCreator extends Creator<Address> {

    private final String city;
    private final String region;
    private final String friendlyName;
    private final String isoCountry;
    private final String street;
    private final String postalCode;
    private final String customerName;

    public AddressCreator(final String city, final String region, final String friendlyName, final String isoCountry,
                          final String street, final String postalCode, final String customerName) {

        this.city = city;
        this.region = region;
        this.friendlyName = friendlyName;
        this.isoCountry = isoCountry;
        this.street = street;
        this.postalCode = postalCode;
        this.customerName = customerName;
    }

    @Override
    public Address execute(final TwilioRestClient client) {
        Request request = new Request(HttpMethod.POST, "/2010-04-01/Accounts/{AccountSid}/Addresses.json", client.getAccountSid());
        addPostParams(request);
        Response response = client.request(request);

        if (response == null) {
            throw new ApiConnectionException("Address creation failed: Unable to connect to server");
        } else if (response.getStatusCode() != TwilioRestClient.HTTP_STATUS_CODE_CREATED) {
            RestException restException = RestException.fromJson(response.getStream(), client.getObjectMapper());
            throw new ApiException(restException.getMessage(), restException.getCode(), restException.getMoreInfo(),
                                   restException.getStatus(), null);
        }

        return Address.fromJson(response.getStream(), client.getObjectMapper());
    }

    private void addPostParams(final Request request) {
        if (city != null) {
            request.addPostParam("City", city);
        }
        if (region != null) {
            request.addPostParam("Region", region);
        }
        if (friendlyName != null) {
            request.addPostParam("FriendlyName", friendlyName);
        }
        if (isoCountry != null) {
            request.addPostParam("IsoCountry", isoCountry);
        }
        if (street != null) {
            request.addPostParam("Street", street);
        }
        if (postalCode != null) {
            request.addPostParam("PostalCode", postalCode);
        }
        if (customerName != null) {
            request.addPostParam("CustomerName", customerName);
        }
    }

}
