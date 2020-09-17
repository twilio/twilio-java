/**
 * This code was generated by
 * \ / _    _  _|   _  _
 *  | (_)\/(_)(_|\/| |(/_  v1.0.0
 *       /       /
 */

package com.twilio.rest.conversations.v1.service.conversation;

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

/**
 * PLEASE NOTE that this class contains beta products that are subject to
 * change. Use them with caution.
 */
public class ParticipantUpdater extends Updater<Participant> {
    private final String pathChatServiceSid;
    private final String pathConversationSid;
    private final String pathSid;
    private DateTime dateCreated;
    private DateTime dateUpdated;
    private String identity;
    private String attributes;
    private String roleSid;
    private String messagingBindingProxyAddress;
    private String messagingBindingProjectedAddress;
    private Participant.WebhookEnabledType xTwilioWebhookEnabled;

    /**
     * Construct a new ParticipantUpdater.
     *
     * @param pathChatServiceSid The SID of the Chat Service that the resource is
     *                           associated with.
     * @param pathConversationSid The unique id of the Conversation for this
     *                            participant.
     * @param pathSid A 34 character string that uniquely identifies this resource.
     */
    public ParticipantUpdater(final String pathChatServiceSid,
                              final String pathConversationSid,
                              final String pathSid) {
        this.pathChatServiceSid = pathChatServiceSid;
        this.pathConversationSid = pathConversationSid;
        this.pathSid = pathSid;
    }

    /**
     * The date that this resource was created..
     *
     * @param dateCreated The date that this resource was created.
     * @return this
     */
    public ParticipantUpdater setDateCreated(final DateTime dateCreated) {
        this.dateCreated = dateCreated;
        return this;
    }

    /**
     * The date that this resource was last updated..
     *
     * @param dateUpdated The date that this resource was last updated.
     * @return this
     */
    public ParticipantUpdater setDateUpdated(final DateTime dateUpdated) {
        this.dateUpdated = dateUpdated;
        return this;
    }

    /**
     * A unique string identifier for the conversation participant as [Chat
     * User](https://www.twilio.com/docs/chat/rest/user-resource). This parameter is
     * non-null if (and only if) the participant is using the Programmable Chat SDK
     * to communicate. Limited to 256 characters..
     *
     * @param identity A unique string identifier for the conversation participant
     *                 as Chat User.
     * @return this
     */
    public ParticipantUpdater setIdentity(final String identity) {
        this.identity = identity;
        return this;
    }

    /**
     * An optional string metadata field you can use to store any data you wish. The
     * string value must contain structurally valid JSON if specified.  **Note**
     * that if the attributes are not set "{}" will be returned..
     *
     * @param attributes An optional string metadata field you can use to store any
     *                   data you wish.
     * @return this
     */
    public ParticipantUpdater setAttributes(final String attributes) {
        this.attributes = attributes;
        return this;
    }

    /**
     * The SID of the [Role](https://www.twilio.com/docs/chat/rest/role-resource) to
     * assign to the participant..
     *
     * @param roleSid The SID of the Role to assign to the participant
     * @return this
     */
    public ParticipantUpdater setRoleSid(final String roleSid) {
        this.roleSid = roleSid;
        return this;
    }

    /**
     * The address of the Twilio phone number that the participant is in contact
     * with. 'null' value will remove it..
     *
     * @param messagingBindingProxyAddress The address of the Twilio phone number
     *                                     that the participant is in contact with.
     * @return this
     */
    public ParticipantUpdater setMessagingBindingProxyAddress(final String messagingBindingProxyAddress) {
        this.messagingBindingProxyAddress = messagingBindingProxyAddress;
        return this;
    }

    /**
     * The address of the Twilio phone number that is used in Group MMS. 'null'
     * value will remove it..
     *
     * @param messagingBindingProjectedAddress The address of the Twilio phone
     *                                         number that is used in Group MMS.
     * @return this
     */
    public ParticipantUpdater setMessagingBindingProjectedAddress(final String messagingBindingProjectedAddress) {
        this.messagingBindingProjectedAddress = messagingBindingProjectedAddress;
        return this;
    }

    /**
     * The X-Twilio-Webhook-Enabled HTTP request header.
     *
     * @param xTwilioWebhookEnabled The X-Twilio-Webhook-Enabled HTTP request header
     * @return this
     */
    public ParticipantUpdater setXTwilioWebhookEnabled(final Participant.WebhookEnabledType xTwilioWebhookEnabled) {
        this.xTwilioWebhookEnabled = xTwilioWebhookEnabled;
        return this;
    }

    /**
     * Make the request to the Twilio API to perform the update.
     *
     * @param client TwilioRestClient with which to make the request
     * @return Updated Participant
     */
    @Override
    @SuppressWarnings("checkstyle:linelength")
    public Participant update(final TwilioRestClient client) {
        Request request = new Request(
            HttpMethod.POST,
            Domains.CONVERSATIONS.toString(),
            "/v1/Services/" + this.pathChatServiceSid + "/Conversations/" + this.pathConversationSid + "/Participants/" + this.pathSid + ""
        );

        addPostParams(request);
        addHeaderParams(request);
        Response response = client.request(request);

        if (response == null) {
            throw new ApiConnectionException("Participant update failed: Unable to connect to server");
        } else if (!TwilioRestClient.SUCCESS.apply(response.getStatusCode())) {
            RestException restException = RestException.fromJson(response.getStream(), client.getObjectMapper());
            if (restException == null) {
                throw new ApiException("Server Error, no content");
            }
            throw new ApiException(restException);
        }

        return Participant.fromJson(response.getStream(), client.getObjectMapper());
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
        if (dateCreated != null) {
            request.addPostParam("DateCreated", dateCreated.toString());
        }

        if (dateUpdated != null) {
            request.addPostParam("DateUpdated", dateUpdated.toString());
        }

        if (identity != null) {
            request.addPostParam("Identity", identity);
        }

        if (attributes != null) {
            request.addPostParam("Attributes", attributes);
        }

        if (roleSid != null) {
            request.addPostParam("RoleSid", roleSid);
        }

        if (messagingBindingProxyAddress != null) {
            request.addPostParam("MessagingBinding.ProxyAddress", messagingBindingProxyAddress);
        }

        if (messagingBindingProjectedAddress != null) {
            request.addPostParam("MessagingBinding.ProjectedAddress", messagingBindingProjectedAddress);
        }
    }
}