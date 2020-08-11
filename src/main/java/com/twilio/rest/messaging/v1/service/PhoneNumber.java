/**
 * This code was generated by
 * \ / _    _  _|   _  _
 *  | (_)\/(_)(_|\/| |(/_  v1.0.0
 *       /       /
 */

package com.twilio.rest.messaging.v1.service;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.base.MoreObjects;
import com.twilio.base.Resource;
import com.twilio.converter.DateConverter;
import com.twilio.exception.ApiConnectionException;
import com.twilio.exception.ApiException;
import com.twilio.exception.RestException;
import com.twilio.http.HttpMethod;
import com.twilio.http.Request;
import com.twilio.http.Response;
import com.twilio.http.TwilioRestClient;
import com.twilio.rest.Domains;
import java.time.ZonedDateTime;

import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.util.List;
import java.util.Map;
import java.util.Objects;

/**
 * PLEASE NOTE that this class contains beta products that are subject to
 * change. Use them with caution.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class PhoneNumber extends Resource {
    private static final long serialVersionUID = 177917350059297L;

    /**
     * Create a PhoneNumberCreator to execute create.
     *
     * @param pathServiceSid The SID of the Service to create the resource under
     * @param phoneNumberSid The SID of the Phone Number being added to the Service
     * @return PhoneNumberCreator capable of executing the create
     */
    public static PhoneNumberCreator creator(final String pathServiceSid,
                                             final String phoneNumberSid) {
        return new PhoneNumberCreator(pathServiceSid, phoneNumberSid);
    }

    /**
     * Create a PhoneNumberDeleter to execute delete.
     *
     * @param pathServiceSid The SID of the Service to delete the resource from
     * @param pathSid The SID that identifies the resource to delete
     * @return PhoneNumberDeleter capable of executing the delete
     */
    public static PhoneNumberDeleter deleter(final String pathServiceSid,
                                             final String pathSid) {
        return new PhoneNumberDeleter(pathServiceSid, pathSid);
    }

    /**
     * Create a PhoneNumberReader to execute read.
     *
     * @param pathServiceSid The SID of the Service to read the resources from
     * @return PhoneNumberReader capable of executing the read
     */
    public static PhoneNumberReader reader(final String pathServiceSid) {
        return new PhoneNumberReader(pathServiceSid);
    }

    /**
     * Create a PhoneNumberFetcher to execute fetch.
     *
     * @param pathServiceSid The SID of the Service to fetch the resource from
     * @param pathSid The SID that identifies the resource to fetch
     * @return PhoneNumberFetcher capable of executing the fetch
     */
    public static PhoneNumberFetcher fetcher(final String pathServiceSid,
                                             final String pathSid) {
        return new PhoneNumberFetcher(pathServiceSid, pathSid);
    }

    /**
     * Converts a JSON String into a PhoneNumber object using the provided
     * ObjectMapper.
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
     * ObjectMapper.
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

    private final String sid;
    private final String accountSid;
    private final String serviceSid;
    private final ZonedDateTime dateCreated;
    private final ZonedDateTime dateUpdated;
    private final com.twilio.type.PhoneNumber phoneNumber;
    private final String countryCode;
    private final List<String> capabilities;
    private final URI url;

    @JsonCreator
    private PhoneNumber(@JsonProperty("sid")
                        final String sid,
                        @JsonProperty("account_sid")
                        final String accountSid,
                        @JsonProperty("service_sid")
                        final String serviceSid,
                        @JsonProperty("date_created")
                        final String dateCreated,
                        @JsonProperty("date_updated")
                        final String dateUpdated,
                        @JsonProperty("phone_number")
                        final com.twilio.type.PhoneNumber phoneNumber,
                        @JsonProperty("country_code")
                        final String countryCode,
                        @JsonProperty("capabilities")
                        final List<String> capabilities,
                        @JsonProperty("url")
                        final URI url) {
        this.sid = sid;
        this.accountSid = accountSid;
        this.serviceSid = serviceSid;
        this.dateCreated = DateConverter.iso8601DateTimeFromString(dateCreated);
        this.dateUpdated = DateConverter.iso8601DateTimeFromString(dateUpdated);
        this.phoneNumber = phoneNumber;
        this.countryCode = countryCode;
        this.capabilities = capabilities;
        this.url = url;
    }

    /**
     * Returns The unique string that identifies the resource.
     *
     * @return The unique string that identifies the resource
     */
    public final String getSid() {
        return this.sid;
    }

    /**
     * Returns The SID of the Account that created the resource.
     *
     * @return The SID of the Account that created the resource
     */
    public final String getAccountSid() {
        return this.accountSid;
    }

    /**
     * Returns The SID of the Service that the resource is associated with.
     *
     * @return The SID of the Service that the resource is associated with
     */
    public final String getServiceSid() {
        return this.serviceSid;
    }

    /**
     * Returns The ISO 8601 date and time in GMT when the resource was created.
     *
     * @return The ISO 8601 date and time in GMT when the resource was created
     */
    public final ZonedDateTime getDateCreated() {
        return this.dateCreated;
    }

    /**
     * Returns The ISO 8601 date and time in GMT when the resource was last updated.
     *
     * @return The ISO 8601 date and time in GMT when the resource was last updated
     */
    public final ZonedDateTime getDateUpdated() {
        return this.dateUpdated;
    }

    /**
     * Returns The phone number in E.164 format.
     *
     * @return The phone number in E.164 format
     */
    public final com.twilio.type.PhoneNumber getPhoneNumber() {
        return this.phoneNumber;
    }

    /**
     * Returns The 2-character ISO Country Code of the number.
     *
     * @return The 2-character ISO Country Code of the number
     */
    public final String getCountryCode() {
        return this.countryCode;
    }

    /**
     * Returns An array of values that describe whether the number can receive calls
     * or messages.
     *
     * @return An array of values that describe whether the number can receive
     *         calls or messages
     */
    public final List<String> getCapabilities() {
        return this.capabilities;
    }

    /**
     * Returns The absolute URL of the PhoneNumber resource.
     *
     * @return The absolute URL of the PhoneNumber resource
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

        PhoneNumber other = (PhoneNumber) o;

        return Objects.equals(sid, other.sid) &&
               Objects.equals(accountSid, other.accountSid) &&
               Objects.equals(serviceSid, other.serviceSid) &&
               Objects.equals(dateCreated, other.dateCreated) &&
               Objects.equals(dateUpdated, other.dateUpdated) &&
               Objects.equals(phoneNumber, other.phoneNumber) &&
               Objects.equals(countryCode, other.countryCode) &&
               Objects.equals(capabilities, other.capabilities) &&
               Objects.equals(url, other.url);
    }

    @Override
    public int hashCode() {
        return Objects.hash(sid,
                            accountSid,
                            serviceSid,
                            dateCreated,
                            dateUpdated,
                            phoneNumber,
                            countryCode,
                            capabilities,
                            url);
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                          .add("sid", sid)
                          .add("accountSid", accountSid)
                          .add("serviceSid", serviceSid)
                          .add("dateCreated", dateCreated)
                          .add("dateUpdated", dateUpdated)
                          .add("phoneNumber", phoneNumber)
                          .add("countryCode", countryCode)
                          .add("capabilities", capabilities)
                          .add("url", url)
                          .toString();
    }
}
