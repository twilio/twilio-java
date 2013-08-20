package com.twilio.sdk.resource.factory;

import java.util.Map;

import com.twilio.sdk.TwilioRestException;
import com.twilio.sdk.resource.instance.Image;

// TODO: Auto-generated Javadoc
/**
 * A factory for creating Image objects.
 */
public interface ImageFactory {

    /**
	 * Creates the.
	 *
	 * @param params the params
	 * @return the Image
	 * @throws TwilioRestException
	 */
	public Image create(Map<String, String> params) throws TwilioRestException;
}
