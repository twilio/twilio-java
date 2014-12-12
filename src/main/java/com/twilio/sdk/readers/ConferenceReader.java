package com.twilio.sdk.readers;

import com.google.common.collect.Range;
import com.twilio.sdk.clients.TwilioRestClient;
import com.twilio.sdk.exceptions.ApiException;
import com.twilio.sdk.http.HttpMethod;
import com.twilio.sdk.http.Request;
import com.twilio.sdk.http.Response;
import com.twilio.sdk.resources.Conference;
import com.twilio.sdk.resources.Page;
import com.twilio.sdk.resources.ResourceSet;
import com.twilio.sdk.resources.RestException;
import org.joda.time.DateTime;

public class ConferenceReader extends Reader<Conference> {

    private Conference.Status status;
    private DateTime absoluteDateCreated;
    private Range<DateTime> rangeDateCreated;
    private String friendlyName;
    private DateTime absoluteDateUpdated;
    private Range<DateTime> rangeDateUpdated;

    @Override
    public ResourceSet<Conference> execute(final TwilioRestClient client) {
        Request request = new Request(HttpMethod.GET, "/2010-04-01/Accounts/{AccountSid}/Conferences.json", client.getAccountSid());
        addQueryParams(request);

        Page<Conference> page = pageForRequest(client, request);

        return new ResourceSet<>(this, client, page);
    }

    @Override
    public Page<Conference> nextPage(final String nextPageUri, final TwilioRestClient client) {
        Request request = new Request(HttpMethod.GET, nextPageUri, client.getAccountSid());
        return pageForRequest(client, request);
    }

    protected Page<Conference> pageForRequest(final TwilioRestClient client, final Request request) {
        Response response = client.request(request);

        if (response.getStatusCode() != TwilioRestClient.HTTP_STATUS_CODE_OK) {
            RestException restException = RestException.fromJson(response.getStream(), client.getObjectMapper());
            throw new ApiException(restException.getMessage(), restException.getCode(), restException.getMoreInfo(),
                                   restException.getStatus(), null);
        }

        Page<Conference> result = new Page<>();
        result.deserialize("conferences", response.getContent(), Conference.class, client.getObjectMapper());

        return result;
    }

    public ConferenceReader byStatus(final Conference.Status status) {
        this.status = status;
        return this;
    }

    public ConferenceReader byDateCreated(final Range<DateTime> dateCreatedRange) {
        absoluteDateCreated = null;
        rangeDateCreated = dateCreatedRange;
        return this;
    }

    public ConferenceReader byDateCreated(final DateTime dateCreated) {
        rangeDateCreated = null;
        absoluteDateCreated = dateCreated;
        return this;
    }

    public ConferenceReader byFriendlyName(final String friendlyName) {
        this.friendlyName = friendlyName;
        return this;
    }

    public ConferenceReader byDateUpdated(final Range<DateTime> dateUpdatedRange) {
        absoluteDateUpdated = null;
        rangeDateUpdated = dateUpdatedRange;
        return this;
    }

    public ConferenceReader byDateUpdated(final DateTime dateUpdated) {
        rangeDateUpdated = null;
        absoluteDateUpdated = dateUpdated;
        return this;
    }

    private void addQueryParams(final Request request) {
        if (status != null) {
            request.addQueryParam("Status", status.toString());
        }
        if (absoluteDateCreated != null) {
            request.addQueryParam("DateCreated", absoluteDateCreated.toString(Request.QUERY_STRING_DATE_FORMAT));
        } else if (rangeDateCreated != null) {
            request.addQueryDateRange("DateCreated", rangeDateCreated);
        }
        if (friendlyName != null) {
            request.addQueryParam("FriendlyName", friendlyName);
        }
        if (absoluteDateUpdated != null) {
            request.addQueryParam("DateUpdated", absoluteDateUpdated.toString(Request.QUERY_STRING_DATE_FORMAT));
        } else if (rangeDateUpdated != null) {
            request.addQueryDateRange("DateUpdated", rangeDateUpdated);
        }
    }
}