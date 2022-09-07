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




/*
    * Twilio - Api
    *
    * This is the public Twilio REST API.
    *
    * API version: 1.35.0
    * Contact: support@twilio.com
*/

// Code generated by OpenAPI Generator (https://openapi-generator.tech); DO NOT EDIT.


public class ParticipantUpdater extends Updater<Participant>{
    private String conferenceSid;
    private String callSid;
    private String accountSid;
    private Boolean muted;
    private Boolean hold;
    private URI holdUrl;
    private HttpMethod holdMethod;
    private URI announceUrl;
    private HttpMethod announceMethod;
    private URI waitUrl;
    private HttpMethod waitMethod;
    private Boolean beepOnExit;
    private Boolean endConferenceOnExit;
    private Boolean coaching;
    private String callSidToCoach;

    public ParticipantUpdater(final String conferenceSid, final String callSid){
        this.conferenceSid = conferenceSid;
        this.callSid = callSid;
    }
    public ParticipantUpdater(final String accountSid, final String conferenceSid, final String callSid){
        this.accountSid = accountSid;
        this.conferenceSid = conferenceSid;
        this.callSid = callSid;
    }

    public ParticipantUpdater setMuted(final Boolean muted){
        this.muted = muted;
        return this;
    }
    public ParticipantUpdater setHold(final Boolean hold){
        this.hold = hold;
        return this;
    }
    public ParticipantUpdater setHoldUrl(final URI holdUrl){
        this.holdUrl = holdUrl;
        return this;
    }

    public ParticipantUpdater setHoldUrl(final String holdUrl){
    this.holdUrl = Promoter.uriFromString(holdUrl);
    return this;
    }
    public ParticipantUpdater setHoldMethod(final HttpMethod holdMethod){
        this.holdMethod = holdMethod;
        return this;
    }
    public ParticipantUpdater setAnnounceUrl(final URI announceUrl){
        this.announceUrl = announceUrl;
        return this;
    }

    public ParticipantUpdater setAnnounceUrl(final String announceUrl){
    this.announceUrl = Promoter.uriFromString(announceUrl);
    return this;
    }
    public ParticipantUpdater setAnnounceMethod(final HttpMethod announceMethod){
        this.announceMethod = announceMethod;
        return this;
    }
    public ParticipantUpdater setWaitUrl(final URI waitUrl){
        this.waitUrl = waitUrl;
        return this;
    }

    public ParticipantUpdater setWaitUrl(final String waitUrl){
    this.waitUrl = Promoter.uriFromString(waitUrl);
    return this;
    }
    public ParticipantUpdater setWaitMethod(final HttpMethod waitMethod){
        this.waitMethod = waitMethod;
        return this;
    }
    public ParticipantUpdater setBeepOnExit(final Boolean beepOnExit){
        this.beepOnExit = beepOnExit;
        return this;
    }
    public ParticipantUpdater setEndConferenceOnExit(final Boolean endConferenceOnExit){
        this.endConferenceOnExit = endConferenceOnExit;
        return this;
    }
    public ParticipantUpdater setCoaching(final Boolean coaching){
        this.coaching = coaching;
        return this;
    }
    public ParticipantUpdater setCallSidToCoach(final String callSidToCoach){
        this.callSidToCoach = callSidToCoach;
        return this;
    }

    @Override
    public Participant update(final TwilioRestClient client){
        String path = "/2010-04-01/Accounts/{AccountSid}/Conferences/{ConferenceSid}/Participants/{CallSid}.json";

        this.accountSid = this.accountSid == null ? client.getAccountSid() : this.accountSid;
        path = path.replace("{"+"AccountSid"+"}", this.accountSid.toString());
        path = path.replace("{"+"ConferenceSid"+"}", this.conferenceSid.toString());
        path = path.replace("{"+"CallSid"+"}", this.callSid.toString());

        Request request = new Request(
            HttpMethod.POST,
            Domains.API.toString(),
            path
        );
        addPostParams(request);
        Response response = client.request(request);
        if (response == null) {
            throw new ApiConnectionException("Participant update failed: Unable to connect to server");
        } else if (!TwilioRestClient.SUCCESS.test(response.getStatusCode())) {
            RestException restException = RestException.fromJson(response.getStream(), client.getObjectMapper());
            if (restException == null) {
                throw new ApiException("Server Error, no content");
            }
            throw new ApiException(restException);
        }

        return Participant.fromJson(response.getStream(), client.getObjectMapper());
    }
    private void addPostParams(final Request request) {
        if (muted != null) {
            request.addPostParam("Muted", muted.toString());
    
        }
        if (hold != null) {
            request.addPostParam("Hold", hold.toString());
    
        }
        if (holdUrl != null) {
            request.addPostParam("HoldUrl", holdUrl.toString());
    
        }
        if (holdMethod != null) {
            request.addPostParam("HoldMethod", holdMethod.toString());
    
        }
        if (announceUrl != null) {
            request.addPostParam("AnnounceUrl", announceUrl.toString());
    
        }
        if (announceMethod != null) {
            request.addPostParam("AnnounceMethod", announceMethod.toString());
    
        }
        if (waitUrl != null) {
            request.addPostParam("WaitUrl", waitUrl.toString());
    
        }
        if (waitMethod != null) {
            request.addPostParam("WaitMethod", waitMethod.toString());
    
        }
        if (beepOnExit != null) {
            request.addPostParam("BeepOnExit", beepOnExit.toString());
    
        }
        if (endConferenceOnExit != null) {
            request.addPostParam("EndConferenceOnExit", endConferenceOnExit.toString());
    
        }
        if (coaching != null) {
            request.addPostParam("Coaching", coaching.toString());
    
        }
        if (callSidToCoach != null) {
            request.addPostParam("CallSidToCoach", callSidToCoach);
    
        }
    }
}
