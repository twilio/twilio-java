package com.twilio.sdk.resource.factory;

import com.twilio.sdk.TwilioRestException;
import com.twilio.sdk.resource.instance.Call;
import org.apache.http.NameValuePair;

import java.util.List;
import java.util.Map;

// TODO: Auto-generated Javadoc
/**
 * A factory for creating Call objects.
 */
public interface CallFactory {

	/**
	 * Creates the Call.
	 *
	 * @param params the params map
	 * @return the call
	 * @throws TwilioRestException
	 */
	public Call create(Map<String, String> params) throws TwilioRestException;

	/**
	 * Creates the Call.
	 *
	 * @param params the params list
	 * @return the call
	 * @throws TwilioRestException
	 */
	public Call create(List<NameValuePair> params) throws TwilioRestException;
}
