/**
 * This code was generated by
 * \ / _    _  _|   _  _
 *  | (_)\/(_)(_|\/| |(/_  v1.0.0
 *       /       /
 */

package com.twilio.rest.proxy.v1.service.session;

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
import java.util.Map;
import java.util.Objects;

/**
 * PLEASE NOTE that this class contains beta products that are subject to
 * change. Use them with caution.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class Interaction extends Resource {
    private static final long serialVersionUID = 184706954085822L;

    public enum Type {
        MESSAGE("message"),
        VOICE("voice"),
        UNKNOWN("unknown");

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

    public enum ResourceStatus {
        ACCEPTED("accepted"),
        ANSWERED("answered"),
        BUSY("busy"),
        CANCELED("canceled"),
        COMPLETED("completed"),
        DELETED("deleted"),
        DELIVERED("delivered"),
        DELIVERY_UNKNOWN("delivery-unknown"),
        FAILED("failed"),
        IN_PROGRESS("in-progress"),
        INITIATED("initiated"),
        NO_ANSWER("no-answer"),
        QUEUED("queued"),
        RECEIVED("received"),
        RECEIVING("receiving"),
        RINGING("ringing"),
        SCHEDULED("scheduled"),
        SENDING("sending"),
        SENT("sent"),
        UNDELIVERED("undelivered"),
        UNKNOWN("unknown");

        private final String value;

        private ResourceStatus(final String value) {
            this.value = value;
        }

        public String toString() {
            return value;
        }

        /**
         * Generate a ResourceStatus from a string.
         * @param value string value
         * @return generated ResourceStatus
         */
        @JsonCreator
        public static ResourceStatus forValue(final String value) {
            return Promoter.enumFromString(value, ResourceStatus.values());
        }
    }

    /**
     * Create a InteractionFetcher to execute fetch.
     *
     * @param pathServiceSid The SID of the parent Service of the resource to fetch
     * @param pathSessionSid he SID of the parent Session of the resource to fetch
     * @param pathSid The unique string that identifies the resource
     * @return InteractionFetcher capable of executing the fetch
     */
    public static InteractionFetcher fetcher(final String pathServiceSid,
                                             final String pathSessionSid,
                                             final String pathSid) {
        return new InteractionFetcher(pathServiceSid, pathSessionSid, pathSid);
    }

    /**
     * Create a InteractionReader to execute read.
     *
     * @param pathServiceSid The SID of the parent Service to read the resource from
     * @param pathSessionSid The SID of the parent Session to read the resource from
     * @return InteractionReader capable of executing the read
     */
    public static InteractionReader reader(final String pathServiceSid,
                                           final String pathSessionSid) {
        return new InteractionReader(pathServiceSid, pathSessionSid);
    }

    /**
     * Create a InteractionDeleter to execute delete.
     *
     * @param pathServiceSid The SID of the parent Service of the resource to delete
     * @param pathSessionSid he SID of the parent Session of the resource to delete
     * @param pathSid The unique string that identifies the resource
     * @return InteractionDeleter capable of executing the delete
     */
    public static InteractionDeleter deleter(final String pathServiceSid,
                                             final String pathSessionSid,
                                             final String pathSid) {
        return new InteractionDeleter(pathServiceSid, pathSessionSid, pathSid);
    }

    /**
     * Converts a JSON String into a Interaction object using the provided
     * ObjectMapper.
     *
     * @param json Raw JSON String
     * @param objectMapper Jackson ObjectMapper
     * @return Interaction object represented by the provided JSON
     */
    public static Interaction fromJson(final String json, final ObjectMapper objectMapper) {
        // Convert all checked exceptions to Runtime
        try {
            return objectMapper.readValue(json, Interaction.class);
        } catch (final JsonMappingException | JsonParseException e) {
            throw new ApiException(e.getMessage(), e);
        } catch (final IOException e) {
            throw new ApiConnectionException(e.getMessage(), e);
        }
    }

    /**
     * Converts a JSON InputStream into a Interaction object using the provided
     * ObjectMapper.
     *
     * @param json Raw JSON InputStream
     * @param objectMapper Jackson ObjectMapper
     * @return Interaction object represented by the provided JSON
     */
    public static Interaction fromJson(final InputStream json, final ObjectMapper objectMapper) {
        // Convert all checked exceptions to Runtime
        try {
            return objectMapper.readValue(json, Interaction.class);
        } catch (final JsonMappingException | JsonParseException e) {
            throw new ApiException(e.getMessage(), e);
        } catch (final IOException e) {
            throw new ApiConnectionException(e.getMessage(), e);
        }
    }

    private final String sid;
    private final String sessionSid;
    private final String serviceSid;
    private final String accountSid;
    private final String data;
    private final Interaction.Type type;
    private final String inboundParticipantSid;
    private final String inboundResourceSid;
    private final Interaction.ResourceStatus inboundResourceStatus;
    private final String inboundResourceType;
    private final URI inboundResourceUrl;
    private final String outboundParticipantSid;
    private final String outboundResourceSid;
    private final Interaction.ResourceStatus outboundResourceStatus;
    private final String outboundResourceType;
    private final URI outboundResourceUrl;
    private final ZonedDateTime dateCreated;
    private final ZonedDateTime dateUpdated;
    private final URI url;

    @JsonCreator
    private Interaction(@JsonProperty("sid")
                        final String sid,
                        @JsonProperty("session_sid")
                        final String sessionSid,
                        @JsonProperty("service_sid")
                        final String serviceSid,
                        @JsonProperty("account_sid")
                        final String accountSid,
                        @JsonProperty("data")
                        final String data,
                        @JsonProperty("type")
                        final Interaction.Type type,
                        @JsonProperty("inbound_participant_sid")
                        final String inboundParticipantSid,
                        @JsonProperty("inbound_resource_sid")
                        final String inboundResourceSid,
                        @JsonProperty("inbound_resource_status")
                        final Interaction.ResourceStatus inboundResourceStatus,
                        @JsonProperty("inbound_resource_type")
                        final String inboundResourceType,
                        @JsonProperty("inbound_resource_url")
                        final URI inboundResourceUrl,
                        @JsonProperty("outbound_participant_sid")
                        final String outboundParticipantSid,
                        @JsonProperty("outbound_resource_sid")
                        final String outboundResourceSid,
                        @JsonProperty("outbound_resource_status")
                        final Interaction.ResourceStatus outboundResourceStatus,
                        @JsonProperty("outbound_resource_type")
                        final String outboundResourceType,
                        @JsonProperty("outbound_resource_url")
                        final URI outboundResourceUrl,
                        @JsonProperty("date_created")
                        final String dateCreated,
                        @JsonProperty("date_updated")
                        final String dateUpdated,
                        @JsonProperty("url")
                        final URI url) {
        this.sid = sid;
        this.sessionSid = sessionSid;
        this.serviceSid = serviceSid;
        this.accountSid = accountSid;
        this.data = data;
        this.type = type;
        this.inboundParticipantSid = inboundParticipantSid;
        this.inboundResourceSid = inboundResourceSid;
        this.inboundResourceStatus = inboundResourceStatus;
        this.inboundResourceType = inboundResourceType;
        this.inboundResourceUrl = inboundResourceUrl;
        this.outboundParticipantSid = outboundParticipantSid;
        this.outboundResourceSid = outboundResourceSid;
        this.outboundResourceStatus = outboundResourceStatus;
        this.outboundResourceType = outboundResourceType;
        this.outboundResourceUrl = outboundResourceUrl;
        this.dateCreated = DateConverter.iso8601DateTimeFromString(dateCreated);
        this.dateUpdated = DateConverter.iso8601DateTimeFromString(dateUpdated);
        this.url = url;
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
     * Returns The The SID of the resource's parent Session.
     *
     * @return The SID of the resource's parent Session
     */
    public final String getSessionSid() {
        return this.sessionSid;
    }

    /**
     * Returns The The SID of the resource's parent Service.
     *
     * @return The SID of the resource's parent Service
     */
    public final String getServiceSid() {
        return this.serviceSid;
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
     * Returns The A JSON string that includes the message body of message
     * interactions.
     *
     * @return A JSON string that includes the message body of message interactions
     */
    public final String getData() {
        return this.data;
    }

    /**
     * Returns The The Type of the Interaction.
     *
     * @return The Type of the Interaction
     */
    public final Interaction.Type getType() {
        return this.type;
    }

    /**
     * Returns The The SID of the inbound Participant resource.
     *
     * @return The SID of the inbound Participant resource
     */
    public final String getInboundParticipantSid() {
        return this.inboundParticipantSid;
    }

    /**
     * Returns The The SID of the inbound resource.
     *
     * @return The SID of the inbound resource
     */
    public final String getInboundResourceSid() {
        return this.inboundResourceSid;
    }

    /**
     * Returns The The inbound resource status of the Interaction.
     *
     * @return The inbound resource status of the Interaction
     */
    public final Interaction.ResourceStatus getInboundResourceStatus() {
        return this.inboundResourceStatus;
    }

    /**
     * Returns The The inbound resource type.
     *
     * @return The inbound resource type
     */
    public final String getInboundResourceType() {
        return this.inboundResourceType;
    }

    /**
     * Returns The The URL of the Twilio inbound resource.
     *
     * @return The URL of the Twilio inbound resource
     */
    public final URI getInboundResourceUrl() {
        return this.inboundResourceUrl;
    }

    /**
     * Returns The The SID of the outbound Participant.
     *
     * @return The SID of the outbound Participant
     */
    public final String getOutboundParticipantSid() {
        return this.outboundParticipantSid;
    }

    /**
     * Returns The The SID of the outbound resource.
     *
     * @return The SID of the outbound resource
     */
    public final String getOutboundResourceSid() {
        return this.outboundResourceSid;
    }

    /**
     * Returns The The outbound resource status of the Interaction.
     *
     * @return The outbound resource status of the Interaction
     */
    public final Interaction.ResourceStatus getOutboundResourceStatus() {
        return this.outboundResourceStatus;
    }

    /**
     * Returns The The outbound resource type.
     *
     * @return The outbound resource type
     */
    public final String getOutboundResourceType() {
        return this.outboundResourceType;
    }

    /**
     * Returns The The URL of the Twilio outbound resource.
     *
     * @return The URL of the Twilio outbound resource
     */
    public final URI getOutboundResourceUrl() {
        return this.outboundResourceUrl;
    }

    /**
     * Returns The The ISO 8601 date and time in GMT when the Interaction was
     * created.
     *
     * @return The ISO 8601 date and time in GMT when the Interaction was created
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
     * Returns The The absolute URL of the Interaction resource.
     *
     * @return The absolute URL of the Interaction resource
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

        Interaction other = (Interaction) o;

        return Objects.equals(sid, other.sid) &&
               Objects.equals(sessionSid, other.sessionSid) &&
               Objects.equals(serviceSid, other.serviceSid) &&
               Objects.equals(accountSid, other.accountSid) &&
               Objects.equals(data, other.data) &&
               Objects.equals(type, other.type) &&
               Objects.equals(inboundParticipantSid, other.inboundParticipantSid) &&
               Objects.equals(inboundResourceSid, other.inboundResourceSid) &&
               Objects.equals(inboundResourceStatus, other.inboundResourceStatus) &&
               Objects.equals(inboundResourceType, other.inboundResourceType) &&
               Objects.equals(inboundResourceUrl, other.inboundResourceUrl) &&
               Objects.equals(outboundParticipantSid, other.outboundParticipantSid) &&
               Objects.equals(outboundResourceSid, other.outboundResourceSid) &&
               Objects.equals(outboundResourceStatus, other.outboundResourceStatus) &&
               Objects.equals(outboundResourceType, other.outboundResourceType) &&
               Objects.equals(outboundResourceUrl, other.outboundResourceUrl) &&
               Objects.equals(dateCreated, other.dateCreated) &&
               Objects.equals(dateUpdated, other.dateUpdated) &&
               Objects.equals(url, other.url);
    }

    @Override
    public int hashCode() {
        return Objects.hash(sid,
                            sessionSid,
                            serviceSid,
                            accountSid,
                            data,
                            type,
                            inboundParticipantSid,
                            inboundResourceSid,
                            inboundResourceStatus,
                            inboundResourceType,
                            inboundResourceUrl,
                            outboundParticipantSid,
                            outboundResourceSid,
                            outboundResourceStatus,
                            outboundResourceType,
                            outboundResourceUrl,
                            dateCreated,
                            dateUpdated,
                            url);
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                          .add("sid", sid)
                          .add("sessionSid", sessionSid)
                          .add("serviceSid", serviceSid)
                          .add("accountSid", accountSid)
                          .add("data", data)
                          .add("type", type)
                          .add("inboundParticipantSid", inboundParticipantSid)
                          .add("inboundResourceSid", inboundResourceSid)
                          .add("inboundResourceStatus", inboundResourceStatus)
                          .add("inboundResourceType", inboundResourceType)
                          .add("inboundResourceUrl", inboundResourceUrl)
                          .add("outboundParticipantSid", outboundParticipantSid)
                          .add("outboundResourceSid", outboundResourceSid)
                          .add("outboundResourceStatus", outboundResourceStatus)
                          .add("outboundResourceType", outboundResourceType)
                          .add("outboundResourceUrl", outboundResourceUrl)
                          .add("dateCreated", dateCreated)
                          .add("dateUpdated", dateUpdated)
                          .add("url", url)
                          .toString();
    }
}
