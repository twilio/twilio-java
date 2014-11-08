package com.twilio.sdk.deleters;

import com.google.common.util.concurrent.ListenableFuture;
import com.twilio.sdk.Twilio;
import com.twilio.sdk.clients.TwilioRestClient;
import com.twilio.sdk.exceptions.ApiConnectionException;
import com.twilio.sdk.exceptions.ApiException;
import com.twilio.sdk.exceptions.InvalidRequestException;
import com.twilio.sdk.resources.Resource;

import java.util.concurrent.Callable;

public abstract class Deleter<T extends Resource> {

    public void execute() throws InvalidRequestException, ApiConnectionException, ApiException {
        execute(Twilio.getRestClient());
    }

    public abstract void execute(final TwilioRestClient client) throws InvalidRequestException, ApiConnectionException,
                                                                       ApiException;

    public ListenableFuture async() {
        return async(Twilio.getRestClient());
    }

    public ListenableFuture async(final TwilioRestClient client) {
        return Twilio.getExecutorService().submit(new Callable() {
            public Object call() throws InvalidRequestException, ApiConnectionException, ApiException {
                execute(client);
                return null;
            }
        });
    }

}
