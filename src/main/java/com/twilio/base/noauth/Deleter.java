package com.twilio.base.noauth;

import com.twilio.Twilio;
import com.twilio.TwilioNoAuth;
import com.twilio.http.noauth.NoAuthTwilioRestClient;

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
     * @return future that resolves to true if the object was deleted
     */
    public CompletableFuture<Boolean> deleteAsync() {
        return deleteAsync(TwilioNoAuth.getRestClient());
    }

    /**
     * Execute an async request using specified client.
     *
     * @param client client used to make request
     * @return future that resolves to true if the object was deleted
     */
    public CompletableFuture<Boolean> deleteAsync(final NoAuthTwilioRestClient client) {
        return CompletableFuture.supplyAsync(() -> delete(client), Twilio.getExecutorService());
    }

    /**
     * Execute a request using default client.
     *
     * @return true if the object was deleted
     */
    public boolean delete() {
        return delete(TwilioNoAuth.getRestClient());
    }

    /**
     * Execute a request using specified client.
     *
     * @param client client used to make request
     * @return true if the object was deleted
     */
    public abstract boolean delete(final NoAuthTwilioRestClient client);
}
