/**
 * This code was generated by
 * \ / _    _  _|   _  _
 *  | (_)\/(_)(_|\/| |(/_  v1.0.0
 *       /       /
 */

package com.twilio.rest.studio.v1.flow.engagement;

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

public class StepReader extends Reader<Step> {
    private final String pathFlowSid;
    private final String pathEngagementSid;

    /**
     * Construct a new StepReader.
     *
     * @param pathFlowSid The SID of the Flow
     * @param pathEngagementSid The SID of the Engagement
     */
    public StepReader(final String pathFlowSid,
                      final String pathEngagementSid) {
        this.pathFlowSid = pathFlowSid;
        this.pathEngagementSid = pathEngagementSid;
    }

    /**
     * Make the request to the Twilio API to perform the read.
     *
     * @param client TwilioRestClient with which to make the request
     * @return Step ResourceSet
     */
    @Override
    public ResourceSet<Step> read(final TwilioRestClient client) {
        return new ResourceSet<>(this, client, firstPage(client));
    }

    /**
     * Make the request to the Twilio API to perform the read.
     *
     * @param client TwilioRestClient with which to make the request
     * @return Step ResourceSet
     */
    @Override
    @SuppressWarnings("checkstyle:linelength")
    public Page<Step> firstPage(final TwilioRestClient client) {
        Request request = new Request(
            HttpMethod.GET,
            Domains.STUDIO.toString(),
            "/v1/Flows/" + this.pathFlowSid + "/Engagements/" + this.pathEngagementSid + "/Steps"
        );

        addQueryParams(request);
        return pageForRequest(client, request);
    }

    /**
     * Retrieve the target page from the Twilio API.
     *
     * @param targetUrl API-generated URL for the requested results page
     * @param client TwilioRestClient with which to make the request
     * @return Step ResourceSet
     */
    @Override
    @SuppressWarnings("checkstyle:linelength")
    public Page<Step> getPage(final String targetUrl, final TwilioRestClient client) {
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
    public Page<Step> nextPage(final Page<Step> page,
                               final TwilioRestClient client) {
        Request request = new Request(
            HttpMethod.GET,
            page.getNextPageUrl(Domains.STUDIO.toString())
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
    public Page<Step> previousPage(final Page<Step> page,
                                   final TwilioRestClient client) {
        Request request = new Request(
            HttpMethod.GET,
            page.getPreviousPageUrl(Domains.STUDIO.toString())
        );
        return pageForRequest(client, request);
    }

    /**
     * Generate a Page of Step Resources for a given request.
     *
     * @param client TwilioRestClient with which to make the request
     * @param request Request to generate a page for
     * @return Page for the Request
     */
    private Page<Step> pageForRequest(final TwilioRestClient client, final Request request) {
        Response response = client.request(request);

        if (response == null) {
            throw new ApiConnectionException("Step read failed: Unable to connect to server");
        } else if (!TwilioRestClient.SUCCESS.test(response.getStatusCode())) {
            RestException restException = RestException.fromJson(response.getStream(), client.getObjectMapper());
            if (restException == null) {
                throw new ApiException("Server Error, no content");
            }
           throw new ApiException(restException);
        }

        return Page.fromJson(
            "steps",
            response.getContent(),
            Step.class,
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
