package com.twilio.sdk.locators;

import com.google.common.collect.Range;
import com.twilio.sdk.clients.TwilioRestClient;
import com.twilio.sdk.http.Request;
import com.twilio.sdk.http.Response;
import com.twilio.sdk.resources.Call;
import org.joda.time.LocalDate;

import java.util.ArrayList;
import java.util.List;

public class CallLocator extends Locator<Call> {
    private String to;
    private String from;
    private String parentCallSid;
    private String status;
    private LocalDate absoluteStartTime;
    private Range<LocalDate> rangeStartTime;

    @Override
    public List<Call> build(final TwilioRestClient client) {
        Request request = new Request("GET", "/Accounts/{AccountSid}/Calls");
        this.addQueryParams(request);
        Response response = client.request(request);

        if (response.getStatusCode() != 200) {
            throw new RuntimeException("Unable to build list of Calls");
        }



        return new ArrayList<Call>();
    }

    @Override
    public Call buildBySid(String sid, final TwilioRestClient client) {
        Request request = new Request("GET", "/Accounts/{AccountSid}/Calls/" + sid);
        Response response = client.request(request);

        if (response.getStatusCode() != 200) {
            throw new RuntimeException("Unable to find Call for Sid " + sid);
        }

        return Call.fromJson(response.getStream());
    }

    public CallLocator byTo(String to) {
        this.to = to;
        return this;
    }

    public CallLocator byFrom(String from) {
        this.from = from;
        return this;
    }

    public CallLocator byParentCallSid(String parentCallSid) {
        this.parentCallSid = parentCallSid;
        return this;
    }

    public CallLocator byStatus(String status) {
        this.status = status;
        return this;
    }

    public CallLocator byStartTime(LocalDate time) {
        this.absoluteStartTime = time;
        this.rangeStartTime = null;
        return this;
    }

    public CallLocator byStartTime(Range<LocalDate> timeRange) {
        this.absoluteStartTime = null;
        this.rangeStartTime = timeRange;
        return this;
    }

    private void addQueryParams(Request request) {
        if (this.to != null) {
            request.addQueryParam("To", this.to);
        }

        if (this.from != null) {
            request.addQueryParam("From", this.from);
        }

        if (this.parentCallSid != null) {
            request.addQueryParam("ParentCallSid", this.parentCallSid);
        }

        if (this.status != null) {
            request.addQueryParam("Status", this.status);
        }

        if (this.absoluteStartTime != null) {
            String value = this.absoluteStartTime.toString(Request.QUERY_STRING_DATE_FORMAT);
            request.addQueryParam("StartTime", value);
        }

        if (this.rangeStartTime != null) {
            request.addQueryDateRange("StartTime", this.rangeStartTime);
        }
    }

}
