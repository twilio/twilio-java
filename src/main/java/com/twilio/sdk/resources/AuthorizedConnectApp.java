package com.twilio.sdk.resources;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.base.MoreObjects;
import com.twilio.sdk.Twilio;
import com.twilio.sdk.exceptions.ApiConnectionException;
import com.twilio.sdk.exceptions.ApiException;
import com.twilio.sdk.fetchers.AuthorizedConnectAppFetcher;
import com.twilio.sdk.readers.AuthorizedConnectAppReader;
import org.joda.time.DateTime;

import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.util.Objects;

public class AuthorizedConnectApp extends SidResource {

    private static final long serialVersionUID = -5732541214023360255L;

    private final String connectAppSid;
    private final DateTime dateUpdated;
    private final String connectAppFriendlyName;
    private final String connectAppCompanyName;
    private final String uri;
    private final String accountSid;
    private final DateTime dateCreated;
    private final String connectAppDescription;
    private final URI connectAppHomepageUrl;
    private final String permissions;

    @JsonCreator
    private AuthorizedConnectApp(@JsonProperty("connect_app_sid") final String connectAppSid,
                                 @JsonProperty("date_updated") final String dateUpdated,
                                 @JsonProperty("connect_app_friendly_name") final String connectAppFriendlyName,
                                 @JsonProperty("connect_app_company_name") final String connectAppCompanyName,
                                 @JsonProperty("uri") final String uri,
                                 @JsonProperty("account_sid") final String accountSid,
                                 @JsonProperty("date_created") final String dateCreated,
                                 @JsonProperty("connect_app_description") final String connectAppDescription,
                                 @JsonProperty("connect_app_homepage_url") final URI connectAppHomepageUrl,
                                 @JsonProperty("permissions") final String permissions) {
        this.connectAppSid = connectAppSid;
        this.dateUpdated = DateTime.parse(dateUpdated, Twilio.DATE_TIME_FORMATTER);
        this.connectAppFriendlyName = connectAppFriendlyName;
        this.connectAppCompanyName = connectAppCompanyName;
        this.uri = uri;
        this.accountSid = accountSid;
        this.dateCreated = DateTime.parse(dateCreated, Twilio.DATE_TIME_FORMATTER);
        this.connectAppDescription = connectAppDescription;
        this.connectAppHomepageUrl = connectAppHomepageUrl;
        this.permissions = permissions;

    }

    public static AuthorizedConnectAppFetcher fetch(final String sid) {
        return new AuthorizedConnectAppFetcher(sid);
    }

    public static AuthorizedConnectAppReader list() {
        return new AuthorizedConnectAppReader();
    }

    public static AuthorizedConnectApp fromJson(final String json, final ObjectMapper objectMapper) {
        try {
            return objectMapper.readValue(json, AuthorizedConnectApp.class);
        } catch (final JsonMappingException | JsonParseException e) {
            throw new ApiException(e.getMessage(), e);
        } catch (final IOException e) {
            throw new ApiConnectionException(e.getMessage(), e);
        }
    }

    public static AuthorizedConnectApp fromJson(final InputStream json, final ObjectMapper objectMapper) {
        try {
            return objectMapper.readValue(json, AuthorizedConnectApp.class);
        } catch (final JsonMappingException | JsonParseException e) {
            throw new ApiException(e.getMessage(), e);
        } catch (final IOException e) {
            throw new ApiConnectionException(e.getMessage(), e);
        }
    }

    public final String getConnectAppSid() {
        return connectAppSid;
    }

    public final DateTime getDateUpdated() {
        return dateUpdated;
    }

    public final String getConnectAppFriendlyName() {
        return connectAppFriendlyName;
    }

    public final String getConnectAppCompanyName() {
        return connectAppCompanyName;
    }

    public final String getUri() {
        return uri;
    }

    public final String getAccountSid() {
        return accountSid;
    }

    public final DateTime getDateCreated() {
        return dateCreated;
    }

    public final String getConnectAppDescription() {
        return connectAppDescription;
    }

    public final URI getConnectAppHomepageUrl() {
        return connectAppHomepageUrl;
    }

    public final String getPermissions() {
        return permissions;
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        AuthorizedConnectApp self = (AuthorizedConnectApp) o;

        return (Objects.equals(connectAppSid, self.connectAppSid) &&
                Objects.equals(dateUpdated, self.dateUpdated) &&
                Objects.equals(connectAppFriendlyName, self.connectAppFriendlyName) &&
                Objects.equals(connectAppCompanyName, self.connectAppCompanyName) &&
                Objects.equals(uri, self.uri) &&
                Objects.equals(accountSid, self.accountSid) &&
                Objects.equals(dateCreated, self.dateCreated) &&
                Objects.equals(connectAppDescription, self.connectAppDescription) &&
                Objects.equals(connectAppHomepageUrl, self.connectAppHomepageUrl) &&
                Objects.equals(permissions, self.permissions));
    }

    @Override
    public int hashCode() {
        return Objects.hash(connectAppSid, dateUpdated, connectAppFriendlyName, connectAppCompanyName, uri, accountSid,
                            dateCreated, connectAppDescription, connectAppHomepageUrl, permissions);
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                          .add("connectAppSid", connectAppSid)
                          .add("dateUpdated", dateUpdated)
                          .add("connectAppFriendlyName", connectAppFriendlyName)
                          .add("connectAppCompanyName", connectAppCompanyName)
                          .add("uri", uri)
                          .add("accountSid", accountSid)
                          .add("dateCreated", dateCreated)
                          .add("connectAppDescription", connectAppDescription)
                          .add("connectAppHomepageUrl", connectAppHomepageUrl)
                          .add("permissions", permissions)
                          .toString();
    }

    @Override
    public String getSid() {
        return connectAppSid;
    }
}
