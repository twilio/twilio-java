package com.twilio.sdk.http;

public class ConsumableResponse extends Response {
    protected int lives;

    public ConsumableResponse(String content, int statusCode) {
        this(content, statusCode, -1);
    }

    public ConsumableResponse(String content, int statusCode, int lives) {
        super(content, statusCode);
        this.lives = lives;
    }

    public void consume() {
        if (this.lives > 0) {
            this.lives--;
        }
    }

    public boolean isExhausted() {
        return this.lives == 0;
    }
}
