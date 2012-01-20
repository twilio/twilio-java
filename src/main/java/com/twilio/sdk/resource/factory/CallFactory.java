package com.twilio.sdk.resource.factory;

import java.util.Map;

import com.twilio.sdk.TwilioRestException;
import com.twilio.sdk.resource.instance.Call;

// TODO: Auto-generated Javadoc
/**
 * A factory for creating Call objects.
 */
public interface CallFactory {
	
	/**
	 * Creates the.
	 *
	 * @param params the params
	 * @return the call
	 * @throws TwilioRestException 
	 */
	public Call create(Map<String, String> params) throws TwilioRestException;
}
