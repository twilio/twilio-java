package com.twilio.sdk.resource.factory.taskrouter;

import java.util.Map;

import com.twilio.sdk.TwilioRestException;
import com.twilio.sdk.resource.instance.taskrouter.Worker;

/**
 * A factory for creating Workers.
 */
public interface WorkerFactory extends TaskRouterFactory<Worker> {

	/**
	 * Creates a worker
	 * @param friendlyName friendly name of the worker
	 * @param attributes attributes of the worker
	 * @param activitySid default activity of the worker
	 * @return a worker
	 * @throws TwilioRestException
	 */
	public Worker create(final String friendlyName, final Map<String, String> attributes, final String activitySid) throws TwilioRestException;
}
