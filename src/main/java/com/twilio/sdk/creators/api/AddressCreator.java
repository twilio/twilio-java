package com.twilio.sdk.creators.api;

import com.twilio.sdk.clients.TwilioRestClient;
import com.twilio.sdk.creators.Creator;
import com.twilio.sdk.exceptions.ApiConnectionException;
import com.twilio.sdk.exceptions.ApiException;
import com.twilio.sdk.http.HttpMethod;
import com.twilio.sdk.http.Request;
import com.twilio.sdk.http.Response;
import com.twilio.sdk.resources.RestException;
import com.twilio.sdk.resources.api.Address;

public class AddressCreator extends Creator<Address> {
    private final String accountSid;
    private final String customerName;
    private final String street;
    private final String city;
    private final String region;
    private final String postalCode;
    private final String isoCountry;
    private String friendlyName;

    /**
     * Construct a new AddressCreator
     * 
     * @param accountSid The account_sid
     * @param customerName The customer_name
     * @param street The street
     * @param city The city
     * @param region The region
     * @param postalCode The postal_code
     * @param isoCountry The iso_country
     */
    public AddressCreator(final String accountSid, final String customerName, final String street, final String city, final String region, final String postalCode, final String isoCountry) {
        this.accountSid = accountSid;
        this.customerName = customerName;
        this.street = street;
        this.city = city;
        this.region = region;
        this.postalCode = postalCode;
        this.isoCountry = isoCountry;
    }

    /**
     * The friendly_name
     * 
     * @param friendlyName The friendly_name
     * @return this
     */
    public AddressCreator setFriendlyName(final String friendlyName) {
        this.friendlyName = friendlyName;
        return this;
    }

    /**
     * Make the request to the Twilio API to perform the create
     * 
     * @param client TwilioRestClient with which to make the request
     * @return Created Address
     */
    @Override
    public Address execute(final TwilioRestClient client) {
        Request request = new Request(
            HttpMethod.POST,
            "/2010-04-01/Accounts/" + this.accountSid + "/Addresses.json",
            client.getAccountSid()
        );
        
        addPostParams(request);
        Response response = client.request(request);
        
        if (response == null) {
            throw new ApiConnectionException("Address creation failed: Unable to connect to server");
        } else if (response.getStatusCode() != TwilioRestClient.HTTP_STATUS_CODE_CREATED) {
            RestException restException = RestException.fromJson(response.getStream(), client.getObjectMapper());
            throw new ApiException(
                restException.getMessage(),
                restException.getCode(),
                restException.getMoreInfo(),
                restException.getStatus(),
                null
            );
        }
        
        return Address.fromJson(response.getStream(), client.getObjectMapper());
    }

    /**
     * Add the requested post parameters to the Request
     * 
     * @param request Request to add post params to
     */
    private void addPostParams(final Request request) {
        if (customerName != null) {
            request.addPostParam("CustomerName", customerName);
        }
        
        if (street != null) {
            request.addPostParam("Street", street);
        }
        
        if (city != null) {
            request.addPostParam("City", city);
        }
        
        if (region != null) {
            request.addPostParam("Region", region);
        }
        
        if (postalCode != null) {
            request.addPostParam("PostalCode", postalCode);
        }
        
        if (isoCountry != null) {
            request.addPostParam("IsoCountry", isoCountry);
        }
        
        if (friendlyName != null) {
            request.addPostParam("FriendlyName", friendlyName);
        }
    }
}