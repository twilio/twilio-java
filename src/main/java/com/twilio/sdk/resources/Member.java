package com.twilio.sdk.resources;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.twilio.sdk.Twilio;
import com.twilio.sdk.exceptions.ApiConnectionException;
import com.twilio.sdk.exceptions.ApiException;
import com.twilio.sdk.fetchers.MemberFetcher;
import com.twilio.sdk.readers.MemberReader;
import com.twilio.sdk.updaters.MemberUpdater;
import org.joda.time.DateTime;

import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.util.Objects;

public class Member extends Resource {

    private static final long serialVersionUID = -5732541214023360255L;

    private final String callSid;
    private final DateTime dateEnqueued;
    private final Integer waitTime;
    private final Integer position;
    private final String uri;

    @JsonCreator
    private Member(@JsonProperty("call_sid") final String callSid,
                   @JsonProperty("date_enqueued") final String dateEnqueued,
                   @JsonProperty("wait_time") final Integer waitTime,
                   @JsonProperty("position") final Integer position,
                   @JsonProperty("uri") final String uri) {
        this.callSid = callSid;
        this.dateEnqueued = DateTime.parse(dateEnqueued, Twilio.DATE_TIME_FORMATTER);
        this.waitTime = waitTime;
        this.position = position;
        this.uri = uri;
    }

    public static MemberReader list(final String queueSid) {
        return new MemberReader(queueSid);
    }

    public static MemberReader list(final Queue target) {
        return new MemberReader(target);
    }

    public static MemberUpdater update(final String queueSid, final String sid, final URI url) {
        return new MemberUpdater(queueSid, sid, url);
    }

    public static MemberUpdater update(final Queue targetQueue, final Member target, final URI url) {
        return new MemberUpdater(targetQueue, target, url);
    }

    public static MemberFetcher fetch(final String queueSid, final String sid) {
        return new MemberFetcher(queueSid, sid);
    }

    public static MemberFetcher fetch(final Queue targetQueue, final String sid) {
        return new MemberFetcher(targetQueue, sid);
    }

    public static MemberFetcher fetchFront(final String queueSid) {
        return new MemberFetcher(queueSid, "Front");
    }

    public static MemberFetcher fetchFront(final Queue target) {
        return new MemberFetcher(target, "Front");
    }

    public String getCallSid() {
        return callSid;
    }

    public DateTime getDateEnqueued() {
        return dateEnqueued;
    }

    public Integer getWaitTime() {
        return waitTime;
    }

    public Integer getPosition() {
        return position;
    }

    public String getUri() {
        return uri;
    }

    public static Member fromJson(final String json, final ObjectMapper objectMapper) {
        try {
            return objectMapper.readValue(json, Member.class);
        } catch (final JsonMappingException | JsonParseException e) {
            throw new ApiException(e.getMessage(), e);
        } catch (final IOException e) {
            throw new ApiConnectionException(e.getMessage(), e);
        }
    }

    public static Member fromJson(final InputStream json, final ObjectMapper objectMapper) {
        try {
            return objectMapper.readValue(json, Member.class);
        } catch (final JsonMappingException | JsonParseException e) {
            throw new ApiException(e.getMessage(), e);
        } catch (final IOException e) {
            throw new ApiConnectionException(e.getMessage(), e);
        }
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }

        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        Member other = (Member) o;

        return (Objects.equals(callSid, other.callSid) &&
                Objects.equals(dateEnqueued, other.dateEnqueued) &&
                Objects.equals(waitTime, other.waitTime) &&
                Objects.equals(position, other.position));
    }

    @Override
    public int hashCode() {
        return Objects.hash(callSid, dateEnqueued, waitTime, position, uri);
    }
}
