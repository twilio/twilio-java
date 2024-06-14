package com.twilio.base.noauth;

import com.twilio.Twilio;

import com.twilio.TwilioNoAuth;
import com.twilio.base.noauth.Resource;
import com.twilio.http.noauth.NoAuthTwilioRestClient;

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
        return fetchAsync(TwilioNoAuth.getRestClient());
    }

    /**
     * Execute an async request using specified client.
     *
     * @param client client used to make request
     * @return future that resolves to requested object
     */
    public CompletableFuture<T> fetchAsync(final NoAuthTwilioRestClient client) {
        return CompletableFuture.supplyAsync(() -> fetch(client), Twilio.getExecutorService());
    }

    /**
     * Execute a request using default client.
     *
     * @return Requested object
     */
    public T fetch() {
        return fetch(TwilioNoAuth.getRestClient());
    }

    /**
     * Execute a request using specified client.
     *
     * @param client client used to make request
     * @return Requested object
     */
    public abstract T fetch(final NoAuthTwilioRestClient client);
}
