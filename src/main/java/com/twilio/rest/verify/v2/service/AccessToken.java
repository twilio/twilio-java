/**
 * This code was generated by
 * \ / _    _  _|   _  _
 *  | (_)\/(_)(_|\/| |(/_  v1.0.0
 *       /       /
 */

package com.twilio.rest.verify.v2.service;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.twilio.base.Resource;
import com.twilio.converter.Promoter;
import com.twilio.exception.ApiConnectionException;
import com.twilio.exception.ApiException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Objects;

/**
 * PLEASE NOTE that this class contains beta products that are subject to
 * change. Use them with caution.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class AccessToken extends Resource {
    private static final long serialVersionUID = 123884033173693L;

    public String toString() {
        return "AccessToken(token=" + this.getToken() + ")";
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
     * Create a AccessTokenCreator to execute create.
     *
     * @param pathServiceSid Service Sid.
     * @param identity Unique external identifier of the Entity
     * @param factorType The Type of this Factor
     * @return AccessTokenCreator capable of executing the create
     */
    public static AccessTokenCreator creator(final String pathServiceSid,
                                             final String identity,
                                             final AccessToken.FactorTypes factorType) {
        return new AccessTokenCreator(pathServiceSid, identity, factorType);
    }

    /**
     * Converts a JSON String into a AccessToken object using the provided
     * ObjectMapper.
     *
     * @param json Raw JSON String
     * @param objectMapper Jackson ObjectMapper
     * @return AccessToken object represented by the provided JSON
     */
    public static AccessToken fromJson(final String json, final ObjectMapper objectMapper) {
        // Convert all checked exceptions to Runtime
        try {
            return objectMapper.readValue(json, AccessToken.class);
        } catch (final JsonMappingException | JsonParseException e) {
            throw new ApiException(e.getMessage(), e);
        } catch (final IOException e) {
            throw new ApiConnectionException(e.getMessage(), e);
        }
    }

    /**
     * Converts a JSON InputStream into a AccessToken object using the provided
     * ObjectMapper.
     *
     * @param json Raw JSON InputStream
     * @param objectMapper Jackson ObjectMapper
     * @return AccessToken object represented by the provided JSON
     */
    public static AccessToken fromJson(final InputStream json, final ObjectMapper objectMapper) {
        // Convert all checked exceptions to Runtime
        try {
            return objectMapper.readValue(json, AccessToken.class);
        } catch (final JsonMappingException | JsonParseException e) {
            throw new ApiException(e.getMessage(), e);
        } catch (final IOException e) {
            throw new ApiConnectionException(e.getMessage(), e);
        }
    }

    private final String token;

    @JsonCreator
    private AccessToken(@JsonProperty("token")
                        final String token) {
        this.token = token;
    }

    /**
     * Returns Generated access token..
     *
     * @return Generated access token.
     */
    public final String getToken() {
        return this.token;
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }

        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        AccessToken other = (AccessToken) o;

        return Objects.equals(token, other.token);
    }

    @Override
    public int hashCode() {
        return Objects.hash(token);
    }
}
