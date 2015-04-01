package com.twilio.sdk.readers;

import com.google.common.collect.Range;
import com.twilio.sdk.clients.TwilioRestClient;
import com.twilio.sdk.exceptions.ApiException;
import com.twilio.sdk.http.HttpMethod;
import com.twilio.sdk.http.Request;
import com.twilio.sdk.http.Response;
import com.twilio.sdk.resources.Call;
import com.twilio.sdk.resources.Page;
import com.twilio.sdk.resources.ResourceSet;
import com.twilio.sdk.resources.RestException;
import org.joda.time.DateTime;

public class CallReader extends Reader<Call> {
    private String to;
    private String from;
    private String parentCallSid;
    private Call.Status status;
    private DateTime absoluteStartTime;
    private Range<DateTime> rangeStartTime;
    private DateTime absoluteEndTime;
    private Range<DateTime> rangeEndTime;

    /**
     * Only show calls to this phone number or Client identifier
     *
     * @param to Phone number or Client identifier to filter `to` on
     * @return this
     */
    public CallReader byTo(final String to) {
        this.to = to;
        return this;
    }

    /**
     * Only show calls from this phone number or Client identifier
     *
     * @param from Phone number or Client identifier to filter `from` on
     * @return this
     */
    public CallReader byFrom(final String from) {
        this.from = from;
        return this;
    }

    /**
     * Only show calls spawned by the call with this Sid
     *
     * @param parentCallSid Parent Call Sid to filter on
     * @return this
     */
    public CallReader byParentCallSid(final String parentCallSid) {
        this.parentCallSid = parentCallSid;
        return this;
    }

    /**
     * Only show calls currently in this status
     *
     * @param status Status to filter on
     * @return this
     */
    public CallReader byStatus(final Call.Status status) {
        this.status = status;
        return this;
    }

    /**
     * Only show calls that started on this date
     *
     * @param absoluteStartTime StartTime to filter on
     * @return this
     */
    public CallReader byStartTime(final DateTime absoluteStartTime) {
        this.rangeStartTime = null;
        this.absoluteStartTime = absoluteStartTime;
        return this;
    }

    /**
     * Only show calls that started within the range
     *
     * @param rangeStartTime Range to filter with
     * @return this
     */
    public CallReader byStartTime(final Range<DateTime> rangeStartTime) {
        this.absoluteStartTime = null;
        this.rangeStartTime = rangeStartTime;
        return this;
    }

    /**
     * Only show call that ended on this date
     *
     * @param absoluteEndTime EndTime to filter on
     * @return this
     */
    public CallReader byEndTime(final DateTime absoluteEndTime) {
        this.rangeEndTime = null;
        this.absoluteEndTime = absoluteEndTime;
        return this;
    }

    /**
     * Only show calls that ended within the Range
     *
     * @param rangeEndTime Range to filter with
     * @return this
     */
    public CallReader byEndTime(final Range<DateTime> rangeEndTime) {
        this.absoluteEndTime = null;
        this.rangeEndTime = rangeEndTime;
        return this;
    }

    /**
     * Make the request to the Twilio API to perform the read
     *
     * @param client TwilioRestClient with which to make the request
     * @return Call ResourceSet
     */
    @Override
    public ResourceSet<Call> execute(final TwilioRestClient client) {
        Request request = new Request(
            HttpMethod.GET,
            "/2010-04-01/Accounts/{AccountSid}/Calls.json",
            client.getAccountSid()
        );

        addQueryParams(request);

        Page<Call> page = pageForRequest(client, request);

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
    public Page<Call> nextPage(final String nextPageUri, final TwilioRestClient client) {
        Request request = new Request(
            HttpMethod.GET,
            nextPageUri,
            client.getAccountSid()
        );
        return pageForRequest(client, request);
    }

    /**
     * Generate a Page of Call Resources for a given request
     *
     * @param client TwilioRestClient with which to make the request
     * @param request Request to generate a page for
     * @return Page for the Request
     */
    protected Page<Call> pageForRequest(final TwilioRestClient client, final Request request) {
        Response response = client.request(request);

        if (response.getStatusCode() != TwilioRestClient.HTTP_STATUS_CODE_OK) {
            RestException restException = RestException.fromJson(response.getStream(), client.getObjectMapper());
            throw new ApiException(
                restException.getMessage(),
                restException.getCode(),
                restException.getMoreInfo(),
                restException.getStatus(),
                null
            );
        }

        Page<Call> result = new Page<>();
        result.deserialize("calls", response.getContent(), Call.class, client.getObjectMapper());

        return result;
    }

    /**
     * Add the requested query string arguments to the Request
     *
     * @param request Request to add query string arguments to
     */
    private void addQueryParams(final Request request) {
        if (to != null) {
            request.addQueryParam("To", to);
        }

        if (from != null) {
            request.addQueryParam("From", from);
        }

        if (parentCallSid != null) {
            request.addQueryParam("ParentCallSid", parentCallSid);
        }

        if (status != null) {
            request.addQueryParam("Status", status.toString());
        }

        if (absoluteStartTime != null) {
            request.addQueryParam("StartTime", absoluteStartTime.toString(Request.QUERY_STRING_DATE_FORMAT));
        } else if (rangeStartTime != null) {
            request.addQueryDateRange("StartTime", rangeStartTime);
        }

        if (absoluteEndTime != null) {
            request.addQueryParam("EndTime", absoluteEndTime.toString(Request.QUERY_STRING_DATE_FORMAT));
        } else if (rangeEndTime != null) {
            request.addQueryDateRange("EndTime", rangeEndTime);
        }

        request.addQueryParam("PageSize", Integer.toString(getPageSize()));
    }
}
