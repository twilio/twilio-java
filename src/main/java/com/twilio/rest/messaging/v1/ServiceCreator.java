/*
 * This code was generated by
 * ___ _ _ _ _ _    _ ____    ____ ____ _    ____ ____ _  _ ____ ____ ____ ___ __   __
 *  |  | | | | |    | |  | __ |  | |__| | __ | __ |___ |\ | |___ |__/ |__|  | |  | |__/
 *  |  |_|_| | |___ | |__|    |__| |  | |    |__] |___ | \| |___ |  \ |  |  | |__| |  \
 *
 * Twilio - Messaging
 * This is the public Twilio REST API.
 *
 * NOTE: This class is auto generated by OpenAPI Generator.
 * https://openapi-generator.tech
 * Do not edit the class manually.
 */

package com.twilio.rest.messaging.v1;

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
    private String friendlyName;
    private URI inboundRequestUrl;
    private HttpMethod inboundMethod;
    private URI fallbackUrl;
    private HttpMethod fallbackMethod;
    private URI statusCallback;
    private Boolean stickySender;
    private Boolean mmsConverter;
    private Boolean smartEncoding;
    private Service.ScanMessageContent scanMessageContent;
    private Boolean fallbackToLongCode;
    private Boolean areaCodeGeomatch;
    private Integer validityPeriod;
    private Boolean synchronousValidation;
    private String usecase;
    private Boolean useInboundWebhookOnNumber;

    public ServiceCreator(final String friendlyName) {
        this.friendlyName = friendlyName;
    }

    public ServiceCreator setFriendlyName(final String friendlyName){
        this.friendlyName = friendlyName;
        return this;
    }
    public ServiceCreator setInboundRequestUrl(final URI inboundRequestUrl){
        this.inboundRequestUrl = inboundRequestUrl;
        return this;
    }

    public ServiceCreator setInboundRequestUrl(final String inboundRequestUrl){
        return setInboundRequestUrl(Promoter.uriFromString(inboundRequestUrl));
    }
    public ServiceCreator setInboundMethod(final HttpMethod inboundMethod){
        this.inboundMethod = inboundMethod;
        return this;
    }
    public ServiceCreator setFallbackUrl(final URI fallbackUrl){
        this.fallbackUrl = fallbackUrl;
        return this;
    }

    public ServiceCreator setFallbackUrl(final String fallbackUrl){
        return setFallbackUrl(Promoter.uriFromString(fallbackUrl));
    }
    public ServiceCreator setFallbackMethod(final HttpMethod fallbackMethod){
        this.fallbackMethod = fallbackMethod;
        return this;
    }
    public ServiceCreator setStatusCallback(final URI statusCallback){
        this.statusCallback = statusCallback;
        return this;
    }

    public ServiceCreator setStatusCallback(final String statusCallback){
        return setStatusCallback(Promoter.uriFromString(statusCallback));
    }
    public ServiceCreator setStickySender(final Boolean stickySender){
        this.stickySender = stickySender;
        return this;
    }
    public ServiceCreator setMmsConverter(final Boolean mmsConverter){
        this.mmsConverter = mmsConverter;
        return this;
    }
    public ServiceCreator setSmartEncoding(final Boolean smartEncoding){
        this.smartEncoding = smartEncoding;
        return this;
    }
    public ServiceCreator setScanMessageContent(final Service.ScanMessageContent scanMessageContent){
        this.scanMessageContent = scanMessageContent;
        return this;
    }
    public ServiceCreator setFallbackToLongCode(final Boolean fallbackToLongCode){
        this.fallbackToLongCode = fallbackToLongCode;
        return this;
    }
    public ServiceCreator setAreaCodeGeomatch(final Boolean areaCodeGeomatch){
        this.areaCodeGeomatch = areaCodeGeomatch;
        return this;
    }
    public ServiceCreator setValidityPeriod(final Integer validityPeriod){
        this.validityPeriod = validityPeriod;
        return this;
    }
    public ServiceCreator setSynchronousValidation(final Boolean synchronousValidation){
        this.synchronousValidation = synchronousValidation;
        return this;
    }
    public ServiceCreator setUsecase(final String usecase){
        this.usecase = usecase;
        return this;
    }
    public ServiceCreator setUseInboundWebhookOnNumber(final Boolean useInboundWebhookOnNumber){
        this.useInboundWebhookOnNumber = useInboundWebhookOnNumber;
        return this;
    }

    @Override
    public Service create(final TwilioRestClient client){
        String path = "/v1/Services";

        path = path.replace("{"+"FriendlyName"+"}", this.friendlyName.toString());

        Request request = new Request(
            HttpMethod.POST,
            Domains.MESSAGING.toString(),
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
                throw new ApiException("Server Error, no content");
            }
            throw new ApiException(restException);
        }

        return Service.fromJson(response.getStream(), client.getObjectMapper());
    }
    private void addPostParams(final Request request) {
        if (friendlyName != null) {
            request.addPostParam("FriendlyName", friendlyName);
    
        }
        if (inboundRequestUrl != null) {
            request.addPostParam("InboundRequestUrl", inboundRequestUrl.toString());
    
        }
        if (inboundMethod != null) {
            request.addPostParam("InboundMethod", inboundMethod.toString());
    
        }
        if (fallbackUrl != null) {
            request.addPostParam("FallbackUrl", fallbackUrl.toString());
    
        }
        if (fallbackMethod != null) {
            request.addPostParam("FallbackMethod", fallbackMethod.toString());
    
        }
        if (statusCallback != null) {
            request.addPostParam("StatusCallback", statusCallback.toString());
    
        }
        if (stickySender != null) {
            request.addPostParam("StickySender", stickySender.toString());
    
        }
        if (mmsConverter != null) {
            request.addPostParam("MmsConverter", mmsConverter.toString());
    
        }
        if (smartEncoding != null) {
            request.addPostParam("SmartEncoding", smartEncoding.toString());
    
        }
        if (scanMessageContent != null) {
            request.addPostParam("ScanMessageContent", scanMessageContent.toString());
    
        }
        if (fallbackToLongCode != null) {
            request.addPostParam("FallbackToLongCode", fallbackToLongCode.toString());
    
        }
        if (areaCodeGeomatch != null) {
            request.addPostParam("AreaCodeGeomatch", areaCodeGeomatch.toString());
    
        }
        if (validityPeriod != null) {
            request.addPostParam("ValidityPeriod", validityPeriod.toString());
    
        }
        if (synchronousValidation != null) {
            request.addPostParam("SynchronousValidation", synchronousValidation.toString());
    
        }
        if (usecase != null) {
            request.addPostParam("Usecase", usecase);
    
        }
        if (useInboundWebhookOnNumber != null) {
            request.addPostParam("UseInboundWebhookOnNumber", useInboundWebhookOnNumber.toString());
    
        }
    }
}
