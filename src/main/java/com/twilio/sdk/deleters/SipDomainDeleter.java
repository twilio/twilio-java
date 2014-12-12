package com.twilio.sdk.deleters;

import com.twilio.sdk.clients.TwilioRestClient;
import com.twilio.sdk.exceptions.ApiConnectionException;
import com.twilio.sdk.exceptions.ApiException;
import com.twilio.sdk.http.HttpMethod;
import com.twilio.sdk.http.Request;
import com.twilio.sdk.http.Response;
import com.twilio.sdk.resources.SipDomain;
import com.twilio.sdk.resources.RestException;

public class SipDomainDeleter extends Deleter<SipDomain> {

    private final String sid;

    public SipDomainDeleter(final String sid) {
        this.sid = sid;
    }

    public SipDomainDeleter(final SipDomain target) {
        this(target.getSid());
    }

    @Override
    public void execute(final TwilioRestClient client)  {
        Request request = new Request(HttpMethod.DELETE, "/2010-04-01/Accounts/{AccountSid}/SIP/Domains/" + sid + ".json", client.getAccountSid());
        Response response = client.request(request);

        if (response == null) {
            throw new ApiConnectionException("SipDomain delete failed: Unable to connect to server");
        } else if (response.getStatusCode() != TwilioRestClient.HTTP_STATUS_CODE_NO_CONTENT) {
            RestException restException = RestException.fromJson(response.getStream(), client.getObjectMapper());
            throw new ApiException(restException.getMessage(), restException.getCode(), restException.getMoreInfo(),
                                   restException.getStatus(), null);
        }
    }
}