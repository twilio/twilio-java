package com.twilio.sdk.http;

public class ConsumableResponse extends Response {
    protected int lives;

    public ConsumableResponse(final String content, final int statusCode) {
        this(content, statusCode, -1);
    }

    public ConsumableResponse(final String content, final int statusCode, final int lives) {
        super(content, statusCode);
        this.lives = lives;
    }

    public void consume() {
        if (lives > 0) {
            lives--;
        }
    }

    public boolean isExhausted() {
        return lives == 0;
    }
}
