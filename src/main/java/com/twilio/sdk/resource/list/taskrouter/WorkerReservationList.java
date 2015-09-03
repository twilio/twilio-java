package com.twilio.sdk.resource.list.taskrouter;

import com.twilio.sdk.TwilioTaskRouterClient;
import com.twilio.sdk.resource.NextGenListResource;
import com.twilio.sdk.resource.instance.taskrouter.Reservation;

import java.util.Map;

/**
 * ReservationList to work with {@link com.twilio.sdk.resource.instance.taskrouter.Reservation}.
 */
public class WorkerReservationList extends NextGenListResource<Reservation, TwilioTaskRouterClient> {

	private String workerSid;

	private String workspaceSid;

	/**
	 * Instantiates a reservation list.
	 *
	 * @param client the client
	 * @param workspaceSid the workspace sid
	 * @param workerSid the worker sid
	 */
	public WorkerReservationList(final TwilioTaskRouterClient client, final String workspaceSid, final String workerSid) {
		this(client, workspaceSid, workerSid, null);
	}

	/**
	 * Instantiates a reservation list.
	 *
	 * @param client the client
	 * @param workspaceSid the workspace sid
	 * @param taskSid the worker sid
	 */
	public WorkerReservationList(final TwilioTaskRouterClient client, final String workspaceSid, final String workerSid,
	                       final Map<String, String> filters) {
		super(client, filters);
		this.workspaceSid = workspaceSid;
		this.workerSid = workerSid;
	}

	@Override
	protected Reservation makeNew(final TwilioTaskRouterClient client, final Map<String, Object> params) {
		return new Reservation(client, params);
	}

	@Override
	protected String getResourceLocation() {
		return "/" + TwilioTaskRouterClient.DEFAULT_VERSION + "/Workspaces/" + workspaceSid + "/Workers/" + workerSid +
		       "/Reservations";
	}
}
