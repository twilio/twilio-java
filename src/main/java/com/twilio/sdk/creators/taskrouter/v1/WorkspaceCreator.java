package com.twilio.sdk.creators.taskrouter.v1;

import com.twilio.sdk.clients.TwilioRestClient;
import com.twilio.sdk.creators.Creator;
import com.twilio.sdk.exceptions.ApiConnectionException;
import com.twilio.sdk.exceptions.ApiException;
import com.twilio.sdk.http.HttpMethod;
import com.twilio.sdk.http.Request;
import com.twilio.sdk.http.Response;
import com.twilio.sdk.resources.RestException;
import com.twilio.sdk.resources.taskrouter.v1.Workspace;

public class WorkspaceCreator extends Creator<Workspace> {
    private final String friendlyName;
    private String eventCallbackUrl;
    private String template;

    /**
     * Construct a new WorkspaceCreator
     * 
     * @param friendlyName The friendly_name
     */
    public WorkspaceCreator(final String friendlyName) {
        this.friendlyName = friendlyName;
    }

    /**
     * The event_callback_url
     * 
     * @param eventCallbackUrl The event_callback_url
     * @return this
     */
    public WorkspaceCreator setEventCallbackUrl(final String eventCallbackUrl) {
        this.eventCallbackUrl = eventCallbackUrl;
        return this;
    }

    /**
     * The template
     * 
     * @param template The template
     * @return this
     */
    public WorkspaceCreator setTemplate(final String template) {
        this.template = template;
        return this;
    }

    /**
     * Make the request to the Twilio API to perform the create
     * 
     * @param client TwilioRestClient with which to make the request
     * @return Created Workspace
     */
    @Override
    public Workspace execute(final TwilioRestClient client) {
        Request request = new Request(
            HttpMethod.POST,
            TwilioRestClient.Domains.TASKROUTER,
            "/v1/Workspaces",
            client.getAccountSid()
        );
        
        addPostParams(request);
        Response response = client.request(request);
        
        if (response == null) {
            throw new ApiConnectionException("Workspace creation failed: Unable to connect to server");
        } else if (response.getStatusCode() != TwilioRestClient.HTTP_STATUS_CODE_CREATED) {
            RestException restException = RestException.fromJson(response.getStream(), client.getObjectMapper());
            if (restException == null)
                throw new ApiException("Server Error, no content");
            throw new ApiException(
                restException.getMessage(),
                restException.getCode(),
                restException.getMoreInfo(),
                restException.getStatus(),
                null
            );
        }
        
        return Workspace.fromJson(response.getStream(), client.getObjectMapper());
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
        
        if (eventCallbackUrl != null) {
            request.addPostParam("EventCallbackUrl", eventCallbackUrl);
        }
        
        if (template != null) {
            request.addPostParam("Template", template);
        }
    }
}