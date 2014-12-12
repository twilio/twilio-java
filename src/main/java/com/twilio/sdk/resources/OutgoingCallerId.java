package com.twilio.sdk.resources;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.base.MoreObjects;
import com.twilio.sdk.creators.OutgoingCallerIdCreator;
import com.twilio.sdk.deleters.OutgoingCallerIdDeleter;
import com.twilio.sdk.exceptions.ApiConnectionException;
import com.twilio.sdk.exceptions.ApiException;
import com.twilio.sdk.fetchers.OutgoingCallerIdFetcher;
import com.twilio.sdk.readers.OutgoingCallerIdReader;
import com.twilio.sdk.updaters.OutgoingCallerIdUpdater;
import org.joda.time.DateTime;

import java.io.IOException;
import java.io.InputStream;
import java.util.Objects;

@JsonIgnoreProperties(ignoreUnknown = true)
public class OutgoingCallerId extends SidResource {

    private static final long serialVersionUID = -5732541214023360255L;

    private final String phoneNumber;
    private final DateTime dateUpdated;
    private final String friendlyName;
    private final String uri;
    private final String accountSid;
    private final String sid;
    private final DateTime dateCreated;

    @JsonCreator
    private OutgoingCallerId(@JsonProperty("phone_number") final String phoneNumber,
                             @JsonProperty("date_updated") final String dateUpdated,
                             @JsonProperty("friendly_name") final String friendlyName,
                             @JsonProperty("uri") final String uri,
                             @JsonProperty("account_sid") final String accountSid,
                             @JsonProperty("sid") final String sid,
                             @JsonProperty("date_created") final String dateCreated) {
        this.phoneNumber = phoneNumber;
        this.dateUpdated = safeDateTimeConvert(dateUpdated);
        this.friendlyName = friendlyName;
        this.uri = uri;
        this.accountSid = accountSid;
        this.sid = sid;
        this.dateCreated = safeDateTimeConvert(dateCreated);

    }

    public static OutgoingCallerIdCreator create(final String phoneNumber) {
        return new OutgoingCallerIdCreator(phoneNumber);
    }

    public static OutgoingCallerIdDeleter delete(final String sid) {
        return new OutgoingCallerIdDeleter(sid);
    }

    public static OutgoingCallerIdDeleter delete(final OutgoingCallerId target) {
        return new OutgoingCallerIdDeleter(target);
    }

    public static OutgoingCallerIdFetcher fetch(final String sid) {
        return new OutgoingCallerIdFetcher(sid);
    }

    public static OutgoingCallerIdReader list() {
        return new OutgoingCallerIdReader();
    }

    public static OutgoingCallerIdUpdater update(final OutgoingCallerId target) {
        return new OutgoingCallerIdUpdater(target);
    }

    public static OutgoingCallerIdUpdater update(final String sid) {
        return new OutgoingCallerIdUpdater(sid);
    }

    public static OutgoingCallerId fromJson(final String json, final ObjectMapper objectMapper) {
        try {
            return objectMapper.readValue(json, OutgoingCallerId.class);
        } catch (final JsonMappingException | JsonParseException e) {
            throw new ApiException(e.getMessage(), e);
        } catch (final IOException e) {
            throw new ApiConnectionException(e.getMessage(), e);
        }
    }

    public static OutgoingCallerId fromJson(final InputStream json, final ObjectMapper objectMapper) {
        try {
            return objectMapper.readValue(json, OutgoingCallerId.class);
        } catch (final JsonMappingException | JsonParseException e) {
            throw new ApiException(e.getMessage(), e);
        } catch (final IOException e) {
            throw new ApiConnectionException(e.getMessage(), e);
        }
    }

    public final String getPhoneNumber() {
        return phoneNumber;
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

        OutgoingCallerId self = (OutgoingCallerId) o;

        return (Objects.equals(phoneNumber, self.phoneNumber) &&
                Objects.equals(dateUpdated, self.dateUpdated) &&
                Objects.equals(friendlyName, self.friendlyName) &&
                Objects.equals(uri, self.uri) &&
                Objects.equals(accountSid, self.accountSid) &&
                Objects.equals(sid, self.sid) &&
                Objects.equals(dateCreated, self.dateCreated));
    }

    @Override
    public int hashCode() {
        return Objects.hash(phoneNumber, dateUpdated, friendlyName, uri, accountSid, sid, dateCreated);
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                          .add("phoneNumber", phoneNumber)
                          .add("dateUpdated", dateUpdated)
                          .add("friendlyName", friendlyName)
                          .add("uri", uri)
                          .add("accountSid", accountSid)
                          .add("sid", sid)
                          .add("dateCreated", dateCreated)
                          .toString();
    }
}
