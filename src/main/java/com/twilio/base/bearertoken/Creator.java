package com.twilio.base.bearertoken;

import com.twilio.TwilioOrgsTokenAuth;
import com.twilio.http.bearertoken.BearerTokenTwilioRestClient;

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
        return createAsync(TwilioOrgsTokenAuth.getRestClient());
    }

    /**
     * Execute an async request using specified client.
     *
     * @param client client used to make request
     * @return future that resolves to requested object
     */
    public CompletableFuture<T> createAsync(final BearerTokenTwilioRestClient client) {
        return CompletableFuture.supplyAsync(() -> create(client), TwilioOrgsTokenAuth.getExecutorService());
    }

    /**
     * Execute a request using default client.
     *
     * @return Requested object
     */
    public T create() {
        return create(TwilioOrgsTokenAuth.getRestClient());
    }

    /**
     * Execute a request using specified client.
     *
     * @param client client used to make request
     * @return Requested object
     */
    public abstract T create(final BearerTokenTwilioRestClient client);
}
