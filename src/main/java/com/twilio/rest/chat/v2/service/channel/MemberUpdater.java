/**
 * This code was generated by
 * \ / _    _  _|   _  _
 *  | (_)\/(_)(_|\/| |(/_  v1.0.0
 *       /       /
 */

package com.twilio.rest.chat.v2.service.channel;

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
import org.joda.time.DateTime;

public class MemberUpdater extends Updater<Member> {
    private final String pathServiceSid;
    private final String pathChannelSid;
    private final String pathSid;
    private String roleSid;
    private Integer lastConsumedMessageIndex;
    private DateTime lastConsumptionTimestamp;
    private DateTime dateCreated;
    private DateTime dateUpdated;
    private String attributes;
    private Member.WebhookEnabledType xTwilioWebhookEnabled;

    /**
     * Construct a new MemberUpdater.
     *
     * @param pathServiceSid The SID of the Service to create the resource under
     * @param pathChannelSid The SID of the channel the member to update belongs to
     * @param pathSid The SID of the Member resource to update
     */
    public MemberUpdater(final String pathServiceSid,
                         final String pathChannelSid,
                         final String pathSid) {
        this.pathServiceSid = pathServiceSid;
        this.pathChannelSid = pathChannelSid;
        this.pathSid = pathSid;
    }

    /**
     * The SID of the <a
     * href="https://www.twilio.com/docs/chat/rest/role-resource">Role</a> to
     * assign to the member. The default roles are those specified on the <a
     * href="https://www.twilio.com/docs/chat/rest/service-resource">Service</a>..
     *
     * @param roleSid The SID of the Role to assign to the member
     * @return this
     */
    public MemberUpdater setRoleSid(final String roleSid) {
        this.roleSid = roleSid;
        return this;
    }

    /**
     * The index of the last <a
     * href="https://www.twilio.com/docs/chat/rest/message-resource">Message</a>
     * that the Member has read within the <a
     * href="https://www.twilio.com/docs/chat/channels">Channel</a>..
     *
     * @param lastConsumedMessageIndex The index of the last consumed Message for
     *                                 the Channel for the Member
     * @return this
     */
    public MemberUpdater setLastConsumedMessageIndex(final Integer lastConsumedMessageIndex) {
        this.lastConsumedMessageIndex = lastConsumedMessageIndex;
        return this;
    }

    /**
     * The <a href="https://en.wikipedia.org/wiki/ISO_8601">ISO 8601</a> timestamp
     * of the last <a
     * href="https://www.twilio.com/docs/chat/rest/message-resource">Message</a>
     * read event for the Member within the <a
     * href="https://www.twilio.com/docs/chat/channels">Channel</a>..
     *
     * @param lastConsumptionTimestamp The ISO 8601 based timestamp string
     *                                 representing the datetime of the last
     *                                 Message read event for the Member within the
     *                                 Channel
     * @return this
     */
    public MemberUpdater setLastConsumptionTimestamp(final DateTime lastConsumptionTimestamp) {
        this.lastConsumptionTimestamp = lastConsumptionTimestamp;
        return this;
    }

    /**
     * The date, specified in <a href="https://en.wikipedia.org/wiki/ISO_8601">ISO
     * 8601</a> format, to assign to the resource as the date it was created. The
     * default value is the current time set by the Chat service.  Note that this
     * parameter should only be used when a Member is being recreated from a
     * backup/separate source..
     *
     * @param dateCreated The ISO 8601 date and time in GMT when the resource was
     *                    created
     * @return this
     */
    public MemberUpdater setDateCreated(final DateTime dateCreated) {
        this.dateCreated = dateCreated;
        return this;
    }

    /**
     * The date, specified in <a href="https://en.wikipedia.org/wiki/ISO_8601">ISO
     * 8601</a> format, to assign to the resource as the date it was last updated..
     *
     * @param dateUpdated The ISO 8601 date and time in GMT when the resource was
     *                    updated
     * @return this
     */
    public MemberUpdater setDateUpdated(final DateTime dateUpdated) {
        this.dateUpdated = dateUpdated;
        return this;
    }

    /**
     * A valid JSON string that contains application-specific data..
     *
     * @param attributes A valid JSON string that contains application-specific data
     * @return this
     */
    public MemberUpdater setAttributes(final String attributes) {
        this.attributes = attributes;
        return this;
    }

    /**
     * The X-Twilio-Webhook-Enabled HTTP request header.
     *
     * @param xTwilioWebhookEnabled The X-Twilio-Webhook-Enabled HTTP request header
     * @return this
     */
    public MemberUpdater setXTwilioWebhookEnabled(final Member.WebhookEnabledType xTwilioWebhookEnabled) {
        this.xTwilioWebhookEnabled = xTwilioWebhookEnabled;
        return this;
    }

    /**
     * Make the request to the Twilio API to perform the update.
     *
     * @param client TwilioRestClient with which to make the request
     * @return Updated Member
     */
    @Override
    @SuppressWarnings("checkstyle:linelength")
    public Member update(final TwilioRestClient client) {
        Request request = new Request(
            HttpMethod.POST,
            Domains.CHAT.toString(),
            "/v2/Services/" + this.pathServiceSid + "/Channels/" + this.pathChannelSid + "/Members/" + this.pathSid + ""
        );

        addPostParams(request);
        addHeaderParams(request);
        Response response = client.request(request);

        if (response == null) {
            throw new ApiConnectionException("Member update failed: Unable to connect to server");
        } else if (!TwilioRestClient.SUCCESS.apply(response.getStatusCode())) {
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
        if (roleSid != null) {
            request.addPostParam("RoleSid", roleSid);
        }

        if (lastConsumedMessageIndex != null) {
            request.addPostParam("LastConsumedMessageIndex", lastConsumedMessageIndex.toString());
        }

        if (lastConsumptionTimestamp != null) {
            request.addPostParam("LastConsumptionTimestamp", lastConsumptionTimestamp.toString());
        }

        if (dateCreated != null) {
            request.addPostParam("DateCreated", dateCreated.toString());
        }

        if (dateUpdated != null) {
            request.addPostParam("DateUpdated", dateUpdated.toString());
        }

        if (attributes != null) {
            request.addPostParam("Attributes", attributes);
        }
    }
}