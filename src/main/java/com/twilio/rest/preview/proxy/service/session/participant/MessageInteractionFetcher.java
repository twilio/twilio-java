/**
 * This code was generated by
 * \ / _    _  _|   _  _
 *  | (_)\/(_)(_|\/| |(/_  v1.0.0
 *       /       /
 */

package com.twilio.rest.preview.proxy.service.session.participant;

import com.twilio.annotations.Preview;
import com.twilio.base.Fetcher;
import com.twilio.exception.ApiConnectionException;
import com.twilio.exception.ApiException;
import com.twilio.exception.RestException;
import com.twilio.http.HttpMethod;
import com.twilio.http.Request;
import com.twilio.http.Response;
import com.twilio.http.TwilioRestClient;
import com.twilio.rest.Domains;

@Preview
public class MessageInteractionFetcher extends Fetcher<MessageInteraction> {
    private final String pathServiceSid;
    private final String pathSessionSid;
    private final String pathParticipantSid;
    private final String pathSid;

    /**
     * Construct a new MessageInteractionFetcher.
     * 
     * @param pathServiceSid Service Sid.
     * @param pathSessionSid Session Sid.
     * @param pathParticipantSid Participant Sid.
     * @param pathSid A string that uniquely identifies this Interaction.
     */
    public MessageInteractionFetcher(final String pathServiceSid, 
                                     final String pathSessionSid, 
                                     final String pathParticipantSid, 
                                     final String pathSid) {
        this.pathServiceSid = pathServiceSid;
        this.pathSessionSid = pathSessionSid;
        this.pathParticipantSid = pathParticipantSid;
        this.pathSid = pathSid;
    }

    /**
     * Make the request to the Twilio API to perform the fetch.
     * 
     * @param client TwilioRestClient with which to make the request
     * @return Fetched MessageInteraction
     */
    @Override
    @SuppressWarnings("checkstyle:linelength")
    public MessageInteraction fetch(final TwilioRestClient client) {
        Request request = new Request(
            HttpMethod.GET,
            Domains.PREVIEW.toString(),
            "/Proxy/Services/" + this.pathServiceSid + "/Sessions/" + this.pathSessionSid + "/Participants/" + this.pathParticipantSid + "/MessageInteractions/" + this.pathSid + "",
            client.getRegion()
        );

        Response response = client.request(request);

        if (response == null) {
            throw new ApiConnectionException("MessageInteraction fetch failed: Unable to connect to server");
        } else if (!TwilioRestClient.SUCCESS.apply(response.getStatusCode())) {
            RestException restException = RestException.fromJson(response.getStream(), client.getObjectMapper());
            if (restException == null) {
                throw new ApiException("Server Error, no content");
            }

            throw new ApiException(
                restException.getMessage(),
                restException.getCode(),
                restException.getMoreInfo(),
                restException.getStatus(),
                null
            );
        }

        return MessageInteraction.fromJson(response.getStream(), client.getObjectMapper());
    }
}