package com.twilio.http.bearertoken;

import com.twilio.http.HttpMethod;
import com.twilio.http.IRequest;

public class BearerTokenRequest extends IRequest {

    private String accessToken;

    public BearerTokenRequest(HttpMethod method, String url) {
        super(method, url);
    }

    public BearerTokenRequest(HttpMethod method, String domain, String uri) {
        super(method, domain, uri);
    }

    public BearerTokenRequest(HttpMethod method, String domain, String uri, String region) {
        super(method, domain, uri, region);
    }

    /**
     * Create auth string from accessToken.
     *
     * @return basic authentication string
     */
    public String getAuthString() {
        return "Bearer " + accessToken;
    }

    public boolean requiresAuthentication() {
        return accessToken != null;
    }

    public void setAuth(String accessToken) {
        this.accessToken = accessToken;
    }
}
