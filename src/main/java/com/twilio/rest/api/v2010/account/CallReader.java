/**
 * This code was generated by
 * \ / _    _  _|   _  _
 *  | (_)\/(_)(_|\/| |(/_  v1.0.0
 *       /       /
 */

package com.twilio.rest.api.v2010.account;

import com.twilio.base.Page;
import com.twilio.base.Reader;
import com.twilio.base.ResourceSet;
import com.twilio.converter.DateConverter;
import com.twilio.converter.Promoter;
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

public class CallReader extends Reader<Call> {
    private String pathAccountSid;
    private com.twilio.type.PhoneNumber to;
    private com.twilio.type.PhoneNumber from;
    private String parentCallSid;
    private Call.Status status;
    private ZonedDateTime startTime;
    private ZonedDateTime startTimeBefore;
    private ZonedDateTime startTimeAfter;
    private ZonedDateTime endTime;
    private ZonedDateTime endTimeBefore;
    private ZonedDateTime endTimeAfter;

    /**
     * Construct a new CallReader.
     */
    public CallReader() {
    }

    /**
     * Construct a new CallReader.
     *
     * @param pathAccountSid The SID of the Account that created the resource(s) to
     *                       read
     */
    public CallReader(final String pathAccountSid) {
        this.pathAccountSid = pathAccountSid;
    }

    /**
     * Only show calls made to this phone number, SIP address, Client identifier or
     * SIM SID..
     *
     * @param to Phone number or Client identifier of calls to include
     * @return this
     */
    public CallReader setTo(final com.twilio.type.PhoneNumber to) {
        this.to = to;
        return this;
    }

    /**
     * Only show calls made to this phone number, SIP address, Client identifier or
     * SIM SID..
     *
     * @param to Phone number or Client identifier of calls to include
     * @return this
     */
    public CallReader setTo(final String to) {
        return setTo(Promoter.phoneNumberFromString(to));
    }

    /**
     * Only include calls from this phone number, SIP address, Client identifier or
     * SIM SID..
     *
     * @param from Phone number or Client identifier to filter `from` on
     * @return this
     */
    public CallReader setFrom(final com.twilio.type.PhoneNumber from) {
        this.from = from;
        return this;
    }

    /**
     * Only include calls from this phone number, SIP address, Client identifier or
     * SIM SID..
     *
     * @param from Phone number or Client identifier to filter `from` on
     * @return this
     */
    public CallReader setFrom(final String from) {
        return setFrom(Promoter.phoneNumberFromString(from));
    }

    /**
     * Only include calls spawned by calls with this SID..
     *
     * @param parentCallSid Parent call SID to filter on
     * @return this
     */
    public CallReader setParentCallSid(final String parentCallSid) {
        this.parentCallSid = parentCallSid;
        return this;
    }

    /**
     * The status of the calls to include. Can be: `queued`, `ringing`,
     * `in-progress`, `canceled`, `completed`, `failed`, `busy`, or `no-answer`..
     *
     * @param status The status of the resources to read
     * @return this
     */
    public CallReader setStatus(final Call.Status status) {
        this.status = status;
        return this;
    }

    /**
     * Only include calls that started on this date. Specify a date as `YYYY-MM-DD`
     * in GMT, for example: `2009-07-06`, to read only calls that started on this
     * date. You can also specify an inequality, such as `StartTime&lt;=YYYY-MM-DD`,
     * to read calls that started on or before midnight of this date, and
     * `StartTime&gt;=YYYY-MM-DD` to read calls that started on or after midnight of
     * this date..
     *
     * @param startTime Only include calls that started on this date
     * @return this
     */
    public CallReader setStartTime(final ZonedDateTime startTime) {
        this.startTimeBefore = null;
        this.startTimeAfter = null;
        this.startTime = startTime;
        return this;
    }

    /**
     * Only include calls that started on this date. Specify a date as `YYYY-MM-DD`
     * in GMT, for example: `2009-07-06`, to read only calls that started on this
     * date. You can also specify an inequality, such as `StartTime&lt;=YYYY-MM-DD`,
     * to read calls that started on or before midnight of this date, and
     * `StartTime&gt;=YYYY-MM-DD` to read calls that started on or after midnight of
     * this date..
     *
     * @param startTimeBefore Only include calls that started on this date
     * @return this
     */
    public CallReader setStartTimeBefore(final ZonedDateTime startTimeBefore) {
        this.startTime = null;
        this.startTimeBefore = startTimeBefore;
        return this;
    }

    /**
     * Only include calls that started on this date. Specify a date as `YYYY-MM-DD`
     * in GMT, for example: `2009-07-06`, to read only calls that started on this
     * date. You can also specify an inequality, such as `StartTime&lt;=YYYY-MM-DD`,
     * to read calls that started on or before midnight of this date, and
     * `StartTime&gt;=YYYY-MM-DD` to read calls that started on or after midnight of
     * this date..
     *
     * @param startTimeAfter Only include calls that started on this date
     * @return this
     */
    public CallReader setStartTimeAfter(final ZonedDateTime startTimeAfter) {
        this.startTime = null;
        this.startTimeAfter = startTimeAfter;
        return this;
    }

    /**
     * Only include calls that ended on this date. Specify a date as `YYYY-MM-DD` in
     * GMT, for example: `2009-07-06`, to read only calls that ended on this date.
     * You can also specify an inequality, such as `EndTime&lt;=YYYY-MM-DD`, to read
     * calls that ended on or before midnight of this date, and
     * `EndTime&gt;=YYYY-MM-DD` to read calls that ended on or after midnight of
     * this date..
     *
     * @param endTime Only include calls that ended on this date
     * @return this
     */
    public CallReader setEndTime(final ZonedDateTime endTime) {
        this.endTimeBefore = null;
        this.endTimeAfter = null;
        this.endTime = endTime;
        return this;
    }

    /**
     * Only include calls that ended on this date. Specify a date as `YYYY-MM-DD` in
     * GMT, for example: `2009-07-06`, to read only calls that ended on this date.
     * You can also specify an inequality, such as `EndTime&lt;=YYYY-MM-DD`, to read
     * calls that ended on or before midnight of this date, and
     * `EndTime&gt;=YYYY-MM-DD` to read calls that ended on or after midnight of
     * this date..
     *
     * @param endTimeBefore Only include calls that ended on this date
     * @return this
     */
    public CallReader setEndTimeBefore(final ZonedDateTime endTimeBefore) {
        this.endTime = null;
        this.endTimeBefore = endTimeBefore;
        return this;
    }

    /**
     * Only include calls that ended on this date. Specify a date as `YYYY-MM-DD` in
     * GMT, for example: `2009-07-06`, to read only calls that ended on this date.
     * You can also specify an inequality, such as `EndTime&lt;=YYYY-MM-DD`, to read
     * calls that ended on or before midnight of this date, and
     * `EndTime&gt;=YYYY-MM-DD` to read calls that ended on or after midnight of
     * this date..
     *
     * @param endTimeAfter Only include calls that ended on this date
     * @return this
     */
    public CallReader setEndTimeAfter(final ZonedDateTime endTimeAfter) {
        this.endTime = null;
        this.endTimeAfter = endTimeAfter;
        return this;
    }

    /**
     * Make the request to the Twilio API to perform the read.
     *
     * @param client TwilioRestClient with which to make the request
     * @return Call ResourceSet
     */
    @Override
    public ResourceSet<Call> read(final TwilioRestClient client) {
        return new ResourceSet<>(this, client, firstPage(client));
    }

    /**
     * Make the request to the Twilio API to perform the read.
     *
     * @param client TwilioRestClient with which to make the request
     * @return Call ResourceSet
     */
    @Override
    @SuppressWarnings("checkstyle:linelength")
    public Page<Call> firstPage(final TwilioRestClient client) {
        this.pathAccountSid = this.pathAccountSid == null ? client.getAccountSid() : this.pathAccountSid;
        Request request = new Request(
            HttpMethod.GET,
            Domains.API.toString(),
            "/2010-04-01/Accounts/" + this.pathAccountSid + "/Calls.json"
        );

        addQueryParams(request);
        return pageForRequest(client, request);
    }

    /**
     * Retrieve the target page from the Twilio API.
     *
     * @param targetUrl API-generated URL for the requested results page
     * @param client TwilioRestClient with which to make the request
     * @return Call ResourceSet
     */
    @Override
    @SuppressWarnings("checkstyle:linelength")
    public Page<Call> getPage(final String targetUrl, final TwilioRestClient client) {
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
    public Page<Call> nextPage(final Page<Call> page,
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
    public Page<Call> previousPage(final Page<Call> page,
                                   final TwilioRestClient client) {
        Request request = new Request(
            HttpMethod.GET,
            page.getPreviousPageUrl(Domains.API.toString())
        );
        return pageForRequest(client, request);
    }

    /**
     * Generate a Page of Call Resources for a given request.
     *
     * @param client TwilioRestClient with which to make the request
     * @param request Request to generate a page for
     * @return Page for the Request
     */
    private Page<Call> pageForRequest(final TwilioRestClient client, final Request request) {
        Response response = client.request(request);

        if (response == null) {
            throw new ApiConnectionException("Call read failed: Unable to connect to server");
        } else if (!TwilioRestClient.SUCCESS.test(response.getStatusCode())) {
            RestException restException = RestException.fromJson(response.getStream(), client.getObjectMapper());
            if (restException == null) {
                throw new ApiException("Server Error, no content");
            }
           throw new ApiException(restException);
        }

        return Page.fromJson(
            "calls",
            response.getContent(),
            Call.class,
            client.getObjectMapper()
        );
    }

    /**
     * Add the requested query string arguments to the Request.
     *
     * @param request Request to add query string arguments to
     */
    private void addQueryParams(final Request request) {
        if (to != null) {
            request.addQueryParam("To", to.toString());
        }

        if (from != null) {
            request.addQueryParam("From", from.toString());
        }

        if (parentCallSid != null) {
            request.addQueryParam("ParentCallSid", parentCallSid);
        }

        if (status != null) {
            request.addQueryParam("Status", status.toString());
        }

        if (startTime != null) {
            request.addQueryParam("StartTime", startTime.format(DateTimeFormatter.ofPattern(Request.QUERY_STRING_DATE_TIME_FORMAT)));
        } else if (startTimeAfter != null || startTimeBefore != null) {
            request.addQueryDateTimeRange("StartTime", startTimeAfter, startTimeBefore);
        }

        if (endTime != null) {
            request.addQueryParam("EndTime", endTime.format(DateTimeFormatter.ofPattern(Request.QUERY_STRING_DATE_TIME_FORMAT)));
        } else if (endTimeAfter != null || endTimeBefore != null) {
            request.addQueryDateTimeRange("EndTime", endTimeAfter, endTimeBefore);
        }

        if (getPageSize() != null) {
            request.addQueryParam("PageSize", Integer.toString(getPageSize()));
        }
    }
}