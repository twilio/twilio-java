/**
 * This code was generated by
 * \ / _    _  _|   _  _
 *  | (_)\/(_)(_|\/| |(/_  v1.0.0
 *       /       /
 */

package com.twilio.rest.api.v2010.account.address;

import com.twilio.base.Page;
import com.twilio.base.Reader;
import com.twilio.base.ResourceSet;
import com.twilio.exception.ApiConnectionException;
import com.twilio.exception.ApiException;
import com.twilio.exception.RestException;
import com.twilio.http.HttpMethod;
import com.twilio.http.Request;
import com.twilio.http.Response;
import com.twilio.http.TwilioRestClient;
import com.twilio.rest.Domains;

public class DependentPhoneNumberReader extends Reader<DependentPhoneNumber> {
    private String pathAccountSid;
    private final String pathAddressSid;

    /**
     * Construct a new DependentPhoneNumberReader.
     *
     * @param pathAddressSid The SID of the Address resource associated with the
     *                       phone number
     */
    public DependentPhoneNumberReader(final String pathAddressSid) {
        this.pathAddressSid = pathAddressSid;
    }

    /**
     * Construct a new DependentPhoneNumberReader.
     *
     * @param pathAccountSid The SID of the Account that created the resources to
     *                       read
     * @param pathAddressSid The SID of the Address resource associated with the
     *                       phone number
     */
    public DependentPhoneNumberReader(final String pathAccountSid,
                                      final String pathAddressSid) {
        this.pathAccountSid = pathAccountSid;
        this.pathAddressSid = pathAddressSid;
    }

    /**
     * Make the request to the Twilio API to perform the read.
     *
     * @param client TwilioRestClient with which to make the request
     * @return DependentPhoneNumber ResourceSet
     */
    @Override
    public ResourceSet<DependentPhoneNumber> read(final TwilioRestClient client) {
        return new ResourceSet<>(this, client, firstPage(client));
    }

    /**
     * Make the request to the Twilio API to perform the read.
     *
     * @param client TwilioRestClient with which to make the request
     * @return DependentPhoneNumber ResourceSet
     */
    @Override
    @SuppressWarnings("checkstyle:linelength")
    public Page<DependentPhoneNumber> firstPage(final TwilioRestClient client) {
        this.pathAccountSid = this.pathAccountSid == null ? client.getAccountSid() : this.pathAccountSid;
        Request request = new Request(
            HttpMethod.GET,
            Domains.API.toString(),
            "/2010-04-01/Accounts/" + this.pathAccountSid + "/Addresses/" + this.pathAddressSid + "/DependentPhoneNumbers.json",
            client.getRegion()
        );

        addQueryParams(request);
        return pageForRequest(client, request);
    }

    /**
     * Retrieve the target page from the Twilio API.
     *
     * @param targetUrl API-generated URL for the requested results page
     * @param client TwilioRestClient with which to make the request
     * @return DependentPhoneNumber ResourceSet
     */
    @Override
    @SuppressWarnings("checkstyle:linelength")
    public Page<DependentPhoneNumber> getPage(final String targetUrl, final TwilioRestClient client) {
        this.pathAccountSid = this.pathAccountSid == null ? client.getAccountSid() : this.pathAccountSid;
        Request request = new Request(
            HttpMethod.GET,
            targetUrl
        );

        return pageForRequest(client, request);
    }

    /**
     * Retrieve the next page from the Twilio API.
     *
     * @param page current page
     * @param client TwilioRestClient with which to make the request
     * @return Next Page
     */
    @Override
    public Page<DependentPhoneNumber> nextPage(final Page<DependentPhoneNumber> page,
                                               final TwilioRestClient client) {
        Request request = new Request(
            HttpMethod.GET,
            page.getNextPageUrl(
                Domains.API.toString(),
                client.getRegion()
            )
        );
        return pageForRequest(client, request);
    }

    /**
     * Retrieve the previous page from the Twilio API.
     *
     * @param page current page
     * @param client TwilioRestClient with which to make the request
     * @return Previous Page
     */
    @Override
    public Page<DependentPhoneNumber> previousPage(final Page<DependentPhoneNumber> page,
                                                   final TwilioRestClient client) {
        Request request = new Request(
            HttpMethod.GET,
            page.getPreviousPageUrl(
                Domains.API.toString(),
                client.getRegion()
            )
        );
        return pageForRequest(client, request);
    }

    /**
     * Generate a Page of DependentPhoneNumber Resources for a given request.
     *
     * @param client TwilioRestClient with which to make the request
     * @param request Request to generate a page for
     * @return Page for the Request
     */
    private Page<DependentPhoneNumber> pageForRequest(final TwilioRestClient client, final Request request) {
        Response response = client.request(request);

        if (response == null) {
            throw new ApiConnectionException("DependentPhoneNumber read failed: Unable to connect to server");
        } else if (!TwilioRestClient.SUCCESS.apply(response.getStatusCode())) {
            RestException restException = RestException.fromJson(response.getStream(), client.getObjectMapper());
            if (restException == null) {
                throw new ApiException("Server Error, no content");
            }

            throw new ApiException(
                restException.getMessage(),
                restException.getCode(),
                restException.getMoreInfo(),
                restException.getStatus(),
                restException.getDetails(),
                null
            );
        }

        return Page.fromJson(
            "dependent_phone_numbers",
            response.getContent(),
            DependentPhoneNumber.class,
            client.getObjectMapper()
        );
    }

    /**
     * Add the requested query string arguments to the Request.
     *
     * @param request Request to add query string arguments to
     */
    private void addQueryParams(final Request request) {
        if (getPageSize() != null) {
            request.addQueryParam("PageSize", Integer.toString(getPageSize()));
        }
    }
}