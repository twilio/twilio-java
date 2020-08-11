/**
 * This code was generated by
 * \ / _    _  _|   _  _
 *  | (_)\/(_)(_|\/| |(/_  v1.0.0
 *       /       /
 */

package com.twilio.rest.api.v2010.account;

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
public class Queue extends Resource {
    private static final long serialVersionUID = 115549588553968L;

    /**
     * Create a QueueFetcher to execute fetch.
     *
     * @param pathAccountSid The SID of the Account that created the resource(s) to
     *                       fetch
     * @param pathSid The unique string that identifies this resource
     * @return QueueFetcher capable of executing the fetch
     */
    public static QueueFetcher fetcher(final String pathAccountSid,
                                       final String pathSid) {
        return new QueueFetcher(pathAccountSid, pathSid);
    }

    /**
     * Create a QueueFetcher to execute fetch.
     *
     * @param pathSid The unique string that identifies this resource
     * @return QueueFetcher capable of executing the fetch
     */
    public static QueueFetcher fetcher(final String pathSid) {
        return new QueueFetcher(pathSid);
    }

    /**
     * Create a QueueUpdater to execute update.
     *
     * @param pathAccountSid The SID of the Account that created the resource(s) to
     *                       update
     * @param pathSid The unique string that identifies this resource
     * @return QueueUpdater capable of executing the update
     */
    public static QueueUpdater updater(final String pathAccountSid,
                                       final String pathSid) {
        return new QueueUpdater(pathAccountSid, pathSid);
    }

    /**
     * Create a QueueUpdater to execute update.
     *
     * @param pathSid The unique string that identifies this resource
     * @return QueueUpdater capable of executing the update
     */
    public static QueueUpdater updater(final String pathSid) {
        return new QueueUpdater(pathSid);
    }

    /**
     * Create a QueueDeleter to execute delete.
     *
     * @param pathAccountSid The SID of the Account that created the resource(s) to
     *                       delete
     * @param pathSid The unique string that identifies this resource
     * @return QueueDeleter capable of executing the delete
     */
    public static QueueDeleter deleter(final String pathAccountSid,
                                       final String pathSid) {
        return new QueueDeleter(pathAccountSid, pathSid);
    }

    /**
     * Create a QueueDeleter to execute delete.
     *
     * @param pathSid The unique string that identifies this resource
     * @return QueueDeleter capable of executing the delete
     */
    public static QueueDeleter deleter(final String pathSid) {
        return new QueueDeleter(pathSid);
    }

    /**
     * Create a QueueReader to execute read.
     *
     * @param pathAccountSid The SID of the Account that created the resource(s) to
     *                       fetch
     * @return QueueReader capable of executing the read
     */
    public static QueueReader reader(final String pathAccountSid) {
        return new QueueReader(pathAccountSid);
    }

    /**
     * Create a QueueReader to execute read.
     *
     * @return QueueReader capable of executing the read
     */
    public static QueueReader reader() {
        return new QueueReader();
    }

    /**
     * Create a QueueCreator to execute create.
     *
     * @param pathAccountSid The SID of the Account that will create the resource
     * @param friendlyName A string to describe this resource
     * @return QueueCreator capable of executing the create
     */
    public static QueueCreator creator(final String pathAccountSid,
                                       final String friendlyName) {
        return new QueueCreator(pathAccountSid, friendlyName);
    }

    /**
     * Create a QueueCreator to execute create.
     *
     * @param friendlyName A string to describe this resource
     * @return QueueCreator capable of executing the create
     */
    public static QueueCreator creator(final String friendlyName) {
        return new QueueCreator(friendlyName);
    }

    /**
     * Converts a JSON String into a Queue object using the provided ObjectMapper.
     *
     * @param json Raw JSON String
     * @param objectMapper Jackson ObjectMapper
     * @return Queue object represented by the provided JSON
     */
    public static Queue fromJson(final String json, final ObjectMapper objectMapper) {
        // Convert all checked exceptions to Runtime
        try {
            return objectMapper.readValue(json, Queue.class);
        } catch (final JsonMappingException | JsonParseException e) {
            throw new ApiException(e.getMessage(), e);
        } catch (final IOException e) {
            throw new ApiConnectionException(e.getMessage(), e);
        }
    }

    /**
     * Converts a JSON InputStream into a Queue object using the provided
     * ObjectMapper.
     *
     * @param json Raw JSON InputStream
     * @param objectMapper Jackson ObjectMapper
     * @return Queue object represented by the provided JSON
     */
    public static Queue fromJson(final InputStream json, final ObjectMapper objectMapper) {
        // Convert all checked exceptions to Runtime
        try {
            return objectMapper.readValue(json, Queue.class);
        } catch (final JsonMappingException | JsonParseException e) {
            throw new ApiException(e.getMessage(), e);
        } catch (final IOException e) {
            throw new ApiConnectionException(e.getMessage(), e);
        }
    }

    private final ZonedDateTime dateUpdated;
    private final Integer currentSize;
    private final String friendlyName;
    private final String uri;
    private final String accountSid;
    private final Integer averageWaitTime;
    private final String sid;
    private final ZonedDateTime dateCreated;
    private final Integer maxSize;

    @JsonCreator
    private Queue(@JsonProperty("date_updated")
                  final String dateUpdated,
                  @JsonProperty("current_size")
                  final Integer currentSize,
                  @JsonProperty("friendly_name")
                  final String friendlyName,
                  @JsonProperty("uri")
                  final String uri,
                  @JsonProperty("account_sid")
                  final String accountSid,
                  @JsonProperty("average_wait_time")
                  final Integer averageWaitTime,
                  @JsonProperty("sid")
                  final String sid,
                  @JsonProperty("date_created")
                  final String dateCreated,
                  @JsonProperty("max_size")
                  final Integer maxSize) {
        this.dateUpdated = DateConverter.rfc2822DateTimeFromString(dateUpdated);
        this.currentSize = currentSize;
        this.friendlyName = friendlyName;
        this.uri = uri;
        this.accountSid = accountSid;
        this.averageWaitTime = averageWaitTime;
        this.sid = sid;
        this.dateCreated = DateConverter.rfc2822DateTimeFromString(dateCreated);
        this.maxSize = maxSize;
    }

    /**
     * Returns The RFC 2822 date and time in GMT that this resource was last
     * updated.
     *
     * @return The RFC 2822 date and time in GMT that this resource was last updated
     */
    public final ZonedDateTime getDateUpdated() {
        return this.dateUpdated;
    }

    /**
     * Returns The number of calls currently in the queue..
     *
     * @return The number of calls currently in the queue.
     */
    public final Integer getCurrentSize() {
        return this.currentSize;
    }

    /**
     * Returns A string that you assigned to describe this resource.
     *
     * @return A string that you assigned to describe this resource
     */
    public final String getFriendlyName() {
        return this.friendlyName;
    }

    /**
     * Returns The URI of this resource, relative to `https://api.twilio.com`.
     *
     * @return The URI of this resource, relative to `https://api.twilio.com`
     */
    public final String getUri() {
        return this.uri;
    }

    /**
     * Returns The SID of the Account that created this resource.
     *
     * @return The SID of the Account that created this resource
     */
    public final String getAccountSid() {
        return this.accountSid;
    }

    /**
     * Returns Average wait time of members in the queue.
     *
     * @return Average wait time of members in the queue
     */
    public final Integer getAverageWaitTime() {
        return this.averageWaitTime;
    }

    /**
     * Returns The unique string that identifies this resource.
     *
     * @return The unique string that identifies this resource
     */
    public final String getSid() {
        return this.sid;
    }

    /**
     * Returns The RFC 2822 date and time in GMT that this resource was created.
     *
     * @return The RFC 2822 date and time in GMT that this resource was created
     */
    public final ZonedDateTime getDateCreated() {
        return this.dateCreated;
    }

    /**
     * Returns The max number of calls allowed in the queue.
     *
     * @return The max number of calls allowed in the queue
     */
    public final Integer getMaxSize() {
        return this.maxSize;
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }

        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        Queue other = (Queue) o;

        return Objects.equals(dateUpdated, other.dateUpdated) &&
               Objects.equals(currentSize, other.currentSize) &&
               Objects.equals(friendlyName, other.friendlyName) &&
               Objects.equals(uri, other.uri) &&
               Objects.equals(accountSid, other.accountSid) &&
               Objects.equals(averageWaitTime, other.averageWaitTime) &&
               Objects.equals(sid, other.sid) &&
               Objects.equals(dateCreated, other.dateCreated) &&
               Objects.equals(maxSize, other.maxSize);
    }

    @Override
    public int hashCode() {
        return Objects.hash(dateUpdated,
                            currentSize,
                            friendlyName,
                            uri,
                            accountSid,
                            averageWaitTime,
                            sid,
                            dateCreated,
                            maxSize);
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                          .add("dateUpdated", dateUpdated)
                          .add("currentSize", currentSize)
                          .add("friendlyName", friendlyName)
                          .add("uri", uri)
                          .add("accountSid", accountSid)
                          .add("averageWaitTime", averageWaitTime)
                          .add("sid", sid)
                          .add("dateCreated", dateCreated)
                          .add("maxSize", maxSize)
                          .toString();
    }
}
