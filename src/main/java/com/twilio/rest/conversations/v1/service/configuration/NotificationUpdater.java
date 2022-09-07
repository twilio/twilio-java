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

package com.twilio.rest.conversations.v1.service.configuration;

import com.twilio.base.Updater;
import com.twilio.exception.ApiConnectionException;
import com.twilio.exception.ApiException;
import com.twilio.exception.RestException;
import com.twilio.http.HttpMethod;
import com.twilio.http.Request;
import com.twilio.http.Response;
import com.twilio.http.TwilioRestClient;
import com.twilio.rest.Domains;





/*
    * Twilio - Conversations
    *
    * This is the public Twilio REST API.
    *
    * API version: 1.35.0
    * Contact: support@twilio.com
*/

// Code generated by OpenAPI Generator (https://openapi-generator.tech); DO NOT EDIT.


public class NotificationUpdater extends Updater<Notification>{
    private String chatServiceSid;
    private Boolean logEnabled;
    private Boolean newMessageEnabled;
    private String newMessageTemplate;
    private String newMessageSound;
    private Boolean newMessageBadgeCountEnabled;
    private Boolean addedToConversationEnabled;
    private String addedToConversationTemplate;
    private String addedToConversationSound;
    private Boolean removedFromConversationEnabled;
    private String removedFromConversationTemplate;
    private String removedFromConversationSound;
    private Boolean newMessageWithMediaEnabled;
    private String newMessageWithMediaTemplate;

    public NotificationUpdater(final String chatServiceSid){
        this.chatServiceSid = chatServiceSid;
    }

    public NotificationUpdater setLogEnabled(final Boolean logEnabled){
        this.logEnabled = logEnabled;
        return this;
    }
    public NotificationUpdater setNewMessageEnabled(final Boolean newMessageEnabled){
        this.newMessageEnabled = newMessageEnabled;
        return this;
    }
    public NotificationUpdater setNewMessageTemplate(final String newMessageTemplate){
        this.newMessageTemplate = newMessageTemplate;
        return this;
    }
    public NotificationUpdater setNewMessageSound(final String newMessageSound){
        this.newMessageSound = newMessageSound;
        return this;
    }
    public NotificationUpdater setNewMessageBadgeCountEnabled(final Boolean newMessageBadgeCountEnabled){
        this.newMessageBadgeCountEnabled = newMessageBadgeCountEnabled;
        return this;
    }
    public NotificationUpdater setAddedToConversationEnabled(final Boolean addedToConversationEnabled){
        this.addedToConversationEnabled = addedToConversationEnabled;
        return this;
    }
    public NotificationUpdater setAddedToConversationTemplate(final String addedToConversationTemplate){
        this.addedToConversationTemplate = addedToConversationTemplate;
        return this;
    }
    public NotificationUpdater setAddedToConversationSound(final String addedToConversationSound){
        this.addedToConversationSound = addedToConversationSound;
        return this;
    }
    public NotificationUpdater setRemovedFromConversationEnabled(final Boolean removedFromConversationEnabled){
        this.removedFromConversationEnabled = removedFromConversationEnabled;
        return this;
    }
    public NotificationUpdater setRemovedFromConversationTemplate(final String removedFromConversationTemplate){
        this.removedFromConversationTemplate = removedFromConversationTemplate;
        return this;
    }
    public NotificationUpdater setRemovedFromConversationSound(final String removedFromConversationSound){
        this.removedFromConversationSound = removedFromConversationSound;
        return this;
    }
    public NotificationUpdater setNewMessageWithMediaEnabled(final Boolean newMessageWithMediaEnabled){
        this.newMessageWithMediaEnabled = newMessageWithMediaEnabled;
        return this;
    }
    public NotificationUpdater setNewMessageWithMediaTemplate(final String newMessageWithMediaTemplate){
        this.newMessageWithMediaTemplate = newMessageWithMediaTemplate;
        return this;
    }

    @Override
    public Notification update(final TwilioRestClient client){
        String path = "/v1/Services/{ChatServiceSid}/Configuration/Notifications";

        path = path.replace("{"+"ChatServiceSid"+"}", this.chatServiceSid.toString());

        Request request = new Request(
            HttpMethod.POST,
            Domains.CONVERSATIONS.toString(),
            path
        );
        addPostParams(request);
        Response response = client.request(request);
        if (response == null) {
            throw new ApiConnectionException("Notification update failed: Unable to connect to server");
        } else if (!TwilioRestClient.SUCCESS.test(response.getStatusCode())) {
            RestException restException = RestException.fromJson(response.getStream(), client.getObjectMapper());
            if (restException == null) {
                throw new ApiException("Server Error, no content");
            }
            throw new ApiException(restException);
        }

        return Notification.fromJson(response.getStream(), client.getObjectMapper());
    }
    private void addPostParams(final Request request) {
        if (logEnabled != null) {
            request.addPostParam("LogEnabled", logEnabled.toString());
    
        }
        if (newMessageEnabled != null) {
            request.addPostParam("NewMessageEnabled", newMessageEnabled.toString());
    
        }
        if (newMessageTemplate != null) {
            request.addPostParam("NewMessage.Template", newMessageTemplate);
    
        }
        if (newMessageSound != null) {
            request.addPostParam("NewMessage.Sound", newMessageSound);
    
        }
        if (newMessageBadgeCountEnabled != null) {
            request.addPostParam("NewMessageBadgeCountEnabled", newMessageBadgeCountEnabled.toString());
    
        }
        if (addedToConversationEnabled != null) {
            request.addPostParam("AddedToConversationEnabled", addedToConversationEnabled.toString());
    
        }
        if (addedToConversationTemplate != null) {
            request.addPostParam("AddedToConversation.Template", addedToConversationTemplate);
    
        }
        if (addedToConversationSound != null) {
            request.addPostParam("AddedToConversation.Sound", addedToConversationSound);
    
        }
        if (removedFromConversationEnabled != null) {
            request.addPostParam("RemovedFromConversationEnabled", removedFromConversationEnabled.toString());
    
        }
        if (removedFromConversationTemplate != null) {
            request.addPostParam("RemovedFromConversation.Template", removedFromConversationTemplate);
    
        }
        if (removedFromConversationSound != null) {
            request.addPostParam("RemovedFromConversation.Sound", removedFromConversationSound);
    
        }
        if (newMessageWithMediaEnabled != null) {
            request.addPostParam("NewMessageWithMediaEnabled", newMessageWithMediaEnabled.toString());
    
        }
        if (newMessageWithMediaTemplate != null) {
            request.addPostParam("NewMessage.WithMedia.Template", newMessageWithMediaTemplate);
    
        }
    }
}
