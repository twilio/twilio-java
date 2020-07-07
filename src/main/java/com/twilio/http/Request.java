package com.twilio.http;

import com.google.common.collect.Range;
import com.twilio.exception.ApiException;
import com.twilio.exception.InvalidRequestException;
import org.joda.time.DateTime;
import org.joda.time.DateTimeZone;
import org.joda.time.LocalDate;

import javax.xml.bind.DatatypeConverter;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.*;

public class Request {

    private static final String DEFAULT_REGION = "us1";

    public static final String QUERY_STRING_DATE_TIME_FORMAT = "yyyy-MM-dd'T'HH:mm:ss";
    public static final String QUERY_STRING_DATE_FORMAT = "yyyy-MM-dd";

    private final HttpMethod method;
    private final String url;
    private final Map<String, List<String>> queryParams;
    private final Map<String, List<String>> postParams;

    private String region;
    private String edge;
    private String username;
    private String password;

    /**
     * Create a new API request.
     *
     * @param method HTTP method
     * @param url    url of request
     */
    public Request(final HttpMethod method, final String url) {
        this.method = method;
        this.url = url;
        this.queryParams = new HashMap<>();
        this.postParams = new HashMap<>();
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
    public Request(
        final HttpMethod method,
        final String domain,
        final String uri,
        final String region
    ) {
        this.method = method;
        this.url = "https://" + domain + ".twilio.com" + uri;
        this.region = region;
        this.queryParams = new HashMap<>();
        this.postParams = new HashMap<>();
    }

    public HttpMethod getMethod() {
        return method;
    }

    public String getUrl() {
        return url;
    }

    public void setAuth(final String username, final String password) {
        this.username = username;
        this.password = password;
    }

    public void setRegion(final String region) {
        this.region = region;
    }

    public void setEdge(final String edge) {
        this.edge = edge;
    }

    /**
     * Create auth string from username and password.
     *
     * @return basic authentication string
     */
    public String getAuthString() {
        final String credentials = this.username + ":" + this.password;
        final String encoded = DatatypeConverter.printBase64Binary(credentials.getBytes(StandardCharsets.US_ASCII));
        return "Basic " + encoded;
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

    /**
     * Build the URL for the request.
     *
     * @return URL for the request
     */
    public URL constructURL() {
        String params = encodeQueryParams();
        String stringUri = buildURL();

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

    private String buildURL() {
        try {
            final URL parsedUrl = new URL(url);
            String host = parsedUrl.getHost();
            final String[] pieces = host.split("\\.");

            if (pieces.length > 1) {
                final String product = pieces[0];
                final String domain = joinIgnoreNull(".", pieces[pieces.length - 2], pieces[pieces.length - 1]);

                String targetRegion = region;
                String targetEdge = edge;

                if (pieces.length == 4) { // product.region.twilio.com
                    targetRegion = targetRegion != null ? targetRegion : pieces[1];
                } else if (pieces.length == 5) { // product.edge.region.twilio.com
                    targetEdge = targetEdge != null ? targetEdge : pieces[1];
                    targetRegion = targetRegion != null ? targetRegion : pieces[2];
                }

                if (targetEdge != null && targetRegion == null)
                    targetRegion = DEFAULT_REGION;

                host = joinIgnoreNull(".", product, targetEdge, targetRegion, domain);
            }

            return new URI(parsedUrl.getProtocol(), parsedUrl.getUserInfo(), host, parsedUrl.getPort(),
                    parsedUrl.getPath(), parsedUrl.getQuery(), parsedUrl.getRef()).toURL().toString();
        } catch (final MalformedURLException | URISyntaxException e) {
            throw new ApiException("Bad URL: " + url, e);
        }
    }

    /**
     * Add query parameters for date ranges.
     *
     * @param name  name of query parameter
     * @param range date range
     */
    public void addQueryDateRange(final String name, final Range<LocalDate> range) {
        if (range.hasLowerBound()) {
            String value = range.lowerEndpoint().toString(QUERY_STRING_DATE_FORMAT);
            addQueryParam(name + ">", value);
        }

        if (range.hasUpperBound()) {
            String value = range.upperEndpoint().toString(QUERY_STRING_DATE_FORMAT);
            addQueryParam(name + "<", value);
        }
    }

    /**
     * Add query parameters for date ranges.
     *
     * @param name  name of query parameter
     * @param range date range
     */
    public void addQueryDateTimeRange(final String name, final Range<DateTime> range) {
        if (range.hasLowerBound()) {
            String value = range.lowerEndpoint().withZone(DateTimeZone.UTC).toString(QUERY_STRING_DATE_TIME_FORMAT);
            addQueryParam(name + ">", value);
        }

        if (range.hasUpperBound()) {
            String value = range.upperEndpoint().withZone(DateTimeZone.UTC).toString(QUERY_STRING_DATE_TIME_FORMAT);
            addQueryParam(name + "<", value);
        }
    }

    /**
     * Add a query parameter.
     *
     * @param name  name of parameter
     * @param value value of parameter
     */
    public void addQueryParam(final String name, final String value) {
        addParam(queryParams, name, value);
    }

    /**
     * Add a form parameter.
     *
     * @param name  name of parameter
     * @param value value of parameter
     */
    public void addPostParam(final String name, final String value) {
        addParam(postParams, name, value);
    }

    private void addParam(final Map<String, List<String>> params, final String name, final String value) {
        if (!params.containsKey(name)) {
            params.put(name, new ArrayList<String>());
        }

        params.get(name).add(value);
    }

    /**
     * Encode the form body.
     *
     * @return url encoded form body
     */
    public String encodeFormBody() {
        return encodeParameters(postParams);
    }

    /**
     * Encode the query parameters.
     *
     * @return url encoded query parameters
     */
    public String encodeQueryParams() {
        return encodeParameters(queryParams);
    }

    private static String encodeParameters(final Map<String, List<String>> params) {
        List<String> parameters = new ArrayList<>();

        for (final Map.Entry<String, List<String>> entry : params.entrySet()) {
            try {
                String encodedName = URLEncoder.encode(entry.getKey(), "UTF-8");
                for (final String value : entry.getValue()) {
                    if (value == null) {
                        continue;
                    }

                    String encodedValue = URLEncoder.encode(value, "UTF-8");
                    parameters.add(encodedName + "=" + encodedValue);
                }
            } catch (final UnsupportedEncodingException e) {
                throw new InvalidRequestException("Couldn't encode params", entry.getKey(), e);
            }
        }
        return joinIgnoreNull("&", parameters);
    }

    private static String joinIgnoreNull(final String separator, final String... items) {
        return joinIgnoreNull(separator, Arrays.asList(items));
    }

    private static String joinIgnoreNull(final String separator, final List<String> items) {
        final StringBuilder builder = new StringBuilder();

        for (final String item : items) {
            if (item != null) {
                if (builder.length() > 0) {
                    builder.append(separator);
                }

                builder.append(item);
            }
        }

        return builder.toString();
    }

    public Map<String, List<String>> getQueryParams() {
        return queryParams;
    }

    public Map<String, List<String>> getPostParams() {
        return postParams;
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
               Objects.equals(this.buildURL(), other.buildURL()) &&
               Objects.equals(this.username, other.username) &&
               Objects.equals(this.password, other.password) &&
               Objects.equals(this.queryParams, other.queryParams) &&
               Objects.equals(this.postParams, other.postParams);
    }
}
