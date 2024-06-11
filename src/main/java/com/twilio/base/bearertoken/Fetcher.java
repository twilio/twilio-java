package com.twilio.base.bearertoken;

import com.twilio.TwilioBearerTokenAuth;
import com.twilio.http.bearertoken.BearerTokenTwilioRestClient;

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
    public CompletableFuture<T> fetchAsync() {
        return fetchAsync(TwilioBearerTokenAuth.getRestClient());
    }

    /**
     * Execute an async request using specified client.
     *
     * @param client client used to make request
     * @return future that resolves to requested object
     */
    public CompletableFuture<T> fetchAsync(final BearerTokenTwilioRestClient client) {
        return CompletableFuture.supplyAsync(() -> fetch(client), TwilioBearerTokenAuth.getExecutorService());
    }

    /**
     * Execute a request using default client.
     *
     * @return Requested object
     */
    public T fetch() {
        return fetch(TwilioBearerTokenAuth.getRestClient());
    }

    /**
     * Execute a request using specified client.
     *
     * @param client client used to make request
     * @return Requested object
     */
    public abstract T fetch(final BearerTokenTwilioRestClient client);
}
