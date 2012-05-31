package com.twilio.sdk.resource.list;

import java.util.Map;

import com.twilio.sdk.TwilioRestClient;
import com.twilio.sdk.resource.ListResource;
import com.twilio.sdk.resource.instance.Conference;

// TODO: Auto-generated Javadoc
/**
 * The Class ConferenceList.
 * 
 *  For more information see <a href="http://www.twilio.com/docs/api/rest/conference">http://www.twilio.com/docs/api/rest/conference</a>
 */
public class ConferenceList extends ListResource<Conference> {

	/**
	 * Instantiates a new conference list.
	 *
	 * @param client the client
	 */
	public ConferenceList(TwilioRestClient client) {
		super(client);
	}

	/**
	 * Instantiates a new conference list.
	 *
	 * @param client the client
	 * @param filters the filters
	 */
	public ConferenceList(TwilioRestClient client, Map<String, String> filters) {
		super(client, filters);
	}

	/* (non-Javadoc)
	 * @see com.twilio.sdk.resource.Resource#getResourceLocation()
	 */
	@Override
	protected String getResourceLocation() {
		return "/" + TwilioRestClient.DEFAULT_VERSION + "/Accounts/"
				+ this.getRequestAccountSid() + "/Conferences.json";
	}

	/* (non-Javadoc)
	 * @see com.twilio.sdk.resource.ListResource#makeNew(com.twilio.sdk.TwilioRestClient, java.util.Map)
	 */
	@Override
	protected Conference makeNew(TwilioRestClient client,
			Map<String, Object> params) {
		return new Conference(client, params);
	}

	/* (non-Javadoc)
	 * @see com.twilio.sdk.resource.ListResource#getListKey()
	 */
	@Override
	protected String getListKey() {
		return "conferences";
	}
	
	
}
