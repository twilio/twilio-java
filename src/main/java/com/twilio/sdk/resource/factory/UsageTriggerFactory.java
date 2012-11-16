package com.twilio.sdk.resource.factory;

import com.twilio.sdk.TwilioRestException;
import com.twilio.sdk.resource.instance.Sms;
import com.twilio.sdk.resource.instance.UsageTrigger;

import java.util.Map;

/**
 * A factory for creating Usage Trigger objects.
 */
public interface UsageTriggerFactory {

    /**
	 * Creates the.
	 *
	 * @param params the params
	 * @return the sms
	 * @throws com.twilio.sdk.TwilioRestException
	 */
	public UsageTrigger create(Map<String, String> params) throws TwilioRestException;
}
