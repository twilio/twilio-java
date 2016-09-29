package com.twilio.base;

import com.google.common.util.concurrent.ListenableFuture;
import com.twilio.Twilio;
import com.twilio.http.TwilioRestClient;

import java.util.concurrent.Callable;

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
    public ListenableFuture<T> updateAsync() {
        return updateAsync(Twilio.getRestClient());
    }

    /**
     * Execute an async request using specified client.
     *
     * @param client client used to make request
     * @return future that resolves to requested object
     */
    public ListenableFuture<T> updateAsync(final TwilioRestClient client) {
        return Twilio.getExecutorService().submit(new Callable<T>() {
            public T call() {
                return update(client);
            }
        });
    }

    /**
     * Execute a request using default client.
     *
     * @return Requested object
     */
    public T update() {
        return update(Twilio.getRestClient());
    }

    /**
     * Execute a request using specified client.
     *
     * @param client client used to make request
     * @return Requested object
     */
    public abstract T update(final TwilioRestClient client);
}
