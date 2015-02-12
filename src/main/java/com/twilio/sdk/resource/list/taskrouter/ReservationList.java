package com.twilio.sdk.resource.list.taskrouter;

import com.twilio.sdk.TwilioTaskRouterClient;
import com.twilio.sdk.resource.NextGenListResource;
import com.twilio.sdk.resource.instance.taskrouter.Reservation;

import java.util.Map;

/**
 * ReservationList to work with {@link com.twilio.sdk.resource.instance.taskrouter.Reservation}.
 */
public class ReservationList extends NextGenListResource<Reservation, TwilioTaskRouterClient> {

	private String taskSid;

	private String workspaceSid;

	/**
	 * Instantiates a reservation list.
	 *
	 * @param client the client
	 * @param workspaceSid the workspace sid
	 * @param taskSid the task sid
	 */
	public ReservationList(final TwilioTaskRouterClient client, final String workspaceSid, final String taskSid) {
		super(client);
		this.workspaceSid = workspaceSid;
		this.taskSid = taskSid;
	}

	/**
	 * Instantiates a reservation list.
	 *
	 * @param client the client
	 * @param workspaceSid the workspace sid
	 * @param taskSid the task sid
	 */
	public ReservationList(final TwilioTaskRouterClient client, final String workspaceSid, final String taskSid,
	                       final Map<String, String> filters) {
		super(client, filters);
		this.workspaceSid = workspaceSid;
		this.taskSid = taskSid;
	}

	@Override
	protected Reservation makeNew(final TwilioTaskRouterClient client, final Map<String, Object> params) {
		return new Reservation(client, params);
	}

	@Override
	protected String getResourceLocation() {
		return "/" + TwilioTaskRouterClient.DEFAULT_VERSION + "/Workspaces/" + workspaceSid + "/Tasks/" + taskSid +
		       "/Reservations";
	}
}
