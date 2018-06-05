package com.twilio.http;

import com.google.common.base.Preconditions;
import com.google.common.util.concurrent.ListenableFuture;
import com.twilio.Twilio;

import java.util.concurrent.Callable;

/**
 * Simple "blocking" implementation of the asyncronous HTTP client.
 */
public class ExecutorAsyncHttpClient implements AsyncHttpClient {

    private final HttpClient httpClient;

    public ExecutorAsyncHttpClient(final HttpClient httpClient) {
        Preconditions.checkNotNull(httpClient, "HTTP client is required");
        this.httpClient = httpClient;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ListenableFuture<Response> reliableRequest(final Request request) {
        return Twilio.getExecutorService().submit(new WaitForResponse(httpClient, request));
    }

    private static class WaitForResponse implements Callable<Response> {

        private final HttpClient httpClient;
        private final Request request;

        private WaitForResponse(HttpClient httpClient, Request request) {
            this.httpClient = httpClient;
            this.request = request;
        }

        /**
         * {@inheritDoc}
         */
        @Override
        public Response call() {
            return httpClient.reliableRequest(request);
        }

    }

}
