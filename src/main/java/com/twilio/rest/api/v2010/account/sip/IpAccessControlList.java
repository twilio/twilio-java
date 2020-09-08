/**
 * This code was generated by
 * \ / _    _  _|   _  _
 *  | (_)\/(_)(_|\/| |(/_  v1.0.0
 *       /       /
 */

package com.twilio.rest.api.v2010.account.sip;

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
import java.util.Map;
import java.util.Objects;

@JsonIgnoreProperties(ignoreUnknown = true)
@ToString
public class IpAccessControlList extends Resource {
    private static final long serialVersionUID = 214188792953524L;

    /**
     * Create a IpAccessControlListReader to execute read.
     *
     * @param pathAccountSid The unique sid that identifies this account
     * @return IpAccessControlListReader capable of executing the read
     */
    public static IpAccessControlListReader reader(final String pathAccountSid) {
        return new IpAccessControlListReader(pathAccountSid);
    }

    /**
     * Create a IpAccessControlListReader to execute read.
     *
     * @return IpAccessControlListReader capable of executing the read
     */
    public static IpAccessControlListReader reader() {
        return new IpAccessControlListReader();
    }

    /**
     * Create a IpAccessControlListCreator to execute create.
     *
     * @param pathAccountSid The unique sid that identifies this account
     * @param friendlyName A human readable description of this resource
     * @return IpAccessControlListCreator capable of executing the create
     */
    public static IpAccessControlListCreator creator(final String pathAccountSid,
                                                     final String friendlyName) {
        return new IpAccessControlListCreator(pathAccountSid, friendlyName);
    }

    /**
     * Create a IpAccessControlListCreator to execute create.
     *
     * @param friendlyName A human readable description of this resource
     * @return IpAccessControlListCreator capable of executing the create
     */
    public static IpAccessControlListCreator creator(final String friendlyName) {
        return new IpAccessControlListCreator(friendlyName);
    }

    /**
     * Create a IpAccessControlListFetcher to execute fetch.
     *
     * @param pathAccountSid The unique sid that identifies this account
     * @param pathSid A string that identifies the resource to fetch
     * @return IpAccessControlListFetcher capable of executing the fetch
     */
    public static IpAccessControlListFetcher fetcher(final String pathAccountSid,
                                                     final String pathSid) {
        return new IpAccessControlListFetcher(pathAccountSid, pathSid);
    }

    /**
     * Create a IpAccessControlListFetcher to execute fetch.
     *
     * @param pathSid A string that identifies the resource to fetch
     * @return IpAccessControlListFetcher capable of executing the fetch
     */
    public static IpAccessControlListFetcher fetcher(final String pathSid) {
        return new IpAccessControlListFetcher(pathSid);
    }

    /**
     * Create a IpAccessControlListUpdater to execute update.
     *
     * @param pathAccountSid The unique sid that identifies this account
     * @param pathSid A string that identifies the resource to update
     * @param friendlyName A human readable description of this resource
     * @return IpAccessControlListUpdater capable of executing the update
     */
    public static IpAccessControlListUpdater updater(final String pathAccountSid,
                                                     final String pathSid,
                                                     final String friendlyName) {
        return new IpAccessControlListUpdater(pathAccountSid, pathSid, friendlyName);
    }

    /**
     * Create a IpAccessControlListUpdater to execute update.
     *
     * @param pathSid A string that identifies the resource to update
     * @param friendlyName A human readable description of this resource
     * @return IpAccessControlListUpdater capable of executing the update
     */
    public static IpAccessControlListUpdater updater(final String pathSid,
                                                     final String friendlyName) {
        return new IpAccessControlListUpdater(pathSid, friendlyName);
    }

    /**
     * Create a IpAccessControlListDeleter to execute delete.
     *
     * @param pathAccountSid The unique sid that identifies this account
     * @param pathSid A string that identifies the resource to delete
     * @return IpAccessControlListDeleter capable of executing the delete
     */
    public static IpAccessControlListDeleter deleter(final String pathAccountSid,
                                                     final String pathSid) {
        return new IpAccessControlListDeleter(pathAccountSid, pathSid);
    }

    /**
     * Create a IpAccessControlListDeleter to execute delete.
     *
     * @param pathSid A string that identifies the resource to delete
     * @return IpAccessControlListDeleter capable of executing the delete
     */
    public static IpAccessControlListDeleter deleter(final String pathSid) {
        return new IpAccessControlListDeleter(pathSid);
    }

    /**
     * Converts a JSON String into a IpAccessControlList object using the provided
     * ObjectMapper.
     *
     * @param json Raw JSON String
     * @param objectMapper Jackson ObjectMapper
     * @return IpAccessControlList object represented by the provided JSON
     */
    public static IpAccessControlList fromJson(final String json, final ObjectMapper objectMapper) {
        // Convert all checked exceptions to Runtime
        try {
            return objectMapper.readValue(json, IpAccessControlList.class);
        } catch (final JsonMappingException | JsonParseException e) {
            throw new ApiException(e.getMessage(), e);
        } catch (final IOException e) {
            throw new ApiConnectionException(e.getMessage(), e);
        }
    }

    /**
     * Converts a JSON InputStream into a IpAccessControlList object using the
     * provided ObjectMapper.
     *
     * @param json Raw JSON InputStream
     * @param objectMapper Jackson ObjectMapper
     * @return IpAccessControlList object represented by the provided JSON
     */
    public static IpAccessControlList fromJson(final InputStream json, final ObjectMapper objectMapper) {
        // Convert all checked exceptions to Runtime
        try {
            return objectMapper.readValue(json, IpAccessControlList.class);
        } catch (final JsonMappingException | JsonParseException e) {
            throw new ApiException(e.getMessage(), e);
        } catch (final IOException e) {
            throw new ApiConnectionException(e.getMessage(), e);
        }
    }

    private final String sid;
    private final String accountSid;
    private final String friendlyName;
    private final DateTime dateCreated;
    private final DateTime dateUpdated;
    private final Map<String, String> subresourceUris;
    private final String uri;

    @JsonCreator
    private IpAccessControlList(@JsonProperty("sid")
                                final String sid,
                                @JsonProperty("account_sid")
                                final String accountSid,
                                @JsonProperty("friendly_name")
                                final String friendlyName,
                                @JsonProperty("date_created")
                                final String dateCreated,
                                @JsonProperty("date_updated")
                                final String dateUpdated,
                                @JsonProperty("subresource_uris")
                                final Map<String, String> subresourceUris,
                                @JsonProperty("uri")
                                final String uri) {
        this.sid = sid;
        this.accountSid = accountSid;
        this.friendlyName = friendlyName;
        this.dateCreated = DateConverter.rfc2822DateTimeFromString(dateCreated);
        this.dateUpdated = DateConverter.rfc2822DateTimeFromString(dateUpdated);
        this.subresourceUris = subresourceUris;
        this.uri = uri;
    }

    /**
     * Returns A string that uniquely identifies this resource.
     *
     * @return A string that uniquely identifies this resource
     */
    public final String getSid() {
        return this.sid;
    }

    /**
     * Returns The unique sid that identifies this account.
     *
     * @return The unique sid that identifies this account
     */
    public final String getAccountSid() {
        return this.accountSid;
    }

    /**
     * Returns A human readable description of this resource.
     *
     * @return A human readable description of this resource
     */
    public final String getFriendlyName() {
        return this.friendlyName;
    }

    /**
     * Returns The date this resource was created.
     *
     * @return The date this resource was created
     */
    public final DateTime getDateCreated() {
        return this.dateCreated;
    }

    /**
     * Returns The date this resource was last updated.
     *
     * @return The date this resource was last updated
     */
    public final DateTime getDateUpdated() {
        return this.dateUpdated;
    }

    /**
     * Returns The IP addresses associated with this resource..
     *
     * @return The IP addresses associated with this resource.
     */
    public final Map<String, String> getSubresourceUris() {
        return this.subresourceUris;
    }

    /**
     * Returns The URI for this resource.
     *
     * @return The URI for this resource
     */
    public final String getUri() {
        return this.uri;
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }

        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        IpAccessControlList other = (IpAccessControlList) o;

        return Objects.equals(sid, other.sid) &&
               Objects.equals(accountSid, other.accountSid) &&
               Objects.equals(friendlyName, other.friendlyName) &&
               Objects.equals(dateCreated, other.dateCreated) &&
               Objects.equals(dateUpdated, other.dateUpdated) &&
               Objects.equals(subresourceUris, other.subresourceUris) &&
               Objects.equals(uri, other.uri);
    }

    @Override
    public int hashCode() {
        return Objects.hash(sid,
                            accountSid,
                            friendlyName,
                            dateCreated,
                            dateUpdated,
                            subresourceUris,
                            uri);
    }
}