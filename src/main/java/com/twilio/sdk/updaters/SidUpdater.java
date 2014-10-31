package com.twilio.sdk.updaters;

import com.google.common.util.concurrent.ListenableFuture;
import com.twilio.sdk.Twilio;
import com.twilio.sdk.clients.TwilioRestClient;
import com.twilio.sdk.resources.SidResource;

import java.util.concurrent.Callable;

public abstract class SidUpdater<T extends SidResource> extends Updater<T> {
    public T build(final String sid) {
        return build(sid, Twilio.getRestClient());
    }

    public abstract T build(final String sid, final TwilioRestClient client);

    public T build(final T target) {
        return build(target, Twilio.getRestClient());
    }

    public T build(final T target, final TwilioRestClient client) {
        return build(target.getSid(), client);
    }

    public ListenableFuture<T> async(final String sid) {
        return async(sid, Twilio.getRestClient());
    }

    public ListenableFuture<T> async(final String sid, final TwilioRestClient client) {
        return Twilio.getExecutorService().submit(new Callable<T>() {
            public T call() {
                return build(sid, client);
            }
        });
    }

    public ListenableFuture<T> async(final T target) {
        return async(target, Twilio.getRestClient());
    }

    public ListenableFuture<T> async(final T target, final TwilioRestClient client) {
        return async(target.getSid(), client);
    }
}
