/*
 * This code was generated by
 * ___ _ _ _ _ _    _ ____    ____ ____ _    ____ ____ _  _ ____ ____ ____ ___ __   __
 *  |  | | | | |    | |  | __ |  | |__| | __ | __ |___ |\ | |___ |__/ |__|  | |  | |__/
 *  |  |_|_| | |___ | |__|    |__| |  | |    |__] |___ | \| |___ |  \ |  |  | |__| |  \
 *
 * Twilio - Verify
 * This is the public Twilio REST API.
 *
 * NOTE: This class is auto generated by OpenAPI Generator.
 * https://openapi-generator.tech
 * Do not edit the class manually.
 */

package com.twilio.rest.verify.v2.service.entity;

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
public class Challenge extends Resource {

    private static final long serialVersionUID = 265978723470772L;

    public static ChallengeCreator creator(
        final String pathServiceSid,
        final String pathIdentity,
        final String factorSid
    ) {
        return new ChallengeCreator(pathServiceSid, pathIdentity, factorSid);
    }

    public static ChallengeFetcher fetcher(
        final String pathServiceSid,
        final String pathIdentity,
        final String pathSid
    ) {
        return new ChallengeFetcher(pathServiceSid, pathIdentity, pathSid);
    }

    public static ChallengeReader reader(
        final String pathServiceSid,
        final String pathIdentity
    ) {
        return new ChallengeReader(pathServiceSid, pathIdentity);
    }

    public static ChallengeUpdater updater(
        final String pathServiceSid,
        final String pathIdentity,
        final String pathSid
    ) {
        return new ChallengeUpdater(pathServiceSid, pathIdentity, pathSid);
    }

    /**
     * Converts a JSON String into a Challenge object using the provided ObjectMapper.
     *
     * @param json Raw JSON String
     * @param objectMapper Jackson ObjectMapper
     * @return Challenge object represented by the provided JSON
     */
    public static Challenge fromJson(
        final String json,
        final ObjectMapper objectMapper
    ) {
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
    public static Challenge fromJson(
        final InputStream json,
        final ObjectMapper objectMapper
    ) {
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
    private final Map<String, Object> metadata;
    private final Challenge.FactorTypes factorType;
    private final URI url;
    private final Map<String, String> links;

    @JsonCreator
    private Challenge(
        @JsonProperty("sid") final String sid,
        @JsonProperty("account_sid") final String accountSid,
        @JsonProperty("service_sid") final String serviceSid,
        @JsonProperty("entity_sid") final String entitySid,
        @JsonProperty("identity") final String identity,
        @JsonProperty("factor_sid") final String factorSid,
        @JsonProperty("date_created") final String dateCreated,
        @JsonProperty("date_updated") final String dateUpdated,
        @JsonProperty("date_responded") final String dateResponded,
        @JsonProperty("expiration_date") final String expirationDate,
        @JsonProperty("status") final Challenge.ChallengeStatuses status,
        @JsonProperty(
            "responded_reason"
        ) final Challenge.ChallengeReasons respondedReason,
        @JsonProperty("details") final Map<String, Object> details,
        @JsonProperty("hidden_details") final Map<String, Object> hiddenDetails,
        @JsonProperty("metadata") final Map<String, Object> metadata,
        @JsonProperty("factor_type") final Challenge.FactorTypes factorType,
        @JsonProperty("url") final URI url,
        @JsonProperty("links") final Map<String, String> links
    ) {
        this.sid = sid;
        this.accountSid = accountSid;
        this.serviceSid = serviceSid;
        this.entitySid = entitySid;
        this.identity = identity;
        this.factorSid = factorSid;
        this.dateCreated = DateConverter.iso8601DateTimeFromString(dateCreated);
        this.dateUpdated = DateConverter.iso8601DateTimeFromString(dateUpdated);
        this.dateResponded =
            DateConverter.iso8601DateTimeFromString(dateResponded);
        this.expirationDate =
            DateConverter.iso8601DateTimeFromString(expirationDate);
        this.status = status;
        this.respondedReason = respondedReason;
        this.details = details;
        this.hiddenDetails = hiddenDetails;
        this.metadata = metadata;
        this.factorType = factorType;
        this.url = url;
        this.links = links;
    }

    public final String getSid() {
        return this.sid;
    }

    public final String getAccountSid() {
        return this.accountSid;
    }

    public final String getServiceSid() {
        return this.serviceSid;
    }

    public final String getEntitySid() {
        return this.entitySid;
    }

    public final String getIdentity() {
        return this.identity;
    }

    public final String getFactorSid() {
        return this.factorSid;
    }

    public final ZonedDateTime getDateCreated() {
        return this.dateCreated;
    }

    public final ZonedDateTime getDateUpdated() {
        return this.dateUpdated;
    }

    public final ZonedDateTime getDateResponded() {
        return this.dateResponded;
    }

    public final ZonedDateTime getExpirationDate() {
        return this.expirationDate;
    }

    public final Challenge.ChallengeStatuses getStatus() {
        return this.status;
    }

    public final Challenge.ChallengeReasons getRespondedReason() {
        return this.respondedReason;
    }

    public final Map<String, Object> getDetails() {
        return this.details;
    }

    public final Map<String, Object> getHiddenDetails() {
        return this.hiddenDetails;
    }

    public final Map<String, Object> getMetadata() {
        return this.metadata;
    }

    public final Challenge.FactorTypes getFactorType() {
        return this.factorType;
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

        Challenge other = (Challenge) o;

        return (
            Objects.equals(sid, other.sid) &&
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
            Objects.equals(metadata, other.metadata) &&
            Objects.equals(factorType, other.factorType) &&
            Objects.equals(url, other.url) &&
            Objects.equals(links, other.links)
        );
    }

    @Override
    public int hashCode() {
        return Objects.hash(
            sid,
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
            metadata,
            factorType,
            url,
            links
        );
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

        @JsonCreator
        public static ChallengeReasons forValue(final String value) {
            return Promoter.enumFromString(value, ChallengeReasons.values());
        }
    }

    public enum ListOrders {
        ASC("asc"),
        DESC("desc");

        private final String value;

        private ListOrders(final String value) {
            this.value = value;
        }

        public String toString() {
            return value;
        }

        @JsonCreator
        public static ListOrders forValue(final String value) {
            return Promoter.enumFromString(value, ListOrders.values());
        }
    }

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

        @JsonCreator
        public static ChallengeStatuses forValue(final String value) {
            return Promoter.enumFromString(value, ChallengeStatuses.values());
        }
    }

    public enum FactorTypes {
        PUSH("push"),
        TOTP("totp");

        private final String value;

        private FactorTypes(final String value) {
            this.value = value;
        }

        public String toString() {
            return value;
        }

        @JsonCreator
        public static FactorTypes forValue(final String value) {
            return Promoter.enumFromString(value, FactorTypes.values());
        }
    }
}
