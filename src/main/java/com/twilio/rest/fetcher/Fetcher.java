package com.twilio.rest.fetcher;

import com.google.common.util.concurrent.ListenableFuture;
import com.twilio.rest.Twilio;
import com.twilio.rest.http.TwilioRestClient;
import com.twilio.rest.resource.Resource;

import java.util.concurrent.Callable;

/**
 * Executor for fetches of a resource.
 *
 * @param <T> type of the resource
 */
public abstract class Fetcher<T extends Resource> {

    /**
     * Execute an async request using default client.
     *
     * @return future that resolves to requested object
     */
    public ListenableFuture<T> async() {
        return async(Twilio.getRestClient());
    }

    /**
     * Execute an async request using specified client.
     *
     * @param client client used to make request
     * @return future that resolves to requested object
     */
    public ListenableFuture<T> async(final TwilioRestClient client) {
        return Twilio.getExecutorService().submit(new Callable<T>() {
            public T call() {
                return execute(client);
            }
        });
    }

    /**
     * Execute a request using default client.
     *
     * @return Requested object
     */
    public T execute() {
        return execute(Twilio.getRestClient());
    }

    /**
     * Execute a request using specified client.
     *
     * @param client client used to make request
     * @return Requested object
     */
    public abstract T execute(final TwilioRestClient client);
}
