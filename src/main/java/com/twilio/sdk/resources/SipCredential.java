package com.twilio.sdk.resources;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.base.MoreObjects;
import com.twilio.sdk.converters.MarshalConverter;
import com.twilio.sdk.creators.SipCredentialCreator;
import com.twilio.sdk.deleters.SipCredentialDeleter;
import com.twilio.sdk.exceptions.ApiConnectionException;
import com.twilio.sdk.exceptions.ApiException;
import com.twilio.sdk.fetchers.SipCredentialFetcher;
import com.twilio.sdk.readers.SipCredentialReader;
import com.twilio.sdk.updaters.SipCredentialUpdater;
import org.joda.time.DateTime;

import java.io.IOException;
import java.io.InputStream;
import java.util.Objects;

public class SipCredential extends SidResource {

    private static final long serialVersionUID = -5732541214023360255L;

    private final String sid;
    private final String accountSid;
    private final String credentialListSid;
    private final DateTime dateCreated;
    private final DateTime dateUpdated;
    private final String username;
    private final String uri;

    @JsonCreator
    public SipCredential(@JsonProperty("sid") final String sid, @JsonProperty("account_sid") final String accountSid,
                         @JsonProperty("credential_list_sid") final String credentialListSid,
                         @JsonProperty("date_created") final String dateCreated,
                         @JsonProperty("date_updated") final String dateUpdated,
                         @JsonProperty("username") final String username, @JsonProperty("uri") final String uri) {

        this.sid = sid;
        this.accountSid = accountSid;
        this.credentialListSid = credentialListSid;
        this.dateCreated = MarshalConverter.dateTimeFromString(dateCreated);
        this.dateUpdated = MarshalConverter.dateTimeFromString(dateUpdated);
        this.username = username;
        this.uri = uri;
    }

    public static SipCredentialCreator create(final String credentialListSid, final String username,
                                              final String password) {
        return new SipCredentialCreator(credentialListSid, username, password);
    }

    public static SipCredentialCreator create(final SipCredentialList target, final String username,
                                              final String password) {
        return new SipCredentialCreator(target, username, password);
    }

    public static SipCredentialReader list(final String credentialListSid) {
        return new SipCredentialReader(credentialListSid);
    }

    public static SipCredentialReader list(final SipCredentialList target) {
        return new SipCredentialReader(target);
    }

    public static SipCredentialFetcher fetch(final String credentialListSid, final String sid) {
        return new SipCredentialFetcher(credentialListSid, sid);
    }

    public static SipCredentialFetcher fetch(final SipCredentialList target, final String sid) {
        return new SipCredentialFetcher(target, sid);
    }

    public static SipCredentialUpdater update(final String credentialListSid, final String sid, final String password) {
        return new SipCredentialUpdater(credentialListSid, sid, password);
    }

    public static SipCredentialUpdater update(final SipCredentialList targetCredentialList, final String sid,
                                              final String password) {
        return new SipCredentialUpdater(targetCredentialList, sid, password);
    }

    public static SipCredentialUpdater update(final SipCredential target, final String password) {
        return new SipCredentialUpdater(target, password);
    }

    public static SipCredentialDeleter delete(final String credentialListSid, final String sid) {
        return new SipCredentialDeleter(credentialListSid, sid);
    }

    public static SipCredentialDeleter delete(final SipCredentialList targetCredentialList, final String sid) {
        return new SipCredentialDeleter(targetCredentialList, sid);
    }

    public static SipCredentialDeleter delete(final SipCredential target) {
        return new SipCredentialDeleter(target);
    }

    public String getSid() {
        return sid;
    }

    public String getAccountSid() {
        return accountSid;
    }

    public String getCredentialListSid() {
        return credentialListSid;
    }

    public DateTime getDateCreated() {
        return dateCreated;
    }

    public DateTime getDateUpdated() {
        return dateUpdated;
    }

    public String getUsername() {
        return username;
    }

    public String getUri() {
        return uri;
    }

    public static SipCredential fromJson(final String json, final ObjectMapper objectMapper) {
        try {
            return objectMapper.readValue(json, SipCredential.class);
        } catch (final JsonMappingException | JsonParseException e) {
            throw new ApiException(e.getMessage(), e);
        } catch (final IOException e) {
            throw new ApiConnectionException(e.getMessage(), e);
        }
    }

    public static SipCredential fromJson(final InputStream json, final ObjectMapper objectMapper) {
        try {
            return objectMapper.readValue(json, SipCredential.class);
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

        SipCredential self = (SipCredential) o;

        return (Objects.equals(dateUpdated, self.dateUpdated) &&
                Objects.equals(username, self.username) &&
                Objects.equals(uri, self.uri) &&
                Objects.equals(accountSid, self.accountSid) &&
                Objects.equals(credentialListSid, self.credentialListSid) &&
                Objects.equals(sid, self.sid) &&
                Objects.equals(dateCreated, self.dateCreated));
    }

    @Override
    public int hashCode() {
        return Objects.hash(dateUpdated, username, uri, accountSid, credentialListSid, sid, dateCreated);
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                          .add("sid", sid)
                          .add("accountSid", accountSid)
                          .add("credentialListSid", credentialListSid)
                          .add("username", username)
                          .add("dateCreated", dateCreated)
                          .add("dateUpdated", dateUpdated)
                          .add("uri", uri)
                          .toString();
    }

}
