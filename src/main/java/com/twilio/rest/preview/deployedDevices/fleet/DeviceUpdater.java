/*
 * This code was generated by
 * ___ _ _ _ _ _    _ ____    ____ ____ _    ____ ____ _  _ ____ ____ ____ ___ __   __
 *  |  | | | | |    | |  | __ |  | |__| | __ | __ |___ |\ | |___ |__/ |__|  | |  | |__/
 *  |  |_|_| | |___ | |__|    |__| |  | |    |__] |___ | \| |___ |  \ |  |  | |__| |  \
 *
 * Twilio - Preview
 * This is the public Twilio REST API.
 *
 * NOTE: This class is auto generated by OpenAPI Generator.
 * https://openapi-generator.tech
 * Do not edit the class manually.
 */

package com.twilio.rest.preview.deployedDevices.fleet;

import com.twilio.base.Updater;
import com.twilio.constant.EnumConstants;
import com.twilio.exception.ApiConnectionException;
import com.twilio.exception.ApiException;
import com.twilio.exception.RestException;
import com.twilio.http.HttpMethod;
import com.twilio.http.Request;
import com.twilio.http.Response;
import com.twilio.http.TwilioRestClient;
import com.twilio.rest.Domains;

public class DeviceUpdater extends Updater<Device> {

    private String pathFleetSid;
    private String pathSid;
    private String friendlyName;
    private String identity;
    private String deploymentSid;
    private Boolean enabled;

    public DeviceUpdater(final String pathFleetSid, final String pathSid) {
        this.pathFleetSid = pathFleetSid;
        this.pathSid = pathSid;
    }

    public DeviceUpdater setFriendlyName(final String friendlyName) {
        this.friendlyName = friendlyName;
        return this;
    }

    public DeviceUpdater setIdentity(final String identity) {
        this.identity = identity;
        return this;
    }

    public DeviceUpdater setDeploymentSid(final String deploymentSid) {
        this.deploymentSid = deploymentSid;
        return this;
    }

    public DeviceUpdater setEnabled(final Boolean enabled) {
        this.enabled = enabled;
        return this;
    }

    @Override
    public Device update(final TwilioRestClient client) {
        String path = "/DeployedDevices/Fleets/{FleetSid}/Devices/{Sid}";

        path =
            path.replace("{" + "FleetSid" + "}", this.pathFleetSid.toString());
        path = path.replace("{" + "Sid" + "}", this.pathSid.toString());

        Request request = new Request(
            HttpMethod.POST,
            Domains.PREVIEW.toString(),
            path
        );
        request.setContentType(EnumConstants.ContentType.FORM_URLENCODED);
        addPostParams(request);
        Response response = client.request(request);
        if (response == null) {
            throw new ApiConnectionException(
                "Device update failed: Unable to connect to server"
            );
        } else if (!TwilioRestClient.SUCCESS.test(response.getStatusCode())) {
            RestException restException = RestException.fromJson(
                response.getStream(),
                client.getObjectMapper()
            );
            if (restException == null) {
                throw new ApiException(
                    "Server Error, no content",
                    response.getStatusCode()
                );
            }
            throw new ApiException(restException);
        }

        return Device.fromJson(response.getStream(), client.getObjectMapper());
    }

    private void addPostParams(final Request request) {
        if (friendlyName != null) {
            request.addPostParam("FriendlyName", friendlyName);
        }
        if (identity != null) {
            request.addPostParam("Identity", identity);
        }
        if (deploymentSid != null) {
            request.addPostParam("DeploymentSid", deploymentSid);
        }
        if (enabled != null) {
            request.addPostParam("Enabled", enabled.toString());
        }
    }
}
