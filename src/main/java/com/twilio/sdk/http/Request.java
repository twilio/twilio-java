package com.twilio.sdk.http;


import com.google.common.collect.Range;
import org.joda.time.LocalDate;

import java.io.UnsupportedEncodingException;
import java.net.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Request {
    public static final String QUERY_STRING_DATE_FORMAT = "yyyy-MM-dd";

    protected String method;
    protected String uri;
    protected String username;
    protected String password;
    protected Map<String, ArrayList<String>> queryParams;
    protected Map<String, ArrayList<String>> postParams;

    public Request(String method, String uri) {
        this.method = method;
        this.uri = uri;
        this.queryParams = new HashMap<String, ArrayList<String>>();
        this.postParams = new HashMap<String, ArrayList<String>>();
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
        String stringUri = this.uri;

        if (params.length() > 0) {
            stringUri += "?" + params;
        }

        try {
            URI uri = new URI(stringUri);
            return uri.toURL();
        } catch(URISyntaxException e) {
            throw new RuntimeException("Bad URI: " + stringUri);
        } catch(MalformedURLException e) {
            throw new RuntimeException("Bad URL: " + stringUri);
        }
    }

    public void addQueryParam(String name, String value) {
        if (!this.queryParams.containsKey(name)) {
            this.queryParams.put(name, new ArrayList<String>());
        }

        this.queryParams.get(name).add(value);
    }

    public void addQueryDateRange(String name, Range<LocalDate> range) {
        if (range.hasLowerBound()) {
            String value = range.lowerEndpoint().toString(QUERY_STRING_DATE_FORMAT);
            this.addQueryParam(name + ">", value);
        }

        if (range.hasUpperBound()) {
            String value = range.upperEndpoint().toString(QUERY_STRING_DATE_FORMAT);
            this.addQueryParam(name + "<", value);
        }
    }

    public void addPostParam(String name, String value) {
        if (!this.postParams.containsKey(name)) {
            this.postParams.put(name, new ArrayList<String>());
        }

        this.postParams.get(name).add(value);
    }

    private static String encodeParameters(Map<String, ArrayList<String>> params) {
        StringBuilder builder = new StringBuilder();
        try {
            for (Map.Entry<String, ArrayList<String>> entry : params.entrySet()) {
                String encodedName = URLEncoder.encode(entry.getKey(), "UTF-8");
                for (String value : entry.getValue()) {
                    String encodedValue = URLEncoder.encode(value, "UTF-8");
                    builder.append(encodedName);
                    builder.append("=");
                    builder.append(encodedValue);
                    builder.append("&");
                }
            }
            String encoded = builder.toString();
            if (encoded.endsWith("&")) {
                encoded = encoded.substring(0, encoded.length() - 1);
            }
            return encoded;
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException("Couldn't encode params");
        }
    }

    public String encodeFormBody() {
        return encodeParameters(this.postParams);
    }

    public String encodeQueryParams() {
        return encodeParameters(this.queryParams);
    }

}