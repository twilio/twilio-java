package com.twilio.sdk.timing;

public class Stopwatch {
    private long start;
    private long stop;

    public Stopwatch() {
        this.start = 0L;
        this.stop = 0L;
    }

    public void start() {
        this.start = System.currentTimeMillis();
    }

    public void stop() {
        this.stop = System.currentTimeMillis();
    }

    public long getElapsed() {
        return this.stop - this.start;
    }

    public void debug(String message) {
        System.out.println(message + " took " + this.getElapsed() + "ms");
    }
}
