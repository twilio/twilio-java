package com.twilio.sdk.resources;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.base.MoreObjects;
import com.twilio.sdk.exceptions.ApiConnectionException;
import com.twilio.sdk.exceptions.ApiException;
import com.twilio.sdk.fetchers.AuthorizedConnectAppFetcher;
import com.twilio.sdk.readers.AuthorizedConnectAppReader;

import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.util.List;
import java.util.Objects;

@JsonIgnoreProperties(ignoreUnknown = true)
public class AuthorizedConnectApp extends SidResource {

    private static final long serialVersionUID = -5732541214023360255L;

    private final String connectAppSid;
    private final String connectAppFriendlyName;
    private final String connectAppCompanyName;
    private final String uri;
    private final String accountSid;
    private final String connectAppDescription;
    private final URI connectAppHomepageUrl;
    private final List<AuthorizedConnectApp.Permission> permissions;

    @JsonCreator
    private AuthorizedConnectApp(@JsonProperty("connect_app_sid") final String connectAppSid,
                                 @JsonProperty("connect_app_friendly_name") final String connectAppFriendlyName,
                                 @JsonProperty("connect_app_company_name") final String connectAppCompanyName,
                                 @JsonProperty("uri") final String uri,
                                 @JsonProperty("account_sid") final String accountSid,
                                 @JsonProperty("connect_app_description") final String connectAppDescription,
                                 @JsonProperty("connect_app_homepage_url") final URI connectAppHomepageUrl,
                                 @JsonProperty("permissions") final List<AuthorizedConnectApp.Permission> permissions) {
        this.connectAppSid = connectAppSid;
        this.connectAppFriendlyName = connectAppFriendlyName;
        this.connectAppCompanyName = connectAppCompanyName;
        this.uri = uri;
        this.accountSid = accountSid;
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

    public final String getConnectAppDescription() {
        return connectAppDescription;
    }

    public final URI getConnectAppHomepageUrl() {
        return connectAppHomepageUrl;
    }

    public final List<AuthorizedConnectApp.Permission> getPermissions() {
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
                Objects.equals(connectAppFriendlyName, self.connectAppFriendlyName) &&
                Objects.equals(connectAppCompanyName, self.connectAppCompanyName) &&
                Objects.equals(uri, self.uri) &&
                Objects.equals(accountSid, self.accountSid) &&
                Objects.equals(connectAppDescription, self.connectAppDescription) &&
                Objects.equals(connectAppHomepageUrl, self.connectAppHomepageUrl) &&
                Objects.equals(permissions, self.permissions));
    }

    @Override
    public int hashCode() {
        return Objects.hash(connectAppSid, connectAppFriendlyName, connectAppCompanyName, uri, accountSid,
                            connectAppDescription, connectAppHomepageUrl, permissions);
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                          .add("connectAppSid", connectAppSid)
                          .add("connectAppFriendlyName", connectAppFriendlyName)
                          .add("connectAppCompanyName", connectAppCompanyName)
                          .add("uri", uri)
                          .add("accountSid", accountSid)
                          .add("connectAppDescription", connectAppDescription)
                          .add("connectAppHomepageUrl", connectAppHomepageUrl)
                          .add("permissions", permissions)
                          .toString();
    }

    @Override
    public String getSid() {
        return connectAppSid;
    }

    public enum Permission {
        GET_ALL("get-all"), POST_ALL("post-all");
        private final String permission;

        private Permission(final String permission) {
            this.permission = permission;
        }

        public String toString() {
            return permission;
        }

        @JsonCreator
        public static Permission forValue(final String value) {
            String munged = value.replace("-", "_")
                                 .toUpperCase();
            return Permission.valueOf(munged);
        }
    }
}
