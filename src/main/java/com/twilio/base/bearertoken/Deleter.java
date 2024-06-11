package com.twilio.base.bearertoken;

import com.twilio.Twilio;
import com.twilio.TwilioBearerTokenAuth;
import com.twilio.http.bearertoken.BearerTokenTwilioRestClient;

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
        return deleteAsync(TwilioBearerTokenAuth.getRestClient());
    }

    /**
     * Execute an async request using specified client.
     *
     * @param client client used to make request
     * @return future that resolves to true if the object was deleted
     */
    public CompletableFuture<Boolean> deleteAsync(final BearerTokenTwilioRestClient client) {
        return CompletableFuture.supplyAsync(() -> delete(client), Twilio.getExecutorService());
    }

    /**
     * Execute a request using default client.
     *
     * @return true if the object was deleted
     */
    public boolean delete() {
        return delete(TwilioBearerTokenAuth.getRestClient());
    }

    /**
     * Execute a request using specified client.
     *
     * @param client client used to make request
     * @return true if the object was deleted
     */
    public abstract boolean delete(final BearerTokenTwilioRestClient client);
}
