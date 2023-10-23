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

package com.twilio.rest.conversations.v1;

import com.twilio.base.Updater;
import com.twilio.constant.EnumConstants;
import com.twilio.exception.ApiConnectionException;
import com.twilio.exception.ApiException;
import com.twilio.exception.RestException;
import com.twilio.http.HttpMethod;
import com.twilio.http.Request;
import com.twilio.http.Response;
import com.twilio.http.TwilioRestClient;
import com.twilio.rest.Domains;

import java.time.ZonedDateTime;



public class ConversationUpdater extends Updater<Conversation>{
    private String pathSid;
    private Conversation.WebhookEnabledType xTwilioWebhookEnabled;
    private String friendlyName;
    private ZonedDateTime dateCreated;
    private ZonedDateTime dateUpdated;
    private String attributes;
    private String messagingServiceSid;
    private Conversation.State state;
    private String timersInactive;
    private String timersClosed;
    private String uniqueName;
    private String bindingsEmailAddress;
    private String bindingsEmailName;

    public ConversationUpdater(final String pathSid){
        this.pathSid = pathSid;
    }

    public ConversationUpdater setXTwilioWebhookEnabled(final Conversation.WebhookEnabledType xTwilioWebhookEnabled){
        this.xTwilioWebhookEnabled = xTwilioWebhookEnabled;
        return this;
    }
    public ConversationUpdater setFriendlyName(final String friendlyName){
        this.friendlyName = friendlyName;
        return this;
    }
    public ConversationUpdater setDateCreated(final ZonedDateTime dateCreated){
        this.dateCreated = dateCreated;
        return this;
    }
    public ConversationUpdater setDateUpdated(final ZonedDateTime dateUpdated){
        this.dateUpdated = dateUpdated;
        return this;
    }
    public ConversationUpdater setAttributes(final String attributes){
        this.attributes = attributes;
        return this;
    }
    public ConversationUpdater setMessagingServiceSid(final String messagingServiceSid){
        this.messagingServiceSid = messagingServiceSid;
        return this;
    }
    public ConversationUpdater setState(final Conversation.State state){
        this.state = state;
        return this;
    }
    public ConversationUpdater setTimersInactive(final String timersInactive){
        this.timersInactive = timersInactive;
        return this;
    }
    public ConversationUpdater setTimersClosed(final String timersClosed){
        this.timersClosed = timersClosed;
        return this;
    }
    public ConversationUpdater setUniqueName(final String uniqueName){
        this.uniqueName = uniqueName;
        return this;
    }
    public ConversationUpdater setBindingsEmailAddress(final String bindingsEmailAddress){
        this.bindingsEmailAddress = bindingsEmailAddress;
        return this;
    }
    public ConversationUpdater setBindingsEmailName(final String bindingsEmailName){
        this.bindingsEmailName = bindingsEmailName;
        return this;
    }

    @Override
    public Conversation update(final TwilioRestClient client){
        String path = "/v1/Conversations/{Sid}";

        path = path.replace("{"+"Sid"+"}", this.pathSid.toString());

        Request request = new Request(
            HttpMethod.POST,
            Domains.CONVERSATIONS.toString(),
            path
        );
        request.setContentType(EnumConstants.ContentType.FORM_URLENCODED);
        addPostParams(request);
        addHeaderParams(request);
        Response response = client.request(request);
        if (response == null) {
            throw new ApiConnectionException("Conversation update failed: Unable to connect to server");
        } else if (!TwilioRestClient.SUCCESS.test(response.getStatusCode())) {
            RestException restException = RestException.fromJson(response.getStream(), client.getObjectMapper());
            if (restException == null) {
                throw new ApiException("Server Error, no content", response.getStatusCode());
            }
            throw new ApiException(restException);
        }

        return Conversation.fromJson(response.getStream(), client.getObjectMapper());
    }
    private void addPostParams(final Request request) {
        if (friendlyName != null) {
            request.addPostParam("FriendlyName", friendlyName);
    
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
        if (messagingServiceSid != null) {
            request.addPostParam("MessagingServiceSid", messagingServiceSid);
    
        }
        if (state != null) {
            request.addPostParam("State", state.toString());
    
        }
        if (timersInactive != null) {
            request.addPostParam("Timers.Inactive", timersInactive);
    
        }
        if (timersClosed != null) {
            request.addPostParam("Timers.Closed", timersClosed);
    
        }
        if (uniqueName != null) {
            request.addPostParam("UniqueName", uniqueName);
    
        }
        if (bindingsEmailAddress != null) {
            request.addPostParam("Bindings.Email.Address", bindingsEmailAddress);
    
        }
        if (bindingsEmailName != null) {
            request.addPostParam("Bindings.Email.Name", bindingsEmailName);
    
        }
    }
    private void addHeaderParams(final Request request) {
        if (xTwilioWebhookEnabled != null) {
            request.addHeaderParam("X-Twilio-Webhook-Enabled", xTwilioWebhookEnabled.toString());
        }
    }
}
