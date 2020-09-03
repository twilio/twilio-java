/**
 * This code was generated by
 * \ / _    _  _|   _  _
 *  | (_)\/(_)(_|\/| |(/_  v1.0.0
 *       /       /
 */

package com.twilio.rest.numbers.v2.regulatorycompliance;

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

public class BundleReader extends Reader<Bundle> {
    private Bundle.Status status;
    private String friendlyName;
    private String regulationSid;
    private String isoCountry;
    private String numberType;

    /**
     * The verification status of the Bundle resource..
     *
     * @param status The verification status of the Bundle resource
     * @return this
     */
    public BundleReader setStatus(final Bundle.Status status) {
        this.status = status;
        return this;
    }

    /**
     * The string that you assigned to describe the resource..
     *
     * @param friendlyName The string that you assigned to describe the resource
     * @return this
     */
    public BundleReader setFriendlyName(final String friendlyName) {
        this.friendlyName = friendlyName;
        return this;
    }

    /**
     * The unique string of a regulation that is associated to the Bundle resource..
     *
     * @param regulationSid The unique string of a regulation.
     * @return this
     */
    public BundleReader setRegulationSid(final String regulationSid) {
        this.regulationSid = regulationSid;
        return this;
    }

    /**
     * The ISO country code of the Bundle's phone number country ownership request..
     *
     * @param isoCountry The ISO country code of the country
     * @return this
     */
    public BundleReader setIsoCountry(final String isoCountry) {
        this.isoCountry = isoCountry;
        return this;
    }

    /**
     * The type of phone number of the Bundle's ownership request..
     *
     * @param numberType The type of phone number
     * @return this
     */
    public BundleReader setNumberType(final String numberType) {
        this.numberType = numberType;
        return this;
    }

    /**
     * Make the request to the Twilio API to perform the read.
     *
     * @param client TwilioRestClient with which to make the request
     * @return Bundle ResourceSet
     */
    @Override
    public ResourceSet<Bundle> read(final TwilioRestClient client) {
        return new ResourceSet<>(this, client, firstPage(client));
    }

    /**
     * Make the request to the Twilio API to perform the read.
     *
     * @param client TwilioRestClient with which to make the request
     * @return Bundle ResourceSet
     */
    @Override
    @SuppressWarnings("checkstyle:linelength")
    public Page<Bundle> firstPage(final TwilioRestClient client) {
        Request request = new Request(
            HttpMethod.GET,
            Domains.NUMBERS.toString(),
            "/v2/RegulatoryCompliance/Bundles"
        );

        addQueryParams(request);
        return pageForRequest(client, request);
    }

    /**
     * Retrieve the target page from the Twilio API.
     *
     * @param targetUrl API-generated URL for the requested results page
     * @param client TwilioRestClient with which to make the request
     * @return Bundle ResourceSet
     */
    @Override
    @SuppressWarnings("checkstyle:linelength")
    public Page<Bundle> getPage(final String targetUrl, final TwilioRestClient client) {
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
    public Page<Bundle> nextPage(final Page<Bundle> page,
                                 final TwilioRestClient client) {
        Request request = new Request(
            HttpMethod.GET,
            page.getNextPageUrl(Domains.NUMBERS.toString())
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
    public Page<Bundle> previousPage(final Page<Bundle> page,
                                     final TwilioRestClient client) {
        Request request = new Request(
            HttpMethod.GET,
            page.getPreviousPageUrl(Domains.NUMBERS.toString())
        );
        return pageForRequest(client, request);
    }

    /**
     * Generate a Page of Bundle Resources for a given request.
     *
     * @param client TwilioRestClient with which to make the request
     * @param request Request to generate a page for
     * @return Page for the Request
     */
    private Page<Bundle> pageForRequest(final TwilioRestClient client, final Request request) {
        Response response = client.request(request);

        if (response == null) {
            throw new ApiConnectionException("Bundle read failed: Unable to connect to server");
        } else if (!TwilioRestClient.SUCCESS.test(response.getStatusCode())) {
            RestException restException = RestException.fromJson(response.getStream(), client.getObjectMapper());
            if (restException == null) {
                throw new ApiException("Server Error, no content");
            }
           throw new ApiException(restException);
        }

        return Page.fromJson(
            "results",
            response.getContent(),
            Bundle.class,
            client.getObjectMapper()
        );
    }

    /**
     * Add the requested query string arguments to the Request.
     *
     * @param request Request to add query string arguments to
     */
    private void addQueryParams(final Request request) {
        if (status != null) {
            request.addQueryParam("Status", status.toString());
        }

        if (friendlyName != null) {
            request.addQueryParam("FriendlyName", friendlyName);
        }

        if (regulationSid != null) {
            request.addQueryParam("RegulationSid", regulationSid);
        }

        if (isoCountry != null) {
            request.addQueryParam("IsoCountry", isoCountry);
        }

        if (numberType != null) {
            request.addQueryParam("NumberType", numberType);
        }

        if (getPageSize() != null) {
            request.addQueryParam("PageSize", Integer.toString(getPageSize()));
        }
    }
}
