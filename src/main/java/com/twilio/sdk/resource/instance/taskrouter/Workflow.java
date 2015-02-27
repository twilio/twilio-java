package com.twilio.sdk.resource.instance.taskrouter;

import com.twilio.sdk.TwilioTaskRouterClient;
import com.twilio.sdk.resource.NextGenInstanceResource;

import java.util.Date;
import java.util.Map;

/**
 * Workflows control how tasks will be prioritized and routed into Queues, and how Tasks should escalate in priority or
 * move across queues over time. Workflows are described in a simple JSON format.
 *
 * See <a href="https://www.twilio.com/docs/taskrouter/workflows">the TaskRouter documentation</a>.
 */
public class Workflow extends NextGenInstanceResource<TwilioTaskRouterClient> {

	private static final String WORKSPACE_SID_PROPERTY = "workspace_sid";

	/**
	 * Instantiates a workflow.
	 *
	 * @param client the client
	 */
	public Workflow(final TwilioTaskRouterClient client) {
		super(client);
	}

	/**
	 * Instantiates a workflow.
	 *
	 * @param client the client
	 * @param properties the properties
	 */
	public Workflow(final TwilioTaskRouterClient client, final Map<String, Object> properties) {
		super(client, properties);
	}

	/**
	 * Instantiates a workflow.
	 *
	 * @param client the client
	 * @param workspaceSid the workspace sid
	 * @param workflowSid the workflow sid
	 */
	public Workflow(final TwilioTaskRouterClient client, final String workspaceSid, final String workflowSid) {
		super(client);
		if (workspaceSid == null || "".equals(workspaceSid)) {
			throw new IllegalArgumentException("The workspaceSid for an Workflow cannot be null");
		}
		if (workflowSid == null || "".equals(workflowSid)) {
			throw new IllegalArgumentException("The workflowSid for an Workflow cannot be null");
		}
		setProperty(WORKSPACE_SID_PROPERTY, workspaceSid);
		setProperty(SID_PROPERTY, workflowSid);
	}

	/**
	 * The URL that will be called whenever a task managed by this Workflow is assigned to a Worker.
	 *
	 * @return the assignment callback url
	 */
	public String getAssignmentCallbackUrl() {
		return getProperty("assignment_callback_url");
	}

	/**
	 * JSON document configuring the rules for this Workflow.
	 *
	 * @return the configuration
	 */
	public String getConfiguration() {
		return getProperty("configuration");
	}

	/**
	 * The date and time this Workflow was created.
	 *
	 * @return the date created
	 */
	public Date getDateCreated() {
		return parseDate(getProperty(DATE_CREATED_PROPERTY));
	}

	/**
	 * The date and time this Workflow was last updated.
	 *
	 * @return the date updated
	 */
	public Date getDateUpdated() {
		return parseDate(getProperty(DATE_UPDATED_PROPERTY));
	}

	/**
	 * If the request to the AssignmentCallbackUrl fails, the assignment callback will be made to this URL.
	 *
	 * @return the fallback assignment callback url
	 */
	public String getFallbackAssignmentCallbackUrl() {
		return getProperty("fallback_assignment_callback_url");
	}

	/**
	 * A human-readable description of this Workflow.
	 *
	 * @return the friendly name
	 */
	public String getFriendlyName() {
		return getProperty("friendly_name");
	}

	/**
	 * This Workflow's unique ID.
	 *
	 * @return the sid
	 */
	public String getSid() {
		return getProperty(SID_PROPERTY);
	}

	/**
	 * Determines how long TaskRouter will wait for a confirmation response from your application after assigning a Task
	 * to a worker. Defaults to 120 seconds.
	 *
	 * @return the task reservation timeout
	 */
	public String getTaskReservationTimeout() {
		return getProperty("task_reservation_timeout");
	}

	/**
	 * The unique ID of the {@link com.twilio.sdk.resource.instance.taskrouter.Workspace} containing this Workflow.
	 *
	 * @return the workspace sid
	 */
	public String getWorkspaceSid() {
		return getProperty(WORKSPACE_SID_PROPERTY);
	}

	@Override
	protected String getResourceLocation() {
		return "/" + TwilioTaskRouterClient.DEFAULT_VERSION + "/Workspaces/" + getWorkspaceSid() + "/Workflows/" +
		       getSid();
	}
}
