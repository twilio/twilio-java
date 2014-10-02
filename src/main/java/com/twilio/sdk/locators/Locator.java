package com.twilio.sdk.locators;

import com.google.common.util.concurrent.ListenableFuture;
import com.twilio.sdk.Twilio;
import com.twilio.sdk.http.Request;
import com.twilio.sdk.http.Response;

import java.util.List;
import java.util.concurrent.Callable;

public abstract class Locator<T> {
    public abstract List<T> build();

    public ListenableFuture<List<T>> async() {
        return Twilio.getExecutorService().submit(new Callable<List<T>>() {
            public List<T> call() {
                return build();
            }
        });
    }

    public abstract T buildBySid(final String sid);

    public ListenableFuture<T> asyncBySid(final String sid) {
        return Twilio.getExecutorService().submit(new Callable<T>() {
            public T call() {
                return buildBySid(sid);
            }
        });
    }
}
