package com.twilio.sdk.resource.factory;

import java.util.Map;

import com.twilio.sdk.TwilioRestException;
import com.twilio.sdk.resource.instance.IncomingPhoneNumber;

// TODO: Auto-generated Javadoc
/**
 * A factory for creating IncomingPhoneNumber objects.
 */
public interface IncomingPhoneNumberFactory {
	
	/**
	 * Creates the.
	 *
	 * @param params the params
	 * @return the incoming phone number
	 * @throws TwilioRestException 
	 */
	public IncomingPhoneNumber create(Map<String, String> params) throws TwilioRestException;
}
