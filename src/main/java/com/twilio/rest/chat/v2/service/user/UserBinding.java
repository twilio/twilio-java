/**
 * This code was generated by
 * \ / _    _  _|   _  _
 *  | (_)\/(_)(_|\/| |(/_  v1.0.0
 *       /       /
 */

package com.twilio.rest.chat.v2.service.user;

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
import java.util.List;
import java.util.Map;
import java.util.Objects;

@JsonIgnoreProperties(ignoreUnknown = true)
@ToString
public class UserBinding extends Resource {
    private static final long serialVersionUID = 241481961692245L;

    public enum BindingType {
        GCM("gcm"),
        APN("apn"),
        FCM("fcm");

        private final String value;

        private BindingType(final String value) {
            this.value = value;
        }

        public String toString() {
            return value;
        }

        /**
         * Generate a BindingType from a string.
         * @param value string value
         * @return generated BindingType
         */
        @JsonCreator
        public static BindingType forValue(final String value) {
            return Promoter.enumFromString(value, BindingType.values());
        }
    }

    /**
     * Create a UserBindingReader to execute read.
     *
     * @param pathServiceSid The SID of the Service to read the resource from
     * @param pathUserSid The SID of the User with the User Bindings to read
     * @return UserBindingReader capable of executing the read
     */
    public static UserBindingReader reader(final String pathServiceSid,
                                           final String pathUserSid) {
        return new UserBindingReader(pathServiceSid, pathUserSid);
    }

    /**
     * Create a UserBindingFetcher to execute fetch.
     *
     * @param pathServiceSid The SID of the Service to fetch the resource from
     * @param pathUserSid The SID of the User with the binding
     * @param pathSid The SID of the User Binding resource to fetch
     * @return UserBindingFetcher capable of executing the fetch
     */
    public static UserBindingFetcher fetcher(final String pathServiceSid,
                                             final String pathUserSid,
                                             final String pathSid) {
        return new UserBindingFetcher(pathServiceSid, pathUserSid, pathSid);
    }

    /**
     * Create a UserBindingDeleter to execute delete.
     *
     * @param pathServiceSid The SID of the Service to delete the resource from
     * @param pathUserSid The SID of the User of the User Bindings to delete
     * @param pathSid The SID of the User Binding resource to delete
     * @return UserBindingDeleter capable of executing the delete
     */
    public static UserBindingDeleter deleter(final String pathServiceSid,
                                             final String pathUserSid,
                                             final String pathSid) {
        return new UserBindingDeleter(pathServiceSid, pathUserSid, pathSid);
    }

    /**
     * Converts a JSON String into a UserBinding object using the provided
     * ObjectMapper.
     *
     * @param json Raw JSON String
     * @param objectMapper Jackson ObjectMapper
     * @return UserBinding object represented by the provided JSON
     */
    public static UserBinding fromJson(final String json, final ObjectMapper objectMapper) {
        // Convert all checked exceptions to Runtime
        try {
            return objectMapper.readValue(json, UserBinding.class);
        } catch (final JsonMappingException | JsonParseException e) {
            throw new ApiException(e.getMessage(), e);
        } catch (final IOException e) {
            throw new ApiConnectionException(e.getMessage(), e);
        }
    }

    /**
     * Converts a JSON InputStream into a UserBinding object using the provided
     * ObjectMapper.
     *
     * @param json Raw JSON InputStream
     * @param objectMapper Jackson ObjectMapper
     * @return UserBinding object represented by the provided JSON
     */
    public static UserBinding fromJson(final InputStream json, final ObjectMapper objectMapper) {
        // Convert all checked exceptions to Runtime
        try {
            return objectMapper.readValue(json, UserBinding.class);
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
    private final String endpoint;
    private final String identity;
    private final String userSid;
    private final String credentialSid;
    private final UserBinding.BindingType bindingType;
    private final List<String> messageTypes;
    private final URI url;

    @JsonCreator
    private UserBinding(@JsonProperty("sid")
                        final String sid,
                        @JsonProperty("account_sid")
                        final String accountSid,
                        @JsonProperty("service_sid")
                        final String serviceSid,
                        @JsonProperty("date_created")
                        final String dateCreated,
                        @JsonProperty("date_updated")
                        final String dateUpdated,
                        @JsonProperty("endpoint")
                        final String endpoint,
                        @JsonProperty("identity")
                        final String identity,
                        @JsonProperty("user_sid")
                        final String userSid,
                        @JsonProperty("credential_sid")
                        final String credentialSid,
                        @JsonProperty("binding_type")
                        final UserBinding.BindingType bindingType,
                        @JsonProperty("message_types")
                        final List<String> messageTypes,
                        @JsonProperty("url")
                        final URI url) {
        this.sid = sid;
        this.accountSid = accountSid;
        this.serviceSid = serviceSid;
        this.dateCreated = DateConverter.iso8601DateTimeFromString(dateCreated);
        this.dateUpdated = DateConverter.iso8601DateTimeFromString(dateUpdated);
        this.endpoint = endpoint;
        this.identity = identity;
        this.userSid = userSid;
        this.credentialSid = credentialSid;
        this.bindingType = bindingType;
        this.messageTypes = messageTypes;
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
     * Returns The unique endpoint identifier for the User Binding.
     *
     * @return The unique endpoint identifier for the User Binding
     */
    public final String getEndpoint() {
        return this.endpoint;
    }

    /**
     * Returns The string that identifies the resource's User.
     *
     * @return The string that identifies the resource's User
     */
    public final String getIdentity() {
        return this.identity;
    }

    /**
     * Returns The SID of the User with the binding.
     *
     * @return The SID of the User with the binding
     */
    public final String getUserSid() {
        return this.userSid;
    }

    /**
     * Returns The SID of the Credential for the binding.
     *
     * @return The SID of the Credential for the binding
     */
    public final String getCredentialSid() {
        return this.credentialSid;
    }

    /**
     * Returns The push technology to use for the binding.
     *
     * @return The push technology to use for the binding
     */
    public final UserBinding.BindingType getBindingType() {
        return this.bindingType;
    }

    /**
     * Returns The Programmable Chat message types the binding is subscribed to.
     *
     * @return The Programmable Chat message types the binding is subscribed to
     */
    public final List<String> getMessageTypes() {
        return this.messageTypes;
    }

    /**
     * Returns The absolute URL of the User Binding resource.
     *
     * @return The absolute URL of the User Binding resource
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

        UserBinding other = (UserBinding) o;

        return Objects.equals(sid, other.sid) &&
               Objects.equals(accountSid, other.accountSid) &&
               Objects.equals(serviceSid, other.serviceSid) &&
               Objects.equals(dateCreated, other.dateCreated) &&
               Objects.equals(dateUpdated, other.dateUpdated) &&
               Objects.equals(endpoint, other.endpoint) &&
               Objects.equals(identity, other.identity) &&
               Objects.equals(userSid, other.userSid) &&
               Objects.equals(credentialSid, other.credentialSid) &&
               Objects.equals(bindingType, other.bindingType) &&
               Objects.equals(messageTypes, other.messageTypes) &&
               Objects.equals(url, other.url);
    }

    @Override
    public int hashCode() {
        return Objects.hash(sid,
                            accountSid,
                            serviceSid,
                            dateCreated,
                            dateUpdated,
                            endpoint,
                            identity,
                            userSid,
                            credentialSid,
                            bindingType,
                            messageTypes,
                            url);
    }
}
