package com.twilio.sdk.resources;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.base.MoreObjects;
import com.twilio.sdk.converters.MarshalConverter;
import com.twilio.sdk.deleters.ParticipantDeleter;
import com.twilio.sdk.exceptions.ApiConnectionException;
import com.twilio.sdk.exceptions.ApiException;
import com.twilio.sdk.fetchers.ParticipantFetcher;
import com.twilio.sdk.readers.ParticipantReader;
import com.twilio.sdk.updaters.ParticipantUpdater;
import org.joda.time.DateTime;

import java.io.IOException;
import java.io.InputStream;
import java.util.Objects;

public class Participant extends Resource {

    private static final long serialVersionUID = -5732541214023360255L;

    private final String callSid;
    private final String conferenceSid;
    private final DateTime dateCreated;
    private final DateTime dateUpdated;
    private final String accountSid;
    private final Boolean muted;
    private final Boolean startConferenceOnEnter;
    private final Boolean endConferenceOnExit;
    private final String uri;

    @JsonCreator
    public Participant(@JsonProperty("call_sid") final String callSid,
                       @JsonProperty("conference_sid") final String conferenceSid,
                       @JsonProperty("date_created") final String dateCreated,
                       @JsonProperty("date_updated") final String dateUpdated,
                       @JsonProperty("account_sid") final String accountSid,
                       @JsonProperty("muted") final Boolean muted,
                       @JsonProperty("start_conference_on_enter") final Boolean startConferenceOnEnter,
                       @JsonProperty("end_conference_on_exit") final Boolean endConferenceOnExit,
                       @JsonProperty("uri") final String uri) {
        this.callSid = callSid;
        this.conferenceSid = conferenceSid;
        this.dateCreated = MarshalConverter.dateTimeFromString(dateCreated);
        this.dateUpdated = MarshalConverter.dateTimeFromString(dateUpdated);
        this.accountSid = accountSid;
        this.muted = muted;
        this.startConferenceOnEnter = startConferenceOnEnter;
        this.endConferenceOnExit = endConferenceOnExit;
        this.uri = uri;
    }

    public static ParticipantReader list(final String conferenceSid) {
        return new ParticipantReader(conferenceSid);
    }

    public static ParticipantReader list(final Conference target) {
        return new ParticipantReader(target);
    }

    public static ParticipantFetcher fetch(final String conferenceSid, final String sid) {
        return new ParticipantFetcher(conferenceSid, sid);
    }

    public static ParticipantFetcher fetch(final Conference targetConference, final String sid) {
        return new ParticipantFetcher(targetConference, sid);
    }

    public static ParticipantUpdater update(final String conferenceSid, final String sid, final Boolean muted) {
        return new ParticipantUpdater(conferenceSid, sid, muted);
    }

    public static ParticipantUpdater update(final Conference targetConference, final String sid, final Boolean muted) {
        return new ParticipantUpdater(targetConference, sid, muted);
    }

    public static ParticipantUpdater update(final Participant target, final Boolean muted) {
        return new ParticipantUpdater(target, muted);
    }

    public static ParticipantUpdater mute(final String conferenceSid, final String sid) {
        return new ParticipantUpdater(conferenceSid, sid, true);
    }

    public static ParticipantUpdater mute(final Conference targetConference, final String sid) {
        return new ParticipantUpdater(targetConference, sid, true);
    }

    public static ParticipantUpdater mute(final Participant target) {
        return new ParticipantUpdater(target, true);
    }

    public static ParticipantUpdater unmute(final String conferenceSid, final String sid) {
        return new ParticipantUpdater(conferenceSid, sid, false);
    }

    public static ParticipantUpdater unmute(final Conference targetConference, final String sid) {
        return new ParticipantUpdater(targetConference, sid, false);
    }

    public static ParticipantUpdater unmute(final Participant target) {
        return new ParticipantUpdater(target, false);
    }

    public static ParticipantDeleter delete(final String conferenceSid, final String sid) {
        return new ParticipantDeleter(conferenceSid, sid);
    }

    public static ParticipantDeleter delete(final Conference targetConference, final String sid) {
        return new ParticipantDeleter(targetConference, sid);
    }

    public static ParticipantDeleter delete(final Participant target) {
        return new ParticipantDeleter(target);
    }

    public static ParticipantDeleter kick(final String conferenceSid, final String sid) {
        return new ParticipantDeleter(conferenceSid, sid);
    }

    public static ParticipantDeleter kick(final Conference targetConference, final String sid) {
        return new ParticipantDeleter(targetConference, sid);
    }

    public static ParticipantDeleter kick(final Participant target) {
        return new ParticipantDeleter(target);
    }


    public String getCallSid() {
        return callSid;
    }

    public String getConferenceSid() {
        return conferenceSid;
    }

    public DateTime getDateCreated() {
        return dateCreated;
    }

    public DateTime getDateUpdated() {
        return dateUpdated;
    }

    public String getAccountSid() {
        return accountSid;
    }

    public Boolean getMuted() {
        return muted;
    }

    public Boolean getStartConferenceOnEnter() {
        return startConferenceOnEnter;
    }

    public Boolean getEndConferenceOnExit() {
        return endConferenceOnExit;
    }

    public String getUri() {
        return uri;
    }

    public static Participant fromJson(final String json, final ObjectMapper objectMapper) {
        try {
            return objectMapper.readValue(json, Participant.class);
        } catch (final JsonMappingException | JsonParseException e) {
            throw new ApiException(e.getMessage(), e);
        } catch (final IOException e) {
            throw new ApiConnectionException(e.getMessage(), e);
        }
    }

    public static Participant fromJson(final InputStream json, final ObjectMapper objectMapper) {
        try {
            return objectMapper.readValue(json, Participant.class);
        } catch (final JsonMappingException | JsonParseException e) {
            throw new ApiException(e.getMessage(), e);
        } catch (final IOException e) {
            throw new ApiConnectionException(e.getMessage(), e);
        }
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        Participant self = (Participant) o;

        return (Objects.equals(callSid, self.callSid) &&
                Objects.equals(conferenceSid, self.conferenceSid) &&
                Objects.equals(dateCreated, self.dateCreated) &&
                Objects.equals(dateUpdated, self.dateUpdated) &&
                Objects.equals(accountSid, self.accountSid) &&
                Objects.equals(muted, self.muted) &&
                Objects.equals(startConferenceOnEnter, self.startConferenceOnEnter) &&
                Objects.equals(endConferenceOnExit, self.endConferenceOnExit) &&
                Objects.equals(uri, self.uri)
        );
    }

    @Override
    public int hashCode() {
        return Objects.hash(callSid, conferenceSid, dateCreated, dateUpdated, accountSid, muted, startConferenceOnEnter, endConferenceOnExit, uri);
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                .add("callSid", callSid)
                .add("conferenceSid", conferenceSid)
                .add("dateCreated", dateCreated)
                .add("dateUpdated", dateUpdated)
                .add("accountSid", accountSid)
                .add("muted", muted)
                .add("startConferenceOnEnter", startConferenceOnEnter)
                .add("endConferenceOnExit", endConferenceOnExit)
                .add("uri", uri)
                .toString();
    }
}
