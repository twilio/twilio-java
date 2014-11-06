package com.twilio.sdk.readers;

import com.google.common.collect.Range;
import com.twilio.sdk.clients.TwilioRestClient;
import com.twilio.sdk.http.HttpMethod;
import com.twilio.sdk.http.Request;
import com.twilio.sdk.http.Response;
import com.twilio.sdk.resources.Call;
import com.twilio.sdk.resources.Page;
import com.twilio.sdk.resources.ResourceSet;
import org.joda.time.LocalDate;

public class CallReader extends SidReader<Call> {
    private String to;
    private String from;
    private String parentCallSid;
    private String status;
    private LocalDate absoluteStartTime;
    private Range<LocalDate> rangeStartTime;
    private LocalDate absoluteEndTime;
    private Range<LocalDate> rangeEndTime;

    @Override
    public ResourceSet<Call> execute(final TwilioRestClient client) {
        Request request = new Request(HttpMethod.GET, "/Accounts/{AccountSid}/Calls.json");
        addQueryParams(request);

        Page<Call> page = pageForRequest(client, request);

        return new ResourceSet<>(this, client, page);
    }

    @Override
    public Call fetch(final String sid, final TwilioRestClient client) {
        Request request = new Request(HttpMethod.GET, "/Accounts/{AccountSid}/Calls/" + sid + ".json");
        Response response = client.request(request);

        if (response.getStatusCode() != 200) {
            throw new RuntimeException("Unable to find Call for Sid " + sid);
        }

        return Call.fromJson(response.getStream());
    }

    @Override
    public Page<Call> nextPage(final String nextPageUri, final TwilioRestClient client) {
        Request request = new Request(HttpMethod.GET, nextPageUri);
        return pageForRequest(client, request);
    }

    protected Page<Call> pageForRequest(final TwilioRestClient client, final Request request) {
        Response response = client.request(request);

        if (response.getStatusCode() != 200) {
            throw new RuntimeException("Unable to build page of Calls");
        }

        Page<Call> result = new Page<>();
        result.deserialize("calls", response.getContent(), Call.class);

        return result;
    }

    public CallReader byTo(final String to) {
        this.to = to;
        return this;
    }

    public CallReader byFrom(final String from) {
        this.from = from;
        return this;
    }

    public CallReader byParentCallSid(final String parentCallSid) {
        this.parentCallSid = parentCallSid;
        return this;
    }

    public CallReader byStatus(final String status) {
        this.status = status;
        return this;
    }

    public CallReader byStartTime(final LocalDate time) {
        absoluteStartTime = time;
        rangeStartTime = null;
        return this;
    }

    public CallReader byStartTime(final Range<LocalDate> timeRange) {
        absoluteStartTime = null;
        rangeStartTime = timeRange;
        return this;
    }

    public CallReader byEndTime(final LocalDate time) {
        absoluteEndTime = time;
        rangeEndTime = null;
        return this;
    }

    public CallReader byEndTime(final Range<LocalDate> timeRange) {
        absoluteEndTime = null;
        rangeEndTime = timeRange;
        return this;
    }

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
            request.addQueryParam("Status", status);
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
