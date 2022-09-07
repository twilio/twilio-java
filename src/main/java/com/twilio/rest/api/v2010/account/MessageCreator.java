/*
 * This code was generated by
 * ___ _ _ _ _ _    _ ____    ____ ____ _    ____ ____ _  _ ____ ____ ____ ___ __   __
 *  |  | | | | |    | |  | __ |  | |__| | __ | __ |___ |\ | |___ |__/ |__|  | |  | |__/
 *  |  |_|_| | |___ | |__|    |__| |  | |    |__] |___ | \| |___ |  \ |  |  | |__| |  \
 *
 * Twilio - Api
 * This is the public Twilio REST API.
 *
 * NOTE: This class is auto generated by OpenAPI Generator.
 * https://openapi-generator.tech
 * Do not edit the class manually.
 */

package com.twilio.rest.api.v2010.account;

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
import java.math.BigDecimal;
import java.util.List;
import java.time.ZonedDateTime;
import java.net.URI;

import java.util.List;



import java.net.URI;

/*
    * Twilio - Api
    *
    * This is the public Twilio REST API.
    *
    * API version: 1.35.0
    * Contact: support@twilio.com
*/

// Code generated by OpenAPI Generator (https://openapi-generator.tech); DO NOT EDIT.


public class MessageCreator extends Creator<Message>{
    private com.twilio.type.PhoneNumber to;
    private String accountSid;
    private URI statusCallback;
    private String applicationSid;
    private BigDecimal maxPrice;
    private Boolean provideFeedback;
    private Integer attempt;
    private Integer validityPeriod;
    private Boolean forceDelivery;
    private Message.ContentRetention contentRetention;
    private Message.AddressRetention addressRetention;
    private Boolean smartEncoded;
    private List<String> persistentAction;
    private Message.ScheduleType scheduleType;
    private ZonedDateTime sendAt;
    private Boolean sendAsMms;
    private com.twilio.type.PhoneNumber from;
    private String messagingServiceSid;
    private String body;
    private List<URI> mediaUrl;

    public MessageCreator(final com.twilio.type.PhoneNumber to, final com.twilio.type.PhoneNumber from, final String body) {
        this.to = to;
        this.from = from;
        this.body = body;
    }
    public MessageCreator(final String accountSid, final com.twilio.type.PhoneNumber to, final com.twilio.type.PhoneNumber from, final String body) {
        this.accountSid = accountSid;
        this.to = to;
        this.from = from;
        this.body = body;
    }
    public MessageCreator(final com.twilio.type.PhoneNumber to, final com.twilio.type.PhoneNumber from, final List<URI> mediaUrl) {
        this.to = to;
        this.from = from;
        this.mediaUrl = mediaUrl;
    }
    public MessageCreator(final String accountSid, final com.twilio.type.PhoneNumber to, final com.twilio.type.PhoneNumber from, final List<URI> mediaUrl) {
        this.accountSid = accountSid;
        this.to = to;
        this.from = from;
        this.mediaUrl = mediaUrl;
    }
    public MessageCreator(final com.twilio.type.PhoneNumber to, final String messagingServiceSid, final String body) {
        this.to = to;
        this.messagingServiceSid = messagingServiceSid;
        this.body = body;
    }
    public MessageCreator(final String accountSid, final com.twilio.type.PhoneNumber to, final String messagingServiceSid, final String body) {
        this.accountSid = accountSid;
        this.to = to;
        this.messagingServiceSid = messagingServiceSid;
        this.body = body;
    }
    public MessageCreator(final com.twilio.type.PhoneNumber to, final String messagingServiceSid, final List<URI> mediaUrl) {
        this.to = to;
        this.messagingServiceSid = messagingServiceSid;
        this.mediaUrl = mediaUrl;
    }
    public MessageCreator(final String accountSid, final com.twilio.type.PhoneNumber to, final String messagingServiceSid, final List<URI> mediaUrl) {
        this.accountSid = accountSid;
        this.to = to;
        this.messagingServiceSid = messagingServiceSid;
        this.mediaUrl = mediaUrl;
    }

    public MessageCreator setTo(final com.twilio.type.PhoneNumber to){
        this.to = to;
        return this;
    }

    public MessageCreator setTo(final String to){
    this.to = Promoter.phoneNumberFromString(to);
    return this;
    }
    public MessageCreator setStatusCallback(final URI statusCallback){
        this.statusCallback = statusCallback;
        return this;
    }

    public MessageCreator setStatusCallback(final String statusCallback){
    this.statusCallback = Promoter.uriFromString(statusCallback);
    return this;
    }
    public MessageCreator setApplicationSid(final String applicationSid){
        this.applicationSid = applicationSid;
        return this;
    }
    public MessageCreator setMaxPrice(final BigDecimal maxPrice){
        this.maxPrice = maxPrice;
        return this;
    }
    public MessageCreator setProvideFeedback(final Boolean provideFeedback){
        this.provideFeedback = provideFeedback;
        return this;
    }
    public MessageCreator setAttempt(final Integer attempt){
        this.attempt = attempt;
        return this;
    }
    public MessageCreator setValidityPeriod(final Integer validityPeriod){
        this.validityPeriod = validityPeriod;
        return this;
    }
    public MessageCreator setForceDelivery(final Boolean forceDelivery){
        this.forceDelivery = forceDelivery;
        return this;
    }
    public MessageCreator setContentRetention(final Message.ContentRetention contentRetention){
        this.contentRetention = contentRetention;
        return this;
    }
    public MessageCreator setAddressRetention(final Message.AddressRetention addressRetention){
        this.addressRetention = addressRetention;
        return this;
    }
    public MessageCreator setSmartEncoded(final Boolean smartEncoded){
        this.smartEncoded = smartEncoded;
        return this;
    }
    public MessageCreator setPersistentAction(final List<String> persistentAction){
        this.persistentAction = persistentAction;
        return this;
    }
    public MessageCreator setScheduleType(final Message.ScheduleType scheduleType){
        this.scheduleType = scheduleType;
        return this;
    }
    public MessageCreator setSendAt(final ZonedDateTime sendAt){
        this.sendAt = sendAt;
        return this;
    }
    public MessageCreator setSendAsMms(final Boolean sendAsMms){
        this.sendAsMms = sendAsMms;
        return this;
    }
    public MessageCreator setFrom(final com.twilio.type.PhoneNumber from){
        this.from = from;
        return this;
    }

    public MessageCreator setFrom(final String from){
    this.from = Promoter.phoneNumberFromString(from);
    return this;
    }
    public MessageCreator setMessagingServiceSid(final String messagingServiceSid){
        this.messagingServiceSid = messagingServiceSid;
        return this;
    }
    public MessageCreator setBody(final String body){
        this.body = body;
        return this;
    }
    public MessageCreator setMediaUrl(final List<URI> mediaUrl){
        this.mediaUrl = mediaUrl;
        return this;
    }

    public MessageCreator setMediaUrl(final String mediaUrl){
        return setMediaUrl(Promoter.uriFromString(mediaUrl));
    }

    public MessageCreator setMediaUrl(final URI mediaUrl){
        return setMediaUrl(Promoter.listOfOne(mediaUrl));
    }

    @Override
    public Message create(final TwilioRestClient client){
        String path = "/2010-04-01/Accounts/{AccountSid}/Messages.json";

        this.accountSid = this.accountSid == null ? client.getAccountSid() : this.accountSid;
        path = path.replace("{"+"AccountSid"+"}", this.accountSid.toString());
        path = path.replace("{"+"To"+"}", this.to.encode("utf-8"));

        Request request = new Request(
            HttpMethod.POST,
            Domains.API.toString(),
            path
        );
        addPostParams(request);
        Response response = client.request(request);
        if (response == null) {
            throw new ApiConnectionException("Message creation failed: Unable to connect to server");
        } else if (!TwilioRestClient.SUCCESS.test(response.getStatusCode())) {
            RestException restException = RestException.fromJson(response.getStream(), client.getObjectMapper());
            if (restException == null) {
                throw new ApiException("Server Error, no content");
            }
            throw new ApiException(restException);
        }

        return Message.fromJson(response.getStream(), client.getObjectMapper());
    }
    private void addPostParams(final Request request) {
        if (to != null) {
            request.addPostParam("To", to.toString());
    
        }
        if (statusCallback != null) {
            request.addPostParam("StatusCallback", statusCallback.toString());
    
        }
        if (applicationSid != null) {
            request.addPostParam("ApplicationSid", applicationSid);
    
        }
        if (maxPrice != null) {
            request.addPostParam("MaxPrice", maxPrice.toString());
    
        }
        if (provideFeedback != null) {
            request.addPostParam("ProvideFeedback", provideFeedback.toString());
    
        }
        if (attempt != null) {
            request.addPostParam("Attempt", attempt.toString());
    
        }
        if (validityPeriod != null) {
            request.addPostParam("ValidityPeriod", validityPeriod.toString());
    
        }
        if (forceDelivery != null) {
            request.addPostParam("ForceDelivery", forceDelivery.toString());
    
        }
        if (contentRetention != null) {
            request.addPostParam("ContentRetention", contentRetention.toString());
    
        }
        if (addressRetention != null) {
            request.addPostParam("AddressRetention", addressRetention.toString());
    
        }
        if (smartEncoded != null) {
            request.addPostParam("SmartEncoded", smartEncoded.toString());
    
        }
        if (persistentAction != null) {
            for (String prop : persistentAction) {
                request.addPostParam("PersistentAction", prop);
            }
    
        }
        if (scheduleType != null) {
            request.addPostParam("ScheduleType", scheduleType.toString());
    
        }
        if (sendAt != null) {
            request.addPostParam("SendAt", sendAt.toInstant().toString());

        }
        if (sendAsMms != null) {
            request.addPostParam("SendAsMms", sendAsMms.toString());
    
        }
        if (from != null) {
            request.addPostParam("From", from.toString());
    
        }
        if (messagingServiceSid != null) {
            request.addPostParam("MessagingServiceSid", messagingServiceSid);
    
        }
        if (body != null) {
            request.addPostParam("Body", body);
    
        }
        if (mediaUrl != null) {
            for (URI prop : mediaUrl) {
                request.addPostParam("MediaUrl", prop.toString());
            }
    
        }
    }
}
