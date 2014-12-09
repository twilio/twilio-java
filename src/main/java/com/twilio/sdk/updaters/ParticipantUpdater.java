package com.twilio.sdk.updaters;

import com.twilio.sdk.clients.TwilioRestClient;
import com.twilio.sdk.exceptions.ApiConnectionException;
import com.twilio.sdk.exceptions.ApiException;
import com.twilio.sdk.http.HttpMethod;
import com.twilio.sdk.http.Request;
import com.twilio.sdk.http.Response;
import com.twilio.sdk.resources.Conference;
import com.twilio.sdk.resources.Participant;

public class ParticipantUpdater extends Updater<Participant> {

    private final String conferenceSid;
    private final String sid;
    private final Boolean muted;

    public ParticipantUpdater(final String conferenceSid, final String sid, final Boolean muted) {
        this.conferenceSid = conferenceSid;
        this.sid = sid;
        this.muted = muted;
    }

    public ParticipantUpdater(final Conference targetConference, final String sid, final Boolean muted) {
        this(targetConference.getSid(), sid, muted);
    }

    public ParticipantUpdater(final Participant target, final Boolean muted) {
        this(target.getConferenceSid(), target.getCallSid(), muted);
    }

    @Override
    public Participant execute(final TwilioRestClient client) {
        Request request = new Request(HttpMethod.POST,
                                      String.format("/Accounts/{AccountSid}/Conferences/%s/Participants/%s.json",
                                                    conferenceSid, sid), client.getAccountSid());
        addPostParams(request);
        Response response = client.request(request);

        if (response == null) {
            throw new ApiConnectionException("Participant update failed: unable to connect to server");
        } else if (response.getStatusCode() != TwilioRestClient.HTTP_STATUS_CODE_OK) {
            throw new ApiException(
                    "Participant update failed: [" + response.getStatusCode() + "] " + response.getContent());
        }

        return Participant.fromJson(response.getStream(), client.getObjectMapper());
    }

    private void addPostParams(final Request request) {
        request.addPostParam("Muted", muted.toString());
    }
}
