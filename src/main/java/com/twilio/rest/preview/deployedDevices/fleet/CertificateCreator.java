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

import com.twilio.base.Creator;
import com.twilio.constant.EnumConstants;
import com.twilio.exception.ApiConnectionException;
import com.twilio.exception.ApiException;
import com.twilio.exception.RestException;
import com.twilio.http.HttpMethod;
import com.twilio.http.Request;
import com.twilio.http.Response;
import com.twilio.http.TwilioRestClient;
import com.twilio.rest.Domains;

public class CertificateCreator extends Creator<Certificate> {

    private String pathFleetSid;
    private String certificateData;
    private String friendlyName;
    private String deviceSid;

    public CertificateCreator(
        final String pathFleetSid,
        final String certificateData
    ) {
        this.pathFleetSid = pathFleetSid;
        this.certificateData = certificateData;
    }

    public CertificateCreator setCertificateData(final String certificateData) {
        this.certificateData = certificateData;
        return this;
    }

    public CertificateCreator setFriendlyName(final String friendlyName) {
        this.friendlyName = friendlyName;
        return this;
    }

    public CertificateCreator setDeviceSid(final String deviceSid) {
        this.deviceSid = deviceSid;
        return this;
    }

    @Override
    public Certificate create(final TwilioRestClient client) {
        String path = "/DeployedDevices/Fleets/{FleetSid}/Certificates";

        path =
            path.replace("{" + "FleetSid" + "}", this.pathFleetSid.toString());
        path =
            path.replace(
                "{" + "CertificateData" + "}",
                this.certificateData.toString()
            );

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
                "Certificate creation failed: Unable to connect to server"
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

        return Certificate.fromJson(
            response.getStream(),
            client.getObjectMapper()
        );
    }

    private void addPostParams(final Request request) {
        if (certificateData != null) {
            request.addPostParam("CertificateData", certificateData);
        }
        if (friendlyName != null) {
            request.addPostParam("FriendlyName", friendlyName);
        }
        if (deviceSid != null) {
            request.addPostParam("DeviceSid", deviceSid);
        }
    }
}
