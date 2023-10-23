/*
 * This code was generated by
 * ___ _ _ _ _ _    _ ____    ____ ____ _    ____ ____ _  _ ____ ____ ____ ___ __   __
 *  |  | | | | |    | |  | __ |  | |__| | __ | __ |___ |\ | |___ |__/ |__|  | |  | |__/
 *  |  |_|_| | |___ | |__|    |__| |  | |    |__] |___ | \| |___ |  \ |  |  | |__| |  \
 *
 * Twilio - Api
 * This is the public Twilio REST API.
 *
 * NOTE: This class is auto generated by OpenAPI Generator.
 * https://openapi-generator.tech
 * Do not edit the class manually.
 */

package com.twilio.rest.api.v2010.account.conference;

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




public class RecordingUpdater extends Updater<Recording>{
    private String pathConferenceSid;
    private String pathSid;
    private Recording.Status status;
    private String pathAccountSid;
    private String pauseBehavior;

    public RecordingUpdater(final String pathConferenceSid, final String pathSid, final Recording.Status status){
        this.pathConferenceSid = pathConferenceSid;
        this.pathSid = pathSid;
        this.status = status;
    }
    public RecordingUpdater(final String pathAccountSid, final String pathConferenceSid, final String pathSid, final Recording.Status status){
        this.pathAccountSid = pathAccountSid;
        this.pathConferenceSid = pathConferenceSid;
        this.pathSid = pathSid;
        this.status = status;
    }

    public RecordingUpdater setStatus(final Recording.Status status){
        this.status = status;
        return this;
    }
    public RecordingUpdater setPauseBehavior(final String pauseBehavior){
        this.pauseBehavior = pauseBehavior;
        return this;
    }

    @Override
    public Recording update(final TwilioRestClient client){
        String path = "/2010-04-01/Accounts/{AccountSid}/Conferences/{ConferenceSid}/Recordings/{Sid}.json";

        this.pathAccountSid = this.pathAccountSid == null ? client.getAccountSid() : this.pathAccountSid;
        path = path.replace("{"+"AccountSid"+"}", this.pathAccountSid.toString());
        path = path.replace("{"+"ConferenceSid"+"}", this.pathConferenceSid.toString());
        path = path.replace("{"+"Sid"+"}", this.pathSid.toString());
        path = path.replace("{"+"Status"+"}", this.status.toString());

        Request request = new Request(
            HttpMethod.POST,
            Domains.API.toString(),
            path
        );
        request.setContentType(EnumConstants.ContentType.FORM_URLENCODED);
        addPostParams(request);
        Response response = client.request(request);
        if (response == null) {
            throw new ApiConnectionException("Recording update failed: Unable to connect to server");
        } else if (!TwilioRestClient.SUCCESS.test(response.getStatusCode())) {
            RestException restException = RestException.fromJson(response.getStream(), client.getObjectMapper());
            if (restException == null) {
                throw new ApiException("Server Error, no content", response.getStatusCode());
            }
            throw new ApiException(restException);
        }

        return Recording.fromJson(response.getStream(), client.getObjectMapper());
    }
    private void addPostParams(final Request request) {
        if (status != null) {
            request.addPostParam("Status", status.toString());
    
        }
        if (pauseBehavior != null) {
            request.addPostParam("PauseBehavior", pauseBehavior);
    
        }
    }
}
