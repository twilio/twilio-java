package com.twilio.sdk.resource.factory.taskrouter;

import com.twilio.sdk.TwilioRestException;
import com.twilio.sdk.resource.instance.taskrouter.Task;

import org.apache.http.NameValuePair;

import java.util.List;
import java.util.Map;

/**
 * A factory for creating Tasks.
 */
public interface TaskFactory {

	/**
	 * Creates a task.
	 *
	 * @param params the params list
	 * @return a task
	 * @throws com.twilio.sdk.TwilioRestException
	 */
	public Task create(Map<String, String> params) throws TwilioRestException;

	/**
	 * Creates a task.
	 *
	 * @param params the params list
	 * @return a task
	 * @throws TwilioRestException
	 */
	public Task create(List<NameValuePair> params) throws TwilioRestException;
	
	/**
	 * Creates a task
	 * @param workflowSid the workflow sid
	 * @param attributes the Map of Attributes that will convert to JSON
	 * @param priority the priority of the task (optional)
	 * @param timeout the max timeout of the task (optional)
	 * @return
	 * @throws TwilioRestException
	 */
    public Task create(String workflowSid, Map attributes, Integer priority, Integer timeout) throws TwilioRestException;
}
