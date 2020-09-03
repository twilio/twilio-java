/**
 * This code was generated by
 * \ / _    _  _|   _  _
 *  | (_)\/(_)(_|\/| |(/_  v1.0.0
 *       /       /
 */

package com.twilio.rest.sync.v1;

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

import java.net.URI;

/**
 * PLEASE NOTE that this class contains beta products that are subject to
 * change. Use them with caution.
 */
public class ServiceUpdater extends Updater<Service> {
    private final String pathSid;
    private URI webhookUrl;
    private String friendlyName;
    private Boolean reachabilityWebhooksEnabled;
    private Boolean aclEnabled;
    private Boolean reachabilityDebouncingEnabled;
    private Integer reachabilityDebouncingWindow;
    private Boolean webhooksFromRestEnabled;

    /**
     * Construct a new ServiceUpdater.
     *
     * @param pathSid The SID of the Service resource to update
     */
    public ServiceUpdater(final String pathSid) {
        this.pathSid = pathSid;
    }

    /**
     * The URL we should call when Sync objects are manipulated..
     *
     * @param webhookUrl The URL we should call when Sync objects are manipulated
     * @return this
     */
    public ServiceUpdater setWebhookUrl(final URI webhookUrl) {
        this.webhookUrl = webhookUrl;
        return this;
    }

    /**
     * The URL we should call when Sync objects are manipulated..
     *
     * @param webhookUrl The URL we should call when Sync objects are manipulated
     * @return this
     */
    public ServiceUpdater setWebhookUrl(final String webhookUrl) {
        return setWebhookUrl(Promoter.uriFromString(webhookUrl));
    }

    /**
     * A string that you assign to describe the resource..
     *
     * @param friendlyName A string that you assign to describe the resource
     * @return this
     */
    public ServiceUpdater setFriendlyName(final String friendlyName) {
        this.friendlyName = friendlyName;
        return this;
    }

    /**
     * Whether the service instance should call `webhook_url` when client endpoints
     * connect to Sync. The default is `false`..
     *
     * @param reachabilityWebhooksEnabled Whether the service instance should call
     *                                    webhook_url when client endpoints connect
     *                                    to Sync
     * @return this
     */
    public ServiceUpdater setReachabilityWebhooksEnabled(final Boolean reachabilityWebhooksEnabled) {
        this.reachabilityWebhooksEnabled = reachabilityWebhooksEnabled;
        return this;
    }

    /**
     * Whether token identities in the Service must be granted access to Sync
     * objects by using the <a
     * href="https://www.twilio.com/docs/sync/api/sync-permissions">Permissions</a>
     * resource..
     *
     * @param aclEnabled Whether token identities in the Service must be granted
     *                   access to Sync objects by using the Permissions resource
     * @return this
     */
    public ServiceUpdater setAclEnabled(final Boolean aclEnabled) {
        this.aclEnabled = aclEnabled;
        return this;
    }

    /**
     * Whether every `endpoint_disconnected` event should occur after a configurable
     * delay. The default is `false`, where the `endpoint_disconnected` event occurs
     * immediately after disconnection. When `true`, intervening reconnections can
     * prevent the `endpoint_disconnected` event..
     *
     * @param reachabilityDebouncingEnabled Whether every endpoint_disconnected
     *                                      event occurs after a configurable delay
     * @return this
     */
    public ServiceUpdater setReachabilityDebouncingEnabled(final Boolean reachabilityDebouncingEnabled) {
        this.reachabilityDebouncingEnabled = reachabilityDebouncingEnabled;
        return this;
    }

    /**
     * The reachability event delay in milliseconds if
     * `reachability_debouncing_enabled` = `true`.  Must be between 1,000 and 30,000
     * and defaults to 5,000. This is the number of milliseconds after the last
     * running client disconnects, and a Sync identity is declared offline, before
     * the webhook is called if all endpoints remain offline. A reconnection from
     * the same identity by any endpoint during this interval prevents the webhook
     * from being called..
     *
     * @param reachabilityDebouncingWindow The reachability event delay in
     *                                     milliseconds
     * @return this
     */
    public ServiceUpdater setReachabilityDebouncingWindow(final Integer reachabilityDebouncingWindow) {
        this.reachabilityDebouncingWindow = reachabilityDebouncingWindow;
        return this;
    }

    /**
     * Whether the Service instance should call `webhook_url` when the REST API is
     * used to update Sync objects. The default is `false`..
     *
     * @param webhooksFromRestEnabled Whether the Service instance should call
     *                                webhook_url when the REST API is used to
     *                                update Sync objects
     * @return this
     */
    public ServiceUpdater setWebhooksFromRestEnabled(final Boolean webhooksFromRestEnabled) {
        this.webhooksFromRestEnabled = webhooksFromRestEnabled;
        return this;
    }

    /**
     * Make the request to the Twilio API to perform the update.
     *
     * @param client TwilioRestClient with which to make the request
     * @return Updated Service
     */
    @Override
    @SuppressWarnings("checkstyle:linelength")
    public Service update(final TwilioRestClient client) {
        Request request = new Request(
            HttpMethod.POST,
            Domains.SYNC.toString(),
            "/v1/Services/" + this.pathSid + ""
        );

        addPostParams(request);
        Response response = client.request(request);

        if (response == null) {
            throw new ApiConnectionException("Service update failed: Unable to connect to server");
        } else if (!TwilioRestClient.SUCCESS.test(response.getStatusCode())) {
            RestException restException = RestException.fromJson(response.getStream(), client.getObjectMapper());
            if (restException == null) {
                throw new ApiException("Server Error, no content");
            }
            throw new ApiException(restException);
        }

        return Service.fromJson(response.getStream(), client.getObjectMapper());
    }

    /**
     * Add the requested post parameters to the Request.
     *
     * @param request Request to add post params to
     */
    private void addPostParams(final Request request) {
        if (webhookUrl != null) {
            request.addPostParam("WebhookUrl", webhookUrl.toString());
        }

        if (friendlyName != null) {
            request.addPostParam("FriendlyName", friendlyName);
        }

        if (reachabilityWebhooksEnabled != null) {
            request.addPostParam("ReachabilityWebhooksEnabled", reachabilityWebhooksEnabled.toString());
        }

        if (aclEnabled != null) {
            request.addPostParam("AclEnabled", aclEnabled.toString());
        }

        if (reachabilityDebouncingEnabled != null) {
            request.addPostParam("ReachabilityDebouncingEnabled", reachabilityDebouncingEnabled.toString());
        }

        if (reachabilityDebouncingWindow != null) {
            request.addPostParam("ReachabilityDebouncingWindow", reachabilityDebouncingWindow.toString());
        }

        if (webhooksFromRestEnabled != null) {
            request.addPostParam("WebhooksFromRestEnabled", webhooksFromRestEnabled.toString());
        }
    }
}
