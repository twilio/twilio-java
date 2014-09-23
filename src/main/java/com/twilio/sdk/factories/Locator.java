package com.twilio.sdk.factories;

import com.google.common.util.concurrent.ListenableFuture;
import com.twilio.sdk.http.Request;
import com.twilio.sdk.http.Response;

import java.util.List;
import java.util.concurrent.Callable;

public abstract class Locator<T> {
    protected Factory factory;

    public Locator(Factory factory) {
        this.factory = factory;
    }

    public abstract List<T> build();

    public ListenableFuture<List<T>> async() {
        return this.factory.getExecutor().submit(new Callable<List<T>>() {
            public List<T> call() {
                return build();
            }
        });
    }

    protected Response makeRequest(Request request) {
        return this.factory.makeRequest(request);
    }
}
