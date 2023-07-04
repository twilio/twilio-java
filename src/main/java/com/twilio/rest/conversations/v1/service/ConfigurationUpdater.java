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

package com.twilio.rest.conversations.v1.service;

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




public class ConfigurationUpdater extends Updater<Configuration>{
    private String pathChatServiceSid;
    private String defaultConversationCreatorRoleSid;
    private String defaultConversationRoleSid;
    private String defaultChatServiceRoleSid;
    private Boolean reachabilityEnabled;

    public ConfigurationUpdater(final String pathChatServiceSid){
        this.pathChatServiceSid = pathChatServiceSid;
    }

    public ConfigurationUpdater setDefaultConversationCreatorRoleSid(final String defaultConversationCreatorRoleSid){
        this.defaultConversationCreatorRoleSid = defaultConversationCreatorRoleSid;
        return this;
    }
    public ConfigurationUpdater setDefaultConversationRoleSid(final String defaultConversationRoleSid){
        this.defaultConversationRoleSid = defaultConversationRoleSid;
        return this;
    }
    public ConfigurationUpdater setDefaultChatServiceRoleSid(final String defaultChatServiceRoleSid){
        this.defaultChatServiceRoleSid = defaultChatServiceRoleSid;
        return this;
    }
    public ConfigurationUpdater setReachabilityEnabled(final Boolean reachabilityEnabled){
        this.reachabilityEnabled = reachabilityEnabled;
        return this;
    }

    @Override
    public Configuration update(final TwilioRestClient client){
        String path = "/v1/Services/{ChatServiceSid}/Configuration";

        path = path.replace("{"+"ChatServiceSid"+"}", this.pathChatServiceSid.toString());

        Request request = new Request(
            HttpMethod.POST,
            Domains.CONVERSATIONS.toString(),
            path
        );
        request.setContentType(EnumConstants.ContentType.FORM_URLENCODED);
        addPostParams(request);
        Response response = client.request(request);
        if (response == null) {
            throw new ApiConnectionException("Configuration update failed: Unable to connect to server");
        } else if (!TwilioRestClient.SUCCESS.test(response.getStatusCode())) {
            RestException restException = RestException.fromJson(response.getStream(), client.getObjectMapper());
            if (restException == null) {
                throw new ApiException("Server Error, no content");
            }
            throw new ApiException(restException);
        }

        return Configuration.fromJson(response.getStream(), client.getObjectMapper());
    }
    private void addPostParams(final Request request) {
        if (defaultConversationCreatorRoleSid != null) {
            request.addPostParam("DefaultConversationCreatorRoleSid", defaultConversationCreatorRoleSid);
    
        }
        if (defaultConversationRoleSid != null) {
            request.addPostParam("DefaultConversationRoleSid", defaultConversationRoleSid);
    
        }
        if (defaultChatServiceRoleSid != null) {
            request.addPostParam("DefaultChatServiceRoleSid", defaultChatServiceRoleSid);
    
        }
        if (reachabilityEnabled != null) {
            request.addPostParam("ReachabilityEnabled", reachabilityEnabled.toString());
    
        }
    }
}
