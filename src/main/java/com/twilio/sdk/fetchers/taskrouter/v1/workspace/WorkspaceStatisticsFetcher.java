package com.twilio.sdk.fetchers.taskrouter.v1.workspace;

import com.twilio.sdk.clients.TwilioRestClient;
import com.twilio.sdk.exceptions.ApiConnectionException;
import com.twilio.sdk.exceptions.ApiException;
import com.twilio.sdk.fetchers.Fetcher;
import com.twilio.sdk.http.HttpMethod;
import com.twilio.sdk.http.Request;
import com.twilio.sdk.http.Response;
import com.twilio.sdk.resources.RestException;
import com.twilio.sdk.resources.taskrouter.v1.workspace.WorkspaceStatistics;

public class WorkspaceStatisticsFetcher extends Fetcher<WorkspaceStatistics> {
    private final String workspaceSid;
    private Integer minutes;
    private String startDate;
    private String endDate;

    /**
     * Construct a new WorkspaceStatisticsFetcher
     * 
     * @param workspaceSid The workspace_sid
     */
    public WorkspaceStatisticsFetcher(final String workspaceSid) {
        this.workspaceSid = workspaceSid;
    }

    /**
     * The minutes
     * 
     * @param minutes The minutes
     * @return this
     */
    public WorkspaceStatisticsFetcher setMinutes(final Integer minutes) {
        this.minutes = minutes;
        return this;
    }

    /**
     * The start_date
     * 
     * @param startDate The start_date
     * @return this
     */
    public WorkspaceStatisticsFetcher setStartDate(final String startDate) {
        this.startDate = startDate;
        return this;
    }

    /**
     * The end_date
     * 
     * @param endDate The end_date
     * @return this
     */
    public WorkspaceStatisticsFetcher setEndDate(final String endDate) {
        this.endDate = endDate;
        return this;
    }

    /**
     * Make the request to the Twilio API to perform the fetch
     * 
     * @param client TwilioRestClient with which to make the request
     * @return Fetched WorkspaceStatistics
     */
    @Override
    public WorkspaceStatistics execute(final TwilioRestClient client) {
        Request request = new Request(
            HttpMethod.GET,
            "/v1/Workspaces/" + this.workspaceSid + "/Statistics",
            client.getAccountSid()
        );
        
        Response response = client.request(request);
        
        if (response == null) {
            throw new ApiConnectionException("WorkspaceStatistics fetch failed: Unable to connect to server");
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
        
        return WorkspaceStatistics.fromJson(response.getStream(), client.getObjectMapper());
    }
}