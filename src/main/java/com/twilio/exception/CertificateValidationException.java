package com.twilio.exception;

import com.twilio.http.Request;
import com.twilio.http.Response;

public class CertificateValidationException extends TwilioException {

    private final Request request;
    private final Response response;

    public CertificateValidationException(final String message, final Request request, final Response response) {
        super(message);

        this.request = request;
        this.response = response;
    }

    public CertificateValidationException(final String message, final Request request) {
        this(message, request, null);
    }

    public Request getRequest() { return this.request; }
    public Response getResponse() { return this.response; }
}
