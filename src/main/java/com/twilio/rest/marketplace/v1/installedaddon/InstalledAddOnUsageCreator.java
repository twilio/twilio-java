/*
 * This code was generated by
 * ___ _ _ _ _ _    _ ____    ____ ____ _    ____ ____ _  _ ____ ____ ____ ___ __   __
 *  |  | | | | |    | |  | __ |  | |__| | __ | __ |___ |\ | |___ |__/ |__|  | |  | |__/
 *  |  |_|_| | |___ | |__|    |__| |  | |    |__] |___ | \| |___ |  \ |  |  | |__| |  \
 *
 * Twilio - Marketplace
 * This is the public Twilio REST API.
 *
 * NOTE: This class is auto generated by OpenAPI Generator.
 * https://openapi-generator.tech
 * Do not edit the class manually.
 */

package com.twilio.rest.marketplace.v1.installedaddon;

import com.fasterxml.jackson.databind.ObjectMapper;
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

public class InstalledAddOnUsageCreator extends Creator<InstalledAddOnUsage> {

    private String pathInstalledAddOnSid;
    private InstalledAddOnUsage.CreateMarketplaceBillingUsageRequest createMarketplaceBillingUsageRequest;

    public InstalledAddOnUsageCreator(
        final String pathInstalledAddOnSid,
        final InstalledAddOnUsage.CreateMarketplaceBillingUsageRequest createMarketplaceBillingUsageRequest
    ) {
        this.pathInstalledAddOnSid = pathInstalledAddOnSid;
        this.createMarketplaceBillingUsageRequest =
            createMarketplaceBillingUsageRequest;
    }

    public InstalledAddOnUsageCreator setCreateMarketplaceBillingUsageRequest(
        final InstalledAddOnUsage.CreateMarketplaceBillingUsageRequest createMarketplaceBillingUsageRequest
    ) {
        this.createMarketplaceBillingUsageRequest =
            createMarketplaceBillingUsageRequest;
        return this;
    }

    @Override
    public InstalledAddOnUsage create(final TwilioRestClient client) {
        String path = "/v1/InstalledAddOns/{InstalledAddOnSid}/Usage";

        path =
            path.replace(
                "{" + "InstalledAddOnSid" + "}",
                this.pathInstalledAddOnSid.toString()
            );
        path =
            path.replace(
                "{" + "CreateMarketplaceBillingUsageRequest" + "}",
                this.createMarketplaceBillingUsageRequest.toString()
            );

        Request request = new Request(
            HttpMethod.POST,
            Domains.MARKETPLACE.toString(),
            path
        );
        request.setContentType(EnumConstants.ContentType.JSON);
        addPostParams(request, client);
        Response response = client.request(request);
        if (response == null) {
            throw new ApiConnectionException(
                "InstalledAddOnUsage creation failed: Unable to connect to server"
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

        return InstalledAddOnUsage.fromJson(
            response.getStream(),
            client.getObjectMapper()
        );
    }

    private void addPostParams(final Request request, TwilioRestClient client) {
        ObjectMapper objectMapper = client.getObjectMapper();
        if (createMarketplaceBillingUsageRequest != null) {
            request.setBody(
                InstalledAddOnUsage.toJson(
                    createMarketplaceBillingUsageRequest,
                    objectMapper
                )
            );
        }
    }
}
