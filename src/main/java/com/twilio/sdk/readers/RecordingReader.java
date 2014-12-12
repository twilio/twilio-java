package com.twilio.sdk.readers;

import com.google.common.collect.Range;
import com.twilio.sdk.clients.TwilioRestClient;
import com.twilio.sdk.exceptions.ApiException;
import com.twilio.sdk.http.HttpMethod;
import com.twilio.sdk.http.Request;
import com.twilio.sdk.http.Response;
import com.twilio.sdk.resources.Page;
import com.twilio.sdk.resources.Recording;
import com.twilio.sdk.resources.ResourceSet;
import com.twilio.sdk.resources.RestException;
import org.joda.time.DateTime;

public class RecordingReader extends Reader<Recording> {

    private DateTime absoluteDateCreated;
    private Range<DateTime> rangeDateCreated;
    private String callSid;

    @Override
    public ResourceSet<Recording> execute(final TwilioRestClient client) {
        Request request = new Request(HttpMethod.GET, "/2010-04-01/Accounts/{AccountSid}/Recordings.json", client.getAccountSid());
        addQueryParams(request);

        Page<Recording> page = pageForRequest(client, request);

        return new ResourceSet<>(this, client, page);
    }

    @Override
    public Page<Recording> nextPage(final String nextPageUri, final TwilioRestClient client) {
        Request request = new Request(HttpMethod.GET, nextPageUri, client.getAccountSid());
        return pageForRequest(client, request);
    }

    protected Page<Recording> pageForRequest(final TwilioRestClient client, final Request request) {
        Response response = client.request(request);

        if (response.getStatusCode() != TwilioRestClient.HTTP_STATUS_CODE_OK) {
            RestException restException = RestException.fromJson(response.getStream(), client.getObjectMapper());
            throw new ApiException(restException.getMessage(), restException.getCode(), restException.getMoreInfo(),
                                   restException.getStatus(), null);
        }

        Page<Recording> result = new Page<>();
        result.deserialize("recordings", response.getContent(), Recording.class, client.getObjectMapper());

        return result;
    }

    public RecordingReader byDateCreated(final Range<DateTime> dateCreatedRange) {
        absoluteDateCreated = null;
        rangeDateCreated = dateCreatedRange;
        return this;
    }

    public RecordingReader byDateCreated(final DateTime dateCreated) {
        rangeDateCreated = null;
        absoluteDateCreated = dateCreated;
        return this;
    }

    public RecordingReader byCallSid(final String callSid) {
        this.callSid = callSid;
        return this;
    }

    private void addQueryParams(final Request request) {
        if (absoluteDateCreated != null) {
            request.addQueryParam("DateCreated", absoluteDateCreated.toString(Request.QUERY_STRING_DATE_FORMAT));
        } else if (rangeDateCreated != null) {
            request.addQueryDateRange("DateCreated", rangeDateCreated);
        }
        if (callSid != null) {
            request.addQueryParam("CallSid", callSid);
        }
    }
}