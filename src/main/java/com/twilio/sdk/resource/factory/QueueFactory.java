package com.twilio.sdk.resource.factory;

import java.util.Map;
import java.util.List;

import com.twilio.sdk.TwilioRestException;
import com.twilio.sdk.resource.instance.Queue;
import org.apache.http.NameValuePair;

/**
 * The {@link QueueFactory} creates {@link Queue} instances, passing in a map of properties
 *
 * @author Christer Fahlgren
 *
 */
public interface QueueFactory {

    public Queue create(Map<String, String> params) throws TwilioRestException;

    public Queue create(List<NameValuePair> params) throws TwilioRestException;
}
