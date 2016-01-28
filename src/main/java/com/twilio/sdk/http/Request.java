package com.twilio.sdk.http;

import com.google.common.collect.Range;
import com.twilio.sdk.clients.TwilioRestClient;
import com.twilio.sdk.exceptions.ApiException;
import com.twilio.sdk.exceptions.InvalidRequestException;
import org.joda.time.DateTime;

import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Request {

    public static final String QUERY_STRING_DATE_FORMAT = "yyyy-MM-dd";

    private final HttpMethod method;
    private final String uri;
    private String username;
    private String password;
    private final Map<String, ArrayList<String>> queryParams;
    private final Map<String, ArrayList<String>> postParams;

    public Request(final HttpMethod method, final String uri, final String accountSid) {
        this(method, TwilioRestClient.Domains.API, uri, accountSid);
    }

    public Request(final HttpMethod method, final TwilioRestClient.Domains domain,
                   final String uri, final String accountSid) {
        this.method = method;
        this.uri = "https://" + domain.toString() + ".twilio.com" + uri;
        queryParams = new HashMap<>();
        postParams = new HashMap<>();
    }

    public HttpMethod getMethod() {
        return method;
    }

    public String getUri() {
        return uri;
    }

    public void setAuth(final String username, final String password) {
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
        String params = encodeQueryParams();
        String stringUri = uri;

        if (params.length() > 0) {
            stringUri += "?" + params;
        }

        try {
            URI uri = new URI(stringUri);
            return uri.toURL();
        } catch (final URISyntaxException e) {
            throw new ApiException("Bad URI: " + stringUri, e);
        } catch (final MalformedURLException e) {
            throw new ApiException("Bad URL: " + stringUri, e);
        }
    }

    public void addQueryParam(final String name, final String value) {
        if (!queryParams.containsKey(name)) {
            queryParams.put(name, new ArrayList<String>());
        }

        queryParams.get(name)
                   .add(value);
    }

    public void addQueryDateRange(final String name, final Range<DateTime> range) {
        if (range.hasLowerBound()) {
            String value = range.lowerEndpoint()
                                .toString(QUERY_STRING_DATE_FORMAT);
            addQueryParam(name + ">", value);
        }

        if (range.hasUpperBound()) {
            String value = range.upperEndpoint()
                                .toString(QUERY_STRING_DATE_FORMAT);
            addQueryParam(name + "<", value);
        }
    }

    public void addPostParam(final String name, final String value) {
        if (!postParams.containsKey(name)) {
            postParams.put(name, new ArrayList<String>());
        }

        postParams.get(name)
                  .add(value);
    }

    private static String encodeParameters(final Map<String, ArrayList<String>> params) {
        StringBuilder builder = new StringBuilder();

        for (final Map.Entry<String, ArrayList<String>> entry : params.entrySet()) {
            try {
                String encodedName = URLEncoder.encode(entry.getKey(), "UTF-8");
                for (final String value : entry.getValue()) {
                    String encodedValue = URLEncoder.encode(value, "UTF-8");
                    builder.append(encodedName);
                    builder.append("=");
                    builder.append(encodedValue);
                    builder.append("&");
                }
            } catch (final UnsupportedEncodingException e) {
                throw new InvalidRequestException("Couldn't encode params", entry.getKey(), e);
            }
        }
        if (builder.length() > 0 && builder.charAt(builder.length() - 1) == '&') {
            builder.deleteCharAt(builder.length() - 1);
        }
        return builder.toString();
    }

    public String encodeFormBody() {
        return encodeParameters(postParams);
    }

    public String encodeQueryParams() {
        return encodeParameters(queryParams);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null)
            return false;
        if (!(o instanceof Request))
            return false;

        Request other = (Request) o;

        if (this.method.equals(other.getMethod()) &&
                this.uri.equals(other.getUri()) &&
                this.encodeQueryParams().equals(other.encodeQueryParams()) &&
                this.encodeFormBody().equals(other.encodeFormBody())) {
            return true;
        }

        return false;
    }
}
