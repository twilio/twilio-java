package com.twilio.base.noauth;

import com.twilio.Twilio;
import com.twilio.TwilioNoAuth;
import com.twilio.http.noauth.NoAuthTwilioRestClient;

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
        return updateAsync(TwilioNoAuth.getRestClient());
    }

    /**
     * Execute an async request using specified client.
     *
     * @param client client used to make request
     * @return future that resolves to requested object
     */
    public CompletableFuture<T> updateAsync(final NoAuthTwilioRestClient client) {
        return CompletableFuture.supplyAsync(() -> update(client), Twilio.getExecutorService());
    }

    /**
     * Execute a request using default client.
     *
     * @return Requested object
     */
    public T update() {
        return update(TwilioNoAuth.getRestClient());
    }

    /**
     * Execute a request using specified client.
     *
     * @param client client used to make request
     * @return Requested object
     */
    public abstract T update(final NoAuthTwilioRestClient client);
}
