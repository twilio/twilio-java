package com.twilio.sdk.deleter;

import com.google.common.util.concurrent.ListenableFuture;
import com.twilio.sdk.Twilio;
import com.twilio.sdk.http.TwilioRestClient;
import com.twilio.sdk.resource.Resource;

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
    public ListenableFuture<Boolean> async() {
        return async(Twilio.getRestClient());
    }

    /**
     * Execute an async request using specified client.
     *
     * @param client client used to make request
     * @return future that resolves to true if the object was deleted
     */
    public ListenableFuture<Boolean> async(final TwilioRestClient client) {
        return Twilio.getExecutorService().submit(new Callable<Boolean>() {
            public Boolean call() {
                return execute(client);
            }
        });
    }

    /**
     * Execute a request using default client.
     *
     * @return true if the object was deleted
     */
    public boolean execute() {
        return execute(Twilio.getRestClient());
    }

    /**
     * Execute a request using specified client.
     *
     * @param client client used to make request
     * @return true if the object was deleted
     */
    public abstract boolean execute(final TwilioRestClient client);
}
