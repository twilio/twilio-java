package com.twilio.sdk.updaters.taskrouter;

import com.twilio.sdk.clients.TwilioRestClient;
import com.twilio.sdk.exceptions.ApiConnectionException;
import com.twilio.sdk.exceptions.ApiException;
import com.twilio.sdk.http.HttpMethod;
import com.twilio.sdk.http.Request;
import com.twilio.sdk.http.Response;
import com.twilio.sdk.resources.RestException;
import com.twilio.sdk.resources.taskrouter.Worker;
import com.twilio.sdk.updaters.Updater;

public class WorkerUpdater extends Updater<Worker> {
    private final String workspaceSid;
    private final String sid;
    private String activitySid;
    private String attributes;
    private String friendlyName;

    /**
     * Construct a new WorkerUpdater
     * 
     * @param workspaceSid The workspace_sid
     * @param sid The sid
     */
    public WorkerUpdater(final String workspaceSid, final String sid) {
        this.workspaceSid = workspaceSid;
        this.sid = sid;
    }

    /**
     * The activity_sid
     * 
     * @param activitySid The activity_sid
     * @return this
     */
    public WorkerUpdater setActivitySid(final String activitySid) {
        this.activitySid = activitySid;
        return this;
    }

    /**
     * The attributes
     * 
     * @param attributes The attributes
     * @return this
     */
    public WorkerUpdater setAttributes(final String attributes) {
        this.attributes = attributes;
        return this;
    }

    /**
     * The friendly_name
     * 
     * @param friendlyName The friendly_name
     * @return this
     */
    public WorkerUpdater setFriendlyName(final String friendlyName) {
        this.friendlyName = friendlyName;
        return this;
    }

    /**
     * Make the request to the Twilio API to perform the update
     * 
     * @param client TwilioRestClient with which to make the request
     * @return Updated Worker
     */
    @Override
    public Worker execute(final TwilioRestClient client) {
        Request request = new Request(
            HttpMethod.POST,
            "/v1/Workspaces/" + this.workspaceSid + "/Workers/" + this.sid + "",
            client.getAccountSid()
        );
        
        addPostParams(request);
        Response response = client.request(request);
        
        if (response == null) {
            throw new ApiConnectionException("Worker update failed: Unable to connect to server");
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
        
        return Worker.fromJson(response.getStream(), client.getObjectMapper());
    }

    /**
     * Add the requested post parameters to the Request
     * 
     * @param request Request to add post params to
     */
    private void addPostParams(final Request request) {
        if (activitySid != null) {
            request.addPostParam("ActivitySid", activitySid);
        }
        
        if (attributes != null) {
            request.addPostParam("Attributes", attributes);
        }
        
        if (friendlyName != null) {
            request.addPostParam("FriendlyName", friendlyName);
        }
    }
}