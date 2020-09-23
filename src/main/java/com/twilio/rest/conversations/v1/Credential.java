/**
 * This code was generated by
 * \ / _    _  _|   _  _
 *  | (_)\/(_)(_|\/| |(/_  v1.0.0
 *       /       /
 */

package com.twilio.rest.conversations.v1;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.twilio.base.Resource;
import com.twilio.converter.DateConverter;
import com.twilio.converter.Promoter;
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
import java.net.URI;
import java.time.ZonedDateTime;
import java.util.Map;
import java.util.Objects;

/**
 * PLEASE NOTE that this class contains beta products that are subject to
 * change. Use them with caution.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
@ToString
public class Credential extends Resource {
    private static final long serialVersionUID = 225197611451747L;

    public enum PushType {
        APN("apn"),
        GCM("gcm"),
        FCM("fcm");

        private final String value;

        private PushType(final String value) {
            this.value = value;
        }

        public String toString() {
            return value;
        }

        /**
         * Generate a PushType from a string.
         * @param value string value
         * @return generated PushType
         */
        @JsonCreator
        public static PushType forValue(final String value) {
            return Promoter.enumFromString(value, PushType.values());
        }
    }

    /**
     * Create a CredentialCreator to execute create.
     *
     * @param type The type of push-notification service the credential is for.
     * @return CredentialCreator capable of executing the create
     */
    public static CredentialCreator creator(final Credential.PushType type) {
        return new CredentialCreator(type);
    }

    /**
     * Create a CredentialUpdater to execute update.
     *
     * @param pathSid A 34 character string that uniquely identifies this resource.
     * @return CredentialUpdater capable of executing the update
     */
    public static CredentialUpdater updater(final String pathSid) {
        return new CredentialUpdater(pathSid);
    }

    /**
     * Create a CredentialDeleter to execute delete.
     *
     * @param pathSid A 34 character string that uniquely identifies this resource.
     * @return CredentialDeleter capable of executing the delete
     */
    public static CredentialDeleter deleter(final String pathSid) {
        return new CredentialDeleter(pathSid);
    }

    /**
     * Create a CredentialFetcher to execute fetch.
     *
     * @param pathSid A 34 character string that uniquely identifies this resource.
     * @return CredentialFetcher capable of executing the fetch
     */
    public static CredentialFetcher fetcher(final String pathSid) {
        return new CredentialFetcher(pathSid);
    }

    /**
     * Create a CredentialReader to execute read.
     *
     * @return CredentialReader capable of executing the read
     */
    public static CredentialReader reader() {
        return new CredentialReader();
    }

    /**
     * Converts a JSON String into a Credential object using the provided
     * ObjectMapper.
     *
     * @param json Raw JSON String
     * @param objectMapper Jackson ObjectMapper
     * @return Credential object represented by the provided JSON
     */
    public static Credential fromJson(final String json, final ObjectMapper objectMapper) {
        // Convert all checked exceptions to Runtime
        try {
            return objectMapper.readValue(json, Credential.class);
        } catch (final JsonMappingException | JsonParseException e) {
            throw new ApiException(e.getMessage(), e);
        } catch (final IOException e) {
            throw new ApiConnectionException(e.getMessage(), e);
        }
    }

    /**
     * Converts a JSON InputStream into a Credential object using the provided
     * ObjectMapper.
     *
     * @param json Raw JSON InputStream
     * @param objectMapper Jackson ObjectMapper
     * @return Credential object represented by the provided JSON
     */
    public static Credential fromJson(final InputStream json, final ObjectMapper objectMapper) {
        // Convert all checked exceptions to Runtime
        try {
            return objectMapper.readValue(json, Credential.class);
        } catch (final JsonMappingException | JsonParseException e) {
            throw new ApiException(e.getMessage(), e);
        } catch (final IOException e) {
            throw new ApiConnectionException(e.getMessage(), e);
        }
    }

    private final String sid;
    private final String accountSid;
    private final String friendlyName;
    private final Credential.PushType type;
    private final String sandbox;
    private final ZonedDateTime dateCreated;
    private final ZonedDateTime dateUpdated;
    private final URI url;

    @JsonCreator
    private Credential(@JsonProperty("sid")
                       final String sid,
                       @JsonProperty("account_sid")
                       final String accountSid,
                       @JsonProperty("friendly_name")
                       final String friendlyName,
                       @JsonProperty("type")
                       final Credential.PushType type,
                       @JsonProperty("sandbox")
                       final String sandbox,
                       @JsonProperty("date_created")
                       final String dateCreated,
                       @JsonProperty("date_updated")
                       final String dateUpdated,
                       @JsonProperty("url")
                       final URI url) {
        this.sid = sid;
        this.accountSid = accountSid;
        this.friendlyName = friendlyName;
        this.type = type;
        this.sandbox = sandbox;
        this.dateCreated = DateConverter.iso8601DateTimeFromString(dateCreated);
        this.dateUpdated = DateConverter.iso8601DateTimeFromString(dateUpdated);
        this.url = url;
    }

    /**
     * Returns A 34 character string that uniquely identifies this resource..
     *
     * @return A 34 character string that uniquely identifies this resource.
     */
    public final String getSid() {
        return this.sid;
    }

    /**
     * Returns The unique ID of the Account responsible for this credential..
     *
     * @return The unique ID of the Account responsible for this credential.
     */
    public final String getAccountSid() {
        return this.accountSid;
    }

    /**
     * Returns The human-readable name of this credential..
     *
     * @return The human-readable name of this credential.
     */
    public final String getFriendlyName() {
        return this.friendlyName;
    }

    /**
     * Returns The type of push-notification service the credential is for..
     *
     * @return The type of push-notification service the credential is for.
     */
    public final Credential.PushType getType() {
        return this.type;
    }

    /**
     * Returns [APN only] Whether to send the credential to sandbox APNs..
     *
     * @return [APN only] Whether to send the credential to sandbox APNs.
     */
    public final String getSandbox() {
        return this.sandbox;
    }

    /**
     * Returns The date that this resource was created..
     *
     * @return The date that this resource was created.
     */
    public final ZonedDateTime getDateCreated() {
        return this.dateCreated;
    }

    /**
     * Returns The date that this resource was last updated..
     *
     * @return The date that this resource was last updated.
     */
    public final ZonedDateTime getDateUpdated() {
        return this.dateUpdated;
    }

    /**
     * Returns An absolute URL for this credential..
     *
     * @return An absolute URL for this credential.
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

        Credential other = (Credential) o;

        return Objects.equals(sid, other.sid) &&
               Objects.equals(accountSid, other.accountSid) &&
               Objects.equals(friendlyName, other.friendlyName) &&
               Objects.equals(type, other.type) &&
               Objects.equals(sandbox, other.sandbox) &&
               Objects.equals(dateCreated, other.dateCreated) &&
               Objects.equals(dateUpdated, other.dateUpdated) &&
               Objects.equals(url, other.url);
    }

    @Override
    public int hashCode() {
        return Objects.hash(sid,
                            accountSid,
                            friendlyName,
                            type,
                            sandbox,
                            dateCreated,
                            dateUpdated,
                            url);
    }
}
