/**
 * This code was generated by
 * \ / _    _  _|   _  _
 *  | (_)\/(_)(_|\/| |(/_  v1.0.0
 *       /       /
 */

package com.twilio.rest.wireless.v1;

import com.twilio.base.Page;
import com.twilio.base.Reader;
import com.twilio.base.ResourceSet;
import com.twilio.converter.DateConverter;
import com.twilio.exception.ApiConnectionException;
import com.twilio.exception.ApiException;
import com.twilio.exception.RestException;
import com.twilio.http.HttpMethod;
import com.twilio.http.Request;
import com.twilio.http.Response;
import com.twilio.http.TwilioRestClient;
import com.twilio.rest.Domains;
import org.joda.time.DateTime;

public class UsageRecordReader extends Reader<UsageRecord> {
    private DateTime end;
    private DateTime start;
    private UsageRecord.Granularity granularity;

    /**
     * Only include usage that has occurred on or before this date. Format is <a
     * href="https://www.iso.org/iso-8601-date-and-time-format.html">ISO 8601</a>..
     *
     * @param end Only include usage that has occurred on or before this date
     * @return this
     */
    public UsageRecordReader setEnd(final DateTime end) {
        this.end = end;
        return this;
    }

    /**
     * Only include usage that has occurred on or after this date. Format is <a
     * href="https://www.iso.org/iso-8601-date-and-time-format.html">ISO 8601</a>..
     *
     * @param start Only include usage that has occurred on or after this date
     * @return this
     */
    public UsageRecordReader setStart(final DateTime start) {
        this.start = start;
        return this;
    }

    /**
     * How to summarize the usage by time. Can be: `daily`, `hourly`, or `all`. A
     * value of `all` returns one Usage Record that describes the usage for the
     * entire period..
     *
     * @param granularity The time-based grouping that results are aggregated by
     * @return this
     */
    public UsageRecordReader setGranularity(final UsageRecord.Granularity granularity) {
        this.granularity = granularity;
        return this;
    }

    /**
     * Make the request to the Twilio API to perform the read.
     *
     * @param client TwilioRestClient with which to make the request
     * @return UsageRecord ResourceSet
     */
    @Override
    public ResourceSet<UsageRecord> read(final TwilioRestClient client) {
        return new ResourceSet<>(this, client, firstPage(client));
    }

    /**
     * Make the request to the Twilio API to perform the read.
     *
     * @param client TwilioRestClient with which to make the request
     * @return UsageRecord ResourceSet
     */
    @Override
    @SuppressWarnings("checkstyle:linelength")
    public Page<UsageRecord> firstPage(final TwilioRestClient client) {
        Request request = new Request(
            HttpMethod.GET,
            Domains.WIRELESS.toString(),
            "/v1/UsageRecords"
        );

        addQueryParams(request);
        return pageForRequest(client, request);
    }

    /**
     * Retrieve the target page from the Twilio API.
     *
     * @param targetUrl API-generated URL for the requested results page
     * @param client TwilioRestClient with which to make the request
     * @return UsageRecord ResourceSet
     */
    @Override
    @SuppressWarnings("checkstyle:linelength")
    public Page<UsageRecord> getPage(final String targetUrl, final TwilioRestClient client) {
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
    public Page<UsageRecord> nextPage(final Page<UsageRecord> page,
                                      final TwilioRestClient client) {
        Request request = new Request(
            HttpMethod.GET,
            page.getNextPageUrl(Domains.WIRELESS.toString())
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
    public Page<UsageRecord> previousPage(final Page<UsageRecord> page,
                                          final TwilioRestClient client) {
        Request request = new Request(
            HttpMethod.GET,
            page.getPreviousPageUrl(Domains.WIRELESS.toString())
        );
        return pageForRequest(client, request);
    }

    /**
     * Generate a Page of UsageRecord Resources for a given request.
     *
     * @param client TwilioRestClient with which to make the request
     * @param request Request to generate a page for
     * @return Page for the Request
     */
    private Page<UsageRecord> pageForRequest(final TwilioRestClient client, final Request request) {
        Response response = client.request(request);

        if (response == null) {
            throw new ApiConnectionException("UsageRecord read failed: Unable to connect to server");
        } else if (!TwilioRestClient.SUCCESS.test(response.getStatusCode())) {
            RestException restException = RestException.fromJson(response.getStream(), client.getObjectMapper());
            if (restException == null) {
                throw new ApiException("Server Error, no content");
            }
           throw new ApiException(restException);
        }

        return Page.fromJson(
            "usage_records",
            response.getContent(),
            UsageRecord.class,
            client.getObjectMapper()
        );
    }

    /**
     * Add the requested query string arguments to the Request.
     *
     * @param request Request to add query string arguments to
     */
    private void addQueryParams(final Request request) {
        if (end != null) {
            request.addQueryParam("End", end.toString());
        }

        if (start != null) {
            request.addQueryParam("Start", start.toString());
        }

        if (granularity != null) {
            request.addQueryParam("Granularity", granularity.toString());
        }

        if (getPageSize() != null) {
            request.addQueryParam("PageSize", Integer.toString(getPageSize()));
        }
    }
}