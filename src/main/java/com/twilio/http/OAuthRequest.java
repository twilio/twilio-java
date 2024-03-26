package com.twilio.http;

public class OAuthRequest extends Request {

    protected static final String DEFAULT_REGION = "us1";
    public static final String QUERY_STRING_DATE_TIME_FORMAT = "yyyy-MM-dd'T'HH:mm:ss";
    public static final String QUERY_STRING_DATE_FORMAT = "yyyy-MM-dd";

    private String clientId;
    private String clientSecret;

    /**
     * Create a new API request.
     *
     * @param method HTTP method
     * @param url    url of request
     */
    public OAuthRequest(final HttpMethod method, final String url) {
        super(method, url);
    }

    /**
     * Create a new API request.
     *
     * @param method HTTP method
     * @param domain Twilio domain
     * @param uri    uri of request
     */
    public OAuthRequest(final HttpMethod method, final String domain, final String uri) {
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
    public OAuthRequest(
            final HttpMethod method,
            final String domain,
            final String uri,
            final String region
    ) {
        super(method, domain, uri, region);
    }
}
