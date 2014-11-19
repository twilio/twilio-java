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
import com.twilio.sdk.fetchers.ConnectAppFetcher;
import com.twilio.sdk.http.HttpMethod;
import com.twilio.sdk.readers.ConnectAppReader;
import com.twilio.sdk.updaters.ConnectAppUpdater;
import org.joda.time.DateTime;

import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.util.Objects;

public class ConnectApp extends SidResource {

    private static final long serialVersionUID = -5732541214023360255L;

    private final URI authorizeRedirectUrl;
    private final String description;
    private final DateTime dateUpdated;
    private final HttpMethod deauthorizeCallbackMethod;
    private final String friendlyName;
    private final String uri;
    private final URI deauthorizeCallbackUrl;
    private final String accountSid;
    private final URI homepageUrl;
    private final String companyName;
    private final String sid;
    private final DateTime dateCreated;
    private final String permissions;

    @JsonCreator
    private ConnectApp(@JsonProperty("authorize_redirect_url") final URI authorizeRedirectUrl,
                       @JsonProperty("description") final String description,
                       @JsonProperty("date_updated") final String dateUpdated,
                       @JsonProperty("deauthorize_callback_method") final HttpMethod deauthorizeCallbackMethod,
                       @JsonProperty("friendly_name") final String friendlyName, @JsonProperty("uri") final String uri,
                       @JsonProperty("deauthorize_callback_url") final URI deauthorizeCallbackUrl,
                       @JsonProperty("account_sid") final String accountSid,
                       @JsonProperty("homepage_url") final URI homepageUrl,
                       @JsonProperty("company_name") final String companyName, @JsonProperty("sid") final String sid,
                       @JsonProperty("date_created") final String dateCreated,
                       @JsonProperty("permissions") final String permissions) {
        this.authorizeRedirectUrl = authorizeRedirectUrl;
        this.description = description;
        this.dateUpdated = DateTime.parse(dateUpdated, Twilio.DATE_TIME_FORMATTER);
        this.deauthorizeCallbackMethod = deauthorizeCallbackMethod;
        this.friendlyName = friendlyName;
        this.uri = uri;
        this.deauthorizeCallbackUrl = deauthorizeCallbackUrl;
        this.accountSid = accountSid;
        this.homepageUrl = homepageUrl;
        this.companyName = companyName;
        this.sid = sid;
        this.dateCreated = DateTime.parse(dateCreated, Twilio.DATE_TIME_FORMATTER);
        this.permissions = permissions;

    }

    public static ConnectAppFetcher fetch(final String sid) {
        return new ConnectAppFetcher(sid);
    }

    public static ConnectAppReader list() {
        return new ConnectAppReader();
    }

    public static ConnectAppUpdater update(final ConnectApp target) {
        return new ConnectAppUpdater(target);
    }

    public static ConnectAppUpdater update(final String sid) {
        return new ConnectAppUpdater(sid);
    }

    public static ConnectApp fromJson(final String json, final ObjectMapper objectMapper) {
        try {
            return objectMapper.readValue(json, ConnectApp.class);
        } catch (final JsonMappingException | JsonParseException e) {
            throw new ApiException(e.getMessage(), e);
        } catch (final IOException e) {
            throw new ApiConnectionException(e.getMessage(), e);
        }
    }

    public static ConnectApp fromJson(final InputStream json, final ObjectMapper objectMapper) {
        try {
            return objectMapper.readValue(json, ConnectApp.class);
        } catch (final JsonMappingException | JsonParseException e) {
            throw new ApiException(e.getMessage(), e);
        } catch (final IOException e) {
            throw new ApiConnectionException(e.getMessage(), e);
        }
    }

    public final URI getAuthorizeRedirectUrl() {
        return authorizeRedirectUrl;
    }

    public final String getDescription() {
        return description;
    }

    public final DateTime getDateUpdated() {
        return dateUpdated;
    }

    public final HttpMethod getDeauthorizeCallbackMethod() {
        return deauthorizeCallbackMethod;
    }

    public final String getFriendlyName() {
        return friendlyName;
    }

    public final String getUri() {
        return uri;
    }

    public final URI getDeauthorizeCallbackUrl() {
        return deauthorizeCallbackUrl;
    }

    public final String getAccountSid() {
        return accountSid;
    }

    public final URI getHomepageUrl() {
        return homepageUrl;
    }

    public final String getCompanyName() {
        return companyName;
    }

    public final String getSid() {
        return sid;
    }

    public final DateTime getDateCreated() {
        return dateCreated;
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

        ConnectApp self = (ConnectApp) o;

        return (Objects.equals(authorizeRedirectUrl, self.authorizeRedirectUrl) &&
                Objects.equals(description, self.description) &&
                Objects.equals(dateUpdated, self.dateUpdated) &&
                Objects.equals(deauthorizeCallbackMethod, self.deauthorizeCallbackMethod) &&
                Objects.equals(friendlyName, self.friendlyName) &&
                Objects.equals(uri, self.uri) &&
                Objects.equals(deauthorizeCallbackUrl, self.deauthorizeCallbackUrl) &&
                Objects.equals(accountSid, self.accountSid) &&
                Objects.equals(homepageUrl, self.homepageUrl) &&
                Objects.equals(companyName, self.companyName) &&
                Objects.equals(sid, self.sid) &&
                Objects.equals(dateCreated, self.dateCreated) &&
                Objects.equals(permissions, self.permissions));
    }

    @Override
    public int hashCode() {
        return Objects.hash(authorizeRedirectUrl, description, dateUpdated, deauthorizeCallbackMethod, friendlyName,
                            uri, deauthorizeCallbackUrl, accountSid, homepageUrl, companyName, sid, dateCreated,
                            permissions);
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                          .add("authorizeRedirectUrl", authorizeRedirectUrl)
                          .add("description", description)
                          .add("dateUpdated", dateUpdated)
                          .add("deauthorizeCallbackMethod", deauthorizeCallbackMethod)
                          .add("friendlyName", friendlyName)
                          .add("uri", uri)
                          .add("deauthorizeCallbackUrl", deauthorizeCallbackUrl)
                          .add("accountSid", accountSid)
                          .add("homepageUrl", homepageUrl)
                          .add("companyName", companyName)
                          .add("sid", sid)
                          .add("dateCreated", dateCreated)
                          .add("permissions", permissions)
                          .toString();
    }
}
