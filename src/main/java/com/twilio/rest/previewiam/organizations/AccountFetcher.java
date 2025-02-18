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

import com.twilio.base.Fetcher;
import com.twilio.constant.EnumConstants;
import com.twilio.exception.ApiConnectionException;
import com.twilio.exception.ApiException;
import com.twilio.exception.RestException;
import com.twilio.http.HttpMethod;
import com.twilio.http.Response;
import com.twilio.http.Request;
import com.twilio.http.TwilioRestClient;
import com.twilio.rest.Domains;

public class AccountFetcher extends Fetcher<Account> {

    private String pathOrganizationSid;
    private String pathAccountSid;

    public AccountFetcher(
        final String pathOrganizationSid,
        final String pathAccountSid
    ) {
        this.pathOrganizationSid = pathOrganizationSid;
        this.pathAccountSid = pathAccountSid;
    }

    @Override
    public Account fetch(final TwilioRestClient client) {
        String path = "/Organizations/{OrganizationSid}/Accounts/{AccountSid}";

        path =
            path.replace(
                "{" + "OrganizationSid" + "}",
                this.pathOrganizationSid.toString()
            );
        path =
            path.replace(
                "{" + "AccountSid" + "}",
                this.pathAccountSid.toString()
            );

        Request request = new Request(
            HttpMethod.GET,
            Domains.PREVIEWIAM.toString(),
            path
        );
        request.setContentType(EnumConstants.ContentType.FORM_URLENCODED);
        Response response = client.request(request);

        if (response == null) {
            throw new ApiConnectionException(
                "Account fetch failed: Unable to connect to server"
            );
        } else if (
            !TwilioRestClient.SUCCESS.test(response.getStatusCode())
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

        return Account.fromJson(response.getStream(), client.getObjectMapper());
    }
}
