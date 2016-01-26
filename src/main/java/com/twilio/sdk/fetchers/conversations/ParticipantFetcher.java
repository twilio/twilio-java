package com.twilio.sdk.fetchers.conversations;

import com.twilio.sdk.clients.TwilioRestClient;
import com.twilio.sdk.exceptions.ApiConnectionException;
import com.twilio.sdk.exceptions.ApiException;
import com.twilio.sdk.fetchers.Fetcher;
import com.twilio.sdk.http.HttpMethod;
import com.twilio.sdk.http.Request;
import com.twilio.sdk.http.Response;
import com.twilio.sdk.resources.RestException;
import com.twilio.sdk.resources.conversations.Participant;

public class ParticipantFetcher extends Fetcher<Participant> {
    private final String conversationSid;
    private final String sid;

    /**
     * Construct a new ParticipantFetcher
     * 
     * @param conversationSid The conversation_sid
     * @param sid The sid
     */
    public ParticipantFetcher(final String conversationSid, final String sid) {
        this.conversationSid = conversationSid;
        this.sid = sid;
    }

    /**
     * Make the request to the Twilio API to perform the fetch
     * 
     * @param client TwilioRestClient with which to make the request
     * @return Fetched Participant
     */
    @Override
    public Participant execute(final TwilioRestClient client) {
        Request request = new Request(
            HttpMethod.GET,
            "/v1/Conversations/" + this.conversationSid + "/Participants/" + this.sid + "",
            client.getAccountSid()
        );
        
        Response response = client.request(request);
        
        if (response == null) {
            throw new ApiConnectionException("Participant fetch failed: Unable to connect to server");
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
}