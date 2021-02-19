/**
 * This code was generated by
 * \ / _    _  _|   _  _
 *  | (_)\/(_)(_|\/| |(/_  v1.0.0
 *       /       /
 */

package com.twilio.rest.proxy.v1.service;

import com.twilio.base.Creator;
import com.twilio.converter.Converter;
import com.twilio.converter.DateConverter;
import com.twilio.converter.Promoter;
import com.twilio.exception.ApiConnectionException;
import com.twilio.exception.ApiException;
import com.twilio.exception.RestException;
import com.twilio.http.HttpMethod;
import com.twilio.http.Request;
import com.twilio.http.Response;
import com.twilio.http.TwilioRestClient;
import com.twilio.rest.Domains;

import java.time.ZonedDateTime;
import java.util.List;
import java.util.Map;

/**
 * PLEASE NOTE that this class contains beta products that are subject to
 * change. Use them with caution.
 */
public class SessionCreator extends Creator<Session> {
    private final String pathServiceSid;
    private String uniqueName;
    private ZonedDateTime dateExpiry;
    private Integer ttl;
    private Session.Mode mode;
    private Session.Status status;
    private List<Map<String, Object>> participants;
    private Boolean failOnParticipantConflict;

    /**
     * Construct a new SessionCreator.
     *
     * @param pathServiceSid The SID of the parent Service resource
     */
    public SessionCreator(final String pathServiceSid) {
        this.pathServiceSid = pathServiceSid;
    }

    /**
     * An application-defined string that uniquely identifies the resource. This
     * value must be 191 characters or fewer in length and be unique. **This value
     * should not have PII.**.
     *
     * @param uniqueName An application-defined string that uniquely identifies the
     *                   resource
     * @return this
     */
    public SessionCreator setUniqueName(final String uniqueName) {
        this.uniqueName = uniqueName;
        return this;
    }

    /**
     * The <a href="https://en.wikipedia.org/wiki/ISO_8601">ISO 8601</a> date when
     * the Session should expire. If this is value is present, it overrides the
     * `ttl` value..
     *
     * @param dateExpiry The ISO 8601 date when the Session should expire
     * @return this
     */
    public SessionCreator setDateExpiry(final ZonedDateTime dateExpiry) {
        this.dateExpiry = dateExpiry;
        return this;
    }

    /**
     * The time, in seconds, when the session will expire. The time is measured from
     * the last Session create or the Session's last Interaction..
     *
     * @param ttl When the session will expire
     * @return this
     */
    public SessionCreator setTtl(final Integer ttl) {
        this.ttl = ttl;
        return this;
    }

    /**
     * The Mode of the Session. Can be: `message-only`, `voice-only`, or
     * `voice-and-message` and the default value is `voice-and-message`..
     *
     * @param mode The Mode of the Session
     * @return this
     */
    public SessionCreator setMode(final Session.Mode mode) {
        this.mode = mode;
        return this;
    }

    /**
     * The initial status of the Session. Can be: `open`, `in-progress`, `closed`,
     * `failed`, or `unknown`. The default is `open` on create..
     *
     * @param status Session status
     * @return this
     */
    public SessionCreator setStatus(final Session.Status status) {
        this.status = status;
        return this;
    }

    /**
     * The Participant objects to include in the new session..
     *
     * @param participants The Participant objects to include in the new session
     * @return this
     */
    public SessionCreator setParticipants(final List<Map<String, Object>> participants) {
        this.participants = participants;
        return this;
    }

    /**
     * The Participant objects to include in the new session..
     *
     * @param participants The Participant objects to include in the new session
     * @return this
     */
    public SessionCreator setParticipants(final Map<String, Object> participants) {
        return setParticipants(Promoter.listOfOne(participants));
    }

    /**
     * [Experimental] For accounts with the ProxyAllowParticipantConflict account
     * flag, setting to true enables per-request opt-in to allowing Proxy to reject
     * a Session create (with Participants) request that could cause the same
     * Identifier/ProxyIdentifier pair to be active in multiple Sessions. Depending
     * on the context, this could be a 409 error (Twilio error code 80623) or a 400
     * error (Twilio error code 80604). If not provided, requests will be allowed to
     * succeed and a Debugger notification (80802) will be emitted. Having multiple,
     * active Participants with the same Identifier/ProxyIdentifier pair causes
     * calls and messages from affected Participants to be routed incorrectly.
     * Please note, the default behavior for accounts without the
     * ProxyAllowParticipantConflict flag is to reject the request as described.
     * This will eventually be the default for all accounts..
     *
     * @param failOnParticipantConflict An experimental parameter to override the
     *                                  ProxyAllowParticipantConflict account flag
     *                                  on a per-request basis.
     * @return this
     */
    public SessionCreator setFailOnParticipantConflict(final Boolean failOnParticipantConflict) {
        this.failOnParticipantConflict = failOnParticipantConflict;
        return this;
    }

    /**
     * Make the request to the Twilio API to perform the create.
     *
     * @param client TwilioRestClient with which to make the request
     * @return Created Session
     */
    @Override
    @SuppressWarnings("checkstyle:linelength")
    public Session create(final TwilioRestClient client) {
        Request request = new Request(
            HttpMethod.POST,
            Domains.PROXY.toString(),
            "/v1/Services/" + this.pathServiceSid + "/Sessions"
        );

        addPostParams(request);
        Response response = client.request(request);

        if (response == null) {
            throw new ApiConnectionException("Session creation failed: Unable to connect to server");
        } else if (!TwilioRestClient.SUCCESS.test(response.getStatusCode())) {
            RestException restException = RestException.fromJson(response.getStream(), client.getObjectMapper());
            if (restException == null) {
                throw new ApiException("Server Error, no content");
            }
            throw new ApiException(restException);
        }

        return Session.fromJson(response.getStream(), client.getObjectMapper());
    }

    /**
     * Add the requested post parameters to the Request.
     *
     * @param request Request to add post params to
     */
    private void addPostParams(final Request request) {
        if (uniqueName != null) {
            request.addPostParam("UniqueName", uniqueName);
        }

        if (dateExpiry != null) {
            request.addPostParam("DateExpiry", dateExpiry.toOffsetDateTime().toString());
        }

        if (ttl != null) {
            request.addPostParam("Ttl", ttl.toString());
        }

        if (mode != null) {
            request.addPostParam("Mode", mode.toString());
        }

        if (status != null) {
            request.addPostParam("Status", status.toString());
        }

        if (participants != null) {
            for (Map<String, Object> prop : participants) {
                request.addPostParam("Participants", Converter.mapToJson(prop));
            }
        }

        if (failOnParticipantConflict != null) {
            request.addPostParam("FailOnParticipantConflict", failOnParticipantConflict.toString());
        }
    }
}