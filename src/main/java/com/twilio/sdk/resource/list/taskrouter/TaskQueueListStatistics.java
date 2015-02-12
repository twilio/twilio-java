package com.twilio.sdk.resource.list.taskrouter;

import com.twilio.sdk.TwilioTaskRouterClient;
import com.twilio.sdk.resource.NextGenListResource;
import com.twilio.sdk.resource.instance.taskrouter.TaskQueueStatistics;

import java.util.Map;

/**
 * TaskQueueListStatistics to work with {@link com.twilio.sdk.resource.instance.taskrouter.TaskQueueStatistics}.
 */
public class TaskQueueListStatistics extends NextGenListResource<TaskQueueStatistics, TwilioTaskRouterClient> {

	private String workspaceSid;

	/**
	 * Instantiates a queue list statistics.
	 *
	 * @param client the client
	 * @param workspaceSid the workspace sid
	 */
	public TaskQueueListStatistics(final TwilioTaskRouterClient client, final String workspaceSid) {
		super(client);
		this.workspaceSid = workspaceSid;
	}

	/**
	 * Instantiates a queue list statistics.
	 *
	 * @param client the client
	 * @param workspaceSid the workspace sid
	 * @param filters the filters
	 */
	public TaskQueueListStatistics(final TwilioTaskRouterClient client, final String workspaceSid,
	                               final Map<String, String> filters) {
		super(client, filters);
		this.workspaceSid = workspaceSid;
	}

	@Override
	protected TaskQueueStatistics makeNew(final TwilioTaskRouterClient client, final Map<String, Object> params) {
		String queueSid = null;
		if (params != null) {
			queueSid = (String) params.get("task_queue_sid");
		}
		return new TaskQueueStatistics(client, workspaceSid, queueSid, null, params);
	}

	@Override
	protected String getResourceLocation() {
		return "/" + TwilioTaskRouterClient.DEFAULT_VERSION + "/Workspaces/" + workspaceSid + "/TaskQueues/Statistics";
	}
}
