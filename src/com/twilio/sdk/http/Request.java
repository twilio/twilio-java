package com.twilio.sdk.http;

public class Request {
    protected Response response;

    public Request() {

    }

    public Response getResponse() {
        return response;
    }

    public void setResponse(Response response) {
        this.response = response;
    }
}
