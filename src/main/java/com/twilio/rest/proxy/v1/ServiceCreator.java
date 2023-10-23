/*
 * This code was generated by
 * ___ _ _ _ _ _    _ ____    ____ ____ _    ____ ____ _  _ ____ ____ ____ ___ __   __
 *  |  | | | | |    | |  | __ |  | |__| | __ | __ |___ |\ | |___ |__/ |__|  | |  | |__/
 *  |  |_|_| | |___ | |__|    |__| |  | |    |__] |___ | \| |___ |  \ |  |  | |__| |  \
 *
 * Twilio - Proxy
 * This is the public Twilio REST API.
 *
 * NOTE: This class is auto generated by OpenAPI Generator.
 * https://openapi-generator.tech
 * Do not edit the class manually.
 */

package com.twilio.rest.proxy.v1;

import com.twilio.base.Creator;
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



import java.net.URI;

public class ServiceCreator extends Creator<Service>{
    private String uniqueName;
    private Integer defaultTtl;
    private URI callbackUrl;
    private Service.GeoMatchLevel geoMatchLevel;
    private Service.NumberSelectionBehavior numberSelectionBehavior;
    private URI interceptCallbackUrl;
    private URI outOfSessionCallbackUrl;
    private String chatInstanceSid;

    public ServiceCreator(final String uniqueName) {
        this.uniqueName = uniqueName;
    }

    public ServiceCreator setUniqueName(final String uniqueName){
        this.uniqueName = uniqueName;
        return this;
    }
    public ServiceCreator setDefaultTtl(final Integer defaultTtl){
        this.defaultTtl = defaultTtl;
        return this;
    }
    public ServiceCreator setCallbackUrl(final URI callbackUrl){
        this.callbackUrl = callbackUrl;
        return this;
    }

    public ServiceCreator setCallbackUrl(final String callbackUrl){
        return setCallbackUrl(Promoter.uriFromString(callbackUrl));
    }
    public ServiceCreator setGeoMatchLevel(final Service.GeoMatchLevel geoMatchLevel){
        this.geoMatchLevel = geoMatchLevel;
        return this;
    }
    public ServiceCreator setNumberSelectionBehavior(final Service.NumberSelectionBehavior numberSelectionBehavior){
        this.numberSelectionBehavior = numberSelectionBehavior;
        return this;
    }
    public ServiceCreator setInterceptCallbackUrl(final URI interceptCallbackUrl){
        this.interceptCallbackUrl = interceptCallbackUrl;
        return this;
    }

    public ServiceCreator setInterceptCallbackUrl(final String interceptCallbackUrl){
        return setInterceptCallbackUrl(Promoter.uriFromString(interceptCallbackUrl));
    }
    public ServiceCreator setOutOfSessionCallbackUrl(final URI outOfSessionCallbackUrl){
        this.outOfSessionCallbackUrl = outOfSessionCallbackUrl;
        return this;
    }

    public ServiceCreator setOutOfSessionCallbackUrl(final String outOfSessionCallbackUrl){
        return setOutOfSessionCallbackUrl(Promoter.uriFromString(outOfSessionCallbackUrl));
    }
    public ServiceCreator setChatInstanceSid(final String chatInstanceSid){
        this.chatInstanceSid = chatInstanceSid;
        return this;
    }

    @Override
    public Service create(final TwilioRestClient client){
        String path = "/v1/Services";

        path = path.replace("{"+"UniqueName"+"}", this.uniqueName.toString());

        Request request = new Request(
            HttpMethod.POST,
            Domains.PROXY.toString(),
            path
        );
        request.setContentType(EnumConstants.ContentType.FORM_URLENCODED);
        addPostParams(request);
        Response response = client.request(request);
        if (response == null) {
            throw new ApiConnectionException("Service creation failed: Unable to connect to server");
        } else if (!TwilioRestClient.SUCCESS.test(response.getStatusCode())) {
            RestException restException = RestException.fromJson(response.getStream(), client.getObjectMapper());
            if (restException == null) {
                throw new ApiException("Server Error, no content", response.getStatusCode());
            }
            throw new ApiException(restException);
        }

        return Service.fromJson(response.getStream(), client.getObjectMapper());
    }
    private void addPostParams(final Request request) {
        if (uniqueName != null) {
            request.addPostParam("UniqueName", uniqueName);
    
        }
        if (defaultTtl != null) {
            request.addPostParam("DefaultTtl", defaultTtl.toString());
    
        }
        if (callbackUrl != null) {
            request.addPostParam("CallbackUrl", callbackUrl.toString());
    
        }
        if (geoMatchLevel != null) {
            request.addPostParam("GeoMatchLevel", geoMatchLevel.toString());
    
        }
        if (numberSelectionBehavior != null) {
            request.addPostParam("NumberSelectionBehavior", numberSelectionBehavior.toString());
    
        }
        if (interceptCallbackUrl != null) {
            request.addPostParam("InterceptCallbackUrl", interceptCallbackUrl.toString());
    
        }
        if (outOfSessionCallbackUrl != null) {
            request.addPostParam("OutOfSessionCallbackUrl", outOfSessionCallbackUrl.toString());
    
        }
        if (chatInstanceSid != null) {
            request.addPostParam("ChatInstanceSid", chatInstanceSid);
    
        }
    }
}
