package com.twilio.sdk.deleters;

import com.twilio.sdk.clients.TwilioRestClient;
import com.twilio.sdk.exceptions.ApiConnectionException;
import com.twilio.sdk.exceptions.ApiException;
import com.twilio.sdk.http.HttpMethod;
import com.twilio.sdk.http.Request;
import com.twilio.sdk.http.Response;
import com.twilio.sdk.resources.Conference;
import com.twilio.sdk.resources.Participant;

public class ParticipantDeleter extends Deleter<Participant> {

    private final String conferenceSid;
    private final String sid;

    public ParticipantDeleter(final String conferenceSid, final String sid) {
        this.conferenceSid = conferenceSid;
        this.sid = sid;
    }

    public ParticipantDeleter(final Conference targetConference, final String sid) {
        this(targetConference.getSid(), sid);
    }

    public ParticipantDeleter(final Participant target) {
        this(target.getConferenceSid(), target.getCallSid());
    }

    @Override
    public void execute(final TwilioRestClient client) {
        Request request = new Request(HttpMethod.DELETE,
                                      String.format("/2010-04-01/Accounts/{AccountSid}/Conferences/%s/Participants/%s.json",
                                                    conferenceSid, sid), client.getAccountSid());
        Response response = client.request(request);

        if (response == null) {
            throw new ApiConnectionException("Participant delete failed: Unable to connect to server");
        } else if (response.getStatusCode() != TwilioRestClient.HTTP_STATUS_CODE_NO_CONTENT) {
            throw new ApiException(
                    "Participant delete failed: [" + response.getStatusCode() + "] " + response.getContent());
        }
    }
}
