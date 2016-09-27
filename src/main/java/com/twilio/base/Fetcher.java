package com.twilio.base;

import com.twilio.Twilio;
import com.twilio.http.TwilioRestClient;

import java.util.concurrent.CompletableFuture;

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
    public CompletableFuture<T> async() {
        return async(Twilio.getRestClient());
    }

    /**
     * Execute an async request using specified client.
     *
     * @param client client used to make request
     * @return future that resolves to requested object
     */
    public CompletableFuture<T> async(final TwilioRestClient client) {
        return CompletableFuture.supplyAsync(() -> execute(client), Twilio.getExecutorService());
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
