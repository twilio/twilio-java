/**
 * This code was generated by
 * \ / _    _  _|   _  _
 *  | (_)\/(_)(_|\/| |(/_  v1.0.0
 *       /       /
 */

package com.twilio.rest.conversations.v1.service.conversation;

import com.twilio.base.Creator;
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

/**
 * PLEASE NOTE that this class contains beta products that are subject to
 * change. Use them with caution.
 */
public class WebhookCreator extends Creator<Webhook> {
    private final String pathChatServiceSid;
    private final String pathConversationSid;
    private final Webhook.Target target;
    private String configurationUrl;
    private Webhook.Method configurationMethod;
    private List<String> configurationFilters;
    private List<String> configurationTriggers;
    private String configurationFlowSid;
    private Integer configurationReplayAfter;

    /**
     * Construct a new WebhookCreator.
     *
     * @param pathChatServiceSid The SID of the Chat Service that the resource is
     *                           associated with.
     * @param pathConversationSid The unique id of the Conversation for this
     *                            webhook.
     * @param target The target of this webhook.
     */
    public WebhookCreator(final String pathChatServiceSid,
                          final String pathConversationSid,
                          final Webhook.Target target) {
        this.pathChatServiceSid = pathChatServiceSid;
        this.pathConversationSid = pathConversationSid;
        this.target = target;
    }

    /**
     * The absolute url the webhook request should be sent to..
     *
     * @param configurationUrl The absolute url the webhook request should be sent
     *                         to.
     * @return this
     */
    public WebhookCreator setConfigurationUrl(final String configurationUrl) {
        this.configurationUrl = configurationUrl;
        return this;
    }

    /**
     * The HTTP method to be used when sending a webhook request..
     *
     * @param configurationMethod The HTTP method to be used when sending a webhook
     *                            request.
     * @return this
     */
    public WebhookCreator setConfigurationMethod(final Webhook.Method configurationMethod) {
        this.configurationMethod = configurationMethod;
        return this;
    }

    /**
     * The list of events, firing webhook event for this Conversation..
     *
     * @param configurationFilters The list of events, firing webhook event for
     *                             this Conversation.
     * @return this
     */
    public WebhookCreator setConfigurationFilters(final List<String> configurationFilters) {
        this.configurationFilters = configurationFilters;
        return this;
    }

    /**
     * The list of events, firing webhook event for this Conversation..
     *
     * @param configurationFilters The list of events, firing webhook event for
     *                             this Conversation.
     * @return this
     */
    public WebhookCreator setConfigurationFilters(final String configurationFilters) {
        return setConfigurationFilters(Promoter.listOfOne(configurationFilters));
    }

    /**
     * The list of keywords, firing webhook event for this Conversation..
     *
     * @param configurationTriggers The list of keywords, firing webhook event for
     *                              this Conversation.
     * @return this
     */
    public WebhookCreator setConfigurationTriggers(final List<String> configurationTriggers) {
        this.configurationTriggers = configurationTriggers;
        return this;
    }

    /**
     * The list of keywords, firing webhook event for this Conversation..
     *
     * @param configurationTriggers The list of keywords, firing webhook event for
     *                              this Conversation.
     * @return this
     */
    public WebhookCreator setConfigurationTriggers(final String configurationTriggers) {
        return setConfigurationTriggers(Promoter.listOfOne(configurationTriggers));
    }

    /**
     * The studio flow sid, where the webhook should be sent to..
     *
     * @param configurationFlowSid The studio flow sid, where the webhook should be
     *                             sent to.
     * @return this
     */
    public WebhookCreator setConfigurationFlowSid(final String configurationFlowSid) {
        this.configurationFlowSid = configurationFlowSid;
        return this;
    }

    /**
     * The message index for which and it's successors the webhook will be replayed.
     * Not set by default.
     *
     * @param configurationReplayAfter The message index for which and it's
     *                                 successors the webhook will be replayed.
     * @return this
     */
    public WebhookCreator setConfigurationReplayAfter(final Integer configurationReplayAfter) {
        this.configurationReplayAfter = configurationReplayAfter;
        return this;
    }

    /**
     * Make the request to the Twilio API to perform the create.
     *
     * @param client TwilioRestClient with which to make the request
     * @return Created Webhook
     */
    @Override
    @SuppressWarnings("checkstyle:linelength")
    public Webhook create(final TwilioRestClient client) {
        Request request = new Request(
            HttpMethod.POST,
            Domains.CONVERSATIONS.toString(),
            "/v1/Services/" + this.pathChatServiceSid + "/Conversations/" + this.pathConversationSid + "/Webhooks"
        );

        addPostParams(request);
        Response response = client.request(request);

        if (response == null) {
            throw new ApiConnectionException("Webhook creation failed: Unable to connect to server");
        } else if (!TwilioRestClient.SUCCESS.apply(response.getStatusCode())) {
            RestException restException = RestException.fromJson(response.getStream(), client.getObjectMapper());
            if (restException == null) {
                throw new ApiException("Server Error, no content");
            }
            throw new ApiException(restException);
        }

        return Webhook.fromJson(response.getStream(), client.getObjectMapper());
    }

    /**
     * Add the requested post parameters to the Request.
     *
     * @param request Request to add post params to
     */
    private void addPostParams(final Request request) {
        if (target != null) {
            request.addPostParam("Target", target.toString());
        }

        if (configurationUrl != null) {
            request.addPostParam("Configuration.Url", configurationUrl);
        }

        if (configurationMethod != null) {
            request.addPostParam("Configuration.Method", configurationMethod.toString());
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

        if (configurationReplayAfter != null) {
            request.addPostParam("Configuration.ReplayAfter", configurationReplayAfter.toString());
        }
    }
}