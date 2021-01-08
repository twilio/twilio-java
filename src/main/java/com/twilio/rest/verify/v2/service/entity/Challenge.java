/**
 * This code was generated by
 * \ / _    _  _|   _  _
 *  | (_)\/(_)(_|\/| |(/_  v1.0.0
 *       /       /
 */

package com.twilio.rest.verify.v2.service.entity;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
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
public class Challenge extends Resource {
    private static final long serialVersionUID = 252131191620543L;

    public enum ChallengeStatuses {
        PENDING("pending"),
        EXPIRED("expired"),
        APPROVED("approved"),
        DENIED("denied");

        private final String value;

        private ChallengeStatuses(final String value) {
            this.value = value;
        }

        public String toString() {
            return value;
        }

        /**
         * Generate a ChallengeStatuses from a string.
         * @param value string value
         * @return generated ChallengeStatuses
         */
        @JsonCreator
        public static ChallengeStatuses forValue(final String value) {
            return Promoter.enumFromString(value, ChallengeStatuses.values());
        }
    }

    public enum ChallengeReasons {
        NONE("none"),
        NOT_NEEDED("not_needed"),
        NOT_REQUESTED("not_requested");

        private final String value;

        private ChallengeReasons(final String value) {
            this.value = value;
        }

        public String toString() {
            return value;
        }

        /**
         * Generate a ChallengeReasons from a string.
         * @param value string value
         * @return generated ChallengeReasons
         */
        @JsonCreator
        public static ChallengeReasons forValue(final String value) {
            return Promoter.enumFromString(value, ChallengeReasons.values());
        }
    }

    public enum FactorTypes {
        PUSH("push");

        private final String value;

        private FactorTypes(final String value) {
            this.value = value;
        }

        public String toString() {
            return value;
        }

        /**
         * Generate a FactorTypes from a string.
         * @param value string value
         * @return generated FactorTypes
         */
        @JsonCreator
        public static FactorTypes forValue(final String value) {
            return Promoter.enumFromString(value, FactorTypes.values());
        }
    }

    /**
     * Create a ChallengeCreator to execute create.
     *
     * @param pathServiceSid Service Sid.
     * @param pathIdentity Unique external identifier of the Entity
     * @param factorSid Factor Sid.
     * @return ChallengeCreator capable of executing the create
     */
    public static ChallengeCreator creator(final String pathServiceSid,
                                           final String pathIdentity,
                                           final String factorSid) {
        return new ChallengeCreator(pathServiceSid, pathIdentity, factorSid);
    }

    /**
     * Create a ChallengeFetcher to execute fetch.
     *
     * @param pathServiceSid Service Sid.
     * @param pathIdentity Unique external identifier of the Entity
     * @param pathSid A string that uniquely identifies this Challenge.
     * @return ChallengeFetcher capable of executing the fetch
     */
    public static ChallengeFetcher fetcher(final String pathServiceSid,
                                           final String pathIdentity,
                                           final String pathSid) {
        return new ChallengeFetcher(pathServiceSid, pathIdentity, pathSid);
    }

    /**
     * Create a ChallengeReader to execute read.
     *
     * @param pathServiceSid Service Sid.
     * @param pathIdentity Unique external identifier of the Entity
     * @return ChallengeReader capable of executing the read
     */
    public static ChallengeReader reader(final String pathServiceSid,
                                         final String pathIdentity) {
        return new ChallengeReader(pathServiceSid, pathIdentity);
    }

    /**
     * Create a ChallengeUpdater to execute update.
     *
     * @param pathServiceSid Service Sid.
     * @param pathIdentity Unique external identifier of the Entity
     * @param pathSid A string that uniquely identifies this Challenge.
     * @return ChallengeUpdater capable of executing the update
     */
    public static ChallengeUpdater updater(final String pathServiceSid,
                                           final String pathIdentity,
                                           final String pathSid) {
        return new ChallengeUpdater(pathServiceSid, pathIdentity, pathSid);
    }

    /**
     * Converts a JSON String into a Challenge object using the provided
     * ObjectMapper.
     *
     * @param json Raw JSON String
     * @param objectMapper Jackson ObjectMapper
     * @return Challenge object represented by the provided JSON
     */
    public static Challenge fromJson(final String json, final ObjectMapper objectMapper) {
        // Convert all checked exceptions to Runtime
        try {
            return objectMapper.readValue(json, Challenge.class);
        } catch (final JsonMappingException | JsonParseException e) {
            throw new ApiException(e.getMessage(), e);
        } catch (final IOException e) {
            throw new ApiConnectionException(e.getMessage(), e);
        }
    }

    /**
     * Converts a JSON InputStream into a Challenge object using the provided
     * ObjectMapper.
     *
     * @param json Raw JSON InputStream
     * @param objectMapper Jackson ObjectMapper
     * @return Challenge object represented by the provided JSON
     */
    public static Challenge fromJson(final InputStream json, final ObjectMapper objectMapper) {
        // Convert all checked exceptions to Runtime
        try {
            return objectMapper.readValue(json, Challenge.class);
        } catch (final JsonMappingException | JsonParseException e) {
            throw new ApiException(e.getMessage(), e);
        } catch (final IOException e) {
            throw new ApiConnectionException(e.getMessage(), e);
        }
    }

    private final String sid;
    private final String accountSid;
    private final String serviceSid;
    private final String entitySid;
    private final String identity;
    private final String factorSid;
    private final ZonedDateTime dateCreated;
    private final ZonedDateTime dateUpdated;
    private final ZonedDateTime dateResponded;
    private final ZonedDateTime expirationDate;
    private final Challenge.ChallengeStatuses status;
    private final Challenge.ChallengeReasons respondedReason;
    private final Map<String, Object> details;
    private final Map<String, Object> hiddenDetails;
    private final Challenge.FactorTypes factorType;
    private final URI url;

    @JsonCreator
    private Challenge(@JsonProperty("sid")
                      final String sid,
                      @JsonProperty("account_sid")
                      final String accountSid,
                      @JsonProperty("service_sid")
                      final String serviceSid,
                      @JsonProperty("entity_sid")
                      final String entitySid,
                      @JsonProperty("identity")
                      final String identity,
                      @JsonProperty("factor_sid")
                      final String factorSid,
                      @JsonProperty("date_created")
                      final String dateCreated,
                      @JsonProperty("date_updated")
                      final String dateUpdated,
                      @JsonProperty("date_responded")
                      final String dateResponded,
                      @JsonProperty("expiration_date")
                      final String expirationDate,
                      @JsonProperty("status")
                      final Challenge.ChallengeStatuses status,
                      @JsonProperty("responded_reason")
                      final Challenge.ChallengeReasons respondedReason,
                      @JsonProperty("details")
                      final Map<String, Object> details,
                      @JsonProperty("hidden_details")
                      final Map<String, Object> hiddenDetails,
                      @JsonProperty("factor_type")
                      final Challenge.FactorTypes factorType,
                      @JsonProperty("url")
                      final URI url) {
        this.sid = sid;
        this.accountSid = accountSid;
        this.serviceSid = serviceSid;
        this.entitySid = entitySid;
        this.identity = identity;
        this.factorSid = factorSid;
        this.dateCreated = DateConverter.iso8601DateTimeFromString(dateCreated);
        this.dateUpdated = DateConverter.iso8601DateTimeFromString(dateUpdated);
        this.dateResponded = DateConverter.iso8601DateTimeFromString(dateResponded);
        this.expirationDate = DateConverter.iso8601DateTimeFromString(expirationDate);
        this.status = status;
        this.respondedReason = respondedReason;
        this.details = details;
        this.hiddenDetails = hiddenDetails;
        this.factorType = factorType;
        this.url = url;
    }

    /**
     * Returns A string that uniquely identifies this Challenge..
     *
     * @return A string that uniquely identifies this Challenge.
     */
    public final String getSid() {
        return this.sid;
    }

    /**
     * Returns Account Sid..
     *
     * @return Account Sid.
     */
    public final String getAccountSid() {
        return this.accountSid;
    }

    /**
     * Returns Service Sid..
     *
     * @return Service Sid.
     */
    public final String getServiceSid() {
        return this.serviceSid;
    }

    /**
     * Returns Entity Sid..
     *
     * @return Entity Sid.
     */
    public final String getEntitySid() {
        return this.entitySid;
    }

    /**
     * Returns Unique external identifier of the Entity.
     *
     * @return Unique external identifier of the Entity
     */
    public final String getIdentity() {
        return this.identity;
    }

    /**
     * Returns Factor Sid..
     *
     * @return Factor Sid.
     */
    public final String getFactorSid() {
        return this.factorSid;
    }

    /**
     * Returns The date this Challenge was created.
     *
     * @return The date this Challenge was created
     */
    public final ZonedDateTime getDateCreated() {
        return this.dateCreated;
    }

    /**
     * Returns The date this Challenge was updated.
     *
     * @return The date this Challenge was updated
     */
    public final ZonedDateTime getDateUpdated() {
        return this.dateUpdated;
    }

    /**
     * Returns The date this Challenge was responded.
     *
     * @return The date this Challenge was responded
     */
    public final ZonedDateTime getDateResponded() {
        return this.dateResponded;
    }

    /**
     * Returns The date-time when this Challenge expires.
     *
     * @return The date-time when this Challenge expires
     */
    public final ZonedDateTime getExpirationDate() {
        return this.expirationDate;
    }

    /**
     * Returns The Status of this Challenge.
     *
     * @return The Status of this Challenge
     */
    public final Challenge.ChallengeStatuses getStatus() {
        return this.status;
    }

    /**
     * Returns The Reason of this Challenge `status`.
     *
     * @return The Reason of this Challenge `status`
     */
    public final Challenge.ChallengeReasons getRespondedReason() {
        return this.respondedReason;
    }

    /**
     * Returns Details about the Challenge..
     *
     * @return Details about the Challenge.
     */
    public final Map<String, Object> getDetails() {
        return this.details;
    }

    /**
     * Returns Hidden details about the Challenge.
     *
     * @return Hidden details about the Challenge
     */
    public final Map<String, Object> getHiddenDetails() {
        return this.hiddenDetails;
    }

    /**
     * Returns The Factor Type of this Challenge.
     *
     * @return The Factor Type of this Challenge
     */
    public final Challenge.FactorTypes getFactorType() {
        return this.factorType;
    }

    /**
     * Returns The URL of this resource..
     *
     * @return The URL of this resource.
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

        Challenge other = (Challenge) o;

        return Objects.equals(sid, other.sid) &&
               Objects.equals(accountSid, other.accountSid) &&
               Objects.equals(serviceSid, other.serviceSid) &&
               Objects.equals(entitySid, other.entitySid) &&
               Objects.equals(identity, other.identity) &&
               Objects.equals(factorSid, other.factorSid) &&
               Objects.equals(dateCreated, other.dateCreated) &&
               Objects.equals(dateUpdated, other.dateUpdated) &&
               Objects.equals(dateResponded, other.dateResponded) &&
               Objects.equals(expirationDate, other.expirationDate) &&
               Objects.equals(status, other.status) &&
               Objects.equals(respondedReason, other.respondedReason) &&
               Objects.equals(details, other.details) &&
               Objects.equals(hiddenDetails, other.hiddenDetails) &&
               Objects.equals(factorType, other.factorType) &&
               Objects.equals(url, other.url);
    }

    @Override
    public int hashCode() {
        return Objects.hash(sid,
                            accountSid,
                            serviceSid,
                            entitySid,
                            identity,
                            factorSid,
                            dateCreated,
                            dateUpdated,
                            dateResponded,
                            expirationDate,
                            status,
                            respondedReason,
                            details,
                            hiddenDetails,
                            factorType,
                            url);
    }
}