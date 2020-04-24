/**
 * This code was generated by
 * \ / _    _  _|   _  _
 *  | (_)\/(_)(_|\/| |(/_  v1.0.0
 *       /       /
 */

package com.twilio.rest.authy.v1.service.entity;

import com.twilio.base.Updater;
import com.twilio.exception.ApiConnectionException;
import com.twilio.exception.ApiException;
import com.twilio.exception.RestException;
import com.twilio.http.HttpMethod;
import com.twilio.http.Request;
import com.twilio.http.Response;
import com.twilio.http.TwilioRestClient;
import com.twilio.rest.Domains;

/**
 * PLEASE NOTE that this class contains preview products that are subject to
 * change. Use them with caution. If you currently do not have developer preview
 * access, please contact help@twilio.com.
 */
public class FactorUpdater extends Updater<Factor> {
    private final String pathServiceSid;
    private final String pathIdentity;
    private final String pathSid;
    private String authPayload;
    private String friendlyName;
    private String config;

    /**
     * Construct a new FactorUpdater.
     *
     * @param pathServiceSid Service Sid.
     * @param pathIdentity Unique identity of the Entity
     * @param pathSid A string that uniquely identifies this Factor.
     */
    public FactorUpdater(final String pathServiceSid,
                         final String pathIdentity,
                         final String pathSid) {
        this.pathServiceSid = pathServiceSid;
        this.pathIdentity = pathIdentity;
        this.pathSid = pathSid;
    }

    /**
     * The optional payload needed to verify the Factor for the first time. E.g. for
     * a TOTP, the numeric code..
     *
     * @param authPayload Optional payload to verify the Factor for the first time
     * @return this
     */
    public FactorUpdater setAuthPayload(final String authPayload) {
        this.authPayload = authPayload;
        return this;
    }

    /**
     * The new friendly name of this Factor.
     *
     * @param friendlyName The friendly name of this Factor
     * @return this
     */
    public FactorUpdater setFriendlyName(final String friendlyName) {
        this.friendlyName = friendlyName;
        return this;
    }

    /**
     * The new config for this Factor. It must be a json string with the required
     * properties for the given factor type.
     *
     * @param config The config for this Factor as a json string
     * @return this
     */
    public FactorUpdater setConfig(final String config) {
        this.config = config;
        return this;
    }

    /**
     * Make the request to the Twilio API to perform the update.
     *
     * @param client TwilioRestClient with which to make the request
     * @return Updated Factor
     */
    @Override
    @SuppressWarnings("checkstyle:linelength")
    public Factor update(final TwilioRestClient client) {
        Request request = new Request(
            HttpMethod.POST,
            Domains.AUTHY.toString(),
            "/v1/Services/" + this.pathServiceSid + "/Entities/" + this.pathIdentity + "/Factors/" + this.pathSid + "",
            client.getRegion()
        );

        addPostParams(request);
        Response response = client.request(request);

        if (response == null) {
            throw new ApiConnectionException("Factor update failed: Unable to connect to server");
        } else if (!TwilioRestClient.SUCCESS.apply(response.getStatusCode())) {
            RestException restException = RestException.fromJson(response.getStream(), client.getObjectMapper());
            if (restException == null) {
                throw new ApiException("Server Error, no content");
            }

            throw new ApiException(
                restException.getMessage(),
                restException.getCode(),
                restException.getMoreInfo(),
                restException.getStatus(),
                restException.getDetails(),
                null
            );
        }

        return Factor.fromJson(response.getStream(), client.getObjectMapper());
    }

    /**
     * Add the requested post parameters to the Request.
     *
     * @param request Request to add post params to
     */
    private void addPostParams(final Request request) {
        if (authPayload != null) {
            request.addPostParam("AuthPayload", authPayload);
        }

        if (friendlyName != null) {
            request.addPostParam("FriendlyName", friendlyName);
        }

        if (config != null) {
            request.addPostParam("Config", config);
        }
    }
}