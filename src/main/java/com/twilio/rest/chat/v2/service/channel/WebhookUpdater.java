/**
 * This code was generated by
 * \ / _    _  _|   _  _
 *  | (_)\/(_)(_|\/| |(/_  v1.0.0
 *       /       /
 */

package com.twilio.rest.chat.v2.service.channel;

import com.twilio.base.Updater;
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
    private final String pathServiceSid;
    private final String pathChannelSid;
    private final String pathSid;
    private String configurationUrl;
    private Webhook.Method configurationMethod;
    private List<String> configurationFilters;
    private List<String> configurationTriggers;
    private String configurationFlowSid;
    private Integer configurationRetryCount;

    /**
     * Construct a new WebhookUpdater.
     *
     * @param pathServiceSid The SID of the Service with the Channel that has the
     *                       Webhook resource to update
     * @param pathChannelSid The SID of the Channel the resource to update belongs
     *                       to
     * @param pathSid The SID of the resource
     */
    public WebhookUpdater(final String pathServiceSid,
                          final String pathChannelSid,
                          final String pathSid) {
        this.pathServiceSid = pathServiceSid;
        this.pathChannelSid = pathChannelSid;
        this.pathSid = pathSid;
    }

    /**
     * The URL of the webhook to call using the `configuration.method`..
     *
     * @param configurationUrl The URL of the webhook to call
     * @return this
     */
    public WebhookUpdater setConfigurationUrl(final String configurationUrl) {
        this.configurationUrl = configurationUrl;
        return this;
    }

    /**
     * The HTTP method used to call `configuration.url`. Can be: `GET` or `POST`
     * and the default is `POST`..
     *
     * @param configurationMethod The HTTP method used to call `configuration.url`
     * @return this
     */
    public WebhookUpdater setConfigurationMethod(final Webhook.Method configurationMethod) {
        this.configurationMethod = configurationMethod;
        return this;
    }

    /**
     * The events that cause us to call the Channel Webhook. Used when `type` is
     * `webhook`. This parameter takes only one event. To specify more than one
     * event, repeat this parameter for each event. For the list of possible
     * events, see <a
     * href="https://www.twilio.com/docs/chat/webhook-events#webhook-event-trigger">Webhook
     * Event Triggers</a>..
     *
     * @param configurationFilters The events that cause us to call the Channel
     *                             Webhook
     * @return this
     */
    public WebhookUpdater setConfigurationFilters(final List<String> configurationFilters) {
        this.configurationFilters = configurationFilters;
        return this;
    }

    /**
     * The events that cause us to call the Channel Webhook. Used when `type` is
     * `webhook`. This parameter takes only one event. To specify more than one
     * event, repeat this parameter for each event. For the list of possible
     * events, see <a
     * href="https://www.twilio.com/docs/chat/webhook-events#webhook-event-trigger">Webhook
     * Event Triggers</a>..
     *
     * @param configurationFilters The events that cause us to call the Channel
     *                             Webhook
     * @return this
     */
    public WebhookUpdater setConfigurationFilters(final String configurationFilters) {
        return setConfigurationFilters(Promoter.listOfOne(configurationFilters));
    }

    /**
     * A string that will cause us to call the webhook when it is present in a
     * message body. This parameter takes only one trigger string. To specify more
     * than one, repeat this parameter for each trigger string up to a total of 5
     * trigger strings. Used only when `type` = `trigger`..
     *
     * @param configurationTriggers A string that will cause us to call the webhook
     *                              when it is found in a message body
     * @return this
     */
    public WebhookUpdater setConfigurationTriggers(final List<String> configurationTriggers) {
        this.configurationTriggers = configurationTriggers;
        return this;
    }

    /**
     * A string that will cause us to call the webhook when it is present in a
     * message body. This parameter takes only one trigger string. To specify more
     * than one, repeat this parameter for each trigger string up to a total of 5
     * trigger strings. Used only when `type` = `trigger`..
     *
     * @param configurationTriggers A string that will cause us to call the webhook
     *                              when it is found in a message body
     * @return this
     */
    public WebhookUpdater setConfigurationTriggers(final String configurationTriggers) {
        return setConfigurationTriggers(Promoter.listOfOne(configurationTriggers));
    }

    /**
     * The SID of the Studio <a
     * href="https://www.twilio.com/docs/studio/rest-api/flow">Flow</a> to call
     * when an event in `configuration.filters` occurs. Used only when `type` =
     * `studio`..
     *
     * @param configurationFlowSid The SID of the Studio Flow to call when an event
     *                             occurs
     * @return this
     */
    public WebhookUpdater setConfigurationFlowSid(final String configurationFlowSid) {
        this.configurationFlowSid = configurationFlowSid;
        return this;
    }

    /**
     * The number of times to retry the webhook if the first attempt fails. Can be
     * an integer between 0 and 3, inclusive, and the default is 0..
     *
     * @param configurationRetryCount The number of times to retry the webhook if
     *                                the first attempt fails
     * @return this
     */
    public WebhookUpdater setConfigurationRetryCount(final Integer configurationRetryCount) {
        this.configurationRetryCount = configurationRetryCount;
        return this;
    }

    /**
     * Make the request to the Twilio API to perform the update.
     *
     * @param client TwilioRestClient with which to make the request
     * @return Updated Webhook
     */
    @Override
    @SuppressWarnings("checkstyle:linelength")
    public Webhook update(final TwilioRestClient client) {
        Request request = new Request(
            HttpMethod.POST,
            Domains.CHAT.toString(),
            "/v2/Services/" + this.pathServiceSid + "/Channels/" + this.pathChannelSid + "/Webhooks/" + this.pathSid + ""
        );

        addPostParams(request);
        Response response = client.request(request);

        if (response == null) {
            throw new ApiConnectionException("Webhook update failed: Unable to connect to server");
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

        if (configurationRetryCount != null) {
            request.addPostParam("Configuration.RetryCount", configurationRetryCount.toString());
        }
    }
}