package com.twilio.sdk.creators;

import com.twilio.sdk.clients.TwilioRestClient;
import com.twilio.sdk.http.Request;
import com.twilio.sdk.http.Response;
import com.twilio.sdk.resources.Call;

import java.net.URL;

public class CallCreator extends Creator<Call> {
    protected String to;
    protected String from;
    protected URL url;
    protected String applicationSid;
    protected String method;
    protected String fallbackUrl;
    protected String fallbackMethod;
    protected String statusCallback;
    protected String statusCallbackMethod;
    protected String sendDigits;
    protected String ifMachine;
    protected Integer timeout;
    protected Boolean record;

    /**
     * @param to The phone number to dial
     * @param from The twilio number to originate the call from
     * @param url The url to fetch twiml from
     */
    public CallCreator(String to, String from, URL url) {
        this.to = to;
        this.from = from;
        this.url = url;
    }

    /**
     * @param to The phone number to dial
     * @param from The twilio number to originate the call from
     * @param applicationSid The ApplicationSid that configures where to fetch twiml from
     */
    public CallCreator(String to, String from, String applicationSid) {
        this.to = to;
        this.from = from;
        this.applicationSid = applicationSid;
    }

    public CallCreator setMethod(String method) {
        this.method = method;
        return this;
    }

    public CallCreator setFallbackUrl(String fallbackUrl) {
        this.fallbackUrl = fallbackUrl;
        return this;
    }

    public CallCreator setFallbackMethod(String fallbackMethod) {
        this.fallbackMethod = fallbackMethod;
        return this;
    }

    public CallCreator setStatusCallback(String statusCallback) {
        this.statusCallback = statusCallback;
        return this;
    }

    public CallCreator setStatusCallbackMethod(String statusCallbackMethod) {
        this.statusCallbackMethod = statusCallbackMethod;
        return this;
    }

    public CallCreator setSendDigits(String sendDigits) {
        this.sendDigits = sendDigits;
        return this;
    }

    /**
     * Tell Twilio to try and determine if a machine (like voicemail) or a human
     * has answered the call. Possible values are Continue and Hangup. See the
     * answering machines section below for more info.
     * @param ifMachine Action to take if a machine has answered the call, valid
     *                  values are "Continue" and "Hangup"
     * @return Instance for fluent API
     */
    public CallCreator setIfMachine(String ifMachine) {
        this.ifMachine = ifMachine;
        return this;
    }

    public CallCreator setTimeout(int timeout) {
        this.timeout = timeout;
        return this;
    }

    public CallCreator setRecord(boolean record) {
        this.record = record;
        return this;
    }

    @Override
    public Call build(final TwilioRestClient client) {
        Request request = new Request("POST", "/Accounts/{AccountSid}/Calls");
        Response response = client.request(request);

        if (response == null) {
            throw new RuntimeException("Call creation failed: Unable to connect to server");
        } else if (response.getStatusCode() != 201) {
            throw new RuntimeException("Call creation failed: [" + response.getStatusCode() + "] " + response.getContent());
        }

        return Call.fromJson(response.getStream());
    }
}