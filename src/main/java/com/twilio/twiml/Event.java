package com.twilio.twiml;

import com.google.common.base.Function;

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

    public static final Function<Event, String> TO_STRING = new Function<Event, String>() {
        @Override
        public String apply(Event event) {
            return event.toString();
        }
    };
}
