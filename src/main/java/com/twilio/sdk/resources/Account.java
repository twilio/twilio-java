package com.twilio.sdk.resources;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.base.MoreObjects;
import com.twilio.sdk.converters.MarshalConverter;
import com.twilio.sdk.creators.AccountCreator;
import com.twilio.sdk.exceptions.ApiConnectionException;
import com.twilio.sdk.exceptions.ApiException;
import com.twilio.sdk.fetchers.AccountFetcher;
import com.twilio.sdk.readers.AccountReader;
import com.twilio.sdk.updaters.AccountUpdater;
import org.joda.time.DateTime;

import java.io.IOException;
import java.io.InputStream;
import java.util.Map;
import java.util.Objects;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Account extends SidResource {

    private static final long serialVersionUID = -5732541214023360255L;

    private final Account.Status status;
    private final Map<String, String> subresourceUris;
    private final DateTime dateUpdated;
    private final String authToken;
    private final String friendlyName;
    private final String ownerAccountSid;
    private final String uri;
    private final String sid;
    private final DateTime dateCreated;
    private final Account.Type type;

    @JsonCreator
    private Account(@JsonProperty("status") final Account.Status status,
                    @JsonProperty("subresource_uris") final Map<String, String> subresourceUris,
                    @JsonProperty("date_updated") final String dateUpdated,
                    @JsonProperty("auth_token") final String authToken,
                    @JsonProperty("friendly_name") final String friendlyName,
                    @JsonProperty("owner_account_sid") final String ownerAccountSid,
                    @JsonProperty("uri") final String uri, @JsonProperty("sid") final String sid,
                    @JsonProperty("date_created") final String dateCreated,
                    @JsonProperty("type") final Account.Type type) {
        this.status = status;
        this.subresourceUris = subresourceUris;
        this.dateUpdated = MarshalConverter.dateTimeFromString(dateUpdated);
        this.authToken = authToken;
        this.friendlyName = friendlyName;
        this.ownerAccountSid = ownerAccountSid;
        this.uri = uri;
        this.sid = sid;
        this.dateCreated = MarshalConverter.dateTimeFromString(dateCreated);
        this.type = type;

    }

    public static AccountCreator create() {
        return new AccountCreator();
    }

    public static AccountFetcher fetch(final String sid) {
        return new AccountFetcher(sid);
    }

    public static AccountReader list() {
        return new AccountReader();
    }

    public static AccountUpdater update(final Account target) {
        return new AccountUpdater(target);
    }

    public static AccountUpdater update(final String sid) {
        return new AccountUpdater(sid);
    }

    public static Account fromJson(final String json, final ObjectMapper objectMapper) {
        try {
            return objectMapper.readValue(json, Account.class);
        } catch (final JsonMappingException | JsonParseException e) {
            throw new ApiException(e.getMessage(), e);
        } catch (final IOException e) {
            throw new ApiConnectionException(e.getMessage(), e);
        }
    }

    public static Account fromJson(final InputStream json, final ObjectMapper objectMapper) {
        try {
            return objectMapper.readValue(json, Account.class);
        } catch (final JsonMappingException | JsonParseException e) {
            throw new ApiException(e.getMessage(), e);
        } catch (final IOException e) {
            throw new ApiConnectionException(e.getMessage(), e);
        }
    }

    public final Account.Status getStatus() {
        return status;
    }

    public final Map<String, String> getSubresourceUris() {
        return subresourceUris;
    }

    public final DateTime getDateUpdated() {
        return dateUpdated;
    }

    public final String getAuthToken() {
        return authToken;
    }

    public final String getFriendlyName() {
        return friendlyName;
    }

    public final String getOwnerAccountSid() {
        return ownerAccountSid;
    }

    public final String getUri() {
        return uri;
    }

    public final String getSid() {
        return sid;
    }

    public final DateTime getDateCreated() {
        return dateCreated;
    }

    public final Account.Type getType() {
        return type;
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        Account self = (Account) o;

        return (Objects.equals(status, self.status) &&
                Objects.equals(subresourceUris, self.subresourceUris) &&
                Objects.equals(dateUpdated, self.dateUpdated) &&
                Objects.equals(authToken, self.authToken) &&
                Objects.equals(friendlyName, self.friendlyName) &&
                Objects.equals(ownerAccountSid, self.ownerAccountSid) &&
                Objects.equals(uri, self.uri) &&
                Objects.equals(sid, self.sid) &&
                Objects.equals(dateCreated, self.dateCreated) &&
                Objects.equals(type, self.type));
    }

    @Override
    public int hashCode() {
        return Objects.hash(status, subresourceUris, dateUpdated, authToken, friendlyName, ownerAccountSid, uri, sid,
                            dateCreated, type);
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                          .add("status", status)
                          .add("subresourceUris", subresourceUris)
                          .add("dateUpdated", dateUpdated)
                          .add("authToken", authToken)
                          .add("friendlyName", friendlyName)
                          .add("ownerAccountSid", ownerAccountSid)
                          .add("uri", uri)
                          .add("sid", sid)
                          .add("dateCreated", dateCreated)
                          .add("type", type)
                          .toString();
    }

    public enum Status {
        ACTIVE("active"), SUSPENDED("suspended"), CLOSED("closed");
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

    public enum Type {
        TRIAL("Trial"), FULL("Full");
        private final String type;

        private Type(final String type) {
            this.type = type;
        }

        public String toString() {
            return type;
        }

        @JsonCreator
        public static Type forValue(final String value) {
            String munged = value.replace("-", "_")
                                 .toUpperCase();
            return Type.valueOf(munged);
        }
    }
}
