package com.twilio.base;

import com.google.common.base.Function;
import com.google.common.util.concurrent.Futures;
import com.google.common.util.concurrent.ListenableFuture;
import com.twilio.http.Request;
import com.twilio.http.Response;
import com.twilio.http.TwilioRestClient;

/**
 * Fetcher with real async capabilities. By separating the request
 * generation and the response handling in two methods we no longer
 * have to block a thread while waiting for the response.
 * <br>
 * This would be a drop-in replacement of the existing fetcher.
 *
 * @param <T> type of the resource
 */
public abstract class FetcherAsyncCapable<T extends Resource> extends Fetcher<T> {

    /**
     * {@inheritDoc}
     */
    @Override
    public final ListenableFuture<T> fetchAsync(final TwilioRestClient client) {
        final Request request = buildRequest(client);
        final ListenableFuture<Response> future = client.requestAsync(request);
        return Futures.transform(future, new Function<Response, T>() {
            @Override
            public T apply(Response response) {
            return FetcherAsyncCapable.this.parseResponse(response, client);
            }
        });
    }

    /**
     * {@inheritDoc}
     */
    @Override
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
