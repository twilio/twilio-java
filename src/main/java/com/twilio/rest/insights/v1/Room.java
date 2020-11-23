/**
 * This code was generated by
 * \ / _    _  _|   _  _
 *  | (_)\/(_)(_|\/| |(/_  v1.0.0
 *       /       /
 */

package com.twilio.rest.insights.v1;

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
import java.util.List;
import java.util.Map;
import java.util.Objects;

/**
 * PLEASE NOTE that this class contains beta products that are subject to
 * change. Use them with caution.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
@ToString
public class Room extends Resource {
    private static final long serialVersionUID = 181047371989815L;

    public enum RoomType {
        GO("go"),
        PEER_TO_PEER("peer_to_peer"),
        GROUP("group"),
        GROUP_SMALL("group_small");

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

    public enum RoomStatus {
        IN_PROGRESS("in_progress"),
        COMPLETED("completed");

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

    public enum CreatedMethod {
        SDK("sdk"),
        AD_HOC("ad_hoc"),
        API("api");

        private final String value;

        private CreatedMethod(final String value) {
            this.value = value;
        }

        public String toString() {
            return value;
        }

        /**
         * Generate a CreatedMethod from a string.
         * @param value string value
         * @return generated CreatedMethod
         */
        @JsonCreator
        public static CreatedMethod forValue(final String value) {
            return Promoter.enumFromString(value, CreatedMethod.values());
        }
    }

    public enum EndReason {
        ROOM_ENDED_VIA_API("room_ended_via_api"),
        TIMEOUT("timeout");

        private final String value;

        private EndReason(final String value) {
            this.value = value;
        }

        public String toString() {
            return value;
        }

        /**
         * Generate a EndReason from a string.
         * @param value string value
         * @return generated EndReason
         */
        @JsonCreator
        public static EndReason forValue(final String value) {
            return Promoter.enumFromString(value, EndReason.values());
        }
    }

    public enum Codec {
        VP8("VP8"),
        H264("H264"),
        VP9("VP9");

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

    public enum TwilioRealm {
        US1("us1"),
        US2("us2"),
        AU1("au1"),
        BR1("br1"),
        IE1("ie1"),
        JP1("jp1"),
        SG1("sg1"),
        IN1("in1"),
        DE1("de1"),
        GLL("gll");

        private final String value;

        private TwilioRealm(final String value) {
            this.value = value;
        }

        public String toString() {
            return value;
        }

        /**
         * Generate a TwilioRealm from a string.
         * @param value string value
         * @return generated TwilioRealm
         */
        @JsonCreator
        public static TwilioRealm forValue(final String value) {
            return Promoter.enumFromString(value, TwilioRealm.values());
        }
    }

    public enum ProcessingState {
        COMPLETE("complete"),
        IN_PROGRESS("in_progress");

        private final String value;

        private ProcessingState(final String value) {
            this.value = value;
        }

        public String toString() {
            return value;
        }

        /**
         * Generate a ProcessingState from a string.
         * @param value string value
         * @return generated ProcessingState
         */
        @JsonCreator
        public static ProcessingState forValue(final String value) {
            return Promoter.enumFromString(value, ProcessingState.values());
        }
    }

    public enum EdgeLocation {
        ASHBURN("ashburn"),
        DUBLIN("dublin"),
        FRANKFURT("frankfurt"),
        SINGAPORE("singapore"),
        SYDNEY("sydney"),
        SAO_PAULO("sao_paulo"),
        ROAMING("roaming"),
        UMATILLA("umatilla"),
        TOKYO("tokyo");

        private final String value;

        private EdgeLocation(final String value) {
            this.value = value;
        }

        public String toString() {
            return value;
        }

        /**
         * Generate a EdgeLocation from a string.
         * @param value string value
         * @return generated EdgeLocation
         */
        @JsonCreator
        public static EdgeLocation forValue(final String value) {
            return Promoter.enumFromString(value, EdgeLocation.values());
        }
    }

    /**
     * Create a RoomFetcher to execute fetch.
     *
     * @param pathRoomSid The room_sid
     * @return RoomFetcher capable of executing the fetch
     */
    public static RoomFetcher fetcher(final String pathRoomSid) {
        return new RoomFetcher(pathRoomSid);
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

    private final String accountSid;
    private final String roomSid;
    private final String roomName;
    private final ZonedDateTime createTime;
    private final ZonedDateTime endTime;
    private final Room.RoomType roomType;
    private final Room.RoomStatus roomStatus;
    private final URI statusCallback;
    private final HttpMethod statusCallbackMethod;
    private final Room.CreatedMethod createdMethod;
    private final Room.EndReason endReason;
    private final Integer maxParticipants;
    private final Integer uniqueParticipants;
    private final Integer uniqueParticipantIdentities;
    private final Integer concurrentParticipants;
    private final Integer maxConcurrentParticipants;
    private final List<Room.Codec> codecs;
    private final Room.TwilioRealm mediaRegion;
    private final Long durationSec;
    private final Long totalParticipantDurationSec;
    private final Long totalRecordingDurationSec;
    private final Room.ProcessingState processingState;
    private final Boolean recordingEnabled;
    private final Room.EdgeLocation edgeLocation;
    private final URI url;
    private final Map<String, String> links;

    @JsonCreator
    private Room(@JsonProperty("account_sid")
                 final String accountSid,
                 @JsonProperty("room_sid")
                 final String roomSid,
                 @JsonProperty("room_name")
                 final String roomName,
                 @JsonProperty("create_time")
                 final String createTime,
                 @JsonProperty("end_time")
                 final String endTime,
                 @JsonProperty("room_type")
                 final Room.RoomType roomType,
                 @JsonProperty("room_status")
                 final Room.RoomStatus roomStatus,
                 @JsonProperty("status_callback")
                 final URI statusCallback,
                 @JsonProperty("status_callback_method")
                 final HttpMethod statusCallbackMethod,
                 @JsonProperty("created_method")
                 final Room.CreatedMethod createdMethod,
                 @JsonProperty("end_reason")
                 final Room.EndReason endReason,
                 @JsonProperty("max_participants")
                 final Integer maxParticipants,
                 @JsonProperty("unique_participants")
                 final Integer uniqueParticipants,
                 @JsonProperty("unique_participant_identities")
                 final Integer uniqueParticipantIdentities,
                 @JsonProperty("concurrent_participants")
                 final Integer concurrentParticipants,
                 @JsonProperty("max_concurrent_participants")
                 final Integer maxConcurrentParticipants,
                 @JsonProperty("codecs")
                 final List<Room.Codec> codecs,
                 @JsonProperty("media_region")
                 final Room.TwilioRealm mediaRegion,
                 @JsonProperty("duration_sec")
                 final Long durationSec,
                 @JsonProperty("total_participant_duration_sec")
                 final Long totalParticipantDurationSec,
                 @JsonProperty("total_recording_duration_sec")
                 final Long totalRecordingDurationSec,
                 @JsonProperty("processing_state")
                 final Room.ProcessingState processingState,
                 @JsonProperty("recording_enabled")
                 final Boolean recordingEnabled,
                 @JsonProperty("edge_location")
                 final Room.EdgeLocation edgeLocation,
                 @JsonProperty("url")
                 final URI url,
                 @JsonProperty("links")
                 final Map<String, String> links) {
        this.accountSid = accountSid;
        this.roomSid = roomSid;
        this.roomName = roomName;
        this.createTime = DateConverter.iso8601DateTimeFromString(createTime);
        this.endTime = DateConverter.iso8601DateTimeFromString(endTime);
        this.roomType = roomType;
        this.roomStatus = roomStatus;
        this.statusCallback = statusCallback;
        this.statusCallbackMethod = statusCallbackMethod;
        this.createdMethod = createdMethod;
        this.endReason = endReason;
        this.maxParticipants = maxParticipants;
        this.uniqueParticipants = uniqueParticipants;
        this.uniqueParticipantIdentities = uniqueParticipantIdentities;
        this.concurrentParticipants = concurrentParticipants;
        this.maxConcurrentParticipants = maxConcurrentParticipants;
        this.codecs = codecs;
        this.mediaRegion = mediaRegion;
        this.durationSec = durationSec;
        this.totalParticipantDurationSec = totalParticipantDurationSec;
        this.totalRecordingDurationSec = totalRecordingDurationSec;
        this.processingState = processingState;
        this.recordingEnabled = recordingEnabled;
        this.edgeLocation = edgeLocation;
        this.url = url;
        this.links = links;
    }

    /**
     * Returns The account_sid.
     *
     * @return The account_sid
     */
    public final String getAccountSid() {
        return this.accountSid;
    }

    /**
     * Returns The room_sid.
     *
     * @return The room_sid
     */
    public final String getRoomSid() {
        return this.roomSid;
    }

    /**
     * Returns The room_name.
     *
     * @return The room_name
     */
    public final String getRoomName() {
        return this.roomName;
    }

    /**
     * Returns The create_time.
     *
     * @return The create_time
     */
    public final ZonedDateTime getCreateTime() {
        return this.createTime;
    }

    /**
     * Returns The end_time.
     *
     * @return The end_time
     */
    public final ZonedDateTime getEndTime() {
        return this.endTime;
    }

    /**
     * Returns The room_type.
     *
     * @return The room_type
     */
    public final Room.RoomType getRoomType() {
        return this.roomType;
    }

    /**
     * Returns The room_status.
     *
     * @return The room_status
     */
    public final Room.RoomStatus getRoomStatus() {
        return this.roomStatus;
    }

    /**
     * Returns The status_callback.
     *
     * @return The status_callback
     */
    public final URI getStatusCallback() {
        return this.statusCallback;
    }

    /**
     * Returns The status_callback_method.
     *
     * @return The status_callback_method
     */
    public final HttpMethod getStatusCallbackMethod() {
        return this.statusCallbackMethod;
    }

    /**
     * Returns The created_method.
     *
     * @return The created_method
     */
    public final Room.CreatedMethod getCreatedMethod() {
        return this.createdMethod;
    }

    /**
     * Returns The end_reason.
     *
     * @return The end_reason
     */
    public final Room.EndReason getEndReason() {
        return this.endReason;
    }

    /**
     * Returns The max_participants.
     *
     * @return The max_participants
     */
    public final Integer getMaxParticipants() {
        return this.maxParticipants;
    }

    /**
     * Returns The unique_participants.
     *
     * @return The unique_participants
     */
    public final Integer getUniqueParticipants() {
        return this.uniqueParticipants;
    }

    /**
     * Returns The unique_participant_identities.
     *
     * @return The unique_participant_identities
     */
    public final Integer getUniqueParticipantIdentities() {
        return this.uniqueParticipantIdentities;
    }

    /**
     * Returns The concurrent_participants.
     *
     * @return The concurrent_participants
     */
    public final Integer getConcurrentParticipants() {
        return this.concurrentParticipants;
    }

    /**
     * Returns The max_concurrent_participants.
     *
     * @return The max_concurrent_participants
     */
    public final Integer getMaxConcurrentParticipants() {
        return this.maxConcurrentParticipants;
    }

    /**
     * Returns The codecs.
     *
     * @return The codecs
     */
    public final List<Room.Codec> getCodecs() {
        return this.codecs;
    }

    /**
     * Returns The media_region.
     *
     * @return The media_region
     */
    public final Room.TwilioRealm getMediaRegion() {
        return this.mediaRegion;
    }

    /**
     * Returns The duration_sec.
     *
     * @return The duration_sec
     */
    public final Long getDurationSec() {
        return this.durationSec;
    }

    /**
     * Returns The total_participant_duration_sec.
     *
     * @return The total_participant_duration_sec
     */
    public final Long getTotalParticipantDurationSec() {
        return this.totalParticipantDurationSec;
    }

    /**
     * Returns The total_recording_duration_sec.
     *
     * @return The total_recording_duration_sec
     */
    public final Long getTotalRecordingDurationSec() {
        return this.totalRecordingDurationSec;
    }

    /**
     * Returns The processing_state.
     *
     * @return The processing_state
     */
    public final Room.ProcessingState getProcessingState() {
        return this.processingState;
    }

    /**
     * Returns The recording_enabled.
     *
     * @return The recording_enabled
     */
    public final Boolean getRecordingEnabled() {
        return this.recordingEnabled;
    }

    /**
     * Returns The edge_location.
     *
     * @return The edge_location
     */
    public final Room.EdgeLocation getEdgeLocation() {
        return this.edgeLocation;
    }

    /**
     * Returns The url.
     *
     * @return The url
     */
    public final URI getUrl() {
        return this.url;
    }

    /**
     * Returns The links.
     *
     * @return The links
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

        return Objects.equals(accountSid, other.accountSid) &&
               Objects.equals(roomSid, other.roomSid) &&
               Objects.equals(roomName, other.roomName) &&
               Objects.equals(createTime, other.createTime) &&
               Objects.equals(endTime, other.endTime) &&
               Objects.equals(roomType, other.roomType) &&
               Objects.equals(roomStatus, other.roomStatus) &&
               Objects.equals(statusCallback, other.statusCallback) &&
               Objects.equals(statusCallbackMethod, other.statusCallbackMethod) &&
               Objects.equals(createdMethod, other.createdMethod) &&
               Objects.equals(endReason, other.endReason) &&
               Objects.equals(maxParticipants, other.maxParticipants) &&
               Objects.equals(uniqueParticipants, other.uniqueParticipants) &&
               Objects.equals(uniqueParticipantIdentities, other.uniqueParticipantIdentities) &&
               Objects.equals(concurrentParticipants, other.concurrentParticipants) &&
               Objects.equals(maxConcurrentParticipants, other.maxConcurrentParticipants) &&
               Objects.equals(codecs, other.codecs) &&
               Objects.equals(mediaRegion, other.mediaRegion) &&
               Objects.equals(durationSec, other.durationSec) &&
               Objects.equals(totalParticipantDurationSec, other.totalParticipantDurationSec) &&
               Objects.equals(totalRecordingDurationSec, other.totalRecordingDurationSec) &&
               Objects.equals(processingState, other.processingState) &&
               Objects.equals(recordingEnabled, other.recordingEnabled) &&
               Objects.equals(edgeLocation, other.edgeLocation) &&
               Objects.equals(url, other.url) &&
               Objects.equals(links, other.links);
    }

    @Override
    public int hashCode() {
        return Objects.hash(accountSid,
                            roomSid,
                            roomName,
                            createTime,
                            endTime,
                            roomType,
                            roomStatus,
                            statusCallback,
                            statusCallbackMethod,
                            createdMethod,
                            endReason,
                            maxParticipants,
                            uniqueParticipants,
                            uniqueParticipantIdentities,
                            concurrentParticipants,
                            maxConcurrentParticipants,
                            codecs,
                            mediaRegion,
                            durationSec,
                            totalParticipantDurationSec,
                            totalRecordingDurationSec,
                            processingState,
                            recordingEnabled,
                            edgeLocation,
                            url,
                            links);
    }
}