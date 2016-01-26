package com.twilio.sdk.readers.api.v2010.account.call;

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
import com.twilio.sdk.resources.api.v2010.account.call.Notification;

public class NotificationReader extends Reader<Notification> {
    private final String accountSid;
    private final String callSid;
    private Integer log;
    private String messageDate;

    /**
     * Construct a new NotificationReader
     * 
     * @param accountSid The account_sid
     * @param callSid The call_sid
     */
    public NotificationReader(final String accountSid, final String callSid) {
        this.accountSid = accountSid;
        this.callSid = callSid;
    }

    /**
     * The log
     * 
     * @param log The log
     * @return this
     */
    public NotificationReader byLog(final Integer log) {
        this.log = log;
        return this;
    }

    /**
     * The message_date
     * 
     * @param messageDate The message_date
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
            "/2010-04-01/Accounts/" + this.accountSid + "/Calls/" + this.callSid + "/Notifications.json",
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