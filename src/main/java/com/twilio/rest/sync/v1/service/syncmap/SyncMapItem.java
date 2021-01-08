/**
 * This code was generated by
 * \ / _    _  _|   _  _
 *  | (_)\/(_)(_|\/| |(/_  v1.0.0
 *       /       /
 */

package com.twilio.rest.sync.v1.service.syncmap;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.twilio.base.Resource;
import com.twilio.converter.Converter;
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
public class SyncMapItem extends Resource {
    private static final long serialVersionUID = 280430240185290L;

    public enum QueryResultOrder {
        ASC("asc"),
        DESC("desc");

        private final String value;

        private QueryResultOrder(final String value) {
            this.value = value;
        }

        public String toString() {
            return value;
        }

        /**
         * Generate a QueryResultOrder from a string.
         * @param value string value
         * @return generated QueryResultOrder
         */
        @JsonCreator
        public static QueryResultOrder forValue(final String value) {
            return Promoter.enumFromString(value, QueryResultOrder.values());
        }
    }

    public enum QueryFromBoundType {
        INCLUSIVE("inclusive"),
        EXCLUSIVE("exclusive");

        private final String value;

        private QueryFromBoundType(final String value) {
            this.value = value;
        }

        public String toString() {
            return value;
        }

        /**
         * Generate a QueryFromBoundType from a string.
         * @param value string value
         * @return generated QueryFromBoundType
         */
        @JsonCreator
        public static QueryFromBoundType forValue(final String value) {
            return Promoter.enumFromString(value, QueryFromBoundType.values());
        }
    }

    public enum HideExpiredType {
        TRUE("true"),
        FALSE("false");

        private final String value;

        private HideExpiredType(final String value) {
            this.value = value;
        }

        public String toString() {
            return value;
        }

        /**
         * Generate a HideExpiredType from a string.
         * @param value string value
         * @return generated HideExpiredType
         */
        @JsonCreator
        public static HideExpiredType forValue(final String value) {
            return Promoter.enumFromString(value, HideExpiredType.values());
        }
    }

    /**
     * Create a SyncMapItemFetcher to execute fetch.
     *
     * @param pathServiceSid The SID of the Sync Service with the Sync Map Item
     *                       resource to fetch
     * @param pathMapSid The SID of the Sync Map with the Sync Map Item resource to
     *                   fetch
     * @param pathKey The key value of the Sync Map Item resource to fetch
     * @return SyncMapItemFetcher capable of executing the fetch
     */
    public static SyncMapItemFetcher fetcher(final String pathServiceSid,
                                             final String pathMapSid,
                                             final String pathKey) {
        return new SyncMapItemFetcher(pathServiceSid, pathMapSid, pathKey);
    }

    /**
     * Create a SyncMapItemDeleter to execute delete.
     *
     * @param pathServiceSid The SID of the Sync Service with the Sync Map Item
     *                       resource to delete
     * @param pathMapSid The SID of the Sync Map with the Sync Map Item resource to
     *                   delete
     * @param pathKey The key value of the Sync Map Item resource to delete
     * @return SyncMapItemDeleter capable of executing the delete
     */
    public static SyncMapItemDeleter deleter(final String pathServiceSid,
                                             final String pathMapSid,
                                             final String pathKey) {
        return new SyncMapItemDeleter(pathServiceSid, pathMapSid, pathKey);
    }

    /**
     * Create a SyncMapItemCreator to execute create.
     *
     * @param pathServiceSid The SID of the Sync Service to create the Map Item in
     * @param pathMapSid The SID of the Sync Map to add the new Map Item to
     * @param key The unique, user-defined key for the Map Item
     * @param data A JSON string that represents an arbitrary, schema-less object
     *             that the Map Item stores
     * @return SyncMapItemCreator capable of executing the create
     */
    public static SyncMapItemCreator creator(final String pathServiceSid,
                                             final String pathMapSid,
                                             final String key,
                                             final Map<String, Object> data) {
        return new SyncMapItemCreator(pathServiceSid, pathMapSid, key, data);
    }

    /**
     * Create a SyncMapItemReader to execute read.
     *
     * @param pathServiceSid The SID of the Sync Service with the Map Item
     *                       resources to read
     * @param pathMapSid The SID of the Sync Map with the Sync Map Item resource to
     *                   fetch
     * @return SyncMapItemReader capable of executing the read
     */
    public static SyncMapItemReader reader(final String pathServiceSid,
                                           final String pathMapSid) {
        return new SyncMapItemReader(pathServiceSid, pathMapSid);
    }

    /**
     * Create a SyncMapItemUpdater to execute update.
     *
     * @param pathServiceSid The SID of the Sync Service with the Sync Map Item
     *                       resource to update
     * @param pathMapSid The SID of the Sync Map with the Sync Map Item resource to
     *                   update
     * @param pathKey The key value of the Sync Map Item resource to update
     * @return SyncMapItemUpdater capable of executing the update
     */
    public static SyncMapItemUpdater updater(final String pathServiceSid,
                                             final String pathMapSid,
                                             final String pathKey) {
        return new SyncMapItemUpdater(pathServiceSid, pathMapSid, pathKey);
    }

    /**
     * Converts a JSON String into a SyncMapItem object using the provided
     * ObjectMapper.
     *
     * @param json Raw JSON String
     * @param objectMapper Jackson ObjectMapper
     * @return SyncMapItem object represented by the provided JSON
     */
    public static SyncMapItem fromJson(final String json, final ObjectMapper objectMapper) {
        // Convert all checked exceptions to Runtime
        try {
            return objectMapper.readValue(json, SyncMapItem.class);
        } catch (final JsonMappingException | JsonParseException e) {
            throw new ApiException(e.getMessage(), e);
        } catch (final IOException e) {
            throw new ApiConnectionException(e.getMessage(), e);
        }
    }

    /**
     * Converts a JSON InputStream into a SyncMapItem object using the provided
     * ObjectMapper.
     *
     * @param json Raw JSON InputStream
     * @param objectMapper Jackson ObjectMapper
     * @return SyncMapItem object represented by the provided JSON
     */
    public static SyncMapItem fromJson(final InputStream json, final ObjectMapper objectMapper) {
        // Convert all checked exceptions to Runtime
        try {
            return objectMapper.readValue(json, SyncMapItem.class);
        } catch (final JsonMappingException | JsonParseException e) {
            throw new ApiException(e.getMessage(), e);
        } catch (final IOException e) {
            throw new ApiConnectionException(e.getMessage(), e);
        }
    }

    private final String key;
    private final String accountSid;
    private final String serviceSid;
    private final String mapSid;
    private final URI url;
    private final String revision;
    private final Map<String, Object> data;
    private final ZonedDateTime dateExpires;
    private final ZonedDateTime dateCreated;
    private final ZonedDateTime dateUpdated;
    private final String createdBy;

    @JsonCreator
    private SyncMapItem(@JsonProperty("key")
                        final String key,
                        @JsonProperty("account_sid")
                        final String accountSid,
                        @JsonProperty("service_sid")
                        final String serviceSid,
                        @JsonProperty("map_sid")
                        final String mapSid,
                        @JsonProperty("url")
                        final URI url,
                        @JsonProperty("revision")
                        final String revision,
                        @JsonProperty("data")
                        final Map<String, Object> data,
                        @JsonProperty("date_expires")
                        final String dateExpires,
                        @JsonProperty("date_created")
                        final String dateCreated,
                        @JsonProperty("date_updated")
                        final String dateUpdated,
                        @JsonProperty("created_by")
                        final String createdBy) {
        this.key = key;
        this.accountSid = accountSid;
        this.serviceSid = serviceSid;
        this.mapSid = mapSid;
        this.url = url;
        this.revision = revision;
        this.data = data;
        this.dateExpires = DateConverter.iso8601DateTimeFromString(dateExpires);
        this.dateCreated = DateConverter.iso8601DateTimeFromString(dateCreated);
        this.dateUpdated = DateConverter.iso8601DateTimeFromString(dateUpdated);
        this.createdBy = createdBy;
    }

    /**
     * Returns The unique, user-defined key for the Map Item.
     *
     * @return The unique, user-defined key for the Map Item
     */
    public final String getKey() {
        return this.key;
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
     * Returns The SID of the Sync Service that the resource is associated with.
     *
     * @return The SID of the Sync Service that the resource is associated with
     */
    public final String getServiceSid() {
        return this.serviceSid;
    }

    /**
     * Returns The SID of the Sync Map that contains the Map Item.
     *
     * @return The SID of the Sync Map that contains the Map Item
     */
    public final String getMapSid() {
        return this.mapSid;
    }

    /**
     * Returns The absolute URL of the Map Item resource.
     *
     * @return The absolute URL of the Map Item resource
     */
    public final URI getUrl() {
        return this.url;
    }

    /**
     * Returns The current revision of the Map Item, represented as a string.
     *
     * @return The current revision of the Map Item, represented as a string
     */
    public final String getRevision() {
        return this.revision;
    }

    /**
     * Returns An arbitrary, schema-less object that the Map Item stores.
     *
     * @return An arbitrary, schema-less object that the Map Item stores
     */
    public final Map<String, Object> getData() {
        return this.data;
    }

    /**
     * Returns The ISO 8601 date and time in GMT when the Map Item expires.
     *
     * @return The ISO 8601 date and time in GMT when the Map Item expires
     */
    public final ZonedDateTime getDateExpires() {
        return this.dateExpires;
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
     * Returns The identity of the Map Item's creator.
     *
     * @return The identity of the Map Item's creator
     */
    public final String getCreatedBy() {
        return this.createdBy;
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }

        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        SyncMapItem other = (SyncMapItem) o;

        return Objects.equals(key, other.key) &&
               Objects.equals(accountSid, other.accountSid) &&
               Objects.equals(serviceSid, other.serviceSid) &&
               Objects.equals(mapSid, other.mapSid) &&
               Objects.equals(url, other.url) &&
               Objects.equals(revision, other.revision) &&
               Objects.equals(data, other.data) &&
               Objects.equals(dateExpires, other.dateExpires) &&
               Objects.equals(dateCreated, other.dateCreated) &&
               Objects.equals(dateUpdated, other.dateUpdated) &&
               Objects.equals(createdBy, other.createdBy);
    }

    @Override
    public int hashCode() {
        return Objects.hash(key,
                            accountSid,
                            serviceSid,
                            mapSid,
                            url,
                            revision,
                            data,
                            dateExpires,
                            dateCreated,
                            dateUpdated,
                            createdBy);
    }
}