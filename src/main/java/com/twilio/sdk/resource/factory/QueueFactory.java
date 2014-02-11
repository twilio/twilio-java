package com.twilio.sdk.resource.factory;

import com.twilio.sdk.TwilioRestException;
import com.twilio.sdk.resource.instance.Queue;
import org.apache.http.NameValuePair;

import java.util.List;
import java.util.Map;

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
