/**
 * This code was generated by
 * \ / _    _  _|   _  _
 *  | (_)\/(_)(_|\/| |(/_  v1.0.0
 *       /       /
 */

package com.twilio.rest.sync.v1.service.synclist;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.base.MoreObjects;
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
public class SyncListItem extends Resource {
    private static final long serialVersionUID = 156990163506527L;

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

    /**
     * Create a SyncListItemFetcher to execute fetch.
     *
     * @param pathServiceSid The SID of the Sync Service with the Sync List Item
     *                       resource to fetch
     * @param pathListSid The SID of the Sync List with the Sync List Item resource
     *                    to fetch
     * @param pathIndex The index of the Sync List Item resource to fetch
     * @return SyncListItemFetcher capable of executing the fetch
     */
    public static SyncListItemFetcher fetcher(final String pathServiceSid,
                                              final String pathListSid,
                                              final Integer pathIndex) {
        return new SyncListItemFetcher(pathServiceSid, pathListSid, pathIndex);
    }

    /**
     * Create a SyncListItemDeleter to execute delete.
     *
     * @param pathServiceSid The SID of the Sync Service with the Sync List Item
     *                       resource to delete
     * @param pathListSid The SID of the Sync List with the Sync List Item resource
     *                    to delete
     * @param pathIndex The index of the Sync List Item resource to delete
     * @return SyncListItemDeleter capable of executing the delete
     */
    public static SyncListItemDeleter deleter(final String pathServiceSid,
                                              final String pathListSid,
                                              final Integer pathIndex) {
        return new SyncListItemDeleter(pathServiceSid, pathListSid, pathIndex);
    }

    /**
     * Create a SyncListItemCreator to execute create.
     *
     * @param pathServiceSid The SID of the Sync Service to create the List Item in
     * @param pathListSid The SID of the Sync List to add the new List Item to
     * @param data A JSON string that represents an arbitrary, schema-less object
     *             that the List Item stores
     * @return SyncListItemCreator capable of executing the create
     */
    public static SyncListItemCreator creator(final String pathServiceSid,
                                              final String pathListSid,
                                              final Map<String, Object> data) {
        return new SyncListItemCreator(pathServiceSid, pathListSid, data);
    }

    /**
     * Create a SyncListItemReader to execute read.
     *
     * @param pathServiceSid The SID of the Sync Service with the List Item
     *                       resources to read
     * @param pathListSid The SID of the Sync List with the List Items to read
     * @return SyncListItemReader capable of executing the read
     */
    public static SyncListItemReader reader(final String pathServiceSid,
                                            final String pathListSid) {
        return new SyncListItemReader(pathServiceSid, pathListSid);
    }

    /**
     * Create a SyncListItemUpdater to execute update.
     *
     * @param pathServiceSid The SID of the Sync Service with the Sync List Item
     *                       resource to update
     * @param pathListSid The SID of the Sync List with the Sync List Item resource
     *                    to update
     * @param pathIndex The index of the Sync List Item resource to update
     * @return SyncListItemUpdater capable of executing the update
     */
    public static SyncListItemUpdater updater(final String pathServiceSid,
                                              final String pathListSid,
                                              final Integer pathIndex) {
        return new SyncListItemUpdater(pathServiceSid, pathListSid, pathIndex);
    }

    /**
     * Converts a JSON String into a SyncListItem object using the provided
     * ObjectMapper.
     *
     * @param json Raw JSON String
     * @param objectMapper Jackson ObjectMapper
     * @return SyncListItem object represented by the provided JSON
     */
    public static SyncListItem fromJson(final String json, final ObjectMapper objectMapper) {
        // Convert all checked exceptions to Runtime
        try {
            return objectMapper.readValue(json, SyncListItem.class);
        } catch (final JsonMappingException | JsonParseException e) {
            throw new ApiException(e.getMessage(), e);
        } catch (final IOException e) {
            throw new ApiConnectionException(e.getMessage(), e);
        }
    }

    /**
     * Converts a JSON InputStream into a SyncListItem object using the provided
     * ObjectMapper.
     *
     * @param json Raw JSON InputStream
     * @param objectMapper Jackson ObjectMapper
     * @return SyncListItem object represented by the provided JSON
     */
    public static SyncListItem fromJson(final InputStream json, final ObjectMapper objectMapper) {
        // Convert all checked exceptions to Runtime
        try {
            return objectMapper.readValue(json, SyncListItem.class);
        } catch (final JsonMappingException | JsonParseException e) {
            throw new ApiException(e.getMessage(), e);
        } catch (final IOException e) {
            throw new ApiConnectionException(e.getMessage(), e);
        }
    }

    private final Integer index;
    private final String accountSid;
    private final String serviceSid;
    private final String listSid;
    private final URI url;
    private final String revision;
    private final Map<String, Object> data;
    private final ZonedDateTime dateExpires;
    private final ZonedDateTime dateCreated;
    private final ZonedDateTime dateUpdated;
    private final String createdBy;

    @JsonCreator
    private SyncListItem(@JsonProperty("index")
                         final Integer index,
                         @JsonProperty("account_sid")
                         final String accountSid,
                         @JsonProperty("service_sid")
                         final String serviceSid,
                         @JsonProperty("list_sid")
                         final String listSid,
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
        this.index = index;
        this.accountSid = accountSid;
        this.serviceSid = serviceSid;
        this.listSid = listSid;
        this.url = url;
        this.revision = revision;
        this.data = data;
        this.dateExpires = DateConverter.iso8601DateTimeFromString(dateExpires);
        this.dateCreated = DateConverter.iso8601DateTimeFromString(dateCreated);
        this.dateUpdated = DateConverter.iso8601DateTimeFromString(dateUpdated);
        this.createdBy = createdBy;
    }

    /**
     * Returns The The automatically generated index of the List Item.
     *
     * @return The automatically generated index of the List Item
     */
    public final Integer getIndex() {
        return this.index;
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
     * Returns The The SID of the Sync Service that the resource is associated with.
     *
     * @return The SID of the Sync Service that the resource is associated with
     */
    public final String getServiceSid() {
        return this.serviceSid;
    }

    /**
     * Returns The The SID of the Sync List that contains the List Item.
     *
     * @return The SID of the Sync List that contains the List Item
     */
    public final String getListSid() {
        return this.listSid;
    }

    /**
     * Returns The The absolute URL of the List Item resource.
     *
     * @return The absolute URL of the List Item resource
     */
    public final URI getUrl() {
        return this.url;
    }

    /**
     * Returns The The current revision of the item, represented as a string.
     *
     * @return The current revision of the item, represented as a string
     */
    public final String getRevision() {
        return this.revision;
    }

    /**
     * Returns The An arbitrary, schema-less object that the List Item stores.
     *
     * @return An arbitrary, schema-less object that the List Item stores
     */
    public final Map<String, Object> getData() {
        return this.data;
    }

    /**
     * Returns The The ISO 8601 date and time in GMT when the List Item expires.
     *
     * @return The ISO 8601 date and time in GMT when the List Item expires
     */
    public final ZonedDateTime getDateExpires() {
        return this.dateExpires;
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
     * Returns The The identity of the List Item's creator.
     *
     * @return The identity of the List Item's creator
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

        SyncListItem other = (SyncListItem) o;

        return Objects.equals(index, other.index) &&
               Objects.equals(accountSid, other.accountSid) &&
               Objects.equals(serviceSid, other.serviceSid) &&
               Objects.equals(listSid, other.listSid) &&
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
        return Objects.hash(index,
                            accountSid,
                            serviceSid,
                            listSid,
                            url,
                            revision,
                            data,
                            dateExpires,
                            dateCreated,
                            dateUpdated,
                            createdBy);
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                          .add("index", index)
                          .add("accountSid", accountSid)
                          .add("serviceSid", serviceSid)
                          .add("listSid", listSid)
                          .add("url", url)
                          .add("revision", revision)
                          .add("data", data)
                          .add("dateExpires", dateExpires)
                          .add("dateCreated", dateCreated)
                          .add("dateUpdated", dateUpdated)
                          .add("createdBy", createdBy)
                          .toString();
    }
}
