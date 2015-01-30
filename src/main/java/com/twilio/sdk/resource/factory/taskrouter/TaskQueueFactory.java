package com.twilio.sdk.resource.factory.taskrouter;

import com.twilio.sdk.TwilioRestException;
import com.twilio.sdk.resource.instance.taskrouter.TaskQueue;
import org.apache.http.NameValuePair;

import java.util.List;
import java.util.Map;

/**
 * A factory for creating Queues.
 */
public interface TaskQueueFactory {

	/**
	 * Creates a {@link com.twilio.sdk.resource.instance.taskrouter.TaskQueue}.
	 *
	 * @param params the params list
	 * @return a TaskQueue
	 * @throws com.twilio.sdk.TwilioRestException
	 */
	public TaskQueue create(Map<String, String> params) throws TwilioRestException;

	/**
	 * Creates a {@link com.twilio.sdk.resource.instance.taskrouter.TaskQueue}.
	 *
	 * @param params the params list
	 * @return a TaskQueue
	 * @throws TwilioRestException
	 */
	public TaskQueue create(List<NameValuePair> params) throws TwilioRestException;
}
