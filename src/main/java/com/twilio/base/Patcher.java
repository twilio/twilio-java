package com.twilio.base;

import com.twilio.Twilio;
import com.twilio.http.TwilioRestClient;

import java.util.concurrent.CompletableFuture;

/**
 * Executor for updates of a resource.
 *
 * @param <T> type of the resource
 */
public abstract class Patcher<T extends Resource> {

    /**
     * Execute an async request using default client.
     *
     * @return future that resolves to requested object
     */
    public CompletableFuture<T> patchAsync() {
        return patchAsync(Twilio.getRestClient());
    }

    /**
     * Execute an async request using specified client.
     *
     * @param client client used to make request
     * @return future that resolves to requested object
     */
    public CompletableFuture<T> patchAsync(final TwilioRestClient client) {
        return CompletableFuture.supplyAsync(() -> patch(client), Twilio.getExecutorService());
    }

    /**
     * Execute a request using default client.
     *
     * @return Requested object
     */
    public T patch() {
        return patch(Twilio.getRestClient());
    }

    /**
     * Execute a request using specified client.
     *
     * @param client client used to make request
     * @return Requested object
     */
    public abstract T patch(final TwilioRestClient client);

    public TwilioResponse<T> patchWithResponse() {
        return patchWithResponse(Twilio.getRestClient());
    }
    public TwilioResponse<T> patchWithResponse(final TwilioRestClient client) {
        throw new UnsupportedOperationException("patchWithResponse is not supported for this resource.");
    }
}
