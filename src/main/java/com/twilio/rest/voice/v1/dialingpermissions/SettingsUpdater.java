/**
 * This code was generated by
 * \ / _    _  _|   _  _
 *  | (_)\/(_)(_|\/| |(/_  v1.0.0
 *       /       /
 */

package com.twilio.rest.voice.v1.dialingpermissions;

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
public class SettingsUpdater extends Updater<Settings> {
    private Boolean dialingPermissionsInheritance;

    /**
     * `true` for the sub-account to inherit voice dialing permissions from the
     * Master Project; otherwise `false`..
     *
     * @param dialingPermissionsInheritance `true` for the sub-account to inherit
     *                                      voice dialing permissions from the
     *                                      Master Project; otherwise `false`
     * @return this
     */
    public SettingsUpdater setDialingPermissionsInheritance(final Boolean dialingPermissionsInheritance) {
        this.dialingPermissionsInheritance = dialingPermissionsInheritance;
        return this;
    }

    /**
     * Make the request to the Twilio API to perform the update.
     *
     * @param client TwilioRestClient with which to make the request
     * @return Updated Settings
     */
    @Override
    @SuppressWarnings("checkstyle:linelength")
    public Settings update(final TwilioRestClient client) {
        Request request = new Request(
            HttpMethod.POST,
            Domains.VOICE.toString(),
            "/v1/Settings",
            client.getRegion()
        );

        addPostParams(request);
        Response response = client.request(request);

        if (response == null) {
            throw new ApiConnectionException("Settings update failed: Unable to connect to server");
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

        return Settings.fromJson(response.getStream(), client.getObjectMapper());
    }

    /**
     * Add the requested post parameters to the Request.
     *
     * @param request Request to add post params to
     */
    private void addPostParams(final Request request) {
        if (dialingPermissionsInheritance != null) {
            request.addPostParam("DialingPermissionsInheritance", dialingPermissionsInheritance.toString());
        }
    }
}