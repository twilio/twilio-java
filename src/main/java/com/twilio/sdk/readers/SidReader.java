package com.twilio.sdk.readers;

import com.google.common.util.concurrent.ListenableFuture;
import com.twilio.sdk.Twilio;
import com.twilio.sdk.clients.TwilioRestClient;
import com.twilio.sdk.resources.SidResource;

import java.util.concurrent.Callable;

public abstract class SidReader<T extends SidResource> extends Reader<T> {
    public T fetch(final String sid) {
        return fetch(sid, Twilio.getRestClient());
    }

    public abstract T fetch(final String sid, final TwilioRestClient client);

    public ListenableFuture<T> fetchAsync(final String sid) {
        return fetchAsync(sid, Twilio.getRestClient());
    }

    public ListenableFuture<T> fetchAsync(final String sid, final TwilioRestClient client) {
        return Twilio.getExecutorService().submit(new Callable<T>() {
            public T call() {
                return fetch(sid, client);
            }
        });
    }
}
