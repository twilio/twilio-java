package com.twilio.sdk.resource.list.sip;

import com.twilio.sdk.TwilioRestClient;
import com.twilio.sdk.TwilioRestException;
import com.twilio.sdk.TwilioRestResponse;
import com.twilio.sdk.resource.ListResource;
import com.twilio.sdk.resource.factory.sip.CredentialListFactory;
import com.twilio.sdk.resource.instance.sip.CredentialListInstance;
import org.apache.http.NameValuePair;

import java.util.List;
import java.util.Map;

public class CredentialListList extends ListResource<CredentialListInstance> implements CredentialListFactory {

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
	protected CredentialListInstance makeNew(TwilioRestClient client, Map<String, Object> params) {
		return new CredentialListInstance(client, params);
	}

	/* (non-Javadoc)
	 * @see com.twilio.sdk.resource.ListResource#getListKey()
	 */
	@Override
	protected String getListKey() {
		return "credential_lists";
	}

	/* (non-Javadoc)
	 * @see com.twilio.sdk.resource.factory.CredentialListFactory#create(java.util.Map)
	 */
	public CredentialListInstance create(Map<String, String> params) throws TwilioRestException {
		TwilioRestResponse response = this.getClient().safeRequest(
				this.getResourceLocation(), "POST", params);
		return makeNew(this.getClient(), response.toMap());
	}

	/* (non-Javadoc)
	 * @see com.twilio.sdk.resource.factory.CredentialListFactory#create(java.util.List)
	 */
	public CredentialListInstance create(List<NameValuePair> params) throws TwilioRestException {
		TwilioRestResponse response = this.getClient().safeRequest(
				this.getResourceLocation(), "POST", params);
		return makeNew(this.getClient(), response.toMap());
	}

}
