package com.twilio.sdk.clients;

import com.twilio.sdk.http.*;

public class TwilioRestClient {

    protected HttpClient httpClient;
    protected String accountSid;
    protected String authToken;

    public TwilioRestClient(String accountSid, String authToken) {
        this(accountSid, authToken, new NetworkHttpClient());
    }

    public TwilioRestClient(String accountSid, String authToken, HttpClient httpClient) {
        this.accountSid = accountSid;
        this.authToken = authToken;
        this.httpClient = httpClient;
    }

    public Response request(Request request) {
        String resolvedUri = "https://api.twilio.com/2010-04-01"
                           + request.getUri().replace("{AccountSid}", this.accountSid)
		                   + ".json"; // XXX is this the right place to do this?
	                                  // why the hell don't we just do accept headers :(
        request.setUri(resolvedUri);
        request.setAuth(this.accountSid, this.authToken);

        return this.httpClient.reliableRequest(request);
    }

    public HttpClient getHttpClient() {
        return httpClient;
    }

    public void setHttpClient(HttpClient httpClient) {
        this.httpClient = httpClient;
    }
}
