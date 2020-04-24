/**
 * This code was generated by
 * \ / _    _  _|   _  _
 *  | (_)\/(_)(_|\/| |(/_  v1.0.0
 *       /       /
 */

package com.twilio.rest.api.v2010.account.conference;

import com.twilio.base.Updater;
import com.twilio.converter.Promoter;
import com.twilio.exception.ApiConnectionException;
import com.twilio.exception.ApiException;
import com.twilio.exception.RestException;
import com.twilio.http.HttpMethod;
import com.twilio.http.Request;
import com.twilio.http.Response;
import com.twilio.http.TwilioRestClient;
import com.twilio.rest.Domains;

import java.net.URI;

public class ParticipantUpdater extends Updater<Participant> {
    private String pathAccountSid;
    private final String pathConferenceSid;
    private final String pathCallSid;
    private Boolean muted;
    private Boolean hold;
    private URI holdUrl;
    private HttpMethod holdMethod;
    private URI announceUrl;
    private HttpMethod announceMethod;
    private URI waitUrl;
    private HttpMethod waitMethod;
    private Boolean beepOnExit;
    private Boolean endConferenceOnExit;
    private Boolean coaching;
    private String callSidToCoach;

    /**
     * Construct a new ParticipantUpdater.
     *
     * @param pathConferenceSid The SID of the conference with the participant to
     *                          update
     * @param pathCallSid The Call SID of the resources to update
     */
    public ParticipantUpdater(final String pathConferenceSid,
                              final String pathCallSid) {
        this.pathConferenceSid = pathConferenceSid;
        this.pathCallSid = pathCallSid;
    }

    /**
     * Construct a new ParticipantUpdater.
     *
     * @param pathAccountSid The SID of the Account that created the resources to
     *                       update
     * @param pathConferenceSid The SID of the conference with the participant to
     *                          update
     * @param pathCallSid The Call SID of the resources to update
     */
    public ParticipantUpdater(final String pathAccountSid,
                              final String pathConferenceSid,
                              final String pathCallSid) {
        this.pathAccountSid = pathAccountSid;
        this.pathConferenceSid = pathConferenceSid;
        this.pathCallSid = pathCallSid;
    }

    /**
     * Whether the participant should be muted. Can be `true` or `false`. `true`
     * will mute the participant, and `false` will un-mute them. Anything value
     * other than `true` or `false` is interpreted as `false`..
     *
     * @param muted Whether the participant should be muted
     * @return this
     */
    public ParticipantUpdater setMuted(final Boolean muted) {
        this.muted = muted;
        return this;
    }

    /**
     * Whether the participant should be on hold. Can be: `true` or `false`. `true`
     * puts the participant on hold, and `false` lets them rejoin the conference..
     *
     * @param hold Whether the participant should be on hold
     * @return this
     */
    public ParticipantUpdater setHold(final Boolean hold) {
        this.hold = hold;
        return this;
    }

    /**
     * The URL we call using the `hold_method` for  music that plays when the
     * participant is on hold. The URL may return an MP3 file, a WAV file, or a
     * TwiML document that contains the `&lt;Play&gt;`, `&lt;Say&gt;` or
     * `&lt;Redirect&gt;` commands..
     *
     * @param holdUrl The URL we call using the `hold_method` for  music that plays
     *                when the participant is on hold
     * @return this
     */
    public ParticipantUpdater setHoldUrl(final URI holdUrl) {
        this.holdUrl = holdUrl;
        return this;
    }

    /**
     * The URL we call using the `hold_method` for  music that plays when the
     * participant is on hold. The URL may return an MP3 file, a WAV file, or a
     * TwiML document that contains the `&lt;Play&gt;`, `&lt;Say&gt;` or
     * `&lt;Redirect&gt;` commands..
     *
     * @param holdUrl The URL we call using the `hold_method` for  music that plays
     *                when the participant is on hold
     * @return this
     */
    public ParticipantUpdater setHoldUrl(final String holdUrl) {
        return setHoldUrl(Promoter.uriFromString(holdUrl));
    }

    /**
     * The HTTP method we should use to call `hold_url`. Can be: `GET` or `POST` and
     * the default is `GET`..
     *
     * @param holdMethod The HTTP method we should use to call hold_url
     * @return this
     */
    public ParticipantUpdater setHoldMethod(final HttpMethod holdMethod) {
        this.holdMethod = holdMethod;
        return this;
    }

    /**
     * The URL we call using the `announce_method` for an announcement to the
     * participant. The URL must return an MP3 file, a WAV file, or a TwiML document
     * that contains `&lt;Play&gt;` or `&lt;Say&gt;` commands..
     *
     * @param announceUrl The URL we call using the `announce_method` for an
     *                    announcement to the participant
     * @return this
     */
    public ParticipantUpdater setAnnounceUrl(final URI announceUrl) {
        this.announceUrl = announceUrl;
        return this;
    }

    /**
     * The URL we call using the `announce_method` for an announcement to the
     * participant. The URL must return an MP3 file, a WAV file, or a TwiML document
     * that contains `&lt;Play&gt;` or `&lt;Say&gt;` commands..
     *
     * @param announceUrl The URL we call using the `announce_method` for an
     *                    announcement to the participant
     * @return this
     */
    public ParticipantUpdater setAnnounceUrl(final String announceUrl) {
        return setAnnounceUrl(Promoter.uriFromString(announceUrl));
    }

    /**
     * The HTTP method we should use to call `announce_url`. Can be: `GET` or `POST`
     * and defaults to `POST`..
     *
     * @param announceMethod The HTTP method we should use to call announce_url
     * @return this
     */
    public ParticipantUpdater setAnnounceMethod(final HttpMethod announceMethod) {
        this.announceMethod = announceMethod;
        return this;
    }

    /**
     * The URL we should call using the `wait_method` for the music to play while
     * participants are waiting for the conference to start. The default value is
     * the URL of our standard hold music. [Learn more about hold
     * music](https://www.twilio.com/labs/twimlets/holdmusic)..
     *
     * @param waitUrl URL that hosts pre-conference hold music
     * @return this
     */
    public ParticipantUpdater setWaitUrl(final URI waitUrl) {
        this.waitUrl = waitUrl;
        return this;
    }

    /**
     * The URL we should call using the `wait_method` for the music to play while
     * participants are waiting for the conference to start. The default value is
     * the URL of our standard hold music. [Learn more about hold
     * music](https://www.twilio.com/labs/twimlets/holdmusic)..
     *
     * @param waitUrl URL that hosts pre-conference hold music
     * @return this
     */
    public ParticipantUpdater setWaitUrl(final String waitUrl) {
        return setWaitUrl(Promoter.uriFromString(waitUrl));
    }

    /**
     * The HTTP method we should use to call `wait_url`. Can be `GET` or `POST` and
     * the default is `POST`. When using a static audio file, this should be `GET`
     * so that we can cache the file..
     *
     * @param waitMethod The HTTP method we should use to call `wait_url`
     * @return this
     */
    public ParticipantUpdater setWaitMethod(final HttpMethod waitMethod) {
        this.waitMethod = waitMethod;
        return this;
    }

    /**
     * Whether to play a notification beep to the conference when the participant
     * exits. Can be: `true` or `false`..
     *
     * @param beepOnExit Whether to play a notification beep to the conference when
     *                   the participant exit
     * @return this
     */
    public ParticipantUpdater setBeepOnExit(final Boolean beepOnExit) {
        this.beepOnExit = beepOnExit;
        return this;
    }

    /**
     * Whether to end the conference when the participant leaves. Can be: `true` or
     * `false` and defaults to `false`..
     *
     * @param endConferenceOnExit Whether to end the conference when the
     *                            participant leaves
     * @return this
     */
    public ParticipantUpdater setEndConferenceOnExit(final Boolean endConferenceOnExit) {
        this.endConferenceOnExit = endConferenceOnExit;
        return this;
    }

    /**
     * Whether the participant is coaching another call. Can be: `true` or `false`.
     * If not present, defaults to `false` unless `call_sid_to_coach` is defined. If
     * `true`, `call_sid_to_coach` must be defined..
     *
     * @param coaching Indicates if the participant changed to coach
     * @return this
     */
    public ParticipantUpdater setCoaching(final Boolean coaching) {
        this.coaching = coaching;
        return this;
    }

    /**
     * The SID of the participant who is being `coached`. The participant being
     * coached is the only participant who can hear the participant who is
     * `coaching`..
     *
     * @param callSidToCoach The SID of the participant who is being `coached`
     * @return this
     */
    public ParticipantUpdater setCallSidToCoach(final String callSidToCoach) {
        this.callSidToCoach = callSidToCoach;
        return this;
    }

    /**
     * Make the request to the Twilio API to perform the update.
     *
     * @param client TwilioRestClient with which to make the request
     * @return Updated Participant
     */
    @Override
    @SuppressWarnings("checkstyle:linelength")
    public Participant update(final TwilioRestClient client) {
        this.pathAccountSid = this.pathAccountSid == null ? client.getAccountSid() : this.pathAccountSid;
        Request request = new Request(
            HttpMethod.POST,
            Domains.API.toString(),
            "/2010-04-01/Accounts/" + this.pathAccountSid + "/Conferences/" + this.pathConferenceSid + "/Participants/" + this.pathCallSid + ".json",
            client.getRegion()
        );

        addPostParams(request);
        Response response = client.request(request);

        if (response == null) {
            throw new ApiConnectionException("Participant update failed: Unable to connect to server");
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
        if (muted != null) {
            request.addPostParam("Muted", muted.toString());
        }

        if (hold != null) {
            request.addPostParam("Hold", hold.toString());
        }

        if (holdUrl != null) {
            request.addPostParam("HoldUrl", holdUrl.toString());
        }

        if (holdMethod != null) {
            request.addPostParam("HoldMethod", holdMethod.toString());
        }

        if (announceUrl != null) {
            request.addPostParam("AnnounceUrl", announceUrl.toString());
        }

        if (announceMethod != null) {
            request.addPostParam("AnnounceMethod", announceMethod.toString());
        }

        if (waitUrl != null) {
            request.addPostParam("WaitUrl", waitUrl.toString());
        }

        if (waitMethod != null) {
            request.addPostParam("WaitMethod", waitMethod.toString());
        }

        if (beepOnExit != null) {
            request.addPostParam("BeepOnExit", beepOnExit.toString());
        }

        if (endConferenceOnExit != null) {
            request.addPostParam("EndConferenceOnExit", endConferenceOnExit.toString());
        }

        if (coaching != null) {
            request.addPostParam("Coaching", coaching.toString());
        }

        if (callSidToCoach != null) {
            request.addPostParam("CallSidToCoach", callSidToCoach);
        }
    }
}