package com.twilio.base.bearertoken;

import com.twilio.TwilioBearerTokenAuth;
import com.twilio.http.bearertoken.BearerTokenTwilioRestClient;

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
    public CompletableFuture<T> updateAsync() {
        return updateAsync(TwilioBearerTokenAuth.getRestClient());
    }

    /**
     * Execute an async request using specified client.
     *
     * @param client client used to make request
     * @return future that resolves to requested object
     */
    public CompletableFuture<T> updateAsync(final BearerTokenTwilioRestClient client) {
        return CompletableFuture.supplyAsync(() -> update(client), TwilioBearerTokenAuth.getExecutorService());
    }

    /**
     * Execute a request using default client.
     *
     * @return Requested object
     */
    public T update() {
        return update(TwilioBearerTokenAuth.getRestClient());
    }

    /**
     * Execute a request using specified client.
     *
     * @param client client used to make request
     * @return Requested object
     */
    public abstract T update(final BearerTokenTwilioRestClient client);
}
