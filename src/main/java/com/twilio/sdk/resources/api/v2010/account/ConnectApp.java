package com.twilio.sdk.resources.api.v2010.account;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.base.MoreObjects;
import com.twilio.sdk.clients.TwilioRestClient;
import com.twilio.sdk.exceptions.ApiConnectionException;
import com.twilio.sdk.exceptions.ApiException;
import com.twilio.sdk.fetchers.api.v2010.account.ConnectAppFetcher;
import com.twilio.sdk.http.HttpMethod;
import com.twilio.sdk.http.Request;
import com.twilio.sdk.http.Response;
import com.twilio.sdk.readers.api.v2010.account.ConnectAppReader;
import com.twilio.sdk.resources.RestException;
import com.twilio.sdk.resources.SidResource;
import com.twilio.sdk.updaters.api.v2010.account.ConnectAppUpdater;

import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.util.List;
import java.util.Map;
import java.util.Objects;

@JsonIgnoreProperties(ignoreUnknown = true)
public class ConnectApp extends SidResource {
    private static final long serialVersionUID = 247633580579926L;

    public enum Permission {
        GET_ALL("get-all"),
        POST_ALL("post-all");
    
        private final String value;
        
        private Permission(final String value) {
            this.value = value;
        }
        
        public String toString() {
            return value;
        }
        
        @JsonCreator
        public static Permission forValue(final String value) {
            String normalized = value.replace("-", "_").toUpperCase();
            return Permission.valueOf(normalized);
        }
    }

    /**
     * Fetch an instance of a connect-app
     * 
     * @param accountSid The account_sid
     * @param sid Fetch by unique connect-app Sid
     * @return ConnectAppFetcher capable of executing the fetch
     */
    public static ConnectAppFetcher fetch(final String accountSid, final String sid) {
        return new ConnectAppFetcher(accountSid, sid);
    }

    /**
     * Update a connect-app with the specified parameters
     * 
     * @param accountSid The account_sid
     * @param sid The sid
     * @return ConnectAppUpdater capable of executing the update
     */
    public static ConnectAppUpdater update(final String accountSid, final String sid) {
        return new ConnectAppUpdater(accountSid, sid);
    }

    /**
     * Retrieve a list of connect-apps belonging to the account used to make the
     * request
     * 
     * @param accountSid The account_sid
     * @return ConnectAppReader capable of executing the read
     */
    public static ConnectAppReader read(final String accountSid) {
        return new ConnectAppReader(accountSid);
    }

    /**
     * Converts a JSON String into a ConnectApp object using the provided
     * ObjectMapper
     * 
     * @param json Raw JSON String
     * @param objectMapper Jackson ObjectMapper
     * @return ConnectApp object represented by the provided JSON
     */
    public static ConnectApp fromJson(final String json, final ObjectMapper objectMapper) {
        // Convert all checked exceptions to Runtime
        try {
            return objectMapper.readValue(json, ConnectApp.class);
        } catch (final JsonMappingException | JsonParseException e) {
            throw new ApiException(e.getMessage(), e);
        } catch (final IOException e) {
            throw new ApiConnectionException(e.getMessage(), e);
        }
    }

    /**
     * Converts a JSON InputStream into a ConnectApp object using the provided
     * ObjectMapper
     * 
     * @param json Raw JSON InputStream
     * @param objectMapper Jackson ObjectMapper
     * @return ConnectApp object represented by the provided JSON
     */
    public static ConnectApp fromJson(final InputStream json, final ObjectMapper objectMapper) {
        // Convert all checked exceptions to Runtime
        try {
            return objectMapper.readValue(json, ConnectApp.class);
        } catch (final JsonMappingException | JsonParseException e) {
            throw new ApiException(e.getMessage(), e);
        } catch (final IOException e) {
            throw new ApiConnectionException(e.getMessage(), e);
        }
    }

    private final String accountSid;
    private final URI authorizeRedirectUrl;
    private final String companyName;
    private final HttpMethod deauthorizeCallbackMethod;
    private final URI deauthorizeCallbackUrl;
    private final String description;
    private final String friendlyName;
    private final URI homepageUrl;
    private final List<ConnectApp.Permission> permissions;
    private final String sid;
    private final String uri;

    @JsonCreator
    private ConnectApp(@JsonProperty("account_sid")
                       final String accountSid, 
                       @JsonProperty("authorize_redirect_url")
                       final URI authorizeRedirectUrl, 
                       @JsonProperty("company_name")
                       final String companyName, 
                       @JsonProperty("deauthorize_callback_method")
                       final HttpMethod deauthorizeCallbackMethod, 
                       @JsonProperty("deauthorize_callback_url")
                       final URI deauthorizeCallbackUrl, 
                       @JsonProperty("description")
                       final String description, 
                       @JsonProperty("friendly_name")
                       final String friendlyName, 
                       @JsonProperty("homepage_url")
                       final URI homepageUrl, 
                       @JsonProperty("permissions")
                       final List<ConnectApp.Permission> permissions, 
                       @JsonProperty("sid")
                       final String sid, 
                       @JsonProperty("uri")
                       final String uri) {
        this.accountSid = accountSid;
        this.authorizeRedirectUrl = authorizeRedirectUrl;
        this.companyName = companyName;
        this.deauthorizeCallbackMethod = deauthorizeCallbackMethod;
        this.deauthorizeCallbackUrl = deauthorizeCallbackUrl;
        this.description = description;
        this.friendlyName = friendlyName;
        this.homepageUrl = homepageUrl;
        this.permissions = permissions;
        this.sid = sid;
        this.uri = uri;
    }

    /**
     * @return The unique sid that identifies this account
     */
    public final String getAccountSid() {
        return this.accountSid;
    }

    /**
     * @return URIL Twilio sends requests when users authorize
     */
    public final URI getAuthorizeRedirectUrl() {
        return this.authorizeRedirectUrl;
    }

    /**
     * @return The company name set for this Connect App.
     */
    public final String getCompanyName() {
        return this.companyName;
    }

    /**
     * @return HTTP method Twilio WIll use making requests to the url
     */
    public final HttpMethod getDeauthorizeCallbackMethod() {
        return this.deauthorizeCallbackMethod;
    }

    /**
     * @return URL Twilio will send a request when a user de-authorizes this app
     */
    public final URI getDeauthorizeCallbackUrl() {
        return this.deauthorizeCallbackUrl;
    }

    /**
     * @return A more detailed human readable description
     */
    public final String getDescription() {
        return this.description;
    }

    /**
     * @return A human readable name for the Connect App.
     */
    public final String getFriendlyName() {
        return this.friendlyName;
    }

    /**
     * @return The URL users can obtain more information
     */
    public final URI getHomepageUrl() {
        return this.homepageUrl;
    }

    /**
     * @return The set of permissions that your ConnectApp requests.
     */
    public final List<ConnectApp.Permission> getPermissions() {
        return this.permissions;
    }

    /**
     * @return A string that uniquely identifies this connect-apps
     */
    public final String getSid() {
        return this.sid;
    }

    /**
     * @return The URI for this resource
     */
    public final String getUri() {
        return this.uri;
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        
        ConnectApp other = (ConnectApp) o;
        
        return Objects.equals(accountSid, other.accountSid) && 
               Objects.equals(authorizeRedirectUrl, other.authorizeRedirectUrl) && 
               Objects.equals(companyName, other.companyName) && 
               Objects.equals(deauthorizeCallbackMethod, other.deauthorizeCallbackMethod) && 
               Objects.equals(deauthorizeCallbackUrl, other.deauthorizeCallbackUrl) && 
               Objects.equals(description, other.description) && 
               Objects.equals(friendlyName, other.friendlyName) && 
               Objects.equals(homepageUrl, other.homepageUrl) && 
               Objects.equals(permissions, other.permissions) && 
               Objects.equals(sid, other.sid) && 
               Objects.equals(uri, other.uri);
    }

    @Override
    public int hashCode() {
        return Objects.hash(accountSid,
                            authorizeRedirectUrl,
                            companyName,
                            deauthorizeCallbackMethod,
                            deauthorizeCallbackUrl,
                            description,
                            friendlyName,
                            homepageUrl,
                            permissions,
                            sid,
                            uri);
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                          .add("accountSid", accountSid)
                          .add("authorizeRedirectUrl", authorizeRedirectUrl)
                          .add("companyName", companyName)
                          .add("deauthorizeCallbackMethod", deauthorizeCallbackMethod)
                          .add("deauthorizeCallbackUrl", deauthorizeCallbackUrl)
                          .add("description", description)
                          .add("friendlyName", friendlyName)
                          .add("homepageUrl", homepageUrl)
                          .add("permissions", permissions)
                          .add("sid", sid)
                          .add("uri", uri)
                          .toString();
    }
}