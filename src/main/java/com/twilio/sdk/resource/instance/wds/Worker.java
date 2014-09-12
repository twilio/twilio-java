package com.twilio.sdk.resource.instance.wds;

import com.twilio.sdk.TwilioWdsClient;
import com.twilio.sdk.resource.InstanceResource;

import java.util.Date;
import java.util.Map;

/**
 * Workers are entities that process tasks, like agents in a call center, or the support staff on a support team. Worker
 * attributes describe which kinds of tasks they can handle, and Worker activities describe what the workers are doing
 * and whether they are ready to accept a new task assignment.
 */
public class Worker extends InstanceResource<TwilioWdsClient> {

	private static final String WORKSPACE_SID_PROPERTY = "workspace_sid";

	/**
	 * Instantiates a worker.
	 *
	 * @param client the client
	 */
	public Worker(final TwilioWdsClient client) {
		super(client);
	}

	/**
	 * Instantiates a worker.
	 *
	 * @param client the client
	 * @param properties the properties
	 */
	public Worker(final TwilioWdsClient client, final Map<String, Object> properties) {
		super(client, properties);
	}

	/**
	 * Instantiates a worker.
	 *
	 * @param client the client
	 * @param workspaceSid the workspace sid
	 * @param workerSid the worker sid
	 */
	public Worker(final TwilioWdsClient client, final String workspaceSid, final String workerSid) {
		super(client);
		if (workspaceSid == null || "".equals(workspaceSid)) {
			throw new IllegalArgumentException("The workspaceSid for an Worker cannot be null");
		}
		if (workerSid == null || "".equals(workerSid)) {
			throw new IllegalArgumentException("The workerSid for an Worker cannot be null");
		}
		setProperty(WORKSPACE_SID_PROPERTY, workspaceSid);
		setProperty(SID_PROPERTY, workerSid);
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
	 * Gets the activity name.
	 *
	 * @return the activity name
	 */
	public String getActivityName() {
		return getProperty("activity_name");
	}

	/**
	 * Gets the activity sid.
	 *
	 * @return the activity sid
	 */
	public String getActivitySid() {
		return getProperty("activity_sid");
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
	public Date getDateStatusChanged() {
		return parseDate(getProperty("date_status_changed"));
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
	 * Gets the workspace sid.
	 *
	 * @return the workspace sid
	 */
	public String getWorkspaceSid() {
		return getProperty(WORKSPACE_SID_PROPERTY);
	}

	/**
	 * Checks if available.
	 *
	 * @return true, if available
	 */
	public boolean isAvailable() {
		return (Boolean) getObject("available");
	}

	@Override
	protected String getResourceLocation() {
		return "/" + TwilioWdsClient.DEFAULT_VERSION + "/Accounts/" + getRequestAccountSid() + "/Workspaces/" +
		       getWorkspaceSid() + "/Workers/" + getSid();
	}
}
