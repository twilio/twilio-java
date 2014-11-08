package com.twilio.sdk.readers;

import com.google.common.util.concurrent.ListenableFuture;
import com.twilio.sdk.Twilio;
import com.twilio.sdk.clients.TwilioRestClient;
import com.twilio.sdk.exceptions.ApiConnectionException;
import com.twilio.sdk.exceptions.ApiException;
import com.twilio.sdk.exceptions.InvalidRequestException;
import com.twilio.sdk.resources.Page;
import com.twilio.sdk.resources.Resource;
import com.twilio.sdk.resources.ResourceSet;

import java.util.concurrent.Callable;

public abstract class Reader<T extends Resource> {

    private int pageSize = 50;

    public ResourceSet<T> execute() throws InvalidRequestException, ApiConnectionException, ApiException {
        return execute(Twilio.getRestClient());
    }

    public abstract ResourceSet<T> execute(final TwilioRestClient client) throws InvalidRequestException,
                                                                                 ApiConnectionException, ApiException;

    public ListenableFuture<ResourceSet<T>> async() {
        return async(Twilio.getRestClient());
    }

    public ListenableFuture<ResourceSet<T>> async(final TwilioRestClient client) {
        return Twilio.getExecutorService().submit(new Callable<ResourceSet<T>>() {
            public ResourceSet<T> call() throws InvalidRequestException, ApiConnectionException, ApiException {
                return execute(client);
            }
        });
    }

    public Page<T> nextPage(final String nextPageUri) throws InvalidRequestException, ApiConnectionException,
                                                             ApiException {
        return nextPage(nextPageUri, Twilio.getRestClient());
    }

    public abstract Page<T> nextPage(final String nextPageUri, final TwilioRestClient client) throws
                                                                                              InvalidRequestException,
                                                                                              ApiConnectionException,
                                                                                              ApiException;

    public int getPageSize() {
        return pageSize;
    }

    public Reader<T> pageSize(final int pageSize) {
        this.pageSize = pageSize;
        return this;
    }
}
