package com.twilio.sdk.resource.list;

import java.util.Map;

import com.twilio.sdk.TwilioRestClient;
import com.twilio.sdk.TwilioRestException;
import com.twilio.sdk.TwilioRestResponse;
import com.twilio.sdk.resource.ListResource;
import com.twilio.sdk.resource.factory.CredentialListFactory;
import com.twilio.sdk.resource.instance.CredentialList;

public class CredentialListList extends ListResource<CredentialList> implements CredentialListFactory {

	/**
	 * Instantiates a new list of ip access control lists
	 *
	 * @param client the client
	 */
	public CredentialListList(TwilioRestClient client) {
		super(client);
	}

	/**
	 * Instantiates a new list of ip access control lists
	 *
	 * @param client the client
	 * @param filters the filters
	 */
	public CredentialListList(TwilioRestClient client, Map<String, String> filters) {
		super(client, filters);
	}

	/* (non-Javadoc)
	 * @see com.twilio.sdk.resource.Resource#getResourceLocation()
	 */
	@Override
	protected String getResourceLocation() {
		return "/" + TwilioRestClient.DEFAULT_VERSION + "/Accounts/"
				+ this.getRequestAccountSid() + "/SIP/CredentialLists.json";
	}

	/* (non-Javadoc)
	 * @see com.twilio.sdk.resource.ListResource#makeNew(com.twilio.sdk.TwilioRestClient, java.util.Map)
	 */
	@Override
	protected CredentialList makeNew(TwilioRestClient client, Map<String, Object> params) {
		return new CredentialList(client, params);
	}

	/* (non-Javadoc)
	 * @see com.twilio.sdk.resource.ListResource#getListKey()
	 */
	@Override
	protected String getListKey() {
		return "ip_access_control_lists";
	}

	/* (non-Javadoc)
	 * @see com.twilio.sdk.resource.factory.CredentialListFactory#create(java.util.Map)
	 */
	public CredentialList create(Map<String, String> params) throws TwilioRestException {
		TwilioRestResponse response = this.getClient().safeRequest(
				this.getResourceLocation(), "POST", params);
		return makeNew(this.getClient(), response.toMap());
	}

}
