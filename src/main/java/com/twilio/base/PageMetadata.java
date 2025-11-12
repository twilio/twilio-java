package com.twilio.base;

import com.twilio.http.TwilioRestClient;
import java.util.Iterator;
import java.util.Map;

/**
 * Wrapper around ResourceSet that includes HTTP response metadata from first page.
 * Provides access to headers and status code while supporting pagination.
 *
 * @param <E> type of the resource
 */
public class PageMetadata<E extends Resource> implements Iterable<E> {

    private final ResourceSet<E> resourceSet;
    private final Map<String, String> headers;
    private final int statusCode;

    /**
     * Create page metadata wrapper.
     *
     * @param resourceSet the resource set with pagination
     * @param statusCode HTTP status code from first page
     * @param headers HTTP headers from first page
     */
    public PageMetadata(
            final ResourceSet<E> resourceSet,
            final int statusCode,
            final Map<String, String> headers
    ) {
        this.resourceSet = resourceSet;
        this.statusCode = statusCode;
        this.headers = headers;
    }

    /**
     * Get HTTP headers from first page response.
     *
     * @return map of header name to value
     */
    public Map<String, String> getHeaders() {
        return headers;
    }

    /**
     * Get HTTP status code from first page response.
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

    /**
     * Get underlying ResourceSet for advanced operations.
     *
     * @return the wrapped resource set
     */
    public ResourceSet<E> getResourceSet() {
        return resourceSet;
    }

    public boolean isAutoPaging() {
        return resourceSet.isAutoPaging();
    }

    public PageMetadata<E> setAutoPaging(final boolean autoPaging) {
        resourceSet.setAutoPaging(autoPaging);
        return this;
    }

    public Integer getPageSize() {
        return resourceSet.getPageSize();
    }

    public PageMetadata<E> setPageSize(final int pageSize) {
        resourceSet.setPageSize(pageSize);
        return this;
    }

    public Long getLimit() {
        return resourceSet.getLimit();
    }

    public PageMetadata<E> setLimit(final long limit) {
        resourceSet.setLimit(limit);
        return this;
    }

    public long getPageLimit() {
        return resourceSet.getPageLimit();
    }

    @Override
    public Iterator<E> iterator() {
        return resourceSet.iterator();
    }

    @Override
    public String toString() {
        return "PageMetadata{" +
                "statusCode=" + statusCode +
                ", headers=" + headers +
                ", resourceSet=" + resourceSet +
                '}';
    }
}
