package com.twilio.sdk.timing;

public class Stopwatch {
    private long start;
    private long stop;

    public Stopwatch() {
        start = 0L;
        stop = 0L;
    }

    public void start() {
        start = System.currentTimeMillis();
    }

    public void stop() {
        stop = System.currentTimeMillis();
    }

    public long getElapsed() {
        return stop - start;
    }

    public void debug(final String message) {
        System.out.println(message + " took " + getElapsed() + "ms");
    }
}
