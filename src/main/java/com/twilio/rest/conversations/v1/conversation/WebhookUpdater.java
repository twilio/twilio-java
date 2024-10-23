/*
 * This code was generated by
 * ___ _ _ _ _ _    _ ____    ____ ____ _    ____ ____ _  _ ____ ____ ____ ___ __   __
 *  |  | | | | |    | |  | __ |  | |__| | __ | __ |___ |\ | |___ |__/ |__|  | |  | |__/
 *  |  |_|_| | |___ | |__|    |__| |  | |    |__] |___ | \| |___ |  \ |  |  | |__| |  \
 *
 * Twilio - Conversations
 * This is the public Twilio REST API.
 *
 * NOTE: This class is auto generated by OpenAPI Generator.
 * https://openapi-generator.tech
 * Do not edit the class manually.
 */

package com.twilio.rest.conversations.v1.conversation;

import com.twilio.base.Updater;
import com.twilio.constant.EnumConstants;
import com.twilio.converter.Promoter;
import com.twilio.exception.ApiConnectionException;
import com.twilio.exception.ApiException;
import com.twilio.exception.RestException;
import com.twilio.http.HttpMethod;
import com.twilio.http.Request;
import com.twilio.http.Response;
import com.twilio.http.TwilioRestClient;
import com.twilio.rest.Domains;
import java.util.List;

public class WebhookUpdater extends Updater<Webhook> {

    private String pathConversationSid;
    private String pathSid;
    private String configurationUrl;
    private Webhook.Method configurationMethod;
    private List<String> configurationFilters;
    private List<String> configurationTriggers;
    private String configurationFlowSid;

    public WebhookUpdater(
        final String pathConversationSid,
        final String pathSid
    ) {
        this.pathConversationSid = pathConversationSid;
        this.pathSid = pathSid;
    }

    public WebhookUpdater setConfigurationUrl(final String configurationUrl) {
        this.configurationUrl = configurationUrl;
        return this;
    }

    public WebhookUpdater setConfigurationMethod(
        final Webhook.Method configurationMethod
    ) {
        this.configurationMethod = configurationMethod;
        return this;
    }

    public WebhookUpdater setConfigurationFilters(
        final List<String> configurationFilters
    ) {
        this.configurationFilters = configurationFilters;
        return this;
    }

    public WebhookUpdater setConfigurationFilters(
        final String configurationFilters
    ) {
        return setConfigurationFilters(
            Promoter.listOfOne(configurationFilters)
        );
    }

    public WebhookUpdater setConfigurationTriggers(
        final List<String> configurationTriggers
    ) {
        this.configurationTriggers = configurationTriggers;
        return this;
    }

    public WebhookUpdater setConfigurationTriggers(
        final String configurationTriggers
    ) {
        return setConfigurationTriggers(
            Promoter.listOfOne(configurationTriggers)
        );
    }

    public WebhookUpdater setConfigurationFlowSid(
        final String configurationFlowSid
    ) {
        this.configurationFlowSid = configurationFlowSid;
        return this;
    }

    @Override
    public Webhook update(final TwilioRestClient client) {
        String path = "/v1/Conversations/{ConversationSid}/Webhooks/{Sid}";

        path =
            path.replace(
                "{" + "ConversationSid" + "}",
                this.pathConversationSid.toString()
            );
        path = path.replace("{" + "Sid" + "}", this.pathSid.toString());

        Request request = new Request(
            HttpMethod.POST,
            Domains.CONVERSATIONS.toString(),
            path
        );
        request.setContentType(EnumConstants.ContentType.FORM_URLENCODED);
        addPostParams(request);
        Response response = client.request(request);
        if (response == null) {
            throw new ApiConnectionException(
                "Webhook update failed: Unable to connect to server"
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

        return Webhook.fromJson(response.getStream(), client.getObjectMapper());
    }

    private void addPostParams(final Request request) {
        if (configurationUrl != null) {
            request.addPostParam("Configuration.Url", configurationUrl);
        }
        if (configurationMethod != null) {
            request.addPostParam(
                "Configuration.Method",
                configurationMethod.toString()
            );
        }
        if (configurationFilters != null) {
            for (String prop : configurationFilters) {
                request.addPostParam("Configuration.Filters", prop);
            }
        }
        if (configurationTriggers != null) {
            for (String prop : configurationTriggers) {
                request.addPostParam("Configuration.Triggers", prop);
            }
        }
        if (configurationFlowSid != null) {
            request.addPostParam("Configuration.FlowSid", configurationFlowSid);
        }
    }
}
