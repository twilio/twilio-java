package com.twilio.sdk.creators.taskrouter.v1.workspace;

import com.twilio.sdk.clients.TwilioRestClient;
import com.twilio.sdk.creators.Creator;
import com.twilio.sdk.exceptions.ApiConnectionException;
import com.twilio.sdk.exceptions.ApiException;
import com.twilio.sdk.http.HttpMethod;
import com.twilio.sdk.http.Request;
import com.twilio.sdk.http.Response;
import com.twilio.sdk.resources.RestException;
import com.twilio.sdk.resources.taskrouter.v1.workspace.Workflow;

public class WorkflowCreator extends Creator<Workflow> {
    private final String workspaceSid;
    private final String friendlyName;
    private final String configuration;
    private final String assignmentCallbackUrl;
    private String fallbackAssignmentCallbackUrl;
    private Integer taskReservationTimeout;

    /**
     * Construct a new WorkflowCreator
     * 
     * @param workspaceSid The workspace_sid
     * @param friendlyName The friendly_name
     * @param configuration The configuration
     * @param assignmentCallbackUrl The assignment_callback_url
     */
    public WorkflowCreator(final String workspaceSid, final String friendlyName, final String configuration, final String assignmentCallbackUrl) {
        this.workspaceSid = workspaceSid;
        this.friendlyName = friendlyName;
        this.configuration = configuration;
        this.assignmentCallbackUrl = assignmentCallbackUrl;
    }

    /**
     * The fallback_assignment_callback_url
     * 
     * @param fallbackAssignmentCallbackUrl The fallback_assignment_callback_url
     * @return this
     */
    public WorkflowCreator setFallbackAssignmentCallbackUrl(final String fallbackAssignmentCallbackUrl) {
        this.fallbackAssignmentCallbackUrl = fallbackAssignmentCallbackUrl;
        return this;
    }

    /**
     * The task_reservation_timeout
     * 
     * @param taskReservationTimeout The task_reservation_timeout
     * @return this
     */
    public WorkflowCreator setTaskReservationTimeout(final Integer taskReservationTimeout) {
        this.taskReservationTimeout = taskReservationTimeout;
        return this;
    }

    /**
     * Make the request to the Twilio API to perform the create
     * 
     * @param client TwilioRestClient with which to make the request
     * @return Created Workflow
     */
    @Override
    public Workflow execute(final TwilioRestClient client) {
        Request request = new Request(
            HttpMethod.POST,
            "/v1/Workspaces/" + this.workspaceSid + "/Workflows",
            client.getAccountSid()
        );
        
        addPostParams(request);
        Response response = client.request(request);
        
        if (response == null) {
            throw new ApiConnectionException("Workflow creation failed: Unable to connect to server");
        } else if (response.getStatusCode() != TwilioRestClient.HTTP_STATUS_CODE_CREATED) {
            RestException restException = RestException.fromJson(response.getStream(), client.getObjectMapper());
            throw new ApiException(
                restException.getMessage(),
                restException.getCode(),
                restException.getMoreInfo(),
                restException.getStatus(),
                null
            );
        }
        
        return Workflow.fromJson(response.getStream(), client.getObjectMapper());
    }

    /**
     * Add the requested post parameters to the Request
     * 
     * @param request Request to add post params to
     */
    private void addPostParams(final Request request) {
        if (friendlyName != null) {
            request.addPostParam("FriendlyName", friendlyName);
        }
        
        if (configuration != null) {
            request.addPostParam("Configuration", configuration);
        }
        
        if (assignmentCallbackUrl != null) {
            request.addPostParam("AssignmentCallbackUrl", assignmentCallbackUrl);
        }
        
        if (fallbackAssignmentCallbackUrl != null) {
            request.addPostParam("FallbackAssignmentCallbackUrl", fallbackAssignmentCallbackUrl);
        }
        
        if (taskReservationTimeout != null) {
            request.addPostParam("TaskReservationTimeout", taskReservationTimeout.toString());
        }
    }
}