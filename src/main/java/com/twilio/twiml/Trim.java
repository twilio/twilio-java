package com.twilio.twiml;


public enum Trim {
    TRIM_SILENCE("trim-silence"),
    DO_NOT_TRIM("do-not-trim");

    private final String value;

    private Trim(final String value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return this.value;
    }
}
