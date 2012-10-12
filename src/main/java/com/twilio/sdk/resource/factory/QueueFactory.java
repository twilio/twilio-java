package com.twilio.sdk.resource.factory;

import java.util.Map;

import com.twilio.sdk.TwilioRestException;
import com.twilio.sdk.resource.instance.Queue;

/**
 * The {@link QueueFactory} creates {@link Queue} instances, passing in a map of properties
 * 
 * @author Christer Fahlgren
 * 
 */
public interface QueueFactory {

    public Queue create(Map<String, String> params) throws TwilioRestException;
}
