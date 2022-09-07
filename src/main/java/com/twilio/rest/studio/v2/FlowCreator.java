/*
 * This code was generated by
 * ___ _ _ _ _ _    _ ____    ____ ____ _    ____ ____ _  _ ____ ____ ____ ___ __   __
 *  |  | | | | |    | |  | __ |  | |__| | __ | __ |___ |\ | |___ |__/ |__|  | |  | |__/
 *  |  |_|_| | |___ | |__|    |__| |  | |    |__] |___ | \| |___ |  \ |  |  | |__| |  \
 *
 * Twilio - Studio
 * This is the public Twilio REST API.
 *
 * NOTE: This class is auto generated by OpenAPI Generator.
 * https://openapi-generator.tech
 * Do not edit the class manually.
 */

package com.twilio.rest.studio.v2;

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
    * Twilio - Studio
    *
    * This is the public Twilio REST API.
    *
    * API version: 1.35.0
    * Contact: support@twilio.com
*/

// Code generated by OpenAPI Generator (https://openapi-generator.tech); DO NOT EDIT.


public class FlowCreator extends Creator<Flow>{
    private String friendlyName;
    private Flow.Status status;
    private Map<String, Object> definition;
    private String commitMessage;

    public FlowCreator(final String friendlyName, final Flow.Status status, final Map<String, Object> definition) {
        this.friendlyName = friendlyName;
        this.status = status;
        this.definition = definition;
    }

    public FlowCreator setFriendlyName(final String friendlyName){
        this.friendlyName = friendlyName;
        return this;
    }
    public FlowCreator setStatus(final Flow.Status status){
        this.status = status;
        return this;
    }
    public FlowCreator setDefinition(final Map<String, Object> definition){
        this.definition = definition;
        return this;
    }
    public FlowCreator setCommitMessage(final String commitMessage){
        this.commitMessage = commitMessage;
        return this;
    }

    @Override
    public Flow create(final TwilioRestClient client){
        String path = "/v2/Flows";

        path = path.replace("{"+"FriendlyName"+"}", this.friendlyName.toString());
        path = path.replace("{"+"Status"+"}", this.status.toString());
        path = path.replace("{"+"Definition"+"}", this.definition.toString());

        Request request = new Request(
            HttpMethod.POST,
            Domains.STUDIO.toString(),
            path
        );
        addPostParams(request);
        Response response = client.request(request);
        if (response == null) {
            throw new ApiConnectionException("Flow creation failed: Unable to connect to server");
        } else if (!TwilioRestClient.SUCCESS.test(response.getStatusCode())) {
            RestException restException = RestException.fromJson(response.getStream(), client.getObjectMapper());
            if (restException == null) {
                throw new ApiException("Server Error, no content");
            }
            throw new ApiException(restException);
        }

        return Flow.fromJson(response.getStream(), client.getObjectMapper());
    }
    private void addPostParams(final Request request) {
        if (friendlyName != null) {
            request.addPostParam("FriendlyName", friendlyName);
    
        }
        if (status != null) {
            request.addPostParam("Status", status.toString());
    
        }
        if (definition != null) {
            request.addPostParam("Definition",  Converter.mapToJson(definition));
    
        }
        if (commitMessage != null) {
            request.addPostParam("CommitMessage", commitMessage);
    
        }
    }
}
