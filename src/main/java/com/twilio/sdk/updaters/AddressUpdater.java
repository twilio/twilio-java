package com.twilio.sdk.updaters;

import com.twilio.sdk.clients.TwilioRestClient;
import com.twilio.sdk.exceptions.ApiConnectionException;
import com.twilio.sdk.exceptions.ApiException;
import com.twilio.sdk.http.HttpMethod;
import com.twilio.sdk.http.Request;
import com.twilio.sdk.http.Response;
import com.twilio.sdk.resources.Address;
import com.twilio.sdk.resources.RestException;




public class AddressUpdater extends Updater<Address> {

    private final String sid;
    private String city;
    private String region;
    private String friendlyName;
    private String street;
    private String postalCode;
    private String customerName;
    

    public AddressUpdater(final String sid) {
        this.sid = sid;
    }

    public AddressUpdater(final Address target) {
        this(target.getSid());
    }

    
    public AddressUpdater setCity(final String city) {
        this.city = city;
        return this;
    }
    
    public AddressUpdater setRegion(final String region) {
        this.region = region;
        return this;
    }
    
    public AddressUpdater setFriendlyName(final String friendlyName) {
        this.friendlyName = friendlyName;
        return this;
    }
    
    public AddressUpdater setStreet(final String street) {
        this.street = street;
        return this;
    }
    
    public AddressUpdater setPostalCode(final String postalCode) {
        this.postalCode = postalCode;
        return this;
    }
    
    public AddressUpdater setCustomerName(final String customerName) {
        this.customerName = customerName;
        return this;
    }
    

    @Override
    public Address execute(final TwilioRestClient client)  {
        Request request = new Request(HttpMethod.POST, "/Addresses/" + sid + ".json");
        addPostParams(request);
        Response response = client.request(request);

        if (response == null) {
            throw new ApiConnectionException("Address update failed: Unable to connect to server");
        } else if (response.getStatusCode() != TwilioRestClient.HTTP_STATUS_CODE_OK) {
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