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

package com.twilio.rest.numbers.v1;

import com.twilio.base.Deleter;
import com.twilio.constant.EnumConstants;
import com.twilio.exception.ApiConnectionException;
import com.twilio.exception.ApiException;
import com.twilio.exception.RestException;
import com.twilio.http.HttpMethod;
import com.twilio.http.Request;
import com.twilio.http.Response;
import com.twilio.http.TwilioRestClient;
import com.twilio.rest.Domains;

public class PortingPortInPhoneNumberDeleter
    extends Deleter<PortingPortInPhoneNumber> {

    private String pathPortInRequestSid;
    private String pathPhoneNumberSid;

    public PortingPortInPhoneNumberDeleter(
        final String pathPortInRequestSid,
        final String pathPhoneNumberSid
    ) {
        this.pathPortInRequestSid = pathPortInRequestSid;
        this.pathPhoneNumberSid = pathPhoneNumberSid;
    }

    @Override
    public boolean delete(final TwilioRestClient client) {
        String path =
            "/v1/Porting/PortIn/{PortInRequestSid}/PhoneNumber/{PhoneNumberSid}";

        path =
            path.replace(
                "{" + "PortInRequestSid" + "}",
                this.pathPortInRequestSid.toString()
            );
        path =
            path.replace(
                "{" + "PhoneNumberSid" + "}",
                this.pathPhoneNumberSid.toString()
            );

        Request request = new Request(
            HttpMethod.DELETE,
            Domains.NUMBERS.toString(),
            path
        );
        request.setContentType(EnumConstants.ContentType.FORM_URLENCODED);
        Response response = client.request(request);

        if (response == null) {
            throw new ApiConnectionException(
                "PortingPortInPhoneNumber delete failed: Unable to connect to server"
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
        return response.getStatusCode() == 204;
    }
}
