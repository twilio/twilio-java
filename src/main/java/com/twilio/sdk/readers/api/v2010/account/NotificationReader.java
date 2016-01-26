package com.twilio.sdk.readers.api.v2010.account;

import com.twilio.sdk.clients.TwilioRestClient;
import com.twilio.sdk.exceptions.ApiConnectionException;
import com.twilio.sdk.exceptions.ApiException;
import com.twilio.sdk.http.HttpMethod;
import com.twilio.sdk.http.Request;
import com.twilio.sdk.http.Response;
import com.twilio.sdk.readers.Reader;
import com.twilio.sdk.resources.Page;
import com.twilio.sdk.resources.ResourceSet;
import com.twilio.sdk.resources.RestException;
import com.twilio.sdk.resources.api.v2010.account.Notification;

public class NotificationReader extends Reader<Notification> {
    private final String accountSid;
    private Integer log;
    private String messageDate;

    /**
     * Construct a new NotificationReader
     * 
     * @param accountSid The account_sid
     */
    public NotificationReader(final String accountSid) {
        this.accountSid = accountSid;
    }

    /**
     * Only show notifications for this log level
     * 
     * @param log Filter by log level
     * @return this
     */
    public NotificationReader byLog(final Integer log) {
        this.log = log;
        return this;
    }

    /**
     * Only show notifications for this date. Should be formatted as YYYY-MM-DD. You
     * can also specify inequalities.
     * 
     * @param messageDate Filter by date
     * @return this
     */
    public NotificationReader byMessageDate(final String messageDate) {
        this.messageDate = messageDate;
        return this;
    }

    /**
     * Make the request to the Twilio API to perform the read
     * 
     * @param client TwilioRestClient with which to make the request
     * @return Notification ResourceSet
     */
    @Override
    public ResourceSet<Notification> execute(final TwilioRestClient client) {
        Request request = new Request(
            HttpMethod.GET,
            TwilioRestClient.Domains.API,
            "/2010-04-01/Accounts/" + this.accountSid + "/Notifications.json",
            client.getAccountSid()
        );
        
        addQueryParams(request);
        
        Page<Notification> page = pageForRequest(client, request);
        
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
    public Page<Notification> nextPage(final String nextPageUri, final TwilioRestClient client) {
        Request request = new Request(
            HttpMethod.GET,
            nextPageUri,
            client.getAccountSid()
        );
        return pageForRequest(client, request);
    }

    /**
     * Generate a Page of Notification Resources for a given request
     * 
     * @param client TwilioRestClient with which to make the request
     * @param request Request to generate a page for
     * @return Page for the Request
     */
    protected Page<Notification> pageForRequest(final TwilioRestClient client, final Request request) {
        Response response = client.request(request);
        
        if (response == null) {
            throw new ApiConnectionException("Notification read failed: Unable to connect to server");
        } else if (response.getStatusCode() != TwilioRestClient.HTTP_STATUS_CODE_OK) {
            RestException restException = RestException.fromJson(response.getStream(), client.getObjectMapper());
            throw new ApiException(
                restException.getMessage(),
                restException.getCode(),
                restException.getMoreInfo(),
                restException.getStatus(),
                null
            );
        }
        
        Page<Notification> result = new Page<>();
        result.deserialize("notifications", response.getContent(), Notification.class, client.getObjectMapper());
        
        return result;
    }

    /**
     * Add the requested query string arguments to the Request
     * 
     * @param request Request to add query string arguments to
     */
    private void addQueryParams(final Request request) {
        if (log != null) {
            request.addQueryParam("Log", log.toString());
        }
        
        if (messageDate != null) {
            request.addQueryParam("MessageDate", messageDate);
        }
        
        request.addQueryParam("PageSize", Integer.toString(getPageSize()));
    }
}