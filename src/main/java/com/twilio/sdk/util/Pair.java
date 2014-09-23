package com.twilio.sdk.util;

public class Pair<L,R> {
    final public L left;
    final public R right;

    public Pair(L left, R right) {
        this.left = left;
        this.right = right;
    }

    public static <L,R> Pair<L,R> of(L left, R right){
        return new Pair<L,R>(left, right);
    }
}
