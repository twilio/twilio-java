package com.twilio.sdk.updaters;

import com.google.common.util.concurrent.ListenableFuture;
import com.twilio.sdk.Twilio;

import java.util.concurrent.Callable;

public abstract class Updater<T> {
    public abstract T build(final T target);

    public ListenableFuture<T> async(final T target) {
        return Twilio.getExecutorService().submit(new Callable<T>() {
           public T call() {
               return build(target);
           }
        });
    }
}
