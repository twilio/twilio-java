package com.twilio.sdk.creators.taskrouter.v1.workspace;

import com.twilio.sdk.clients.TwilioRestClient;
import com.twilio.sdk.creators.Creator;
import com.twilio.sdk.exceptions.ApiConnectionException;
import com.twilio.sdk.exceptions.ApiException;
import com.twilio.sdk.http.HttpMethod;
import com.twilio.sdk.http.Request;
import com.twilio.sdk.http.Response;
import com.twilio.sdk.resources.RestException;
import com.twilio.sdk.resources.taskrouter.Activity;

public class ActivityCreator extends Creator<Activity> {
    private final String workspaceSid;
    private final String friendlyName;
    private final Boolean available;

    /**
     * Construct a new ActivityCreator
     * 
     * @param workspaceSid The workspace_sid
     * @param friendlyName The friendly_name
     * @param available The available
     */
    public ActivityCreator(final String workspaceSid, final String friendlyName, final Boolean available) {
        this.workspaceSid = workspaceSid;
        this.friendlyName = friendlyName;
        this.available = available;
    }

    /**
     * Make the request to the Twilio API to perform the create
     * 
     * @param client TwilioRestClient with which to make the request
     * @return Created Activity
     */
    @Override
    public Activity execute(final TwilioRestClient client) {
        Request request = new Request(
            HttpMethod.POST,
            "/v1/Workspaces/" + this.workspaceSid + "/Activities",
            client.getAccountSid()
        );
        
        addPostParams(request);
        Response response = client.request(request);
        
        if (response == null) {
            throw new ApiConnectionException("Activity creation failed: Unable to connect to server");
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
        
        return Activity.fromJson(response.getStream(), client.getObjectMapper());
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
        
        if (available != null) {
            request.addPostParam("Available", available.toString());
        }
    }
}