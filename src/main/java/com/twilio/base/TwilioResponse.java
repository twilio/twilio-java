package com.twilio.base;

import org.apache.hc.core5.http.Header;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * Wrapper class that contains the resource along with response metadata
 * including status code and headers.
 *
 * @param <T> type of the resource
 */
public class TwilioResponse<T> {

    private final T content;
    private final int statusCode;
    private final Map<String, String> headers;

    /**
     * Create a TwilioResponse with content, status code, and headers.
     *
     * @param content    the resource content
     * @param statusCode HTTP status code
     * @param headers    response headers as array
     */
    public TwilioResponse(final T content, final int statusCode, final Header[] headers) {
        this.content = content;
        this.statusCode = statusCode;
        this.headers = convertHeaders(headers);
    }

    /**
     * Get the resource content.
     *
     * @return the resource content
     */
    public T getContent() {
        return content;
    }

    /**
     * Get the HTTP status code.
     *
     * @return the status code
     */
    public int getStatusCode() {
        return statusCode;
    }

    /**
     * Get the response headers as a map.
     *
     * @return map of header names to values
     */
    public Map<String, String> getHeaders() {
        return Collections.unmodifiableMap(headers);
    }

    private Map<String, String> convertHeaders(final Header[] headerArray) {
        Map<String, String> headerMap = new HashMap<>();
        if (headerArray != null) {
            for (Header header : headerArray) {
                headerMap.put(header.getName(), header.getValue());
            }
        }
        return headerMap;
    }
}
