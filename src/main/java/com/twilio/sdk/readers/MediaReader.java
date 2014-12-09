package com.twilio.sdk.readers;

import com.google.common.collect.Range;
import com.twilio.sdk.clients.TwilioRestClient;
import com.twilio.sdk.exceptions.ApiException;
import com.twilio.sdk.http.HttpMethod;
import com.twilio.sdk.http.Request;
import com.twilio.sdk.http.Response;
import com.twilio.sdk.resources.Media;
import com.twilio.sdk.resources.Message;
import com.twilio.sdk.resources.Page;
import com.twilio.sdk.resources.ResourceSet;
import com.twilio.sdk.resources.RestException;
import org.joda.time.DateTime;

public class MediaReader extends Reader<Media> {

    private final String messageSid;
    private DateTime absoluteDateCreated;
    private Range<DateTime> rangeDateCreated;

    public MediaReader(final String messageSid) {
        this.messageSid = messageSid;
    }

    public MediaReader(final Message target) {
        this(target.getSid());
    }

    @Override
    public ResourceSet<Media> execute(final TwilioRestClient client) {
        Request request = new Request(HttpMethod.GET, "/Accounts/{AccountSid}/Messages/" + messageSid + "/Media.json",
                                      client.getAccountSid());
        addQueryParams(request);

        Page<Media> page = pageForRequest(client, request);

        return new ResourceSet<>(this, client, page);
    }

    @Override
    public Page<Media> nextPage(final String nextPageUri, final TwilioRestClient client) {
        Request request = new Request(HttpMethod.GET, nextPageUri, client.getAccountSid());
        return pageForRequest(client, request);
    }

    protected Page<Media> pageForRequest(final TwilioRestClient client, final Request request) {
        Response response = client.request(request);

        if (response.getStatusCode() != TwilioRestClient.HTTP_STATUS_CODE_OK) {
            RestException restException = RestException.fromJson(response.getStream(), client.getObjectMapper());
            throw new ApiException(restException.getMessage(), restException.getCode(), restException.getMoreInfo(),
                                   restException.getStatus(), null);
        }

        Page<Media> result = new Page<>();
        result.deserialize("media_list", response.getContent(), Media.class, client.getObjectMapper());

        return result;
    }

    public MediaReader byDateCreated(final Range<DateTime> dateCreatedRange) {
        absoluteDateCreated = null;
        rangeDateCreated = dateCreatedRange;
        return this;
    }

    public MediaReader byDateCreated(final DateTime dateCreated) {
        rangeDateCreated = null;
        absoluteDateCreated = dateCreated;
        return this;
    }

    private void addQueryParams(final Request request) {
        if (absoluteDateCreated != null) {
            request.addQueryParam("DateCreated", absoluteDateCreated.toString(Request.QUERY_STRING_DATE_FORMAT));
        } else if (rangeDateCreated != null) {
            request.addQueryDateRange("DateCreated", rangeDateCreated);
        }
    }
}
