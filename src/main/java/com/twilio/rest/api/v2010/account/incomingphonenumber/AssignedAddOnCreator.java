/*
 * This code was generated by
 * ___ _ _ _ _ _    _ ____    ____ ____ _    ____ ____ _  _ ____ ____ ____ ___ __   __
 *  |  | | | | |    | |  | __ |  | |__| | __ | __ |___ |\ | |___ |__/ |__|  | |  | |__/
 *  |  |_|_| | |___ | |__|    |__| |  | |    |__] |___ | \| |___ |  \ |  |  | |__| |  \
 *
 * Twilio - Api
 * This is the public Twilio REST API.
 *
 * NOTE: This class is auto generated by OpenAPI Generator.
 * https://openapi-generator.tech
 * Do not edit the class manually.
 */

package com.twilio.rest.api.v2010.account.incomingphonenumber;

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

public class AssignedAddOnCreator extends Creator<AssignedAddOn> {

    private String pathResourceSid;
    private String installedAddOnSid;
    private String pathAccountSid;

    public AssignedAddOnCreator(
        final String pathResourceSid,
        final String installedAddOnSid
    ) {
        this.pathResourceSid = pathResourceSid;
        this.installedAddOnSid = installedAddOnSid;
    }

    public AssignedAddOnCreator(
        final String pathAccountSid,
        final String pathResourceSid,
        final String installedAddOnSid
    ) {
        this.pathAccountSid = pathAccountSid;
        this.pathResourceSid = pathResourceSid;
        this.installedAddOnSid = installedAddOnSid;
    }

    public AssignedAddOnCreator setInstalledAddOnSid(
        final String installedAddOnSid
    ) {
        this.installedAddOnSid = installedAddOnSid;
        return this;
    }

    @Override
    public AssignedAddOn create(final TwilioRestClient client) {
        String path =
            "/2010-04-01/Accounts/{AccountSid}/IncomingPhoneNumbers/{ResourceSid}/AssignedAddOns.json";

        this.pathAccountSid =
            this.pathAccountSid == null
                ? client.getAccountSid()
                : this.pathAccountSid;
        path =
            path.replace(
                "{" + "AccountSid" + "}",
                this.pathAccountSid.toString()
            );
        path =
            path.replace(
                "{" + "ResourceSid" + "}",
                this.pathResourceSid.toString()
            );
        path =
            path.replace(
                "{" + "InstalledAddOnSid" + "}",
                this.installedAddOnSid.toString()
            );

        Request request = new Request(
            HttpMethod.POST,
            Domains.API.toString(),
            path
        );
        request.setContentType(EnumConstants.ContentType.FORM_URLENCODED);
        addPostParams(request);
        Response response = client.request(request);
        if (response == null) {
            throw new ApiConnectionException(
                "AssignedAddOn creation failed: Unable to connect to server"
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

        return AssignedAddOn.fromJson(
            response.getStream(),
            client.getObjectMapper()
        );
    }

    private void addPostParams(final Request request) {
        if (installedAddOnSid != null) {
            request.addPostParam("InstalledAddOnSid", installedAddOnSid);
        }
    }
}
