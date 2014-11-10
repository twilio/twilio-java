package com.twilio.sdk.deleters;

import com.google.common.util.concurrent.ListenableFuture;
import com.twilio.sdk.Twilio;
import com.twilio.sdk.clients.TwilioRestClient;
import com.twilio.sdk.exceptions.ApiConnectionException;
import com.twilio.sdk.exceptions.ApiException;
import com.twilio.sdk.exceptions.AuthenticationException;
import com.twilio.sdk.exceptions.InvalidRequestException;
import com.twilio.sdk.resources.Resource;

import java.util.concurrent.Callable;

public abstract class Deleter<T extends Resource> {

    public void execute() throws InvalidRequestException, ApiConnectionException, ApiException,
                                 AuthenticationException {
        execute(Twilio.getRestClient());
    }

    public abstract void execute(final TwilioRestClient client) throws ApiConnectionException, ApiException,
                                                                       InvalidRequestException;

    public ListenableFuture async() throws AuthenticationException {
        return async(Twilio.getRestClient());
    }

    public ListenableFuture async(final TwilioRestClient client) {
        return Twilio.getExecutorService().submit(new Callable() {
            public Object call() throws ApiConnectionException, ApiException, InvalidRequestException {
                execute(client);
                return null;
            }
        });
    }

}
