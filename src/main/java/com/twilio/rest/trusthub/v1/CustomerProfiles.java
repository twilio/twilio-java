/*
 * This code was generated by
 * ___ _ _ _ _ _    _ ____    ____ ____ _    ____ ____ _  _ ____ ____ ____ ___ __   __
 *  |  | | | | |    | |  | __ |  | |__| | __ | __ |___ |\ | |___ |__/ |__|  | |  | |__/
 *  |  |_|_| | |___ | |__|    |__| |  | |    |__] |___ | \| |___ |  \ |  |  | |__| |  \
 *
 * Twilio - Trusthub
 * This is the public Twilio REST API.
 *
 * NOTE: This class is auto generated by OpenAPI Generator.
 * https://openapi-generator.tech
 * Do not edit the class manually.
 */

package com.twilio.rest.trusthub.v1;

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
import java.util.List;
import java.util.Map;
import java.util.Map;
import java.util.Objects;
import lombok.ToString;
import lombok.ToString;

@JsonIgnoreProperties(ignoreUnknown = true)
@ToString
public class CustomerProfiles extends Resource {

    private static final long serialVersionUID = 258820529618531L;

    public static CustomerProfilesCreator creator(
        final String friendlyName,
        final String email,
        final String policySid
    ) {
        return new CustomerProfilesCreator(friendlyName, email, policySid);
    }

    public static CustomerProfilesDeleter deleter(final String pathSid) {
        return new CustomerProfilesDeleter(pathSid);
    }

    public static CustomerProfilesFetcher fetcher(final String pathSid) {
        return new CustomerProfilesFetcher(pathSid);
    }

    public static CustomerProfilesReader reader() {
        return new CustomerProfilesReader();
    }

    public static CustomerProfilesUpdater updater(final String pathSid) {
        return new CustomerProfilesUpdater(pathSid);
    }

    /**
     * Converts a JSON String into a CustomerProfiles object using the provided ObjectMapper.
     *
     * @param json Raw JSON String
     * @param objectMapper Jackson ObjectMapper
     * @return CustomerProfiles object represented by the provided JSON
     */
    public static CustomerProfiles fromJson(
        final String json,
        final ObjectMapper objectMapper
    ) {
        // Convert all checked exceptions to Runtime
        try {
            return objectMapper.readValue(json, CustomerProfiles.class);
        } catch (final JsonMappingException | JsonParseException e) {
            throw new ApiException(e.getMessage(), e);
        } catch (final IOException e) {
            throw new ApiConnectionException(e.getMessage(), e);
        }
    }

    /**
     * Converts a JSON InputStream into a CustomerProfiles object using the provided
     * ObjectMapper.
     *
     * @param json Raw JSON InputStream
     * @param objectMapper Jackson ObjectMapper
     * @return CustomerProfiles object represented by the provided JSON
     */
    public static CustomerProfiles fromJson(
        final InputStream json,
        final ObjectMapper objectMapper
    ) {
        // Convert all checked exceptions to Runtime
        try {
            return objectMapper.readValue(json, CustomerProfiles.class);
        } catch (final JsonMappingException | JsonParseException e) {
            throw new ApiException(e.getMessage(), e);
        } catch (final IOException e) {
            throw new ApiConnectionException(e.getMessage(), e);
        }
    }

    private final String sid;
    private final String accountSid;
    private final String policySid;
    private final String friendlyName;
    private final CustomerProfiles.Status status;
    private final ZonedDateTime validUntil;
    private final String email;
    private final URI statusCallback;
    private final ZonedDateTime dateCreated;
    private final ZonedDateTime dateUpdated;
    private final URI url;
    private final Map<String, String> links;
    private final List<Map<String, Object>> errors;

    @JsonCreator
    private CustomerProfiles(
        @JsonProperty("sid") final String sid,
        @JsonProperty("account_sid") final String accountSid,
        @JsonProperty("policy_sid") final String policySid,
        @JsonProperty("friendly_name") final String friendlyName,
        @JsonProperty("status") final CustomerProfiles.Status status,
        @JsonProperty("valid_until") final String validUntil,
        @JsonProperty("email") final String email,
        @JsonProperty("status_callback") final URI statusCallback,
        @JsonProperty("date_created") final String dateCreated,
        @JsonProperty("date_updated") final String dateUpdated,
        @JsonProperty("url") final URI url,
        @JsonProperty("links") final Map<String, String> links,
        @JsonProperty("errors") final List<Map<String, Object>> errors
    ) {
        this.sid = sid;
        this.accountSid = accountSid;
        this.policySid = policySid;
        this.friendlyName = friendlyName;
        this.status = status;
        this.validUntil = DateConverter.iso8601DateTimeFromString(validUntil);
        this.email = email;
        this.statusCallback = statusCallback;
        this.dateCreated = DateConverter.iso8601DateTimeFromString(dateCreated);
        this.dateUpdated = DateConverter.iso8601DateTimeFromString(dateUpdated);
        this.url = url;
        this.links = links;
        this.errors = errors;
    }

    public final String getSid() {
        return this.sid;
    }

    public final String getAccountSid() {
        return this.accountSid;
    }

    public final String getPolicySid() {
        return this.policySid;
    }

    public final String getFriendlyName() {
        return this.friendlyName;
    }

    public final CustomerProfiles.Status getStatus() {
        return this.status;
    }

    public final ZonedDateTime getValidUntil() {
        return this.validUntil;
    }

    public final String getEmail() {
        return this.email;
    }

    public final URI getStatusCallback() {
        return this.statusCallback;
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

    public final List<Map<String, Object>> getErrors() {
        return this.errors;
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }

        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        CustomerProfiles other = (CustomerProfiles) o;

        return (
            Objects.equals(sid, other.sid) &&
            Objects.equals(accountSid, other.accountSid) &&
            Objects.equals(policySid, other.policySid) &&
            Objects.equals(friendlyName, other.friendlyName) &&
            Objects.equals(status, other.status) &&
            Objects.equals(validUntil, other.validUntil) &&
            Objects.equals(email, other.email) &&
            Objects.equals(statusCallback, other.statusCallback) &&
            Objects.equals(dateCreated, other.dateCreated) &&
            Objects.equals(dateUpdated, other.dateUpdated) &&
            Objects.equals(url, other.url) &&
            Objects.equals(links, other.links) &&
            Objects.equals(errors, other.errors)
        );
    }

    @Override
    public int hashCode() {
        return Objects.hash(
            sid,
            accountSid,
            policySid,
            friendlyName,
            status,
            validUntil,
            email,
            statusCallback,
            dateCreated,
            dateUpdated,
            url,
            links,
            errors
        );
    }

    public enum Status {
        DRAFT("draft"),
        PENDING_REVIEW("pending-review"),
        IN_REVIEW("in-review"),
        TWILIO_REJECTED("twilio-rejected"),
        TWILIO_APPROVED("twilio-approved");

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
}
