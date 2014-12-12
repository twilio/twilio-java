package com.twilio.sdk.resources;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.base.MoreObjects;
import com.twilio.sdk.creators.QueueCreator;
import com.twilio.sdk.deleters.QueueDeleter;
import com.twilio.sdk.exceptions.ApiConnectionException;
import com.twilio.sdk.exceptions.ApiException;
import com.twilio.sdk.fetchers.MemberFetcher;
import com.twilio.sdk.fetchers.QueueFetcher;
import com.twilio.sdk.readers.MemberReader;
import com.twilio.sdk.readers.QueueReader;
import com.twilio.sdk.updaters.MemberUpdater;
import com.twilio.sdk.updaters.QueueUpdater;
import org.joda.time.DateTime;

import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.util.Objects;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Queue extends SidResource {

    private static final long serialVersionUID = -5732541214023360255L;

    private final DateTime dateUpdated;
    private final Integer currentSize;
    private final String friendlyName;
    private final String uri;
    private final Integer averageWaitTime;
    private final String sid;
    private final DateTime dateCreated;
    private final Integer maxSize;

    @JsonCreator
    private Queue(@JsonProperty("date_updated") final String dateUpdated,
                  @JsonProperty("current_size") final Integer currentSize,
                  @JsonProperty("friendly_name") final String friendlyName, @JsonProperty("uri") final String uri,
                  @JsonProperty("average_wait_time") final Integer averageWaitTime,
                  @JsonProperty("sid") final String sid, @JsonProperty("date_created") final String dateCreated,
                  @JsonProperty("max_size") final Integer maxSize) {
        this.dateUpdated = safeDateTimeConvert(dateUpdated);
        this.currentSize = currentSize;
        this.friendlyName = friendlyName;
        this.uri = uri;
        this.averageWaitTime = averageWaitTime;
        this.sid = sid;
        this.dateCreated = safeDateTimeConvert(dateCreated);
        this.maxSize = maxSize;

    }

    public static QueueCreator create(final String friendlyName) {
        return new QueueCreator(friendlyName);
    }

    public static QueueDeleter delete(final String sid) {
        return new QueueDeleter(sid);
    }

    public static QueueDeleter delete(final Queue target) {
        return new QueueDeleter(target);
    }

    public static QueueFetcher fetch(final String sid) {
        return new QueueFetcher(sid);
    }

    public static QueueReader list() {
        return new QueueReader();
    }

    public static QueueUpdater update(final Queue target) {
        return new QueueUpdater(target);
    }

    public static QueueUpdater update(final String sid) {
        return new QueueUpdater(sid);
    }

    public MemberFetcher fetchMember(final String sid) {
        return new MemberFetcher(this, sid);
    }

    public MemberFetcher fetchFront() {
        return new MemberFetcher(this, "Front");
    }

    public MemberReader listMembers() {
        return new MemberReader(this);
    }

    public MemberUpdater dequeue(final String sid, final URI url) {
        return new MemberUpdater(this, sid, url);
    }

    public MemberUpdater dequeue(final URI url) {
        return new MemberUpdater(this, "Front", url);
    }

    public static Queue fromJson(final String json, final ObjectMapper objectMapper) {
        try {
            return objectMapper.readValue(json, Queue.class);
        } catch (final JsonMappingException | JsonParseException e) {
            throw new ApiException(e.getMessage(), e);
        } catch (final IOException e) {
            throw new ApiConnectionException(e.getMessage(), e);
        }
    }

    public static Queue fromJson(final InputStream json, final ObjectMapper objectMapper) {
        try {
            return objectMapper.readValue(json, Queue.class);
        } catch (final JsonMappingException | JsonParseException e) {
            throw new ApiException(e.getMessage(), e);
        } catch (final IOException e) {
            throw new ApiConnectionException(e.getMessage(), e);
        }
    }

    public final DateTime getDateUpdated() {
        return dateUpdated;
    }

    public final Integer getCurrentSize() {
        return currentSize;
    }

    public final String getFriendlyName() {
        return friendlyName;
    }

    public final String getUri() {
        return uri;
    }

    public final Integer getAverageWaitTime() {
        return averageWaitTime;
    }

    public final String getSid() {
        return sid;
    }

    public final DateTime getDateCreated() {
        return dateCreated;
    }

    public final Integer getMaxSize() {
        return maxSize;
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        Queue self = (Queue) o;

        return (Objects.equals(dateUpdated, self.dateUpdated) &&
                Objects.equals(currentSize, self.currentSize) &&
                Objects.equals(friendlyName, self.friendlyName) &&
                Objects.equals(uri, self.uri) &&
                Objects.equals(averageWaitTime, self.averageWaitTime) &&
                Objects.equals(sid, self.sid) &&
                Objects.equals(dateCreated, self.dateCreated) &&
                Objects.equals(maxSize, self.maxSize));
    }

    @Override
    public int hashCode() {
        return Objects.hash(dateUpdated, currentSize, friendlyName, uri, averageWaitTime, sid, dateCreated, maxSize);
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                          .add("dateUpdated", dateUpdated)
                          .add("currentSize", currentSize)
                          .add("friendlyName", friendlyName)
                          .add("uri", uri)
                          .add("averageWaitTime", averageWaitTime)
                          .add("sid", sid)
                          .add("dateCreated", dateCreated)
                          .add("maxSize", maxSize)
                          .toString();
    }
}
