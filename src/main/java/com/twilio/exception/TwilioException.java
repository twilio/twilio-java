package com.twilio.exception;

public abstract class TwilioException extends RuntimeException {

    private static final long serialVersionUID = 2516935680980388130L;

    public TwilioException(final String message) {
        this(message, null);
    }

    public TwilioException(final String message, final Throwable cause) {
        super(message, cause);
    }
}
