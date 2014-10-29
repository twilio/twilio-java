package com.twilio.sdk.locators;

import com.google.common.util.concurrent.ListenableFuture;
import com.twilio.sdk.Twilio;
import com.twilio.sdk.clients.TwilioRestClient;
import com.twilio.sdk.resources.Result;

import java.util.concurrent.Callable;

public abstract class Locator<T> {
    public Result<T> build() {
        return build(Twilio.getRestClient());
    }

    public abstract Result<T> build(final TwilioRestClient client);

    public ListenableFuture<Result<T>> async() {
        return async(Twilio.getRestClient());
    }

    public ListenableFuture<Result<T>> async(final TwilioRestClient client) {
        return Twilio.getExecutorService().submit(new Callable<Result<T>>() {
            public Result<T> call() {
                return build(client);
            }
        });
    }

    public T buildBySid(final String sid) {
        return buildBySid(sid, Twilio.getRestClient());
    }

    public abstract T buildBySid(final String sid, final TwilioRestClient client);

    public ListenableFuture<T> asyncBySid(final String sid) {
        return asyncBySid(sid, Twilio.getRestClient());
    }

    public ListenableFuture<T> asyncBySid(final String sid, final TwilioRestClient client) {
        return Twilio.getExecutorService().submit(new Callable<T>() {
            public T call() {
                return buildBySid(sid, client);
            }
        });
    }
}
