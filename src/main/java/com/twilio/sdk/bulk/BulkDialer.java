package com.twilio.sdk.bulk;

import com.twilio.sdk.factories.CallFactory;
import com.twilio.sdk.resources.Call;

public interface BulkDialer extends Iterable<Call> {
    public void add(String key, CallFactory.CallCreator callCreator);
    public Call get(String key);
    public void complete();
}