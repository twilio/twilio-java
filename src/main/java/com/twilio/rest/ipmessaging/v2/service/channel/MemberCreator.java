/**
 * This code was generated by
 * \ / _    _  _|   _  _
 *  | (_)\/(_)(_|\/| |(/_  v1.0.0
 *       /       /
 */

package com.twilio.rest.ipmessaging.v2.service.channel;

import com.twilio.base.Creator;
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

public class MemberCreator extends Creator<Member> {
    private final String pathServiceSid;
    private final String pathChannelSid;
    private final String identity;
    private String roleSid;
    private Integer lastConsumedMessageIndex;
    private ZonedDateTime lastConsumptionTimestamp;
    private ZonedDateTime dateCreated;
    private ZonedDateTime dateUpdated;
    private String attributes;
    private Member.WebhookEnabledType xTwilioWebhookEnabled;

    /**
     * Construct a new MemberCreator.
     *
     * @param pathServiceSid The service_sid
     * @param pathChannelSid The channel_sid
     * @param identity The identity
     */
    public MemberCreator(final String pathServiceSid,
                         final String pathChannelSid,
                         final String identity) {
        this.pathServiceSid = pathServiceSid;
        this.pathChannelSid = pathChannelSid;
        this.identity = identity;
    }

    /**
     * The role_sid.
     *
     * @param roleSid The role_sid
     * @return this
     */
    public MemberCreator setRoleSid(final String roleSid) {
        this.roleSid = roleSid;
        return this;
    }

    /**
     * The last_consumed_message_index.
     *
     * @param lastConsumedMessageIndex The last_consumed_message_index
     * @return this
     */
    public MemberCreator setLastConsumedMessageIndex(final Integer lastConsumedMessageIndex) {
        this.lastConsumedMessageIndex = lastConsumedMessageIndex;
        return this;
    }

    /**
     * The last_consumption_timestamp.
     *
     * @param lastConsumptionTimestamp The last_consumption_timestamp
     * @return this
     */
    public MemberCreator setLastConsumptionTimestamp(final ZonedDateTime lastConsumptionTimestamp) {
        this.lastConsumptionTimestamp = lastConsumptionTimestamp;
        return this;
    }

    /**
     * The date_created.
     *
     * @param dateCreated The date_created
     * @return this
     */
    public MemberCreator setDateCreated(final ZonedDateTime dateCreated) {
        this.dateCreated = dateCreated;
        return this;
    }

    /**
     * The date_updated.
     *
     * @param dateUpdated The date_updated
     * @return this
     */
    public MemberCreator setDateUpdated(final ZonedDateTime dateUpdated) {
        this.dateUpdated = dateUpdated;
        return this;
    }

    /**
     * The attributes.
     *
     * @param attributes The attributes
     * @return this
     */
    public MemberCreator setAttributes(final String attributes) {
        this.attributes = attributes;
        return this;
    }

    /**
     * The X-Twilio-Webhook-Enabled HTTP request header.
     *
     * @param xTwilioWebhookEnabled The X-Twilio-Webhook-Enabled HTTP request header
     * @return this
     */
    public MemberCreator setXTwilioWebhookEnabled(final Member.WebhookEnabledType xTwilioWebhookEnabled) {
        this.xTwilioWebhookEnabled = xTwilioWebhookEnabled;
        return this;
    }

    /**
     * Make the request to the Twilio API to perform the create.
     *
     * @param client TwilioRestClient with which to make the request
     * @return Created Member
     */
    @Override
    @SuppressWarnings("checkstyle:linelength")
    public Member create(final TwilioRestClient client) {
        Request request = new Request(
            HttpMethod.POST,
            Domains.IPMESSAGING.toString(),
            "/v2/Services/" + this.pathServiceSid + "/Channels/" + this.pathChannelSid + "/Members"
        );

        addPostParams(request);
        addHeaderParams(request);
        Response response = client.request(request);

        if (response == null) {
            throw new ApiConnectionException("Member creation failed: Unable to connect to server");
        } else if (!TwilioRestClient.SUCCESS.test(response.getStatusCode())) {
            RestException restException = RestException.fromJson(response.getStream(), client.getObjectMapper());
            if (restException == null) {
                throw new ApiException("Server Error, no content");
            }
            throw new ApiException(restException);
        }

        return Member.fromJson(response.getStream(), client.getObjectMapper());
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
        if (identity != null) {
            request.addPostParam("Identity", identity);
        }

        if (roleSid != null) {
            request.addPostParam("RoleSid", roleSid);
        }

        if (lastConsumedMessageIndex != null) {
            request.addPostParam("LastConsumedMessageIndex", lastConsumedMessageIndex.toString());
        }

        if (lastConsumptionTimestamp != null) {
            request.addPostParam("LastConsumptionTimestamp", lastConsumptionTimestamp.toInstant().toString());
        }

        if (dateCreated != null) {
            request.addPostParam("DateCreated", dateCreated.toInstant().toString());
        }

        if (dateUpdated != null) {
            request.addPostParam("DateUpdated", dateUpdated.toInstant().toString());
        }

        if (attributes != null) {
            request.addPostParam("Attributes", attributes);
        }
    }
}