package com.twilio.sdk.numbers;

import com.twilio.sdk.hash.ConsistentHashRing;

public class PhoneNumberGroup {
    protected ConsistentHashRing<String> ring;

    public PhoneNumberGroup() {
        ring = new ConsistentHashRing<String>();
    }

    public PhoneNumberGroup add(String number) {
        ring.add(number);
        return this;
    }

    public String get(String receiver) {
        return ring.get(receiver);
    }
}
