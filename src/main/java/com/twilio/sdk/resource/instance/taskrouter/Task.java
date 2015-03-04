package com.twilio.sdk.resource.instance.taskrouter;

import com.twilio.sdk.TwilioTaskRouterClient;
import com.twilio.sdk.resource.NextGenInstanceResource;

import java.util.Date;
import java.util.Map;

/**
 * A Task resource represents a single item of work waiting to be processed.
 *
 * See <a href="https://www.twilio.com/docs/taskrouter/tasks">the TaskRouter documentation</a>.
 */
public class Task extends NextGenInstanceResource<TwilioTaskRouterClient> {

	private static final String WORKSPACE_SID_PROPERTY = "workspace_sid";

	/**
	 * Instantiates a task.
	 *
	 * @param client the client
	 */
	public Task(final TwilioTaskRouterClient client) {
		super(client);
	}

	/**
	 * Instantiates a task.
	 *
	 * @param client the client
	 * @param properties the properties
	 */
	public Task(final TwilioTaskRouterClient client, final Map<String, Object> properties) {
		super(client, properties);
	}

	/**
	 * Instantiates a task.
	 *
	 * @param client the client
	 * @param workspaceSid the workspace sid
	 * @param taskSid the task sid
	 */
	public Task(final TwilioTaskRouterClient client, final String workspaceSid, final String taskSid) {
		super(client);
		if (workspaceSid == null || "".equals(workspaceSid)) {
			throw new IllegalArgumentException("The workspaceSid for an Task cannot be null");
		}
		if (taskSid == null || "".equals(taskSid)) {
			throw new IllegalArgumentException("The taskSid for an Task cannot be null");
		}
		setProperty(WORKSPACE_SID_PROPERTY, workspaceSid);
		setProperty(SID_PROPERTY, taskSid);
	}

	/**
	 * The ID of the {@link com.twilio.sdk.resource.instance.Account} that owns this Task.
	 *
	 * @return the account sid
	 */
	public String getAccountSid() {
		return getProperty("account_sid");
	}

	/**
	 * The number of seconds since this Task was created.
	 *
	 * @return the age
	 */
	public int getAge() {
		Integer prop = (Integer) getObject("age");
		if (prop != null) {
			return prop;
		} else {
			throw new IllegalStateException("The Task doesn't have its age set");
		}
	}

	/**
	 * A string representing the Assignment State of the task. May be "pending", "reserved", "assigned" or "canceled".
	 *
	 * @return the assignment status
	 */
	public String getAssignmentStatus() {
		return getProperty("assignment_status");
	}

	/**
	 * The user-defined JSON string describing the custom attributes of this work.
	 *
	 * @return the attributes
	 */
	public String getAttributes() {
		return getProperty("attributes");
	}

	/**
	 * Date and time this Task was created.
	 *
	 * @return the date created
	 */
	public Date getDateCreated() {
		return parseDate(getProperty(DATE_CREATED_PROPERTY));
	}

	/**
	 * Date and time this Task was last updated.
	 *
	 * @return the date updated
	 */
	public Date getDateUpdated() {
		return parseDate(getProperty(DATE_UPDATED_PROPERTY));
	}

	/**
	 * The current priority score of the task, as assigned by the workflow. Tasks with higher values will be assigned before tasks with lower values.
	 *
	 * @return the priority
	 */
	public int getPriority() {
		Integer prop = (Integer) getObject("priority");
		if (prop != null) {
			return prop;
		} else {
			throw new IllegalStateException("The Task doesn't have its priority set");
		}
	}

	/**
	 * The Task assignment request timeout, in seconds.
	 *
	 * @return the timeout
	 */
	public int getTimeout() {
		return (Integer)getObject("timeout");
	}

	/**
	 * The unique ID of the {@link com.twilio.sdk.resource.instance.taskrouter.TaskQueue} this Task occupies.
	 *
	 * @return the {@link TaskQueue} sid
	 */
	public String getQueueSid() {
		return getProperty("task_queue_sid");
	}

	/**
	 * The unique ID of this Task.
	 *
	 * @return the sid
	 */
	public String getSid() {
		return getProperty(SID_PROPERTY);
	}

	/**
	 * The ID of the {@link com.twilio.sdk.resource.instance.taskrouter.Workflow} responsible for routing this Task.
	 *
	 * @return the workflow sid
	 */
	public String getWorkflowSid() {
		return getProperty("workflow_sid");
	}

	/**
	 * The ID of the {@link com.twilio.sdk.resource.instance.taskrouter.Workspace} containing this Task.
	 *
	 * @return the workspace sid
	 */
	public String getWorkspaceSid() {
		return getProperty(WORKSPACE_SID_PROPERTY);
	}

	/**
	 * The reason the Task was canceled (if applicable).
	 *
	 * @return cancellation reason
	 */
	public String getReason() {
		return getProperty("reason");
	}

	@Override
	protected String getResourceLocation() {
		return "/" + TwilioTaskRouterClient.DEFAULT_VERSION + "/Workspaces/" + getWorkspaceSid() + "/Tasks/" + getSid();
	}
}
