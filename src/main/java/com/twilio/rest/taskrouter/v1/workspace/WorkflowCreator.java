/*
 * This code was generated by
 * ___ _ _ _ _ _    _ ____    ____ ____ _    ____ ____ _  _ ____ ____ ____ ___ __   __
 *  |  | | | | |    | |  | __ |  | |__| | __ | __ |___ |\ | |___ |__/ |__|  | |  | |__/
 *  |  |_|_| | |___ | |__|    |__| |  | |    |__] |___ | \| |___ |  \ |  |  | |__| |  \
 *
 * Twilio - Taskrouter
 * This is the public Twilio REST API.
 *
 * NOTE: This class is auto generated by OpenAPI Generator.
 * https://openapi-generator.tech
 * Do not edit the class manually.
 */

package com.twilio.rest.taskrouter.v1.workspace;

import com.twilio.base.Creator;
import com.twilio.constant.EnumConstants;
import com.twilio.converter.Promoter;
import com.twilio.exception.ApiConnectionException;
import com.twilio.exception.ApiException;
import com.twilio.exception.RestException;
import com.twilio.http.HttpMethod;
import com.twilio.http.Request;
import com.twilio.http.Response;
import com.twilio.http.TwilioRestClient;
import com.twilio.rest.Domains;
import java.net.URI;



import java.net.URI;

public class WorkflowCreator extends Creator<Workflow>{
    private String pathWorkspaceSid;
    private String friendlyName;
    private String configuration;
    private URI assignmentCallbackUrl;
    private URI fallbackAssignmentCallbackUrl;
    private Integer taskReservationTimeout;

    public WorkflowCreator(final String pathWorkspaceSid, final String friendlyName, final String configuration) {
        this.pathWorkspaceSid = pathWorkspaceSid;
        this.friendlyName = friendlyName;
        this.configuration = configuration;
    }

    public WorkflowCreator setFriendlyName(final String friendlyName){
        this.friendlyName = friendlyName;
        return this;
    }
    public WorkflowCreator setConfiguration(final String configuration){
        this.configuration = configuration;
        return this;
    }
    public WorkflowCreator setAssignmentCallbackUrl(final URI assignmentCallbackUrl){
        this.assignmentCallbackUrl = assignmentCallbackUrl;
        return this;
    }

    public WorkflowCreator setAssignmentCallbackUrl(final String assignmentCallbackUrl){
        return setAssignmentCallbackUrl(Promoter.uriFromString(assignmentCallbackUrl));
    }
    public WorkflowCreator setFallbackAssignmentCallbackUrl(final URI fallbackAssignmentCallbackUrl){
        this.fallbackAssignmentCallbackUrl = fallbackAssignmentCallbackUrl;
        return this;
    }

    public WorkflowCreator setFallbackAssignmentCallbackUrl(final String fallbackAssignmentCallbackUrl){
        return setFallbackAssignmentCallbackUrl(Promoter.uriFromString(fallbackAssignmentCallbackUrl));
    }
    public WorkflowCreator setTaskReservationTimeout(final Integer taskReservationTimeout){
        this.taskReservationTimeout = taskReservationTimeout;
        return this;
    }

    @Override
    public Workflow create(final TwilioRestClient client){
        String path = "/v1/Workspaces/{WorkspaceSid}/Workflows";

        path = path.replace("{"+"WorkspaceSid"+"}", this.pathWorkspaceSid.toString());
        path = path.replace("{"+"FriendlyName"+"}", this.friendlyName.toString());
        path = path.replace("{"+"Configuration"+"}", this.configuration.toString());

        Request request = new Request(
            HttpMethod.POST,
            Domains.TASKROUTER.toString(),
            path
        );
        request.setContentType(EnumConstants.ContentType.FORM_URLENCODED);
        addPostParams(request);
        Response response = client.request(request);
        if (response == null) {
            throw new ApiConnectionException("Workflow creation failed: Unable to connect to server");
        } else if (!TwilioRestClient.SUCCESS.test(response.getStatusCode())) {
            RestException restException = RestException.fromJson(response.getStream(), client.getObjectMapper());
            if (restException == null) {
                throw new ApiException("Server Error, no content");
            }
            throw new ApiException(restException);
        }

        return Workflow.fromJson(response.getStream(), client.getObjectMapper());
    }
    private void addPostParams(final Request request) {
        if (friendlyName != null) {
            request.addPostParam("FriendlyName", friendlyName);
    
        }
        if (configuration != null) {
            request.addPostParam("Configuration", configuration);
    
        }
        if (assignmentCallbackUrl != null) {
            request.addPostParam("AssignmentCallbackUrl", assignmentCallbackUrl.toString());
    
        }
        if (fallbackAssignmentCallbackUrl != null) {
            request.addPostParam("FallbackAssignmentCallbackUrl", fallbackAssignmentCallbackUrl.toString());
    
        }
        if (taskReservationTimeout != null) {
            request.addPostParam("TaskReservationTimeout", taskReservationTimeout.toString());
    
        }
    }
}
