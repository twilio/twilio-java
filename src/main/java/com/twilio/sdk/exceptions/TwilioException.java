package com.twilio.sdk.exceptions;

public abstract class TwilioException extends Exception {

    private static final long serialVersionUID = 2516935680980388130L;

    public TwilioException(final String message) {
        super(message);
    }

    public TwilioException(final String message, final Throwable cause) {
        super(message, cause);
    }
}
