package com.twilio.sdk.resource.list;

import com.twilio.sdk.TwilioRestClient;
import com.twilio.sdk.resource.ListResource;
import com.twilio.sdk.resource.instance.Recording;

import java.util.Map;

// TODO: Auto-generated Javadoc
/**
 * The Class RecordingList.
 *
 * For more information see <a href="https://www.twilio.com/docs/api/rest/recording">https://www.twilio.com/docs/api/rest/recording</a>
 */
public class RecordingList extends ListResource<Recording> {

	private static String requestCallSid;

	/**
	 * Instantiates a new recording list.
	 *
	 * @param client the client
	 */
	public RecordingList(TwilioRestClient client) {
		super(client);
	}

	/**
	 * Instantiates a new recording list.
	 *
	 * @param client the client
	 * @param filters the filters
	 */
	public RecordingList(TwilioRestClient client, Map<String, String> filters) {
		super(client, filters);
	}

	/**
	 * Instantiates a new recording list belonging to call.
	 *
	 * @param client the client
	 * @param callSid the sid of the owning call
	 */
	public RecordingList(TwilioRestClient client, String callSid) {
		super(client);
		this.requestCallSid = callSid;
	}

	/**
	 * Instantiates a new recording list belonging to call with filters.
	 *
	 * @param client the client
	 * @param callSid the sid of the owning call
	 * @param filters the filters
	 */
	public RecordingList(TwilioRestClient client, String callSid,
			Map<String, String> filters) {
		super(client, filters);
		this.requestCallSid = callSid;
	}

	/* (non-Javadoc)
	 * @see com.twilio.sdk.resource.Resource#getResourceLocation()
	 */
	@Override
	protected String getResourceLocation() {
		if (this.requestCallSid != null) {
			return "/" + TwilioRestClient.DEFAULT_VERSION + "/Accounts/"
					+ this.getRequestAccountSid() + "/Calls/"
					+ this.getRequestCallSid() + "/Recordings.json";
		} else {
			return "/" + TwilioRestClient.DEFAULT_VERSION + "/Accounts/"
					+ this.getRequestAccountSid() + "/Recordings.json";
		}

	}

	/* (non-Javadoc)
	 * @see com.twilio.sdk.resource.ListResource#makeNew(com.twilio.sdk.TwilioRestClient, java.util.Map)
	 */
	@Override
	protected Recording makeNew(TwilioRestClient client,
			Map<String, Object> params) {
		return new Recording(client, params);
	}

	/* (non-Javadoc)
	 * @see com.twilio.sdk.resource.ListResource#getListKey()
	 */
	@Override
	protected String getListKey() {
		return "recordings";
	}

	/**
	 * Gets the call sid of the recording *if* it was constructed
	 * with a call sid.
	 *
	 * @return the call sid of the parent call
	 */
	public String getRequestCallSid() {
		return this.requestCallSid;
	}
}
