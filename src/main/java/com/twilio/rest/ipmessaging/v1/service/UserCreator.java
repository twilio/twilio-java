/**
 * This code was generated by
 * \ / _    _  _|   _  _
 *  | (_)\/(_)(_|\/| |(/_  v1.0.0
 *       /       /
 */

package com.twilio.rest.ipmessaging.v1.service;

import com.twilio.base.Creator;
import com.twilio.exception.ApiConnectionException;
import com.twilio.exception.ApiException;
import com.twilio.exception.RestException;
import com.twilio.http.HttpMethod;
import com.twilio.http.Request;
import com.twilio.http.Response;
import com.twilio.http.TwilioRestClient;
import com.twilio.rest.Domains;

public class UserCreator extends Creator<User> {
    private final String pathServiceSid;
    private final String identity;
    private String roleSid;
    private String attributes;
    private String friendlyName;

    /**
     * Construct a new UserCreator.
     *
     * @param pathServiceSid The service_sid
     * @param identity The identity
     */
    public UserCreator(final String pathServiceSid,
                       final String identity) {
        this.pathServiceSid = pathServiceSid;
        this.identity = identity;
    }

    /**
     * The role_sid.
     *
     * @param roleSid The role_sid
     * @return this
     */
    public UserCreator setRoleSid(final String roleSid) {
        this.roleSid = roleSid;
        return this;
    }

    /**
     * The attributes.
     *
     * @param attributes The attributes
     * @return this
     */
    public UserCreator setAttributes(final String attributes) {
        this.attributes = attributes;
        return this;
    }

    /**
     * The friendly_name.
     *
     * @param friendlyName The friendly_name
     * @return this
     */
    public UserCreator setFriendlyName(final String friendlyName) {
        this.friendlyName = friendlyName;
        return this;
    }

    /**
     * Make the request to the Twilio API to perform the create.
     *
     * @param client TwilioRestClient with which to make the request
     * @return Created User
     */
    @Override
    @SuppressWarnings("checkstyle:linelength")
    public User create(final TwilioRestClient client) {
        Request request = new Request(
            HttpMethod.POST,
            Domains.IPMESSAGING.toString(),
            "/v1/Services/" + this.pathServiceSid + "/Users"
        );

        addPostParams(request);
        Response response = client.request(request);

        if (response == null) {
            throw new ApiConnectionException("User creation failed: Unable to connect to server");
        } else if (!TwilioRestClient.SUCCESS.test(response.getStatusCode())) {
            RestException restException = RestException.fromJson(response.getStream(), client.getObjectMapper());
            if (restException == null) {
                throw new ApiException("Server Error, no content");
            }
            throw new ApiException(restException);
        }

        return User.fromJson(response.getStream(), client.getObjectMapper());
    }

    /**
     * Add the requested post parameters to the Request.
     *
     * @param request Request to add post params to
     */
    private void addPostParams(final Request request) {
        if (identity != null) {
            request.addPostParam("Identity", identity);
        }

        if (roleSid != null) {
            request.addPostParam("RoleSid", roleSid);
        }

        if (attributes != null) {
            request.addPostParam("Attributes", attributes);
        }

        if (friendlyName != null) {
            request.addPostParam("FriendlyName", friendlyName);
        }
    }
}