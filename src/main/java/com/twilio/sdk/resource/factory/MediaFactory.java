package com.twilio.sdk.resource.factory;

import java.util.Map;

import com.twilio.sdk.TwilioRestException;
import com.twilio.sdk.resource.instance.Media;

// TODO: Auto-generated Javadoc
/**
 * A factory for creating Media objects.
 */
public interface MediaFactory {

    /**
	 * Creates the.
	 *
	 * @param params the params
	 * @return the Media
	 * @throws TwilioRestException
	 */
	public Media create(Map<String, String> params) throws TwilioRestException;
}
