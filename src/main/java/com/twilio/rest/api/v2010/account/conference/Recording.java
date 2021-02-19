/**
 * This code was generated by
 * \ / _    _  _|   _  _
 *  | (_)\/(_)(_|\/| |(/_  v1.0.0
 *       /       /
 */

package com.twilio.rest.api.v2010.account.conference;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.twilio.base.Resource;
import com.twilio.converter.Converter;
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
import java.math.BigDecimal;
import java.time.ZonedDateTime;
import java.util.Currency;
import java.util.Map;
import java.util.Objects;

@JsonIgnoreProperties(ignoreUnknown = true)
@ToString
public class Recording extends Resource {
    private static final long serialVersionUID = 119371438714806L;

    public enum Status {
        IN_PROGRESS("in-progress"),
        PAUSED("paused"),
        STOPPED("stopped"),
        PROCESSING("processing"),
        COMPLETED("completed"),
        ABSENT("absent");

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

    public enum Source {
        DIALVERB("DialVerb"),
        CONFERENCE("Conference"),
        OUTBOUNDAPI("OutboundAPI"),
        TRUNKING("Trunking"),
        RECORDVERB("RecordVerb"),
        STARTCALLRECORDINGAPI("StartCallRecordingAPI"),
        STARTCONFERENCERECORDINGAPI("StartConferenceRecordingAPI");

        private final String value;

        private Source(final String value) {
            this.value = value;
        }

        public String toString() {
            return value;
        }

        /**
         * Generate a Source from a string.
         * @param value string value
         * @return generated Source
         */
        @JsonCreator
        public static Source forValue(final String value) {
            return Promoter.enumFromString(value, Source.values());
        }
    }

    /**
     * Create a RecordingUpdater to execute update.
     *
     * @param pathAccountSid The SID of the Account that created the resource to
     *                       update
     * @param pathConferenceSid Update by unique Conference SID for the recording
     * @param pathSid The unique string that identifies the resource
     * @param status The new status of the recording
     * @return RecordingUpdater capable of executing the update
     */
    public static RecordingUpdater updater(final String pathAccountSid,
                                           final String pathConferenceSid,
                                           final String pathSid,
                                           final Recording.Status status) {
        return new RecordingUpdater(pathAccountSid, pathConferenceSid, pathSid, status);
    }

    /**
     * Create a RecordingUpdater to execute update.
     *
     * @param pathConferenceSid Update by unique Conference SID for the recording
     * @param pathSid The unique string that identifies the resource
     * @param status The new status of the recording
     * @return RecordingUpdater capable of executing the update
     */
    public static RecordingUpdater updater(final String pathConferenceSid,
                                           final String pathSid,
                                           final Recording.Status status) {
        return new RecordingUpdater(pathConferenceSid, pathSid, status);
    }

    /**
     * Create a RecordingFetcher to execute fetch.
     *
     * @param pathAccountSid The SID of the Account that created the resource to
     *                       fetch
     * @param pathConferenceSid Fetch by unique Conference SID for the recording
     * @param pathSid The unique string that identifies the resource
     * @return RecordingFetcher capable of executing the fetch
     */
    public static RecordingFetcher fetcher(final String pathAccountSid,
                                           final String pathConferenceSid,
                                           final String pathSid) {
        return new RecordingFetcher(pathAccountSid, pathConferenceSid, pathSid);
    }

    /**
     * Create a RecordingFetcher to execute fetch.
     *
     * @param pathConferenceSid Fetch by unique Conference SID for the recording
     * @param pathSid The unique string that identifies the resource
     * @return RecordingFetcher capable of executing the fetch
     */
    public static RecordingFetcher fetcher(final String pathConferenceSid,
                                           final String pathSid) {
        return new RecordingFetcher(pathConferenceSid, pathSid);
    }

    /**
     * Create a RecordingDeleter to execute delete.
     *
     * @param pathAccountSid The SID of the Account that created the resources to
     *                       delete
     * @param pathConferenceSid Delete by the recording's conference SID
     * @param pathSid The unique string that identifies the resource
     * @return RecordingDeleter capable of executing the delete
     */
    public static RecordingDeleter deleter(final String pathAccountSid,
                                           final String pathConferenceSid,
                                           final String pathSid) {
        return new RecordingDeleter(pathAccountSid, pathConferenceSid, pathSid);
    }

    /**
     * Create a RecordingDeleter to execute delete.
     *
     * @param pathConferenceSid Delete by the recording's conference SID
     * @param pathSid The unique string that identifies the resource
     * @return RecordingDeleter capable of executing the delete
     */
    public static RecordingDeleter deleter(final String pathConferenceSid,
                                           final String pathSid) {
        return new RecordingDeleter(pathConferenceSid, pathSid);
    }

    /**
     * Create a RecordingReader to execute read.
     *
     * @param pathAccountSid The SID of the Account that created the resources to
     *                       read
     * @param pathConferenceSid Read by unique Conference SID for the recording
     * @return RecordingReader capable of executing the read
     */
    public static RecordingReader reader(final String pathAccountSid,
                                         final String pathConferenceSid) {
        return new RecordingReader(pathAccountSid, pathConferenceSid);
    }

    /**
     * Create a RecordingReader to execute read.
     *
     * @param pathConferenceSid Read by unique Conference SID for the recording
     * @return RecordingReader capable of executing the read
     */
    public static RecordingReader reader(final String pathConferenceSid) {
        return new RecordingReader(pathConferenceSid);
    }

    /**
     * Converts a JSON String into a Recording object using the provided
     * ObjectMapper.
     *
     * @param json Raw JSON String
     * @param objectMapper Jackson ObjectMapper
     * @return Recording object represented by the provided JSON
     */
    public static Recording fromJson(final String json, final ObjectMapper objectMapper) {
        // Convert all checked exceptions to Runtime
        try {
            return objectMapper.readValue(json, Recording.class);
        } catch (final JsonMappingException | JsonParseException e) {
            throw new ApiException(e.getMessage(), e);
        } catch (final IOException e) {
            throw new ApiConnectionException(e.getMessage(), e);
        }
    }

    /**
     * Converts a JSON InputStream into a Recording object using the provided
     * ObjectMapper.
     *
     * @param json Raw JSON InputStream
     * @param objectMapper Jackson ObjectMapper
     * @return Recording object represented by the provided JSON
     */
    public static Recording fromJson(final InputStream json, final ObjectMapper objectMapper) {
        // Convert all checked exceptions to Runtime
        try {
            return objectMapper.readValue(json, Recording.class);
        } catch (final JsonMappingException | JsonParseException e) {
            throw new ApiException(e.getMessage(), e);
        } catch (final IOException e) {
            throw new ApiConnectionException(e.getMessage(), e);
        }
    }

    private final String accountSid;
    private final String apiVersion;
    private final String callSid;
    private final String conferenceSid;
    private final ZonedDateTime dateCreated;
    private final ZonedDateTime dateUpdated;
    private final ZonedDateTime startTime;
    private final String duration;
    private final String sid;
    private final BigDecimal price;
    private final Currency priceUnit;
    private final Recording.Status status;
    private final Integer channels;
    private final Recording.Source source;
    private final Integer errorCode;
    private final Map<String, Object> encryptionDetails;
    private final String uri;

    @JsonCreator
    private Recording(@JsonProperty("account_sid")
                      final String accountSid,
                      @JsonProperty("api_version")
                      final String apiVersion,
                      @JsonProperty("call_sid")
                      final String callSid,
                      @JsonProperty("conference_sid")
                      final String conferenceSid,
                      @JsonProperty("date_created")
                      final String dateCreated,
                      @JsonProperty("date_updated")
                      final String dateUpdated,
                      @JsonProperty("start_time")
                      final String startTime,
                      @JsonProperty("duration")
                      final String duration,
                      @JsonProperty("sid")
                      final String sid,
                      @JsonProperty("price")
                      final BigDecimal price,
                      @JsonProperty("price_unit")
                      @JsonDeserialize(using = com.twilio.converter.CurrencyDeserializer.class)
                      final Currency priceUnit,
                      @JsonProperty("status")
                      final Recording.Status status,
                      @JsonProperty("channels")
                      final Integer channels,
                      @JsonProperty("source")
                      final Recording.Source source,
                      @JsonProperty("error_code")
                      final Integer errorCode,
                      @JsonProperty("encryption_details")
                      final Map<String, Object> encryptionDetails,
                      @JsonProperty("uri")
                      final String uri) {
        this.accountSid = accountSid;
        this.apiVersion = apiVersion;
        this.callSid = callSid;
        this.conferenceSid = conferenceSid;
        this.dateCreated = DateConverter.rfc2822DateTimeFromString(dateCreated);
        this.dateUpdated = DateConverter.rfc2822DateTimeFromString(dateUpdated);
        this.startTime = DateConverter.rfc2822DateTimeFromString(startTime);
        this.duration = duration;
        this.sid = sid;
        this.price = price;
        this.priceUnit = priceUnit;
        this.status = status;
        this.channels = channels;
        this.source = source;
        this.errorCode = errorCode;
        this.encryptionDetails = encryptionDetails;
        this.uri = uri;
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
     * Returns The API version used to create the recording.
     *
     * @return The API version used to create the recording
     */
    public final String getApiVersion() {
        return this.apiVersion;
    }

    /**
     * Returns The SID of the Call the resource is associated with.
     *
     * @return The SID of the Call the resource is associated with
     */
    public final String getCallSid() {
        return this.callSid;
    }

    /**
     * Returns The Conference SID that identifies the conference associated with the
     * recording.
     *
     * @return The Conference SID that identifies the conference associated with
     *         the recording
     */
    public final String getConferenceSid() {
        return this.conferenceSid;
    }

    /**
     * Returns The RFC 2822 date and time in GMT that the resource was created.
     *
     * @return The RFC 2822 date and time in GMT that the resource was created
     */
    public final ZonedDateTime getDateCreated() {
        return this.dateCreated;
    }

    /**
     * Returns The RFC 2822 date and time in GMT that the resource was last updated.
     *
     * @return The RFC 2822 date and time in GMT that the resource was last updated
     */
    public final ZonedDateTime getDateUpdated() {
        return this.dateUpdated;
    }

    /**
     * Returns The start time of the recording, given in RFC 2822 format.
     *
     * @return The start time of the recording, given in RFC 2822 format
     */
    public final ZonedDateTime getStartTime() {
        return this.startTime;
    }

    /**
     * Returns The length of the recording in seconds.
     *
     * @return The length of the recording in seconds
     */
    public final String getDuration() {
        return this.duration;
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
     * Returns The one-time cost of creating the recording..
     *
     * @return The one-time cost of creating the recording.
     */
    public final BigDecimal getPrice() {
        return this.price;
    }

    /**
     * Returns The currency used in the price property..
     *
     * @return The currency used in the price property.
     */
    public final Currency getPriceUnit() {
        return this.priceUnit;
    }

    /**
     * Returns The status of the recording.
     *
     * @return The status of the recording
     */
    public final Recording.Status getStatus() {
        return this.status;
    }

    /**
     * Returns The number of channels in the final recording file as an integer.
     *
     * @return The number of channels in the final recording file as an integer
     */
    public final Integer getChannels() {
        return this.channels;
    }

    /**
     * Returns How the recording was created.
     *
     * @return How the recording was created
     */
    public final Recording.Source getSource() {
        return this.source;
    }

    /**
     * Returns More information about why the recording is missing, if status is
     * `absent`..
     *
     * @return More information about why the recording is missing, if status is
     *         `absent`.
     */
    public final Integer getErrorCode() {
        return this.errorCode;
    }

    /**
     * Returns How to decrypt the recording..
     *
     * @return How to decrypt the recording.
     */
    public final Map<String, Object> getEncryptionDetails() {
        return this.encryptionDetails;
    }

    /**
     * Returns The URI of the resource, relative to `https://api.twilio.com`.
     *
     * @return The URI of the resource, relative to `https://api.twilio.com`
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

        Recording other = (Recording) o;

        return Objects.equals(accountSid, other.accountSid) &&
               Objects.equals(apiVersion, other.apiVersion) &&
               Objects.equals(callSid, other.callSid) &&
               Objects.equals(conferenceSid, other.conferenceSid) &&
               Objects.equals(dateCreated, other.dateCreated) &&
               Objects.equals(dateUpdated, other.dateUpdated) &&
               Objects.equals(startTime, other.startTime) &&
               Objects.equals(duration, other.duration) &&
               Objects.equals(sid, other.sid) &&
               Objects.equals(price, other.price) &&
               Objects.equals(priceUnit, other.priceUnit) &&
               Objects.equals(status, other.status) &&
               Objects.equals(channels, other.channels) &&
               Objects.equals(source, other.source) &&
               Objects.equals(errorCode, other.errorCode) &&
               Objects.equals(encryptionDetails, other.encryptionDetails) &&
               Objects.equals(uri, other.uri);
    }

    @Override
    public int hashCode() {
        return Objects.hash(accountSid,
                            apiVersion,
                            callSid,
                            conferenceSid,
                            dateCreated,
                            dateUpdated,
                            startTime,
                            duration,
                            sid,
                            price,
                            priceUnit,
                            status,
                            channels,
                            source,
                            errorCode,
                            encryptionDetails,
                            uri);
    }
}