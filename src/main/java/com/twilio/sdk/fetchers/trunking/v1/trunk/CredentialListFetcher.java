package com.twilio.sdk.fetchers.trunking.v1.trunk;

import com.twilio.sdk.clients.TwilioRestClient;
import com.twilio.sdk.exceptions.ApiConnectionException;
import com.twilio.sdk.exceptions.ApiException;
import com.twilio.sdk.fetchers.Fetcher;
import com.twilio.sdk.http.HttpMethod;
import com.twilio.sdk.http.Request;
import com.twilio.sdk.http.Response;
import com.twilio.sdk.resources.RestException;
import com.twilio.sdk.resources.trunking.v1.trunk.CredentialList;

public class CredentialListFetcher extends Fetcher<CredentialList> {
    private final String trunkSid;
    private final String sid;

    /**
     * Construct a new CredentialListFetcher
     * 
     * @param trunkSid The trunk_sid
     * @param sid The sid
     */
    public CredentialListFetcher(final String trunkSid, final String sid) {
        this.trunkSid = trunkSid;
        this.sid = sid;
    }

    /**
     * Make the request to the Twilio API to perform the fetch
     * 
     * @param client TwilioRestClient with which to make the request
     * @return Fetched CredentialList
     */
    @Override
    public CredentialList execute(final TwilioRestClient client) {
        Request request = new Request(
            HttpMethod.GET,
            TwilioRestClient.Domains.TRUNKING,
            "/v1/Trunks/" + this.trunkSid + "/CredentialLists/" + this.sid + "",
            client.getAccountSid()
        );
        
        Response response = client.request(request);
        
        if (response == null) {
            throw new ApiConnectionException("CredentialList fetch failed: Unable to connect to server");
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
        
        return CredentialList.fromJson(response.getStream(), client.getObjectMapper());
    }
}