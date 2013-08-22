package com.twilio.sdk.resource.factory;

import java.util.Map;

import com.twilio.sdk.TwilioRestException;
import com.twilio.sdk.resource.instance.Message;

// TODO: Auto-generated Javadoc
/**
 * A factory for creating Message objects.
 */
public interface MessageFactory {

	/**
	 * Creates the message.
	 *
	 * @param params the params
	 * @return the message
	 * @throws TwilioRestException
   */
	public Message create(Map<String, String> params) throws TwilioRestException;
}
