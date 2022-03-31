/**
 * This code was generated by
 * \ / _    _  _|   _  _
 *  | (_)\/(_)(_|\/| |(/_  v1.0.0
 *       /       /
 */

package com.twilio.rest.numbers.v2.regulatorycompliance.bundle;

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

/**
 * PLEASE NOTE that this class contains beta products that are subject to
 * change. Use them with caution.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
@ToString
public class ReplaceItems extends Resource {
    private static final long serialVersionUID = 208092719239092L;

    public enum Status {
        DRAFT("draft"),
        PENDING_REVIEW("pending-review"),
        IN_REVIEW("in-review"),
        TWILIO_REJECTED("twilio-rejected"),
        TWILIO_APPROVED("twilio-approved"),
        PROVISIONALLY_APPROVED("provisionally-approved");

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

    public enum EndUserType {
        INDIVIDUAL("individual"),
        BUSINESS("business");

        private final String value;

        private EndUserType(final String value) {
            this.value = value;
        }

        public String toString() {
            return value;
        }

        /**
         * Generate a EndUserType from a string.
         * @param value string value
         * @return generated EndUserType
         */
        @JsonCreator
        public static EndUserType forValue(final String value) {
            return Promoter.enumFromString(value, EndUserType.values());
        }
    }

    /**
     * Create a ReplaceItemsCreator to execute create.
     *
     * @param pathBundleSid The unique string that identifies the Bundle where the
     *                      item assignments are going to be replaced
     * @param fromBundleSid The source bundle sid to copy the item assignments from
     * @return ReplaceItemsCreator capable of executing the create
     */
    public static ReplaceItemsCreator creator(final String pathBundleSid,
                                              final String fromBundleSid) {
        return new ReplaceItemsCreator(pathBundleSid, fromBundleSid);
    }

    /**
     * Converts a JSON String into a ReplaceItems object using the provided
     * ObjectMapper.
     *
     * @param json Raw JSON String
     * @param objectMapper Jackson ObjectMapper
     * @return ReplaceItems object represented by the provided JSON
     */
    public static ReplaceItems fromJson(final String json, final ObjectMapper objectMapper) {
        // Convert all checked exceptions to Runtime
        try {
            return objectMapper.readValue(json, ReplaceItems.class);
        } catch (final JsonMappingException | JsonParseException e) {
            throw new ApiException(e.getMessage(), e);
        } catch (final IOException e) {
            throw new ApiConnectionException(e.getMessage(), e);
        }
    }

    /**
     * Converts a JSON InputStream into a ReplaceItems object using the provided
     * ObjectMapper.
     *
     * @param json Raw JSON InputStream
     * @param objectMapper Jackson ObjectMapper
     * @return ReplaceItems object represented by the provided JSON
     */
    public static ReplaceItems fromJson(final InputStream json, final ObjectMapper objectMapper) {
        // Convert all checked exceptions to Runtime
        try {
            return objectMapper.readValue(json, ReplaceItems.class);
        } catch (final JsonMappingException | JsonParseException e) {
            throw new ApiException(e.getMessage(), e);
        } catch (final IOException e) {
            throw new ApiConnectionException(e.getMessage(), e);
        }
    }

    private final String sid;
    private final String accountSid;
    private final String regulationSid;
    private final String friendlyName;
    private final ReplaceItems.Status status;
    private final ZonedDateTime validUntil;
    private final String email;
    private final URI statusCallback;
    private final ZonedDateTime dateCreated;
    private final ZonedDateTime dateUpdated;

    @JsonCreator
    private ReplaceItems(@JsonProperty("sid")
                         final String sid,
                         @JsonProperty("account_sid")
                         final String accountSid,
                         @JsonProperty("regulation_sid")
                         final String regulationSid,
                         @JsonProperty("friendly_name")
                         final String friendlyName,
                         @JsonProperty("status")
                         final ReplaceItems.Status status,
                         @JsonProperty("valid_until")
                         final String validUntil,
                         @JsonProperty("email")
                         final String email,
                         @JsonProperty("status_callback")
                         final URI statusCallback,
                         @JsonProperty("date_created")
                         final String dateCreated,
                         @JsonProperty("date_updated")
                         final String dateUpdated) {
        this.sid = sid;
        this.accountSid = accountSid;
        this.regulationSid = regulationSid;
        this.friendlyName = friendlyName;
        this.status = status;
        this.validUntil = DateConverter.iso8601DateTimeFromString(validUntil);
        this.email = email;
        this.statusCallback = statusCallback;
        this.dateCreated = DateConverter.iso8601DateTimeFromString(dateCreated);
        this.dateUpdated = DateConverter.iso8601DateTimeFromString(dateUpdated);
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
     * Returns The unique string of a regulation.
     *
     * @return The unique string of a regulation
     */
    public final String getRegulationSid() {
        return this.regulationSid;
    }

    /**
     * Returns The string that you assigned to describe the resource.
     *
     * @return The string that you assigned to describe the resource
     */
    public final String getFriendlyName() {
        return this.friendlyName;
    }

    /**
     * Returns The verification status of the Bundle resource.
     *
     * @return The verification status of the Bundle resource
     */
    public final ReplaceItems.Status getStatus() {
        return this.status;
    }

    /**
     * Returns The ISO 8601 date and time in GMT when the resource will be valid
     * until.
     *
     * @return The ISO 8601 date and time in GMT when the resource will be valid
     *         until
     */
    public final ZonedDateTime getValidUntil() {
        return this.validUntil;
    }

    /**
     * Returns The email address.
     *
     * @return The email address
     */
    public final String getEmail() {
        return this.email;
    }

    /**
     * Returns The URL we call to inform your application of status changes.
     *
     * @return The URL we call to inform your application of status changes
     */
    public final URI getStatusCallback() {
        return this.statusCallback;
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

    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }

        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        ReplaceItems other = (ReplaceItems) o;

        return Objects.equals(sid, other.sid) &&
               Objects.equals(accountSid, other.accountSid) &&
               Objects.equals(regulationSid, other.regulationSid) &&
               Objects.equals(friendlyName, other.friendlyName) &&
               Objects.equals(status, other.status) &&
               Objects.equals(validUntil, other.validUntil) &&
               Objects.equals(email, other.email) &&
               Objects.equals(statusCallback, other.statusCallback) &&
               Objects.equals(dateCreated, other.dateCreated) &&
               Objects.equals(dateUpdated, other.dateUpdated);
    }

    @Override
    public int hashCode() {
        return Objects.hash(sid,
                            accountSid,
                            regulationSid,
                            friendlyName,
                            status,
                            validUntil,
                            email,
                            statusCallback,
                            dateCreated,
                            dateUpdated);
    }
}