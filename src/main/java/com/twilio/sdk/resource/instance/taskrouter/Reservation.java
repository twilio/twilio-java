package com.twilio.sdk.resource.instance.taskrouter;

import com.twilio.sdk.TwilioTaskRouterClient;
import com.twilio.sdk.resource.InstanceResource;

import java.util.Date;
import java.util.Map;

/**
 * Reservation class use to get a reservation resource.
 */
public class Reservation extends InstanceResource<TwilioTaskRouterClient> {

	private static final String TASK_SID_PROPERTY = "task_sid";

	private static final String WORKSPACE_SID_PROPERTY = "workspace_sid";

	/**
	 * Instantiates a reservation.
	 *
	 * @param client the client
	 */
	public Reservation(final TwilioTaskRouterClient client) {
		super(client);
	}

	/**
	 * Instantiates a reservation.
	 *
	 * @param client the client
	 * @param properties the properties
	 */
	public Reservation(final TwilioTaskRouterClient client, final Map<String, Object> properties) {
		super(client, properties);
	}

	/**
	 * Instantiates a reservation.
	 *
	 * @param client the client
	 * @param workspaceSid the workspace sid
	 * @param taskSid the task sid
	 * @param reservationSid the reservation sid
	 */
	public Reservation(final TwilioTaskRouterClient client, final String workspaceSid, final String taskSid,
	                   final String reservationSid) {
		super(client);
		if (workspaceSid == null || "".equals(workspaceSid)) {
			throw new IllegalArgumentException("The workspaceSid for a Reservation cannot be null");
		}
		if (taskSid == null || "".equals(taskSid)) {
			throw new IllegalArgumentException("The taskSid for a Reservation cannot be null");
		}
		if (reservationSid == null || "".equals(reservationSid)) {
			throw new IllegalArgumentException("The reservationSid for a Reservation cannot be null");
		}
		setProperty(WORKSPACE_SID_PROPERTY, workspaceSid);
		setProperty(TASK_SID_PROPERTY, taskSid);
		setProperty(SID_PROPERTY, reservationSid);
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
	 * Gets the reservation status.
	 *
	 * @return the reservation status
	 */
	public String getReservationStatus() {
		return getProperty("reservation_status");
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
	 * Gets the task sid.
	 *
	 * @return the task sid
	 */
	public String getTaskSid() {
		return getProperty(TASK_SID_PROPERTY);
	}

	/**
	 * Gets the worker activity sid.
	 *
	 * @return the worker activity sid
	 */
	public String getWorkerActivitySid() {
		return getProperty("worker_activity_sid");
	}

	/**
	 * Gets the worker name.
	 *
	 * @return the worker name
	 */
	public String getWorkerName() {
		return getProperty("worker_name");
	}

	/**
	 * Gets the worker sid.
	 *
	 * @return the worker sid
	 */
	public String getWorkerSid() {
		return getProperty("worker_sid");
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
		       getWorkspaceSid() + "/Tasks/" + getTaskSid() + "/Reservations/" + getSid();
	}
}
