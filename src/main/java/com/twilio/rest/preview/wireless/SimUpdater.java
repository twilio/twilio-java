/*
 * This code was generated by
 * ___ _ _ _ _ _    _ ____    ____ ____ _    ____ ____ _  _ ____ ____ ____ ___ __   __
 *  |  | | | | |    | |  | __ |  | |__| | __ | __ |___ |\ | |___ |__/ |__|  | |  | |__/
 *  |  |_|_| | |___ | |__|    |__| |  | |    |__] |___ | \| |___ |  \ |  |  | |__| |  \
 *
 * Twilio - Preview
 * This is the public Twilio REST API.
 *
 * NOTE: This class is auto generated by OpenAPI Generator.
 * https://openapi-generator.tech
 * Do not edit the class manually.
 */

package com.twilio.rest.preview.wireless;

import com.twilio.base.Updater;
import com.twilio.constant.EnumConstants;
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



public class SimUpdater extends Updater<Sim>{
    private String pathSid;
    private String uniqueName;
    private String callbackMethod;
    private URI callbackUrl;
    private String friendlyName;
    private String ratePlan;
    private String status;
    private HttpMethod commandsCallbackMethod;
    private URI commandsCallbackUrl;
    private HttpMethod smsFallbackMethod;
    private URI smsFallbackUrl;
    private HttpMethod smsMethod;
    private URI smsUrl;
    private HttpMethod voiceFallbackMethod;
    private URI voiceFallbackUrl;
    private HttpMethod voiceMethod;
    private URI voiceUrl;

    public SimUpdater(final String pathSid){
        this.pathSid = pathSid;
    }

    public SimUpdater setUniqueName(final String uniqueName){
        this.uniqueName = uniqueName;
        return this;
    }
    public SimUpdater setCallbackMethod(final String callbackMethod){
        this.callbackMethod = callbackMethod;
        return this;
    }
    public SimUpdater setCallbackUrl(final URI callbackUrl){
        this.callbackUrl = callbackUrl;
        return this;
    }

    public SimUpdater setCallbackUrl(final String callbackUrl){
        return setCallbackUrl(Promoter.uriFromString(callbackUrl));
    }
    public SimUpdater setFriendlyName(final String friendlyName){
        this.friendlyName = friendlyName;
        return this;
    }
    public SimUpdater setRatePlan(final String ratePlan){
        this.ratePlan = ratePlan;
        return this;
    }
    public SimUpdater setStatus(final String status){
        this.status = status;
        return this;
    }
    public SimUpdater setCommandsCallbackMethod(final HttpMethod commandsCallbackMethod){
        this.commandsCallbackMethod = commandsCallbackMethod;
        return this;
    }
    public SimUpdater setCommandsCallbackUrl(final URI commandsCallbackUrl){
        this.commandsCallbackUrl = commandsCallbackUrl;
        return this;
    }

    public SimUpdater setCommandsCallbackUrl(final String commandsCallbackUrl){
        return setCommandsCallbackUrl(Promoter.uriFromString(commandsCallbackUrl));
    }
    public SimUpdater setSmsFallbackMethod(final HttpMethod smsFallbackMethod){
        this.smsFallbackMethod = smsFallbackMethod;
        return this;
    }
    public SimUpdater setSmsFallbackUrl(final URI smsFallbackUrl){
        this.smsFallbackUrl = smsFallbackUrl;
        return this;
    }

    public SimUpdater setSmsFallbackUrl(final String smsFallbackUrl){
        return setSmsFallbackUrl(Promoter.uriFromString(smsFallbackUrl));
    }
    public SimUpdater setSmsMethod(final HttpMethod smsMethod){
        this.smsMethod = smsMethod;
        return this;
    }
    public SimUpdater setSmsUrl(final URI smsUrl){
        this.smsUrl = smsUrl;
        return this;
    }

    public SimUpdater setSmsUrl(final String smsUrl){
        return setSmsUrl(Promoter.uriFromString(smsUrl));
    }
    public SimUpdater setVoiceFallbackMethod(final HttpMethod voiceFallbackMethod){
        this.voiceFallbackMethod = voiceFallbackMethod;
        return this;
    }
    public SimUpdater setVoiceFallbackUrl(final URI voiceFallbackUrl){
        this.voiceFallbackUrl = voiceFallbackUrl;
        return this;
    }

    public SimUpdater setVoiceFallbackUrl(final String voiceFallbackUrl){
        return setVoiceFallbackUrl(Promoter.uriFromString(voiceFallbackUrl));
    }
    public SimUpdater setVoiceMethod(final HttpMethod voiceMethod){
        this.voiceMethod = voiceMethod;
        return this;
    }
    public SimUpdater setVoiceUrl(final URI voiceUrl){
        this.voiceUrl = voiceUrl;
        return this;
    }

    public SimUpdater setVoiceUrl(final String voiceUrl){
        return setVoiceUrl(Promoter.uriFromString(voiceUrl));
    }

    @Override
    public Sim update(final TwilioRestClient client){
        String path = "/wireless/Sims/{Sid}";

        path = path.replace("{"+"Sid"+"}", this.pathSid.toString());

        Request request = new Request(
            HttpMethod.POST,
            Domains.PREVIEW.toString(),
            path
        );
        request.setContentType(EnumConstants.ContentType.FORM_URLENCODED);
        addPostParams(request);
        Response response = client.request(request);
        if (response == null) {
            throw new ApiConnectionException("Sim update failed: Unable to connect to server");
        } else if (!TwilioRestClient.SUCCESS.test(response.getStatusCode())) {
            RestException restException = RestException.fromJson(response.getStream(), client.getObjectMapper());
            if (restException == null) {
                throw new ApiException("Server Error, no content");
            }
            throw new ApiException(restException);
        }

        return Sim.fromJson(response.getStream(), client.getObjectMapper());
    }
    private void addPostParams(final Request request) {
        if (uniqueName != null) {
            request.addPostParam("UniqueName", uniqueName);
    
        }
        if (callbackMethod != null) {
            request.addPostParam("CallbackMethod", callbackMethod);
    
        }
        if (callbackUrl != null) {
            request.addPostParam("CallbackUrl", callbackUrl.toString());
    
        }
        if (friendlyName != null) {
            request.addPostParam("FriendlyName", friendlyName);
    
        }
        if (ratePlan != null) {
            request.addPostParam("RatePlan", ratePlan);
    
        }
        if (status != null) {
            request.addPostParam("Status", status);
    
        }
        if (commandsCallbackMethod != null) {
            request.addPostParam("CommandsCallbackMethod", commandsCallbackMethod.toString());
    
        }
        if (commandsCallbackUrl != null) {
            request.addPostParam("CommandsCallbackUrl", commandsCallbackUrl.toString());
    
        }
        if (smsFallbackMethod != null) {
            request.addPostParam("SmsFallbackMethod", smsFallbackMethod.toString());
    
        }
        if (smsFallbackUrl != null) {
            request.addPostParam("SmsFallbackUrl", smsFallbackUrl.toString());
    
        }
        if (smsMethod != null) {
            request.addPostParam("SmsMethod", smsMethod.toString());
    
        }
        if (smsUrl != null) {
            request.addPostParam("SmsUrl", smsUrl.toString());
    
        }
        if (voiceFallbackMethod != null) {
            request.addPostParam("VoiceFallbackMethod", voiceFallbackMethod.toString());
    
        }
        if (voiceFallbackUrl != null) {
            request.addPostParam("VoiceFallbackUrl", voiceFallbackUrl.toString());
    
        }
        if (voiceMethod != null) {
            request.addPostParam("VoiceMethod", voiceMethod.toString());
    
        }
        if (voiceUrl != null) {
            request.addPostParam("VoiceUrl", voiceUrl.toString());
    
        }
    }
}
