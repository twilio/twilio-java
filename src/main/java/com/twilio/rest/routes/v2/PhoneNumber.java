/*
 * This code was generated by
 * ___ _ _ _ _ _    _ ____    ____ ____ _    ____ ____ _  _ ____ ____ ____ ___ __   __
 *  |  | | | | |    | |  | __ |  | |__| | __ | __ |___ |\ | |___ |__/ |__|  | |  | |__/
 *  |  |_|_| | |___ | |__|    |__| |  | |    |__] |___ | \| |___ |  \ |  |  | |__| |  \
 *
 * Twilio - Routes
 * This is the public Twilio REST API.
 *
 * NOTE: This class is auto generated by OpenAPI Generator.
 * https://openapi-generator.tech
 * Do not edit the class manually.
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
import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.time.ZonedDateTime;
import java.util.Objects;
import lombok.ToString;
import lombok.ToString;

@JsonIgnoreProperties(ignoreUnknown = true)
@ToString
public class PhoneNumber extends Resource {

    private static final long serialVersionUID = 213320512216415L;

    public static PhoneNumberFetcher fetcher(final String pathPhoneNumber) {
        return new PhoneNumberFetcher(pathPhoneNumber);
    }

    public static PhoneNumberUpdater updater(final String pathPhoneNumber) {
        return new PhoneNumberUpdater(pathPhoneNumber);
    }

    /**
     * Converts a JSON String into a PhoneNumber object using the provided ObjectMapper.
     *
     * @param json Raw JSON String
     * @param objectMapper Jackson ObjectMapper
     * @return PhoneNumber object represented by the provided JSON
     */
    public static PhoneNumber fromJson(
        final String json,
        final ObjectMapper objectMapper
    ) {
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
    public static PhoneNumber fromJson(
        final InputStream json,
        final ObjectMapper objectMapper
    ) {
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
    private PhoneNumber(
        @JsonProperty("phone_number") final String phoneNumber,
        @JsonProperty("url") final URI url,
        @JsonProperty("sid") final String sid,
        @JsonProperty("account_sid") final String accountSid,
        @JsonProperty("friendly_name") final String friendlyName,
        @JsonProperty("voice_region") final String voiceRegion,
        @JsonProperty("date_created") final String dateCreated,
        @JsonProperty("date_updated") final String dateUpdated
    ) {
        this.phoneNumber = phoneNumber;
        this.url = url;
        this.sid = sid;
        this.accountSid = accountSid;
        this.friendlyName = friendlyName;
        this.voiceRegion = voiceRegion;
        this.dateCreated = DateConverter.iso8601DateTimeFromString(dateCreated);
        this.dateUpdated = DateConverter.iso8601DateTimeFromString(dateUpdated);
    }

    public final String getPhoneNumber() {
        return this.phoneNumber;
    }

    public final URI getUrl() {
        return this.url;
    }

    public final String getSid() {
        return this.sid;
    }

    public final String getAccountSid() {
        return this.accountSid;
    }

    public final String getFriendlyName() {
        return this.friendlyName;
    }

    public final String getVoiceRegion() {
        return this.voiceRegion;
    }

    public final ZonedDateTime getDateCreated() {
        return this.dateCreated;
    }

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

        return (
            Objects.equals(phoneNumber, other.phoneNumber) &&
            Objects.equals(url, other.url) &&
            Objects.equals(sid, other.sid) &&
            Objects.equals(accountSid, other.accountSid) &&
            Objects.equals(friendlyName, other.friendlyName) &&
            Objects.equals(voiceRegion, other.voiceRegion) &&
            Objects.equals(dateCreated, other.dateCreated) &&
            Objects.equals(dateUpdated, other.dateUpdated)
        );
    }

    @Override
    public int hashCode() {
        return Objects.hash(
            phoneNumber,
            url,
            sid,
            accountSid,
            friendlyName,
            voiceRegion,
            dateCreated,
            dateUpdated
        );
    }
}
