/**
 * This code was generated by
 * \ / _    _  _|   _  _
 *  | (_)\/(_)(_|\/| |(/_  v1.0.0
 *       /       /
 */

package com.twilio.rest.preview.deployedDevices.fleet;

import com.twilio.base.Creator;
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
public class DeploymentCreator extends Creator<Deployment> {
    private final String pathFleetSid;
    private String friendlyName;
    private String syncServiceSid;

    /**
     * Construct a new DeploymentCreator.
     *
     * @param pathFleetSid The fleet_sid
     */
    public DeploymentCreator(final String pathFleetSid) {
        this.pathFleetSid = pathFleetSid;
    }

    /**
     * Provides a human readable descriptive text for this Deployment, up to 256
     * characters long..
     *
     * @param friendlyName A human readable description for this Deployment.
     * @return this
     */
    public DeploymentCreator setFriendlyName(final String friendlyName) {
        this.friendlyName = friendlyName;
        return this;
    }

    /**
     * Provides the unique string identifier of the Twilio Sync service instance
     * that will be linked to and accessible by this Deployment..
     *
     * @param syncServiceSid The unique identifier of the Sync service instance.
     * @return this
     */
    public DeploymentCreator setSyncServiceSid(final String syncServiceSid) {
        this.syncServiceSid = syncServiceSid;
        return this;
    }

    /**
     * Make the request to the Twilio API to perform the create.
     *
     * @param client TwilioRestClient with which to make the request
     * @return Created Deployment
     */
    @Override
    @SuppressWarnings("checkstyle:linelength")
    public Deployment create(final TwilioRestClient client) {
        Request request = new Request(
            HttpMethod.POST,
            Domains.PREVIEW.toString(),
            "/DeployedDevices/Fleets/" + this.pathFleetSid + "/Deployments",
            client.getRegion()
        );

        addPostParams(request);
        Response response = client.request(request);

        if (response == null) {
            throw new ApiConnectionException("Deployment creation failed: Unable to connect to server");
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

        return Deployment.fromJson(response.getStream(), client.getObjectMapper());
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

        if (syncServiceSid != null) {
            request.addPostParam("SyncServiceSid", syncServiceSid);
        }
    }
}