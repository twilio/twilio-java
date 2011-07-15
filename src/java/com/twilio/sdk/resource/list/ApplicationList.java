package com.twilio.sdk.resource.list;

import java.util.Map;

import com.twilio.sdk.TwilioRestClient;
import com.twilio.sdk.TwilioRestException;
import com.twilio.sdk.TwilioRestResponse;
import com.twilio.sdk.resource.ListResource;
import com.twilio.sdk.resource.factory.ApplicationFactory;
import com.twilio.sdk.resource.instance.Application;

// TODO: Auto-generated Javadoc
/**
 * The Class ApplicationList.
 * 
 * For more information see {@see <a href="http://www.twilio.com/docs/api/rest/applications">http://www.twilio.com/docs/api/rest/applications}
 */
public class ApplicationList extends ListResource<Application> implements
		ApplicationFactory {

	/**
	 * Instantiates a new application list.
	 *
	 * @param client the client
	 */
	public ApplicationList(TwilioRestClient client) {
		super(client);
	}

	/**
	 * Instantiates a new application list.
	 *
	 * @param client the client
	 * @param filters the filters
	 */
	public ApplicationList(TwilioRestClient client, Map<String, String> filters) {
		super(client, filters);
	}

	/* (non-Javadoc)
	 * @see com.twilio.sdk.resource.Resource#getResourceLocation()
	 */
	@Override
	protected String getResourceLocation() {
		return "/" + TwilioRestClient.DEFAULT_VERSION + "/Accounts/"
				+ this.getRequestAccountSid() + "/Applications.json";
	}

	/* (non-Javadoc)
	 * @see com.twilio.sdk.resource.ListResource#makeNew(com.twilio.sdk.TwilioRestClient, java.util.Map)
	 */
	@Override
	protected Application makeNew(TwilioRestClient client,
			Map<String, Object> params) {
		return new Application(client, params);
	}

	/* (non-Javadoc)
	 * @see com.twilio.sdk.resource.ListResource#getListKey()
	 */
	@Override
	protected String getListKey() {
		return "Applications";
	}

	/* (non-Javadoc)
	 * @see com.twilio.sdk.resource.factory.ApplicationFactory#create(java.util.Map)
	 */
	public Application create(Map<String, String> params) throws TwilioRestException {
		TwilioRestResponse response = this.getClient().request(
				this.getResourceLocation(), "POST", params);
		return makeNew(this.getClient(), response.toMap());
	}
}
