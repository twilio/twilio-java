package com.twilio.sdk.readers.api.v2010.account.sip.ipaccesscontrollist;

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
import com.twilio.sdk.resources.api.v2010.account.sip.ipaccesscontrollist.IpAddress;

public class IpAddressReader extends Reader<IpAddress> {
    private final String accountSid;
    private final String ipAccessControlListSid;

    /**
     * Construct a new IpAddressReader.
     * 
     * @param accountSid The account_sid
     * @param ipAccessControlListSid The ip_access_control_list_sid
     */
    public IpAddressReader(final String accountSid, final String ipAccessControlListSid) {
        this.accountSid = accountSid;
        this.ipAccessControlListSid = ipAccessControlListSid;
    }

    /**
     * Make the request to the Twilio API to perform the read.
     * 
     * @param client TwilioRestClient with which to make the request
     * @return IpAddress ResourceSet
     */
    @Override
    public ResourceSet<IpAddress> execute(final TwilioRestClient client) {
        Request request = new Request(
            HttpMethod.GET,
            TwilioRestClient.Domains.API,
            "/2010-04-01/Accounts/" + this.accountSid + "/SIP/IpAccessControlLists/" + this.ipAccessControlListSid + "/IpAddresses.json",
            client.getAccountSid()
        );
        
        addQueryParams(request);
        
        Page<IpAddress> page = pageForRequest(client, request);
        
        return new ResourceSet<>(this, client, page);
    }

    /**
     * Retrieve the next page from the Twilio API.
     * 
     * @param nextPageUri URI from which to retrieve the next page
     * @param client TwilioRestClient with which to make the request
     * @return Next Page
     */
    @Override
    public Page<IpAddress> nextPage(final String nextPageUri, final TwilioRestClient client) {
        Request request = new Request(
            HttpMethod.GET,
            nextPageUri,
            client.getAccountSid()
        );
        return pageForRequest(client, request);
    }

    /**
     * Generate a Page of IpAddress Resources for a given request.
     * 
     * @param client TwilioRestClient with which to make the request
     * @param request Request to generate a page for
     * @return Page for the Request
     */
    protected Page<IpAddress> pageForRequest(final TwilioRestClient client, final Request request) {
        Response response = client.request(request);
        
        if (response == null) {
            throw new ApiConnectionException("IpAddress read failed: Unable to connect to server");
        } else if (response.getStatusCode() != TwilioRestClient.HTTP_STATUS_CODE_OK) {
            RestException restException = RestException.fromJson(response.getStream(), client.getObjectMapper());
            if (restException == null) {
                throw new ApiException("Server Error, no content");
            }
        
            throw new ApiException(
                restException.getMessage(),
                restException.getCode(),
                restException.getMoreInfo(),
                restException.getStatus(),
                null
            );
        }
        
        Page<IpAddress> result = new Page<>();
        result.deserialize(
            "ip_addresses",
            response.getContent(),
            IpAddress.class,
            client.getObjectMapper()
        );
        
        return result;
    }

    /**
     * Add the requested query string arguments to the Request.
     * 
     * @param request Request to add query string arguments to
     */
    private void addQueryParams(final Request request) {
        request.addQueryParam("PageSize", Integer.toString(getPageSize()));
    }
}