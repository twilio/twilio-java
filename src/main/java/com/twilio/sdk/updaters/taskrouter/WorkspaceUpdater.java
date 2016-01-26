package com.twilio.sdk.updaters.taskrouter;

import com.twilio.sdk.clients.TwilioRestClient;
import com.twilio.sdk.exceptions.ApiConnectionException;
import com.twilio.sdk.exceptions.ApiException;
import com.twilio.sdk.http.HttpMethod;
import com.twilio.sdk.http.Request;
import com.twilio.sdk.http.Response;
import com.twilio.sdk.resources.RestException;
import com.twilio.sdk.resources.taskrouter.Workspace;
import com.twilio.sdk.updaters.Updater;

public class WorkspaceUpdater extends Updater<Workspace> {
    private final String sid;
    private String defaultActivitySid;
    private String eventCallbackUrl;
    private String friendlyName;
    private String timeoutActivitySid;

    /**
     * Construct a new WorkspaceUpdater
     * 
     * @param sid The sid
     */
    public WorkspaceUpdater(final String sid) {
        this.sid = sid;
    }

    /**
     * The default_activity_sid
     * 
     * @param defaultActivitySid The default_activity_sid
     * @return this
     */
    public WorkspaceUpdater setDefaultActivitySid(final String defaultActivitySid) {
        this.defaultActivitySid = defaultActivitySid;
        return this;
    }

    /**
     * The event_callback_url
     * 
     * @param eventCallbackUrl The event_callback_url
     * @return this
     */
    public WorkspaceUpdater setEventCallbackUrl(final String eventCallbackUrl) {
        this.eventCallbackUrl = eventCallbackUrl;
        return this;
    }

    /**
     * The friendly_name
     * 
     * @param friendlyName The friendly_name
     * @return this
     */
    public WorkspaceUpdater setFriendlyName(final String friendlyName) {
        this.friendlyName = friendlyName;
        return this;
    }

    /**
     * The timeout_activity_sid
     * 
     * @param timeoutActivitySid The timeout_activity_sid
     * @return this
     */
    public WorkspaceUpdater setTimeoutActivitySid(final String timeoutActivitySid) {
        this.timeoutActivitySid = timeoutActivitySid;
        return this;
    }

    /**
     * Make the request to the Twilio API to perform the update
     * 
     * @param client TwilioRestClient with which to make the request
     * @return Updated Workspace
     */
    @Override
    public Workspace execute(final TwilioRestClient client) {
        Request request = new Request(
            HttpMethod.POST,
            "/v1/Workspaces/" + this.sid + "",
            client.getAccountSid()
        );
        
        addPostParams(request);
        Response response = client.request(request);
        
        if (response == null) {
            throw new ApiConnectionException("Workspace update failed: Unable to connect to server");
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
        
        return Workspace.fromJson(response.getStream(), client.getObjectMapper());
    }

    /**
     * Add the requested post parameters to the Request
     * 
     * @param request Request to add post params to
     */
    private void addPostParams(final Request request) {
        if (defaultActivitySid != null) {
            request.addPostParam("DefaultActivitySid", defaultActivitySid);
        }
        
        if (eventCallbackUrl != null) {
            request.addPostParam("EventCallbackUrl", eventCallbackUrl);
        }
        
        if (friendlyName != null) {
            request.addPostParam("FriendlyName", friendlyName);
        }
        
        if (timeoutActivitySid != null) {
            request.addPostParam("TimeoutActivitySid", timeoutActivitySid);
        }
    }
}