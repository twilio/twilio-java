package com.twilio.sdk.resources.api.v2010.account;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.base.MoreObjects;
import com.twilio.sdk.clients.TwilioRestClient;
import com.twilio.sdk.converters.MarshalConverter;
import com.twilio.sdk.creators.api.v2010.account.QueueCreator;
import com.twilio.sdk.deleters.api.v2010.account.QueueDeleter;
import com.twilio.sdk.exceptions.ApiConnectionException;
import com.twilio.sdk.exceptions.ApiException;
import com.twilio.sdk.fetchers.api.v2010.account.QueueFetcher;
import com.twilio.sdk.http.HttpMethod;
import com.twilio.sdk.http.Request;
import com.twilio.sdk.http.Response;
import com.twilio.sdk.readers.api.v2010.account.QueueReader;
import com.twilio.sdk.resources.RestException;
import com.twilio.sdk.resources.SidResource;
import com.twilio.sdk.updaters.api.v2010.account.QueueUpdater;
import org.joda.time.DateTime;

import java.io.IOException;
import java.io.InputStream;
import java.util.Map;
import java.util.Objects;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Queue extends SidResource {
    private static final long serialVersionUID = 51300543687284L;

    /**
     * Fetch an instance of a queue identified by the QueueSid
     * 
     * @param accountSid The account_sid
     * @param sid Fetch by unique queue Sid
     * @return QueueFetcher capable of executing the fetch
     */
    public static QueueFetcher fetch(final String accountSid, final String sid) {
        return new QueueFetcher(accountSid, sid);
    }

    /**
     * Update the queue with the new parameters
     * 
     * @param accountSid The account_sid
     * @param sid The sid
     * @return QueueUpdater capable of executing the update
     */
    public static QueueUpdater update(final String accountSid, final String sid) {
        return new QueueUpdater(accountSid, sid);
    }

    /**
     * Remove an empty queue
     * 
     * @param accountSid The account_sid
     * @param sid Delete by unique queue Sid
     * @return QueueDeleter capable of executing the delete
     */
    public static QueueDeleter delete(final String accountSid, final String sid) {
        return new QueueDeleter(accountSid, sid);
    }

    /**
     * Retrieve a list of queues belonging to the account used to make the request
     * 
     * @param accountSid The account_sid
     * @return QueueReader capable of executing the read
     */
    public static QueueReader read(final String accountSid) {
        return new QueueReader(accountSid);
    }

    /**
     * Create a queue
     * 
     * @param accountSid The account_sid
     * @return QueueCreator capable of executing the create
     */
    public static QueueCreator create(final String accountSid) {
        return new QueueCreator(accountSid);
    }

    /**
     * Converts a JSON String into a Queue object using the provided ObjectMapper
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
     * ObjectMapper
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

    private final String accountSid;
    private final Integer averageWaitTime;
    private final Integer currentSize;
    private final DateTime dateCreated;
    private final DateTime dateUpdated;
    private final String friendlyName;
    private final Integer maxSize;
    private final String sid;
    private final String uri;

    @JsonCreator
    private Queue(@JsonProperty("account_sid")
                  final String accountSid, 
                  @JsonProperty("average_wait_time")
                  final Integer averageWaitTime, 
                  @JsonProperty("current_size")
                  final Integer currentSize, 
                  @JsonProperty("date_created")
                  final String dateCreated, 
                  @JsonProperty("date_updated")
                  final String dateUpdated, 
                  @JsonProperty("friendly_name")
                  final String friendlyName, 
                  @JsonProperty("max_size")
                  final Integer maxSize, 
                  @JsonProperty("sid")
                  final String sid, 
                  @JsonProperty("uri")
                  final String uri) {
        this.accountSid = accountSid;
        this.averageWaitTime = averageWaitTime;
        this.currentSize = currentSize;
        this.dateCreated = MarshalConverter.dateTimeFromString(dateCreated);
        this.dateUpdated = MarshalConverter.dateTimeFromString(dateUpdated);
        this.friendlyName = friendlyName;
        this.maxSize = maxSize;
        this.sid = sid;
        this.uri = uri;
    }

    /**
     * @return The account_sid
     */
    public final String getAccountSid() {
        return this.accountSid;
    }

    /**
     * @return Average wait time of members in the queue
     */
    public final Integer getAverageWaitTime() {
        return this.averageWaitTime;
    }

    /**
     * @return The count of calls currently in the queue.
     */
    public final Integer getCurrentSize() {
        return this.currentSize;
    }

    /**
     * @return The date_created
     */
    public final DateTime getDateCreated() {
        return this.dateCreated;
    }

    /**
     * @return The date_updated
     */
    public final DateTime getDateUpdated() {
        return this.dateUpdated;
    }

    /**
     * @return A user-provided string that identifies this queue.
     */
    public final String getFriendlyName() {
        return this.friendlyName;
    }

    /**
     * @return The max number of calls allowed in the queue
     */
    public final Integer getMaxSize() {
        return this.maxSize;
    }

    /**
     * @return A string that uniquely identifies this queue
     */
    public final String getSid() {
        return this.sid;
    }

    /**
     * @return The uri
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
        
        Queue other = (Queue) o;
        
        return Objects.equals(accountSid, other.accountSid) && 
               Objects.equals(averageWaitTime, other.averageWaitTime) && 
               Objects.equals(currentSize, other.currentSize) && 
               Objects.equals(dateCreated, other.dateCreated) && 
               Objects.equals(dateUpdated, other.dateUpdated) && 
               Objects.equals(friendlyName, other.friendlyName) && 
               Objects.equals(maxSize, other.maxSize) && 
               Objects.equals(sid, other.sid) && 
               Objects.equals(uri, other.uri);
    }

    @Override
    public int hashCode() {
        return Objects.hash(accountSid,
                            averageWaitTime,
                            currentSize,
                            dateCreated,
                            dateUpdated,
                            friendlyName,
                            maxSize,
                            sid,
                            uri);
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                          .add("accountSid", accountSid)
                          .add("averageWaitTime", averageWaitTime)
                          .add("currentSize", currentSize)
                          .add("dateCreated", dateCreated)
                          .add("dateUpdated", dateUpdated)
                          .add("friendlyName", friendlyName)
                          .add("maxSize", maxSize)
                          .add("sid", sid)
                          .add("uri", uri)
                          .toString();
    }
}