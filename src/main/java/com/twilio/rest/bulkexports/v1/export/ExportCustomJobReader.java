/**
 * This code was generated by
 * \ / _    _  _|   _  _
 *  | (_)\/(_)(_|\/| |(/_  v1.0.0
 *       /       /
 */

package com.twilio.rest.bulkexports.v1.export;

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

/**
 * PLEASE NOTE that this class contains beta products that are subject to
 * change. Use them with caution.
 */
public class ExportCustomJobReader extends Reader<ExportCustomJob> {
    private final String pathResourceType;
    private String nextToken;
    private String previousToken;

    /**
     * Construct a new ExportCustomJobReader.
     *
     * @param pathResourceType The type of communication – Messages, Calls
     */
    public ExportCustomJobReader(final String pathResourceType) {
        this.pathResourceType = pathResourceType;
    }

    /**
     * The token for the next page of job results, and may be null if there are no
     * more pages.
     *
     * @param nextToken The token for the next page of job results
     * @return this
     */
    public ExportCustomJobReader setNextToken(final String nextToken) {
        this.nextToken = nextToken;
        return this;
    }

    /**
     * The token for the previous page of results, and may be null if this is the
     * first page.
     *
     * @param previousToken The token for the previous page of result
     * @return this
     */
    public ExportCustomJobReader setPreviousToken(final String previousToken) {
        this.previousToken = previousToken;
        return this;
    }

    /**
     * Make the request to the Twilio API to perform the read.
     *
     * @param client TwilioRestClient with which to make the request
     * @return ExportCustomJob ResourceSet
     */
    @Override
    public ResourceSet<ExportCustomJob> read(final TwilioRestClient client) {
        return new ResourceSet<>(this, client, firstPage(client));
    }

    /**
     * Make the request to the Twilio API to perform the read.
     *
     * @param client TwilioRestClient with which to make the request
     * @return ExportCustomJob ResourceSet
     */
    @Override
    @SuppressWarnings("checkstyle:linelength")
    public Page<ExportCustomJob> firstPage(final TwilioRestClient client) {
        Request request = new Request(
            HttpMethod.GET,
            Domains.BULKEXPORTS.toString(),
            "/v1/Exports/" + this.pathResourceType + "/Jobs",
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
     * @return ExportCustomJob ResourceSet
     */
    @Override
    @SuppressWarnings("checkstyle:linelength")
    public Page<ExportCustomJob> getPage(final String targetUrl, final TwilioRestClient client) {
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
    public Page<ExportCustomJob> nextPage(final Page<ExportCustomJob> page,
                                          final TwilioRestClient client) {
        Request request = new Request(
            HttpMethod.GET,
            page.getNextPageUrl(
                Domains.BULKEXPORTS.toString(),
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
    public Page<ExportCustomJob> previousPage(final Page<ExportCustomJob> page,
                                              final TwilioRestClient client) {
        Request request = new Request(
            HttpMethod.GET,
            page.getPreviousPageUrl(
                Domains.BULKEXPORTS.toString(),
                client.getRegion()
            )
        );
        return pageForRequest(client, request);
    }

    /**
     * Generate a Page of ExportCustomJob Resources for a given request.
     *
     * @param client TwilioRestClient with which to make the request
     * @param request Request to generate a page for
     * @return Page for the Request
     */
    private Page<ExportCustomJob> pageForRequest(final TwilioRestClient client, final Request request) {
        Response response = client.request(request);

        if (response == null) {
            throw new ApiConnectionException("ExportCustomJob read failed: Unable to connect to server");
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
            "jobs",
            response.getContent(),
            ExportCustomJob.class,
            client.getObjectMapper()
        );
    }

    /**
     * Add the requested query string arguments to the Request.
     *
     * @param request Request to add query string arguments to
     */
    private void addQueryParams(final Request request) {
        if (nextToken != null) {
            request.addQueryParam("NextToken", nextToken);
        }

        if (previousToken != null) {
            request.addQueryParam("PreviousToken", previousToken);
        }

        if (getPageSize() != null) {
            request.addQueryParam("PageSize", Integer.toString(getPageSize()));
        }
    }
}