package com.twilio.sdk.factories;

import com.twilio.sdk.http.Request;
import com.twilio.sdk.http.Response;

import java.util.concurrent.Callable;
import java.util.concurrent.Future;

public abstract class Creator<T> {
    protected Factory factory;

    public Creator(Factory factory) {
        this.factory = factory;
    }

    public abstract T go();

    public Future<T> async() {
        return this.factory.getExecutor().submit(new Callable<T>() {
            public T call() {
                return go();
            }
        });
    }

    protected Response makeRequest(Request request) {
        return this.factory.makeRequest(request);
    }
}
