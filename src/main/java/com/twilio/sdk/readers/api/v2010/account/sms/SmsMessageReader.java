package com.twilio.sdk.readers.api.v2010.account.sms;

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
import com.twilio.sdk.resources.api.SmsMessage;

public class SmsMessageReader extends Reader<SmsMessage> {
    private final String accountSid;
    private String to;
    private String from;
    private String dateSent;

    /**
     * Construct a new SmsMessageReader
     * 
     * @param accountSid The account_sid
     */
    public SmsMessageReader(final String accountSid) {
        this.accountSid = accountSid;
    }

    /**
     * The to
     * 
     * @param to The to
     * @return this
     */
    public SmsMessageReader byTo(final String to) {
        this.to = to;
        return this;
    }

    /**
     * The from
     * 
     * @param from The from
     * @return this
     */
    public SmsMessageReader byFrom(final String from) {
        this.from = from;
        return this;
    }

    /**
     * The date_sent
     * 
     * @param dateSent The date_sent
     * @return this
     */
    public SmsMessageReader byDateSent(final String dateSent) {
        this.dateSent = dateSent;
        return this;
    }

    /**
     * Make the request to the Twilio API to perform the read
     * 
     * @param client TwilioRestClient with which to make the request
     * @return SmsMessage ResourceSet
     */
    @Override
    public ResourceSet<SmsMessage> execute(final TwilioRestClient client) {
        Request request = new Request(
            HttpMethod.GET,
            "/2010-04-01/Accounts/" + this.accountSid + "/SMS/Messages.json",
            client.getAccountSid()
        );
        
        addQueryParams(request);
        
        Page<SmsMessage> page = pageForRequest(client, request);
        
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
    public Page<SmsMessage> nextPage(final String nextPageUri, final TwilioRestClient client) {
        Request request = new Request(
            HttpMethod.GET,
            nextPageUri,
            client.getAccountSid()
        );
        return pageForRequest(client, request);
    }

    /**
     * Generate a Page of SmsMessage Resources for a given request
     * 
     * @param client TwilioRestClient with which to make the request
     * @param request Request to generate a page for
     * @return Page for the Request
     */
    protected Page<SmsMessage> pageForRequest(final TwilioRestClient client, final Request request) {
        Response response = client.request(request);
        
        if (response == null) {
            throw new ApiConnectionException("SmsMessage read failed: Unable to connect to server");
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
        
        Page<SmsMessage> result = new Page<>();
        result.deserialize("sms_messages", response.getContent(), SmsMessage.class, client.getObjectMapper());
        
        return result;
    }

    /**
     * Add the requested query string arguments to the Request
     * 
     * @param request Request to add query string arguments to
     */
    private void addQueryParams(final Request request) {
        if (to != null) {
            request.addQueryParam("To", to);
        }
        
        if (from != null) {
            request.addQueryParam("From", from);
        }
        
        if (dateSent != null) {
            request.addQueryParam("DateSent", dateSent);
        }
        
        request.addQueryParam("PageSize", Integer.toString(getPageSize()));
    }
}