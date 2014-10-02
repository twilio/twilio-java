package com.twilio.sdk.creators;

import com.google.common.util.concurrent.ListenableFuture;
import com.twilio.sdk.Twilio;

import java.util.concurrent.Callable;

public abstract class Creator<T> {
    public abstract T build();

    public ListenableFuture<T> async() {
        return Twilio.getExecutorService().submit(new Callable<T>() {
            public T call() {
                return build();
            }
        });
    }
}
