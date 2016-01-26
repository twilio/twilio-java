package com.twilio.sdk.numbers;

import com.twilio.sdk.creators.api.v2010.account.CallCreator;
import com.twilio.sdk.hash.ConsistentHashRing;
import com.twilio.sdk.resources.api.v2010.account.Call;

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

    public CallCreator create(final String accountSid, final PhoneNumber to, final PhoneNumber from, final URI uri) {
        return Call.create(accountSid, to, from, uri);
    }
}
