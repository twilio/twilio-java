package com.twilio.sdk.resource.factory;

import com.twilio.sdk.TwilioRestException;
import com.twilio.sdk.resource.instance.CallerIdValidation;
import org.apache.http.NameValuePair;

import java.util.List;
import java.util.Map;

// TODO: Auto-generated Javadoc
/**
 * A factory for creating OutgoingCallerId objects.
 */
public interface OutgoingCallerIdFactory {

	/**
	 * Creates the OutgoingCallerId
	 *
	 * @param params the params map
	 * @return the caller id validation
	 * @throws TwilioRestException
	 */
	public CallerIdValidation create(Map<String, String> params) throws TwilioRestException;

	/**
	 * Creates the OutgoingCallerId
	 *
	 * @param params the params
	 * @return the caller id validation
	 * @throws TwilioRestException
	 */
	public CallerIdValidation create(List<NameValuePair> params) throws TwilioRestException;
}
