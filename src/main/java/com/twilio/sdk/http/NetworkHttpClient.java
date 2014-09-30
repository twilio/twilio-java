package com.twilio.sdk.http;

public class NetworkHttpClient extends HttpClient{
    @Override
    public Response makeRequest(Request request) {


        return new Response("", 500);
    }
}
