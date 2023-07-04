/*
 * This code was generated by
 * ___ _ _ _ _ _    _ ____    ____ ____ _    ____ ____ _  _ ____ ____ ____ ___ __   __
 *  |  | | | | |    | |  | __ |  | |__| | __ | __ |___ |\ | |___ |__/ |__|  | |  | |__/
 *  |  |_|_| | |___ | |__|    |__| |  | |    |__] |___ | \| |___ |  \ |  |  | |__| |  \
 *
 * Twilio - Video
 * This is the public Twilio REST API.
 *
 * NOTE: This class is auto generated by OpenAPI Generator.
 * https://openapi-generator.tech
 * Do not edit the class manually.
 */

package com.twilio.rest.video.v1;

import com.twilio.base.Updater;
import com.twilio.constant.EnumConstants;
import com.twilio.converter.Promoter;
import com.twilio.exception.ApiConnectionException;
import com.twilio.converter.Converter;
import com.twilio.exception.ApiException;
import com.twilio.exception.RestException;
import com.twilio.http.HttpMethod;
import com.twilio.http.Request;
import com.twilio.http.Response;
import com.twilio.http.TwilioRestClient;
import com.twilio.rest.Domains;

import java.net.URI;

import java.util.List;
import java.util.Map;


public class CompositionHookUpdater extends Updater<CompositionHook>{
    private String pathSid;
    private String friendlyName;
    private Boolean enabled;
    private Map<String, Object> videoLayout;
    private List<String> audioSources;
    private List<String> audioSourcesExcluded;
    private Boolean trim;
    private CompositionHook.Format format;
    private String resolution;
    private URI statusCallback;
    private HttpMethod statusCallbackMethod;

    public CompositionHookUpdater(final String pathSid, final String friendlyName){
        this.pathSid = pathSid;
        this.friendlyName = friendlyName;
    }

    public CompositionHookUpdater setFriendlyName(final String friendlyName){
        this.friendlyName = friendlyName;
        return this;
    }
    public CompositionHookUpdater setEnabled(final Boolean enabled){
        this.enabled = enabled;
        return this;
    }
    public CompositionHookUpdater setVideoLayout(final Map<String, Object> videoLayout){
        this.videoLayout = videoLayout;
        return this;
    }
    public CompositionHookUpdater setAudioSources(final List<String> audioSources){
        this.audioSources = audioSources;
        return this;
    }
    public CompositionHookUpdater setAudioSources(final String audioSources){
        return setAudioSources(Promoter.listOfOne(audioSources));
    }
    public CompositionHookUpdater setAudioSourcesExcluded(final List<String> audioSourcesExcluded){
        this.audioSourcesExcluded = audioSourcesExcluded;
        return this;
    }
    public CompositionHookUpdater setAudioSourcesExcluded(final String audioSourcesExcluded){
        return setAudioSourcesExcluded(Promoter.listOfOne(audioSourcesExcluded));
    }
    public CompositionHookUpdater setTrim(final Boolean trim){
        this.trim = trim;
        return this;
    }
    public CompositionHookUpdater setFormat(final CompositionHook.Format format){
        this.format = format;
        return this;
    }
    public CompositionHookUpdater setResolution(final String resolution){
        this.resolution = resolution;
        return this;
    }
    public CompositionHookUpdater setStatusCallback(final URI statusCallback){
        this.statusCallback = statusCallback;
        return this;
    }

    public CompositionHookUpdater setStatusCallback(final String statusCallback){
        return setStatusCallback(Promoter.uriFromString(statusCallback));
    }
    public CompositionHookUpdater setStatusCallbackMethod(final HttpMethod statusCallbackMethod){
        this.statusCallbackMethod = statusCallbackMethod;
        return this;
    }

    @Override
    public CompositionHook update(final TwilioRestClient client){
        String path = "/v1/CompositionHooks/{Sid}";

        path = path.replace("{"+"Sid"+"}", this.pathSid.toString());
        path = path.replace("{"+"FriendlyName"+"}", this.friendlyName.toString());

        Request request = new Request(
            HttpMethod.POST,
            Domains.VIDEO.toString(),
            path
        );
        request.setContentType(EnumConstants.ContentType.FORM_URLENCODED);
        addPostParams(request);
        Response response = client.request(request);
        if (response == null) {
            throw new ApiConnectionException("CompositionHook update failed: Unable to connect to server");
        } else if (!TwilioRestClient.SUCCESS.test(response.getStatusCode())) {
            RestException restException = RestException.fromJson(response.getStream(), client.getObjectMapper());
            if (restException == null) {
                throw new ApiException("Server Error, no content");
            }
            throw new ApiException(restException);
        }

        return CompositionHook.fromJson(response.getStream(), client.getObjectMapper());
    }
    private void addPostParams(final Request request) {
        if (friendlyName != null) {
            request.addPostParam("FriendlyName", friendlyName);
    
        }
        if (enabled != null) {
            request.addPostParam("Enabled", enabled.toString());
    
        }
        if (videoLayout != null) {
            request.addPostParam("VideoLayout",  Converter.mapToJson(videoLayout));
    
        }
        if (audioSources != null) {
            for (String prop : audioSources) {
                request.addPostParam("AudioSources", prop);
            }
    
        }
        if (audioSourcesExcluded != null) {
            for (String prop : audioSourcesExcluded) {
                request.addPostParam("AudioSourcesExcluded", prop);
            }
    
        }
        if (trim != null) {
            request.addPostParam("Trim", trim.toString());
    
        }
        if (format != null) {
            request.addPostParam("Format", format.toString());
    
        }
        if (resolution != null) {
            request.addPostParam("Resolution", resolution);
    
        }
        if (statusCallback != null) {
            request.addPostParam("StatusCallback", statusCallback.toString());
    
        }
        if (statusCallbackMethod != null) {
            request.addPostParam("StatusCallbackMethod", statusCallbackMethod.toString());
    
        }
    }
}
