package com.twilio.http;

import com.twilio.auth_strategy.AuthStrategy;

import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.Objects;

public class Request extends IRequest {
    private static final String DEFAULT_REGION = "us1";

    public static final String QUERY_STRING_DATE_TIME_FORMAT = "yyyy-MM-dd'T'HH:mm:ss";
    public static final String QUERY_STRING_DATE_FORMAT = "yyyy-MM-dd";
    private String username;
    private String password;
    private AuthStrategy authStrategy;

    /**
     * Create a new API request.
     *
     * @param method HTTP method
     * @param url    url of request
     */
    public Request(final HttpMethod method, final String url) {
        super(method, url);
    }

    /**
     * Create a new API request.
     *
     * @param method HTTP method
     * @param domain Twilio domain
     * @param uri    uri of request
     */
    public Request(final HttpMethod method, final String domain, final String uri) {
        this(method, domain, uri, null);
    }

    /**
     * Create a new API request.
     *
     * @param method HTTP Method
     * @param domain Twilio domain
     * @param uri    uri of request
     * @param region region to make request
     */
    public Request(final HttpMethod method, final String domain, final String uri, final String region) {
        super(method, domain, uri, region);
    }

    public void setAuth(final String username, final String password) {
        this.username = username;
        this.password = password;
        this.authStrategy = null;
    }

    public void setAuth(final AuthStrategy authStrategy) {
        this.authStrategy = authStrategy;
        this.username = null;
        this.password = null;
    }

    /**
     * Create auth string from username and password.
     *
     * @return basic authentication string
     */
    public String getAuthString() {
        if (username != null && password != null) {
            final String credentials = this.username + ":" + this.password;
            final String encoded = Base64.getEncoder().encodeToString(credentials.getBytes(StandardCharsets.US_ASCII));
            return "Basic " + encoded;
        }
        if (authStrategy != null) {
            return authStrategy.getAuthString();
        }
        return "";
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public AuthStrategy getAuthStrategy() {
        return authStrategy;
    }
    
    public boolean requiresAuthentication() {
        return (username != null && password != null) || (authStrategy != null && authStrategy.requiresAuthentication());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }

        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        Request other = (Request) o;
        return Objects.equals(this.method, other.method) &&
                Objects.equals(this.buildURL(), this.buildURL()) &&
               Objects.equals(this.username, other.username) &&
               Objects.equals(this.password, other.password) && 
               Objects.equals(this.authStrategy, other.authStrategy) &&
               Objects.equals(this.queryParams, other.queryParams) &&
               Objects.equals(this.postParams, other.postParams) &&
               Objects.equals(this.headerParams, other.headerParams);
    }
}
