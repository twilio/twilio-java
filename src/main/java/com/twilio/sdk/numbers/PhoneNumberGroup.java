package com.twilio.sdk.numbers;

import com.twilio.sdk.clients.TwilioRestClient;
import com.twilio.sdk.factories.CallFactory;
import com.twilio.sdk.hash.ConsistentHashRing;

import java.net.URL;

public class PhoneNumberGroup {
    protected ConsistentHashRing<String> ring;
    protected TwilioRestClient client;

    public PhoneNumberGroup(TwilioRestClient client) {
        this.client = client;
        this.ring = new ConsistentHashRing<String>();
    }

    public PhoneNumberGroup add(String number) {
        ring.add(number);
        return this;
    }

    public String get(String receiver) {
        return ring.get(receiver);
    }

    public CallFactory.CallCreator create(String to, URL url) {
        return this.client.calls.create(to, get(to), url);
    }
}
