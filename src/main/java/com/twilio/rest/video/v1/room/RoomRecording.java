/*
 * This code was generated by
 * ___ _ _ _ _ _    _ ____    ____ ____ _    ____ ____ _  _ ____ ____ ____ ___ __   __
 *  |  | | | | |    | |  | __ |  | |__| | __ | __ |___ |\ | |___ |__/ |__|  | |  | |__/
 *  |  |_|_| | |___ | |__|    |__| |  | |    |__] |___ | \| |___ |  \ |  |  | |__| |  \
 *
 * Twilio - Video
 * This is the public Twilio REST API.
 *
 * NOTE: This class is auto generated by OpenAPI Generator.
 * https://openapi-generator.tech
 * Do not edit the class manually.
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
import java.util.Map;
import java.util.Objects;
import lombok.ToString;
import lombok.ToString;

@JsonIgnoreProperties(ignoreUnknown = true)
@ToString
public class RoomRecording extends Resource {

    private static final long serialVersionUID = 14590901968979L;

    public static RoomRecordingDeleter deleter(
        final String pathRoomSid,
        final String pathSid
    ) {
        return new RoomRecordingDeleter(pathRoomSid, pathSid);
    }

    public static RoomRecordingFetcher fetcher(
        final String pathRoomSid,
        final String pathSid
    ) {
        return new RoomRecordingFetcher(pathRoomSid, pathSid);
    }

    public static RoomRecordingReader reader(final String pathRoomSid) {
        return new RoomRecordingReader(pathRoomSid);
    }

    /**
     * Converts a JSON String into a RoomRecording object using the provided ObjectMapper.
     *
     * @param json Raw JSON String
     * @param objectMapper Jackson ObjectMapper
     * @return RoomRecording object represented by the provided JSON
     */
    public static RoomRecording fromJson(
        final String json,
        final ObjectMapper objectMapper
    ) {
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
    public static RoomRecording fromJson(
        final InputStream json,
        final ObjectMapper objectMapper
    ) {
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
    private final URI mediaExternalLocation;
    private final String roomSid;
    private final Map<String, String> links;

    @JsonCreator
    private RoomRecording(
        @JsonProperty("account_sid") final String accountSid,
        @JsonProperty("status") final RoomRecording.Status status,
        @JsonProperty("date_created") final String dateCreated,
        @JsonProperty("sid") final String sid,
        @JsonProperty("source_sid") final String sourceSid,
        @JsonProperty("size") final Long size,
        @JsonProperty("url") final URI url,
        @JsonProperty("type") final RoomRecording.Type type,
        @JsonProperty("duration") final Integer duration,
        @JsonProperty(
            "container_format"
        ) final RoomRecording.Format containerFormat,
        @JsonProperty("codec") final RoomRecording.Codec codec,
        @JsonProperty("grouping_sids") final Map<String, Object> groupingSids,
        @JsonProperty("track_name") final String trackName,
        @JsonProperty("offset") final Long offset,
        @JsonProperty(
            "media_external_location"
        ) final URI mediaExternalLocation,
        @JsonProperty("room_sid") final String roomSid,
        @JsonProperty("links") final Map<String, String> links
    ) {
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
        this.mediaExternalLocation = mediaExternalLocation;
        this.roomSid = roomSid;
        this.links = links;
    }

    public final String getAccountSid() {
        return this.accountSid;
    }

    public final RoomRecording.Status getStatus() {
        return this.status;
    }

    public final ZonedDateTime getDateCreated() {
        return this.dateCreated;
    }

    public final String getSid() {
        return this.sid;
    }

    public final String getSourceSid() {
        return this.sourceSid;
    }

    public final Long getSize() {
        return this.size;
    }

    public final URI getUrl() {
        return this.url;
    }

    public final RoomRecording.Type getType() {
        return this.type;
    }

    public final Integer getDuration() {
        return this.duration;
    }

    public final RoomRecording.Format getContainerFormat() {
        return this.containerFormat;
    }

    public final RoomRecording.Codec getCodec() {
        return this.codec;
    }

    public final Map<String, Object> getGroupingSids() {
        return this.groupingSids;
    }

    public final String getTrackName() {
        return this.trackName;
    }

    public final Long getOffset() {
        return this.offset;
    }

    public final URI getMediaExternalLocation() {
        return this.mediaExternalLocation;
    }

    public final String getRoomSid() {
        return this.roomSid;
    }

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

        return (
            Objects.equals(accountSid, other.accountSid) &&
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
            Objects.equals(
                mediaExternalLocation,
                other.mediaExternalLocation
            ) &&
            Objects.equals(roomSid, other.roomSid) &&
            Objects.equals(links, other.links)
        );
    }

    @Override
    public int hashCode() {
        return Objects.hash(
            accountSid,
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
            mediaExternalLocation,
            roomSid,
            links
        );
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

        @JsonCreator
        public static Type forValue(final String value) {
            return Promoter.enumFromString(value, Type.values());
        }
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

        @JsonCreator
        public static Status forValue(final String value) {
            return Promoter.enumFromString(value, Status.values());
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

        @JsonCreator
        public static Codec forValue(final String value) {
            return Promoter.enumFromString(value, Codec.values());
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

        @JsonCreator
        public static Format forValue(final String value) {
            return Promoter.enumFromString(value, Format.values());
        }
    }
}
