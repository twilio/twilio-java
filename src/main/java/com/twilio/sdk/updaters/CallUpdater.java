package com.twilio.sdk.updaters;

import com.twilio.sdk.clients.TwilioRestClient;
import com.twilio.sdk.http.HttpMethod;
import com.twilio.sdk.http.Request;
import com.twilio.sdk.http.Response;
import com.twilio.sdk.resources.Call;

import java.net.URI;

public class CallUpdater extends Updater<Call> {
    private URI url;
    private HttpMethod method;
    private Call.Status status;
    private URI fallbackUrl;
    private HttpMethod fallbackMethod;
    private URI statusCallback;
    private HttpMethod statusCallbackMethod;

    public CallUpdater setUrl(URI url) {
        this.url = url;
        return this;
    }

    public CallUpdater setMethod(HttpMethod method) {
        this.method = method;
        return this;
    }

    public CallUpdater setStatus(Call.Status status) {
        this.status = status;
        return this;
    }

    public CallUpdater setFallbackUrl(URI fallbackUrl) {
        this.fallbackUrl = fallbackUrl;
        return this;
    }

    public CallUpdater setFallbackMethod(HttpMethod fallbackMethod) {
        this.fallbackMethod = fallbackMethod;
        return this;
    }

    public CallUpdater setStatusCallback(URI statusCallback) {
        this.statusCallback = statusCallback;
        return this;
    }

    public CallUpdater setStatusCallbackMethod(HttpMethod statusCallbackMethod) {
        this.statusCallbackMethod = statusCallbackMethod;
        return this;
    }

    @Override
    public Call build(Call target, TwilioRestClient client) {
        Request request = new Request(HttpMethod.POST, "/Accounts/{AccountSid}/Calls/" + target.getSid());
        this.addPostParams(request);

        Response response = client.request(request);

        if (response == null) {
            throw new RuntimeException("Call update failed: Unable to connect to server");
        } else if (response.getStatusCode() != 200) {
            throw new RuntimeException("Call update failed: [" + response.getStatusCode() + "] " + response.getContent());
        }

        return Call.fromJson(response.getStream());
    }

    private void addPostParams(Request request) {
        if (this.url != null) {
            request.addPostParam("Url", this.url.toString());
        }

        if (this.method != null) {
            request.addPostParam("Method", this.method.toString());
        }

        if (this.status != null) {
            request.addPostParam("Status", this.status.toString());
        }

        if (this.fallbackUrl != null) {
            request.addPostParam("FallbackUrl", this.fallbackUrl.toString());
        }

        if (this.fallbackMethod != null) {
            request.addPostParam("FallbackMethod", this.fallbackMethod.toString());
        }

        if (this.statusCallback != null) {
            request.addPostParam("StatusCallback", this.statusCallback.toString());
        }

        if (this.statusCallbackMethod != null) {
            request.addPostParam("StatusCallbackMethod", this.statusCallbackMethod.toString());
        }
    }
}