package com.twilio.sdk.http;

import com.ning.http.client.AsyncHttpClient;
import com.ning.http.client.ListenableFuture;

import java.io.IOException;
import java.util.concurrent.ExecutionException;

public class NingHttpClient extends HttpClient {

    @Override
    public Response makeRequest(Request request) {
        if (request.getMethod() == null) {
            throw new RuntimeException("Request has no method");
        }

        AsyncHttpClient client = new AsyncHttpClient();
        AsyncHttpClient.BoundRequestBuilder builder;

        String method = request.getMethod().toString();

        if (method.equals("GET")) {
            builder = client.prepareGet(request.getUri());
        } else if (method.equals("POST")) {
            builder = client.preparePost(request.getUri());
        } else if (method.equals("DELETE")) {
            builder = client.preparePost(request.getUri());
        } else {
            throw new RuntimeException(method + " is not a supported HTTP Method");
        }

        if (request.requiresAuthentication()) {
            builder.addHeader("Authentication", authentication(request.getUsername(), request.getPassword()));
        }

        try {
            ListenableFuture<com.ning.http.client.Response> future = builder.execute();
            com.ning.http.client.Response response = future.get();
            return new Response(
                    response.getResponseBody(),
                    response.getStatusCode()
            );
        } catch (IOException e) {
            throw new RuntimeException(e.getMessage());
        } catch (InterruptedException e) {
            throw new RuntimeException(e.getMessage());
        } catch (ExecutionException e) {
            throw new RuntimeException(e.getMessage());
        }

    }

}
