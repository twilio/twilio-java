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
public class Factor extends Resource {

    private static final long serialVersionUID = 254306005270593L;

    public static FactorDeleter deleter(
        final String pathServiceSid,
        final String pathIdentity,
        final String pathSid
    ) {
        return new FactorDeleter(pathServiceSid, pathIdentity, pathSid);
    }

    public static FactorFetcher fetcher(
        final String pathServiceSid,
        final String pathIdentity,
        final String pathSid
    ) {
        return new FactorFetcher(pathServiceSid, pathIdentity, pathSid);
    }

    public static FactorReader reader(
        final String pathServiceSid,
        final String pathIdentity
    ) {
        return new FactorReader(pathServiceSid, pathIdentity);
    }

    public static FactorUpdater updater(
        final String pathServiceSid,
        final String pathIdentity,
        final String pathSid
    ) {
        return new FactorUpdater(pathServiceSid, pathIdentity, pathSid);
    }

    /**
     * Converts a JSON String into a Factor object using the provided ObjectMapper.
     *
     * @param json Raw JSON String
     * @param objectMapper Jackson ObjectMapper
     * @return Factor object represented by the provided JSON
     */
    public static Factor fromJson(
        final String json,
        final ObjectMapper objectMapper
    ) {
        // Convert all checked exceptions to Runtime
        try {
            return objectMapper.readValue(json, Factor.class);
        } catch (final JsonMappingException | JsonParseException e) {
            throw new ApiException(e.getMessage(), e);
        } catch (final IOException e) {
            throw new ApiConnectionException(e.getMessage(), e);
        }
    }

    /**
     * Converts a JSON InputStream into a Factor object using the provided
     * ObjectMapper.
     *
     * @param json Raw JSON InputStream
     * @param objectMapper Jackson ObjectMapper
     * @return Factor object represented by the provided JSON
     */
    public static Factor fromJson(
        final InputStream json,
        final ObjectMapper objectMapper
    ) {
        // Convert all checked exceptions to Runtime
        try {
            return objectMapper.readValue(json, Factor.class);
        } catch (final JsonMappingException | JsonParseException e) {
            throw new ApiException(e.getMessage(), e);
        } catch (final IOException e) {
            throw new ApiConnectionException(e.getMessage(), e);
        }
    }

    public enum FactorStatuses {
        UNVERIFIED("unverified"),
        VERIFIED("verified");

        private final String value;

        private FactorStatuses(final String value) {
            this.value = value;
        }

        public String toString() {
            return value;
        }

        @JsonCreator
        public static FactorStatuses forValue(final String value) {
            return Promoter.enumFromString(value, FactorStatuses.values());
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

    public enum TotpAlgorithms {
        SHA1("sha1"),
        SHA256("sha256"),
        SHA512("sha512");

        private final String value;

        private TotpAlgorithms(final String value) {
            this.value = value;
        }

        public String toString() {
            return value;
        }

        @JsonCreator
        public static TotpAlgorithms forValue(final String value) {
            return Promoter.enumFromString(value, TotpAlgorithms.values());
        }
    }

    private final String sid;
    private final String accountSid;
    private final String serviceSid;
    private final String entitySid;
    private final String identity;
    private final ZonedDateTime dateCreated;
    private final ZonedDateTime dateUpdated;
    private final String friendlyName;
    private final Factor.FactorStatuses status;
    private final Factor.FactorTypes factorType;
    private final Map<String, Object> config;
    private final Map<String, Object> metadata;
    private final URI url;

    @JsonCreator
    private Factor(
        @JsonProperty("sid") final String sid,
        @JsonProperty("account_sid") final String accountSid,
        @JsonProperty("service_sid") final String serviceSid,
        @JsonProperty("entity_sid") final String entitySid,
        @JsonProperty("identity") final String identity,
        @JsonProperty("date_created") final String dateCreated,
        @JsonProperty("date_updated") final String dateUpdated,
        @JsonProperty("friendly_name") final String friendlyName,
        @JsonProperty("status") final Factor.FactorStatuses status,
        @JsonProperty("factor_type") final Factor.FactorTypes factorType,
        @JsonProperty("config") final Map<String, Object> config,
        @JsonProperty("metadata") final Map<String, Object> metadata,
        @JsonProperty("url") final URI url
    ) {
        this.sid = sid;
        this.accountSid = accountSid;
        this.serviceSid = serviceSid;
        this.entitySid = entitySid;
        this.identity = identity;
        this.dateCreated = DateConverter.iso8601DateTimeFromString(dateCreated);
        this.dateUpdated = DateConverter.iso8601DateTimeFromString(dateUpdated);
        this.friendlyName = friendlyName;
        this.status = status;
        this.factorType = factorType;
        this.config = config;
        this.metadata = metadata;
        this.url = url;
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

    public final ZonedDateTime getDateCreated() {
        return this.dateCreated;
    }

    public final ZonedDateTime getDateUpdated() {
        return this.dateUpdated;
    }

    public final String getFriendlyName() {
        return this.friendlyName;
    }

    public final Factor.FactorStatuses getStatus() {
        return this.status;
    }

    public final Factor.FactorTypes getFactorType() {
        return this.factorType;
    }

    public final Map<String, Object> getConfig() {
        return this.config;
    }

    public final Map<String, Object> getMetadata() {
        return this.metadata;
    }

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

        Factor other = (Factor) o;

        return (
            Objects.equals(sid, other.sid) &&
            Objects.equals(accountSid, other.accountSid) &&
            Objects.equals(serviceSid, other.serviceSid) &&
            Objects.equals(entitySid, other.entitySid) &&
            Objects.equals(identity, other.identity) &&
            Objects.equals(dateCreated, other.dateCreated) &&
            Objects.equals(dateUpdated, other.dateUpdated) &&
            Objects.equals(friendlyName, other.friendlyName) &&
            Objects.equals(status, other.status) &&
            Objects.equals(factorType, other.factorType) &&
            Objects.equals(config, other.config) &&
            Objects.equals(metadata, other.metadata) &&
            Objects.equals(url, other.url)
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
            dateCreated,
            dateUpdated,
            friendlyName,
            status,
            factorType,
            config,
            metadata,
            url
        );
    }
}
