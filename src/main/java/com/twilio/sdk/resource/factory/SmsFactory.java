package com.twilio.sdk.resource.factory;

import java.util.Map;

import com.twilio.sdk.TwilioRestException;
import com.twilio.sdk.resource.instance.Sms;

// TODO: Auto-generated Javadoc
/**
 * A factory for creating Sms objects.
 */
public interface SmsFactory {

    /**
	 * Creates the.
	 *
	 * @param params the params
	 * @return the sms
	 * @throws TwilioRestException 
	 */
	public Sms create(Map<String, String> params) throws TwilioRestException;
}
