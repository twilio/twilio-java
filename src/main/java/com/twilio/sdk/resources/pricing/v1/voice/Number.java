package com.twilio.sdk.resources.pricing.v1.voice;

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
import com.twilio.sdk.fetchers.pricing.v1.voice.NumberFetcher;
import com.twilio.sdk.http.HttpMethod;
import com.twilio.sdk.http.Request;
import com.twilio.sdk.http.Response;
import com.twilio.sdk.numbers.PhoneNumber;
import com.twilio.sdk.resources.RestException;
import com.twilio.sdk.resources.SidResource;

import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.util.Currency;
import java.util.Map;
import java.util.Objects;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Number extends SidResource {
    private static final long serialVersionUID = 80598460304386L;

    /**
     * fetch
     * 
     * @param number The number
     * @return NumberFetcher capable of executing the fetch
     */
    public static NumberFetcher fetch(final PhoneNumber number) {
        return new NumberFetcher(number);
    }

    /**
     * Converts a JSON String into a Number object using the provided ObjectMapper
     * 
     * @param json Raw JSON String
     * @param objectMapper Jackson ObjectMapper
     * @return Number object represented by the provided JSON
     */
    public static Number fromJson(final String json, final ObjectMapper objectMapper) {
        // Convert all checked exceptions to Runtime
        try {
            return objectMapper.readValue(json, Number.class);
        } catch (final JsonMappingException | JsonParseException e) {
            throw new ApiException(e.getMessage(), e);
        } catch (final IOException e) {
            throw new ApiConnectionException(e.getMessage(), e);
        }
    }

    /**
     * Converts a JSON InputStream into a Number object using the provided
     * ObjectMapper
     * 
     * @param json Raw JSON InputStream
     * @param objectMapper Jackson ObjectMapper
     * @return Number object represented by the provided JSON
     */
    public static Number fromJson(final InputStream json, final ObjectMapper objectMapper) {
        // Convert all checked exceptions to Runtime
        try {
            return objectMapper.readValue(json, Number.class);
        } catch (final JsonMappingException | JsonParseException e) {
            throw new ApiException(e.getMessage(), e);
        } catch (final IOException e) {
            throw new ApiConnectionException(e.getMessage(), e);
        }
    }

    private final PhoneNumber number;
    private final String country;
    private final String isoCountry;
    private final String outboundCallPrice;
    private final String inboundCallPrice;
    private final Currency priceUnit;
    private final URI url;

    @JsonCreator
    private Number(@JsonProperty("number") final PhoneNumber number, 
                   @JsonProperty("country") final String country, 
                   @JsonProperty("iso_country") final String isoCountry, 
                   @JsonProperty("outbound_call_price") final String outboundCallPrice, 
                   @JsonProperty("inbound_call_price") final String inboundCallPrice, 
                   @JsonProperty("price_unit") final Currency priceUnit, 
                   @JsonProperty("url") final URI url) {
        this.number = number;
        this.country = country;
        this.isoCountry = isoCountry;
        this.outboundCallPrice = outboundCallPrice;
        this.inboundCallPrice = inboundCallPrice;
        this.priceUnit = priceUnit;
        this.url = url;
    }

    /**
     * @return The number
     */
    public final PhoneNumber getSid() {
        return this.getNumber();
    }

    /**
     * @return The number
     */
    public final PhoneNumber getNumber() {
        return this.number;
    }

    /**
     * @return The country
     */
    public final String getCountry() {
        return this.country;
    }

    /**
     * @return The iso_country
     */
    public final String getIsoCountry() {
        return this.isoCountry;
    }

    /**
     * @return The outbound_call_price
     */
    public final String getOutboundCallPrice() {
        return this.outboundCallPrice;
    }

    /**
     * @return The inbound_call_price
     */
    public final String getInboundCallPrice() {
        return this.inboundCallPrice;
    }

    /**
     * @return The price_unit
     */
    public final Currency getPriceUnit() {
        return this.priceUnit;
    }

    /**
     * @return The url
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
        
        Number other = (Number) o;
        
        return Objects.equals(number, other.number) && 
               Objects.equals(country, other.country) && 
               Objects.equals(isoCountry, other.isoCountry) && 
               Objects.equals(outboundCallPrice, other.outboundCallPrice) && 
               Objects.equals(inboundCallPrice, other.inboundCallPrice) && 
               Objects.equals(priceUnit, other.priceUnit) && 
               Objects.equals(url, other.url);
    }

    @Override
    public int hashCode() {
        return Objects.hash(number,
                            country,
                            isoCountry,
                            outboundCallPrice,
                            inboundCallPrice,
                            priceUnit,
                            url);
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                          .add("number", number)
                          .add("country", country)
                          .add("isoCountry", isoCountry)
                          .add("outboundCallPrice", outboundCallPrice)
                          .add("inboundCallPrice", inboundCallPrice)
                          .add("priceUnit", priceUnit)
                          .add("url", url)
                          .toString();
    }
}