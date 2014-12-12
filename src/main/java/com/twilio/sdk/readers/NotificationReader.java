package com.twilio.sdk.readers;

import com.google.common.collect.Range;
import com.twilio.sdk.clients.TwilioRestClient;
import com.twilio.sdk.exceptions.ApiException;
import com.twilio.sdk.http.HttpMethod;
import com.twilio.sdk.http.Request;
import com.twilio.sdk.http.Response;
import com.twilio.sdk.resources.Notification;
import com.twilio.sdk.resources.Page;
import com.twilio.sdk.resources.ResourceSet;
import com.twilio.sdk.resources.RestException;
import org.joda.time.DateTime;

public class NotificationReader extends Reader<Notification> {

    private DateTime absoluteMessageDate;
    private Range<DateTime> rangeMessageDate;
    private Integer log;

    @Override
    public ResourceSet<Notification> execute(final TwilioRestClient client) {
        Request request = new Request(HttpMethod.GET, "/2010-04-01/Accounts/{AccountSid}/Notifications.json", client.getAccountSid());
        addQueryParams(request);

        Page<Notification> page = pageForRequest(client, request);

        return new ResourceSet<>(this, client, page);
    }

    @Override
    public Page<Notification> nextPage(final String nextPageUri, final TwilioRestClient client) {
        Request request = new Request(HttpMethod.GET, nextPageUri, client.getAccountSid());
        return pageForRequest(client, request);
    }

    protected Page<Notification> pageForRequest(final TwilioRestClient client, final Request request) {
        Response response = client.request(request);

        if (response.getStatusCode() != TwilioRestClient.HTTP_STATUS_CODE_OK) {
            RestException restException = RestException.fromJson(response.getStream(), client.getObjectMapper());
            throw new ApiException(restException.getMessage(), restException.getCode(), restException.getMoreInfo(),
                                   restException.getStatus(), null);
        }

        Page<Notification> result = new Page<>();
        result.deserialize("notifications", response.getContent(), Notification.class, client.getObjectMapper());

        return result;
    }

    public NotificationReader byMessageDate(final Range<DateTime> messageDateRange) {
        absoluteMessageDate = null;
        rangeMessageDate = messageDateRange;
        return this;
    }

    public NotificationReader byMessageDate(final DateTime messageDate) {
        rangeMessageDate = null;
        absoluteMessageDate = messageDate;
        return this;
    }

    public NotificationReader byLog(final Integer log) {
        this.log = log;
        return this;
    }

    private void addQueryParams(final Request request) {
        if (absoluteMessageDate != null) {
            request.addQueryParam("MessageDate", absoluteMessageDate.toString(Request.QUERY_STRING_DATE_FORMAT));
        } else if (rangeMessageDate != null) {
            request.addQueryDateRange("MessageDate", rangeMessageDate);
        }
        if (log != null) {
            request.addQueryParam("Log", log.toString());
        }
    }
}