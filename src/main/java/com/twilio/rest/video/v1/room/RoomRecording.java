/**
 * This code was generated by
 * \ / _    _  _|   _  _
 *  | (_)\/(_)(_|\/| |(/_  v1.0.0
 *       /       /
 */

package com.twilio.rest.video.v1.room;

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
import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.time.ZonedDateTime;
import java.util.Map;
import java.util.Objects;

@JsonIgnoreProperties(ignoreUnknown = true)
public class RoomRecording extends Resource {
    private static final long serialVersionUID = 32348280451006L;

  public String toString() {
    return "RoomRecording(accountSid=" + this.getAccountSid() + ", status=" + this.getStatus() + ", dateCreated=" + this.getDateCreated() + ", sid=" + this.getSid() + ", sourceSid=" + this.getSourceSid() + ", size=" + this.getSize() + ", url=" + this.getUrl() + ", type=" + this.getType() + ", duration=" + this.getDuration() + ", containerFormat=" + this.getContainerFormat() + ", codec=" + this.getCodec() + ", groupingSids=" + this.getGroupingSids() + ", trackName=" + this.getTrackName() + ", offset=" + this.getOffset() + ", roomSid=" + this.getRoomSid() + ", links=" + this.getLinks() + ")";
  }

  public enum Status {
        PROCESSING("processing"),
        COMPLETED("completed"),
        DELETED("deleted"),
        FAILED("failed");

        private final String value;

        private Status(final String value) {
            this.value = value;
        }

        public String toString() {
            return value;
        }

        /**
         * Generate a Status from a string.
         * @param value string value
         * @return generated Status
         */
        @JsonCreator
        public static Status forValue(final String value) {
            return Promoter.enumFromString(value, Status.values());
        }
    }

    public enum Type {
        AUDIO("audio"),
        VIDEO("video"),
        DATA("data");

        private final String value;

        private Type(final String value) {
            this.value = value;
        }

        public String toString() {
            return value;
        }

        /**
         * Generate a Type from a string.
         * @param value string value
         * @return generated Type
         */
        @JsonCreator
        public static Type forValue(final String value) {
            return Promoter.enumFromString(value, Type.values());
        }
    }

    public enum Format {
        MKA("mka"),
        MKV("mkv");

        private final String value;

        private Format(final String value) {
            this.value = value;
        }

        public String toString() {
            return value;
        }

        /**
         * Generate a Format from a string.
         * @param value string value
         * @return generated Format
         */
        @JsonCreator
        public static Format forValue(final String value) {
            return Promoter.enumFromString(value, Format.values());
        }
    }

    public enum Codec {
        VP8("VP8"),
        H264("H264"),
        OPUS("OPUS"),
        PCMU("PCMU");

        private final String value;

        private Codec(final String value) {
            this.value = value;
        }

        public String toString() {
            return value;
        }

        /**
         * Generate a Codec from a string.
         * @param value string value
         * @return generated Codec
         */
        @JsonCreator
        public static Codec forValue(final String value) {
            return Promoter.enumFromString(value, Codec.values());
        }
    }

    /**
     * Create a RoomRecordingFetcher to execute fetch.
     *
     * @param pathRoomSid The SID of the Room resource with the recording to fetch
     * @param pathSid The SID that identifies the resource to fetch
     * @return RoomRecordingFetcher capable of executing the fetch
     */
    public static RoomRecordingFetcher fetcher(final String pathRoomSid,
                                               final String pathSid) {
        return new RoomRecordingFetcher(pathRoomSid, pathSid);
    }

    /**
     * Create a RoomRecordingReader to execute read.
     *
     * @param pathRoomSid The SID of the room with the RoomRecording resources to
     *                    read
     * @return RoomRecordingReader capable of executing the read
     */
    public static RoomRecordingReader reader(final String pathRoomSid) {
        return new RoomRecordingReader(pathRoomSid);
    }

    /**
     * Create a RoomRecordingDeleter to execute delete.
     *
     * @param pathRoomSid The SID of the room with the RoomRecording resource to
     *                    delete
     * @param pathSid The SID that identifies the resource to delete
     * @return RoomRecordingDeleter capable of executing the delete
     */
    public static RoomRecordingDeleter deleter(final String pathRoomSid,
                                               final String pathSid) {
        return new RoomRecordingDeleter(pathRoomSid, pathSid);
    }

    /**
     * Converts a JSON String into a RoomRecording object using the provided
     * ObjectMapper.
     *
     * @param json Raw JSON String
     * @param objectMapper Jackson ObjectMapper
     * @return RoomRecording object represented by the provided JSON
     */
    public static RoomRecording fromJson(final String json, final ObjectMapper objectMapper) {
        // Convert all checked exceptions to Runtime
        try {
            return objectMapper.readValue(json, RoomRecording.class);
        } catch (final JsonMappingException | JsonParseException e) {
            throw new ApiException(e.getMessage(), e);
        } catch (final IOException e) {
            throw new ApiConnectionException(e.getMessage(), e);
        }
    }

    /**
     * Converts a JSON InputStream into a RoomRecording object using the provided
     * ObjectMapper.
     *
     * @param json Raw JSON InputStream
     * @param objectMapper Jackson ObjectMapper
     * @return RoomRecording object represented by the provided JSON
     */
    public static RoomRecording fromJson(final InputStream json, final ObjectMapper objectMapper) {
        // Convert all checked exceptions to Runtime
        try {
            return objectMapper.readValue(json, RoomRecording.class);
        } catch (final JsonMappingException | JsonParseException e) {
            throw new ApiException(e.getMessage(), e);
        } catch (final IOException e) {
            throw new ApiConnectionException(e.getMessage(), e);
        }
    }

    private final String accountSid;
    private final RoomRecording.Status status;
    private final ZonedDateTime dateCreated;
    private final String sid;
    private final String sourceSid;
    private final Long size;
    private final URI url;
    private final RoomRecording.Type type;
    private final Integer duration;
    private final RoomRecording.Format containerFormat;
    private final RoomRecording.Codec codec;
    private final Map<String, Object> groupingSids;
    private final String trackName;
    private final Long offset;
    private final String roomSid;
    private final Map<String, String> links;

    @JsonCreator
    private RoomRecording(@JsonProperty("account_sid")
                          final String accountSid,
                          @JsonProperty("status")
                          final RoomRecording.Status status,
                          @JsonProperty("date_created")
                          final String dateCreated,
                          @JsonProperty("sid")
                          final String sid,
                          @JsonProperty("source_sid")
                          final String sourceSid,
                          @JsonProperty("size")
                          final Long size,
                          @JsonProperty("url")
                          final URI url,
                          @JsonProperty("type")
                          final RoomRecording.Type type,
                          @JsonProperty("duration")
                          final Integer duration,
                          @JsonProperty("container_format")
                          final RoomRecording.Format containerFormat,
                          @JsonProperty("codec")
                          final RoomRecording.Codec codec,
                          @JsonProperty("grouping_sids")
                          final Map<String, Object> groupingSids,
                          @JsonProperty("track_name")
                          final String trackName,
                          @JsonProperty("offset")
                          final Long offset,
                          @JsonProperty("room_sid")
                          final String roomSid,
                          @JsonProperty("links")
                          final Map<String, String> links) {
        this.accountSid = accountSid;
        this.status = status;
        this.dateCreated = DateConverter.iso8601DateTimeFromString(dateCreated);
        this.sid = sid;
        this.sourceSid = sourceSid;
        this.size = size;
        this.url = url;
        this.type = type;
        this.duration = duration;
        this.containerFormat = containerFormat;
        this.codec = codec;
        this.groupingSids = groupingSids;
        this.trackName = trackName;
        this.offset = offset;
        this.roomSid = roomSid;
        this.links = links;
    }

    /**
     * Returns The SID of the Account that created the resource.
     *
     * @return The SID of the Account that created the resource
     */
    public final String getAccountSid() {
        return this.accountSid;
    }

    /**
     * Returns The status of the recording.
     *
     * @return The status of the recording
     */
    public final RoomRecording.Status getStatus() {
        return this.status;
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
     * Returns The unique string that identifies the resource.
     *
     * @return The unique string that identifies the resource
     */
    public final String getSid() {
        return this.sid;
    }

    /**
     * Returns The SID of the recording source.
     *
     * @return The SID of the recording source
     */
    public final String getSourceSid() {
        return this.sourceSid;
    }

    /**
     * Returns The size of the recorded track in bytes.
     *
     * @return The size of the recorded track in bytes
     */
    public final Long getSize() {
        return this.size;
    }

    /**
     * Returns The absolute URL of the resource.
     *
     * @return The absolute URL of the resource
     */
    public final URI getUrl() {
        return this.url;
    }

    /**
     * Returns The recording's media type.
     *
     * @return The recording's media type
     */
    public final RoomRecording.Type getType() {
        return this.type;
    }

    /**
     * Returns The duration of the recording in seconds.
     *
     * @return The duration of the recording in seconds
     */
    public final Integer getDuration() {
        return this.duration;
    }

    /**
     * Returns The file format for the recording.
     *
     * @return The file format for the recording
     */
    public final RoomRecording.Format getContainerFormat() {
        return this.containerFormat;
    }

    /**
     * Returns The codec used for the recording.
     *
     * @return The codec used for the recording
     */
    public final RoomRecording.Codec getCodec() {
        return this.codec;
    }

    /**
     * Returns A list of SIDs related to the Recording.
     *
     * @return A list of SIDs related to the Recording
     */
    public final Map<String, Object> getGroupingSids() {
        return this.groupingSids;
    }

    /**
     * Returns The name that was given to the source track of the recording.
     *
     * @return The name that was given to the source track of the recording
     */
    public final String getTrackName() {
        return this.trackName;
    }

    /**
     * Returns The number of milliseconds between a point in time that is common to
     * all rooms in a group and when the source room of the recording started.
     *
     * @return The number of milliseconds between a point in time that is common to
     *         all rooms in a group and when the source room of the recording
     *         started
     */
    public final Long getOffset() {
        return this.offset;
    }

    /**
     * Returns The SID of the Room resource the recording is associated with.
     *
     * @return The SID of the Room resource the recording is associated with
     */
    public final String getRoomSid() {
        return this.roomSid;
    }

    /**
     * Returns The URLs of related resources.
     *
     * @return The URLs of related resources
     */
    public final Map<String, String> getLinks() {
        return this.links;
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }

        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        RoomRecording other = (RoomRecording) o;

        return Objects.equals(accountSid, other.accountSid) &&
               Objects.equals(status, other.status) &&
               Objects.equals(dateCreated, other.dateCreated) &&
               Objects.equals(sid, other.sid) &&
               Objects.equals(sourceSid, other.sourceSid) &&
               Objects.equals(size, other.size) &&
               Objects.equals(url, other.url) &&
               Objects.equals(type, other.type) &&
               Objects.equals(duration, other.duration) &&
               Objects.equals(containerFormat, other.containerFormat) &&
               Objects.equals(codec, other.codec) &&
               Objects.equals(groupingSids, other.groupingSids) &&
               Objects.equals(trackName, other.trackName) &&
               Objects.equals(offset, other.offset) &&
               Objects.equals(roomSid, other.roomSid) &&
               Objects.equals(links, other.links);
    }

    @Override
    public int hashCode() {
        return Objects.hash(accountSid,
                            status,
                            dateCreated,
                            sid,
                            sourceSid,
                            size,
                            url,
                            type,
                            duration,
                            containerFormat,
                            codec,
                            groupingSids,
                            trackName,
                            offset,
                            roomSid,
                            links);
    }
}
