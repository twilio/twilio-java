/*
 * This code was generated by
 * ___ _ _ _ _ _    _ ____    ____ ____ _    ____ ____ _  _ ____ ____ ____ ___ __   __
 *  |  | | | | |    | |  | __ |  | |__| | __ | __ |___ |\ | |___ |__/ |__|  | |  | |__/
 *  |  |_|_| | |___ | |__|    |__| |  | |    |__] |___ | \| |___ |  \ |  |  | |__| |  \
 *
 * Twilio - Events
 * This is the public Twilio REST API.
 *
 * NOTE: This class is auto generated by OpenAPI Generator.
 * https://openapi-generator.tech
 * Do not edit the class manually.
 */

package com.twilio.rest.events.v1.subscription;

import com.twilio.base.Creator;
import com.twilio.constant.EnumConstants;
import com.twilio.exception.ApiConnectionException;
import com.twilio.exception.ApiException;
import com.twilio.exception.RestException;
import com.twilio.http.HttpMethod;
import com.twilio.http.Request;
import com.twilio.http.Response;
import com.twilio.http.TwilioRestClient;
import com.twilio.rest.Domains;




public class SubscribedEventCreator extends Creator<SubscribedEvent>{
    private String pathSubscriptionSid;
    private String type;
    private Integer schemaVersion;

    public SubscribedEventCreator(final String pathSubscriptionSid, final String type) {
        this.pathSubscriptionSid = pathSubscriptionSid;
        this.type = type;
    }

    public SubscribedEventCreator setType(final String type){
        this.type = type;
        return this;
    }
    public SubscribedEventCreator setSchemaVersion(final Integer schemaVersion){
        this.schemaVersion = schemaVersion;
        return this;
    }

    @Override
    public SubscribedEvent create(final TwilioRestClient client){
        String path = "/v1/Subscriptions/{SubscriptionSid}/SubscribedEvents";

        path = path.replace("{"+"SubscriptionSid"+"}", this.pathSubscriptionSid.toString());
        path = path.replace("{"+"Type"+"}", this.type.toString());

        Request request = new Request(
            HttpMethod.POST,
            Domains.EVENTS.toString(),
            path
        );
        request.setContentType(EnumConstants.ContentType.FORM_URLENCODED);
        addPostParams(request);
        Response response = client.request(request);
        if (response == null) {
            throw new ApiConnectionException("SubscribedEvent creation failed: Unable to connect to server");
        } else if (!TwilioRestClient.SUCCESS.test(response.getStatusCode())) {
            RestException restException = RestException.fromJson(response.getStream(), client.getObjectMapper());
            if (restException == null) {
                throw new ApiException("Server Error, no content");
            }
            throw new ApiException(restException);
        }

        return SubscribedEvent.fromJson(response.getStream(), client.getObjectMapper());
    }
    private void addPostParams(final Request request) {
        if (type != null) {
            request.addPostParam("Type", type);
    
        }
        if (schemaVersion != null) {
            request.addPostParam("SchemaVersion", schemaVersion.toString());
    
        }
    }
}
