/**
 * This code was generated by
 * \ / _    _  _|   _  _
 *  | (_)\/(_)(_|\/| |(/_  v1.0.0
 *       /       /       
 */

package com.twilio.rest.api.v2010.account.call;

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
import org.joda.time.LocalDate;

public class NotificationReader extends Reader<Notification> {
    private String accountSid;
    private final String callSid;
    private Integer log;
    private LocalDate absoluteMessageDate;
    private Range<LocalDate> rangeMessageDate;

    /**
     * Construct a new NotificationReader.
     * 
     * @param callSid The call_sid
     */
    public NotificationReader(final String callSid) {
        this.callSid = callSid;
    }

    /**
     * Construct a new NotificationReader.
     * 
     * @param accountSid The account_sid
     * @param callSid The call_sid
     */
    public NotificationReader(final String accountSid, 
                              final String callSid) {
        this.accountSid = accountSid;
        this.callSid = callSid;
    }

    /**
     * The log.
     * 
     * @param log The log
     * @return this
     */
    public NotificationReader setLog(final Integer log) {
        this.log = log;
        return this;
    }

    /**
     * The absolute_message_date.
     * 
     * @param absoluteMessageDate The absolute_message_date
     * @return this
     */
    public NotificationReader setMessageDate(final LocalDate absoluteMessageDate) {
        this.rangeMessageDate = null;
        this.absoluteMessageDate = absoluteMessageDate;
        return this;
    }

    /**
     * The range_message_date.
     * 
     * @param rangeMessageDate The range_message_date
     * @return this
     */
    public NotificationReader setMessageDate(final Range<LocalDate> rangeMessageDate) {
        this.absoluteMessageDate = null;
        this.rangeMessageDate = rangeMessageDate;
        return this;
    }

    /**
     * Make the request to the Twilio API to perform the read.
     * 
     * @param client TwilioRestClient with which to make the request
     * @return Notification ResourceSet
     */
    @Override
    public ResourceSet<Notification> read(final TwilioRestClient client) {
        return new ResourceSet<>(this, client, firstPage(client));
    }

    /**
     * Make the request to the Twilio API to perform the read.
     * 
     * @param client TwilioRestClient with which to make the request
     * @return Notification ResourceSet
     */
    @Override
    @SuppressWarnings("checkstyle:linelength")
    public Page<Notification> firstPage(final TwilioRestClient client) {
        this.accountSid = this.accountSid == null ? client.getAccountSid() : this.accountSid;
        Request request = new Request(
            HttpMethod.GET,
            Domains.API.toString(),
            "/2010-04-01/Accounts/" + this.accountSid + "/Calls/" + this.callSid + "/Notifications.json",
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
    public Page<Notification> nextPage(final Page<Notification> page, 
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
     * Generate a Page of Notification Resources for a given request.
     * 
     * @param client TwilioRestClient with which to make the request
     * @param request Request to generate a page for
     * @return Page for the Request
     */
    private Page<Notification> pageForRequest(final TwilioRestClient client, final Request request) {
        Response response = client.request(request);
        
        if (response == null) {
            throw new ApiConnectionException("Notification read failed: Unable to connect to server");
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
            "notifications",
            response.getContent(),
            Notification.class,
            client.getObjectMapper()
        );
    }

    /**
     * Add the requested query string arguments to the Request.
     * 
     * @param request Request to add query string arguments to
     */
    private void addQueryParams(final Request request) {
        if (log != null) {
            request.addQueryParam("Log", log.toString());
        }
        
        if (absoluteMessageDate != null) {
            request.addQueryParam("MessageDate", absoluteMessageDate.toString(Request.QUERY_STRING_DATE_FORMAT));
        } else if (rangeMessageDate != null) {
            request.addQueryDateRange("MessageDate", rangeMessageDate);
        }
        
        if (getPageSize() != null) {
            request.addQueryParam("PageSize", Integer.toString(getPageSize()));
        }
    }
}