package com.twilio.sdk.updaters;

import com.twilio.sdk.clients.TwilioRestClient;
import com.twilio.sdk.exceptions.ApiConnectionException;
import com.twilio.sdk.exceptions.ApiException;
import com.twilio.sdk.exceptions.InvalidRequestException;
import com.twilio.sdk.http.HttpMethod;
import com.twilio.sdk.http.Request;
import com.twilio.sdk.http.Response;
import com.twilio.sdk.resources.Call;

import java.net.URI;

public class CallUpdater extends Updater<Call> {

    private final String sid;
    private URI url;
    private HttpMethod method;
    private Call.Status status;
    private URI fallbackUrl;
    private HttpMethod fallbackMethod;
    private URI statusCallback;
    private HttpMethod statusCallbackMethod;

    public CallUpdater(final String sid) {
        this.sid = sid;
    }

    public CallUpdater(final Call call) {
        this(call.getSid());
    }

    public CallUpdater setUrl(final URI url) {
        this.url = url;
        return this;
    }

    public CallUpdater setMethod(final HttpMethod method) {
        this.method = method;
        return this;
    }

    public CallUpdater setStatus(final Call.Status status) {
        this.status = status;
        return this;
    }

    public CallUpdater setFallbackUrl(final URI fallbackUrl) {
        this.fallbackUrl = fallbackUrl;
        return this;
    }

    public CallUpdater setFallbackMethod(final HttpMethod fallbackMethod) {
        this.fallbackMethod = fallbackMethod;
        return this;
    }

    public CallUpdater setStatusCallback(final URI statusCallback) {
        this.statusCallback = statusCallback;
        return this;
    }

    public CallUpdater setStatusCallbackMethod(final HttpMethod statusCallbackMethod) {
        this.statusCallbackMethod = statusCallbackMethod;
        return this;
    }

    @Override
    public Call execute(final TwilioRestClient client) throws InvalidRequestException, ApiConnectionException,
                                                              ApiException {
        Request request = new Request(HttpMethod.POST, "/Accounts/{AccountSid}/Calls/" + sid + ".json");
        addPostParams(request);
        Response response = client.request(request);

        if (response == null) {
            throw new RuntimeException("Call update failed: Unable to connect to server");
        } else if (response.getStatusCode() != 200) {
            throw new RuntimeException(
                    "Call update failed: [" + response.getStatusCode() + "] " + response.getContent());
        }

        return Call.fromJson(response.getStream());
    }

    private void addPostParams(final Request request) {
        if (url != null) {
            request.addPostParam("Url", url.toString());
        }

        if (method != null) {
            request.addPostParam("Method", method.toString());
        }

        if (status != null) {
            request.addPostParam("Status", status.toString());
        }

        if (fallbackUrl != null) {
            request.addPostParam("FallbackUrl", fallbackUrl.toString());
        }

        if (fallbackMethod != null) {
            request.addPostParam("FallbackMethod", fallbackMethod.toString());
        }

        if (statusCallback != null) {
            request.addPostParam("StatusCallback", statusCallback.toString());
        }

        if (statusCallbackMethod != null) {
            request.addPostParam("StatusCallbackMethod", statusCallbackMethod.toString());
        }
    }
}
