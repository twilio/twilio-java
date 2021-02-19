/**
 * This code was generated by
 * \ / _    _  _|   _  _
 *  | (_)\/(_)(_|\/| |(/_  v1.0.0
 *       /       /
 */

package com.twilio.rest.ipmessaging.v2.service;

import com.twilio.base.Updater;
import com.twilio.converter.DateConverter;
import com.twilio.exception.ApiConnectionException;
import com.twilio.exception.ApiException;
import com.twilio.exception.RestException;
import com.twilio.http.HttpMethod;
import com.twilio.http.Request;
import com.twilio.http.Response;
import com.twilio.http.TwilioRestClient;
import com.twilio.rest.Domains;

import java.time.ZonedDateTime;

public class ChannelUpdater extends Updater<Channel> {
    private final String pathServiceSid;
    private final String pathSid;
    private String friendlyName;
    private String uniqueName;
    private String attributes;
    private ZonedDateTime dateCreated;
    private ZonedDateTime dateUpdated;
    private String createdBy;
    private Channel.WebhookEnabledType xTwilioWebhookEnabled;

    /**
     * Construct a new ChannelUpdater.
     *
     * @param pathServiceSid The service_sid
     * @param pathSid The sid
     */
    public ChannelUpdater(final String pathServiceSid,
                          final String pathSid) {
        this.pathServiceSid = pathServiceSid;
        this.pathSid = pathSid;
    }

    /**
     * The friendly_name.
     *
     * @param friendlyName The friendly_name
     * @return this
     */
    public ChannelUpdater setFriendlyName(final String friendlyName) {
        this.friendlyName = friendlyName;
        return this;
    }

    /**
     * The unique_name.
     *
     * @param uniqueName The unique_name
     * @return this
     */
    public ChannelUpdater setUniqueName(final String uniqueName) {
        this.uniqueName = uniqueName;
        return this;
    }

    /**
     * The attributes.
     *
     * @param attributes The attributes
     * @return this
     */
    public ChannelUpdater setAttributes(final String attributes) {
        this.attributes = attributes;
        return this;
    }

    /**
     * The date_created.
     *
     * @param dateCreated The date_created
     * @return this
     */
    public ChannelUpdater setDateCreated(final ZonedDateTime dateCreated) {
        this.dateCreated = dateCreated;
        return this;
    }

    /**
     * The date_updated.
     *
     * @param dateUpdated The date_updated
     * @return this
     */
    public ChannelUpdater setDateUpdated(final ZonedDateTime dateUpdated) {
        this.dateUpdated = dateUpdated;
        return this;
    }

    /**
     * The created_by.
     *
     * @param createdBy The created_by
     * @return this
     */
    public ChannelUpdater setCreatedBy(final String createdBy) {
        this.createdBy = createdBy;
        return this;
    }

    /**
     * The X-Twilio-Webhook-Enabled HTTP request header.
     *
     * @param xTwilioWebhookEnabled The X-Twilio-Webhook-Enabled HTTP request header
     * @return this
     */
    public ChannelUpdater setXTwilioWebhookEnabled(final Channel.WebhookEnabledType xTwilioWebhookEnabled) {
        this.xTwilioWebhookEnabled = xTwilioWebhookEnabled;
        return this;
    }

    /**
     * Make the request to the Twilio API to perform the update.
     *
     * @param client TwilioRestClient with which to make the request
     * @return Updated Channel
     */
    @Override
    @SuppressWarnings("checkstyle:linelength")
    public Channel update(final TwilioRestClient client) {
        Request request = new Request(
            HttpMethod.POST,
            Domains.IPMESSAGING.toString(),
            "/v2/Services/" + this.pathServiceSid + "/Channels/" + this.pathSid + ""
        );

        addPostParams(request);
        addHeaderParams(request);
        Response response = client.request(request);

        if (response == null) {
            throw new ApiConnectionException("Channel update failed: Unable to connect to server");
        } else if (!TwilioRestClient.SUCCESS.test(response.getStatusCode())) {
            RestException restException = RestException.fromJson(response.getStream(), client.getObjectMapper());
            if (restException == null) {
                throw new ApiException("Server Error, no content");
            }
            throw new ApiException(restException);
        }

        return Channel.fromJson(response.getStream(), client.getObjectMapper());
    }

    /**
     * Add the requested header parameters to the Request.
     *
     * @param request Request to add header params to
     */
    private void addHeaderParams(final Request request) {
        if (xTwilioWebhookEnabled != null) {
            request.addHeaderParam("X-Twilio-Webhook-Enabled", xTwilioWebhookEnabled.toString());
        }
    }

    /**
     * Add the requested post parameters to the Request.
     *
     * @param request Request to add post params to
     */
    private void addPostParams(final Request request) {
        if (friendlyName != null) {
            request.addPostParam("FriendlyName", friendlyName);
        }

        if (uniqueName != null) {
            request.addPostParam("UniqueName", uniqueName);
        }

        if (attributes != null) {
            request.addPostParam("Attributes", attributes);
        }

        if (dateCreated != null) {
            request.addPostParam("DateCreated", dateCreated.toOffsetDateTime().toString());
        }

        if (dateUpdated != null) {
            request.addPostParam("DateUpdated", dateUpdated.toOffsetDateTime().toString());
        }

        if (createdBy != null) {
            request.addPostParam("CreatedBy", createdBy);
        }
    }
}