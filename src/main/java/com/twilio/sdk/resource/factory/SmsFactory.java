package com.twilio.sdk.resource.factory;

import com.twilio.sdk.TwilioRestException;
import com.twilio.sdk.resource.instance.Sms;
import org.apache.http.NameValuePair;

import java.util.List;
import java.util.Map;

// TODO: Auto-generated Javadoc
/**
 * A factory for creating Sms objects.
 */
public interface SmsFactory {

    /**
	 * Creates the sms.
	 *
	 * @param params the params map
	 * @return the sms
	 * @throws TwilioRestException
	 */
	public Sms create(Map<String, String> params) throws TwilioRestException;

    /**
	 * Creates the sms.
	 *
	 * @param params the params list
	 * @return the sms
	 * @throws TwilioRestException
	 */
	public Sms create(List<NameValuePair> params) throws TwilioRestException;
}
