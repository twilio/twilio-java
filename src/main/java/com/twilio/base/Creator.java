package com.twilio.base;

import com.twilio.Twilio;
import com.twilio.http.TwilioRestClient;

import java.util.concurrent.CompletableFuture;

/**
 * Executor for creation of a resource.
 *
 * @param <T> type of the resource
 */
public abstract class Creator<T extends Resource> {

    /**
     * Execute an async request using default client.
     *
     * @return future that resolves to requested object
     */
    public CompletableFuture<T> createAsync() {
        return createAsync(Twilio.getRestClient());
    }

    /**
     * Execute an async request using specified client.
     *
     * @param client client used to make request
     * @return future that resolves to requested object
     */
    public CompletableFuture<T> createAsync(final TwilioRestClient client) {
        return CompletableFuture.supplyAsync(() -> create(client), Twilio.getExecutorService());
    }

    /**
     * Execute a request using default client.
     *
     * @return Requested object
     */
    public T create() {
        return create(Twilio.getRestClient());
    }

    /**
     * Execute a request using specified client.
     *
     * @param client client used to make request
     * @return Requested object
     */
    public abstract T create(final TwilioRestClient client);
}
