/*
 * This code was generated by
 * ___ _ _ _ _ _    _ ____    ____ ____ _    ____ ____ _  _ ____ ____ ____ ___ __   __
 *  |  | | | | |    | |  | __ |  | |__| | __ | __ |___ |\ | |___ |__/ |__|  | |  | |__/
 *  |  |_|_| | |___ | |__|    |__| |  | |    |__] |___ | \| |___ |  \ |  |  | |__| |  \
 *
 * Twilio - Numbers
 * This is the public Twilio REST API.
 *
 * NOTE: This class is auto generated by OpenAPI Generator.
 * https://openapi-generator.tech
 * Do not edit the class manually.
 */

package com.twilio.rest.numbers.v2;

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

public class HostedNumberOrderUpdater extends Updater<HostedNumberOrder> {

    private String pathSid;
    private HostedNumberOrder.Status status;
    private Integer verificationCallDelay;
    private String verificationCallExtension;

    public HostedNumberOrderUpdater(
        final String pathSid,
        final HostedNumberOrder.Status status
    ) {
        this.pathSid = pathSid;
        this.status = status;
    }

    public HostedNumberOrderUpdater setStatus(
        final HostedNumberOrder.Status status
    ) {
        this.status = status;
        return this;
    }

    public HostedNumberOrderUpdater setVerificationCallDelay(
        final Integer verificationCallDelay
    ) {
        this.verificationCallDelay = verificationCallDelay;
        return this;
    }

    public HostedNumberOrderUpdater setVerificationCallExtension(
        final String verificationCallExtension
    ) {
        this.verificationCallExtension = verificationCallExtension;
        return this;
    }

    @Override
    public HostedNumberOrder update(final TwilioRestClient client) {
        String path = "/v2/HostedNumber/Orders/{Sid}";

        path = path.replace("{" + "Sid" + "}", this.pathSid.toString());
        path = path.replace("{" + "Status" + "}", this.status.toString());

        Request request = new Request(
            HttpMethod.POST,
            Domains.NUMBERS.toString(),
            path
        );
        request.setContentType(EnumConstants.ContentType.FORM_URLENCODED);
        addPostParams(request);
        Response response = client.request(request);
        if (response == null) {
            throw new ApiConnectionException(
                "HostedNumberOrder update failed: Unable to connect to server"
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

        return HostedNumberOrder.fromJson(
            response.getStream(),
            client.getObjectMapper()
        );
    }

    private void addPostParams(final Request request) {
        if (status != null) {
            request.addPostParam("Status", status.toString());
        }
        if (verificationCallDelay != null) {
            request.addPostParam(
                "VerificationCallDelay",
                verificationCallDelay.toString()
            );
        }
        if (verificationCallExtension != null) {
            request.addPostParam(
                "VerificationCallExtension",
                verificationCallExtension
            );
        }
    }
}
