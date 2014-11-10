package com.twilio.sdk.bulk;

import com.twilio.sdk.creators.CallCreator;
import com.twilio.sdk.exceptions.AuthenticationException;
import com.twilio.sdk.resources.Call;

public interface BulkDialer extends Iterable<Call> {
    public void add(String key, CallCreator callCreator) throws AuthenticationException;

    public Call get(String key) throws AuthenticationException;

    public void complete() throws AuthenticationException;
}
