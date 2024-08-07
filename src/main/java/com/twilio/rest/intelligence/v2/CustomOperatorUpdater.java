/*
 * This code was generated by
 * ___ _ _ _ _ _    _ ____    ____ ____ _    ____ ____ _  _ ____ ____ ____ ___ __   __
 *  |  | | | | |    | |  | __ |  | |__| | __ | __ |___ |\ | |___ |__/ |__|  | |  | |__/
 *  |  |_|_| | |___ | |__|    |__| |  | |    |__] |___ | \| |___ |  \ |  |  | |__| |  \
 *
 * Twilio - Intelligence
 * This is the public Twilio REST API.
 *
 * NOTE: This class is auto generated by OpenAPI Generator.
 * https://openapi-generator.tech
 * Do not edit the class manually.
 */

package com.twilio.rest.intelligence.v2;

import com.twilio.base.Updater;
import com.twilio.constant.EnumConstants;
import com.twilio.converter.Converter;
import com.twilio.exception.ApiConnectionException;
import com.twilio.exception.ApiException;
import com.twilio.exception.RestException;
import com.twilio.http.HttpMethod;
import com.twilio.http.Request;
import com.twilio.http.Response;
import com.twilio.http.TwilioRestClient;
import com.twilio.rest.Domains;
import java.util.Map;

public class CustomOperatorUpdater extends Updater<CustomOperator> {

    private String pathSid;
    private String friendlyName;
    private Map<String, Object> config;
    private String ifMatch;

    public CustomOperatorUpdater(
        final String pathSid,
        final String friendlyName,
        final Map<String, Object> config
    ) {
        this.pathSid = pathSid;
        this.friendlyName = friendlyName;
        this.config = config;
    }

    public CustomOperatorUpdater setFriendlyName(final String friendlyName) {
        this.friendlyName = friendlyName;
        return this;
    }

    public CustomOperatorUpdater setConfig(final Map<String, Object> config) {
        this.config = config;
        return this;
    }

    public CustomOperatorUpdater setIfMatch(final String ifMatch) {
        this.ifMatch = ifMatch;
        return this;
    }

    @Override
    public CustomOperator update(final TwilioRestClient client) {
        String path = "/v2/Operators/Custom/{Sid}";

        path = path.replace("{" + "Sid" + "}", this.pathSid.toString());
        path =
            path.replace(
                "{" + "FriendlyName" + "}",
                this.friendlyName.toString()
            );
        path = path.replace("{" + "Config" + "}", this.config.toString());

        Request request = new Request(
            HttpMethod.POST,
            Domains.INTELLIGENCE.toString(),
            path
        );
        request.setContentType(EnumConstants.ContentType.FORM_URLENCODED);
        addPostParams(request);
        addHeaderParams(request);
        Response response = client.request(request);
        if (response == null) {
            throw new ApiConnectionException(
                "CustomOperator update failed: Unable to connect to server"
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

        return CustomOperator.fromJson(
            response.getStream(),
            client.getObjectMapper()
        );
    }

    private void addPostParams(final Request request) {
        if (friendlyName != null) {
            request.addPostParam("FriendlyName", friendlyName);
        }
        if (config != null) {
            request.addPostParam("Config", Converter.mapToJson(config));
        }
    }

    private void addHeaderParams(final Request request) {
        if (ifMatch != null) {
            request.addHeaderParam("If-Match", ifMatch);
        }
    }
}
