/*
 * This code was generated by
 * ___ _ _ _ _ _    _ ____    ____ ____ _    ____ ____ _  _ ____ ____ ____ ___ __   __
 *  |  | | | | |    | |  | __ |  | |__| | __ | __ |___ |\ | |___ |__/ |__|  | |  | |__/
 *  |  |_|_| | |___ | |__|    |__| |  | |    |__] |___ | \| |___ |  \ |  |  | |__| |  \
 *
 * Twilio - Api
 * This is the public Twilio REST API.
 *
 * NOTE: This class is auto generated by OpenAPI Generator.
 * https://openapi-generator.tech
 * Do not edit the class manually.
 */

package com.twilio.rest.api.v2010.account.availablephonenumbercountry;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.twilio.base.Resource;
import com.twilio.exception.ApiConnectionException;

import com.twilio.exception.ApiException;

import lombok.ToString;

import java.io.IOException;
import java.io.InputStream;

import java.util.Objects;

import lombok.ToString;

import java.math.BigDecimal;
import com.twilio.type.PhoneNumberCapabilities;

@JsonIgnoreProperties(ignoreUnknown = true)
@ToString
public class Voip extends Resource {
    private static final long serialVersionUID = 211749226408502L;

    

    public static VoipReader reader(final String pathCountryCode){
        return new VoipReader(pathCountryCode);
    }
    public static VoipReader reader(final String pathAccountSid, final String pathCountryCode){
        return new VoipReader(pathAccountSid, pathCountryCode);
    }

    /**
    * Converts a JSON String into a Voip object using the provided ObjectMapper.
    *
    * @param json Raw JSON String
    * @param objectMapper Jackson ObjectMapper
    * @return Voip object represented by the provided JSON
    */
    public static Voip fromJson(final String json, final ObjectMapper objectMapper) {
        // Convert all checked exceptions to Runtime
        try {
            return objectMapper.readValue(json, Voip.class);
        } catch (final JsonMappingException | JsonParseException e) {
            throw new ApiException(e.getMessage(), e);
        } catch (final IOException e) {
            throw new ApiConnectionException(e.getMessage(), e);
        }
    }

    /**
    * Converts a JSON InputStream into a Voip object using the provided
    * ObjectMapper.
    *
    * @param json Raw JSON InputStream
    * @param objectMapper Jackson ObjectMapper
    * @return Voip object represented by the provided JSON
    */
    public static Voip fromJson(final InputStream json, final ObjectMapper objectMapper) {
        // Convert all checked exceptions to Runtime
        try {
            return objectMapper.readValue(json, Voip.class);
        } catch (final JsonMappingException | JsonParseException e) {
            throw new ApiException(e.getMessage(), e);
        } catch (final IOException e) {
            throw new ApiConnectionException(e.getMessage(), e);
        }
    }


    private final com.twilio.type.PhoneNumber friendlyName;
    private final com.twilio.type.PhoneNumber phoneNumber;
    private final String lata;
    private final String locality;
    private final String rateCenter;
    private final BigDecimal latitude;
    private final BigDecimal longitude;
    private final String region;
    private final String postalCode;
    private final String isoCountry;
    private final String addressRequirements;
    private final Boolean beta;
    private final PhoneNumberCapabilities capabilities;

    @JsonCreator
    private Voip(
        @JsonProperty("friendly_name")
        final com.twilio.type.PhoneNumber friendlyName,

        @JsonProperty("phone_number")
        final com.twilio.type.PhoneNumber phoneNumber,

        @JsonProperty("lata")
        final String lata,

        @JsonProperty("locality")
        final String locality,

        @JsonProperty("rate_center")
        final String rateCenter,

        @JsonProperty("latitude")
        final BigDecimal latitude,

        @JsonProperty("longitude")
        final BigDecimal longitude,

        @JsonProperty("region")
        final String region,

        @JsonProperty("postal_code")
        final String postalCode,

        @JsonProperty("iso_country")
        final String isoCountry,

        @JsonProperty("address_requirements")
        final String addressRequirements,

        @JsonProperty("beta")
        final Boolean beta,

        @JsonProperty("capabilities")
        final PhoneNumberCapabilities capabilities
    ) {
        this.friendlyName = friendlyName;
        this.phoneNumber = phoneNumber;
        this.lata = lata;
        this.locality = locality;
        this.rateCenter = rateCenter;
        this.latitude = latitude;
        this.longitude = longitude;
        this.region = region;
        this.postalCode = postalCode;
        this.isoCountry = isoCountry;
        this.addressRequirements = addressRequirements;
        this.beta = beta;
        this.capabilities = capabilities;
    }

        public final com.twilio.type.PhoneNumber getFriendlyName() {
            return this.friendlyName;
        }
        public final com.twilio.type.PhoneNumber getPhoneNumber() {
            return this.phoneNumber;
        }
        public final String getLata() {
            return this.lata;
        }
        public final String getLocality() {
            return this.locality;
        }
        public final String getRateCenter() {
            return this.rateCenter;
        }
        public final BigDecimal getLatitude() {
            return this.latitude;
        }
        public final BigDecimal getLongitude() {
            return this.longitude;
        }
        public final String getRegion() {
            return this.region;
        }
        public final String getPostalCode() {
            return this.postalCode;
        }
        public final String getIsoCountry() {
            return this.isoCountry;
        }
        public final String getAddressRequirements() {
            return this.addressRequirements;
        }
        public final Boolean getBeta() {
            return this.beta;
        }
        public final PhoneNumberCapabilities getCapabilities() {
            return this.capabilities;
        }

    @Override
    public boolean equals(final Object o) {
        if (this==o) {
            return true;
        }

        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        Voip other = (Voip) o;

        return Objects.equals(friendlyName, other.friendlyName) &&  Objects.equals(phoneNumber, other.phoneNumber) &&  Objects.equals(lata, other.lata) &&  Objects.equals(locality, other.locality) &&  Objects.equals(rateCenter, other.rateCenter) &&  Objects.equals(latitude, other.latitude) &&  Objects.equals(longitude, other.longitude) &&  Objects.equals(region, other.region) &&  Objects.equals(postalCode, other.postalCode) &&  Objects.equals(isoCountry, other.isoCountry) &&  Objects.equals(addressRequirements, other.addressRequirements) &&  Objects.equals(beta, other.beta) &&  Objects.equals(capabilities, other.capabilities)  ;
    }

    @Override
    public int hashCode() {
        return Objects.hash(friendlyName, phoneNumber, lata, locality, rateCenter, latitude, longitude, region, postalCode, isoCountry, addressRequirements, beta, capabilities);
    }


}

