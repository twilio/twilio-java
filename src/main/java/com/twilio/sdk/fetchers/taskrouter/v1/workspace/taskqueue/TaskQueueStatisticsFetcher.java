package com.twilio.sdk.fetchers.taskrouter.v1.workspace.taskqueue;

import com.twilio.sdk.clients.TwilioRestClient;
import com.twilio.sdk.converters.MarshalConverter;
import com.twilio.sdk.exceptions.ApiConnectionException;
import com.twilio.sdk.exceptions.ApiException;
import com.twilio.sdk.fetchers.Fetcher;
import com.twilio.sdk.http.HttpMethod;
import com.twilio.sdk.http.Request;
import com.twilio.sdk.http.Response;
import com.twilio.sdk.resources.RestException;
import com.twilio.sdk.resources.taskrouter.v1.workspace.taskqueue.TaskQueueStatistics;
import org.joda.time.DateTime;

public class TaskQueueStatisticsFetcher extends Fetcher<TaskQueueStatistics> {
    private final String workspaceSid;
    private final String taskQueueSid;
    private DateTime endDate;
    private String friendlyName;
    private Integer minutes;
    private DateTime startDate;

    /**
     * Construct a new TaskQueueStatisticsFetcher.
     * 
     * @param workspaceSid The workspace_sid
     * @param taskQueueSid The task_queue_sid
     */
    public TaskQueueStatisticsFetcher(final String workspaceSid, 
                                      final String taskQueueSid) {
        this.workspaceSid = workspaceSid;
        this.taskQueueSid = taskQueueSid;
    }

    /**
     * The end_date.
     * 
     * @param endDate The end_date
     * @return this
     */
    public TaskQueueStatisticsFetcher setEndDate(final DateTime endDate) {
        this.endDate = endDate;
        return this;
    }

    /**
     * The friendly_name.
     * 
     * @param friendlyName The friendly_name
     * @return this
     */
    public TaskQueueStatisticsFetcher setFriendlyName(final String friendlyName) {
        this.friendlyName = friendlyName;
        return this;
    }

    /**
     * The minutes.
     * 
     * @param minutes The minutes
     * @return this
     */
    public TaskQueueStatisticsFetcher setMinutes(final Integer minutes) {
        this.minutes = minutes;
        return this;
    }

    /**
     * The start_date.
     * 
     * @param startDate The start_date
     * @return this
     */
    public TaskQueueStatisticsFetcher setStartDate(final DateTime startDate) {
        this.startDate = startDate;
        return this;
    }

    /**
     * Make the request to the Twilio API to perform the fetch.
     * 
     * @param client TwilioRestClient with which to make the request
     * @return Fetched TaskQueueStatistics
     */
    @Override
    @SuppressWarnings("checkstyle:linelength")
    public TaskQueueStatistics execute(final TwilioRestClient client) {
        Request request = new Request(
            HttpMethod.GET,
            TwilioRestClient.Domains.TASKROUTER,
            "/v1/Workspaces/" + this.workspaceSid + "/TaskQueues/" + this.taskQueueSid + "/Statistics",
            client.getAccountSid()
        );
        
        Response response = client.request(request);
        
        if (response == null) {
            throw new ApiConnectionException("TaskQueueStatistics fetch failed: Unable to connect to server");
        } else if (response.getStatusCode() != TwilioRestClient.HTTP_STATUS_CODE_OK) {
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
        
        return TaskQueueStatistics.fromJson(response.getStream(), client.getObjectMapper());
    }
}