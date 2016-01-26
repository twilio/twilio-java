package com.twilio.sdk.readers.api;

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
import com.twilio.sdk.resources.api.ShortCode;

public class ShortCodeReader extends Reader<ShortCode> {
    private final String accountSid;
    private String friendlyName;
    private String shortCode;

    /**
     * Construct a new ShortCodeReader
     * 
     * @param accountSid The account_sid
     */
    public ShortCodeReader(final String accountSid) {
        this.accountSid = accountSid;
    }

    /**
     * Only show the ShortCode resources with friendly names that exactly match this
     * name
     * 
     * @param friendlyName Filter by friendly name
     * @return this
     */
    public ShortCodeReader byFriendlyName(final String friendlyName) {
        this.friendlyName = friendlyName;
        return this;
    }

    /**
     * Only show the ShortCode resources that match this pattern. You can specify
     * partial numbers and use '*' as a wildcard for any digit
     * 
     * @param shortCode Filter by ShortCode
     * @return this
     */
    public ShortCodeReader byShortCode(final String shortCode) {
        this.shortCode = shortCode;
        return this;
    }

    /**
     * Make the request to the Twilio API to perform the read
     * 
     * @param client TwilioRestClient with which to make the request
     * @return ShortCode ResourceSet
     */
    @Override
    public ResourceSet<ShortCode> execute(final TwilioRestClient client) {
        Request request = new Request(
            HttpMethod.GET,
            "/2010-04-01/Accounts/" + this.accountSid + "/SMS/ShortCodes.json",
            client.getAccountSid()
        );
        
        addQueryParams(request);
        
        Page<ShortCode> page = pageForRequest(client, request);
        
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
    public Page<ShortCode> nextPage(final String nextPageUri, final TwilioRestClient client) {
        Request request = new Request(
            HttpMethod.GET,
            nextPageUri,
            client.getAccountSid()
        );
        return pageForRequest(client, request);
    }

    /**
     * Generate a Page of ShortCode Resources for a given request
     * 
     * @param client TwilioRestClient with which to make the request
     * @param request Request to generate a page for
     * @return Page for the Request
     */
    protected Page<ShortCode> pageForRequest(final TwilioRestClient client, final Request request) {
        Response response = client.request(request);
        
        if (response == null) {
            throw new ApiConnectionException("ShortCode read failed: Unable to connect to server");
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
        
        Page<ShortCode> result = new Page<>();
        result.deserialize("short_codes", response.getContent(), ShortCode.class, client.getObjectMapper());
        
        return result;
    }

    /**
     * Add the requested query string arguments to the Request
     * 
     * @param request Request to add query string arguments to
     */
    private void addQueryParams(final Request request) {
        if (friendlyName != null) {
            request.addQueryParam("FriendlyName", friendlyName);
        }
        
        if (shortCode != null) {
            request.addQueryParam("ShortCode", shortCode);
        }
        
        request.addQueryParam("PageSize", Integer.toString(getPageSize()));
    }
}