/**
 * This code was generated by
 * \ / _    _  _|   _  _
 *  | (_)\/(_)(_|\/| |(/_  v1.0.0
 *       /       /
 */

package com.twilio.rest.chat.v1.service.channel;

import com.twilio.base.Fetcher;
import com.twilio.exception.ApiConnectionException;
import com.twilio.exception.ApiException;
import com.twilio.exception.RestException;
import com.twilio.http.HttpMethod;
import com.twilio.http.Request;
import com.twilio.http.Response;
import com.twilio.http.TwilioRestClient;
import com.twilio.rest.Domains;

public class InviteFetcher extends Fetcher<Invite> {
    private final String pathServiceSid;
    private final String pathChannelSid;
    private final String pathSid;

    /**
     * Construct a new InviteFetcher.
     *
     * @param pathServiceSid The SID of the Service to fetch the resource from
     * @param pathChannelSid The SID of the Channel the resource to fetch belongs to
     * @param pathSid The unique string that identifies the resource
     */
    public InviteFetcher(final String pathServiceSid,
                         final String pathChannelSid,
                         final String pathSid) {
        this.pathServiceSid = pathServiceSid;
        this.pathChannelSid = pathChannelSid;
        this.pathSid = pathSid;
    }

    /**
     * Make the request to the Twilio API to perform the fetch.
     *
     * @param client TwilioRestClient with which to make the request
     * @return Fetched Invite
     */
    @Override
    @SuppressWarnings("checkstyle:linelength")
    public Invite fetch(final TwilioRestClient client) {
        Request request = new Request(
            HttpMethod.GET,
            Domains.CHAT.toString(),
            "/v1/Services/" + this.pathServiceSid + "/Channels/" + this.pathChannelSid + "/Invites/" + this.pathSid + ""
        );

        Response response = client.request(request);

        if (response == null) {
            throw new ApiConnectionException("Invite fetch failed: Unable to connect to server");
        } else if (!TwilioRestClient.SUCCESS.test(response.getStatusCode())) {
            RestException restException = RestException.fromJson(response.getStream(), client.getObjectMapper());
            if (restException == null) {
                throw new ApiException("Server Error, no content");
            }
            throw new ApiException(restException);
        }

        return Invite.fromJson(response.getStream(), client.getObjectMapper());
    }
}