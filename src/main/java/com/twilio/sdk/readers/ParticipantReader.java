package com.twilio.sdk.readers;

import com.twilio.sdk.clients.TwilioRestClient;
import com.twilio.sdk.exceptions.ApiException;
import com.twilio.sdk.http.HttpMethod;
import com.twilio.sdk.http.Request;
import com.twilio.sdk.http.Response;
import com.twilio.sdk.resources.Conference;
import com.twilio.sdk.resources.Page;
import com.twilio.sdk.resources.Participant;
import com.twilio.sdk.resources.ResourceSet;

public class ParticipantReader extends Reader<Participant> {

    private final String conferenceSid;
    private Boolean muted;

    public ParticipantReader(final String conferenceSid) {
        this.conferenceSid = conferenceSid;
    }

    public ParticipantReader(final Conference target) {
        this(target.getSid());
    }

    public ParticipantReader byMuted(final Boolean muted) {
        this.muted = muted;
        return this;
    }

    @Override
    public ResourceSet<Participant> execute(final TwilioRestClient client) {
        Request request = new Request(HttpMethod.GET,
                                      "/Accounts/{AccountSid}/Conferences/ " + conferenceSid + "/Participants.json",
                                      client.getAccountSid());
        addQueryParams(request);

        Page<Participant> page = pageForRequest(client, request);

        return new ResourceSet<>(this, client, page);
    }

    @Override
    public Page<Participant> nextPage(final String nextPageUri, final TwilioRestClient client) {
        Request request = new Request(HttpMethod.GET, nextPageUri, client.getAccountSid());
        return pageForRequest(client, request);
    }

    protected Page<Participant> pageForRequest(final TwilioRestClient client, final Request request) {
        Response response = client.request(request);

        if (response.getStatusCode() != TwilioRestClient.HTTP_STATUS_CODE_OK) {
            throw new ApiException("Unable to build page for Participant");
        }

        Page<Participant> result = new Page<>();
        result.deserialize("participants", response.getContent(), Participant.class, client.getObjectMapper());

        return result;
    }

    private void addQueryParams(final Request request) {
        if (muted != null) {
            request.addQueryParam("Muted", muted.toString());
        }
    }
}
