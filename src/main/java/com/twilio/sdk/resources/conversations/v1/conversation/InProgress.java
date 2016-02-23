package com.twilio.sdk.resources.conversations.v1.conversation;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.base.MoreObjects;
import com.twilio.sdk.clients.TwilioRestClient;
import com.twilio.sdk.converters.MarshalConverter;
import com.twilio.sdk.exceptions.ApiConnectionException;
import com.twilio.sdk.exceptions.ApiException;
import com.twilio.sdk.http.HttpMethod;
import com.twilio.sdk.http.Request;
import com.twilio.sdk.http.Response;
import com.twilio.sdk.readers.conversations.v1.conversation.InProgressReader;
import com.twilio.sdk.resources.Resource;
import com.twilio.sdk.resources.RestException;
import org.joda.time.DateTime;

import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.util.Map;
import java.util.Objects;

@JsonIgnoreProperties(ignoreUnknown = true)
public class InProgress extends Resource {
    private static final long serialVersionUID = 33453296983711L;

    public enum Status {
        CREATED("created"),
        IN_PROGRESS("in-progress"),
        COMPLETED("completed"),
        FAILED("failed");
    
        private final String value;
        
        private Status(final String value) {
            this.value = value;
        }
        
        public String toString() {
            return value;
        }
        
        @JsonCreator
        public static Status forValue(final String value) {
            String normalized = value.replace("-", "_").toUpperCase();
            return Status.valueOf(normalized);
        }
    }

    /**
     * Create a InProgressReader to execute read.
     * 
     * @return InProgressReader capable of executing the read
     */
    public static InProgressReader read() {
        return new InProgressReader();
    }

    /**
     * Converts a JSON String into a InProgress object using the provided
     * ObjectMapper.
     * 
     * @param json Raw JSON String
     * @param objectMapper Jackson ObjectMapper
     * @return InProgress object represented by the provided JSON
     */
    public static InProgress fromJson(final String json, final ObjectMapper objectMapper) {
        // Convert all checked exceptions to Runtime
        try {
            return objectMapper.readValue(json, InProgress.class);
        } catch (final JsonMappingException | JsonParseException e) {
            throw new ApiException(e.getMessage(), e);
        } catch (final IOException e) {
            throw new ApiConnectionException(e.getMessage(), e);
        }
    }

    /**
     * Converts a JSON InputStream into a InProgress object using the provided
     * ObjectMapper.
     * 
     * @param json Raw JSON InputStream
     * @param objectMapper Jackson ObjectMapper
     * @return InProgress object represented by the provided JSON
     */
    public static InProgress fromJson(final InputStream json, final ObjectMapper objectMapper) {
        // Convert all checked exceptions to Runtime
        try {
            return objectMapper.readValue(json, InProgress.class);
        } catch (final JsonMappingException | JsonParseException e) {
            throw new ApiException(e.getMessage(), e);
        } catch (final IOException e) {
            throw new ApiConnectionException(e.getMessage(), e);
        }
    }

    private final String sid;
    private final InProgress.Status status;
    private final Integer duration;
    private final DateTime dateCreated;
    private final DateTime startTime;
    private final DateTime endTime;
    private final String accountSid;
    private final URI url;

    @JsonCreator
    private InProgress(@JsonProperty("sid")
                       final String sid, 
                       @JsonProperty("status")
                       final InProgress.Status status, 
                       @JsonProperty("duration")
                       final Integer duration, 
                       @JsonProperty("date_created")
                       final String dateCreated, 
                       @JsonProperty("start_time")
                       final String startTime, 
                       @JsonProperty("end_time")
                       final String endTime, 
                       @JsonProperty("account_sid")
                       final String accountSid, 
                       @JsonProperty("url")
                       final URI url) {
        this.sid = sid;
        this.status = status;
        this.duration = duration;
        this.dateCreated = MarshalConverter.dateTimeFromString(dateCreated);
        this.startTime = MarshalConverter.dateTimeFromString(startTime);
        this.endTime = MarshalConverter.dateTimeFromString(endTime);
        this.accountSid = accountSid;
        this.url = url;
    }

    /**
     * Returns The The sid.
     * 
     * @return The sid
     */
    public final String getSid() {
        return this.sid;
    }

    /**
     * Returns The The status.
     * 
     * @return The status
     */
    public final InProgress.Status getStatus() {
        return this.status;
    }

    /**
     * Returns The The duration.
     * 
     * @return The duration
     */
    public final Integer getDuration() {
        return this.duration;
    }

    /**
     * Returns The The date_created.
     * 
     * @return The date_created
     */
    public final DateTime getDateCreated() {
        return this.dateCreated;
    }

    /**
     * Returns The The start_time.
     * 
     * @return The start_time
     */
    public final DateTime getStartTime() {
        return this.startTime;
    }

    /**
     * Returns The The end_time.
     * 
     * @return The end_time
     */
    public final DateTime getEndTime() {
        return this.endTime;
    }

    /**
     * Returns The The account_sid.
     * 
     * @return The account_sid
     */
    public final String getAccountSid() {
        return this.accountSid;
    }

    /**
     * Returns The The url.
     * 
     * @return The url
     */
    public final URI getUrl() {
        return this.url;
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        
        InProgress other = (InProgress) o;
        
        return Objects.equals(sid, other.sid) && 
               Objects.equals(status, other.status) && 
               Objects.equals(duration, other.duration) && 
               Objects.equals(dateCreated, other.dateCreated) && 
               Objects.equals(startTime, other.startTime) && 
               Objects.equals(endTime, other.endTime) && 
               Objects.equals(accountSid, other.accountSid) && 
               Objects.equals(url, other.url);
    }

    @Override
    public int hashCode() {
        return Objects.hash(sid,
                            status,
                            duration,
                            dateCreated,
                            startTime,
                            endTime,
                            accountSid,
                            url);
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                          .add("sid", sid)
                          .add("status", status)
                          .add("duration", duration)
                          .add("dateCreated", dateCreated)
                          .add("startTime", startTime)
                          .add("endTime", endTime)
                          .add("accountSid", accountSid)
                          .add("url", url)
                          .toString();
    }
}