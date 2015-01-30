package com.twilio.sdk.resource.factory.taskrouter;

import com.twilio.sdk.TwilioRestException;
import com.twilio.sdk.resource.instance.taskrouter.Worker;
import org.apache.http.NameValuePair;

import java.util.List;
import java.util.Map;

/**
 * A factory for creating Workers.
 */
public interface WorkerFactory {

	/**
	 * Creates a worker.
	 *
	 * @param params the params list
	 * @return a worker
	 * @throws com.twilio.sdk.TwilioRestException
	 */
	public Worker create(Map<String, String> params) throws TwilioRestException;

	/**
	 * Creates a worker.
	 *
	 * @param params the params list
	 * @return a worker
	 * @throws TwilioRestException
	 */
	public Worker create(List<NameValuePair> params) throws TwilioRestException;
}
