package com.twilio.sdk.resources;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.base.MoreObjects;
import com.twilio.sdk.Twilio;
import com.twilio.sdk.deleters.RecordingDeleter;
import com.twilio.sdk.exceptions.ApiConnectionException;
import com.twilio.sdk.exceptions.ApiException;
import com.twilio.sdk.fetchers.RecordingFetcher;
import com.twilio.sdk.readers.RecordingReader;
import org.joda.time.DateTime;

import java.io.IOException;
import java.io.InputStream;
import java.util.Objects;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Recording extends SidResource {

    private static final long serialVersionUID = -5732541214023360255L;

    private final DateTime dateUpdated;
    private final String uri;
    private final String accountSid;
    private final String callSid;
    private final String sid;
    private final Integer duration;
    private final DateTime dateCreated;
    private final String apiVersion;

    @JsonCreator
    private Recording(@JsonProperty("date_updated") final String dateUpdated, @JsonProperty("uri") final String uri,
                      @JsonProperty("account_sid") final String accountSid,
                      @JsonProperty("call_sid") final String callSid, @JsonProperty("sid") final String sid,
                      @JsonProperty("duration") final Integer duration,
                      @JsonProperty("date_created") final String dateCreated,
                      @JsonProperty("api_version") final String apiVersion) {
        this.dateUpdated = DateTime.parse(dateUpdated, Twilio.DATE_TIME_FORMATTER);
        this.uri = uri;
        this.accountSid = accountSid;
        this.callSid = callSid;
        this.sid = sid;
        this.duration = duration;
        this.dateCreated = DateTime.parse(dateCreated, Twilio.DATE_TIME_FORMATTER);
        this.apiVersion = apiVersion;

    }

    public static RecordingDeleter delete(final String sid) {
        return new RecordingDeleter(sid);
    }

    public static RecordingDeleter delete(final Recording target) {
        return new RecordingDeleter(target);
    }

    public static RecordingFetcher fetch(final String sid) {
        return new RecordingFetcher(sid);
    }

    public static RecordingReader list() {
        return new RecordingReader();
    }

    public static Recording fromJson(final String json, final ObjectMapper objectMapper) {
        try {
            return objectMapper.readValue(json, Recording.class);
        } catch (final JsonMappingException | JsonParseException e) {
            throw new ApiException(e.getMessage(), e);
        } catch (final IOException e) {
            throw new ApiConnectionException(e.getMessage(), e);
        }
    }

    public static Recording fromJson(final InputStream json, final ObjectMapper objectMapper) {
        try {
            return objectMapper.readValue(json, Recording.class);
        } catch (final JsonMappingException | JsonParseException e) {
            throw new ApiException(e.getMessage(), e);
        } catch (final IOException e) {
            throw new ApiConnectionException(e.getMessage(), e);
        }
    }

    public final DateTime getDateUpdated() {
        return dateUpdated;
    }

    public final String getUri() {
        return uri;
    }

    public final String getAccountSid() {
        return accountSid;
    }

    public final String getCallSid() {
        return callSid;
    }

    public final String getSid() {
        return sid;
    }

    public final Integer getDuration() {
        return duration;
    }

    public final DateTime getDateCreated() {
        return dateCreated;
    }

    public final String getApiVersion() {
        return apiVersion;
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        Recording self = (Recording) o;

        return (Objects.equals(dateUpdated, self.dateUpdated) &&
                Objects.equals(uri, self.uri) &&
                Objects.equals(accountSid, self.accountSid) &&
                Objects.equals(callSid, self.callSid) &&
                Objects.equals(sid, self.sid) &&
                Objects.equals(duration, self.duration) &&
                Objects.equals(dateCreated, self.dateCreated) &&
                Objects.equals(apiVersion, self.apiVersion));
    }

    @Override
    public int hashCode() {
        return Objects.hash(dateUpdated, uri, accountSid, callSid, sid, duration, dateCreated, apiVersion);
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                          .add("dateUpdated", dateUpdated)
                          .add("uri", uri)
                          .add("accountSid", accountSid)
                          .add("callSid", callSid)
                          .add("sid", sid)
                          .add("duration", duration)
                          .add("dateCreated", dateCreated)
                          .add("apiVersion", apiVersion)
                          .toString();
    }
}
