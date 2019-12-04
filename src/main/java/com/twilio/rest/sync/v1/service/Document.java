/**
 * This code was generated by
 * \ / _    _  _|   _  _
 *  | (_)\/(_)(_|\/| |(/_  v1.0.0
 *       /       /
 */

package com.twilio.rest.sync.v1.service;

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
public class Document extends Resource {
    private static final long serialVersionUID = 114918671939352L;

    /**
     * Create a DocumentFetcher to execute fetch.
     *
     * @param pathServiceSid The SID of the Sync Service with the Document resource
     *                       to fetch
     * @param pathSid The SID of the Document resource to fetch
     * @return DocumentFetcher capable of executing the fetch
     */
    public static DocumentFetcher fetcher(final String pathServiceSid,
                                          final String pathSid) {
        return new DocumentFetcher(pathServiceSid, pathSid);
    }

    /**
     * Create a DocumentDeleter to execute delete.
     *
     * @param pathServiceSid The SID of the Sync Service with the Document resource
     *                       to delete
     * @param pathSid The SID of the Document resource to delete
     * @return DocumentDeleter capable of executing the delete
     */
    public static DocumentDeleter deleter(final String pathServiceSid,
                                          final String pathSid) {
        return new DocumentDeleter(pathServiceSid, pathSid);
    }

    /**
     * Create a DocumentCreator to execute create.
     *
     * @param pathServiceSid The SID of the Sync Service to associate the Document
     *                       resource to create with
     * @return DocumentCreator capable of executing the create
     */
    public static DocumentCreator creator(final String pathServiceSid) {
        return new DocumentCreator(pathServiceSid);
    }

    /**
     * Create a DocumentReader to execute read.
     *
     * @param pathServiceSid The SID of the Sync Service with the Document
     *                       resources to read
     * @return DocumentReader capable of executing the read
     */
    public static DocumentReader reader(final String pathServiceSid) {
        return new DocumentReader(pathServiceSid);
    }

    /**
     * Create a DocumentUpdater to execute update.
     *
     * @param pathServiceSid The SID of the Sync Service with the Document resource
     *                       to update
     * @param pathSid The SID of the Document resource to update
     * @return DocumentUpdater capable of executing the update
     */
    public static DocumentUpdater updater(final String pathServiceSid,
                                          final String pathSid) {
        return new DocumentUpdater(pathServiceSid, pathSid);
    }

    /**
     * Converts a JSON String into a Document object using the provided
     * ObjectMapper.
     *
     * @param json Raw JSON String
     * @param objectMapper Jackson ObjectMapper
     * @return Document object represented by the provided JSON
     */
    public static Document fromJson(final String json, final ObjectMapper objectMapper) {
        // Convert all checked exceptions to Runtime
        try {
            return objectMapper.readValue(json, Document.class);
        } catch (final JsonMappingException | JsonParseException e) {
            throw new ApiException(e.getMessage(), e);
        } catch (final IOException e) {
            throw new ApiConnectionException(e.getMessage(), e);
        }
    }

    /**
     * Converts a JSON InputStream into a Document object using the provided
     * ObjectMapper.
     *
     * @param json Raw JSON InputStream
     * @param objectMapper Jackson ObjectMapper
     * @return Document object represented by the provided JSON
     */
    public static Document fromJson(final InputStream json, final ObjectMapper objectMapper) {
        // Convert all checked exceptions to Runtime
        try {
            return objectMapper.readValue(json, Document.class);
        } catch (final JsonMappingException | JsonParseException e) {
            throw new ApiException(e.getMessage(), e);
        } catch (final IOException e) {
            throw new ApiConnectionException(e.getMessage(), e);
        }
    }

    private final String sid;
    private final String uniqueName;
    private final String accountSid;
    private final String serviceSid;
    private final URI url;
    private final Map<String, String> links;
    private final String revision;
    private final Map<String, Object> data;
    private final ZonedDateTime dateExpires;
    private final ZonedDateTime dateCreated;
    private final ZonedDateTime dateUpdated;
    private final String createdBy;

    @JsonCreator
    private Document(@JsonProperty("sid")
                     final String sid,
                     @JsonProperty("unique_name")
                     final String uniqueName,
                     @JsonProperty("account_sid")
                     final String accountSid,
                     @JsonProperty("service_sid")
                     final String serviceSid,
                     @JsonProperty("url")
                     final URI url,
                     @JsonProperty("links")
                     final Map<String, String> links,
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
        this.sid = sid;
        this.uniqueName = uniqueName;
        this.accountSid = accountSid;
        this.serviceSid = serviceSid;
        this.url = url;
        this.links = links;
        this.revision = revision;
        this.data = data;
        this.dateExpires = DateConverter.iso8601DateTimeFromString(dateExpires);
        this.dateCreated = DateConverter.iso8601DateTimeFromString(dateCreated);
        this.dateUpdated = DateConverter.iso8601DateTimeFromString(dateUpdated);
        this.createdBy = createdBy;
    }

    /**
     * Returns The The unique string that identifies the resource.
     *
     * @return The unique string that identifies the resource
     */
    public final String getSid() {
        return this.sid;
    }

    /**
     * Returns The An application-defined string that uniquely identifies the
     * resource.
     *
     * @return An application-defined string that uniquely identifies the resource
     */
    public final String getUniqueName() {
        return this.uniqueName;
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
     * Returns The The absolute URL of the Document resource.
     *
     * @return The absolute URL of the Document resource
     */
    public final URI getUrl() {
        return this.url;
    }

    /**
     * Returns The The URLs of resources related to the Sync Document.
     *
     * @return The URLs of resources related to the Sync Document
     */
    public final Map<String, String> getLinks() {
        return this.links;
    }

    /**
     * Returns The The current revision of the Sync Document, represented by a
     * string identifier.
     *
     * @return The current revision of the Sync Document, represented by a string
     *         identifier
     */
    public final String getRevision() {
        return this.revision;
    }

    /**
     * Returns The An arbitrary, schema-less object that the Sync Document stores.
     *
     * @return An arbitrary, schema-less object that the Sync Document stores
     */
    public final Map<String, Object> getData() {
        return this.data;
    }

    /**
     * Returns The The ISO 8601 date and time in GMT when the Sync Document expires.
     *
     * @return The ISO 8601 date and time in GMT when the Sync Document expires
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
     * Returns The The identity of the Sync Document's creator.
     *
     * @return The identity of the Sync Document's creator
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

        Document other = (Document) o;

        return Objects.equals(sid, other.sid) &&
               Objects.equals(uniqueName, other.uniqueName) &&
               Objects.equals(accountSid, other.accountSid) &&
               Objects.equals(serviceSid, other.serviceSid) &&
               Objects.equals(url, other.url) &&
               Objects.equals(links, other.links) &&
               Objects.equals(revision, other.revision) &&
               Objects.equals(data, other.data) &&
               Objects.equals(dateExpires, other.dateExpires) &&
               Objects.equals(dateCreated, other.dateCreated) &&
               Objects.equals(dateUpdated, other.dateUpdated) &&
               Objects.equals(createdBy, other.createdBy);
    }

    @Override
    public int hashCode() {
        return Objects.hash(sid,
                            uniqueName,
                            accountSid,
                            serviceSid,
                            url,
                            links,
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
                          .add("sid", sid)
                          .add("uniqueName", uniqueName)
                          .add("accountSid", accountSid)
                          .add("serviceSid", serviceSid)
                          .add("url", url)
                          .add("links", links)
                          .add("revision", revision)
                          .add("data", data)
                          .add("dateExpires", dateExpires)
                          .add("dateCreated", dateCreated)
                          .add("dateUpdated", dateUpdated)
                          .add("createdBy", createdBy)
                          .toString();
    }
}
