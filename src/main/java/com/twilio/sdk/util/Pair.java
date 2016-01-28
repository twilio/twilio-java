package com.twilio.sdk.util;

public class Pair<L, R> {
    final public L left;
    final public R right;

    public Pair(final L left, final R right) {
        this.left = left;
        this.right = right;
    }

    public static <L, R> Pair<L, R> of(final L left, final R right) {
        return new Pair<>(left, right);
    }
}
