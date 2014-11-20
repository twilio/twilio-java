package com.twilio.sdk.resources;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.base.MoreObjects;
import com.twilio.sdk.Twilio;
import com.twilio.sdk.creators.SipCredentialListCreator;
import com.twilio.sdk.deleters.SipCredentialListDeleter;
import com.twilio.sdk.exceptions.ApiConnectionException;
import com.twilio.sdk.exceptions.ApiException;
import com.twilio.sdk.fetchers.SipCredentialListFetcher;

import com.twilio.sdk.readers.SipCredentialListReader;
import com.twilio.sdk.updaters.SipCredentialListUpdater;

import org.joda.time.DateTime;

import java.io.IOException;
import java.io.InputStream;

import java.util.Map;
import java.util.Objects;

public class SipCredentialList extends SidResource {

    private static final long serialVersionUID = -5732541214023360255L;

    private final DateTime dateUpdated;
    private final String friendlyName;
    private final String uri;
    private final String accountSid;
    private final String sid;
    private final Map<String, String> subresourceUris;
    private final DateTime dateCreated;

    @JsonCreator
    private SipCredentialList(@JsonProperty("date_updated") final String dateUpdated,
                              @JsonProperty("friendly_name") final String friendlyName,
                              @JsonProperty("uri") final String uri,
                              @JsonProperty("account_sid") final String accountSid,
                              @JsonProperty("sid") final String sid,
                              @JsonProperty("subresource_uris") final Map<String, String> subresourceUris,
                              @JsonProperty("date_created") final String dateCreated) {
        this.dateUpdated = DateTime.parse(dateUpdated, Twilio.DATE_TIME_FORMATTER);
        this.friendlyName = friendlyName;
        this.uri = uri;
        this.accountSid = accountSid;
        this.sid = sid;
        this.subresourceUris = subresourceUris;
        this.dateCreated = DateTime.parse(dateCreated, Twilio.DATE_TIME_FORMATTER);
    }

    public static SipCredentialListCreator create(final String friendlyName) {
        return new SipCredentialListCreator(friendlyName);
    }

    public static SipCredentialListDeleter delete(final String sid) {
        return new SipCredentialListDeleter(sid);
    }

    public static SipCredentialListDeleter delete(final SipCredentialList target) {
        return new SipCredentialListDeleter(target);
    }

    public static SipCredentialListFetcher fetch(final String sid) {
        return new SipCredentialListFetcher(sid);
    }

    public static SipCredentialListReader list() {
        return new SipCredentialListReader();
    }

    public static SipCredentialListUpdater update(final SipCredentialList target) {
        return new SipCredentialListUpdater(target);
    }

    public static SipCredentialListUpdater update(final String sid) {
        return new SipCredentialListUpdater(sid);
    }

    public static SipCredentialList fromJson(final String json, final ObjectMapper objectMapper) {
        try {
            return objectMapper.readValue(json, SipCredentialList.class);
        } catch (final JsonMappingException | JsonParseException e) {
            throw new ApiException(e.getMessage(), e);
        } catch (final IOException e) {
            throw new ApiConnectionException(e.getMessage(), e);
        }
    }

    public static SipCredentialList fromJson(final InputStream json, final ObjectMapper objectMapper) {
        try {
            return objectMapper.readValue(json, SipCredentialList.class);
        } catch (final JsonMappingException | JsonParseException e) {
            throw new ApiException(e.getMessage(), e);
        } catch (final IOException e) {
            throw new ApiConnectionException(e.getMessage(), e);
        }
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

    public final Map<String, String> getSubresourceUris() {
        return subresourceUris;
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        SipCredentialList self = (SipCredentialList) o;

        return (
            Objects.equals(dateUpdated, self.dateUpdated) &&
            Objects.equals(friendlyName, self.friendlyName) &&
            Objects.equals(uri, self.uri) &&
            Objects.equals(accountSid, self.accountSid) &&
            Objects.equals(sid, self.sid) &&
            Objects.equals(dateCreated, self.dateCreated) 
            );
    }

    @Override
    public int hashCode() {
        return Objects
                .hash(
                dateUpdated,
                friendlyName,
                uri,
                accountSid,
                sid,
                dateCreated
                );
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                        .add("dateUpdated", dateUpdated)
                        .add("friendlyName", friendlyName)
                        .add("uri", uri)
                        .add("accountSid", accountSid)
                        .add("sid", sid)
                        .add("dateCreated", dateCreated)
                        .toString();
    }
}