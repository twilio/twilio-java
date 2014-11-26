package com.twilio.sdk.fetchers;

import com.twilio.sdk.clients.TwilioRestClient;
import com.twilio.sdk.exceptions.ApiException;
import com.twilio.sdk.http.HttpMethod;
import com.twilio.sdk.http.Request;
import com.twilio.sdk.http.Response;
import com.twilio.sdk.resources.Conference;
import com.twilio.sdk.resources.Participant;

public class ParticipantFetcher extends Fetcher<Participant> {

    private final String conferenceSid;
    private final String sid;

    public ParticipantFetcher(final String conferenceSid, final String sid) {
        this.conferenceSid = conferenceSid;
        this.sid = sid;
    }

    public ParticipantFetcher(final Conference targetConference, final String sid) {
        this(targetConference.getSid(), sid);
    }

    @Override
    public Participant execute(final TwilioRestClient client) {
        Request request = new Request(HttpMethod.GET, "/Accounts/{AccountSid}/Conferences/" + conferenceSid + "/Participants/" + sid + ".json");
        Response response = client.request(request);

        if (response.getStatusCode() != TwilioRestClient.HTTP_STATUS_CODE_OK) {
            throw new ApiException("Unable to fetch Participant " + sid + " in Conference " + conferenceSid);
        }

        return Participant.fromJson(response.getStream(), client.getObjectMapper());
    }
}
