package com.twilio.sdk.resource.factory.taskrouter;

import java.util.Map;

import com.twilio.sdk.TwilioRestException;
import com.twilio.sdk.resource.instance.taskrouter.Task;

/**
 * A factory for creating Tasks.
 */
public interface TaskFactory extends TaskRouterFactory<Task> {

	/**
	 * Creates a task
	 * @param workflowSid the workflow sid
	 * @param attributes the Map of Attributes that will convert to JSON
	 * @param priority the priority of the task (optional)
	 * @param timeout the max timeout of the task (optional)
	 * @return a task
	 * @throws TwilioRestException
	 */
	public Task create(final String workflowSid, final Map<String, String> attributes, final Integer priority, final Integer timeout) throws TwilioRestException;
}
