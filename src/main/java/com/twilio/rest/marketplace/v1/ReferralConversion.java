/*
 * This code was generated by
 * ___ _ _ _ _ _    _ ____    ____ ____ _    ____ ____ _  _ ____ ____ ____ ___ __   __
 *  |  | | | | |    | |  | __ |  | |__| | __ | __ |___ |\ | |___ |__/ |__|  | |  | |__/
 *  |  |_|_| | |___ | |__|    |__| |  | |    |__] |___ | \| |___ |  \ |  |  | |__| |  \
 *
 * Twilio - Marketplace
 * This is the public Twilio REST API.
 *
 * NOTE: This class is auto generated by OpenAPI Generator.
 * https://openapi-generator.tech
 * Do not edit the class manually.
 */

package com.twilio.rest.marketplace.v1;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.twilio.base.Resource;
import com.twilio.exception.ApiConnectionException;
import com.twilio.exception.ApiException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Objects;
import lombok.Builder;
import lombok.ToString;
import lombok.ToString;

@JsonIgnoreProperties(ignoreUnknown = true)
@ToString
public class ReferralConversion extends Resource {

    private static final long serialVersionUID = 67114907443655L;

    @ToString
    @Builder
    public static class CreateReferralConversionRequest {

        @JsonInclude(JsonInclude.Include.NON_EMPTY)
        @JsonProperty("referral_account_sid")
        private String referralAccountSid;

        public static CreateReferralConversionRequest fromJson(
            String jsonString,
            ObjectMapper mapper
        ) throws IOException {
            return mapper.readValue(
                jsonString,
                CreateReferralConversionRequest.class
            );
        }
    }

    public static ReferralConversionCreator creator(
        final ReferralConversion.CreateReferralConversionRequest createReferralConversionRequest
    ) {
        return new ReferralConversionCreator(createReferralConversionRequest);
    }

    /**
     * Converts a JSON String into a ReferralConversion object using the provided ObjectMapper.
     *
     * @param json Raw JSON String
     * @param objectMapper Jackson ObjectMapper
     * @return ReferralConversion object represented by the provided JSON
     */
    public static ReferralConversion fromJson(
        final String json,
        final ObjectMapper objectMapper
    ) {
        // Convert all checked exceptions to Runtime
        try {
            return objectMapper.readValue(json, ReferralConversion.class);
        } catch (final JsonMappingException | JsonParseException e) {
            throw new ApiException(e.getMessage(), e);
        } catch (final IOException e) {
            throw new ApiConnectionException(e.getMessage(), e);
        }
    }

    /**
     * Converts a JSON InputStream into a ReferralConversion object using the provided
     * ObjectMapper.
     *
     * @param json Raw JSON InputStream
     * @param objectMapper Jackson ObjectMapper
     * @return ReferralConversion object represented by the provided JSON
     */
    public static ReferralConversion fromJson(
        final InputStream json,
        final ObjectMapper objectMapper
    ) {
        // Convert all checked exceptions to Runtime
        try {
            return objectMapper.readValue(json, ReferralConversion.class);
        } catch (final JsonMappingException | JsonParseException e) {
            throw new ApiException(e.getMessage(), e);
        } catch (final IOException e) {
            throw new ApiConnectionException(e.getMessage(), e);
        }
    }

    public static String toJson(Object object, ObjectMapper mapper) {
        try {
            return mapper.writeValueAsString(object);
        } catch (final JsonMappingException e) {
            throw new ApiException(e.getMessage(), e);
        } catch (JsonProcessingException e) {
            throw new ApiException(e.getMessage(), e);
        } catch (final IOException e) {
            throw new ApiConnectionException(e.getMessage(), e);
        }
    }

    private final String convertedAccountSid;

    @JsonCreator
    private ReferralConversion(
        @JsonProperty("converted_account_sid") final String convertedAccountSid
    ) {
        this.convertedAccountSid = convertedAccountSid;
    }

    public final String getConvertedAccountSid() {
        return this.convertedAccountSid;
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }

        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        ReferralConversion other = (ReferralConversion) o;

        return Objects.equals(convertedAccountSid, other.convertedAccountSid);
    }

    @Override
    public int hashCode() {
        return Objects.hash(convertedAccountSid);
    }
}
