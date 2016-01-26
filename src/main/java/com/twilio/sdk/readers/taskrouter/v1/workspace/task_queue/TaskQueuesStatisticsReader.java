package com.twilio.sdk.readers.taskrouter.v1.workspace.task_queue;

import com.twilio.sdk.clients.TwilioRestClient;
import com.twilio.sdk.converters.MarshalConverter;
import com.twilio.sdk.exceptions.ApiConnectionException;
import com.twilio.sdk.exceptions.ApiException;
import com.twilio.sdk.http.HttpMethod;
import com.twilio.sdk.http.Request;
import com.twilio.sdk.http.Response;
import com.twilio.sdk.readers.Reader;
import com.twilio.sdk.resources.Page;
import com.twilio.sdk.resources.ResourceSet;
import com.twilio.sdk.resources.RestException;
import com.twilio.sdk.resources.taskrouter.v1.workspace.task_queue.TaskQueuesStatistics;
import org.joda.time.DateTime;

public class TaskQueuesStatisticsReader extends Reader<TaskQueuesStatistics> {
    private final String workspaceSid;
    private DateTime endDate;
    private String friendlyName;
    private Integer minutes;
    private DateTime startDate;

    /**
     * Construct a new TaskQueuesStatisticsReader
     * 
     * @param workspaceSid The workspace_sid
     */
    public TaskQueuesStatisticsReader(final String workspaceSid) {
        this.workspaceSid = workspaceSid;
    }

    /**
     * The end_date
     * 
     * @param endDate The end_date
     * @return this
     */
    public TaskQueuesStatisticsReader byEndDate(final DateTime endDate) {
        this.endDate = endDate;
        return this;
    }

    /**
     * The friendly_name
     * 
     * @param friendlyName The friendly_name
     * @return this
     */
    public TaskQueuesStatisticsReader byFriendlyName(final String friendlyName) {
        this.friendlyName = friendlyName;
        return this;
    }

    /**
     * The minutes
     * 
     * @param minutes The minutes
     * @return this
     */
    public TaskQueuesStatisticsReader byMinutes(final Integer minutes) {
        this.minutes = minutes;
        return this;
    }

    /**
     * The start_date
     * 
     * @param startDate The start_date
     * @return this
     */
    public TaskQueuesStatisticsReader byStartDate(final DateTime startDate) {
        this.startDate = startDate;
        return this;
    }

    /**
     * Make the request to the Twilio API to perform the read
     * 
     * @param client TwilioRestClient with which to make the request
     * @return TaskQueuesStatistics ResourceSet
     */
    @Override
    public ResourceSet<TaskQueuesStatistics> execute(final TwilioRestClient client) {
        Request request = new Request(
            HttpMethod.GET,
            "/v1/Workspaces/" + this.workspaceSid + "/TaskQueues/Statistics",
            client.getAccountSid()
        );
        
        addQueryParams(request);
        
        Page<TaskQueuesStatistics> page = pageForRequest(client, request);
        
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
    public Page<TaskQueuesStatistics> nextPage(final String nextPageUri, final TwilioRestClient client) {
        Request request = new Request(
            HttpMethod.GET,
            nextPageUri,
            client.getAccountSid()
        );
        return pageForRequest(client, request);
    }

    /**
     * Generate a Page of TaskQueuesStatistics Resources for a given request
     * 
     * @param client TwilioRestClient with which to make the request
     * @param request Request to generate a page for
     * @return Page for the Request
     */
    protected Page<TaskQueuesStatistics> pageForRequest(final TwilioRestClient client, final Request request) {
        Response response = client.request(request);
        
        if (response == null) {
            throw new ApiConnectionException("TaskQueuesStatistics read failed: Unable to connect to server");
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
        
        Page<TaskQueuesStatistics> result = new Page<>();
        result.deserialize("task_queues_statistics", response.getContent(), TaskQueuesStatistics.class, client.getObjectMapper());
        
        return result;
    }

    /**
     * Add the requested query string arguments to the Request
     * 
     * @param request Request to add query string arguments to
     */
    private void addQueryParams(final Request request) {
        if (endDate != null) {
            request.addQueryParam("EndDate", endDate.toString());
        }
        
        if (friendlyName != null) {
            request.addQueryParam("FriendlyName", friendlyName);
        }
        
        if (minutes != null) {
            request.addQueryParam("Minutes", minutes.toString());
        }
        
        if (startDate != null) {
            request.addQueryParam("StartDate", startDate.toString());
        }
        
        request.addQueryParam("PageSize", Integer.toString(getPageSize()));
    }
}