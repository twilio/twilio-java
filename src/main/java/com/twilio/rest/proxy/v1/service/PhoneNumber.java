/**
 * This code was generated by
 * \ / _    _  _|   _  _
 *  | (_)\/(_)(_|\/| |(/_  v1.0.0
 *       /       /
 */

package com.twilio.rest.proxy.v1.service;

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
import com.twilio.type.PhoneNumberCapabilities;
import java.time.ZonedDateTime;

import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.util.Map;
import java.util.Objects;

/**
 * PLEASE NOTE that this class contains beta products that are subject to
 * change. Use them with caution.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class PhoneNumber extends Resource {
    private static final long serialVersionUID = 107544964482709L;

    /**
     * Create a PhoneNumberCreator to execute create.
     *
     * @param pathServiceSid The SID of the resource's parent Service
     * @return PhoneNumberCreator capable of executing the create
     */
    public static PhoneNumberCreator creator(final String pathServiceSid) {
        return new PhoneNumberCreator(pathServiceSid);
    }

    /**
     * Create a PhoneNumberDeleter to execute delete.
     *
     * @param pathServiceSid The SID of the parent Service resource of the
     *                       PhoneNumber resource to delete
     * @param pathSid The unique string that identifies the resource
     * @return PhoneNumberDeleter capable of executing the delete
     */
    public static PhoneNumberDeleter deleter(final String pathServiceSid,
                                             final String pathSid) {
        return new PhoneNumberDeleter(pathServiceSid, pathSid);
    }

    /**
     * Create a PhoneNumberReader to execute read.
     *
     * @param pathServiceSid The SID of the parent Service resource of the
     *                       PhoneNumber resource to read
     * @return PhoneNumberReader capable of executing the read
     */
    public static PhoneNumberReader reader(final String pathServiceSid) {
        return new PhoneNumberReader(pathServiceSid);
    }

    /**
     * Create a PhoneNumberFetcher to execute fetch.
     *
     * @param pathServiceSid The SID of the parent Service resource of the
     *                       PhoneNumber resource to fetch
     * @param pathSid The unique string that identifies the resource
     * @return PhoneNumberFetcher capable of executing the fetch
     */
    public static PhoneNumberFetcher fetcher(final String pathServiceSid,
                                             final String pathSid) {
        return new PhoneNumberFetcher(pathServiceSid, pathSid);
    }

    /**
     * Create a PhoneNumberUpdater to execute update.
     *
     * @param pathServiceSid The SID of the parent Service resource of the
     *                       PhoneNumber resource to update
     * @param pathSid The unique string that identifies the resource
     * @return PhoneNumberUpdater capable of executing the update
     */
    public static PhoneNumberUpdater updater(final String pathServiceSid,
                                             final String pathSid) {
        return new PhoneNumberUpdater(pathServiceSid, pathSid);
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
    private final String friendlyName;
    private final String isoCountry;
    private final PhoneNumberCapabilities capabilities;
    private final URI url;
    private final Boolean isReserved;
    private final Integer inUse;

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
                        @JsonProperty("friendly_name")
                        final String friendlyName,
                        @JsonProperty("iso_country")
                        final String isoCountry,
                        @JsonProperty("capabilities")
                        final PhoneNumberCapabilities capabilities,
                        @JsonProperty("url")
                        final URI url,
                        @JsonProperty("is_reserved")
                        final Boolean isReserved,
                        @JsonProperty("in_use")
                        final Integer inUse) {
        this.sid = sid;
        this.accountSid = accountSid;
        this.serviceSid = serviceSid;
        this.dateCreated = DateConverter.iso8601DateTimeFromString(dateCreated);
        this.dateUpdated = DateConverter.iso8601DateTimeFromString(dateUpdated);
        this.phoneNumber = phoneNumber;
        this.friendlyName = friendlyName;
        this.isoCountry = isoCountry;
        this.capabilities = capabilities;
        this.url = url;
        this.isReserved = isReserved;
        this.inUse = inUse;
    }

    /**
     * Returns The The unique string that identifies the resource.
     *
     * @return The unique string that identifies the resource
     */
    public final String getSid() {
        return this.sid;
    }

    /**
     * Returns The The SID of the Account that created the resource.
     *
     * @return The SID of the Account that created the resource
     */
    public final String getAccountSid() {
        return this.accountSid;
    }

    /**
     * Returns The The SID of the PhoneNumber resource's parent Service resource.
     *
     * @return The SID of the PhoneNumber resource's parent Service resource
     */
    public final String getServiceSid() {
        return this.serviceSid;
    }

    /**
     * Returns The The ISO 8601 date and time in GMT when the resource was created.
     *
     * @return The ISO 8601 date and time in GMT when the resource was created
     */
    public final ZonedDateTime getDateCreated() {
        return this.dateCreated;
    }

    /**
     * Returns The The ISO 8601 date and time in GMT when the resource was last
     * updated.
     *
     * @return The ISO 8601 date and time in GMT when the resource was last updated
     */
    public final ZonedDateTime getDateUpdated() {
        return this.dateUpdated;
    }

    /**
     * Returns The The phone number in E.164 format.
     *
     * @return The phone number in E.164 format
     */
    public final com.twilio.type.PhoneNumber getPhoneNumber() {
        return this.phoneNumber;
    }

    /**
     * Returns The The string that you assigned to describe the resource.
     *
     * @return The string that you assigned to describe the resource
     */
    public final String getFriendlyName() {
        return this.friendlyName;
    }

    /**
     * Returns The The ISO Country Code.
     *
     * @return The ISO Country Code
     */
    public final String getIsoCountry() {
        return this.isoCountry;
    }

    /**
     * Returns The The capabilities of the phone number.
     *
     * @return The capabilities of the phone number
     */
    public final PhoneNumberCapabilities getCapabilities() {
        return this.capabilities;
    }

    /**
     * Returns The The absolute URL of the PhoneNumber resource.
     *
     * @return The absolute URL of the PhoneNumber resource
     */
    public final URI getUrl() {
        return this.url;
    }

    /**
     * Returns The Reserve the phone number for manual assignment to participants
     * only.
     *
     * @return Reserve the phone number for manual assignment to participants only
     */
    public final Boolean getIsReserved() {
        return this.isReserved;
    }

    /**
     * Returns The The number of open session assigned to the number..
     *
     * @return The number of open session assigned to the number.
     */
    public final Integer getInUse() {
        return this.inUse;
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
               Objects.equals(friendlyName, other.friendlyName) &&
               Objects.equals(isoCountry, other.isoCountry) &&
               Objects.equals(capabilities, other.capabilities) &&
               Objects.equals(url, other.url) &&
               Objects.equals(isReserved, other.isReserved) &&
               Objects.equals(inUse, other.inUse);
    }

    @Override
    public int hashCode() {
        return Objects.hash(sid,
                            accountSid,
                            serviceSid,
                            dateCreated,
                            dateUpdated,
                            phoneNumber,
                            friendlyName,
                            isoCountry,
                            capabilities,
                            url,
                            isReserved,
                            inUse);
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
                          .add("friendlyName", friendlyName)
                          .add("isoCountry", isoCountry)
                          .add("capabilities", capabilities)
                          .add("url", url)
                          .add("isReserved", isReserved)
                          .add("inUse", inUse)
                          .toString();
    }
}
