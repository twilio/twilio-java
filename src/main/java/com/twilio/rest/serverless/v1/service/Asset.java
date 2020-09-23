/**
 * This code was generated by
 * \ / _    _  _|   _  _
 *  | (_)\/(_)(_|\/| |(/_  v1.0.0
 *       /       /
 */

package com.twilio.rest.serverless.v1.service;

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
import java.net.URI;
import java.time.ZonedDateTime;
import java.util.Map;
import java.util.Objects;

/**
 * PLEASE NOTE that this class contains preview products that are subject to
 * change. Use them with caution. If you currently do not have developer preview
 * access, please contact help@twilio.com.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
@ToString
public class Asset extends Resource {
    private static final long serialVersionUID = 37942173237399L;

    /**
     * Create a AssetReader to execute read.
     *
     * @param pathServiceSid The SID of the Service to read the Asset resource from
     * @return AssetReader capable of executing the read
     */
    public static AssetReader reader(final String pathServiceSid) {
        return new AssetReader(pathServiceSid);
    }

    /**
     * Create a AssetFetcher to execute fetch.
     *
     * @param pathServiceSid The SID of the Service to fetch the Asset resource from
     * @param pathSid The SID that identifies the Asset resource to fetch
     * @return AssetFetcher capable of executing the fetch
     */
    public static AssetFetcher fetcher(final String pathServiceSid,
                                       final String pathSid) {
        return new AssetFetcher(pathServiceSid, pathSid);
    }

    /**
     * Create a AssetDeleter to execute delete.
     *
     * @param pathServiceSid The SID of the Service to delete the Asset resource
     *                       from
     * @param pathSid The SID that identifies the Asset resource to delete
     * @return AssetDeleter capable of executing the delete
     */
    public static AssetDeleter deleter(final String pathServiceSid,
                                       final String pathSid) {
        return new AssetDeleter(pathServiceSid, pathSid);
    }

    /**
     * Create a AssetCreator to execute create.
     *
     * @param pathServiceSid The SID of the Service to create the Asset resource
     *                       under
     * @param friendlyName A string to describe the Asset resource
     * @return AssetCreator capable of executing the create
     */
    public static AssetCreator creator(final String pathServiceSid,
                                       final String friendlyName) {
        return new AssetCreator(pathServiceSid, friendlyName);
    }

    /**
     * Create a AssetUpdater to execute update.
     *
     * @param pathServiceSid The SID of the Service to update the Asset resource
     *                       from
     * @param pathSid The SID that identifies the Asset resource to update
     * @param friendlyName A string to describe the Asset resource
     * @return AssetUpdater capable of executing the update
     */
    public static AssetUpdater updater(final String pathServiceSid,
                                       final String pathSid,
                                       final String friendlyName) {
        return new AssetUpdater(pathServiceSid, pathSid, friendlyName);
    }

    /**
     * Converts a JSON String into a Asset object using the provided ObjectMapper.
     *
     * @param json Raw JSON String
     * @param objectMapper Jackson ObjectMapper
     * @return Asset object represented by the provided JSON
     */
    public static Asset fromJson(final String json, final ObjectMapper objectMapper) {
        // Convert all checked exceptions to Runtime
        try {
            return objectMapper.readValue(json, Asset.class);
        } catch (final JsonMappingException | JsonParseException e) {
            throw new ApiException(e.getMessage(), e);
        } catch (final IOException e) {
            throw new ApiConnectionException(e.getMessage(), e);
        }
    }

    /**
     * Converts a JSON InputStream into a Asset object using the provided
     * ObjectMapper.
     *
     * @param json Raw JSON InputStream
     * @param objectMapper Jackson ObjectMapper
     * @return Asset object represented by the provided JSON
     */
    public static Asset fromJson(final InputStream json, final ObjectMapper objectMapper) {
        // Convert all checked exceptions to Runtime
        try {
            return objectMapper.readValue(json, Asset.class);
        } catch (final JsonMappingException | JsonParseException e) {
            throw new ApiException(e.getMessage(), e);
        } catch (final IOException e) {
            throw new ApiConnectionException(e.getMessage(), e);
        }
    }

    private final String sid;
    private final String accountSid;
    private final String serviceSid;
    private final String friendlyName;
    private final ZonedDateTime dateCreated;
    private final ZonedDateTime dateUpdated;
    private final URI url;
    private final Map<String, String> links;

    @JsonCreator
    private Asset(@JsonProperty("sid")
                  final String sid,
                  @JsonProperty("account_sid")
                  final String accountSid,
                  @JsonProperty("service_sid")
                  final String serviceSid,
                  @JsonProperty("friendly_name")
                  final String friendlyName,
                  @JsonProperty("date_created")
                  final String dateCreated,
                  @JsonProperty("date_updated")
                  final String dateUpdated,
                  @JsonProperty("url")
                  final URI url,
                  @JsonProperty("links")
                  final Map<String, String> links) {
        this.sid = sid;
        this.accountSid = accountSid;
        this.serviceSid = serviceSid;
        this.friendlyName = friendlyName;
        this.dateCreated = DateConverter.iso8601DateTimeFromString(dateCreated);
        this.dateUpdated = DateConverter.iso8601DateTimeFromString(dateUpdated);
        this.url = url;
        this.links = links;
    }

    /**
     * Returns The unique string that identifies the Asset resource.
     *
     * @return The unique string that identifies the Asset resource
     */
    public final String getSid() {
        return this.sid;
    }

    /**
     * Returns The SID of the Account that created the Asset resource.
     *
     * @return The SID of the Account that created the Asset resource
     */
    public final String getAccountSid() {
        return this.accountSid;
    }

    /**
     * Returns The SID of the Service that the Asset resource is associated with.
     *
     * @return The SID of the Service that the Asset resource is associated with
     */
    public final String getServiceSid() {
        return this.serviceSid;
    }

    /**
     * Returns The string that you assigned to describe the Asset resource.
     *
     * @return The string that you assigned to describe the Asset resource
     */
    public final String getFriendlyName() {
        return this.friendlyName;
    }

    /**
     * Returns The ISO 8601 date and time in GMT when the Asset resource was
     * created.
     *
     * @return The ISO 8601 date and time in GMT when the Asset resource was created
     */
    public final ZonedDateTime getDateCreated() {
        return this.dateCreated;
    }

    /**
     * Returns The ISO 8601 date and time in GMT when the Asset resource was last
     * updated.
     *
     * @return The ISO 8601 date and time in GMT when the Asset resource was last
     *         updated
     */
    public final ZonedDateTime getDateUpdated() {
        return this.dateUpdated;
    }

    /**
     * Returns The absolute URL of the Asset resource.
     *
     * @return The absolute URL of the Asset resource
     */
    public final URI getUrl() {
        return this.url;
    }

    /**
     * Returns The URLs of the Asset resource's nested resources.
     *
     * @return The URLs of the Asset resource's nested resources
     */
    public final Map<String, String> getLinks() {
        return this.links;
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }

        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        Asset other = (Asset) o;

        return Objects.equals(sid, other.sid) &&
               Objects.equals(accountSid, other.accountSid) &&
               Objects.equals(serviceSid, other.serviceSid) &&
               Objects.equals(friendlyName, other.friendlyName) &&
               Objects.equals(dateCreated, other.dateCreated) &&
               Objects.equals(dateUpdated, other.dateUpdated) &&
               Objects.equals(url, other.url) &&
               Objects.equals(links, other.links);
    }

    @Override
    public int hashCode() {
        return Objects.hash(sid,
                            accountSid,
                            serviceSid,
                            friendlyName,
                            dateCreated,
                            dateUpdated,
                            url,
                            links);
    }
}
