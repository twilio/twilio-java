/**
 * This code was generated by
 * \ / _    _  _|   _  _
 *  | (_)\/(_)(_|\/| |(/_  v1.0.0
 *       /       /
 */

package com.twilio.rest.routes.v2;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
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
import lombok.ToString;

import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.time.ZonedDateTime;
import java.util.Map;
import java.util.Objects;

@JsonIgnoreProperties(ignoreUnknown = true)
@ToString
public class PhoneNumber extends Resource {
    private static final long serialVersionUID = 224923539890397L;

    /**
     * Create a PhoneNumberCreator to execute create.
     *
     * @param pathPhoneNumber The phone number
     * @return PhoneNumberCreator capable of executing the create
     */
    public static PhoneNumberCreator creator(final String pathPhoneNumber) {
        return new PhoneNumberCreator(pathPhoneNumber);
    }

    /**
     * Create a PhoneNumberUpdater to execute update.
     *
     * @param pathPhoneNumber The phone number
     * @param voiceRegion The Inbound Processing Region used for this phone number
     *                    for voice
     * @param friendlyName A human readable description of this resource.
     * @return PhoneNumberUpdater capable of executing the update
     */
    public static PhoneNumberUpdater updater(final String pathPhoneNumber,
                                             final String voiceRegion,
                                             final String friendlyName) {
        return new PhoneNumberUpdater(pathPhoneNumber, voiceRegion, friendlyName);
    }

    /**
     * Create a PhoneNumberFetcher to execute fetch.
     *
     * @param pathPhoneNumber The phone number
     * @return PhoneNumberFetcher capable of executing the fetch
     */
    public static PhoneNumberFetcher fetcher(final String pathPhoneNumber) {
        return new PhoneNumberFetcher(pathPhoneNumber);
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

    private final String phoneNumber;
    private final URI url;
    private final String sid;
    private final String accountSid;
    private final String friendlyName;
    private final String voiceRegion;
    private final ZonedDateTime dateCreated;
    private final ZonedDateTime dateUpdated;

    @JsonCreator
    private PhoneNumber(@JsonProperty("phone_number")
                        final String phoneNumber,
                        @JsonProperty("url")
                        final URI url,
                        @JsonProperty("sid")
                        final String sid,
                        @JsonProperty("account_sid")
                        final String accountSid,
                        @JsonProperty("friendly_name")
                        final String friendlyName,
                        @JsonProperty("voice_region")
                        final String voiceRegion,
                        @JsonProperty("date_created")
                        final String dateCreated,
                        @JsonProperty("date_updated")
                        final String dateUpdated) {
        this.phoneNumber = phoneNumber;
        this.url = url;
        this.sid = sid;
        this.accountSid = accountSid;
        this.friendlyName = friendlyName;
        this.voiceRegion = voiceRegion;
        this.dateCreated = DateConverter.iso8601DateTimeFromString(dateCreated);
        this.dateUpdated = DateConverter.iso8601DateTimeFromString(dateUpdated);
    }

    /**
     * Returns The phone number.
     *
     * @return The phone number
     */
    public final String getPhoneNumber() {
        return this.phoneNumber;
    }

    /**
     * Returns The absolute URL of the resource.
     *
     * @return The absolute URL of the resource
     */
    public final URI getUrl() {
        return this.url;
    }

    /**
     * Returns A string that uniquely identifies the Inbound Processing Region
     * assignments for this phone number..
     *
     * @return A string that uniquely identifies the Inbound Processing Region
     *         assignments for this phone number.
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
     * Returns A human readable description of the Inbound Processing Region
     * assignments for this phone number..
     *
     * @return A human readable description of the Inbound Processing Region
     *         assignments for this phone number.
     */
    public final String getFriendlyName() {
        return this.friendlyName;
    }

    /**
     * Returns The Inbound Processing Region used for this phone number for voice..
     *
     * @return The Inbound Processing Region used for this phone number for voice.
     */
    public final String getVoiceRegion() {
        return this.voiceRegion;
    }

    /**
     * Returns The date that this phone number was assigned an Inbound Processing
     * Region..
     *
     * @return The date that this phone number was assigned an Inbound Processing
     *         Region.
     */
    public final ZonedDateTime getDateCreated() {
        return this.dateCreated;
    }

    /**
     * Returns The date that the Inbound Processing Region was updated for this
     * phone number..
     *
     * @return The date that the Inbound Processing Region was updated for this
     *         phone number.
     */
    public final ZonedDateTime getDateUpdated() {
        return this.dateUpdated;
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

        return Objects.equals(phoneNumber, other.phoneNumber) &&
               Objects.equals(url, other.url) &&
               Objects.equals(sid, other.sid) &&
               Objects.equals(accountSid, other.accountSid) &&
               Objects.equals(friendlyName, other.friendlyName) &&
               Objects.equals(voiceRegion, other.voiceRegion) &&
               Objects.equals(dateCreated, other.dateCreated) &&
               Objects.equals(dateUpdated, other.dateUpdated);
    }

    @Override
    public int hashCode() {
        return Objects.hash(phoneNumber,
                            url,
                            sid,
                            accountSid,
                            friendlyName,
                            voiceRegion,
                            dateCreated,
                            dateUpdated);
    }
}