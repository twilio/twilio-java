package com.twilio.sdk.resource.factory;

import java.util.Map;

import com.twilio.sdk.TwilioRestException;
import com.twilio.sdk.resource.instance.MediaInstance;

// TODO: Auto-generated Javadoc
/**
 * A factory for creating MediaInstance objects.
 */
public interface MediaFactory {

    /**
	 * Creates the.
	 *
	 * @param params the params
	 * @return the MediaInstance
	 * @throws TwilioRestException
	 */
	public MediaInstance create(Map<String, String> params) throws TwilioRestException;
}
