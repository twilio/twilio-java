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

import com.twilio.base.Creator;
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
import java.util.List;
import java.util.Map;
import com.twilio.converter.Converter;
import java.net.URI;

import java.util.List;
import java.util.Map;



import java.net.URI;

/*
    * Twilio - Video
    *
    * This is the public Twilio REST API.
    *
    * API version: 1.35.0
    * Contact: support@twilio.com
*/

// Code generated by OpenAPI Generator (https://openapi-generator.tech); DO NOT EDIT.


public class CompositionCreator extends Creator<Composition>{
    private String roomSid;
    private Map<String, Object> videoLayout;
    private List<String> audioSources;
    private List<String> audioSourcesExcluded;
    private String resolution;
    private Composition.Format format;
    private URI statusCallback;
    private HttpMethod statusCallbackMethod;
    private Boolean trim;

    public CompositionCreator(final String roomSid) {
        this.roomSid = roomSid;
    }

    public CompositionCreator setRoomSid(final String roomSid){
        this.roomSid = roomSid;
        return this;
    }
    public CompositionCreator setVideoLayout(final Map<String, Object> videoLayout){
        this.videoLayout = videoLayout;
        return this;
    }
    public CompositionCreator setAudioSources(final List<String> audioSources){
        this.audioSources = audioSources;
        return this;
    }
    public CompositionCreator setAudioSourcesExcluded(final List<String> audioSourcesExcluded){
        this.audioSourcesExcluded = audioSourcesExcluded;
        return this;
    }
    public CompositionCreator setResolution(final String resolution){
        this.resolution = resolution;
        return this;
    }
    public CompositionCreator setFormat(final Composition.Format format){
        this.format = format;
        return this;
    }
    public CompositionCreator setStatusCallback(final URI statusCallback){
        this.statusCallback = statusCallback;
        return this;
    }

    public CompositionCreator setStatusCallback(final String statusCallback){
    this.statusCallback = Promoter.uriFromString(statusCallback);
    return this;
    }
    public CompositionCreator setStatusCallbackMethod(final HttpMethod statusCallbackMethod){
        this.statusCallbackMethod = statusCallbackMethod;
        return this;
    }
    public CompositionCreator setTrim(final Boolean trim){
        this.trim = trim;
        return this;
    }

    @Override
    public Composition create(final TwilioRestClient client){
        String path = "/v1/Compositions";

        path = path.replace("{"+"RoomSid"+"}", this.roomSid.toString());

        Request request = new Request(
            HttpMethod.POST,
            Domains.VIDEO.toString(),
            path
        );
        addPostParams(request);
        Response response = client.request(request);
        if (response == null) {
            throw new ApiConnectionException("Composition creation failed: Unable to connect to server");
        } else if (!TwilioRestClient.SUCCESS.test(response.getStatusCode())) {
            RestException restException = RestException.fromJson(response.getStream(), client.getObjectMapper());
            if (restException == null) {
                throw new ApiException("Server Error, no content");
            }
            throw new ApiException(restException);
        }

        return Composition.fromJson(response.getStream(), client.getObjectMapper());
    }
    private void addPostParams(final Request request) {
        if (roomSid != null) {
            request.addPostParam("RoomSid", roomSid);
    
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
        if (resolution != null) {
            request.addPostParam("Resolution", resolution);
    
        }
        if (format != null) {
            request.addPostParam("Format", format.toString());
    
        }
        if (statusCallback != null) {
            request.addPostParam("StatusCallback", statusCallback.toString());
    
        }
        if (statusCallbackMethod != null) {
            request.addPostParam("StatusCallbackMethod", statusCallbackMethod.toString());
    
        }
        if (trim != null) {
            request.addPostParam("Trim", trim.toString());
    
        }
    }
}
