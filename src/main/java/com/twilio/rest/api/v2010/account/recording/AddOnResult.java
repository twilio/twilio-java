/**
 * This code was generated by
 * \ / _    _  _|   _  _
 *  | (_)\/(_)(_|\/| |(/_  v1.0.0
 *       /       /
 */

package com.twilio.rest.api.v2010.account.recording;

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
import java.time.ZonedDateTime;
import java.util.Map;
import java.util.Objects;

@JsonIgnoreProperties(ignoreUnknown = true)
@ToString
public class AddOnResult extends Resource {
    private static final long serialVersionUID = 195304696893034L;

    public enum Status {
        CANCELED("canceled"),
        COMPLETED("completed"),
        DELETED("deleted"),
        FAILED("failed"),
        IN_PROGRESS("in-progress"),
        INIT("init"),
        PROCESSING("processing"),
        QUEUED("queued");

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

    /**
     * Create a AddOnResultFetcher to execute fetch.
     *
     * @param pathAccountSid The SID of the Account that created the resource to
     *                       fetch
     * @param pathReferenceSid The SID of the recording to which the result to
     *                         fetch belongs
     * @param pathSid The unique string that identifies the resource to fetch
     * @return AddOnResultFetcher capable of executing the fetch
     */
    public static AddOnResultFetcher fetcher(final String pathAccountSid,
                                             final String pathReferenceSid,
                                             final String pathSid) {
        return new AddOnResultFetcher(pathAccountSid, pathReferenceSid, pathSid);
    }

    /**
     * Create a AddOnResultFetcher to execute fetch.
     *
     * @param pathReferenceSid The SID of the recording to which the result to
     *                         fetch belongs
     * @param pathSid The unique string that identifies the resource to fetch
     * @return AddOnResultFetcher capable of executing the fetch
     */
    public static AddOnResultFetcher fetcher(final String pathReferenceSid,
                                             final String pathSid) {
        return new AddOnResultFetcher(pathReferenceSid, pathSid);
    }

    /**
     * Create a AddOnResultReader to execute read.
     *
     * @param pathAccountSid The SID of the Account that created the resources to
     *                       read
     * @param pathReferenceSid The SID of the recording to which the result to read
     *                         belongs
     * @return AddOnResultReader capable of executing the read
     */
    public static AddOnResultReader reader(final String pathAccountSid,
                                           final String pathReferenceSid) {
        return new AddOnResultReader(pathAccountSid, pathReferenceSid);
    }

    /**
     * Create a AddOnResultReader to execute read.
     *
     * @param pathReferenceSid The SID of the recording to which the result to read
     *                         belongs
     * @return AddOnResultReader capable of executing the read
     */
    public static AddOnResultReader reader(final String pathReferenceSid) {
        return new AddOnResultReader(pathReferenceSid);
    }

    /**
     * Create a AddOnResultDeleter to execute delete.
     *
     * @param pathAccountSid The SID of the Account that created the resources to
     *                       delete
     * @param pathReferenceSid The SID of the recording to which the result to
     *                         delete belongs
     * @param pathSid The unique string that identifies the resource to delete
     * @return AddOnResultDeleter capable of executing the delete
     */
    public static AddOnResultDeleter deleter(final String pathAccountSid,
                                             final String pathReferenceSid,
                                             final String pathSid) {
        return new AddOnResultDeleter(pathAccountSid, pathReferenceSid, pathSid);
    }

    /**
     * Create a AddOnResultDeleter to execute delete.
     *
     * @param pathReferenceSid The SID of the recording to which the result to
     *                         delete belongs
     * @param pathSid The unique string that identifies the resource to delete
     * @return AddOnResultDeleter capable of executing the delete
     */
    public static AddOnResultDeleter deleter(final String pathReferenceSid,
                                             final String pathSid) {
        return new AddOnResultDeleter(pathReferenceSid, pathSid);
    }

    /**
     * Converts a JSON String into a AddOnResult object using the provided
     * ObjectMapper.
     *
     * @param json Raw JSON String
     * @param objectMapper Jackson ObjectMapper
     * @return AddOnResult object represented by the provided JSON
     */
    public static AddOnResult fromJson(final String json, final ObjectMapper objectMapper) {
        // Convert all checked exceptions to Runtime
        try {
            return objectMapper.readValue(json, AddOnResult.class);
        } catch (final JsonMappingException | JsonParseException e) {
            throw new ApiException(e.getMessage(), e);
        } catch (final IOException e) {
            throw new ApiConnectionException(e.getMessage(), e);
        }
    }

    /**
     * Converts a JSON InputStream into a AddOnResult object using the provided
     * ObjectMapper.
     *
     * @param json Raw JSON InputStream
     * @param objectMapper Jackson ObjectMapper
     * @return AddOnResult object represented by the provided JSON
     */
    public static AddOnResult fromJson(final InputStream json, final ObjectMapper objectMapper) {
        // Convert all checked exceptions to Runtime
        try {
            return objectMapper.readValue(json, AddOnResult.class);
        } catch (final JsonMappingException | JsonParseException e) {
            throw new ApiException(e.getMessage(), e);
        } catch (final IOException e) {
            throw new ApiConnectionException(e.getMessage(), e);
        }
    }

    private final String sid;
    private final String accountSid;
    private final AddOnResult.Status status;
    private final String addOnSid;
    private final String addOnConfigurationSid;
    private final ZonedDateTime dateCreated;
    private final ZonedDateTime dateUpdated;
    private final ZonedDateTime dateCompleted;
    private final String referenceSid;
    private final Map<String, String> subresourceUris;

    @JsonCreator
    private AddOnResult(@JsonProperty("sid")
                        final String sid,
                        @JsonProperty("account_sid")
                        final String accountSid,
                        @JsonProperty("status")
                        final AddOnResult.Status status,
                        @JsonProperty("add_on_sid")
                        final String addOnSid,
                        @JsonProperty("add_on_configuration_sid")
                        final String addOnConfigurationSid,
                        @JsonProperty("date_created")
                        final String dateCreated,
                        @JsonProperty("date_updated")
                        final String dateUpdated,
                        @JsonProperty("date_completed")
                        final String dateCompleted,
                        @JsonProperty("reference_sid")
                        final String referenceSid,
                        @JsonProperty("subresource_uris")
                        final Map<String, String> subresourceUris) {
        this.sid = sid;
        this.accountSid = accountSid;
        this.status = status;
        this.addOnSid = addOnSid;
        this.addOnConfigurationSid = addOnConfigurationSid;
        this.dateCreated = DateConverter.rfc2822DateTimeFromString(dateCreated);
        this.dateUpdated = DateConverter.rfc2822DateTimeFromString(dateUpdated);
        this.dateCompleted = DateConverter.rfc2822DateTimeFromString(dateCompleted);
        this.referenceSid = referenceSid;
        this.subresourceUris = subresourceUris;
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
     * Returns The SID of the Account that created the resource.
     *
     * @return The SID of the Account that created the resource
     */
    public final String getAccountSid() {
        return this.accountSid;
    }

    /**
     * Returns The status of the result.
     *
     * @return The status of the result
     */
    public final AddOnResult.Status getStatus() {
        return this.status;
    }

    /**
     * Returns The SID of the Add-on to which the result belongs.
     *
     * @return The SID of the Add-on to which the result belongs
     */
    public final String getAddOnSid() {
        return this.addOnSid;
    }

    /**
     * Returns The SID of the Add-on configuration.
     *
     * @return The SID of the Add-on configuration
     */
    public final String getAddOnConfigurationSid() {
        return this.addOnConfigurationSid;
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
     * Returns The date and time in GMT that the result was completed.
     *
     * @return The date and time in GMT that the result was completed
     */
    public final ZonedDateTime getDateCompleted() {
        return this.dateCompleted;
    }

    /**
     * Returns The SID of the recording to which the AddOnResult resource belongs.
     *
     * @return The SID of the recording to which the AddOnResult resource belongs
     */
    public final String getReferenceSid() {
        return this.referenceSid;
    }

    /**
     * Returns A list of related resources identified by their relative URIs.
     *
     * @return A list of related resources identified by their relative URIs
     */
    public final Map<String, String> getSubresourceUris() {
        return this.subresourceUris;
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }

        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        AddOnResult other = (AddOnResult) o;

        return Objects.equals(sid, other.sid) &&
               Objects.equals(accountSid, other.accountSid) &&
               Objects.equals(status, other.status) &&
               Objects.equals(addOnSid, other.addOnSid) &&
               Objects.equals(addOnConfigurationSid, other.addOnConfigurationSid) &&
               Objects.equals(dateCreated, other.dateCreated) &&
               Objects.equals(dateUpdated, other.dateUpdated) &&
               Objects.equals(dateCompleted, other.dateCompleted) &&
               Objects.equals(referenceSid, other.referenceSid) &&
               Objects.equals(subresourceUris, other.subresourceUris);
    }

    @Override
    public int hashCode() {
        return Objects.hash(sid,
                            accountSid,
                            status,
                            addOnSid,
                            addOnConfigurationSid,
                            dateCreated,
                            dateUpdated,
                            dateCompleted,
                            referenceSid,
                            subresourceUris);
    }
}