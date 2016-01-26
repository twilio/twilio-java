package com.twilio.sdk.readers.monitor;

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
import com.twilio.sdk.resources.monitor.Alert;

public class AlertReader extends Reader<Alert> {
    private String logLevel;
    private String startDate;
    private String endDate;

    /**
     * The log_level
     * 
     * @param logLevel The log_level
     * @return this
     */
    public AlertReader byLogLevel(final String logLevel) {
        this.logLevel = logLevel;
        return this;
    }

    /**
     * The start_date
     * 
     * @param startDate The start_date
     * @return this
     */
    public AlertReader byStartDate(final String startDate) {
        this.startDate = startDate;
        return this;
    }

    /**
     * The end_date
     * 
     * @param endDate The end_date
     * @return this
     */
    public AlertReader byEndDate(final String endDate) {
        this.endDate = endDate;
        return this;
    }

    /**
     * Make the request to the Twilio API to perform the read
     * 
     * @param client TwilioRestClient with which to make the request
     * @return Alert ResourceSet
     */
    @Override
    public ResourceSet<Alert> execute(final TwilioRestClient client) {
        Request request = new Request(
            HttpMethod.GET,
            "/v1/Alerts",
            client.getAccountSid()
        );
        
        addQueryParams(request);
        
        Page<Alert> page = pageForRequest(client, request);
        
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
    public Page<Alert> nextPage(final String nextPageUri, final TwilioRestClient client) {
        Request request = new Request(
            HttpMethod.GET,
            nextPageUri,
            client.getAccountSid()
        );
        return pageForRequest(client, request);
    }

    /**
     * Generate a Page of Alert Resources for a given request
     * 
     * @param client TwilioRestClient with which to make the request
     * @param request Request to generate a page for
     * @return Page for the Request
     */
    protected Page<Alert> pageForRequest(final TwilioRestClient client, final Request request) {
        Response response = client.request(request);
        
        if (response == null) {
            throw new ApiConnectionException("Alert read failed: Unable to connect to server");
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
        
        Page<Alert> result = new Page<>();
        result.deserialize("alerts", response.getContent(), Alert.class, client.getObjectMapper());
        
        return result;
    }

    /**
     * Add the requested query string arguments to the Request
     * 
     * @param request Request to add query string arguments to
     */
    private void addQueryParams(final Request request) {
        if (logLevel != null) {
            request.addQueryParam("LogLevel", logLevel);
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