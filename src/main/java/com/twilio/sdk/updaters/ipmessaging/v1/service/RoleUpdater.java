package com.twilio.sdk.updaters.ipmessaging.v1.service;

import com.twilio.sdk.clients.TwilioRestClient;
import com.twilio.sdk.exceptions.ApiConnectionException;
import com.twilio.sdk.exceptions.ApiException;
import com.twilio.sdk.http.HttpMethod;
import com.twilio.sdk.http.Request;
import com.twilio.sdk.http.Response;
import com.twilio.sdk.resources.RestException;
import com.twilio.sdk.resources.ipmessaging.v1.service.Role;
import com.twilio.sdk.updaters.Updater;

import java.util.List;

public class RoleUpdater extends Updater<Role> {
    private final String serviceSid;
    private final String sid;
    private final String friendlyName;
    private final List<String> permission;

    /**
     * Construct a new RoleUpdater.
     * 
     * @param serviceSid The service_sid
     * @param sid The sid
     * @param friendlyName The friendly_name
     * @param permission The permission
     */
    public RoleUpdater(final String serviceSid, 
                       final String sid, 
                       final String friendlyName, 
                       final List<String> permission) {
        this.serviceSid = serviceSid;
        this.sid = sid;
        this.friendlyName = friendlyName;
        this.permission = permission;
    }

    /**
     * Make the request to the Twilio API to perform the update.
     * 
     * @param client TwilioRestClient with which to make the request
     * @return Updated Role
     */
    @Override
    @SuppressWarnings("checkstyle:linelength")
    public Role execute(final TwilioRestClient client) {
        Request request = new Request(
            HttpMethod.POST,
            TwilioRestClient.Domains.IPMESSAGING,
            "/v1/Services/" + this.serviceSid + "/Roles/" + this.sid + "",
            client.getAccountSid()
        );
        
        addPostParams(request);
        Response response = client.request(request);
        
        if (response == null) {
            throw new ApiConnectionException("Role update failed: Unable to connect to server");
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
        
        return Role.fromJson(response.getStream(), client.getObjectMapper());
    }

    /**
     * Add the requested post parameters to the Request.
     * 
     * @param request Request to add post params to
     */
    private void addPostParams(final Request request) {
        if (friendlyName != null) {
            request.addPostParam("FriendlyName", friendlyName);
        }
        
        if (permission != null) {
            for (Object prop : permission) {
                request.addPostParam("Permission", prop.toString());
            }
        }
    }
}