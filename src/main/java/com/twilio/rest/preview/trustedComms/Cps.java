/**
 * This code was generated by
 * \ / _    _  _|   _  _
 *  | (_)\/(_)(_|\/| |(/_  v1.0.0
 *       /       /
 */

package com.twilio.rest.preview.trustedComms;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.twilio.base.Resource;
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
import java.util.Map;
import java.util.Objects;

/**
 * PLEASE NOTE that this class contains preview products that are subject to
 * change. Use them with caution. If you currently do not have developer preview
 * access, please contact help@twilio.com.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
@ToString
public class Cps extends Resource {
    private static final long serialVersionUID = 222355853485460L;

    /**
     * Create a CpsFetcher to execute fetch.
     *
     * @return CpsFetcher capable of executing the fetch
     */
    public static CpsFetcher fetcher() {
        return new CpsFetcher();
    }

    /**
     * Converts a JSON String into a Cps object using the provided ObjectMapper.
     *
     * @param json Raw JSON String
     * @param objectMapper Jackson ObjectMapper
     * @return Cps object represented by the provided JSON
     */
    public static Cps fromJson(final String json, final ObjectMapper objectMapper) {
        // Convert all checked exceptions to Runtime
        try {
            return objectMapper.readValue(json, Cps.class);
        } catch (final JsonMappingException | JsonParseException e) {
            throw new ApiException(e.getMessage(), e);
        } catch (final IOException e) {
            throw new ApiConnectionException(e.getMessage(), e);
        }
    }

    /**
     * Converts a JSON InputStream into a Cps object using the provided
     * ObjectMapper.
     *
     * @param json Raw JSON InputStream
     * @param objectMapper Jackson ObjectMapper
     * @return Cps object represented by the provided JSON
     */
    public static Cps fromJson(final InputStream json, final ObjectMapper objectMapper) {
        // Convert all checked exceptions to Runtime
        try {
            return objectMapper.readValue(json, Cps.class);
        } catch (final JsonMappingException | JsonParseException e) {
            throw new ApiException(e.getMessage(), e);
        } catch (final IOException e) {
            throw new ApiConnectionException(e.getMessage(), e);
        }
    }

    private final URI cpsUrl;
    private final String phoneNumber;
    private final URI url;

    @JsonCreator
    private Cps(@JsonProperty("cps_url")
                final URI cpsUrl,
                @JsonProperty("phone_number")
                final String phoneNumber,
                @JsonProperty("url")
                final URI url) {
        this.cpsUrl = cpsUrl;
        this.phoneNumber = phoneNumber;
        this.url = url;
    }

    /**
     * Returns CPS URL of the phone number..
     *
     * @return CPS URL of the phone number.
     */
    public final URI getCpsUrl() {
        return this.cpsUrl;
    }

    /**
     * Returns Phone number passed..
     *
     * @return Phone number passed.
     */
    public final String getPhoneNumber() {
        return this.phoneNumber;
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

        Cps other = (Cps) o;

        return Objects.equals(cpsUrl, other.cpsUrl) &&
               Objects.equals(phoneNumber, other.phoneNumber) &&
               Objects.equals(url, other.url);
    }

    @Override
    public int hashCode() {
        return Objects.hash(cpsUrl,
                            phoneNumber,
                            url);
    }
}