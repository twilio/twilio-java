package com.twilio.sdk.resource.factory.wds;

import com.twilio.sdk.TwilioRestException;
import com.twilio.sdk.resource.instance.wds.Queue;
import org.apache.http.NameValuePair;

import java.util.List;
import java.util.Map;

/**
 * A factory for creating Queues.
 */
public interface QueueFactory {

	/**
	 * Creates a {@link com.twilio.sdk.resource.instance.wds.Queue}.
	 *
	 * @param params the params list
	 * @return a Queue
	 * @throws com.twilio.sdk.TwilioRestException
	 */
	public Queue create(Map<String, String> params) throws TwilioRestException;

	/**
	 * Creates a {@link com.twilio.sdk.resource.instance.wds.Queue}.
	 *
	 * @param params the params list
	 * @return a Queue
	 * @throws TwilioRestException
	 */
	public Queue create(List<NameValuePair> params) throws TwilioRestException;
}
