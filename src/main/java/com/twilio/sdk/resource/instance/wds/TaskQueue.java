package com.twilio.sdk.resource.instance.wds;

import com.twilio.sdk.TwilioWdsClient;
import com.twilio.sdk.resource.InstanceResource;

import java.util.Date;
import java.util.Map;

/**
 * Queues distribute tasks to workers, and collect statistics about task distribution.
 */
public class TaskQueue extends InstanceResource<TwilioWdsClient> {

	private static final String WORKSPACE_SID_PROPERTY = "workspace_sid";

	/**
	 * Instantiates a queue.
	 *
	 * @param client the client
	 */
	public TaskQueue(final TwilioWdsClient client) {
		super(client);
	}

	/**
	 * Instantiates a queue.
	 *
	 * @param client the client
	 * @param properties the properties
	 */
	public TaskQueue(final TwilioWdsClient client, final Map<String, Object> properties) {
		super(client, properties);
	}

	/**
	 * Instantiates a queue.
	 *
	 * @param client the client
	 * @param workspaceSid the workspace sid
	 * @param queueSid the queue sid
	 */
	public TaskQueue(final TwilioWdsClient client, final String workspaceSid, final String queueSid) {
		super(client);
		if (workspaceSid == null || "".equals(workspaceSid)) {
			throw new IllegalArgumentException("The workspaceSid for a TaskQueue cannot be null");
		}
		if (queueSid == null || "".equals(queueSid)) {
			throw new IllegalArgumentException("The queueSid for a TaskQueue cannot be null");
		}
		setProperty(WORKSPACE_SID_PROPERTY, workspaceSid);
		setProperty(SID_PROPERTY, queueSid);
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
	 * Gets the assignment activity name.
	 *
	 * @return the assignment activity name
	 */
	public String getAssignmentActivityName() {
		return getProperty("assignment_activity_name");
	}

	/**
	 * Gets the assignment activity sid.
	 *
	 * @return the assignment activity sid
	 */
	public String getAssignmentActivitySid() {
		return getProperty("assignment_activity_sid");
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
	 * Gets the friendly name.
	 *
	 * @return the friendly name
	 */
	public String getFriendlyName() {
		return getProperty("friendly_name");
	}

	/**
	 * Gets the reservation activity name.
	 *
	 * @return the reservation activity name
	 */
	public String getReservationActivityName() {
		return getProperty("reservation_activity_name");
	}

	/**
	 * Gets the reservation activity sid.
	 *
	 * @return the reservation activity sid
	 */
	public String getReservationActivitySid() {
		return getProperty("reservation_activity_sid");
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
	 * Gets the target workers.
	 *
	 * @return the target workers
	 */
	public String getTargetWorkers() {
		return getProperty("target_workers");
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
		return "/" + TwilioWdsClient.DEFAULT_VERSION + "/Accounts/" + getRequestAccountSid() + "/Workspaces/" +
		       getWorkspaceSid() + "/TaskQueues/" + getSid();
	}
}
