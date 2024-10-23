/*
 * This code was generated by
 * ___ _ _ _ _ _    _ ____    ____ ____ _    ____ ____ _  _ ____ ____ ____ ___ __   __
 *  |  | | | | |    | |  | __ |  | |__| | __ | __ |___ |\ | |___ |__/ |__|  | |  | |__/
 *  |  |_|_| | |___ | |__|    |__| |  | |    |__] |___ | \| |___ |  \ |  |  | |__| |  \
 *
 * Twilio - Assistants
 * This is the public Twilio REST API.
 *
 * NOTE: This class is auto generated by OpenAPI Generator.
 * https://openapi-generator.tech
 * Do not edit the class manually.
 */

package com.twilio.rest.assistants.v1.assistant;

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

public class AssistantsToolDeleter extends Deleter<AssistantsTool> {

    private String pathAssistantId;
    private String pathId;

    public AssistantsToolDeleter(
        final String pathAssistantId,
        final String pathId
    ) {
        this.pathAssistantId = pathAssistantId;
        this.pathId = pathId;
    }

    @Override
    public boolean delete(final TwilioRestClient client) {
        String path = "/v1/Assistants/{assistantId}/Tools/{id}";

        path =
            path.replace(
                "{" + "assistantId" + "}",
                this.pathAssistantId.toString()
            );
        path = path.replace("{" + "id" + "}", this.pathId.toString());

        Request request = new Request(
            HttpMethod.DELETE,
            Domains.ASSISTANTS.toString(),
            path
        );
        request.setContentType(EnumConstants.ContentType.FORM_URLENCODED);
        Response response = client.request(request);

        if (response == null) {
            throw new ApiConnectionException(
                "AssistantsTool delete failed: Unable to connect to server"
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
