package com.twilio.sdk.bulk;

import com.twilio.sdk.creators.CallCreator;
import com.twilio.sdk.resources.Call;

public interface BulkDialer extends Iterable<Call> {
    public void add(String key, CallCreator callCreator);

    public Call get(String key);

    public void complete();
}
