package com.twilio.sdk.numbers;

import com.twilio.sdk.creators.CallCreator;
import com.twilio.sdk.hash.ConsistentHashRing;
import com.twilio.sdk.resources.Call;

import java.net.URI;
import java.net.URL;

public class PhoneNumberGroup {
    protected ConsistentHashRing<String> ring;

    public PhoneNumberGroup() {
        this.ring = new ConsistentHashRing<String>();
    }

    public PhoneNumberGroup add(String number) {
        ring.add(number);
        return this;
    }

    public String get(String receiver) {
        return ring.get(receiver);
    }

    public CallCreator create(String to, URI url) {
        return Call.create(to, get(to), url);
    }
}
