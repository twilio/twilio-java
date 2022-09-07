/*
 * This code was generated by
 * ___ _ _ _ _ _    _ ____    ____ ____ _    ____ ____ _  _ ____ ____ ____ ___ __   __
 *  |  | | | | |    | |  | __ |  | |__| | __ | __ |___ |\ | |___ |__/ |__|  | |  | |__/
 *  |  |_|_| | |___ | |__|    |__| |  | |    |__] |___ | \| |___ |  \ |  |  | |__| |  \
 *
 * Twilio - Verify
 * This is the public Twilio REST API.
 *
 * NOTE: This class is auto generated by OpenAPI Generator.
 * https://openapi-generator.tech
 * Do not edit the class manually.
 */

package com.twilio.rest.verify.v2.service.entity;

import com.twilio.base.Creator;
import com.twilio.exception.ApiConnectionException;
import com.twilio.converter.Converter;
import com.twilio.exception.ApiException;
import com.twilio.exception.RestException;
import com.twilio.http.HttpMethod;
import com.twilio.http.Request;
import com.twilio.http.Response;
import com.twilio.http.TwilioRestClient;
import com.twilio.rest.Domains;
import java.util.Map;
import com.twilio.converter.Converter;

import java.util.Map;




/*
    * Twilio - Verify
    *
    * This is the public Twilio REST API.
    *
    * API version: 1.35.0
    * Contact: support@twilio.com
*/

// Code generated by OpenAPI Generator (https://openapi-generator.tech); DO NOT EDIT.


public class NewFactorCreator extends Creator<NewFactor>{
    private String serviceSid;
    private String identity;
    private String friendlyName;
    private NewFactor.FactorTypes factorType;
    private String bindingAlg;
    private String bindingPublicKey;
    private String configAppId;
    private NewFactor.NotificationPlatforms configNotificationPlatform;
    private String configNotificationToken;
    private String configSdkVersion;
    private String bindingSecret;
    private Integer configTimeStep;
    private Integer configSkew;
    private Integer configCodeLength;
    private NewFactor.TotpAlgorithms configAlg;
    private Map<String, Object> metadata;

    public NewFactorCreator(final String serviceSid, final String identity, final String friendlyName, final NewFactor.FactorTypes factorType) {
        this.serviceSid = serviceSid;
        this.identity = identity;
        this.friendlyName = friendlyName;
        this.factorType = factorType;
    }

    public NewFactorCreator setFriendlyName(final String friendlyName){
        this.friendlyName = friendlyName;
        return this;
    }
    public NewFactorCreator setFactorType(final NewFactor.FactorTypes factorType){
        this.factorType = factorType;
        return this;
    }
    public NewFactorCreator setBindingAlg(final String bindingAlg){
        this.bindingAlg = bindingAlg;
        return this;
    }
    public NewFactorCreator setBindingPublicKey(final String bindingPublicKey){
        this.bindingPublicKey = bindingPublicKey;
        return this;
    }
    public NewFactorCreator setConfigAppId(final String configAppId){
        this.configAppId = configAppId;
        return this;
    }
    public NewFactorCreator setConfigNotificationPlatform(final NewFactor.NotificationPlatforms configNotificationPlatform){
        this.configNotificationPlatform = configNotificationPlatform;
        return this;
    }
    public NewFactorCreator setConfigNotificationToken(final String configNotificationToken){
        this.configNotificationToken = configNotificationToken;
        return this;
    }
    public NewFactorCreator setConfigSdkVersion(final String configSdkVersion){
        this.configSdkVersion = configSdkVersion;
        return this;
    }
    public NewFactorCreator setBindingSecret(final String bindingSecret){
        this.bindingSecret = bindingSecret;
        return this;
    }
    public NewFactorCreator setConfigTimeStep(final Integer configTimeStep){
        this.configTimeStep = configTimeStep;
        return this;
    }
    public NewFactorCreator setConfigSkew(final Integer configSkew){
        this.configSkew = configSkew;
        return this;
    }
    public NewFactorCreator setConfigCodeLength(final Integer configCodeLength){
        this.configCodeLength = configCodeLength;
        return this;
    }
    public NewFactorCreator setConfigAlg(final NewFactor.TotpAlgorithms configAlg){
        this.configAlg = configAlg;
        return this;
    }
    public NewFactorCreator setMetadata(final Map<String, Object> metadata){
        this.metadata = metadata;
        return this;
    }

    @Override
    public NewFactor create(final TwilioRestClient client){
        String path = "/v2/Services/{ServiceSid}/Entities/{Identity}/Factors";

        path = path.replace("{"+"ServiceSid"+"}", this.serviceSid.toString());
        path = path.replace("{"+"Identity"+"}", this.identity.toString());
        path = path.replace("{"+"FriendlyName"+"}", this.friendlyName.toString());
        path = path.replace("{"+"FactorType"+"}", this.factorType.toString());

        Request request = new Request(
            HttpMethod.POST,
            Domains.VERIFY.toString(),
            path
        );
        addPostParams(request);
        Response response = client.request(request);
        if (response == null) {
            throw new ApiConnectionException("NewFactor creation failed: Unable to connect to server");
        } else if (!TwilioRestClient.SUCCESS.test(response.getStatusCode())) {
            RestException restException = RestException.fromJson(response.getStream(), client.getObjectMapper());
            if (restException == null) {
                throw new ApiException("Server Error, no content");
            }
            throw new ApiException(restException);
        }

        return NewFactor.fromJson(response.getStream(), client.getObjectMapper());
    }
    private void addPostParams(final Request request) {
        if (friendlyName != null) {
            request.addPostParam("FriendlyName", friendlyName);
    
        }
        if (factorType != null) {
            request.addPostParam("FactorType", factorType.toString());
    
        }
        if (bindingAlg != null) {
            request.addPostParam("Binding.Alg", bindingAlg);
    
        }
        if (bindingPublicKey != null) {
            request.addPostParam("Binding.PublicKey", bindingPublicKey);
    
        }
        if (configAppId != null) {
            request.addPostParam("Config.AppId", configAppId);
    
        }
        if (configNotificationPlatform != null) {
            request.addPostParam("ConfigNotificationPlatform", configNotificationPlatform.toString());
    
        }
        if (configNotificationToken != null) {
            request.addPostParam("Config.NotificationToken", configNotificationToken);
    
        }
        if (configSdkVersion != null) {
            request.addPostParam("Config.SdkVersion", configSdkVersion);
    
        }
        if (bindingSecret != null) {
            request.addPostParam("Binding.Secret", bindingSecret);
    
        }
        if (configTimeStep != null) {
            request.addPostParam("ConfigTimeStep", configTimeStep.toString());
    
        }
        if (configSkew != null) {
            request.addPostParam("ConfigSkew", configSkew.toString());
    
        }
        if (configCodeLength != null) {
            request.addPostParam("ConfigCodeLength", configCodeLength.toString());
    
        }
        if (configAlg != null) {
            request.addPostParam("ConfigAlg", configAlg.toString());
    
        }
        if (metadata != null) {
            request.addPostParam("Metadata",  Converter.mapToJson(metadata));
    
        }
    }
}
