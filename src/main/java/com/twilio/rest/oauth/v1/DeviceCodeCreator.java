/*
 * This code was generated by
 * ___ _ _ _ _ _    _ ____    ____ ____ _    ____ ____ _  _ ____ ____ ____ ___ __   __
 *  |  | | | | |    | |  | __ |  | |__| | __ | __ |___ |\ | |___ |__/ |__|  | |  | |__/
 *  |  |_|_| | |___ | |__|    |__| |  | |    |__] |___ | \| |___ |  \ |  |  | |__| |  \
 *
 * Twilio - Oauth
 * This is the public Twilio REST API.
 *
 * NOTE: This class is auto generated by OpenAPI Generator.
 * https://openapi-generator.tech
 * Do not edit the class manually.
 */

package com.twilio.rest.oauth.v1;

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
import java.util.List;

import java.util.List;



public class DeviceCodeCreator extends Creator<DeviceCode>{
    private String clientSid;
    private List<String> scopes;
    private List<String> audiences;

    public DeviceCodeCreator(final String clientSid, final List<String> scopes) {
        this.clientSid = clientSid;
        this.scopes = scopes;
    }

    public DeviceCodeCreator setClientSid(final String clientSid){
        this.clientSid = clientSid;
        return this;
    }
    public DeviceCodeCreator setScopes(final List<String> scopes){
        this.scopes = scopes;
        return this;
    }
    public DeviceCodeCreator setScopes(final String scopes){
        return setScopes(Promoter.listOfOne(scopes));
    }
    public DeviceCodeCreator setAudiences(final List<String> audiences){
        this.audiences = audiences;
        return this;
    }
    public DeviceCodeCreator setAudiences(final String audiences){
        return setAudiences(Promoter.listOfOne(audiences));
    }

    @Override
    public DeviceCode create(final TwilioRestClient client){
        String path = "/v1/device/code";

        path = path.replace("{"+"ClientSid"+"}", this.clientSid.toString());
        path = path.replace("{"+"Scopes"+"}", this.scopes.toString());

        Request request = new Request(
            HttpMethod.POST,
            Domains.OAUTH.toString(),
            path
        );
        request.setContentType(EnumConstants.ContentType.FORM_URLENCODED);
        addPostParams(request);
        Response response = client.request(request);
        if (response == null) {
            throw new ApiConnectionException("DeviceCode creation failed: Unable to connect to server");
        } else if (!TwilioRestClient.SUCCESS.test(response.getStatusCode())) {
            RestException restException = RestException.fromJson(response.getStream(), client.getObjectMapper());
            if (restException == null) {
                throw new ApiException("Server Error, no content");
            }
            throw new ApiException(restException);
        }

        return DeviceCode.fromJson(response.getStream(), client.getObjectMapper());
    }
    private void addPostParams(final Request request) {
        if (clientSid != null) {
            request.addPostParam("ClientSid", clientSid);
    
        }
        if (scopes != null) {
            for (String prop : scopes) {
                request.addPostParam("Scopes", prop);
            }
    
        }
        if (audiences != null) {
            for (String prop : audiences) {
                request.addPostParam("Audiences", prop);
            }
    
        }
    }
}
