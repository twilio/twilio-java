package com.twilio.base.bearertoken;

import com.twilio.TwilioBearerTokenAuth;
import com.twilio.http.bearertoken.BearerTokenTwilioRestClient;

import java.util.concurrent.CompletableFuture;

/**
 * Executor for listing of a resource.
 *
 * @param <T> type of the resource
 */
public abstract class Reader<T extends Resource> {

    private Integer pageSize;
    private Long limit;

    /**
     * Execute a request using default client.
     *
     * @return ResourceSet of objects
     */
    public ResourceSet<T> read() {
        return read(TwilioBearerTokenAuth.getRestClient());
    }

    /**
     * Execute a request using specified client.
     *
     * @param client client used to make request
     * @return ResourceSet of objects
     */
    public abstract ResourceSet<T> read(final BearerTokenTwilioRestClient client);

    /**
     * Execute an async request using default client.
     *
     * @return future that resolves to the ResourceSet of objects
     */
    public CompletableFuture<ResourceSet<T>> readAsync() {
        return readAsync(TwilioBearerTokenAuth.getRestClient());
    }

    /**
     * Execute an async request using specified client.
     *
     * @param client client used to make request
     * @return future that resolves to the ResourceSet of objects
     */
    public CompletableFuture<ResourceSet<T>> readAsync(final BearerTokenTwilioRestClient client) {
        return CompletableFuture.supplyAsync(() -> read(client), TwilioBearerTokenAuth.getExecutorService());
    }

    /**
     * Fetch the first page of resources.
     *
     * @return Page containing the first pageSize of resources
     */
    public Page<T> firstPage() {
        return firstPage(TwilioBearerTokenAuth.getRestClient());
    }

    /**
     * Fetch the first page of resources using specified client.
     *
     * @param client client used to fetch
     * @return Page containing the first pageSize of resources
     */
    public abstract Page<T> firstPage(final BearerTokenTwilioRestClient client);

    /**
     * Retrieve the target page of resources.
     *
     * @param targetUrl API-generated URL for the requested results page
     * @return Page containing the target pageSize of resources
     */
    public Page<T> getPage(final String targetUrl) {
        return getPage(targetUrl, TwilioBearerTokenAuth.getRestClient());
    }

    /**
     * Retrieve the target page of resources.
     *
     * @param targetUrl API-generated URL for the requested results page
     * @param client    client used to fetch
     * @return Page containing the target pageSize of resources
     */
    public abstract Page<T> getPage(final String targetUrl, final BearerTokenTwilioRestClient client);

    /**
     * Fetch the following page of resources.
     *
     * @param page current page of resources
     * @return Page containing the next pageSize of resources
     */
    public Page<T> nextPage(final Page<T> page) {
        return nextPage(page, TwilioBearerTokenAuth.getRestClient());
    }

    /**
     * Fetch the following page of resources using specified client.
     *
     * @param page   current page of resources
     * @param client client used to fetch
     * @return Page containing the next pageSize of resources
     */
    public abstract Page<T> nextPage(final Page<T> page, final BearerTokenTwilioRestClient client);

    /**
     * Fetch the prior page of resources.
     *
     * @param page current page of resources
     * @return Page containing the previous pageSize of resources
     */
    public Page<T> previousPage(final Page<T> page) {
        return previousPage(page, TwilioBearerTokenAuth.getRestClient());
    }

    /**
     * Fetch the prior page of resources using specified client.
     *
     * @param page   current page of resources
     * @param client client used to fetch
     * @return Page containing the previous pageSize of resources
     */
    public abstract Page<T> previousPage(final Page<T> page, final BearerTokenTwilioRestClient client);

    public Integer getPageSize() {
        return pageSize;
    }

    public Reader<T> pageSize(final int pageSize) {
        this.pageSize = pageSize;
        return this;
    }

    public Long getLimit() {
        return limit;
    }

    /**
     * Sets the max number of records to read.
     *
     * @param limit max number of records to read
     * @return this reader
     */
    public Reader<T> limit(final long limit) {
        this.limit = limit;

        if (this.pageSize == null) {
            this.pageSize = this.limit.intValue();
        }

        return this;
    }
}
