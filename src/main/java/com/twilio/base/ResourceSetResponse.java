package com.twilio.base;

import org.apache.hc.core5.http.Header;

import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * A wrapper around ResourceSet that preserves HTTP response metadata (headers and status code)
 * from the initial page request while enabling pagination across multiple pages.
 *
 * <p>This class implements Iterable to allow direct iteration over resources while
 * providing access to the response metadata from the first page.</p>
 *
 * @param <E> type of the resource
 */
public class ResourceSetResponse<E extends Resource> implements Iterable<E> {

    private final ResourceSet<E> resourceSet;
    private final int statusCode;
    private final Map<String, String> headers;

    /**
     * Create a ResourceSetResponse with the resource set, status code, and headers.
     *
     * @param resourceSet the underlying resource set
     * @param statusCode  HTTP status code from the first page response
     * @param headers     response headers from the first page as array
     */
    public ResourceSetResponse(final ResourceSet<E> resourceSet, final int statusCode, final Header[] headers) {
        this.resourceSet = resourceSet;
        this.statusCode = statusCode;
        this.headers = convertHeaders(headers);
    }

    /**
     * Get the underlying ResourceSet.
     *
     * @return the resource set
     */
    public ResourceSet<E> getResourceSet() {
        return resourceSet;
    }

    /**
     * Get the HTTP status code from the first page response.
     *
     * @return the status code
     */
    public int getStatusCode() {
        return statusCode;
    }

    /**
     * Get all response headers from the first page as a map.
     *
     * @return map of header names to values
     */
    public Map<String, String> getHeaders() {
        return Collections.unmodifiableMap(headers);
    }

    /**
     * Get a specific header value by name.
     *
     * @param name the header name
     * @return the header value, or null if not present
     */
    public String getHeader(final String name) {
        return headers.get(name);
    }

    /**
     * Check if auto-paging is enabled.
     *
     * @return true if auto-paging is enabled
     */
    public boolean isAutoPaging() {
        return resourceSet.isAutoPaging();
    }

    /**
     * Enable or disable auto-paging.
     *
     * @param autoPaging whether to enable auto-paging
     * @return this ResourceSetResponse for method chaining
     */
    public ResourceSetResponse<E> setAutoPaging(final boolean autoPaging) {
        resourceSet.setAutoPaging(autoPaging);
        return this;
    }

    /**
     * Get the page size.
     *
     * @return the page size
     */
    public Integer getPageSize() {
        return resourceSet.getPageSize();
    }

    /**
     * Set the page size.
     *
     * @param pageSize the page size
     * @return this ResourceSetResponse for method chaining
     */
    public ResourceSetResponse<E> setPageSize(final int pageSize) {
        resourceSet.setPageSize(pageSize);
        return this;
    }

    /**
     * Get the limit on total records.
     *
     * @return the limit
     */
    public Long getLimit() {
        return resourceSet.getLimit();
    }

    /**
     * Set the limit on total records.
     *
     * @param limit the maximum number of records to return
     * @return this ResourceSetResponse for method chaining
     */
    public ResourceSetResponse<E> setLimit(final long limit) {
        resourceSet.setLimit(limit);
        return this;
    }

    /**
     * Get the page limit.
     *
     * @return the page limit
     */
    public long getPageLimit() {
        return resourceSet.getPageLimit();
    }

    @Override
    public Iterator<E> iterator() {
        return resourceSet.iterator();
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

    @Override
    public String toString() {
        return "ResourceSetResponse{" +
            "statusCode=" + statusCode +
            ", headers=" + headers +
            ", resourceSet=" + resourceSet +
            '}';
    }
}
