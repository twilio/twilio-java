package com.twilio.sdk.exceptions;

public class ApiException extends TwilioException {

    private static final long serialVersionUID = -3228320166955630014L;

    public ApiException(final String message, final Throwable cause) {
        super(message, cause);
    }
}
