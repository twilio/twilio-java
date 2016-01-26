package com.twilio.sdk.numbers;

import com.twilio.sdk.creators.api.CallCreator;
import com.twilio.sdk.hash.ConsistentHashRing;
import com.twilio.sdk.resources.api.Call;

import java.net.URI;

public class PhoneNumberGroup {

    protected ConsistentHashRing<String> ring;

    public PhoneNumberGroup() {
        ring = new ConsistentHashRing<>();
    }

    public PhoneNumberGroup add(final String number) {
        ring.add(number);
        return this;
    }

    public String get(final String receiver) {
        return ring.get(receiver);
    }

    public CallCreator create(final String to, final URI uri) {
        return Call.create(to, get(to), uri);
    }
}
