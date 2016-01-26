package com.twilio.sdk.readers.api;

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
import com.twilio.sdk.resources.api.Record;

public class RecordReader extends Reader<Record> {
    private final String accountSid;
    private Record.Category category;
    private String startDate;
    private String endDate;

    /**
     * Construct a new RecordReader
     * 
     * @param accountSid The account_sid
     */
    public RecordReader(final String accountSid) {
        this.accountSid = accountSid;
    }

    /**
     * Only include usage of a given category
     * 
     * @param category Only include usage of a given category
     * @return this
     */
    public RecordReader byCategory(final Record.Category category) {
        this.category = category;
        return this;
    }

    /**
     * Only include usage that has occurred on or after this date. Format is
     * YYYY-MM-DD in GTM. As a convenience, you can also specify offsets to today,
     * for example, StartDate=-30days, which will make StartDate 30 days before
     * today
     * 
     * @param startDate Filter by start date
     * @return this
     */
    public RecordReader byStartDate(final String startDate) {
        this.startDate = startDate;
        return this;
    }

    /**
     * Only include usage that has occurred on or after this date. Format is
     * YYYY-MM-DD in GTM. As a convenience, you can also specify offsets to today,
     * for example, EndDate=+30days, which will make EndDate 30 days from today
     * 
     * @param endDate Filter by end date
     * @return this
     */
    public RecordReader byEndDate(final String endDate) {
        this.endDate = endDate;
        return this;
    }

    /**
     * Make the request to the Twilio API to perform the read
     * 
     * @param client TwilioRestClient with which to make the request
     * @return Record ResourceSet
     */
    @Override
    public ResourceSet<Record> execute(final TwilioRestClient client) {
        Request request = new Request(
            HttpMethod.GET,
            "/2010-04-01/Accounts/" + this.accountSid + "/Usage/Records.json",
            client.getAccountSid()
        );
        
        addQueryParams(request);
        
        Page<Record> page = pageForRequest(client, request);
        
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
    public Page<Record> nextPage(final String nextPageUri, final TwilioRestClient client) {
        Request request = new Request(
            HttpMethod.GET,
            nextPageUri,
            client.getAccountSid()
        );
        return pageForRequest(client, request);
    }

    /**
     * Generate a Page of Record Resources for a given request
     * 
     * @param client TwilioRestClient with which to make the request
     * @param request Request to generate a page for
     * @return Page for the Request
     */
    protected Page<Record> pageForRequest(final TwilioRestClient client, final Request request) {
        Response response = client.request(request);
        
        if (response == null) {
            throw new ApiConnectionException("Record read failed: Unable to connect to server");
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
        
        Page<Record> result = new Page<>();
        result.deserialize("usage_records", response.getContent(), Record.class, client.getObjectMapper());
        
        return result;
    }

    /**
     * Add the requested query string arguments to the Request
     * 
     * @param request Request to add query string arguments to
     */
    private void addQueryParams(final Request request) {
        if (category != null) {
            request.addQueryParam("Category", category.toString());
        }
        
        if (startDate != null) {
            request.addQueryParam("StartDate", startDate);
        }
        
        if (endDate != null) {
            request.addQueryParam("EndDate", endDate);
        }
        
        request.addQueryParam("PageSize", Integer.toString(getPageSize()));
    }
}