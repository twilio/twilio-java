package com.twilio.sdk.readers.taskrouter.v1.workspace;

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
import com.twilio.sdk.resources.taskrouter.v1.workspace.Task;

public class TaskReader extends Reader<Task> {
    private final String workspaceSid;
    private Integer priority;
    private Task.Status assignmentStatus;
    private String workflowSid;
    private String workflowName;
    private String taskQueueSid;
    private String taskQueueName;

    /**
     * Construct a new TaskReader
     * 
     * @param workspaceSid The workspace_sid
     */
    public TaskReader(final String workspaceSid) {
        this.workspaceSid = workspaceSid;
    }

    /**
     * The priority
     * 
     * @param priority The priority
     * @return this
     */
    public TaskReader byPriority(final Integer priority) {
        this.priority = priority;
        return this;
    }

    /**
     * The assignment_status
     * 
     * @param assignmentStatus The assignment_status
     * @return this
     */
    public TaskReader byAssignmentStatus(final Task.Status assignmentStatus) {
        this.assignmentStatus = assignmentStatus;
        return this;
    }

    /**
     * The workflow_sid
     * 
     * @param workflowSid The workflow_sid
     * @return this
     */
    public TaskReader byWorkflowSid(final String workflowSid) {
        this.workflowSid = workflowSid;
        return this;
    }

    /**
     * The workflow_name
     * 
     * @param workflowName The workflow_name
     * @return this
     */
    public TaskReader byWorkflowName(final String workflowName) {
        this.workflowName = workflowName;
        return this;
    }

    /**
     * The task_queue_sid
     * 
     * @param taskQueueSid The task_queue_sid
     * @return this
     */
    public TaskReader byTaskQueueSid(final String taskQueueSid) {
        this.taskQueueSid = taskQueueSid;
        return this;
    }

    /**
     * The task_queue_name
     * 
     * @param taskQueueName The task_queue_name
     * @return this
     */
    public TaskReader byTaskQueueName(final String taskQueueName) {
        this.taskQueueName = taskQueueName;
        return this;
    }

    /**
     * Make the request to the Twilio API to perform the read
     * 
     * @param client TwilioRestClient with which to make the request
     * @return Task ResourceSet
     */
    @Override
    public ResourceSet<Task> execute(final TwilioRestClient client) {
        Request request = new Request(
            HttpMethod.GET,
            "/v1/Workspaces/" + this.workspaceSid + "/Tasks",
            client.getAccountSid()
        );
        
        addQueryParams(request);
        
        Page<Task> page = pageForRequest(client, request);
        
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
    public Page<Task> nextPage(final String nextPageUri, final TwilioRestClient client) {
        Request request = new Request(
            HttpMethod.GET,
            nextPageUri,
            client.getAccountSid()
        );
        return pageForRequest(client, request);
    }

    /**
     * Generate a Page of Task Resources for a given request
     * 
     * @param client TwilioRestClient with which to make the request
     * @param request Request to generate a page for
     * @return Page for the Request
     */
    protected Page<Task> pageForRequest(final TwilioRestClient client, final Request request) {
        Response response = client.request(request);
        
        if (response == null) {
            throw new ApiConnectionException("Task read failed: Unable to connect to server");
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
        
        Page<Task> result = new Page<>();
        result.deserialize("tasks", response.getContent(), Task.class, client.getObjectMapper());
        
        return result;
    }

    /**
     * Add the requested query string arguments to the Request
     * 
     * @param request Request to add query string arguments to
     */
    private void addQueryParams(final Request request) {
        if (priority != null) {
            request.addQueryParam("Priority", priority.toString());
        }
        
        if (assignmentStatus != null) {
            request.addQueryParam("AssignmentStatus", assignmentStatus.toString());
        }
        
        if (workflowSid != null) {
            request.addQueryParam("WorkflowSid", workflowSid);
        }
        
        if (workflowName != null) {
            request.addQueryParam("WorkflowName", workflowName);
        }
        
        if (taskQueueSid != null) {
            request.addQueryParam("TaskQueueSid", taskQueueSid);
        }
        
        if (taskQueueName != null) {
            request.addQueryParam("TaskQueueName", taskQueueName);
        }
        
        request.addQueryParam("PageSize", Integer.toString(getPageSize()));
    }
}