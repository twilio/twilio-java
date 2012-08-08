package com.twilio.sdk.resource.factory;

import java.util.Map;

import com.twilio.sdk.TwilioRestException;
import com.twilio.sdk.resource.instance.Queue;

public interface QueueFactory {

    public Queue create(Map<String, String> params) throws TwilioRestException;
}
