package com.twilio.base;

import java.util.Map;

/**
 * Wrapper containing a resource along with HTTP response metadata (headers, status code).
 * Allows access to response headers while maintaining backward compatibility.
 *
 * @param <T> type of the resource
 */
public class ResponseMetadata<T extends Resource> {

    private final T resource;
    private final Map<String, String> headers;
    private final int statusCode;

    /**
     * Create response metadata wrapper.
     *
     * @param resource the resource object (Message, Call, etc.)
     * @param statusCode HTTP status code
     * @param headers HTTP response headers
     */
    public ResponseMetadata(
            final T resource,
            final int statusCode,
            final Map<String, String> headers
    ) {
        this.resource = resource;
        this.statusCode = statusCode;
        this.headers = headers;
    }

    /**
     * Get the resource.
     *
     * @return the resource
     */
    public T getResource() {
        return resource;
    }

    /**
     * Get HTTP response headers.
     *
     * @return map of header name to value
     */
    public Map<String, String> getHeaders() {
        return headers;
    }

    /**
     * Get HTTP status code.
     *
     * @return status code
     */
    public int getStatusCode() {
        return statusCode;
    }

    /**
     * Get specific header value.
     *
     * @param headerName name of the header
     * @return header value or null if not present
     */
    public String getHeader(final String headerName) {
        return headers != null ? headers.get(headerName) : null;
    }

    @Override
    public String toString() {
        return "ResponseMetadata{" +
                "statusCode=" + statusCode +
                ", headers=" + headers +
                ", resource=" + resource +
                '}';
    }
}
