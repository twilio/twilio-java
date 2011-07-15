package com.twilio.sdk.resource.list;

import java.util.Map;

import com.twilio.sdk.TwilioRestClient;
import com.twilio.sdk.TwilioRestException;
import com.twilio.sdk.TwilioRestResponse;
import com.twilio.sdk.resource.ListResource;
import com.twilio.sdk.resource.factory.CallFactory;
import com.twilio.sdk.resource.instance.Call;

// TODO: Auto-generated Javadoc
/**
 * The Class CallList.
 * 
 *  For more information see {@see <a href="http://www.twilio.com/docs/api/rest/call">http://www.twilio.com/docs/api/rest/call}
 */
public class CallList extends ListResource<Call> implements CallFactory {
	
	/**
	 * Instantiates a new call list.
	 *
	 * @param client the client
	 */
	public CallList(TwilioRestClient client) {
		super(client);
	}

	/**
	 * Instantiates a new call list.
	 *
	 * @param client the client
	 * @param filters the filters
	 */
	public CallList(TwilioRestClient client, Map<String, String> filters) {
		super(client, filters);
	}

	/* (non-Javadoc)
	 * @see com.twilio.sdk.resource.Resource#getResourceLocation()
	 */
	@Override
	protected String getResourceLocation() {
		return "/" + TwilioRestClient.DEFAULT_VERSION + "/Accounts/"
				+ this.getRequestAccountSid() + "/Calls.json";
	}

	/* (non-Javadoc)
	 * @see com.twilio.sdk.resource.ListResource#makeNew(com.twilio.sdk.TwilioRestClient, java.util.Map)
	 */
	@Override
	protected Call makeNew(TwilioRestClient client, Map<String, Object> params) {
		return new Call(client, params);
	}

	/* (non-Javadoc)
	 * @see com.twilio.sdk.resource.ListResource#getListKey()
	 */
	@Override
	protected String getListKey() {
		return "calls";
	}

	/* (non-Javadoc)
	 * @see com.twilio.sdk.resource.factory.CallFactory#create(java.util.Map)
	 */
	public Call create(Map<String, String> params) throws TwilioRestException {
		TwilioRestResponse response = this.getClient().request(
				this.getResourceLocation(), "POST", params);
		return makeNew(this.getClient(), response.toMap());
	}

}
