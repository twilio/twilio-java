package com.twilio.sdk.deleters;

import com.google.common.util.concurrent.ListenableFuture;
import com.twilio.sdk.Twilio;
import com.twilio.sdk.clients.TwilioRestClient;
import com.twilio.sdk.resources.SidResource;

public abstract class SidDeleter<T extends SidResource> extends Deleter<T> {
    public void execute(final String sid) {
        execute(sid, Twilio.getRestClient());
    }

    public abstract void execute(final String sid, final TwilioRestClient client);

    public void execute(final T target, final TwilioRestClient client) {
        execute(target.getSid(), client);
    }

    public ListenableFuture async(final String sid) {
        return async(sid, Twilio.getRestClient());
    }

    public ListenableFuture async(final String sid, final TwilioRestClient client) {
        return Twilio.getExecutorService().submit(new Runnable() {
            public void run() {
                execute(sid, client);
            }
        });
    }

    public ListenableFuture async(final T target, final TwilioRestClient client) {
        return async(target.getSid(), client);
    }
}
