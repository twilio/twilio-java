package com.twilio.sdk.resource.list.wds;

import com.twilio.sdk.TwilioWdsClient;
import com.twilio.sdk.resource.ListResource;
import com.twilio.sdk.resource.instance.wds.Reservation;

import java.util.Map;

/**
 * ReservationList to work with {@link com.twilio.sdk.resource.instance.wds.Reservation}.
 */
public class ReservationList extends ListResource<Reservation, TwilioWdsClient> {

	private String taskSid;

	private String workspaceSid;

	/**
	 * Instantiates a reservation list.
	 *
	 * @param client the client
	 * @param workspaceSid the workspace sid
	 * @param taskSid the task sid
	 */
	public ReservationList(final TwilioWdsClient client, final String workspaceSid, final String taskSid) {
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
	public ReservationList(final TwilioWdsClient client, final String workspaceSid, final String taskSid,
	                       final Map<String, String> filters) {
		super(client, filters);
		this.workspaceSid = workspaceSid;
		this.taskSid = taskSid;
	}

	@Override
	protected String getListKey() {
		return "reservations";
	}

	@Override
	protected Reservation makeNew(final TwilioWdsClient client, final Map<String, Object> params) {
		return new Reservation(client, params);
	}

	@Override
	protected String getResourceLocation() {
		return "/" + TwilioWdsClient.DEFAULT_VERSION + "/Accounts/" + getRequestAccountSid() + "/Workspaces/" +
		       workspaceSid + "/Tasks/" + taskSid + "/Reservations";
	}
}
