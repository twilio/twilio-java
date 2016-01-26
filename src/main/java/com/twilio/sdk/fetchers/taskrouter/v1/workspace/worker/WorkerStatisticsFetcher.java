package com.twilio.sdk.fetchers.taskrouter.v1.workspace.worker;

import com.twilio.sdk.clients.TwilioRestClient;
import com.twilio.sdk.converters.MarshalConverter;
import com.twilio.sdk.exceptions.ApiConnectionException;
import com.twilio.sdk.exceptions.ApiException;
import com.twilio.sdk.fetchers.Fetcher;
import com.twilio.sdk.http.HttpMethod;
import com.twilio.sdk.http.Request;
import com.twilio.sdk.http.Response;
import com.twilio.sdk.resources.RestException;
import com.twilio.sdk.resources.taskrouter.v1.workspace.worker.WorkerStatistics;
import org.joda.time.DateTime;

public class WorkerStatisticsFetcher extends Fetcher<WorkerStatistics> {
    private final String workspaceSid;
    private final String workerSid;
    private Integer minutes;
    private DateTime startDate;
    private DateTime endDate;

    /**
     * Construct a new WorkerStatisticsFetcher
     * 
     * @param workspaceSid The workspace_sid
     * @param workerSid The worker_sid
     */
    public WorkerStatisticsFetcher(final String workspaceSid, final String workerSid) {
        this.workspaceSid = workspaceSid;
        this.workerSid = workerSid;
    }

    /**
     * The minutes
     * 
     * @param minutes The minutes
     * @return this
     */
    public WorkerStatisticsFetcher setMinutes(final Integer minutes) {
        this.minutes = minutes;
        return this;
    }

    /**
     * The start_date
     * 
     * @param startDate The start_date
     * @return this
     */
    public WorkerStatisticsFetcher setStartDate(final DateTime startDate) {
        this.startDate = startDate;
        return this;
    }

    /**
     * The end_date
     * 
     * @param endDate The end_date
     * @return this
     */
    public WorkerStatisticsFetcher setEndDate(final DateTime endDate) {
        this.endDate = endDate;
        return this;
    }

    /**
     * Make the request to the Twilio API to perform the fetch
     * 
     * @param client TwilioRestClient with which to make the request
     * @return Fetched WorkerStatistics
     */
    @Override
    public WorkerStatistics execute(final TwilioRestClient client) {
        Request request = new Request(
            HttpMethod.GET,
            TwilioRestClient.Domains.TASKROUTER,
            "/v1/Workspaces/" + this.workspaceSid + "/Workers/" + this.workerSid + "/Statistics",
            client.getAccountSid()
        );
        
        Response response = client.request(request);
        
        if (response == null) {
            throw new ApiConnectionException("WorkerStatistics fetch failed: Unable to connect to server");
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
        
        return WorkerStatistics.fromJson(response.getStream(), client.getObjectMapper());
    }
}