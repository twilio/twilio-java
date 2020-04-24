/**
 * This code was generated by
 * \ / _    _  _|   _  _
 *  | (_)\/(_)(_|\/| |(/_  v1.0.0
 *       /       /
 */

package com.twilio.rest.api.v2010.account.conference;

import com.twilio.base.Creator;
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
import java.util.List;

public class ParticipantCreator extends Creator<Participant> {
    private String pathAccountSid;
    private final String pathConferenceSid;
    private final com.twilio.type.PhoneNumber from;
    private final com.twilio.type.PhoneNumber to;
    private URI statusCallback;
    private HttpMethod statusCallbackMethod;
    private List<String> statusCallbackEvent;
    private Integer timeout;
    private Boolean record;
    private Boolean muted;
    private String beep;
    private Boolean startConferenceOnEnter;
    private Boolean endConferenceOnExit;
    private URI waitUrl;
    private HttpMethod waitMethod;
    private Boolean earlyMedia;
    private Integer maxParticipants;
    private String conferenceRecord;
    private String conferenceTrim;
    private URI conferenceStatusCallback;
    private HttpMethod conferenceStatusCallbackMethod;
    private List<String> conferenceStatusCallbackEvent;
    private String recordingChannels;
    private URI recordingStatusCallback;
    private HttpMethod recordingStatusCallbackMethod;
    private String sipAuthUsername;
    private String sipAuthPassword;
    private String region;
    private URI conferenceRecordingStatusCallback;
    private HttpMethod conferenceRecordingStatusCallbackMethod;
    private List<String> recordingStatusCallbackEvent;
    private List<String> conferenceRecordingStatusCallbackEvent;
    private Boolean coaching;
    private String callSidToCoach;
    private String byoc;

    /**
     * Construct a new ParticipantCreator.
     *
     * @param pathConferenceSid The SID of the participant's conference
     * @param from The `from` phone number used to invite a participant
     * @param to The number, client id, or sip address of the new participant
     */
    public ParticipantCreator(final String pathConferenceSid,
                              final com.twilio.type.PhoneNumber from,
                              final com.twilio.type.PhoneNumber to) {
        this.pathConferenceSid = pathConferenceSid;
        this.from = from;
        this.to = to;
    }

    /**
     * Construct a new ParticipantCreator.
     *
     * @param pathAccountSid The SID of the Account that will create the resource
     * @param pathConferenceSid The SID of the participant's conference
     * @param from The `from` phone number used to invite a participant
     * @param to The number, client id, or sip address of the new participant
     */
    public ParticipantCreator(final String pathAccountSid,
                              final String pathConferenceSid,
                              final com.twilio.type.PhoneNumber from,
                              final com.twilio.type.PhoneNumber to) {
        this.pathAccountSid = pathAccountSid;
        this.pathConferenceSid = pathConferenceSid;
        this.from = from;
        this.to = to;
    }

    /**
     * The URL we should call using the `status_callback_method` to send status
     * information to your application..
     *
     * @param statusCallback The URL we should call to send status information to
     *                       your application
     * @return this
     */
    public ParticipantCreator setStatusCallback(final URI statusCallback) {
        this.statusCallback = statusCallback;
        return this;
    }

    /**
     * The URL we should call using the `status_callback_method` to send status
     * information to your application..
     *
     * @param statusCallback The URL we should call to send status information to
     *                       your application
     * @return this
     */
    public ParticipantCreator setStatusCallback(final String statusCallback) {
        return setStatusCallback(Promoter.uriFromString(statusCallback));
    }

    /**
     * The HTTP method we should use to call `status_callback`. Can be: `GET` and
     * `POST` and defaults to `POST`..
     *
     * @param statusCallbackMethod The HTTP method we should use to call
     *                             `status_callback`
     * @return this
     */
    public ParticipantCreator setStatusCallbackMethod(final HttpMethod statusCallbackMethod) {
        this.statusCallbackMethod = statusCallbackMethod;
        return this;
    }

    /**
     * The conference state changes that should generate a call to
     * `status_callback`. Can be: `initiated`, `ringing`, `answered`, and
     * `completed`. Separate multiple values with a space. The default value is
     * `completed`..
     *
     * @param statusCallbackEvent Set state change events that will trigger a
     *                            callback
     * @return this
     */
    public ParticipantCreator setStatusCallbackEvent(final List<String> statusCallbackEvent) {
        this.statusCallbackEvent = statusCallbackEvent;
        return this;
    }

    /**
     * The conference state changes that should generate a call to
     * `status_callback`. Can be: `initiated`, `ringing`, `answered`, and
     * `completed`. Separate multiple values with a space. The default value is
     * `completed`..
     *
     * @param statusCallbackEvent Set state change events that will trigger a
     *                            callback
     * @return this
     */
    public ParticipantCreator setStatusCallbackEvent(final String statusCallbackEvent) {
        return setStatusCallbackEvent(Promoter.listOfOne(statusCallbackEvent));
    }

    /**
     * The number of seconds that we should allow the phone to ring before assuming
     * there is no answer. Can be an integer between `5` and `600`, inclusive. The
     * default value is `60`. We always add a 5-second timeout buffer to outgoing
     * calls, so  value of 10 would result in an actual timeout that was closer to
     * 15 seconds..
     *
     * @param timeout he number of seconds that we should wait for an answer
     * @return this
     */
    public ParticipantCreator setTimeout(final Integer timeout) {
        this.timeout = timeout;
        return this;
    }

    /**
     * Whether to record the participant and their conferences, including the time
     * between conferences. Can be `true` or `false` and the default is `false`..
     *
     * @param record Whether to record the participant and their conferences
     * @return this
     */
    public ParticipantCreator setRecord(final Boolean record) {
        this.record = record;
        return this;
    }

    /**
     * Whether the agent is muted in the conference. Can be `true` or `false` and
     * the default is `false`..
     *
     * @param muted Whether to mute the agent
     * @return this
     */
    public ParticipantCreator setMuted(final Boolean muted) {
        this.muted = muted;
        return this;
    }

    /**
     * Whether to play a notification beep to the conference when the participant
     * joins. Can be: `true`, `false`, `onEnter`, or `onExit`. The default value is
     * `true`..
     *
     * @param beep Whether to play a notification beep to the conference when the
     *             participant joins
     * @return this
     */
    public ParticipantCreator setBeep(final String beep) {
        this.beep = beep;
        return this;
    }

    /**
     * Whether to start the conference when the participant joins, if it has not
     * already started. Can be: `true` or `false` and the default is `true`. If
     * `false` and the conference has not started, the participant is muted and
     * hears background music until another participant starts the conference..
     *
     * @param startConferenceOnEnter Whether the conference starts when the
     *                               participant joins the conference
     * @return this
     */
    public ParticipantCreator setStartConferenceOnEnter(final Boolean startConferenceOnEnter) {
        this.startConferenceOnEnter = startConferenceOnEnter;
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
    public ParticipantCreator setEndConferenceOnExit(final Boolean endConferenceOnExit) {
        this.endConferenceOnExit = endConferenceOnExit;
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
    public ParticipantCreator setWaitUrl(final URI waitUrl) {
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
    public ParticipantCreator setWaitUrl(final String waitUrl) {
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
    public ParticipantCreator setWaitMethod(final HttpMethod waitMethod) {
        this.waitMethod = waitMethod;
        return this;
    }

    /**
     * Whether to allow an agent to hear the state of the outbound call, including
     * ringing or disconnect messages. Can be: `true` or `false` and defaults to
     * `true`..
     *
     * @param earlyMedia Whether agents can hear the state of the outbound call
     * @return this
     */
    public ParticipantCreator setEarlyMedia(final Boolean earlyMedia) {
        this.earlyMedia = earlyMedia;
        return this;
    }

    /**
     * The maximum number of participants in the conference. Can be a positive
     * integer from `2` to `250`. The default value is `250`..
     *
     * @param maxParticipants The maximum number of agent conference participants
     * @return this
     */
    public ParticipantCreator setMaxParticipants(final Integer maxParticipants) {
        this.maxParticipants = maxParticipants;
        return this;
    }

    /**
     * Whether to record the conference the participant is joining. Can be: `true`,
     * `false`, `record-from-start`, and `do-not-record`. The default value is
     * `false`..
     *
     * @param conferenceRecord Whether to record the conference the participant is
     *                         joining
     * @return this
     */
    public ParticipantCreator setConferenceRecord(final String conferenceRecord) {
        this.conferenceRecord = conferenceRecord;
        return this;
    }

    /**
     * Whether to trim leading and trailing silence from your recorded conference
     * audio files. Can be: `trim-silence` or `do-not-trim` and defaults to
     * `trim-silence`..
     *
     * @param conferenceTrim Whether to trim leading and trailing silence from your
     *                       recorded conference audio files
     * @return this
     */
    public ParticipantCreator setConferenceTrim(final String conferenceTrim) {
        this.conferenceTrim = conferenceTrim;
        return this;
    }

    /**
     * The URL we should call using the `conference_status_callback_method` when the
     * conference events in `conference_status_callback_event` occur. Only the value
     * set by the first participant to join the conference is used. Subsequent
     * `conference_status_callback` values are ignored..
     *
     * @param conferenceStatusCallback The callback URL for conference events
     * @return this
     */
    public ParticipantCreator setConferenceStatusCallback(final URI conferenceStatusCallback) {
        this.conferenceStatusCallback = conferenceStatusCallback;
        return this;
    }

    /**
     * The URL we should call using the `conference_status_callback_method` when the
     * conference events in `conference_status_callback_event` occur. Only the value
     * set by the first participant to join the conference is used. Subsequent
     * `conference_status_callback` values are ignored..
     *
     * @param conferenceStatusCallback The callback URL for conference events
     * @return this
     */
    public ParticipantCreator setConferenceStatusCallback(final String conferenceStatusCallback) {
        return setConferenceStatusCallback(Promoter.uriFromString(conferenceStatusCallback));
    }

    /**
     * The HTTP method we should use to call `conference_status_callback`. Can be:
     * `GET` or `POST` and defaults to `POST`..
     *
     * @param conferenceStatusCallbackMethod HTTP method for requesting
     *                                       `conference_status_callback` URL
     * @return this
     */
    public ParticipantCreator setConferenceStatusCallbackMethod(final HttpMethod conferenceStatusCallbackMethod) {
        this.conferenceStatusCallbackMethod = conferenceStatusCallbackMethod;
        return this;
    }

    /**
     * The conference state changes that should generate a call to
     * `conference_status_callback`. Can be: `start`, `end`, `join`, `leave`,
     * `mute`, `hold`, and `speaker`. Separate multiple values with a space.
     * Defaults to `start end`..
     *
     * @param conferenceStatusCallbackEvent The conference state changes that
     *                                      should generate a call to
     *                                      `conference_status_callback`
     * @return this
     */
    public ParticipantCreator setConferenceStatusCallbackEvent(final List<String> conferenceStatusCallbackEvent) {
        this.conferenceStatusCallbackEvent = conferenceStatusCallbackEvent;
        return this;
    }

    /**
     * The conference state changes that should generate a call to
     * `conference_status_callback`. Can be: `start`, `end`, `join`, `leave`,
     * `mute`, `hold`, and `speaker`. Separate multiple values with a space.
     * Defaults to `start end`..
     *
     * @param conferenceStatusCallbackEvent The conference state changes that
     *                                      should generate a call to
     *                                      `conference_status_callback`
     * @return this
     */
    public ParticipantCreator setConferenceStatusCallbackEvent(final String conferenceStatusCallbackEvent) {
        return setConferenceStatusCallbackEvent(Promoter.listOfOne(conferenceStatusCallbackEvent));
    }

    /**
     * The recording channels for the final recording. Can be: `mono` or `dual` and
     * the default is `mono`..
     *
     * @param recordingChannels Specify `mono` or `dual` recording channels
     * @return this
     */
    public ParticipantCreator setRecordingChannels(final String recordingChannels) {
        this.recordingChannels = recordingChannels;
        return this;
    }

    /**
     * The URL that we should call using the `recording_status_callback_method` when
     * the recording status changes..
     *
     * @param recordingStatusCallback The URL that we should call using the
     *                                `recording_status_callback_method` when the
     *                                recording status changes
     * @return this
     */
    public ParticipantCreator setRecordingStatusCallback(final URI recordingStatusCallback) {
        this.recordingStatusCallback = recordingStatusCallback;
        return this;
    }

    /**
     * The URL that we should call using the `recording_status_callback_method` when
     * the recording status changes..
     *
     * @param recordingStatusCallback The URL that we should call using the
     *                                `recording_status_callback_method` when the
     *                                recording status changes
     * @return this
     */
    public ParticipantCreator setRecordingStatusCallback(final String recordingStatusCallback) {
        return setRecordingStatusCallback(Promoter.uriFromString(recordingStatusCallback));
    }

    /**
     * The HTTP method we should use when we call `recording_status_callback`. Can
     * be: `GET` or `POST` and defaults to `POST`..
     *
     * @param recordingStatusCallbackMethod The HTTP method we should use when we
     *                                      call `recording_status_callback`
     * @return this
     */
    public ParticipantCreator setRecordingStatusCallbackMethod(final HttpMethod recordingStatusCallbackMethod) {
        this.recordingStatusCallbackMethod = recordingStatusCallbackMethod;
        return this;
    }

    /**
     * The SIP username used for authentication..
     *
     * @param sipAuthUsername The SIP username used for authentication
     * @return this
     */
    public ParticipantCreator setSipAuthUsername(final String sipAuthUsername) {
        this.sipAuthUsername = sipAuthUsername;
        return this;
    }

    /**
     * The SIP password for authentication..
     *
     * @param sipAuthPassword The SIP password for authentication
     * @return this
     */
    public ParticipantCreator setSipAuthPassword(final String sipAuthPassword) {
        this.sipAuthPassword = sipAuthPassword;
        return this;
    }

    /**
     * The
     * [region](https://support.twilio.com/hc/en-us/articles/223132167-How-global-low-latency-routing-and-region-selection-work-for-conferences-and-Client-calls) where we should mix the recorded audio. Can be:`us1`, `ie1`, `de1`, `sg1`, `br1`, `au1`, or `jp1`..
     *
     * @param region The region where we should mix the conference audio
     * @return this
     */
    public ParticipantCreator setRegion(final String region) {
        this.region = region;
        return this;
    }

    /**
     * The URL we should call using the
     * `conference_recording_status_callback_method` when the conference recording
     * is available..
     *
     * @param conferenceRecordingStatusCallback The URL we should call using the
     *                                          `conference_recording_status_callback_method` when the conference recording is available
     * @return this
     */
    public ParticipantCreator setConferenceRecordingStatusCallback(final URI conferenceRecordingStatusCallback) {
        this.conferenceRecordingStatusCallback = conferenceRecordingStatusCallback;
        return this;
    }

    /**
     * The URL we should call using the
     * `conference_recording_status_callback_method` when the conference recording
     * is available..
     *
     * @param conferenceRecordingStatusCallback The URL we should call using the
     *                                          `conference_recording_status_callback_method` when the conference recording is available
     * @return this
     */
    public ParticipantCreator setConferenceRecordingStatusCallback(final String conferenceRecordingStatusCallback) {
        return setConferenceRecordingStatusCallback(Promoter.uriFromString(conferenceRecordingStatusCallback));
    }

    /**
     * The HTTP method we should use to call `conference_recording_status_callback`.
     * Can be: `GET` or `POST` and defaults to `POST`..
     *
     * @param conferenceRecordingStatusCallbackMethod The HTTP method we should use
     *                                                to call
     *                                                `conference_recording_status_callback`
     * @return this
     */
    public ParticipantCreator setConferenceRecordingStatusCallbackMethod(final HttpMethod conferenceRecordingStatusCallbackMethod) {
        this.conferenceRecordingStatusCallbackMethod = conferenceRecordingStatusCallbackMethod;
        return this;
    }

    /**
     * The recording state changes that should generate a call to
     * `recording_status_callback`. Can be: `in-progress`, `completed`, and
     * `failed`. Separate multiple values with a space. The default value is
     * `in-progress completed failed`..
     *
     * @param recordingStatusCallbackEvent The recording state changes that should
     *                                     generate a call to
     *                                     `recording_status_callback`
     * @return this
     */
    public ParticipantCreator setRecordingStatusCallbackEvent(final List<String> recordingStatusCallbackEvent) {
        this.recordingStatusCallbackEvent = recordingStatusCallbackEvent;
        return this;
    }

    /**
     * The recording state changes that should generate a call to
     * `recording_status_callback`. Can be: `in-progress`, `completed`, and
     * `failed`. Separate multiple values with a space. The default value is
     * `in-progress completed failed`..
     *
     * @param recordingStatusCallbackEvent The recording state changes that should
     *                                     generate a call to
     *                                     `recording_status_callback`
     * @return this
     */
    public ParticipantCreator setRecordingStatusCallbackEvent(final String recordingStatusCallbackEvent) {
        return setRecordingStatusCallbackEvent(Promoter.listOfOne(recordingStatusCallbackEvent));
    }

    /**
     * The conference recording state changes that generate a call to
     * `conference_recording_status_callback`. Can be: `in-progress`, `completed`,
     * and `failed`. Separate multiple values with a space. The default value is
     * `in-progress completed failed`..
     *
     * @param conferenceRecordingStatusCallbackEvent The conference recording state
     *                                               changes that should generate a
     *                                               call to
     *                                               `conference_recording_status_callback`
     * @return this
     */
    public ParticipantCreator setConferenceRecordingStatusCallbackEvent(final List<String> conferenceRecordingStatusCallbackEvent) {
        this.conferenceRecordingStatusCallbackEvent = conferenceRecordingStatusCallbackEvent;
        return this;
    }

    /**
     * The conference recording state changes that generate a call to
     * `conference_recording_status_callback`. Can be: `in-progress`, `completed`,
     * and `failed`. Separate multiple values with a space. The default value is
     * `in-progress completed failed`..
     *
     * @param conferenceRecordingStatusCallbackEvent The conference recording state
     *                                               changes that should generate a
     *                                               call to
     *                                               `conference_recording_status_callback`
     * @return this
     */
    public ParticipantCreator setConferenceRecordingStatusCallbackEvent(final String conferenceRecordingStatusCallbackEvent) {
        return setConferenceRecordingStatusCallbackEvent(Promoter.listOfOne(conferenceRecordingStatusCallbackEvent));
    }

    /**
     * Whether the participant is coaching another call. Can be: `true` or `false`.
     * If not present, defaults to `false` unless `call_sid_to_coach` is defined. If
     * `true`, `call_sid_to_coach` must be defined..
     *
     * @param coaching Indicates if the participant changed to coach
     * @return this
     */
    public ParticipantCreator setCoaching(final Boolean coaching) {
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
    public ParticipantCreator setCallSidToCoach(final String callSidToCoach) {
        this.callSidToCoach = callSidToCoach;
        return this;
    }

    /**
     * The SID of a BYOC (Bring Your Own Carrier) trunk to route this call with.
     * Note that `byoc` is only meaningful when `to` is a phone number; it will
     * otherwise be ignored. (Beta).
     *
     * @param byoc BYOC trunk SID (Beta)
     * @return this
     */
    public ParticipantCreator setByoc(final String byoc) {
        this.byoc = byoc;
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
        this.pathAccountSid = this.pathAccountSid == null ? client.getAccountSid() : this.pathAccountSid;
        Request request = new Request(
            HttpMethod.POST,
            Domains.API.toString(),
            "/2010-04-01/Accounts/" + this.pathAccountSid + "/Conferences/" + this.pathConferenceSid + "/Participants.json",
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
        if (from != null) {
            request.addPostParam("From", from.toString());
        }

        if (to != null) {
            request.addPostParam("To", to.toString());
        }

        if (statusCallback != null) {
            request.addPostParam("StatusCallback", statusCallback.toString());
        }

        if (statusCallbackMethod != null) {
            request.addPostParam("StatusCallbackMethod", statusCallbackMethod.toString());
        }

        if (statusCallbackEvent != null) {
            for (String prop : statusCallbackEvent) {
                request.addPostParam("StatusCallbackEvent", prop);
            }
        }

        if (timeout != null) {
            request.addPostParam("Timeout", timeout.toString());
        }

        if (record != null) {
            request.addPostParam("Record", record.toString());
        }

        if (muted != null) {
            request.addPostParam("Muted", muted.toString());
        }

        if (beep != null) {
            request.addPostParam("Beep", beep);
        }

        if (startConferenceOnEnter != null) {
            request.addPostParam("StartConferenceOnEnter", startConferenceOnEnter.toString());
        }

        if (endConferenceOnExit != null) {
            request.addPostParam("EndConferenceOnExit", endConferenceOnExit.toString());
        }

        if (waitUrl != null) {
            request.addPostParam("WaitUrl", waitUrl.toString());
        }

        if (waitMethod != null) {
            request.addPostParam("WaitMethod", waitMethod.toString());
        }

        if (earlyMedia != null) {
            request.addPostParam("EarlyMedia", earlyMedia.toString());
        }

        if (maxParticipants != null) {
            request.addPostParam("MaxParticipants", maxParticipants.toString());
        }

        if (conferenceRecord != null) {
            request.addPostParam("ConferenceRecord", conferenceRecord);
        }

        if (conferenceTrim != null) {
            request.addPostParam("ConferenceTrim", conferenceTrim);
        }

        if (conferenceStatusCallback != null) {
            request.addPostParam("ConferenceStatusCallback", conferenceStatusCallback.toString());
        }

        if (conferenceStatusCallbackMethod != null) {
            request.addPostParam("ConferenceStatusCallbackMethod", conferenceStatusCallbackMethod.toString());
        }

        if (conferenceStatusCallbackEvent != null) {
            for (String prop : conferenceStatusCallbackEvent) {
                request.addPostParam("ConferenceStatusCallbackEvent", prop);
            }
        }

        if (recordingChannels != null) {
            request.addPostParam("RecordingChannels", recordingChannels);
        }

        if (recordingStatusCallback != null) {
            request.addPostParam("RecordingStatusCallback", recordingStatusCallback.toString());
        }

        if (recordingStatusCallbackMethod != null) {
            request.addPostParam("RecordingStatusCallbackMethod", recordingStatusCallbackMethod.toString());
        }

        if (sipAuthUsername != null) {
            request.addPostParam("SipAuthUsername", sipAuthUsername);
        }

        if (sipAuthPassword != null) {
            request.addPostParam("SipAuthPassword", sipAuthPassword);
        }

        if (region != null) {
            request.addPostParam("Region", region);
        }

        if (conferenceRecordingStatusCallback != null) {
            request.addPostParam("ConferenceRecordingStatusCallback", conferenceRecordingStatusCallback.toString());
        }

        if (conferenceRecordingStatusCallbackMethod != null) {
            request.addPostParam("ConferenceRecordingStatusCallbackMethod", conferenceRecordingStatusCallbackMethod.toString());
        }

        if (recordingStatusCallbackEvent != null) {
            for (String prop : recordingStatusCallbackEvent) {
                request.addPostParam("RecordingStatusCallbackEvent", prop);
            }
        }

        if (conferenceRecordingStatusCallbackEvent != null) {
            for (String prop : conferenceRecordingStatusCallbackEvent) {
                request.addPostParam("ConferenceRecordingStatusCallbackEvent", prop);
            }
        }

        if (coaching != null) {
            request.addPostParam("Coaching", coaching.toString());
        }

        if (callSidToCoach != null) {
            request.addPostParam("CallSidToCoach", callSidToCoach);
        }

        if (byoc != null) {
            request.addPostParam("Byoc", byoc);
        }
    }
}