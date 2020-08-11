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
import java.util.Map;
import java.util.Objects;

@JsonIgnoreProperties(ignoreUnknown = true)
public class AuthCallsIpAccessControlListMapping extends Resource {
    private static final long serialVersionUID = 271887954844194L;

    /**
     * Create a AuthCallsIpAccessControlListMappingCreator to execute create.
     *
     * @param pathAccountSid The SID of the Account that will create the resource
     * @param pathDomainSid The SID of the SIP domain that will contain the new
     *                      resource
     * @param ipAccessControlListSid The SID of the IpAccessControlList resource to
     *                               map to the SIP domain
     * @return AuthCallsIpAccessControlListMappingCreator capable of executing the
     *         create
     */
    public static AuthCallsIpAccessControlListMappingCreator creator(final String pathAccountSid,
                                                                     final String pathDomainSid,
                                                                     final String ipAccessControlListSid) {
        return new AuthCallsIpAccessControlListMappingCreator(pathAccountSid, pathDomainSid, ipAccessControlListSid);
    }

    /**
     * Create a AuthCallsIpAccessControlListMappingCreator to execute create.
     *
     * @param pathDomainSid The SID of the SIP domain that will contain the new
     *                      resource
     * @param ipAccessControlListSid The SID of the IpAccessControlList resource to
     *                               map to the SIP domain
     * @return AuthCallsIpAccessControlListMappingCreator capable of executing the
     *         create
     */
    public static AuthCallsIpAccessControlListMappingCreator creator(final String pathDomainSid,
                                                                     final String ipAccessControlListSid) {
        return new AuthCallsIpAccessControlListMappingCreator(pathDomainSid, ipAccessControlListSid);
    }

    /**
     * Create a AuthCallsIpAccessControlListMappingReader to execute read.
     *
     * @param pathAccountSid The SID of the Account that created the resources to
     *                       read
     * @param pathDomainSid The SID of the SIP domain that contains the resources
     *                      to read
     * @return AuthCallsIpAccessControlListMappingReader capable of executing the
     *         read
     */
    public static AuthCallsIpAccessControlListMappingReader reader(final String pathAccountSid,
                                                                   final String pathDomainSid) {
        return new AuthCallsIpAccessControlListMappingReader(pathAccountSid, pathDomainSid);
    }

    /**
     * Create a AuthCallsIpAccessControlListMappingReader to execute read.
     *
     * @param pathDomainSid The SID of the SIP domain that contains the resources
     *                      to read
     * @return AuthCallsIpAccessControlListMappingReader capable of executing the
     *         read
     */
    public static AuthCallsIpAccessControlListMappingReader reader(final String pathDomainSid) {
        return new AuthCallsIpAccessControlListMappingReader(pathDomainSid);
    }

    /**
     * Create a AuthCallsIpAccessControlListMappingFetcher to execute fetch.
     *
     * @param pathAccountSid The SID of the Account that created the resource to
     *                       fetch
     * @param pathDomainSid The SID of the SIP domain that contains the resource to
     *                      fetch
     * @param pathSid The unique string that identifies the resource
     * @return AuthCallsIpAccessControlListMappingFetcher capable of executing the
     *         fetch
     */
    public static AuthCallsIpAccessControlListMappingFetcher fetcher(final String pathAccountSid,
                                                                     final String pathDomainSid,
                                                                     final String pathSid) {
        return new AuthCallsIpAccessControlListMappingFetcher(pathAccountSid, pathDomainSid, pathSid);
    }

    /**
     * Create a AuthCallsIpAccessControlListMappingFetcher to execute fetch.
     *
     * @param pathDomainSid The SID of the SIP domain that contains the resource to
     *                      fetch
     * @param pathSid The unique string that identifies the resource
     * @return AuthCallsIpAccessControlListMappingFetcher capable of executing the
     *         fetch
     */
    public static AuthCallsIpAccessControlListMappingFetcher fetcher(final String pathDomainSid,
                                                                     final String pathSid) {
        return new AuthCallsIpAccessControlListMappingFetcher(pathDomainSid, pathSid);
    }

    /**
     * Create a AuthCallsIpAccessControlListMappingDeleter to execute delete.
     *
     * @param pathAccountSid The SID of the Account that created the resources to
     *                       delete
     * @param pathDomainSid The SID of the SIP domain that contains the resources
     *                      to delete
     * @param pathSid The unique string that identifies the resource
     * @return AuthCallsIpAccessControlListMappingDeleter capable of executing the
     *         delete
     */
    public static AuthCallsIpAccessControlListMappingDeleter deleter(final String pathAccountSid,
                                                                     final String pathDomainSid,
                                                                     final String pathSid) {
        return new AuthCallsIpAccessControlListMappingDeleter(pathAccountSid, pathDomainSid, pathSid);
    }

    /**
     * Create a AuthCallsIpAccessControlListMappingDeleter to execute delete.
     *
     * @param pathDomainSid The SID of the SIP domain that contains the resources
     *                      to delete
     * @param pathSid The unique string that identifies the resource
     * @return AuthCallsIpAccessControlListMappingDeleter capable of executing the
     *         delete
     */
    public static AuthCallsIpAccessControlListMappingDeleter deleter(final String pathDomainSid,
                                                                     final String pathSid) {
        return new AuthCallsIpAccessControlListMappingDeleter(pathDomainSid, pathSid);
    }

    /**
     * Converts a JSON String into a AuthCallsIpAccessControlListMapping object
     * using the provided ObjectMapper.
     *
     * @param json Raw JSON String
     * @param objectMapper Jackson ObjectMapper
     * @return AuthCallsIpAccessControlListMapping object represented by the
     *         provided JSON
     */
    public static AuthCallsIpAccessControlListMapping fromJson(final String json, final ObjectMapper objectMapper) {
        // Convert all checked exceptions to Runtime
        try {
            return objectMapper.readValue(json, AuthCallsIpAccessControlListMapping.class);
        } catch (final JsonMappingException | JsonParseException e) {
            throw new ApiException(e.getMessage(), e);
        } catch (final IOException e) {
            throw new ApiConnectionException(e.getMessage(), e);
        }
    }

    /**
     * Converts a JSON InputStream into a AuthCallsIpAccessControlListMapping object
     * using the provided ObjectMapper.
     *
     * @param json Raw JSON InputStream
     * @param objectMapper Jackson ObjectMapper
     * @return AuthCallsIpAccessControlListMapping object represented by the
     *         provided JSON
     */
    public static AuthCallsIpAccessControlListMapping fromJson(final InputStream json, final ObjectMapper objectMapper) {
        // Convert all checked exceptions to Runtime
        try {
            return objectMapper.readValue(json, AuthCallsIpAccessControlListMapping.class);
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
    private AuthCallsIpAccessControlListMapping(@JsonProperty("account_sid")
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

        AuthCallsIpAccessControlListMapping other = (AuthCallsIpAccessControlListMapping) o;

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

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                          .add("accountSid", accountSid)
                          .add("dateCreated", dateCreated)
                          .add("dateUpdated", dateUpdated)
                          .add("friendlyName", friendlyName)
                          .add("sid", sid)
                          .toString();
    }
}
