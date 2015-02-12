package com.twilio.sdk.resource.instance.taskrouter;

import com.twilio.sdk.TwilioTaskRouterClient;
import com.twilio.sdk.resource.NextGenInstanceResource;

import java.util.Date;
import java.util.Map;

/**
 * Workflows route tasks to the appropriate queues, and set rules for each task's prioritization and escalation.
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
	 * Gets the assignment callback url.
	 *
	 * @return the assignment callback url
	 */
	public String getAssignmentCallbackUrl() {
		return getProperty("assignment_callback_url");
	}

	/**
	 * Gets the configuration.
	 *
	 * @return the configuration
	 */
	public String getConfiguration() {
		return getProperty("configuration");
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
	 * Gets the document content type.
	 *
	 * @return the document content type
	 */
	public String getDocumentContentType() {
		return getProperty("document_content_type");
	}

	/**
	 * Gets the fallback assignment callback url.
	 *
	 * @return the fallback assignment callback url
	 */
	public String getFallbackAssignmentCallbackUrl() {
		return getProperty("fallback_assignment_callback_url");
	}

	/**
	 * Gets the friendly name
	 *
	 * @return the friendly name
	 */
	public String getFriendlyName() {
		return getProperty("friendly_name");
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
	 * Gets the task reservation timeout.
	 *
	 * @return the task reservation timeout
	 */
	public String getTaskReservationTimeout() {
		return getProperty("task_reservation_timeout");
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
		return "/" + TwilioTaskRouterClient.DEFAULT_VERSION + "/Workspaces/" + getWorkspaceSid() + "/Workflows/" +
		       getSid();
	}
}
