package com.twilio.sdk.http;

import com.ning.http.client.AsyncHttpClient;
import com.ning.http.client.ListenableFuture;
import com.twilio.sdk.exceptions.ApiConnectionException;
import com.twilio.sdk.exceptions.ApiException;

import java.io.IOException;
import java.util.concurrent.ExecutionException;

public class NingHttpClient extends HttpClient {

    @Override
    public Response makeRequest(final Request request) {
        if (request.getMethod() == null) {
            throw new ApiConnectionException("Request has no method");
        }

        AsyncHttpClient client = new AsyncHttpClient();
        AsyncHttpClient.BoundRequestBuilder builder;

        String method = request.getMethod().toString();

        switch (method) {
            case "GET":
                builder = client.prepareGet(request.getUri());
                break;
            case "POST":
                builder = client.preparePost(request.getUri());
                break;
            case "DELETE":
                builder = client.preparePost(request.getUri());
                break;
            default:
                throw new ApiConnectionException(method + " is not a supported HTTP Method");
        }

        if (request.requiresAuthentication()) {
            builder.addHeader("Authentication", request.getAuthString());
        }

        try {
            ListenableFuture<com.ning.http.client.Response> future = builder.execute();
            com.ning.http.client.Response response = future.get();
            return new Response(response.getResponseBody(), response.getStatusCode());
        } catch (final IOException e) {
            throw new ApiConnectionException("IOException during API request to Twilio", e);
        } catch (final InterruptedException | ExecutionException e) {
            throw new ApiException(e.getMessage(), e);
        }

    }

}
