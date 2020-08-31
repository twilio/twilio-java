/**
 * This code was generated by
 * \ / _    _  _|   _  _
 *  | (_)\/(_)(_|\/| |(/_  v1.0.0
 *       /       /
 */

package com.twilio.rest.video.v1.room.participant;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.twilio.base.Resource;
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
import lombok.ToString;

import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.time.ZonedDateTime;
import java.util.Map;
import java.util.Objects;

@JsonIgnoreProperties(ignoreUnknown = true)
@ToString
public class SubscribedTrack extends Resource {
    private static final long serialVersionUID = 63596798347062L;

    public enum Kind {
        AUDIO("audio"),
        VIDEO("video"),
        DATA("data");

        private final String value;

        private Kind(final String value) {
            this.value = value;
        }

        public String toString() {
            return value;
        }

        /**
         * Generate a Kind from a string.
         * @param value string value
         * @return generated Kind
         */
        @JsonCreator
        public static Kind forValue(final String value) {
            return Promoter.enumFromString(value, Kind.values());
        }
    }

    /**
     * Create a SubscribedTrackFetcher to execute fetch.
     *
     * @param pathRoomSid The SID of the Room where the Track resource to fetch is
     *                    subscribed
     * @param pathParticipantSid The SID of the participant that subscribes to the
     *                           Track resource to fetch
     * @param pathSid The SID that identifies the resource to fetch
     * @return SubscribedTrackFetcher capable of executing the fetch
     */
    public static SubscribedTrackFetcher fetcher(final String pathRoomSid,
                                                 final String pathParticipantSid,
                                                 final String pathSid) {
        return new SubscribedTrackFetcher(pathRoomSid, pathParticipantSid, pathSid);
    }

    /**
     * Create a SubscribedTrackReader to execute read.
     *
     * @param pathRoomSid The SID of the Room resource with the Track resources to
     *                    read
     * @param pathParticipantSid The SID of the participant that subscribes to the
     *                           Track resources to read
     * @return SubscribedTrackReader capable of executing the read
     */
    public static SubscribedTrackReader reader(final String pathRoomSid,
                                               final String pathParticipantSid) {
        return new SubscribedTrackReader(pathRoomSid, pathParticipantSid);
    }

    /**
     * Converts a JSON String into a SubscribedTrack object using the provided
     * ObjectMapper.
     *
     * @param json Raw JSON String
     * @param objectMapper Jackson ObjectMapper
     * @return SubscribedTrack object represented by the provided JSON
     */
    public static SubscribedTrack fromJson(final String json, final ObjectMapper objectMapper) {
        // Convert all checked exceptions to Runtime
        try {
            return objectMapper.readValue(json, SubscribedTrack.class);
        } catch (final JsonMappingException | JsonParseException e) {
            throw new ApiException(e.getMessage(), e);
        } catch (final IOException e) {
            throw new ApiConnectionException(e.getMessage(), e);
        }
    }

    /**
     * Converts a JSON InputStream into a SubscribedTrack object using the provided
     * ObjectMapper.
     *
     * @param json Raw JSON InputStream
     * @param objectMapper Jackson ObjectMapper
     * @return SubscribedTrack object represented by the provided JSON
     */
    public static SubscribedTrack fromJson(final InputStream json, final ObjectMapper objectMapper) {
        // Convert all checked exceptions to Runtime
        try {
            return objectMapper.readValue(json, SubscribedTrack.class);
        } catch (final JsonMappingException | JsonParseException e) {
            throw new ApiException(e.getMessage(), e);
        } catch (final IOException e) {
            throw new ApiConnectionException(e.getMessage(), e);
        }
    }

    private final String sid;
    private final String participantSid;
    private final String publisherSid;
    private final String roomSid;
    private final String name;
    private final ZonedDateTime dateCreated;
    private final ZonedDateTime dateUpdated;
    private final Boolean enabled;
    private final SubscribedTrack.Kind kind;
    private final URI url;

    @JsonCreator
    private SubscribedTrack(@JsonProperty("sid")
                            final String sid,
                            @JsonProperty("participant_sid")
                            final String participantSid,
                            @JsonProperty("publisher_sid")
                            final String publisherSid,
                            @JsonProperty("room_sid")
                            final String roomSid,
                            @JsonProperty("name")
                            final String name,
                            @JsonProperty("date_created")
                            final String dateCreated,
                            @JsonProperty("date_updated")
                            final String dateUpdated,
                            @JsonProperty("enabled")
                            final Boolean enabled,
                            @JsonProperty("kind")
                            final SubscribedTrack.Kind kind,
                            @JsonProperty("url")
                            final URI url) {
        this.sid = sid;
        this.participantSid = participantSid;
        this.publisherSid = publisherSid;
        this.roomSid = roomSid;
        this.name = name;
        this.dateCreated = DateConverter.iso8601DateTimeFromString(dateCreated);
        this.dateUpdated = DateConverter.iso8601DateTimeFromString(dateUpdated);
        this.enabled = enabled;
        this.kind = kind;
        this.url = url;
    }

    /**
     * Returns The unique string that identifies the resource.
     *
     * @return The unique string that identifies the resource
     */
    public final String getSid() {
        return this.sid;
    }

    /**
     * Returns The SID of the participant that subscribes to the track.
     *
     * @return The SID of the participant that subscribes to the track
     */
    public final String getParticipantSid() {
        return this.participantSid;
    }

    /**
     * Returns The SID of the participant that publishes the track.
     *
     * @return The SID of the participant that publishes the track
     */
    public final String getPublisherSid() {
        return this.publisherSid;
    }

    /**
     * Returns The SID of the room where the track is published.
     *
     * @return The SID of the room where the track is published
     */
    public final String getRoomSid() {
        return this.roomSid;
    }

    /**
     * Returns The track name.
     *
     * @return The track name
     */
    public final String getName() {
        return this.name;
    }

    /**
     * Returns The ISO 8601 date and time in GMT when the resource was created.
     *
     * @return The ISO 8601 date and time in GMT when the resource was created
     */
    public final ZonedDateTime getDateCreated() {
        return this.dateCreated;
    }

    /**
     * Returns The ISO 8601 date and time in GMT when the resource was last updated.
     *
     * @return The ISO 8601 date and time in GMT when the resource was last updated
     */
    public final ZonedDateTime getDateUpdated() {
        return this.dateUpdated;
    }

    /**
     * Returns Whether the track is enabled.
     *
     * @return Whether the track is enabled
     */
    public final Boolean getEnabled() {
        return this.enabled;
    }

    /**
     * Returns The track type.
     *
     * @return The track type
     */
    public final SubscribedTrack.Kind getKind() {
        return this.kind;
    }

    /**
     * Returns The absolute URL of the resource.
     *
     * @return The absolute URL of the resource
     */
    public final URI getUrl() {
        return this.url;
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }

        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        SubscribedTrack other = (SubscribedTrack) o;

        return Objects.equals(sid, other.sid) &&
               Objects.equals(participantSid, other.participantSid) &&
               Objects.equals(publisherSid, other.publisherSid) &&
               Objects.equals(roomSid, other.roomSid) &&
               Objects.equals(name, other.name) &&
               Objects.equals(dateCreated, other.dateCreated) &&
               Objects.equals(dateUpdated, other.dateUpdated) &&
               Objects.equals(enabled, other.enabled) &&
               Objects.equals(kind, other.kind) &&
               Objects.equals(url, other.url);
    }

    @Override
    public int hashCode() {
        return Objects.hash(sid,
                            participantSid,
                            publisherSid,
                            roomSid,
                            name,
                            dateCreated,
                            dateUpdated,
                            enabled,
                            kind,
                            url);
    }
}