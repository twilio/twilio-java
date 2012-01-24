package com.twilio.sdk.resource.factory;

import java.util.Map;

import com.twilio.sdk.TwilioRestException;
import com.twilio.sdk.resource.instance.CallerIdValidation;

// TODO: Auto-generated Javadoc
/**
 * A factory for creating OutgoingCallerId objects.
 */
public interface OutgoingCallerIdFactory {
	
	/**
	 * Creates the.
	 *
	 * @param params the params
	 * @return the caller id validation
	 * @throws TwilioRestException 
	 */
	public CallerIdValidation create(Map<String, String> params) throws TwilioRestException;
}
