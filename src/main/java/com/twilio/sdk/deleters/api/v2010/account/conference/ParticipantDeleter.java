package com.twilio.sdk.deleters.api.v2010.account.conference;

import com.twilio.sdk.clients.TwilioRestClient;
import com.twilio.sdk.deleters.Deleter;
import com.twilio.sdk.exceptions.ApiConnectionException;
import com.twilio.sdk.exceptions.ApiException;
import com.twilio.sdk.http.HttpMethod;
import com.twilio.sdk.http.Request;
import com.twilio.sdk.http.Response;
import com.twilio.sdk.resources.RestException;
import com.twilio.sdk.resources.api.Participant;

public class ParticipantDeleter extends Deleter<Participant> {
    private final String accountSid;
    private final String conferenceSid;
    private final String callSid;

    /**
     * Construct a new ParticipantDeleter
     * 
     * @param accountSid The account_sid
     * @param conferenceSid The string that uniquely identifies this conference
     * @param callSid The call_sid
     */
    public ParticipantDeleter(final String accountSid, final String conferenceSid, final String callSid) {
        this.accountSid = accountSid;
        this.conferenceSid = conferenceSid;
        this.callSid = callSid;
    }

    /**
     * Make the request to the Twilio API to perform the delete
     * 
     * @param client TwilioRestClient with which to make the request
     */
    @Override
    public void execute(final TwilioRestClient client) {
        Request request = new Request(
            HttpMethod.DELETE,
            "/2010-04-01/Accounts/" + this.accountSid + "/Conferences/" + this.conferenceSid + "/Participants/" + this.callSid + ".json",
            client.getAccountSid()
        );
        
        Response response = client.request(request);
        
        if (response == null) {
            throw new ApiConnectionException("Participant delete failed: Unable to connect to server");
        } else if (response.getStatusCode() != TwilioRestClient.HTTP_STATUS_CODE_NO_CONTENT) {
            RestException restException = RestException.fromJson(response.getStream(), client.getObjectMapper());
            throw new ApiException(
                restException.getMessage(),
                restException.getCode(),
                restException.getMoreInfo(),
                restException.getStatus(),
                null
            );
        }
    }
}