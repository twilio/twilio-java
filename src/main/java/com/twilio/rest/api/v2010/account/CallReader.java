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
import org.joda.time.DateTime;

public class CallReader extends Reader<Call> {
    private String accountSid;
    private com.twilio.type.PhoneNumber to;
    private com.twilio.type.PhoneNumber from;
    private String parentCallSid;
    private Call.Status status;
    private DateTime absoluteStartTime;
    private Range<DateTime> rangeStartTime;
    private DateTime absoluteEndTime;
    private Range<DateTime> rangeEndTime;

    /**
     * Construct a new CallReader.
     */
    public CallReader() {
    }

    /**
     * Construct a new CallReader.
     * 
     * @param accountSid The account_sid
     */
    public CallReader(final String accountSid) {
        this.accountSid = accountSid;
    }

    /**
     * Only show calls to this phone number or Client identifier.
     * 
     * @param to Phone number or Client identifier to filter `to` on
     * @return this
     */
    public CallReader setTo(final com.twilio.type.PhoneNumber to) {
        this.to = to;
        return this;
    }

    /**
     * Only show calls from this phone number or Client identifier.
     * 
     * @param from Phone number or Client identifier to filter `from` on
     * @return this
     */
    public CallReader setFrom(final com.twilio.type.PhoneNumber from) {
        this.from = from;
        return this;
    }

    /**
     * Only show calls spawned by the call with this Sid.
     * 
     * @param parentCallSid Parent Call Sid to filter on
     * @return this
     */
    public CallReader setParentCallSid(final String parentCallSid) {
        this.parentCallSid = parentCallSid;
        return this;
    }

    /**
     * Only show calls currently in this status.
     * 
     * @param status Status to filter on
     * @return this
     */
    public CallReader setStatus(final Call.Status status) {
        this.status = status;
        return this;
    }

    /**
     * Only show calls that started on this date.
     * 
     * @param absoluteStartTime StartTime to filter on
     * @return this
     */
    public CallReader setStartTime(final DateTime absoluteStartTime) {
        this.rangeStartTime = null;
        this.absoluteStartTime = absoluteStartTime;
        return this;
    }

    /**
     * Only show calls that started on this date.
     * 
     * @param rangeStartTime StartTime to filter on
     * @return this
     */
    public CallReader setStartTime(final Range<DateTime> rangeStartTime) {
        this.absoluteStartTime = null;
        this.rangeStartTime = rangeStartTime;
        return this;
    }

    /**
     * Only show call that ended on this date.
     * 
     * @param absoluteEndTime EndTime to filter on
     * @return this
     */
    public CallReader setEndTime(final DateTime absoluteEndTime) {
        this.rangeEndTime = null;
        this.absoluteEndTime = absoluteEndTime;
        return this;
    }

    /**
     * Only show call that ended on this date.
     * 
     * @param rangeEndTime EndTime to filter on
     * @return this
     */
    public CallReader setEndTime(final Range<DateTime> rangeEndTime) {
        this.absoluteEndTime = null;
        this.rangeEndTime = rangeEndTime;
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
        this.accountSid = this.accountSid == null ? client.getAccountSid() : this.accountSid;
        Request request = new Request(
            HttpMethod.GET,
            Domains.API.toString(),
            "/2010-04-01/Accounts/" + this.accountSid + "/Calls.json",
            client.getRegion()
        );
        
        addQueryParams(request);
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
            page.getNextPageUrl(
                Domains.API.toString(),
                client.getRegion()
            )
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
                null
            );
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
        
        if (absoluteStartTime != null) {
            request.addQueryParam("StartTime", absoluteStartTime.toString(Request.QUERY_STRING_DATE_TIME_FORMAT));
        } else if (rangeStartTime != null) {
            request.addQueryDateTimeRange("StartTime", rangeStartTime);
        }
        
        if (absoluteEndTime != null) {
            request.addQueryParam("EndTime", absoluteEndTime.toString(Request.QUERY_STRING_DATE_TIME_FORMAT));
        } else if (rangeEndTime != null) {
            request.addQueryDateTimeRange("EndTime", rangeEndTime);
        }
        
        if (getPageSize() != null) {
            request.addQueryParam("PageSize", Integer.toString(getPageSize()));
        }
    }
}