/**
 * This code was generated by
 * \ / _    _  _|   _  _
 *  | (_)\/(_)(_|\/| |(/_  v1.0.0
 *       /       /
 */

package com.twilio.rest.api.v2010.account.sip.domain.authtypes.authtypecalls;

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
import java.time.ZonedDateTime;
import java.util.Objects;

@JsonIgnoreProperties(ignoreUnknown = true)
public class AuthCallsCredentialListMapping extends Resource {
    private static final long serialVersionUID = 271887954844194L;

    /**
     * Create a AuthCallsCredentialListMappingCreator to execute create.
     *
     * @param pathAccountSid The SID of the Account that will create the resource
     * @param pathDomainSid The SID of the SIP domain that will contain the new
     *                      resource
     * @param credentialListSid The SID of the CredentialList resource to map to
     *                          the SIP domain
     * @return AuthCallsCredentialListMappingCreator capable of executing the create
     */
    public static AuthCallsCredentialListMappingCreator creator(final String pathAccountSid,
                                                                final String pathDomainSid,
                                                                final String credentialListSid) {
        return new AuthCallsCredentialListMappingCreator(pathAccountSid, pathDomainSid, credentialListSid);
    }

    /**
     * Create a AuthCallsCredentialListMappingCreator to execute create.
     *
     * @param pathDomainSid The SID of the SIP domain that will contain the new
     *                      resource
     * @param credentialListSid The SID of the CredentialList resource to map to
     *                          the SIP domain
     * @return AuthCallsCredentialListMappingCreator capable of executing the create
     */
    public static AuthCallsCredentialListMappingCreator creator(final String pathDomainSid,
                                                                final String credentialListSid) {
        return new AuthCallsCredentialListMappingCreator(pathDomainSid, credentialListSid);
    }

    /**
     * Create a AuthCallsCredentialListMappingReader to execute read.
     *
     * @param pathAccountSid The SID of the Account that created the resources to
     *                       read
     * @param pathDomainSid The SID of the SIP domain that contains the resources
     *                      to read
     * @return AuthCallsCredentialListMappingReader capable of executing the read
     */
    public static AuthCallsCredentialListMappingReader reader(final String pathAccountSid,
                                                              final String pathDomainSid) {
        return new AuthCallsCredentialListMappingReader(pathAccountSid, pathDomainSid);
    }

    /**
     * Create a AuthCallsCredentialListMappingReader to execute read.
     *
     * @param pathDomainSid The SID of the SIP domain that contains the resources
     *                      to read
     * @return AuthCallsCredentialListMappingReader capable of executing the read
     */
    public static AuthCallsCredentialListMappingReader reader(final String pathDomainSid) {
        return new AuthCallsCredentialListMappingReader(pathDomainSid);
    }

    /**
     * Create a AuthCallsCredentialListMappingFetcher to execute fetch.
     *
     * @param pathAccountSid The SID of the Account that created the resource to
     *                       fetch
     * @param pathDomainSid The SID of the SIP domain that contains the resource to
     *                      fetch
     * @param pathSid The unique string that identifies the resource
     * @return AuthCallsCredentialListMappingFetcher capable of executing the fetch
     */
    public static AuthCallsCredentialListMappingFetcher fetcher(final String pathAccountSid,
                                                                final String pathDomainSid,
                                                                final String pathSid) {
        return new AuthCallsCredentialListMappingFetcher(pathAccountSid, pathDomainSid, pathSid);
    }

    /**
     * Create a AuthCallsCredentialListMappingFetcher to execute fetch.
     *
     * @param pathDomainSid The SID of the SIP domain that contains the resource to
     *                      fetch
     * @param pathSid The unique string that identifies the resource
     * @return AuthCallsCredentialListMappingFetcher capable of executing the fetch
     */
    public static AuthCallsCredentialListMappingFetcher fetcher(final String pathDomainSid,
                                                                final String pathSid) {
        return new AuthCallsCredentialListMappingFetcher(pathDomainSid, pathSid);
    }

    /**
     * Create a AuthCallsCredentialListMappingDeleter to execute delete.
     *
     * @param pathAccountSid The SID of the Account that created the resources to
     *                       delete
     * @param pathDomainSid The SID of the SIP domain that contains the resource to
     *                      delete
     * @param pathSid The unique string that identifies the resource
     * @return AuthCallsCredentialListMappingDeleter capable of executing the delete
     */
    public static AuthCallsCredentialListMappingDeleter deleter(final String pathAccountSid,
                                                                final String pathDomainSid,
                                                                final String pathSid) {
        return new AuthCallsCredentialListMappingDeleter(pathAccountSid, pathDomainSid, pathSid);
    }

    /**
     * Create a AuthCallsCredentialListMappingDeleter to execute delete.
     *
     * @param pathDomainSid The SID of the SIP domain that contains the resource to
     *                      delete
     * @param pathSid The unique string that identifies the resource
     * @return AuthCallsCredentialListMappingDeleter capable of executing the delete
     */
    public static AuthCallsCredentialListMappingDeleter deleter(final String pathDomainSid,
                                                                final String pathSid) {
        return new AuthCallsCredentialListMappingDeleter(pathDomainSid, pathSid);
    }

    /**
     * Converts a JSON String into a AuthCallsCredentialListMapping object using the
     * provided ObjectMapper.
     *
     * @param json Raw JSON String
     * @param objectMapper Jackson ObjectMapper
     * @return AuthCallsCredentialListMapping object represented by the provided
     *         JSON
     */
    public static AuthCallsCredentialListMapping fromJson(final String json, final ObjectMapper objectMapper) {
        // Convert all checked exceptions to Runtime
        try {
            return objectMapper.readValue(json, AuthCallsCredentialListMapping.class);
        } catch (final JsonMappingException | JsonParseException e) {
            throw new ApiException(e.getMessage(), e);
        } catch (final IOException e) {
            throw new ApiConnectionException(e.getMessage(), e);
        }
    }

    /**
     * Converts a JSON InputStream into a AuthCallsCredentialListMapping object
     * using the provided ObjectMapper.
     *
     * @param json Raw JSON InputStream
     * @param objectMapper Jackson ObjectMapper
     * @return AuthCallsCredentialListMapping object represented by the provided
     *         JSON
     */
    public static AuthCallsCredentialListMapping fromJson(final InputStream json, final ObjectMapper objectMapper) {
        // Convert all checked exceptions to Runtime
        try {
            return objectMapper.readValue(json, AuthCallsCredentialListMapping.class);
        } catch (final JsonMappingException | JsonParseException e) {
            throw new ApiException(e.getMessage(), e);
        } catch (final IOException e) {
            throw new ApiConnectionException(e.getMessage(), e);
        }
    }

    private final String accountSid;
    private final ZonedDateTime dateCreated;
    private final ZonedDateTime dateUpdated;
    private final String friendlyName;
    private final String sid;

    @JsonCreator
    private AuthCallsCredentialListMapping(@JsonProperty("account_sid")
                                           final String accountSid,
                                           @JsonProperty("date_created")
                                           final String dateCreated,
                                           @JsonProperty("date_updated")
                                           final String dateUpdated,
                                           @JsonProperty("friendly_name")
                                           final String friendlyName,
                                           @JsonProperty("sid")
                                           final String sid) {
        this.accountSid = accountSid;
        this.dateCreated = DateConverter.rfc2822DateTimeFromString(dateCreated);
        this.dateUpdated = DateConverter.rfc2822DateTimeFromString(dateUpdated);
        this.friendlyName = friendlyName;
        this.sid = sid;
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

    /**
     * Returns The string that you assigned to describe the resource.
     *
     * @return The string that you assigned to describe the resource
     */
    public final String getFriendlyName() {
        return this.friendlyName;
    }

    /**
     * Returns The unique string that identifies the resource.
     *
     * @return The unique string that identifies the resource
     */
    public final String getSid() {
        return this.sid;
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }

        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        AuthCallsCredentialListMapping other = (AuthCallsCredentialListMapping) o;

        return Objects.equals(accountSid, other.accountSid) &&
               Objects.equals(dateCreated, other.dateCreated) &&
               Objects.equals(dateUpdated, other.dateUpdated) &&
               Objects.equals(friendlyName, other.friendlyName) &&
               Objects.equals(sid, other.sid);
    }

    @Override
    public int hashCode() {
        return Objects.hash(accountSid,
                            dateCreated,
                            dateUpdated,
                            friendlyName,
                            sid);
    }

  public String toString() {
    return "AuthCallsCredentialListMapping(accountSid=" + this.getAccountSid() + ", dateCreated=" + this.getDateCreated() + ", dateUpdated=" + this.getDateUpdated() + ", friendlyName=" + this.getFriendlyName() + ", sid=" + this.getSid() + ")";
  }
}
