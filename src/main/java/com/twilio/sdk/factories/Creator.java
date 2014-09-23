package com.twilio.sdk.factories;

import com.google.common.util.concurrent.ListenableFuture;
import com.twilio.sdk.http.Request;
import com.twilio.sdk.http.Response;

import java.util.concurrent.Callable;

public abstract class Creator<T> {
    protected Factory factory;

    public Creator(Factory factory) {
        this.factory = factory;
    }

    public abstract T build();

    public ListenableFuture<T> async() {
        return this.factory.getExecutor().submit(new Callable<T>() {
            public T call() {
                return build();
            }
        });
    }

    protected Response makeRequest(Request request) {
        return this.factory.makeRequest(request);
    }
}
