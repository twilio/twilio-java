package com.twilio.sdk.creators.ipmessaging.v1.service;

import com.twilio.sdk.clients.TwilioRestClient;
import com.twilio.sdk.creators.Creator;
import com.twilio.sdk.exceptions.ApiConnectionException;
import com.twilio.sdk.exceptions.ApiException;
import com.twilio.sdk.http.HttpMethod;
import com.twilio.sdk.http.Request;
import com.twilio.sdk.http.Response;
import com.twilio.sdk.resources.RestException;
import com.twilio.sdk.resources.ipmessaging.v1.service.Role;

import java.util.List;

public class RoleCreator extends Creator<Role> {
    private final String serviceSid;
    private final String friendlyName;
    private final Role.RoleType type;
    private final List<String> permission;

    /**
     * Construct a new RoleCreator.
     * 
     * @param serviceSid The service_sid
     * @param friendlyName The friendly_name
     * @param type The type
     * @param permission The permission
     */
    public RoleCreator(final String serviceSid, 
                       final String friendlyName, 
                       final Role.RoleType type, 
                       final List<String> permission) {
        this.serviceSid = serviceSid;
        this.friendlyName = friendlyName;
        this.type = type;
        this.permission = permission;
    }

    /**
     * Make the request to the Twilio API to perform the create.
     * 
     * @param client TwilioRestClient with which to make the request
     * @return Created Role
     */
    @Override
    @SuppressWarnings("checkstyle:linelength")
    public Role execute(final TwilioRestClient client) {
        Request request = new Request(
            HttpMethod.POST,
            TwilioRestClient.Domains.IPMESSAGING,
            "/v1/Services/" + this.serviceSid + "/Roles",
            client.getAccountSid()
        );
        
        addPostParams(request);
        Response response = client.request(request);
        
        if (response == null) {
            throw new ApiConnectionException("Role creation failed: Unable to connect to server");
        } else if (response.getStatusCode() != TwilioRestClient.HTTP_STATUS_CODE_CREATED) {
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
        
        if (type != null) {
            request.addPostParam("Type", type.toString());
        }
        
        if (permission != null) {
            for (Object prop : permission) {
                request.addPostParam("Permission", prop.toString());
            }
        }
    }
}