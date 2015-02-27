package com.twilio.sdk.resource.instance.taskrouter;

import com.twilio.sdk.TwilioTaskRouterClient;
import com.twilio.sdk.resource.NextGenInstanceResource;

import java.util.Date;
import java.util.Map;

/**
 * The Reservation subresource of {@link com.twilio.sdk.resource.instance.taskrouter.Task}
 * is created whenever a Task is assigned to a {@link com.twilio.sdk.resource.instance.taskrouter.Worker}.
 * TaskRouter will provide the details of this Reservation instance subresource in the Assignment Callback
 * HTTP request it makes to your application server.
 *
 * See <a href="https://www.twilio.com/docs/taskrouter/tasks#reservation">the TaskRouter documentation</a>.
 */
public class Reservation extends NextGenInstanceResource<TwilioTaskRouterClient> {

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
	 * The unique ID of the {@link com.twilio.sdk.resource.instance.Account} that owns this Reservation.
	 *
	 * @return the account sid
	 */
	public String getAccountSid() {
		return getProperty("account_sid");
	}

	/**
	 * The date and time this Reservation was created.
	 *
	 * @return the date created
	 */
	public Date getDateCreated() {
		return parseDate(getProperty(DATE_CREATED_PROPERTY));
	}

	/**
	 * The date and time this Reservation was last updated.
	 *
	 * @return the date updated
	 */
	public Date getDateUpdated() {
		return parseDate(getProperty(DATE_UPDATED_PROPERTY));
	}

	/**
	 * The current status of this Reservation. One of "pending", "accepted", "rejected", or "timeout".
	 *
	 * @return the reservation status
	 */
	public String getReservationStatus() {
		return getProperty("reservation_status");
	}

	/**
	 * The unique ID of this Reservation.
	 *
	 * @return the sid
	 */
	public String getSid() {
		return getProperty(SID_PROPERTY);
	}

	/**
	 * The unique ID of the Task this Reservation was created for.
	 *
	 * @return the task sid
	 */
	public String getTaskSid() {
		return getProperty(TASK_SID_PROPERTY);
	}

	/**
	 * The FriendlyName of the reserved {@link com.twilio.sdk.resource.instance.taskrouter.Worker}.
	 *
	 * @return the worker name
	 */
	public String getWorkerName() {
		return getProperty("worker_name");
	}

	/**
	 * The unique ID of the reserved {@link com.twilio.sdk.resource.instance.taskrouter.Worker}
	 *
	 * @return the worker sid
	 */
	public String getWorkerSid() {
		return getProperty("worker_sid");
	}

	/**
	 * The unique ID of the {@link com.twilio.sdk.resource.instance.taskrouter.Workspace} containing this Reservation.
	 *
	 * @return the workspace sid
	 */
	public String getWorkspaceSid() {
		return getProperty(WORKSPACE_SID_PROPERTY);
	}

	@Override
	protected String getResourceLocation() {
		return "/" + TwilioTaskRouterClient.DEFAULT_VERSION + "/Workspaces/" + getWorkspaceSid() + "/Tasks/" +
		       getTaskSid() + "/Reservations/" + getSid();
	}
}
