/**
 * This code was generated by
 * \ / _    _  _|   _  _
 *  | (_)\/(_)(_|\/| |(/_  v1.0.0
 *       /       /
 */

package com.twilio.rest.proxy.v1.service.session;

import com.twilio.base.Creator;
import com.twilio.exception.ApiConnectionException;
import com.twilio.exception.ApiException;
import com.twilio.exception.RestException;
import com.twilio.http.HttpMethod;
import com.twilio.http.Request;
import com.twilio.http.Response;
import com.twilio.http.TwilioRestClient;
import com.twilio.rest.Domains;

/**
 * PLEASE NOTE that this class contains beta products that are subject to
 * change. Use them with caution.
 */
public class ParticipantCreator extends Creator<Participant> {
    private final String pathServiceSid;
    private final String pathSessionSid;
    private final String identifier;
    private String friendlyName;
    private String proxyIdentifier;
    private String proxyIdentifierSid;

    /**
     * Construct a new ParticipantCreator.
     *
     * @param pathServiceSid The SID of the parent Service resource
     * @param pathSessionSid The SID of the parent Session resource
     * @param identifier The phone number of the Participant
     */
    public ParticipantCreator(final String pathServiceSid,
                              final String pathSessionSid,
                              final String identifier) {
        this.pathServiceSid = pathServiceSid;
        this.pathSessionSid = pathSessionSid;
        this.identifier = identifier;
    }

    /**
     * The string that you assigned to describe the participant. This value must be
     * 255 characters or fewer. **This value should not have PII.**.
     *
     * @param friendlyName The string that you assigned to describe the participant
     * @return this
     */
    public ParticipantCreator setFriendlyName(final String friendlyName) {
        this.friendlyName = friendlyName;
        return this;
    }

    /**
     * The proxy phone number to use for the Participant. If not specified, Proxy
     * will select a number from the pool..
     *
     * @param proxyIdentifier The proxy phone number to use for the Participant
     * @return this
     */
    public ParticipantCreator setProxyIdentifier(final String proxyIdentifier) {
        this.proxyIdentifier = proxyIdentifier;
        return this;
    }

    /**
     * The SID of the Proxy Identifier to assign to the Participant..
     *
     * @param proxyIdentifierSid The Proxy Identifier Sid
     * @return this
     */
    public ParticipantCreator setProxyIdentifierSid(final String proxyIdentifierSid) {
        this.proxyIdentifierSid = proxyIdentifierSid;
        return this;
    }

    /**
     * Make the request to the Twilio API to perform the create.
     *
     * @param client TwilioRestClient with which to make the request
     * @return Created Participant
     */
    @Override
    @SuppressWarnings("checkstyle:linelength")
    public Participant create(final TwilioRestClient client) {
        Request request = new Request(
            HttpMethod.POST,
            Domains.PROXY.toString(),
            "/v1/Services/" + this.pathServiceSid + "/Sessions/" + this.pathSessionSid + "/Participants",
            client.getRegion()
        );

        addPostParams(request);
        Response response = client.request(request);

        if (response == null) {
            throw new ApiConnectionException("Participant creation failed: Unable to connect to server");
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
                restException.getDetails(),
                null
            );
        }

        return Participant.fromJson(response.getStream(), client.getObjectMapper());
    }

    /**
     * Add the requested post parameters to the Request.
     *
     * @param request Request to add post params to
     */
    private void addPostParams(final Request request) {
        if (identifier != null) {
            request.addPostParam("Identifier", identifier);
        }

        if (friendlyName != null) {
            request.addPostParam("FriendlyName", friendlyName);
        }

        if (proxyIdentifier != null) {
            request.addPostParam("ProxyIdentifier", proxyIdentifier);
        }

        if (proxyIdentifierSid != null) {
            request.addPostParam("ProxyIdentifierSid", proxyIdentifierSid);
        }
    }
}