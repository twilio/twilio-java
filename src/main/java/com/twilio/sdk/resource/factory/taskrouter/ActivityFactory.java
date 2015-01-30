package com.twilio.sdk.resource.factory.taskrouter;

import com.twilio.sdk.TwilioRestException;
import com.twilio.sdk.resource.instance.taskrouter.Activity;
import org.apache.http.NameValuePair;

import java.util.List;
import java.util.Map;

/**
 * A factory for creating Activities.
 */
public interface ActivityFactory {

	/**
	 * Creates an activity.
	 *
	 * @param params the params list
	 * @return a activity
	 * @throws com.twilio.sdk.TwilioRestException
	 */
	public Activity create(Map<String, String> params) throws TwilioRestException;

	/**
	 * Creates an activity.
	 *
	 * @param params the params list
	 * @return a activity
	 * @throws TwilioRestException
	 */
	public Activity create(List<NameValuePair> params) throws TwilioRestException;

}
