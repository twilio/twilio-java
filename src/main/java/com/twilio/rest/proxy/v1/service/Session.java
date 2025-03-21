/*
 * This code was generated by
 * ___ _ _ _ _ _    _ ____    ____ ____ _    ____ ____ _  _ ____ ____ ____ ___ __   __
 *  |  | | | | |    | |  | __ |  | |__| | __ | __ |___ |\ | |___ |__/ |__|  | |  | |__/
 *  |  |_|_| | |___ | |__|    |__| |  | |    |__] |___ | \| |___ |  \ |  |  | |__| |  \
 *
 * Twilio - Proxy
 * This is the public Twilio REST API.
 *
 * NOTE: This class is auto generated by OpenAPI Generator.
 * https://openapi-generator.tech
 * Do not edit the class manually.
 */

package com.twilio.rest.proxy.v1.service;

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
public class Session extends Resource {

    private static final long serialVersionUID = 86537474068288L;

    public static SessionCreator creator(final String pathServiceSid) {
        return new SessionCreator(pathServiceSid);
    }

    public static SessionDeleter deleter(
        final String pathServiceSid,
        final String pathSid
    ) {
        return new SessionDeleter(pathServiceSid, pathSid);
    }

    public static SessionFetcher fetcher(
        final String pathServiceSid,
        final String pathSid
    ) {
        return new SessionFetcher(pathServiceSid, pathSid);
    }

    public static SessionReader reader(final String pathServiceSid) {
        return new SessionReader(pathServiceSid);
    }

    public static SessionUpdater updater(
        final String pathServiceSid,
        final String pathSid
    ) {
        return new SessionUpdater(pathServiceSid, pathSid);
    }

    /**
     * Converts a JSON String into a Session object using the provided ObjectMapper.
     *
     * @param json Raw JSON String
     * @param objectMapper Jackson ObjectMapper
     * @return Session object represented by the provided JSON
     */
    public static Session fromJson(
        final String json,
        final ObjectMapper objectMapper
    ) {
        // Convert all checked exceptions to Runtime
        try {
            return objectMapper.readValue(json, Session.class);
        } catch (final JsonMappingException | JsonParseException e) {
            throw new ApiException(e.getMessage(), e);
        } catch (final IOException e) {
            throw new ApiConnectionException(e.getMessage(), e);
        }
    }

    /**
     * Converts a JSON InputStream into a Session object using the provided
     * ObjectMapper.
     *
     * @param json Raw JSON InputStream
     * @param objectMapper Jackson ObjectMapper
     * @return Session object represented by the provided JSON
     */
    public static Session fromJson(
        final InputStream json,
        final ObjectMapper objectMapper
    ) {
        // Convert all checked exceptions to Runtime
        try {
            return objectMapper.readValue(json, Session.class);
        } catch (final JsonMappingException | JsonParseException e) {
            throw new ApiException(e.getMessage(), e);
        } catch (final IOException e) {
            throw new ApiConnectionException(e.getMessage(), e);
        }
    }

    private final String sid;
    private final String serviceSid;
    private final String accountSid;
    private final ZonedDateTime dateStarted;
    private final ZonedDateTime dateEnded;
    private final ZonedDateTime dateLastInteraction;
    private final ZonedDateTime dateExpiry;
    private final String uniqueName;
    private final Session.Status status;
    private final String closedReason;
    private final Integer ttl;
    private final Session.Mode mode;
    private final ZonedDateTime dateCreated;
    private final ZonedDateTime dateUpdated;
    private final URI url;
    private final Map<String, String> links;

    @JsonCreator
    private Session(
        @JsonProperty("sid") final String sid,
        @JsonProperty("service_sid") final String serviceSid,
        @JsonProperty("account_sid") final String accountSid,
        @JsonProperty("date_started") final String dateStarted,
        @JsonProperty("date_ended") final String dateEnded,
        @JsonProperty("date_last_interaction") final String dateLastInteraction,
        @JsonProperty("date_expiry") final String dateExpiry,
        @JsonProperty("unique_name") final String uniqueName,
        @JsonProperty("status") final Session.Status status,
        @JsonProperty("closed_reason") final String closedReason,
        @JsonProperty("ttl") final Integer ttl,
        @JsonProperty("mode") final Session.Mode mode,
        @JsonProperty("date_created") final String dateCreated,
        @JsonProperty("date_updated") final String dateUpdated,
        @JsonProperty("url") final URI url,
        @JsonProperty("links") final Map<String, String> links
    ) {
        this.sid = sid;
        this.serviceSid = serviceSid;
        this.accountSid = accountSid;
        this.dateStarted = DateConverter.iso8601DateTimeFromString(dateStarted);
        this.dateEnded = DateConverter.iso8601DateTimeFromString(dateEnded);
        this.dateLastInteraction =
            DateConverter.iso8601DateTimeFromString(dateLastInteraction);
        this.dateExpiry = DateConverter.iso8601DateTimeFromString(dateExpiry);
        this.uniqueName = uniqueName;
        this.status = status;
        this.closedReason = closedReason;
        this.ttl = ttl;
        this.mode = mode;
        this.dateCreated = DateConverter.iso8601DateTimeFromString(dateCreated);
        this.dateUpdated = DateConverter.iso8601DateTimeFromString(dateUpdated);
        this.url = url;
        this.links = links;
    }

    public final String getSid() {
        return this.sid;
    }

    public final String getServiceSid() {
        return this.serviceSid;
    }

    public final String getAccountSid() {
        return this.accountSid;
    }

    public final ZonedDateTime getDateStarted() {
        return this.dateStarted;
    }

    public final ZonedDateTime getDateEnded() {
        return this.dateEnded;
    }

    public final ZonedDateTime getDateLastInteraction() {
        return this.dateLastInteraction;
    }

    public final ZonedDateTime getDateExpiry() {
        return this.dateExpiry;
    }

    public final String getUniqueName() {
        return this.uniqueName;
    }

    public final Session.Status getStatus() {
        return this.status;
    }

    public final String getClosedReason() {
        return this.closedReason;
    }

    public final Integer getTtl() {
        return this.ttl;
    }

    public final Session.Mode getMode() {
        return this.mode;
    }

    public final ZonedDateTime getDateCreated() {
        return this.dateCreated;
    }

    public final ZonedDateTime getDateUpdated() {
        return this.dateUpdated;
    }

    public final URI getUrl() {
        return this.url;
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

        Session other = (Session) o;

        return (
            Objects.equals(sid, other.sid) &&
            Objects.equals(serviceSid, other.serviceSid) &&
            Objects.equals(accountSid, other.accountSid) &&
            Objects.equals(dateStarted, other.dateStarted) &&
            Objects.equals(dateEnded, other.dateEnded) &&
            Objects.equals(dateLastInteraction, other.dateLastInteraction) &&
            Objects.equals(dateExpiry, other.dateExpiry) &&
            Objects.equals(uniqueName, other.uniqueName) &&
            Objects.equals(status, other.status) &&
            Objects.equals(closedReason, other.closedReason) &&
            Objects.equals(ttl, other.ttl) &&
            Objects.equals(mode, other.mode) &&
            Objects.equals(dateCreated, other.dateCreated) &&
            Objects.equals(dateUpdated, other.dateUpdated) &&
            Objects.equals(url, other.url) &&
            Objects.equals(links, other.links)
        );
    }

    @Override
    public int hashCode() {
        return Objects.hash(
            sid,
            serviceSid,
            accountSid,
            dateStarted,
            dateEnded,
            dateLastInteraction,
            dateExpiry,
            uniqueName,
            status,
            closedReason,
            ttl,
            mode,
            dateCreated,
            dateUpdated,
            url,
            links
        );
    }

    public enum Status {
        OPEN("open"),
        IN_PROGRESS("in-progress"),
        CLOSED("closed"),
        FAILED("failed"),
        UNKNOWN("unknown");

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

    public enum Mode {
        MESSAGE_ONLY("message-only"),
        VOICE_ONLY("voice-only"),
        VOICE_AND_MESSAGE("voice-and-message");

        private final String value;

        private Mode(final String value) {
            this.value = value;
        }

        public String toString() {
            return value;
        }

        @JsonCreator
        public static Mode forValue(final String value) {
            return Promoter.enumFromString(value, Mode.values());
        }
    }
}
