package com.twilio.sdk.resources;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.base.MoreObjects;
import com.twilio.sdk.deleters.ParticipantDeleter;
import com.twilio.sdk.exceptions.ApiConnectionException;
import com.twilio.sdk.exceptions.ApiException;
import com.twilio.sdk.fetchers.ConferenceFetcher;
import com.twilio.sdk.fetchers.ParticipantFetcher;
import com.twilio.sdk.readers.ConferenceReader;
import com.twilio.sdk.readers.ParticipantReader;
import com.twilio.sdk.updaters.ParticipantUpdater;
import org.joda.time.DateTime;

import java.io.IOException;
import java.io.InputStream;
import java.util.Objects;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Conference extends SidResource {

    private static final long serialVersionUID = -5732541214023360255L;

    private final Conference.Status status;
    private final DateTime dateUpdated;
    private final String friendlyName;
    private final String uri;
    private final String accountSid;
    private final String sid;
    private final DateTime dateCreated;

    @JsonCreator
    private Conference(@JsonProperty("status") final Conference.Status status,
                       @JsonProperty("date_updated") final String dateUpdated,
                       @JsonProperty("friendly_name") final String friendlyName, @JsonProperty("uri") final String uri,
                       @JsonProperty("account_sid") final String accountSid, @JsonProperty("sid") final String sid,
                       @JsonProperty("date_created") final String dateCreated) {
        this.status = status;
        this.dateUpdated = safeDateTimeConvert(dateUpdated);
        this.friendlyName = friendlyName;
        this.uri = uri;
        this.accountSid = accountSid;
        this.sid = sid;
        this.dateCreated = safeDateTimeConvert(dateCreated);

    }

    public static ConferenceFetcher fetch(final String sid) {
        return new ConferenceFetcher(sid);
    }

    public static ConferenceReader list() {
        return new ConferenceReader();
    }

    public ParticipantFetcher fetchParticipant(final String sid) {
        return new ParticipantFetcher(this, sid);
    }

    public ParticipantReader listParticipants() {
        return new ParticipantReader(this);
    }

    public ParticipantUpdater updateParticipant(final String sid, final Boolean muted) {
        return new ParticipantUpdater(this, sid, muted);
    }

    public ParticipantUpdater muteParticipant(final String sid) {
        return new ParticipantUpdater(this, sid, true);
    }

    public ParticipantUpdater unmuteParticipant(final String sid) {
        return new ParticipantUpdater(this, sid, false);
    }

    public ParticipantDeleter deleteParticipant(final String sid) {
        return new ParticipantDeleter(this, sid);
    }

    public ParticipantDeleter kickParticipant(final String sid) {
        return new ParticipantDeleter(this, sid);
    }

    public static Conference fromJson(final String json, final ObjectMapper objectMapper) {
        try {
            return objectMapper.readValue(json, Conference.class);
        } catch (final JsonMappingException | JsonParseException e) {
            throw new ApiException(e.getMessage(), e);
        } catch (final IOException e) {
            throw new ApiConnectionException(e.getMessage(), e);
        }
    }

    public static Conference fromJson(final InputStream json, final ObjectMapper objectMapper) {
        try {
            return objectMapper.readValue(json, Conference.class);
        } catch (final JsonMappingException | JsonParseException e) {
            throw new ApiException(e.getMessage(), e);
        } catch (final IOException e) {
            throw new ApiConnectionException(e.getMessage(), e);
        }
    }

    public final Conference.Status getStatus() {
        return status;
    }

    public final DateTime getDateUpdated() {
        return dateUpdated;
    }

    public final String getFriendlyName() {
        return friendlyName;
    }

    public final String getUri() {
        return uri;
    }

    public final String getAccountSid() {
        return accountSid;
    }

    public final String getSid() {
        return sid;
    }

    public final DateTime getDateCreated() {
        return dateCreated;
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        Conference self = (Conference) o;

        return (Objects.equals(status, self.status) &&
                Objects.equals(dateUpdated, self.dateUpdated) &&
                Objects.equals(friendlyName, self.friendlyName) &&
                Objects.equals(uri, self.uri) &&
                Objects.equals(accountSid, self.accountSid) &&
                Objects.equals(sid, self.sid) &&
                Objects.equals(dateCreated, self.dateCreated));
    }

    @Override
    public int hashCode() {
        return Objects.hash(status, dateUpdated, friendlyName, uri, accountSid, sid, dateCreated);
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                          .add("status", status)
                          .add("dateUpdated", dateUpdated)
                          .add("friendlyName", friendlyName)
                          .add("uri", uri)
                          .add("accountSid", accountSid)
                          .add("sid", sid)
                          .add("dateCreated", dateCreated)
                          .toString();
    }

    public enum Status {
        INIT("init"), IN_PROGRESS("in-progress"), COMPLETED("completed");
        private final String status;

        private Status(final String status) {
            this.status = status;
        }

        public String toString() {
            return status;
        }

        @JsonCreator
        public static Status forValue(final String value) {
            String munged = value.replace("-", "_")
                                 .toUpperCase();
            return Status.valueOf(munged);
        }
    }
}
