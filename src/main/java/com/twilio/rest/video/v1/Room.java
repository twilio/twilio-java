/**
 * This code was generated by
 * \ / _    _  _|   _  _
 *  | (_)\/(_)(_|\/| |(/_  v1.0.0
 *       /       /
 */

package com.twilio.rest.video.v1;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.base.MoreObjects;
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
import java.time.ZonedDateTime;

import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.util.List;
import java.util.Map;
import java.util.Objects;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Room extends Resource {
    private static final long serialVersionUID = 123703237879933L;

    public enum RoomStatus {
        IN_PROGRESS("in-progress"),
        COMPLETED("completed"),
        FAILED("failed");

        private final String value;

        private RoomStatus(final String value) {
            this.value = value;
        }

        public String toString() {
            return value;
        }

        /**
         * Generate a RoomStatus from a string.
         * @param value string value
         * @return generated RoomStatus
         */
        @JsonCreator
        public static RoomStatus forValue(final String value) {
            return Promoter.enumFromString(value, RoomStatus.values());
        }
    }

    public enum RoomType {
        PEER_TO_PEER("peer-to-peer"),
        GROUP("group"),
        GROUP_SMALL("group-small");

        private final String value;

        private RoomType(final String value) {
            this.value = value;
        }

        public String toString() {
            return value;
        }

        /**
         * Generate a RoomType from a string.
         * @param value string value
         * @return generated RoomType
         */
        @JsonCreator
        public static RoomType forValue(final String value) {
            return Promoter.enumFromString(value, RoomType.values());
        }
    }

    public enum VideoCodec {
        VP8("VP8"),
        H264("H264");

        private final String value;

        private VideoCodec(final String value) {
            this.value = value;
        }

        public String toString() {
            return value;
        }

        /**
         * Generate a VideoCodec from a string.
         * @param value string value
         * @return generated VideoCodec
         */
        @JsonCreator
        public static VideoCodec forValue(final String value) {
            return Promoter.enumFromString(value, VideoCodec.values());
        }
    }

    /**
     * Create a RoomFetcher to execute fetch.
     *
     * @param pathSid The SID that identifies the resource to fetch
     * @return RoomFetcher capable of executing the fetch
     */
    public static RoomFetcher fetcher(final String pathSid) {
        return new RoomFetcher(pathSid);
    }

    /**
     * Create a RoomCreator to execute create.
     *
     * @return RoomCreator capable of executing the create
     */
    public static RoomCreator creator() {
        return new RoomCreator();
    }

    /**
     * Create a RoomReader to execute read.
     *
     * @return RoomReader capable of executing the read
     */
    public static RoomReader reader() {
        return new RoomReader();
    }

    /**
     * Create a RoomUpdater to execute update.
     *
     * @param pathSid The SID that identifies the resource to update
     * @param status The new status of the resource
     * @return RoomUpdater capable of executing the update
     */
    public static RoomUpdater updater(final String pathSid,
                                      final Room.RoomStatus status) {
        return new RoomUpdater(pathSid, status);
    }

    /**
     * Converts a JSON String into a Room object using the provided ObjectMapper.
     *
     * @param json Raw JSON String
     * @param objectMapper Jackson ObjectMapper
     * @return Room object represented by the provided JSON
     */
    public static Room fromJson(final String json, final ObjectMapper objectMapper) {
        // Convert all checked exceptions to Runtime
        try {
            return objectMapper.readValue(json, Room.class);
        } catch (final JsonMappingException | JsonParseException e) {
            throw new ApiException(e.getMessage(), e);
        } catch (final IOException e) {
            throw new ApiConnectionException(e.getMessage(), e);
        }
    }

    /**
     * Converts a JSON InputStream into a Room object using the provided
     * ObjectMapper.
     *
     * @param json Raw JSON InputStream
     * @param objectMapper Jackson ObjectMapper
     * @return Room object represented by the provided JSON
     */
    public static Room fromJson(final InputStream json, final ObjectMapper objectMapper) {
        // Convert all checked exceptions to Runtime
        try {
            return objectMapper.readValue(json, Room.class);
        } catch (final JsonMappingException | JsonParseException e) {
            throw new ApiException(e.getMessage(), e);
        } catch (final IOException e) {
            throw new ApiConnectionException(e.getMessage(), e);
        }
    }

    private final String sid;
    private final Room.RoomStatus status;
    private final ZonedDateTime dateCreated;
    private final ZonedDateTime dateUpdated;
    private final String accountSid;
    private final Boolean enableTurn;
    private final String uniqueName;
    private final URI statusCallback;
    private final HttpMethod statusCallbackMethod;
    private final ZonedDateTime endTime;
    private final Integer duration;
    private final Room.RoomType type;
    private final Integer maxParticipants;
    private final Boolean recordParticipantsOnConnect;
    private final List<Room.VideoCodec> videoCodecs;
    private final String mediaRegion;
    private final URI url;
    private final Map<String, String> links;

    @JsonCreator
    private Room(@JsonProperty("sid")
                 final String sid,
                 @JsonProperty("status")
                 final Room.RoomStatus status,
                 @JsonProperty("date_created")
                 final String dateCreated,
                 @JsonProperty("date_updated")
                 final String dateUpdated,
                 @JsonProperty("account_sid")
                 final String accountSid,
                 @JsonProperty("enable_turn")
                 final Boolean enableTurn,
                 @JsonProperty("unique_name")
                 final String uniqueName,
                 @JsonProperty("status_callback")
                 final URI statusCallback,
                 @JsonProperty("status_callback_method")
                 final HttpMethod statusCallbackMethod,
                 @JsonProperty("end_time")
                 final String endTime,
                 @JsonProperty("duration")
                 final Integer duration,
                 @JsonProperty("type")
                 final Room.RoomType type,
                 @JsonProperty("max_participants")
                 final Integer maxParticipants,
                 @JsonProperty("record_participants_on_connect")
                 final Boolean recordParticipantsOnConnect,
                 @JsonProperty("video_codecs")
                 final List<Room.VideoCodec> videoCodecs,
                 @JsonProperty("media_region")
                 final String mediaRegion,
                 @JsonProperty("url")
                 final URI url,
                 @JsonProperty("links")
                 final Map<String, String> links) {
        this.sid = sid;
        this.status = status;
        this.dateCreated = DateConverter.iso8601DateTimeFromString(dateCreated);
        this.dateUpdated = DateConverter.iso8601DateTimeFromString(dateUpdated);
        this.accountSid = accountSid;
        this.enableTurn = enableTurn;
        this.uniqueName = uniqueName;
        this.statusCallback = statusCallback;
        this.statusCallbackMethod = statusCallbackMethod;
        this.endTime = DateConverter.iso8601DateTimeFromString(endTime);
        this.duration = duration;
        this.type = type;
        this.maxParticipants = maxParticipants;
        this.recordParticipantsOnConnect = recordParticipantsOnConnect;
        this.videoCodecs = videoCodecs;
        this.mediaRegion = mediaRegion;
        this.url = url;
        this.links = links;
    }

    /**
     * Returns The The unique string that identifies the resource.
     *
     * @return The unique string that identifies the resource
     */
    public final String getSid() {
        return this.sid;
    }

    /**
     * Returns The The status of the room.
     *
     * @return The status of the room
     */
    public final Room.RoomStatus getStatus() {
        return this.status;
    }

    /**
     * Returns The The ISO 8601 date and time in GMT when the resource was created.
     *
     * @return The ISO 8601 date and time in GMT when the resource was created
     */
    public final ZonedDateTime getDateCreated() {
        return this.dateCreated;
    }

    /**
     * Returns The The ISO 8601 date and time in GMT when the resource was last
     * updated.
     *
     * @return The ISO 8601 date and time in GMT when the resource was last updated
     */
    public final ZonedDateTime getDateUpdated() {
        return this.dateUpdated;
    }

    /**
     * Returns The The SID of the Account that created the resource.
     *
     * @return The SID of the Account that created the resource
     */
    public final String getAccountSid() {
        return this.accountSid;
    }

    /**
     * Returns The Enable Twilio's Network Traversal TURN service.
     *
     * @return Enable Twilio's Network Traversal TURN service
     */
    public final Boolean getEnableTurn() {
        return this.enableTurn;
    }

    /**
     * Returns The An application-defined string that uniquely identifies the
     * resource.
     *
     * @return An application-defined string that uniquely identifies the resource
     */
    public final String getUniqueName() {
        return this.uniqueName;
    }

    /**
     * Returns The The URL to send status information to your application.
     *
     * @return The URL to send status information to your application
     */
    public final URI getStatusCallback() {
        return this.statusCallback;
    }

    /**
     * Returns The The HTTP method we use to call status_callback.
     *
     * @return The HTTP method we use to call status_callback
     */
    public final HttpMethod getStatusCallbackMethod() {
        return this.statusCallbackMethod;
    }

    /**
     * Returns The The UTC end time of the room in UTC ISO 8601 format.
     *
     * @return The UTC end time of the room in UTC ISO 8601 format
     */
    public final ZonedDateTime getEndTime() {
        return this.endTime;
    }

    /**
     * Returns The The duration of the room in seconds.
     *
     * @return The duration of the room in seconds
     */
    public final Integer getDuration() {
        return this.duration;
    }

    /**
     * Returns The The type of room.
     *
     * @return The type of room
     */
    public final Room.RoomType getType() {
        return this.type;
    }

    /**
     * Returns The The maximum number of concurrent Participants allowed in the
     * room.
     *
     * @return The maximum number of concurrent Participants allowed in the room
     */
    public final Integer getMaxParticipants() {
        return this.maxParticipants;
    }

    /**
     * Returns The Whether to start recording when Participants connect.
     *
     * @return Whether to start recording when Participants connect
     */
    public final Boolean getRecordParticipantsOnConnect() {
        return this.recordParticipantsOnConnect;
    }

    /**
     * Returns The An array of the video codecs that are supported when publishing a
     * track in the room.
     *
     * @return An array of the video codecs that are supported when publishing a
     *         track in the room
     */
    public final List<Room.VideoCodec> getVideoCodecs() {
        return this.videoCodecs;
    }

    /**
     * Returns The The region for the media server in Group Rooms.
     *
     * @return The region for the media server in Group Rooms
     */
    public final String getMediaRegion() {
        return this.mediaRegion;
    }

    /**
     * Returns The The absolute URL of the resource.
     *
     * @return The absolute URL of the resource
     */
    public final URI getUrl() {
        return this.url;
    }

    /**
     * Returns The The URLs of related resources.
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

        Room other = (Room) o;

        return Objects.equals(sid, other.sid) &&
               Objects.equals(status, other.status) &&
               Objects.equals(dateCreated, other.dateCreated) &&
               Objects.equals(dateUpdated, other.dateUpdated) &&
               Objects.equals(accountSid, other.accountSid) &&
               Objects.equals(enableTurn, other.enableTurn) &&
               Objects.equals(uniqueName, other.uniqueName) &&
               Objects.equals(statusCallback, other.statusCallback) &&
               Objects.equals(statusCallbackMethod, other.statusCallbackMethod) &&
               Objects.equals(endTime, other.endTime) &&
               Objects.equals(duration, other.duration) &&
               Objects.equals(type, other.type) &&
               Objects.equals(maxParticipants, other.maxParticipants) &&
               Objects.equals(recordParticipantsOnConnect, other.recordParticipantsOnConnect) &&
               Objects.equals(videoCodecs, other.videoCodecs) &&
               Objects.equals(mediaRegion, other.mediaRegion) &&
               Objects.equals(url, other.url) &&
               Objects.equals(links, other.links);
    }

    @Override
    public int hashCode() {
        return Objects.hash(sid,
                            status,
                            dateCreated,
                            dateUpdated,
                            accountSid,
                            enableTurn,
                            uniqueName,
                            statusCallback,
                            statusCallbackMethod,
                            endTime,
                            duration,
                            type,
                            maxParticipants,
                            recordParticipantsOnConnect,
                            videoCodecs,
                            mediaRegion,
                            url,
                            links);
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                          .add("sid", sid)
                          .add("status", status)
                          .add("dateCreated", dateCreated)
                          .add("dateUpdated", dateUpdated)
                          .add("accountSid", accountSid)
                          .add("enableTurn", enableTurn)
                          .add("uniqueName", uniqueName)
                          .add("statusCallback", statusCallback)
                          .add("statusCallbackMethod", statusCallbackMethod)
                          .add("endTime", endTime)
                          .add("duration", duration)
                          .add("type", type)
                          .add("maxParticipants", maxParticipants)
                          .add("recordParticipantsOnConnect", recordParticipantsOnConnect)
                          .add("videoCodecs", videoCodecs)
                          .add("mediaRegion", mediaRegion)
                          .add("url", url)
                          .add("links", links)
                          .toString();
    }
}
