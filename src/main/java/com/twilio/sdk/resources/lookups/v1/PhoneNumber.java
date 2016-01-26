package com.twilio.sdk.resources.lookups.v1.;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.base.MoreObjects;
import com.twilio.sdk.clients.TwilioRestClient;
import com.twilio.sdk.exceptions.ApiConnectionException;
import com.twilio.sdk.exceptions.ApiException;
import com.twilio.sdk.fetchers.lookups.v1..PhoneNumberFetcher;
import com.twilio.sdk.http.HttpMethod;
import com.twilio.sdk.http.Request;
import com.twilio.sdk.http.Response;
import com.twilio.sdk.resources.RestException;
import com.twilio.sdk.resources.SidResource;

import java.io.IOException;
import java.io.InputStream;
import java.util.Map;
import java.util.Objects;

@JsonIgnoreProperties(ignoreUnknown = true)
public class PhoneNumber extends SidResource {
    private static final long serialVersionUID = 65373945668693L;

    public enum Type {
        LANDLINE("landline"),
        MOBILE("mobile"),
        VOIP("voip");
    
        private final String value;
        
        private Type(final String value) {
            this.value = value;
        }
        
        public String toString() {
            return value;
        }
        
        @JsonCreator
        public static Type forValue(final String value) {
            String normalized = value.replace("-", "_").toUpperCase();
            return Type.valueOf(normalized);
        }
    }

    /**
     * fetch
     * 
     * @param phoneNumber The phone_number
     * @return PhoneNumberFetcher capable of executing the fetch
     */
    public static PhoneNumberFetcher fetch(final String phoneNumber) {
        return new PhoneNumberFetcher(phoneNumber);
    }

    /**
     * Converts a JSON String into a PhoneNumber object using the provided
     * ObjectMapper
     * 
     * @param json Raw JSON String
     * @param objectMapper Jackson ObjectMapper
     * @return PhoneNumber object represented by the provided JSON
     */
    public static PhoneNumber fromJson(final String json, final ObjectMapper objectMapper) {
        // Convert all checked exceptions to Runtime
        try {
            return objectMapper.readValue(json, PhoneNumber.class);
        } catch (final JsonMappingException | JsonParseException e) {
            throw new ApiException(e.getMessage(), e);
        } catch (final IOException e) {
            throw new ApiConnectionException(e.getMessage(), e);
        }
    }

    /**
     * Converts a JSON InputStream into a PhoneNumber object using the provided
     * ObjectMapper
     * 
     * @param json Raw JSON InputStream
     * @param objectMapper Jackson ObjectMapper
     * @return PhoneNumber object represented by the provided JSON
     */
    public static PhoneNumber fromJson(final InputStream json, final ObjectMapper objectMapper) {
        // Convert all checked exceptions to Runtime
        try {
            return objectMapper.readValue(json, PhoneNumber.class);
        } catch (final JsonMappingException | JsonParseException e) {
            throw new ApiException(e.getMessage(), e);
        } catch (final IOException e) {
            throw new ApiConnectionException(e.getMessage(), e);
        }
    }

    private final String countryCode;
    private final String phoneNumber;
    private final String nationalFormat;
    private final Map<String, String> carrier;

    @JsonCreator
    private PhoneNumber(@JsonProperty("country_code") final String countryCode, 
                        @JsonProperty("phone_number") final String phoneNumber, 
                        @JsonProperty("national_format") final String nationalFormat, 
                        @JsonProperty("carrier") final Map<String, String> carrier) {
        this.countryCode = countryCode;
        this.phoneNumber = phoneNumber;
        this.nationalFormat = nationalFormat;
        this.carrier = carrier;
    }

    /**
     * @return The phone_number
     */
    public final String getSid() {
        return this.getPhoneNumber();
    }

    /**
     * @return The country_code
     */
    public final String getCountryCode() {
        return this.countryCode;
    }

    /**
     * @return The phone_number
     */
    public final String getPhoneNumber() {
        return this.phoneNumber;
    }

    /**
     * @return The national_format
     */
    public final String getNationalFormat() {
        return this.nationalFormat;
    }

    /**
     * @return The carrier
     */
    public final Map<String, String> getCarrier() {
        return this.carrier;
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        
        PhoneNumber other = (PhoneNumber) o;
        
        return Objects.equals(countryCode, other.countryCode) && 
               Objects.equals(phoneNumber, other.phoneNumber) && 
               Objects.equals(nationalFormat, other.nationalFormat) && 
               Objects.equals(carrier, other.carrier);
    }

    @Override
    public int hashCode() {
        return Objects.hash(countryCode,
                            phoneNumber,
                            nationalFormat,
                            carrier);
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                          .add("countryCode", countryCode)
                          .add("phoneNumber", phoneNumber)
                          .add("nationalFormat", nationalFormat)
                          .add("carrier", carrier)
                          .toString();
    }
}