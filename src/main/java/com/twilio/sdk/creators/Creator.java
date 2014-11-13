package com.twilio.sdk.creators;

import com.google.common.util.concurrent.ListenableFuture;
import com.twilio.sdk.Twilio;
import com.twilio.sdk.clients.TwilioRestClient;
import com.twilio.sdk.exceptions.ApiConnectionException;
import com.twilio.sdk.exceptions.ApiException;
import com.twilio.sdk.exceptions.AuthenticationException;
import com.twilio.sdk.exceptions.InvalidRequestException;
import com.twilio.sdk.resources.Resource;

import java.util.concurrent.Callable;

public abstract class Creator<T extends Resource> {
    public T execute() {
        return execute(Twilio.getRestClient());
    }

    public abstract T execute(final TwilioRestClient client);

    public ListenableFuture<T> async() {
        return async(Twilio.getRestClient());
    }

    public ListenableFuture<T> async(final TwilioRestClient client) {
        return Twilio.getExecutorService().submit(new Callable<T>() {
            public T call() {
                return execute(client);
            }
        });
    }
}
