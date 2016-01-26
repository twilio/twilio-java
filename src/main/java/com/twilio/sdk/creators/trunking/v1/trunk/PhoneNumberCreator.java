package com.twilio.sdk.creators.trunking.v1.trunk;

import com.twilio.sdk.clients.TwilioRestClient;
import com.twilio.sdk.creators.Creator;
import com.twilio.sdk.exceptions.ApiConnectionException;
import com.twilio.sdk.exceptions.ApiException;
import com.twilio.sdk.http.HttpMethod;
import com.twilio.sdk.http.Request;
import com.twilio.sdk.http.Response;
import com.twilio.sdk.resources.RestException;
import com.twilio.sdk.resources.trunking.PhoneNumber;

public class PhoneNumberCreator extends Creator<PhoneNumber> {
    private final String trunkSid;
    private final String phoneNumberSid;

    /**
     * Construct a new PhoneNumberCreator
     * 
     * @param trunkSid The trunk_sid
     * @param phoneNumberSid The phone_number_sid
     */
    public PhoneNumberCreator(final String trunkSid, final String phoneNumberSid) {
        this.trunkSid = trunkSid;
        this.phoneNumberSid = phoneNumberSid;
    }

    /**
     * Make the request to the Twilio API to perform the create
     * 
     * @param client TwilioRestClient with which to make the request
     * @return Created PhoneNumber
     */
    @Override
    public PhoneNumber execute(final TwilioRestClient client) {
        Request request = new Request(
            HttpMethod.POST,
            "/v1/Trunks/" + this.trunkSid + "/PhoneNumbers",
            client.getAccountSid()
        );
        
        addPostParams(request);
        Response response = client.request(request);
        
        if (response == null) {
            throw new ApiConnectionException("PhoneNumber creation failed: Unable to connect to server");
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
        
        return PhoneNumber.fromJson(response.getStream(), client.getObjectMapper());
    }

    /**
     * Add the requested post parameters to the Request
     * 
     * @param request Request to add post params to
     */
    private void addPostParams(final Request request) {
        if (phoneNumberSid != null) {
            request.addPostParam("PhoneNumberSid", phoneNumberSid);
        }
    }
}