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

package com.twilio.rest.marketplace.v1;

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

public class ModuleDataCreator extends Creator<ModuleData> {

    private String moduleInfo;
    private String configuration;

    public ModuleDataCreator() {}

    public ModuleDataCreator setModuleInfo(final String moduleInfo) {
        this.moduleInfo = moduleInfo;
        return this;
    }

    public ModuleDataCreator setConfiguration(final String configuration) {
        this.configuration = configuration;
        return this;
    }

    @Override
    public ModuleData create(final TwilioRestClient client) {
        String path = "/v1/Listings";

        Request request = new Request(
            HttpMethod.POST,
            Domains.MARKETPLACE.toString(),
            path
        );
        request.setContentType(EnumConstants.ContentType.FORM_URLENCODED);
        addPostParams(request);
        Response response = client.request(request);
        if (response == null) {
            throw new ApiConnectionException(
                "ModuleData creation failed: Unable to connect to server"
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

        return ModuleData.fromJson(
            response.getStream(),
            client.getObjectMapper()
        );
    }

    private void addPostParams(final Request request) {
        if (moduleInfo != null) {
            request.addPostParam("ModuleInfo", moduleInfo);
        }
        if (configuration != null) {
            request.addPostParam("Configuration", configuration);
        }
    }
}
