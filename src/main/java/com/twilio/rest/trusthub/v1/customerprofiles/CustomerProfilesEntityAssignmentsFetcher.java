/*
 * This code was generated by
 * ___ _ _ _ _ _    _ ____    ____ ____ _    ____ ____ _  _ ____ ____ ____ ___ __   __
 *  |  | | | | |    | |  | __ |  | |__| | __ | __ |___ |\ | |___ |__/ |__|  | |  | |__/
 *  |  |_|_| | |___ | |__|    |__| |  | |    |__] |___ | \| |___ |  \ |  |  | |__| |  \
 *
 * Twilio - Trusthub
 * This is the public Twilio REST API.
 *
 * NOTE: This class is auto generated by OpenAPI Generator.
 * https://openapi-generator.tech
 * Do not edit the class manually.
 */

package com.twilio.rest.trusthub.v1.customerprofiles;

import com.twilio.base.Fetcher;
import com.twilio.constant.EnumConstants;
import com.twilio.exception.ApiConnectionException;
import com.twilio.exception.ApiException;
import com.twilio.exception.RestException;
import com.twilio.http.HttpMethod;
import com.twilio.http.Request;
import com.twilio.http.Response;
import com.twilio.http.TwilioRestClient;
import com.twilio.rest.Domains;

public class CustomerProfilesEntityAssignmentsFetcher
    extends Fetcher<CustomerProfilesEntityAssignments> {

    private String pathCustomerProfileSid;
    private String pathSid;

    public CustomerProfilesEntityAssignmentsFetcher(
        final String pathCustomerProfileSid,
        final String pathSid
    ) {
        this.pathCustomerProfileSid = pathCustomerProfileSid;
        this.pathSid = pathSid;
    }

    @Override
    public CustomerProfilesEntityAssignments fetch(
        final TwilioRestClient client
    ) {
        String path =
            "/v1/CustomerProfiles/{CustomerProfileSid}/EntityAssignments/{Sid}";

        path =
            path.replace(
                "{" + "CustomerProfileSid" + "}",
                this.pathCustomerProfileSid.toString()
            );
        path = path.replace("{" + "Sid" + "}", this.pathSid.toString());

        Request request = new Request(
            HttpMethod.GET,
            Domains.TRUSTHUB.toString(),
            path
        );
        request.setContentType(EnumConstants.ContentType.FORM_URLENCODED);
        Response response = client.request(request);

        if (response == null) {
            throw new ApiConnectionException(
                "CustomerProfilesEntityAssignments fetch failed: Unable to connect to server"
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

        return CustomerProfilesEntityAssignments.fromJson(
            response.getStream(),
            client.getObjectMapper()
        );
    }
}
