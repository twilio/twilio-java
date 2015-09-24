package com.twilio.sdk.resource.factory;

import java.util.List;
import java.util.Map;

import org.apache.http.NameValuePair;

import com.twilio.sdk.TwilioRestException;

public interface Factory<T> {
	
	/**
	 * Creates a task router resource
	 *
	 * @param params the params list
	 * @return a activity
	 * @throws com.twilio.sdk.TwilioRestException
	 */
    public T create(Map<String, String> params) throws TwilioRestException;
    
	/**
	 * Creates a task router resource
	 *
	 * @param params the params list
	 * @return a activity
	 * @throws TwilioRestException
	 */
    public T create(List<NameValuePair> params) throws TwilioRestException;
}
