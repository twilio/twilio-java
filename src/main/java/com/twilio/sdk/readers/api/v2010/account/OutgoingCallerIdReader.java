package com.twilio.sdk.readers.api.v2010.account;

import com.twilio.sdk.clients.TwilioRestClient;
import com.twilio.sdk.exceptions.ApiConnectionException;
import com.twilio.sdk.exceptions.ApiException;
import com.twilio.sdk.http.HttpMethod;
import com.twilio.sdk.http.Request;
import com.twilio.sdk.http.Response;
import com.twilio.sdk.readers.Reader;
import com.twilio.sdk.resources.Page;
import com.twilio.sdk.resources.ResourceSet;
import com.twilio.sdk.resources.RestException;
import com.twilio.sdk.resources.api.OutgoingCallerId;

public class OutgoingCallerIdReader extends Reader<OutgoingCallerId> {
    private final String accountSid;
    private String phoneNumber;
    private String friendlyName;

    /**
     * Construct a new OutgoingCallerIdReader
     * 
     * @param accountSid The account_sid
     */
    public OutgoingCallerIdReader(final String accountSid) {
        this.accountSid = accountSid;
    }

    /**
     * Only show the caller id resource that exactly matches this phone number
     * 
     * @param phoneNumber Filter by phone number
     * @return this
     */
    public OutgoingCallerIdReader byPhoneNumber(final String phoneNumber) {
        this.phoneNumber = phoneNumber;
        return this;
    }

    /**
     * Only show the caller id resource that exactly matches this name
     * 
     * @param friendlyName Filter by friendly name
     * @return this
     */
    public OutgoingCallerIdReader byFriendlyName(final String friendlyName) {
        this.friendlyName = friendlyName;
        return this;
    }

    /**
     * Make the request to the Twilio API to perform the read
     * 
     * @param client TwilioRestClient with which to make the request
     * @return OutgoingCallerId ResourceSet
     */
    @Override
    public ResourceSet<OutgoingCallerId> execute(final TwilioRestClient client) {
        Request request = new Request(
            HttpMethod.GET,
            "/2010-04-01/Accounts/" + this.accountSid + "/OutgoingCallerIds.json",
            client.getAccountSid()
        );
        
        addQueryParams(request);
        
        Page<OutgoingCallerId> page = pageForRequest(client, request);
        
        return new ResourceSet<>(this, client, page);
    }

    /**
     * Retrieve the next page from the Twilio API
     * 
     * @param nextPageUri URI from which to retrieve the next page
     * @param client TwilioRestClient with which to make the request
     * @return Next Page
     */
    @Override
    public Page<OutgoingCallerId> nextPage(final String nextPageUri, final TwilioRestClient client) {
        Request request = new Request(
            HttpMethod.GET,
            nextPageUri,
            client.getAccountSid()
        );
        return pageForRequest(client, request);
    }

    /**
     * Generate a Page of OutgoingCallerId Resources for a given request
     * 
     * @param client TwilioRestClient with which to make the request
     * @param request Request to generate a page for
     * @return Page for the Request
     */
    protected Page<OutgoingCallerId> pageForRequest(final TwilioRestClient client, final Request request) {
        Response response = client.request(request);
        
        if (response == null) {
            throw new ApiConnectionException("OutgoingCallerId read failed: Unable to connect to server");
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
        
        Page<OutgoingCallerId> result = new Page<>();
        result.deserialize("outgoing_caller_ids", response.getContent(), OutgoingCallerId.class, client.getObjectMapper());
        
        return result;
    }

    /**
     * Add the requested query string arguments to the Request
     * 
     * @param request Request to add query string arguments to
     */
    private void addQueryParams(final Request request) {
        if (phoneNumber != null) {
            request.addQueryParam("PhoneNumber", phoneNumber);
        }
        
        if (friendlyName != null) {
            request.addQueryParam("FriendlyName", friendlyName);
        }
        
        request.addQueryParam("PageSize", Integer.toString(getPageSize()));
    }
}