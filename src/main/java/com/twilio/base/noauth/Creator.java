package com.twilio.base.noauth;

import com.twilio.Twilio;
import com.twilio.TwilioNoAuth;
import com.twilio.base.Resource;
import com.twilio.http.noauth.NoAuthTwilioRestClient;

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
        return createAsync(TwilioNoAuth.getRestClient());
    }

    /**
     * Execute an async request using specified client.
     *
     * @param client client used to make request
     * @return future that resolves to requested object
     */
    public CompletableFuture<T> createAsync(final NoAuthTwilioRestClient client) {
        return CompletableFuture.supplyAsync(() -> create(client), Twilio.getExecutorService());
    }

    /**
     * Execute a request using default client.
     *
     * @return Requested object
     */
    public T create() {
        return create(TwilioNoAuth.getRestClient());
    }

    /**
     * Execute a request using specified client.
     *
     * @param client client used to make request
     * @return Requested object
     */
    public abstract T create(final NoAuthTwilioRestClient client);
}
