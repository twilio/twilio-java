package com.twilio.sdk.resources.api.v2010.account.conference;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.base.MoreObjects;
import com.twilio.sdk.clients.TwilioRestClient;
import com.twilio.sdk.converters.MarshalConverter;
import com.twilio.sdk.deleters.api.v2010.account.conference.ParticipantDeleter;
import com.twilio.sdk.exceptions.ApiConnectionException;
import com.twilio.sdk.exceptions.ApiException;
import com.twilio.sdk.fetchers.api.v2010.account.conference.ParticipantFetcher;
import com.twilio.sdk.http.HttpMethod;
import com.twilio.sdk.http.Request;
import com.twilio.sdk.http.Response;
import com.twilio.sdk.readers.api.v2010.account.conference.ParticipantReader;
import com.twilio.sdk.resources.RestException;
import com.twilio.sdk.resources.SidResource;
import com.twilio.sdk.updaters.api.v2010.account.conference.ParticipantUpdater;
import org.joda.time.DateTime;

import java.io.IOException;
import java.io.InputStream;
import java.util.Map;
import java.util.Objects;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Participant extends SidResource {
    private static final long serialVersionUID = 238358972354024L;

    /**
     * Fetch an instance of a participant
     * 
     * @param accountSid The account_sid
     * @param conferenceSid The string that uniquely identifies this conference
     * @param callSid The call_sid
     * @return ParticipantFetcher capable of executing the fetch
     */
    public static ParticipantFetcher fetch(final String accountSid, final String conferenceSid, final String callSid) {
        return new ParticipantFetcher(accountSid, conferenceSid, callSid);
    }

    /**
     * Update the properties of this participant
     * 
     * @param accountSid The account_sid
     * @param conferenceSid The string that uniquely identifies this conference
     * @param callSid The call_sid
     * @param muted Indicates if the participant should be muted
     * @return ParticipantUpdater capable of executing the update
     */
    public static ParticipantUpdater update(final String accountSid, final String conferenceSid, final String callSid, final Boolean muted) {
        return new ParticipantUpdater(accountSid, conferenceSid, callSid, muted);
    }

    /**
     * Kick a participant from a given conference
     * 
     * @param accountSid The account_sid
     * @param conferenceSid The string that uniquely identifies this conference
     * @param callSid The call_sid
     * @return ParticipantDeleter capable of executing the delete
     */
    public static ParticipantDeleter delete(final String accountSid, final String conferenceSid, final String callSid) {
        return new ParticipantDeleter(accountSid, conferenceSid, callSid);
    }

    /**
     * Retrieve a list of participants belonging to the account used to make the
     * request
     * 
     * @param accountSid The account_sid
     * @param conferenceSid The string that uniquely identifies this conference
     * @return ParticipantReader capable of executing the read
     */
    public static ParticipantReader read(final String accountSid, final String conferenceSid) {
        return new ParticipantReader(accountSid, conferenceSid);
    }

    /**
     * Converts a JSON String into a Participant object using the provided
     * ObjectMapper
     * 
     * @param json Raw JSON String
     * @param objectMapper Jackson ObjectMapper
     * @return Participant object represented by the provided JSON
     */
    public static Participant fromJson(final String json, final ObjectMapper objectMapper) {
        // Convert all checked exceptions to Runtime
        try {
            return objectMapper.readValue(json, Participant.class);
        } catch (final JsonMappingException | JsonParseException e) {
            throw new ApiException(e.getMessage(), e);
        } catch (final IOException e) {
            throw new ApiConnectionException(e.getMessage(), e);
        }
    }

    /**
     * Converts a JSON InputStream into a Participant object using the provided
     * ObjectMapper
     * 
     * @param json Raw JSON InputStream
     * @param objectMapper Jackson ObjectMapper
     * @return Participant object represented by the provided JSON
     */
    public static Participant fromJson(final InputStream json, final ObjectMapper objectMapper) {
        // Convert all checked exceptions to Runtime
        try {
            return objectMapper.readValue(json, Participant.class);
        } catch (final JsonMappingException | JsonParseException e) {
            throw new ApiException(e.getMessage(), e);
        } catch (final IOException e) {
            throw new ApiConnectionException(e.getMessage(), e);
        }
    }

    private final String accountSid;
    private final String callSid;
    private final String conferenceSid;
    private final DateTime dateCreated;
    private final DateTime dateUpdated;
    private final Boolean endConferenceOnExit;
    private final Boolean muted;
    private final Boolean startConferenceOnEnter;
    private final String uri;

    @JsonCreator
    private Participant(@JsonProperty("account_sid")
                        final String accountSid, 
                        @JsonProperty("call_sid")
                        final String callSid, 
                        @JsonProperty("conference_sid")
                        final String conferenceSid, 
                        @JsonProperty("date_created")
                        final String dateCreated, 
                        @JsonProperty("date_updated")
                        final String dateUpdated, 
                        @JsonProperty("end_conference_on_exit")
                        final Boolean endConferenceOnExit, 
                        @JsonProperty("muted")
                        final Boolean muted, 
                        @JsonProperty("start_conference_on_enter")
                        final Boolean startConferenceOnEnter, 
                        @JsonProperty("uri")
                        final String uri) {
        this.accountSid = accountSid;
        this.callSid = callSid;
        this.conferenceSid = conferenceSid;
        this.dateCreated = MarshalConverter.dateTimeFromString(dateCreated);
        this.dateUpdated = MarshalConverter.dateTimeFromString(dateUpdated);
        this.endConferenceOnExit = endConferenceOnExit;
        this.muted = muted;
        this.startConferenceOnEnter = startConferenceOnEnter;
        this.uri = uri;
    }

    /**
     * @return A string that uniquely identifies this call
     */
    public final String getSid() {
        return this.getCallSid();
    }

    /**
     * @return The unique sid that identifies this account
     */
    public final String getAccountSid() {
        return this.accountSid;
    }

    /**
     * @return A string that uniquely identifies this call
     */
    public final String getCallSid() {
        return this.callSid;
    }

    /**
     * @return A string that uniquely identifies this conference
     */
    public final String getConferenceSid() {
        return this.conferenceSid;
    }

    /**
     * @return The date this resource was created
     */
    public final DateTime getDateCreated() {
        return this.dateCreated;
    }

    /**
     * @return The date this resource was last updated
     */
    public final DateTime getDateUpdated() {
        return this.dateUpdated;
    }

    /**
     * @return Indicates if the endConferenceOnExit was set
     */
    public final Boolean getEndConferenceOnExit() {
        return this.endConferenceOnExit;
    }

    /**
     * @return Indicates if the participant is muted
     */
    public final Boolean getMuted() {
        return this.muted;
    }

    /**
     * @return Indicates if the startConferenceOnEnter attribute was set
     */
    public final Boolean getStartConferenceOnEnter() {
        return this.startConferenceOnEnter;
    }

    /**
     * @return The URI for this resource
     */
    public final String getUri() {
        return this.uri;
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        
        Participant other = (Participant) o;
        
        return Objects.equals(accountSid, other.accountSid) && 
               Objects.equals(callSid, other.callSid) && 
               Objects.equals(conferenceSid, other.conferenceSid) && 
               Objects.equals(dateCreated, other.dateCreated) && 
               Objects.equals(dateUpdated, other.dateUpdated) && 
               Objects.equals(endConferenceOnExit, other.endConferenceOnExit) && 
               Objects.equals(muted, other.muted) && 
               Objects.equals(startConferenceOnEnter, other.startConferenceOnEnter) && 
               Objects.equals(uri, other.uri);
    }

    @Override
    public int hashCode() {
        return Objects.hash(accountSid,
                            callSid,
                            conferenceSid,
                            dateCreated,
                            dateUpdated,
                            endConferenceOnExit,
                            muted,
                            startConferenceOnEnter,
                            uri);
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                          .add("accountSid", accountSid)
                          .add("callSid", callSid)
                          .add("conferenceSid", conferenceSid)
                          .add("dateCreated", dateCreated)
                          .add("dateUpdated", dateUpdated)
                          .add("endConferenceOnExit", endConferenceOnExit)
                          .add("muted", muted)
                          .add("startConferenceOnEnter", startConferenceOnEnter)
                          .add("uri", uri)
                          .toString();
    }
}