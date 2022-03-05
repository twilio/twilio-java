package com.twilio.base;

import com.twilio.Twilio;
import com.twilio.http.TwilioRestClient;

import java.util.concurrent.CompletableFuture;

/**
 * Executor for updates of a resource.
 *
 * @param <T> type of the resource
 */
public abstract class Updater<T extends Resource> {

    /**
     * Execute an async request using default client.
     *
     * @return future that resolves to requested object
     */
    public CompletableFuture<T> updateAsync(Twilio tw) {
        return updateAsync(tw.getRestClient());
    }

    /**
     * Execute an async request using specified client.
     *
     * @param client client used to make request
     * @return future that resolves to requested object
     */
    public CompletableFuture<T> updateAsync(final TwilioRestClient client) {
        return CompletableFuture.supplyAsync(() -> update(client), Twilio.getExecutorService());
    }

    /**
     * Execute a request using default client.
     *
     * @return Requested object
     * @param tw
     */
    public T update(Twilio tw) {
        return update(tw.getRestClient());
    }

    /**
     * Execute a request using specified client.
     *
     * @param client client used to make request
     * @return Requested object
     */
    public abstract T update(final TwilioRestClient client);
}
