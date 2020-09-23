/**
 * This code was generated by
 * \ / _    _  _|   _  _
 *  | (_)\/(_)(_|\/| |(/_  v1.0.0
 *       /       /
 */

package com.twilio.rest.api.v2010.account;

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
import org.joda.time.DateTime;

import java.io.IOException;
import java.io.InputStream;
import java.time.ZonedDateTime;
import java.util.Map;
import java.util.Objects;

@JsonIgnoreProperties(ignoreUnknown = true)
@ToString
public class Key extends Resource {
    private static final long serialVersionUID = 96350302686036L;

    /**
     * Create a KeyFetcher to execute fetch.
     *
     * @param pathAccountSid The SID of the Account that created the resource to
     *                       fetch
     * @param pathSid The unique string that identifies the resource
     * @return KeyFetcher capable of executing the fetch
     */
    public static KeyFetcher fetcher(final String pathAccountSid,
                                     final String pathSid) {
        return new KeyFetcher(pathAccountSid, pathSid);
    }

    /**
     * Create a KeyFetcher to execute fetch.
     *
     * @param pathSid The unique string that identifies the resource
     * @return KeyFetcher capable of executing the fetch
     */
    public static KeyFetcher fetcher(final String pathSid) {
        return new KeyFetcher(pathSid);
    }

    /**
     * Create a KeyUpdater to execute update.
     *
     * @param pathAccountSid The SID of the Account that created the resources to
     *                       update
     * @param pathSid The unique string that identifies the resource
     * @return KeyUpdater capable of executing the update
     */
    public static KeyUpdater updater(final String pathAccountSid,
                                     final String pathSid) {
        return new KeyUpdater(pathAccountSid, pathSid);
    }

    /**
     * Create a KeyUpdater to execute update.
     *
     * @param pathSid The unique string that identifies the resource
     * @return KeyUpdater capable of executing the update
     */
    public static KeyUpdater updater(final String pathSid) {
        return new KeyUpdater(pathSid);
    }

    /**
     * Create a KeyDeleter to execute delete.
     *
     * @param pathAccountSid The SID of the Account that created the resources to
     *                       delete
     * @param pathSid The unique string that identifies the resource
     * @return KeyDeleter capable of executing the delete
     */
    public static KeyDeleter deleter(final String pathAccountSid,
                                     final String pathSid) {
        return new KeyDeleter(pathAccountSid, pathSid);
    }

    /**
     * Create a KeyDeleter to execute delete.
     *
     * @param pathSid The unique string that identifies the resource
     * @return KeyDeleter capable of executing the delete
     */
    public static KeyDeleter deleter(final String pathSid) {
        return new KeyDeleter(pathSid);
    }

    /**
     * Create a KeyReader to execute read.
     *
     * @param pathAccountSid The SID of the Account that created the resources to
     *                       read
     * @return KeyReader capable of executing the read
     */
    public static KeyReader reader(final String pathAccountSid) {
        return new KeyReader(pathAccountSid);
    }

    /**
     * Create a KeyReader to execute read.
     *
     * @return KeyReader capable of executing the read
     */
    public static KeyReader reader() {
        return new KeyReader();
    }

    /**
     * Converts a JSON String into a Key object using the provided ObjectMapper.
     *
     * @param json Raw JSON String
     * @param objectMapper Jackson ObjectMapper
     * @return Key object represented by the provided JSON
     */
    public static Key fromJson(final String json, final ObjectMapper objectMapper) {
        // Convert all checked exceptions to Runtime
        try {
            return objectMapper.readValue(json, Key.class);
        } catch (final JsonMappingException | JsonParseException e) {
            throw new ApiException(e.getMessage(), e);
        } catch (final IOException e) {
            throw new ApiConnectionException(e.getMessage(), e);
        }
    }

    /**
     * Converts a JSON InputStream into a Key object using the provided
     * ObjectMapper.
     *
     * @param json Raw JSON InputStream
     * @param objectMapper Jackson ObjectMapper
     * @return Key object represented by the provided JSON
     */
    public static Key fromJson(final InputStream json, final ObjectMapper objectMapper) {
        // Convert all checked exceptions to Runtime
        try {
            return objectMapper.readValue(json, Key.class);
        } catch (final JsonMappingException | JsonParseException e) {
            throw new ApiException(e.getMessage(), e);
        } catch (final IOException e) {
            throw new ApiConnectionException(e.getMessage(), e);
        }
    }

    private final String sid;
    private final String friendlyName;
    private final ZonedDateTime dateCreated;
    private final ZonedDateTime dateUpdated;

    @JsonCreator
    private Key(@JsonProperty("sid")
                final String sid,
                @JsonProperty("friendly_name")
                final String friendlyName,
                @JsonProperty("date_created")
                final String dateCreated,
                @JsonProperty("date_updated")
                final String dateUpdated) {
        this.sid = sid;
        this.friendlyName = friendlyName;
        this.dateCreated = DateConverter.rfc2822DateTimeFromString(dateCreated);
        this.dateUpdated = DateConverter.rfc2822DateTimeFromString(dateUpdated);
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
     * Returns The string that you assigned to describe the resource.
     *
     * @return The string that you assigned to describe the resource
     */
    public final String getFriendlyName() {
        return this.friendlyName;
    }

    /**
     * Returns The RFC 2822 date and time in GMT that the resource was created.
     *
     * @return The RFC 2822 date and time in GMT that the resource was created
     */
    public final ZonedDateTime getDateCreated() {
        return this.dateCreated;
    }

    /**
     * Returns The RFC 2822 date and time in GMT that the resource was last updated.
     *
     * @return The RFC 2822 date and time in GMT that the resource was last updated
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

        Key other = (Key) o;

        return Objects.equals(sid, other.sid) &&
               Objects.equals(friendlyName, other.friendlyName) &&
               Objects.equals(dateCreated, other.dateCreated) &&
               Objects.equals(dateUpdated, other.dateUpdated);
    }

    @Override
    public int hashCode() {
        return Objects.hash(sid,
                            friendlyName,
                            dateCreated,
                            dateUpdated);
    }
}
