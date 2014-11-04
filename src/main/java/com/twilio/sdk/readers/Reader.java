package com.twilio.sdk.readers;

import com.google.common.util.concurrent.ListenableFuture;
import com.twilio.sdk.Twilio;
import com.twilio.sdk.clients.TwilioRestClient;
import com.twilio.sdk.resources.Page;
import com.twilio.sdk.resources.Resource;
import com.twilio.sdk.resources.Result;

import java.util.concurrent.Callable;

public abstract class Reader<T extends Resource> {
    public Result<T> execute() {
        return execute(Twilio.getRestClient());
    }

    public abstract Result<T> execute(final TwilioRestClient client);

    public ListenableFuture<Result<T>> async() {
        return async(Twilio.getRestClient());
    }

    public ListenableFuture<Result<T>> async(final TwilioRestClient client) {
        return Twilio.getExecutorService().submit(new Callable<Result<T>>() {
            public Result<T> call() {
                return execute(client);
            }
        });
    }

    public Page<T> nextPage(final String nextPageUri) {
        return nextPage(nextPageUri, Twilio.getRestClient());
    }

    public abstract Page<T> nextPage(final String nextPageUri, final TwilioRestClient client);
}
