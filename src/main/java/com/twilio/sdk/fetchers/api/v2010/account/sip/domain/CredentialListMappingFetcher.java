package com.twilio.sdk.fetchers.api.v2010.account.sip.domain;

import com.twilio.sdk.clients.TwilioRestClient;
import com.twilio.sdk.exceptions.ApiConnectionException;
import com.twilio.sdk.exceptions.ApiException;
import com.twilio.sdk.fetchers.Fetcher;
import com.twilio.sdk.http.HttpMethod;
import com.twilio.sdk.http.Request;
import com.twilio.sdk.http.Response;
import com.twilio.sdk.resources.RestException;
import com.twilio.sdk.resources.api.CredentialListMapping;

public class CredentialListMappingFetcher extends Fetcher<CredentialListMapping> {
    private final String accountSid;
    private final String domainSid;
    private final String sid;

    /**
     * Construct a new CredentialListMappingFetcher
     * 
     * @param accountSid The account_sid
     * @param domainSid The domain_sid
     * @param sid The sid
     */
    public CredentialListMappingFetcher(final String accountSid, final String domainSid, final String sid) {
        this.accountSid = accountSid;
        this.domainSid = domainSid;
        this.sid = sid;
    }

    /**
     * Make the request to the Twilio API to perform the fetch
     * 
     * @param client TwilioRestClient with which to make the request
     * @return Fetched CredentialListMapping
     */
    @Override
    public CredentialListMapping execute(final TwilioRestClient client) {
        Request request = new Request(
            HttpMethod.GET,
            "/2010-04-01/Accounts/" + this.accountSid + "/SIP/Domains/" + this.domainSid + "/CredentialListMappings/" + this.sid + ".json",
            client.getAccountSid()
        );
        
        Response response = client.request(request);
        
        if (response == null) {
            throw new ApiConnectionException("CredentialListMapping fetch failed: Unable to connect to server");
        } else if (response.getStatusCode() != TwilioRestClient.HTTP_STATUS_CODE_OK) {
            RestException restException = RestException.fromJson(response.getStream(), client.getObjectMapper());
            throw new ApiException(
                restException.getMessage(),
                restException.getCode(),
                restException.getMoreInfo(),
                restException.getStatus(),
                null
            );
        }
        
        return CredentialListMapping.fromJson(response.getStream(), client.getObjectMapper());
    }
}