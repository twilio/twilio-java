package com.twilio.base;

import com.twilio.Twilio;
import com.twilio.http.TwilioRestClient;

import java.util.concurrent.CompletableFuture;

/**
 * Executor for deletes of a resource.
 *
 * @param <T> type of the resource
 */
public abstract class Deleter<T extends Resource> {

    /**
     * Execute an async request using default client.
     *
     * @param tw Twilio object
     * @return future that resolves to true if the object was deleted
     */
    public CompletableFuture<Boolean> deleteAsync(Twilio tw) {
        return deleteAsync(tw.getRestClient());
    }

    /**
     * Execute an async request using specified client.
     *
     * @param client client used to make request
     * @return future that resolves to true if the object was deleted
     */
    public CompletableFuture<Boolean> deleteAsync(final TwilioRestClient client) {
        return CompletableFuture.supplyAsync(() -> delete(client), Twilio.getExecutorService());
    }

    /**
     * Execute a request using default client.
     *
     * @param tw Twilio object
     * @return true if the object was deleted
     */
    public boolean delete(Twilio tw) {
        return delete(tw.getRestClient());
    }

    /**
     * Execute a request using specified client.
     *
     * @param client client used to make request
     * @return true if the object was deleted
     */
    public abstract boolean delete(final TwilioRestClient client);
}
