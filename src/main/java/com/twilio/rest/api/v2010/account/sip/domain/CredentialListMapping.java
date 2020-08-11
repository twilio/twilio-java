/**
 * This code was generated by
 * \ / _    _  _|   _  _
 *  | (_)\/(_)(_|\/| |(/_  v1.0.0
 *       /       /
 */

package com.twilio.rest.api.v2010.account.sip.domain;

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
public class CredentialListMapping extends Resource {
    private static final long serialVersionUID = 214188792953524L;

    /**
     * Create a CredentialListMappingCreator to execute create.
     *
     * @param pathAccountSid The unique sid that identifies this account
     * @param pathDomainSid A string that identifies the SIP Domain for which the
     *                      CredentialList resource will be mapped
     * @param credentialListSid A string that identifies the CredentialList
     *                          resource to map to the SIP domain
     * @return CredentialListMappingCreator capable of executing the create
     */
    public static CredentialListMappingCreator creator(final String pathAccountSid,
                                                       final String pathDomainSid,
                                                       final String credentialListSid) {
        return new CredentialListMappingCreator(pathAccountSid, pathDomainSid, credentialListSid);
    }

    /**
     * Create a CredentialListMappingCreator to execute create.
     *
     * @param pathDomainSid A string that identifies the SIP Domain for which the
     *                      CredentialList resource will be mapped
     * @param credentialListSid A string that identifies the CredentialList
     *                          resource to map to the SIP domain
     * @return CredentialListMappingCreator capable of executing the create
     */
    public static CredentialListMappingCreator creator(final String pathDomainSid,
                                                       final String credentialListSid) {
        return new CredentialListMappingCreator(pathDomainSid, credentialListSid);
    }

    /**
     * Create a CredentialListMappingReader to execute read.
     *
     * @param pathAccountSid The unique sid that identifies this account
     * @param pathDomainSid A string that identifies the SIP Domain that includes
     *                      the resource to read
     * @return CredentialListMappingReader capable of executing the read
     */
    public static CredentialListMappingReader reader(final String pathAccountSid,
                                                     final String pathDomainSid) {
        return new CredentialListMappingReader(pathAccountSid, pathDomainSid);
    }

    /**
     * Create a CredentialListMappingReader to execute read.
     *
     * @param pathDomainSid A string that identifies the SIP Domain that includes
     *                      the resource to read
     * @return CredentialListMappingReader capable of executing the read
     */
    public static CredentialListMappingReader reader(final String pathDomainSid) {
        return new CredentialListMappingReader(pathDomainSid);
    }

    /**
     * Create a CredentialListMappingFetcher to execute fetch.
     *
     * @param pathAccountSid The unique sid that identifies this account
     * @param pathDomainSid A string that identifies the SIP Domain that includes
     *                      the resource to fetch
     * @param pathSid A string that identifies the resource to fetch
     * @return CredentialListMappingFetcher capable of executing the fetch
     */
    public static CredentialListMappingFetcher fetcher(final String pathAccountSid,
                                                       final String pathDomainSid,
                                                       final String pathSid) {
        return new CredentialListMappingFetcher(pathAccountSid, pathDomainSid, pathSid);
    }

    /**
     * Create a CredentialListMappingFetcher to execute fetch.
     *
     * @param pathDomainSid A string that identifies the SIP Domain that includes
     *                      the resource to fetch
     * @param pathSid A string that identifies the resource to fetch
     * @return CredentialListMappingFetcher capable of executing the fetch
     */
    public static CredentialListMappingFetcher fetcher(final String pathDomainSid,
                                                       final String pathSid) {
        return new CredentialListMappingFetcher(pathDomainSid, pathSid);
    }

    /**
     * Create a CredentialListMappingDeleter to execute delete.
     *
     * @param pathAccountSid The unique sid that identifies this account
     * @param pathDomainSid A string that identifies the SIP Domain that includes
     *                      the resource to delete
     * @param pathSid A string that identifies the resource to delete
     * @return CredentialListMappingDeleter capable of executing the delete
     */
    public static CredentialListMappingDeleter deleter(final String pathAccountSid,
                                                       final String pathDomainSid,
                                                       final String pathSid) {
        return new CredentialListMappingDeleter(pathAccountSid, pathDomainSid, pathSid);
    }

    /**
     * Create a CredentialListMappingDeleter to execute delete.
     *
     * @param pathDomainSid A string that identifies the SIP Domain that includes
     *                      the resource to delete
     * @param pathSid A string that identifies the resource to delete
     * @return CredentialListMappingDeleter capable of executing the delete
     */
    public static CredentialListMappingDeleter deleter(final String pathDomainSid,
                                                       final String pathSid) {
        return new CredentialListMappingDeleter(pathDomainSid, pathSid);
    }

    /**
     * Converts a JSON String into a CredentialListMapping object using the provided
     * ObjectMapper.
     *
     * @param json Raw JSON String
     * @param objectMapper Jackson ObjectMapper
     * @return CredentialListMapping object represented by the provided JSON
     */
    public static CredentialListMapping fromJson(final String json, final ObjectMapper objectMapper) {
        // Convert all checked exceptions to Runtime
        try {
            return objectMapper.readValue(json, CredentialListMapping.class);
        } catch (final JsonMappingException | JsonParseException e) {
            throw new ApiException(e.getMessage(), e);
        } catch (final IOException e) {
            throw new ApiConnectionException(e.getMessage(), e);
        }
    }

    /**
     * Converts a JSON InputStream into a CredentialListMapping object using the
     * provided ObjectMapper.
     *
     * @param json Raw JSON InputStream
     * @param objectMapper Jackson ObjectMapper
     * @return CredentialListMapping object represented by the provided JSON
     */
    public static CredentialListMapping fromJson(final InputStream json, final ObjectMapper objectMapper) {
        // Convert all checked exceptions to Runtime
        try {
            return objectMapper.readValue(json, CredentialListMapping.class);
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
    private final String uri;
    private final Map<String, String> subresourceUris;

    @JsonCreator
    private CredentialListMapping(@JsonProperty("account_sid")
                                  final String accountSid,
                                  @JsonProperty("date_created")
                                  final String dateCreated,
                                  @JsonProperty("date_updated")
                                  final String dateUpdated,
                                  @JsonProperty("friendly_name")
                                  final String friendlyName,
                                  @JsonProperty("sid")
                                  final String sid,
                                  @JsonProperty("uri")
                                  final String uri,
                                  @JsonProperty("subresource_uris")
                                  final Map<String, String> subresourceUris) {
        this.accountSid = accountSid;
        this.dateCreated = DateConverter.rfc2822DateTimeFromString(dateCreated);
        this.dateUpdated = DateConverter.rfc2822DateTimeFromString(dateUpdated);
        this.friendlyName = friendlyName;
        this.sid = sid;
        this.uri = uri;
        this.subresourceUris = subresourceUris;
    }

    /**
     * Returns The unique id of the Account that is responsible for this resource..
     *
     * @return The unique id of the Account that is responsible for this resource.
     */
    public final String getAccountSid() {
        return this.accountSid;
    }

    /**
     * Returns The date that this resource was created, given as GMT in RFC 2822
     * format..
     *
     * @return The date that this resource was created, given as GMT in RFC 2822
     *         format.
     */
    public final ZonedDateTime getDateCreated() {
        return this.dateCreated;
    }

    /**
     * Returns The date that this resource was last updated, given as GMT in RFC
     * 2822 format..
     *
     * @return The date that this resource was last updated, given as GMT in RFC
     *         2822 format.
     */
    public final ZonedDateTime getDateUpdated() {
        return this.dateUpdated;
    }

    /**
     * Returns A human readable descriptive text for this resource, up to 64
     * characters long..
     *
     * @return A human readable descriptive text for this resource, up to 64
     *         characters long.
     */
    public final String getFriendlyName() {
        return this.friendlyName;
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
     * Returns The URI for this resource, relative to https://api.twilio.com.
     *
     * @return The URI for this resource, relative to https://api.twilio.com
     */
    public final String getUri() {
        return this.uri;
    }

    /**
     * Returns The credentials associated with this resource..
     *
     * @return The credentials associated with this resource.
     */
    public final Map<String, String> getSubresourceUris() {
        return this.subresourceUris;
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }

        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        CredentialListMapping other = (CredentialListMapping) o;

        return Objects.equals(accountSid, other.accountSid) &&
               Objects.equals(dateCreated, other.dateCreated) &&
               Objects.equals(dateUpdated, other.dateUpdated) &&
               Objects.equals(friendlyName, other.friendlyName) &&
               Objects.equals(sid, other.sid) &&
               Objects.equals(uri, other.uri) &&
               Objects.equals(subresourceUris, other.subresourceUris);
    }

    @Override
    public int hashCode() {
        return Objects.hash(accountSid,
                            dateCreated,
                            dateUpdated,
                            friendlyName,
                            sid,
                            uri,
                            subresourceUris);
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                          .add("accountSid", accountSid)
                          .add("dateCreated", dateCreated)
                          .add("dateUpdated", dateUpdated)
                          .add("friendlyName", friendlyName)
                          .add("sid", sid)
                          .add("uri", uri)
                          .add("subresourceUris", subresourceUris)
                          .toString();
    }
}
