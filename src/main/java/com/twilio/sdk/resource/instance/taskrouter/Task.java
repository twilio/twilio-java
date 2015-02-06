package com.twilio.sdk.resource.instance.taskrouter;

import com.twilio.sdk.TwilioTaskRouterClient;
import com.twilio.sdk.resource.InstanceResource;

import java.util.Date;
import java.util.Map;

/**
 * Tasks are the individual pieces of work managed by the system.
 */
public class Task extends InstanceResource<TwilioTaskRouterClient> {

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
	 * Gets the account sid.
	 *
	 * @return the account sid
	 */
	public String getAccountSid() {
		return getProperty("account_sid");
	}

	/**
	 * Gets the age.
	 *
	 * @return the age
	 */
	public int getAge() {
		Integer prop = (Integer)getObject("age");
		if (prop != null) {
			return prop;
		} else {
			throw new IllegalStateException("The Task doesn't have its age set");
		}
	}

	/**
	 * Gets the assignment status.
	 *
	 * @return the assignment status
	 */
	public String getAssignmentStatus() {
		return getProperty("assignment_status");
	}

	/**
	 * Gets the attributes.
	 *
	 * @return the attributes
	 */
	public String getAttributes() {
		return getProperty("attributes");
	}

	/**
	 * Gets the date created.
	 *
	 * @return the date created
	 */
	public Date getDateCreated() {
		return parseDate(getProperty(DATE_CREATED_PROPERTY));
	}

	/**
	 * Gets the date updated.
	 *
	 * @return the date updated
	 */
	public Date getDateUpdated() {
		return parseDate(getProperty(DATE_UPDATED_PROPERTY));
	}

	/**
	 * Gets the priority.
	 *
	 * @return the priority
	 */
	public int getPriority() {
		Integer prop = (Integer)getObject("priority");
		if (prop != null) {
			return prop;
		} else {
			throw new IllegalStateException("The Task doesn't have its priority set");
		}
	}

    /**
     * Gets the timeout.
     *
     * @return the timeout
     */
    public int getTimeout() {
        return (Integer)getObject("timeout");
    }

	/**
	 * Gets the {@link TaskQueue} sid.
	 *
	 * @return the {@link TaskQueue} sid
	 */
	public String getQueueSid() {
		return getProperty("task_queue_sid");
	}

	/**
	 * Gets the sid.
	 *
	 * @return the sid
	 */
	public String getSid() {
		return getProperty(SID_PROPERTY);
	}

	/**
	 * Gets the workflow sid.
	 *
	 * @return the workflow sid
	 */
	public String getWorkflowSid() {
		return getProperty("workflow_sid");
	}

	/**
	 * Gets the workspace sid.
	 *
	 * @return the workspace sid
	 */
	public String getWorkspaceSid() {
		return getProperty(WORKSPACE_SID_PROPERTY);
	}

	@Override
	protected String getResourceLocation() {
		return "/" + TwilioTaskRouterClient.DEFAULT_VERSION + "/Accounts/" + getRequestAccountSid() + "/Workspaces/" +
		       getWorkspaceSid() + "/Tasks/" + getSid();
	}
}
