package com.twilio.sdk.factories;

import com.twilio.sdk.http.Request;
import com.twilio.sdk.http.Response;

import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.Future;

public abstract class Locator<T> {
    protected Factory factory;

    public Locator(Factory factory) {
        this.factory = factory;
    }

    public abstract List<T> go();

    public Future<List<T>> async() {
        return this.factory.getExecutor().submit(new Callable<List<T>>() {
            public List<T> call() {
                return go();
            }
        });
    }

    protected Response makeRequest(Request request) {
        return this.factory.makeRequest(request);
    }
}
