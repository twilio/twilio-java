package com.twilio.http;

import com.twilio.constant.EnumConstants;
import com.twilio.exception.ApiException;
import com.twilio.exception.InvalidRequestException;

import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.net.URLEncoder;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

public class IRequest {
    private static final String DEFAULT_REGION = "us1";

    public static final String QUERY_STRING_DATE_TIME_FORMAT = "yyyy-MM-dd'T'HH:mm:ss";
    public static final String QUERY_STRING_DATE_FORMAT = "yyyy-MM-dd";

    protected final HttpMethod method;
    protected final String url;
    protected final Map<String, List<String>> queryParams;
    protected final Map<String, List<String>> postParams;
    protected final Map<String, List<String>> headerParams;
    protected List<FormParameters> formParameters;

    protected String region;
    protected String edge;

    private List<String> userAgentExtensions;

    private EnumConstants.ContentType contentType;

    private String body;

    public static class FormParameters {
        private String name;
        private Type type;
        private Object value;

        public enum Type {
            TEXT, FILE
        }

        public FormParameters(String name, Type type, Object value) {
            this.name = name;
            this.type = type;
            this.value = value;
        }

        public String getName() {
            return name;
        }

        public Type getType() {
            return type;
        }

        public Object getValue() {
            return value;
        }
    }

    /**
     * Create a new API request.
     *
     * @param method HTTP method
     * @param url    url of request
     */
    public IRequest(final HttpMethod method, final String url) {
        this.method = method;
        this.url = url;
        this.queryParams = new HashMap<>();
        this.postParams = new HashMap<>();
        this.headerParams = new HashMap<>();
        this.formParameters = new ArrayList<>();
    }

    /**
     * Create a new API request.
     *
     * @param method HTTP method
     * @param domain Twilio domain
     * @param uri    uri of request
     */
    public IRequest(final HttpMethod method, final String domain, final String uri) {
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
    public IRequest(final HttpMethod method, final String domain, final String uri, final String region
    ) {
        this.method = method;
        this.url = "https://" + domain + ".twilio.com" + uri;
        this.region = region;
        this.queryParams = new HashMap<>();
        this.postParams = new HashMap<>();
        this.headerParams = new HashMap<>();
        this.formParameters = new ArrayList<>();
    }

    public HttpMethod getMethod() {
        return method;
    }

    public String getUrl() {
        return url;
    }

    public void setRegion(final String region) {
        this.region = region;
    }

    public void setEdge(final String edge) {
        this.edge = edge;
    }

    public void setUserAgentExtensions(List<String> userAgentExtensions) {
        this.userAgentExtensions = userAgentExtensions;
    }

    public List<String> getUserAgentExtensions() {
        return this.userAgentExtensions;
    }

    public EnumConstants.ContentType getContentType() {
        return contentType;
    }

    public void setContentType(EnumConstants.ContentType contentType) {
        this.contentType = contentType;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public List<FormParameters> getFormParameters() {
        return this.formParameters;
    }

    public void addFormParameter(String name, FormParameters.Type type, Object value) {
        this.formParameters.add(new FormParameters(name, type, value));
    }

    public void setFormParameters(final List<FormParameters> formParameters) {
        if (formParameters == null) {
            throw new IllegalArgumentException("Form parameters cannot be null");
        }
        this.formParameters.addAll(formParameters);
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
            throw new ApiException("Bad URI: " + e.getMessage());
        } catch (final MalformedURLException e) {
            throw new ApiException("Bad URL: " + e.getMessage());
        }
    }

    protected String buildURL() {
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

            String urlPort = parsedUrl.getPort() != -1 ? ":" + parsedUrl.getPort() : null;
            String protocol = parsedUrl.getProtocol() + "://";
            String[] pathPieces = parsedUrl.getPath().split("/");
            for (int i = 0; i < pathPieces.length; i++) {
                pathPieces[i] = URLEncoder.encode(pathPieces[i], "UTF-8");
            }
            String encodedPath = String.join("/", pathPieces);
            String query = parsedUrl.getQuery() != null ? "?" + parsedUrl.getQuery() : null;
            String ref = parsedUrl.getRef() != null ? "#" + parsedUrl.getRef() : null;
            String credentials = parsedUrl.getUserInfo() != null ? parsedUrl.getUserInfo() + "@" : null;
            return joinIgnoreNull("", protocol, credentials, host, urlPort, encodedPath, query, ref);
        } catch (final MalformedURLException | UnsupportedEncodingException e) {
            throw new ApiException("Bad URL: " + e.getMessage());
        }
    }

    /**
     * Add query parameters for date ranges.
     *
     * @param name       name of query parameter
     * @param lowerBound lower bound of LocalDate range
     * @param upperBound upper bound of LocalDate range
     */
    public void addQueryDateRange(final String name, LocalDate lowerBound, LocalDate upperBound) {
        if (lowerBound != null) {
            String value = lowerBound.toString();
            addQueryParam(name + ">", value);
        }

        if (upperBound != null) {
            String value = upperBound.toString();
            addQueryParam(name + "<", value);
        }
    }

    /**
     * Add query parameters for date ranges.
     *
     * @param name       name of query parameter
     * @param lowerBound lower bound of ZonedDateTime range
     * @param upperBound upper bound of ZonedDateTime range
     */
    public void addQueryDateTimeRange(final String name, ZonedDateTime lowerBound, ZonedDateTime upperBound) {
        if (lowerBound != null) {
            String value = lowerBound.withZoneSameInstant(ZoneId.of("UTC")).format(DateTimeFormatter.ofPattern(QUERY_STRING_DATE_TIME_FORMAT));
            addQueryParam(name + ">", value);
        }

        if (upperBound != null) {
            String value = upperBound.withZoneSameInstant(ZoneId.of("UTC")).format(DateTimeFormatter.ofPattern(QUERY_STRING_DATE_TIME_FORMAT));
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

    /**
     * Add a header parameter.
     *
     * @param name  name of parameter
     * @param value value of parameter
     */
    public void addHeaderParam(final String name, final String value) {
        addParam(headerParams, name, value);
    }

    private void addParam(final Map<String, List<String>> params, final String name, final String value) {
        if (value == null || value.equals("null"))
            return;

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

    public Map<String, List<String>> getHeaderParams() {
        return headerParams;
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
                Objects.equals(this.queryParams, other.queryParams) &&
                Objects.equals(this.postParams, other.postParams) &&
                Objects.equals(this.headerParams, other.headerParams);
    }
}
