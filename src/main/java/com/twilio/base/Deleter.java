package com.twilio.base;

import com.google.common.util.concurrent.ListenableFuture;
import com.twilio.Twilio;
import com.twilio.http.TwilioRestClient;

import java.util.concurrent.Callable;

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
    public ListenableFuture<Boolean> deleteAsync() {
        return deleteAsync(Twilio.getRestClient());
    }

    /**
     * Execute an async request using specified client.
     *
     * @param client client used to make request
     * @return future that resolves to true if the object was deleted
     */
    public ListenableFuture<Boolean> deleteAsync(final TwilioRestClient client) {
        return Twilio.getExecutorService().submit(new Callable<Boolean>() {
            public Boolean call() {
                return delete(client);
            }
        });
    }

    /**
     * Execute a request using default client.
     *
     * @return true if the object was deleted
     */
    public boolean delete() {
        return delete(Twilio.getRestClient());
    }

    /**
     * Execute a request using specified client.
     *
     * @param client client used to make request
     * @return true if the object was deleted
     */
    public abstract boolean delete(final TwilioRestClient client);
}
