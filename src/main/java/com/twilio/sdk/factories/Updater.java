package com.twilio.sdk.factories;

import com.google.common.util.concurrent.ListenableFuture;
import com.twilio.sdk.http.Request;
import com.twilio.sdk.http.Response;

import java.util.concurrent.*;

public abstract class Updater<T> {
    protected Factory factory;

    public Updater(Factory factory) {
        this.factory = factory;
    }

    public abstract T build(final T target);

    public ListenableFuture<T> async(final T target) {
        return this.factory.getExecutor().submit(new Callable<T>() {
           public T call() {
               return build(target);
           }
        });
    }

    protected Response makeRequest(Request request) {
        return this.factory.makeRequest(request);
    }
}
