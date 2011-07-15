package com.twilio.sdk.resource.list;

import java.util.Map;

import com.twilio.sdk.TwilioRestClient;
import com.twilio.sdk.resource.ListResource;
import com.twilio.sdk.resource.instance.Recording;

// TODO: Auto-generated Javadoc
/**
 * The Class RecordingList.
 * 
 * For more information see {@see <a href="http://www.twilio.com/docs/api/rest/recording">http://www.twilio.com/docs/api/rest/recording}
 */
public class RecordingList extends ListResource<Recording> {

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

	/* (non-Javadoc)
	 * @see com.twilio.sdk.resource.Resource#getResourceLocation()
	 */
	@Override
	protected String getResourceLocation() {
		return "/" + TwilioRestClient.DEFAULT_VERSION + "/Accounts/"
				+ this.getRequestAccountSid() + "/Recordings.json";
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
}
