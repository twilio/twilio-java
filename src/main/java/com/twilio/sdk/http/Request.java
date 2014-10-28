package com.twilio.sdk.http;


import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;

public class Request {
    protected String method;
    protected String uri;
    protected String username;
    protected String password;

    public Request(String method, String uri) {
        this.method = method;
        this.uri = uri;
    }

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    public String getUri() {
        return uri;
    }

    public void setUri(String uri) {
        this.uri = uri;
    }

    public void setAuth(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public boolean requiresAuthentication() {
        return username != null || password != null;
    }

    public URL constructURL() {
        String params = this.encodeQueryParams();
        String stringUri = this.uri + "?" + params;
        try {
            URI uri = new URI(stringUri);
            return uri.toURL();
        } catch(URISyntaxException e) {
            throw new RuntimeException("Bad URI: " + stringUri);
        } catch(MalformedURLException e) {
            throw new RuntimeException("Bad URL: " + stringUri);
        }
    }

    private String encodeQueryParams() {
        return ""; // TODO implement this
    }


}