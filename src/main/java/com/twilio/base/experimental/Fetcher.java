package com.twilio.base.experimental;

import com.google.common.base.Function;
import com.google.common.util.concurrent.Futures;
import com.google.common.util.concurrent.ListenableFuture;
import com.twilio.Twilio;
import com.twilio.base.Resource;
import com.twilio.http.Request;
import com.twilio.http.Response;
import com.twilio.http.TwilioRestClient;

/**
 * Executor for fetches of a resource.
 * <p/>
 * Experimental version with real asynchronous support.
 *
 * @param <T> type of the resource
 */
public abstract class Fetcher<T extends Resource> {

    /**
     * Execute an async request using default client.
     *
     * @return future that resolves to requested object
     */
    public final ListenableFuture<T> fetchAsync() {
        return fetchAsync(Twilio.getRestClient());
    }

    /**
     * Execute an async request using specified client.
     *
     * @param client client used to make request
     * @return future that resolves to requested object
     */
    public final ListenableFuture<T> fetchAsync(final TwilioRestClient client) {
        final Request request = buildRequest(client);
        final ListenableFuture<Response> future = client.requestAsync(request);
        return Futures.transform(future, new Function<Response, T>() {
            @Override
            public T apply(Response response) {
                return Fetcher.this.parseResponse(response, client);
            }
        });
    }

    /**
     * Execute a request using default client.
     *
     * @return Requested object
     */
    public final T fetch() {
        return fetch(Twilio.getRestClient());
    }

    /**
     * Execute a request using specified client.
     *
     * @param client client used to make request
     * @return Requested object
     */
    public final T fetch(final TwilioRestClient client) {
        final Request request = buildRequest(client);
        final Response response = client.request(request);
        return parseResponse(response, client);
    }

    /**
     * Build a request based on the current state.
     *
     * @param client client used to make request
     * @return the request
     */
    protected abstract Request buildRequest(final TwilioRestClient client);

    /**
     * Parse the response into the resource entity.
     *
     * @param response the response
     * @param client client used to make request
     * @return the resource entity
     */
    protected abstract T parseResponse(final Response response, final TwilioRestClient client);

}
