package com.twilio.sdk.deleters;

import com.google.common.util.concurrent.ListenableFuture;
import com.twilio.sdk.Twilio;
import com.twilio.sdk.clients.TwilioRestClient;
import com.twilio.sdk.resources.Resource;

public abstract class Deleter<T extends Resource> {
    public void execute(final T target) {
        execute(target, Twilio.getRestClient());
    }

    public abstract void execute(final T target, final TwilioRestClient client);

    public ListenableFuture async(final T target) {
        return async(target, Twilio.getRestClient());
    }

    public ListenableFuture async(final T target, final TwilioRestClient client) {
        return Twilio.getExecutorService().submit(new Runnable() {
            public void run() {
                execute(target, client);
            }
        });
    }


}
