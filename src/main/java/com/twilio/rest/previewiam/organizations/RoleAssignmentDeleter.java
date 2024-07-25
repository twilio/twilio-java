/*
 * This code was generated by
 * ___ _ _ _ _ _    _ ____    ____ ____ _    ____ ____ _  _ ____ ____ ____ ___ __   __
 *  |  | | | | |    | |  | __ |  | |__| | __ | __ |___ |\ | |___ |__/ |__|  | |  | |__/
 *  |  |_|_| | |___ | |__|    |__| |  | |    |__] |___ | \| |___ |  \ |  |  | |__| |  \
 *
 * Organization Public API
 * No description provided (generated by Openapi Generator https://github.com/openapitools/openapi-generator)
 *
 * NOTE: This class is auto generated by OpenAPI Generator.
 * https://openapi-generator.tech
 * Do not edit the class manually.
 */

package com.twilio.rest.previewiam.organizations;

import com.twilio.base.bearertoken.Deleter;
import com.twilio.constant.EnumConstants;
import com.twilio.exception.ApiConnectionException;
import com.twilio.exception.ApiException;
import com.twilio.exception.RestException;
import com.twilio.http.HttpMethod;
import com.twilio.http.Response;
import com.twilio.http.bearertoken.BearerTokenRequest;
import com.twilio.http.bearertoken.BearerTokenTwilioRestClient;
import com.twilio.rest.Domains;

public class RoleAssignmentDeleter extends Deleter<RoleAssignment> {

    private String pathOrganizationSid;
    private String pathRoleAssignmentSid;

    public RoleAssignmentDeleter(
        final String pathOrganizationSid,
        final String pathRoleAssignmentSid
    ) {
        this.pathOrganizationSid = pathOrganizationSid;
        this.pathRoleAssignmentSid = pathRoleAssignmentSid;
    }

    @Override
    public boolean delete(final BearerTokenTwilioRestClient client) {
        String path =
            "/Organizations/{OrganizationSid}/RoleAssignments/{RoleAssignmentSid}";

        path =
            path.replace(
                "{" + "OrganizationSid" + "}",
                this.pathOrganizationSid.toString()
            );
        path =
            path.replace(
                "{" + "RoleAssignmentSid" + "}",
                this.pathRoleAssignmentSid.toString()
            );

        BearerTokenRequest request = new BearerTokenRequest(
            HttpMethod.DELETE,
            Domains.PREVIEWIAM.toString(),
            path
        );
        request.setContentType(EnumConstants.ContentType.FORM_URLENCODED);
        Response response = client.request(request);

        if (response == null) {
            throw new ApiConnectionException(
                "RoleAssignment delete failed: Unable to connect to server"
            );
        } else if (
            !BearerTokenTwilioRestClient.SUCCESS.test(response.getStatusCode())
        ) {
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