/**
 * This code was generated by
 * \ / _    _  _|   _  _
 *  | (_)\/(_)(_|\/| |(/_  v1.0.0
 *       /       /
 */

package com.twilio.rest.api.v2010.account;

import com.google.common.collect.Range;
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

import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

public class RecordingReader extends Reader<Recording> {
    private String pathAccountSid;
    private ZonedDateTime startDateCreated;
    private ZonedDateTime endDateCreated;
    private String callSid;
    private String conferenceSid;

    /**
     * Construct a new RecordingReader.
     */
    public RecordingReader() {
    }

    /**
     * Construct a new RecordingReader.
     *
     * @param pathAccountSid The SID of the Account that created the resources to
     *                       read
     */
    public RecordingReader(final String pathAccountSid) {
        this.pathAccountSid = pathAccountSid;
    }

    /**
     * Only include recordings that were created on this date. Specify a date as
     * `YYYY-MM-DD` in GMT, for example: `2009-07-06`, to read recordings that were
     * created on this date. You can also specify an inequality, such as
     * `DateCreated&lt;=YYYY-MM-DD`, to read recordings that were created on or
     * before midnight of this date, and `DateCreated&gt;=YYYY-MM-DD` to read
     * recordings that were created on or after midnight of this date..
     *
     * @param startDateCreated Only include recordings that were created on this
     *                         date
     * @return this
     */
    public RecordingReader setStartDateCreated(final ZonedDateTime startDateCreated) {
        this.startDateCreated = startDateCreated;
        return this;
    }

    /**
     * Only include recordings that were created on this date. Specify a date as
     * `YYYY-MM-DD` in GMT, for example: `2009-07-06`, to read recordings that were
     * created on this date. You can also specify an inequality, such as
     * `DateCreated&lt;=YYYY-MM-DD`, to read recordings that were created on or
     * before midnight of this date, and `DateCreated&gt;=YYYY-MM-DD` to read
     * recordings that were created on or after midnight of this date..
     *
     * @param endDateCreated Only include recordings that were created on this
     *                       date
     * @return this
     */
    public RecordingReader setEndDateCreated(final ZonedDateTime endDateCreated) {
        this.endDateCreated = endDateCreated;
        return this;
    }

    /**
     * The <a href="https://www.twilio.com/docs/voice/api/call-resource">Call</a>
     * SID of the resources to read..
     *
     * @param callSid The Call SID of the resources to read
     * @return this
     */
    public RecordingReader setCallSid(final String callSid) {
        this.callSid = callSid;
        return this;
    }

    /**
     * The Conference SID that identifies the conference associated with the
     * recording to read..
     *
     * @param conferenceSid Read by unique Conference SID for the recording
     * @return this
     */
    public RecordingReader setConferenceSid(final String conferenceSid) {
        this.conferenceSid = conferenceSid;
        return this;
    }

    /**
     * Make the request to the Twilio API to perform the read.
     *
     * @param client TwilioRestClient with which to make the request
     * @return Recording ResourceSet
     */
    @Override
    public ResourceSet<Recording> read(final TwilioRestClient client) {
        return new ResourceSet<>(this, client, firstPage(client));
    }

    /**
     * Make the request to the Twilio API to perform the read.
     *
     * @param client TwilioRestClient with which to make the request
     * @return Recording ResourceSet
     */
    @Override
    @SuppressWarnings("checkstyle:linelength")
    public Page<Recording> firstPage(final TwilioRestClient client) {
        this.pathAccountSid = this.pathAccountSid == null ? client.getAccountSid() : this.pathAccountSid;
        Request request = new Request(
            HttpMethod.GET,
            Domains.API.toString(),
            "/2010-04-01/Accounts/" + this.pathAccountSid + "/Recordings.json"
        );

        addQueryParams(request);
        return pageForRequest(client, request);
    }

    /**
     * Retrieve the target page from the Twilio API.
     *
     * @param targetUrl API-generated URL for the requested results page
     * @param client TwilioRestClient with which to make the request
     * @return Recording ResourceSet
     */
    @Override
    @SuppressWarnings("checkstyle:linelength")
    public Page<Recording> getPage(final String targetUrl, final TwilioRestClient client) {
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
    public Page<Recording> nextPage(final Page<Recording> page,
                                    final TwilioRestClient client) {
        Request request = new Request(
            HttpMethod.GET,
            page.getNextPageUrl(Domains.API.toString())
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
    public Page<Recording> previousPage(final Page<Recording> page,
                                        final TwilioRestClient client) {
        Request request = new Request(
            HttpMethod.GET,
            page.getPreviousPageUrl(Domains.API.toString())
        );
        return pageForRequest(client, request);
    }

    /**
     * Generate a Page of Recording Resources for a given request.
     *
     * @param client TwilioRestClient with which to make the request
     * @param request Request to generate a page for
     * @return Page for the Request
     */
    private Page<Recording> pageForRequest(final TwilioRestClient client, final Request request) {
        Response response = client.request(request);

        if (response == null) {
            throw new ApiConnectionException("Recording read failed: Unable to connect to server");
        } else if (!TwilioRestClient.SUCCESS.test(response.getStatusCode())) {
            RestException restException = RestException.fromJson(response.getStream(), client.getObjectMapper());
            if (restException == null) {
                throw new ApiException("Server Error, no content");
            }
           throw new ApiException(restException);
        }

        return Page.fromJson(
            "recordings",
            response.getContent(),
            Recording.class,
            client.getObjectMapper()
        );
    }

    /**
     * Add the requested query string arguments to the Request.
     *
     * @param request Request to add query string arguments to
     */
    private void addQueryParams(final Request request) {
        if (startDateCreated != null || endDateCreated != null) {
            request.addQueryDateTimeRange("DateCreated", startDateCreated, endDateCreated);
        }

        if (callSid != null) {
            request.addQueryParam("CallSid", callSid);
        }

        if (conferenceSid != null) {
            request.addQueryParam("ConferenceSid", conferenceSid);
        }

        if (getPageSize() != null) {
            request.addQueryParam("PageSize", Integer.toString(getPageSize()));
        }
    }
}
