package com.twilio.sdk.updaters.api;

import com.twilio.sdk.clients.TwilioRestClient;
import com.twilio.sdk.exceptions.ApiConnectionException;
import com.twilio.sdk.exceptions.ApiException;
import com.twilio.sdk.http.HttpMethod;
import com.twilio.sdk.http.Request;
import com.twilio.sdk.http.Response;
import com.twilio.sdk.resources.RestException;
import com.twilio.sdk.resources.api.Participant;
import com.twilio.sdk.updaters.Updater;

public class ParticipantUpdater extends Updater<Participant> {
    private final String accountSid;
    private final String conferenceSid;
    private final String callSid;
    private final Boolean muted;

    /**
     * Construct a new ParticipantUpdater
     * 
     * @param accountSid The account_sid
     * @param conferenceSid The string that uniquely identifies this conference
     * @param callSid The call_sid
     * @param muted Indicates if the participant should be muted
     */
    public ParticipantUpdater(final String accountSid, final String conferenceSid, final String callSid, final Boolean muted) {
        this.accountSid = accountSid;
        this.conferenceSid = conferenceSid;
        this.callSid = callSid;
        this.muted = muted;
    }

    /**
     * Make the request to the Twilio API to perform the update
     * 
     * @param client TwilioRestClient with which to make the request
     * @return Updated Participant
     */
    @Override
    public Participant execute(final TwilioRestClient client) {
        Request request = new Request(
            HttpMethod.POST,
            "/2010-04-01/Accounts/" + this.accountSid + "/Conferences/" + this.conferenceSid + "/Participants/" + this.callSid + ".json",
            client.getAccountSid()
        );
        
        addPostParams(request);
        Response response = client.request(request);
        
        if (response == null) {
            throw new ApiConnectionException("Participant update failed: Unable to connect to server");
        } else if (response.getStatusCode() != TwilioRestClient.HTTP_STATUS_CODE_OK) {
            RestException restException = RestException.fromJson(response.getStream(), client.getObjectMapper());
            throw new ApiException(
                restException.getMessage(),
                restException.getCode(),
                restException.getMoreInfo(),
                restException.getStatus(),
                null
            );
        }
        
        return Participant.fromJson(response.getStream(), client.getObjectMapper());
    }

    /**
     * Add the requested post parameters to the Request
     * 
     * @param request Request to add post params to
     */
    private void addPostParams(final Request request) {
        if (muted != null) {
            request.addPostParam("Muted", muted.toString());
        }
    }
}