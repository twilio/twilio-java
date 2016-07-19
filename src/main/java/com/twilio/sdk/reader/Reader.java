package com.twilio.sdk.reader;

import com.google.common.util.concurrent.ListenableFuture;
import com.twilio.sdk.Twilio;
import com.twilio.sdk.http.TwilioRestClient;
import com.twilio.sdk.resource.Page;
import com.twilio.sdk.resource.Resource;
import com.twilio.sdk.resource.ResourceSet;

import java.util.concurrent.Callable;

/**
 * Executor for listing of a resource.
 *
 * @param <T> type of the resource
 */
public abstract class Reader<T extends Resource> {

    private int pageSize = 50;

    /**
     * Execute a request using default client.
     *
     * @return ResourceSet of objects
     */
    public ResourceSet<T> execute() {
        return execute(Twilio.getRestClient());
    }

    /**
     * Execute a request using specified client.
     *
     * @param client client used to make request
     * @return ResourceSet of objects
     */
    public abstract ResourceSet<T> execute(final TwilioRestClient client);

    /**
     * Execute an async request using default client.
     *
     * @return future that resolves to the ResourceSet of objects
     */
    public ListenableFuture<ResourceSet<T>> async() {
        return async(Twilio.getRestClient());
    }

    /**
     * Execute an async request using specified client.
     *
     * @param client client used to make request
     * @return future that resolves to the ResourceSet of objects
     */
    public ListenableFuture<ResourceSet<T>> async(final TwilioRestClient client) {
        return Twilio.getExecutorService().submit(new Callable<ResourceSet<T>>() {
            public ResourceSet<T> call() {
                return execute(client);
            }
        });
    }

    /**
     * Fetch the first page of resources.
     *
     * @return Page containing the first pageSize of resources
     */
    public Page<T> firstPage() {
        return firstPage(Twilio.getRestClient());
    }

    /**
     * Fetch the first page of resources using specified client.
     *
     * @param client client used to fetch
     * @return Page containing the first pageSize of resources
     */
    public abstract Page<T> firstPage(final TwilioRestClient client);

    /**
     * Fetch the following page of resources.
     *
     * @param page current page of resources
     * @return Page containing the first pageSize of resources
     */
    public Page<T> nextPage(final Page<T> page) {
        return nextPage(page, Twilio.getRestClient());
    }

    /**
     * Fetch the following page of resources using specified client.
     *
     * @param page current page of resources
     * @param client client used to fetch
     * @return Page containing the first pageSize of resources
     */
    public abstract Page<T> nextPage(final Page<T> page, final TwilioRestClient client);

    public int getPageSize() {
        return pageSize;
    }

    public Reader<T> pageSize(final int pageSize) {
        this.pageSize = pageSize;
        return this;
    }
}
