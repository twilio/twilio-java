package com.twilio.sdk.twiml;

public enum Event {
    INITIATED("initiated"),
    RINGING("ringing"),
    ANSWERED("answered"),
    COMPLETED("completed");

    private final String value;

    Event(String value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return this.value;
    }

    //public static class Serializer
}
