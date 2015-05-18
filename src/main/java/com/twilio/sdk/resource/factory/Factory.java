package com.twilio.sdk.resource.factory;

import java.util.List;
import java.util.Map;

import org.apache.http.NameValuePair;

import com.twilio.sdk.TwilioRestException;

/**
 * A factory for creating resources.
 */
public interface Factory<T> {

	/**
	 * Creates a Resource
	 *
	 * @param params the params list
	 * @return a Participant
	 * @throws com.twilio.sdk.TwilioRestException
	 */
	public T create(Map<String, String> params) throws TwilioRestException;

	/**
	 * Creates a Resource
	 *
	 * @param params the params list
	 * @return a Participant
	 * @throws TwilioRestException
	 */
	public T create(List<NameValuePair> params) throws TwilioRestException;

}
