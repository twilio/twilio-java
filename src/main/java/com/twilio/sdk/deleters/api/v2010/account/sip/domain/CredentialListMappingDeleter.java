package com.twilio.sdk.deleters.api.v2010.account.sip.domain;

import com.twilio.sdk.clients.TwilioRestClient;
import com.twilio.sdk.deleters.Deleter;
import com.twilio.sdk.exceptions.ApiConnectionException;
import com.twilio.sdk.exceptions.ApiException;
import com.twilio.sdk.http.HttpMethod;
import com.twilio.sdk.http.Request;
import com.twilio.sdk.http.Response;
import com.twilio.sdk.resources.RestException;
import com.twilio.sdk.resources.api.CredentialListMapping;

public class CredentialListMappingDeleter extends Deleter<CredentialListMapping> {
    private final String accountSid;
    private final String domainSid;
    private final String sid;

    /**
     * Construct a new CredentialListMappingDeleter
     * 
     * @param accountSid The account_sid
     * @param domainSid The domain_sid
     * @param sid The sid
     */
    public CredentialListMappingDeleter(final String accountSid, final String domainSid, final String sid) {
        this.accountSid = accountSid;
        this.domainSid = domainSid;
        this.sid = sid;
    }

    /**
     * Make the request to the Twilio API to perform the delete
     * 
     * @param client TwilioRestClient with which to make the request
     */
    @Override
    public void execute(final TwilioRestClient client) {
        Request request = new Request(
            HttpMethod.DELETE,
            "/2010-04-01/Accounts/" + this.accountSid + "/SIP/Domains/" + this.domainSid + "/CredentialListMappings/" + this.sid + ".json",
            client.getAccountSid()
        );
        
        Response response = client.request(request);
        
        if (response == null) {
            throw new ApiConnectionException("CredentialListMapping delete failed: Unable to connect to server");
        } else if (response.getStatusCode() != TwilioRestClient.HTTP_STATUS_CODE_NO_CONTENT) {
            RestException restException = RestException.fromJson(response.getStream(), client.getObjectMapper());
            throw new ApiException(
                restException.getMessage(),
                restException.getCode(),
                restException.getMoreInfo(),
                restException.getStatus(),
                null
            );
        }
    }
}