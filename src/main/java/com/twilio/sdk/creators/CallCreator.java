package com.twilio.sdk.creators;

import com.google.common.base.CaseFormat;
import com.twilio.sdk.clients.TwilioRestClient;
import com.twilio.sdk.exceptions.ApiConnectionException;
import com.twilio.sdk.exceptions.ApiException;
import com.twilio.sdk.http.HttpMethod;
import com.twilio.sdk.http.Request;
import com.twilio.sdk.http.Response;
import com.twilio.sdk.resources.Call;

import java.net.URI;

public class CallCreator extends Creator<Call> {
    private final String to;
    private final String from;
    private URI url;
    private String applicationSid;
    private HttpMethod method;
    private String fallbackUrl;
    private HttpMethod fallbackMethod;
    private String statusCallback;
    private HttpMethod statusCallbackMethod;
    private String sendDigits;
    private String ifMachine;
    private Integer timeout;
    private Boolean record;

    /**
     * @param to The phone number to dial
     * @param from The twilio number to originate the call from
     * @param url The url to fetch twiml from
     */
    public CallCreator(final String to, final String from, final URI url) {
        this.to = to;
        this.from = from;
        this.url = url;
    }

    /**
     * @param to The phone number to dial
     * @param from The twilio number to originate the call from
     * @param applicationSid The ApplicationSid that configures where to fetch twiml from
     */
    public CallCreator(final String to, final String from, final String applicationSid) {
        this.to = to;
        this.from = from;
        this.applicationSid = applicationSid;
    }

    public CallCreator setMethod(final HttpMethod method) {
        this.method = method;
        return this;
    }

    public CallCreator setFallbackUrl(final String fallbackUrl) {
        this.fallbackUrl = fallbackUrl;
        return this;
    }

    public CallCreator setFallbackMethod(final HttpMethod fallbackMethod) {
        this.fallbackMethod = fallbackMethod;
        return this;
    }

    public CallCreator setStatusCallback(final String statusCallback) {
        this.statusCallback = statusCallback;
        return this;
    }

    public CallCreator setStatusCallbackMethod(final HttpMethod statusCallbackMethod) {
        this.statusCallbackMethod = statusCallbackMethod;
        return this;
    }

    public CallCreator setSendDigits(final String sendDigits) {
        this.sendDigits = sendDigits;
        return this;
    }

    /**
     * Tell Twilio to try and determine if a machine (like voicemail) or a human has answered the call. Possible values
     * are Continue and Hangup. See the answering machines section below for more info.
     *
     * @param ifMachine Action to take if a machine has answered the call, valid values are "Continue" and "Hangup"
     * @return Instance for fluent API
     */
    public CallCreator setIfMachine(final String ifMachine) {
        this.ifMachine = ifMachine;
        return this;
    }

    public CallCreator setTimeout(final int timeout) {
        this.timeout = timeout;
        return this;
    }

    public CallCreator setRecord(final boolean record) {
        this.record = record;
        return this;
    }

    @Override
    public Call execute(final TwilioRestClient client) {
        Request request = new Request(HttpMethod.POST, "/Accounts/{AccountSid}/Calls.json");
        addPostParams(request);
        Response response = client.request(request);

        if (response == null) {
            throw new ApiConnectionException("Call creation failed: Unable to connect to server");
        } else if (response.getStatusCode() != 201) {
            throw new ApiException("Call creation failed: [" + response.getStatusCode() + "] " + response.getContent(),
                                   null);
        }

        return Call.fromJson(response.getStream(), client.getObjectMapper());
    }

    private void addPostParams(final Request request) {
        if (to != null) {
            request.addPostParam("To", to);
        }

        if (from != null) {
            request.addPostParam("From", from);
        }

        if (url != null) {
            request.addPostParam("Url", url.toString());
        }

        if (applicationSid != null) {
            request.addPostParam("ApplicationSid", applicationSid);
        }

        if (method != null) {
            request.addPostParam("Method", method.toString());
        }

        if (fallbackUrl != null) {
            request.addPostParam("FallbackUrl", fallbackUrl);
        }

        if (fallbackMethod != null) {
            request.addPostParam("FallbackMethod", fallbackMethod.toString());
        }

        if (statusCallback != null) {
            request.addPostParam("StatusCallback", statusCallback);
        }

        if (statusCallbackMethod != null) {
            request.addPostParam("StatusCallbackMethod", statusCallbackMethod.toString());
        }

        if (sendDigits != null) {
            request.addPostParam("SendDigits", sendDigits);
        }

        if (ifMachine != null) {
            request.addPostParam("IfMachine", ifMachine);
        }

        if (timeout != null) {
            request.addPostParam("Timeout", timeout.toString());
        }

        if (record != null) {
            String value = CaseFormat.LOWER_CAMEL.to(CaseFormat.UPPER_CAMEL, record.toString());
            request.addPostParam("Record", value);
        }
    }

}
