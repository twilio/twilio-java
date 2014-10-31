package com.twilio.sdk.updaters;

import com.twilio.sdk.clients.TwilioRestClient;
import com.twilio.sdk.http.Request;
import com.twilio.sdk.http.Response;
import com.twilio.sdk.resources.Call;

public class CallUpdater extends SidUpdater<Call> {
    private String uri;
    private String method;
    private String status;
    private String fallbackUri;
    private String fallbackMethod;
    private String statusCallback;
    private String statusCallbackMethod;

    public CallUpdater setUri(String uri) {
        this.uri = uri;
        return this;
    }

    public CallUpdater setMethod(String method) {
        this.method = method;
        return this;
    }

    public CallUpdater setStatus(String status) {
        this.status = status;
        return this;
    }

    public CallUpdater setFallbackUri(String fallbackUri) {
        this.fallbackUri = fallbackUri;
        return this;
    }

    public CallUpdater setFallbackMethod(String fallbackMethod) {
        this.fallbackMethod = fallbackMethod;
        return this;
    }

    public CallUpdater setStatusCallback(String statusCallback) {
        this.statusCallback = statusCallback;
        return this;
    }

    public CallUpdater setStatusCallbackMethod(String statusCallbackMethod) {
        this.statusCallbackMethod = statusCallbackMethod;
        return this;
    }

    @Override
    public Call build(String id, TwilioRestClient client) {
        Request request = new Request("POST", "/Accounts/{AccountSid}/Calls/" + id);
        this.addPostParams(request);
        Response response = client.request(request);

        if (response == null) {
            throw new RuntimeException("Call update failed: Unable to connect to server");
        } else if (response.getStatusCode() != 200) {
            throw new RuntimeException("Call update failed: [" + response.getStatusCode() + "] " + response.getContent());
        }

        return Call.fromJson(response.getContent());
    }

    private void addPostParams(Request request) {
        if (this.uri != null) {
            request.addPostParam("Uri", this.uri);
        }

        if (this.method != null) {
            request.addPostParam("Method", this.method);
        }

        if (this.status != null) {
            request.addPostParam("Status", this.status);
        }

        if (this.fallbackUri != null) {
            request.addPostParam("FallbackUri", this.fallbackUri);
        }

        if (this.fallbackMethod != null) {
            request.addPostParam("FallbackMethod", this.fallbackMethod);
        }

        if (this.statusCallback != null) {
            request.addPostParam("StatusCallback", this.statusCallback);
        }

        if (this.statusCallbackMethod != null) {
            request.addPostParam("StatusCallbackMethod", this.statusCallbackMethod);
        }
    }
}