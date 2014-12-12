package com.twilio.sdk.readers;

import com.google.common.collect.Range;
import com.twilio.sdk.clients.TwilioRestClient;
import com.twilio.sdk.exceptions.ApiException;
import com.twilio.sdk.http.HttpMethod;
import com.twilio.sdk.http.Request;
import com.twilio.sdk.http.Response;
import com.twilio.sdk.resources.Message;
import com.twilio.sdk.resources.Page;
import com.twilio.sdk.resources.ResourceSet;
import com.twilio.sdk.resources.RestException;
import org.joda.time.DateTime;

public class MessageReader extends Reader<Message> {

    private DateTime absoluteDateSent;
    private Range<DateTime> rangeDateSent;
    private String to;
    private String from;

    @Override
    public ResourceSet<Message> execute(final TwilioRestClient client) {
        Request request = new Request(HttpMethod.GET, "/2010-04-01/Accounts/{AccountSid}/Messages.json", client.getAccountSid());
        addQueryParams(request);

        Page<Message> page = pageForRequest(client, request);

        return new ResourceSet<>(this, client, page);
    }

    @Override
    public Page<Message> nextPage(final String nextPageUri, final TwilioRestClient client) {
        Request request = new Request(HttpMethod.GET, nextPageUri, client.getAccountSid());
        return pageForRequest(client, request);
    }

    protected Page<Message> pageForRequest(final TwilioRestClient client, final Request request) {
        Response response = client.request(request);

        if (response.getStatusCode() != TwilioRestClient.HTTP_STATUS_CODE_OK) {
            RestException restException = RestException.fromJson(response.getStream(), client.getObjectMapper());
            throw new ApiException(restException.getMessage(), restException.getCode(), restException.getMoreInfo(),
                                   restException.getStatus(), null);
        }

        Page<Message> result = new Page<>();
        result.deserialize("sms_messages", response.getContent(), Message.class, client.getObjectMapper());

        return result;
    }

    public MessageReader byDateSent(final Range<DateTime> dateSentRange) {
        absoluteDateSent = null;
        rangeDateSent = dateSentRange;
        return this;
    }

    public MessageReader byDateSent(final DateTime dateSent) {
        rangeDateSent = null;
        absoluteDateSent = dateSent;
        return this;
    }

    public MessageReader byTo(final String to) {
        this.to = to;
        return this;
    }

    public MessageReader byFrom(final String from) {
        this.from = from;
        return this;
    }

    private void addQueryParams(final Request request) {
        if (absoluteDateSent != null) {
            request.addQueryParam("DateSent", absoluteDateSent.toString(Request.QUERY_STRING_DATE_FORMAT));
        } else if (rangeDateSent != null) {
            request.addQueryDateRange("DateSent", rangeDateSent);
        }
        if (to != null) {
            request.addQueryParam("To", to);
        }
        if (from != null) {
            request.addQueryParam("From", from);
        }
    }
}