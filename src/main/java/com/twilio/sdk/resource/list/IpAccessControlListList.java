package com.twilio.sdk.resource.list;

import java.util.Map;

import com.twilio.sdk.TwilioRestClient;
import com.twilio.sdk.TwilioRestException;
import com.twilio.sdk.TwilioRestResponse;
import com.twilio.sdk.resource.ListResource;
import com.twilio.sdk.resource.factory.IpAccessControlListFactory;
import com.twilio.sdk.resource.instance.IpAccessControlList;

public class IpAccessControlListList extends ListResource<IpAccessControlList> implements IpAccessControlListFactory {

	/**
	 * Instantiates a new list of ip access control lists
	 *
	 * @param client the client
	 */
	public IpAccessControlListList(TwilioRestClient client) {
		super(client);
	}

	/**
	 * Instantiates a new list of ip access control lists
	 *
	 * @param client the client
	 * @param filters the filters
	 */
	public IpAccessControlListList(TwilioRestClient client, Map<String, String> filters) {
		super(client, filters);
	}

	/* (non-Javadoc)
	 * @see com.twilio.sdk.resource.Resource#getResourceLocation()
	 */
	@Override
	protected String getResourceLocation() {
		return "/" + TwilioRestClient.DEFAULT_VERSION + "/Accounts/"
				+ this.getRequestAccountSid() + "/SIP/IpAccessControlLists.json";
	}

	/* (non-Javadoc)
	 * @see com.twilio.sdk.resource.ListResource#makeNew(com.twilio.sdk.TwilioRestClient, java.util.Map)
	 */
	@Override
	protected IpAccessControlList makeNew(TwilioRestClient client, Map<String, Object> params) {
		return new IpAccessControlList(client, params);
	}

	/* (non-Javadoc)
	 * @see com.twilio.sdk.resource.ListResource#getListKey()
	 */
	@Override
	protected String getListKey() {
		return "ip_access_control_lists";
	}

	/* (non-Javadoc)
	 * @see com.twilio.sdk.resource.factory.IpAccessControlListFactory#create(java.util.Map)
	 */
	public IpAccessControlList create(Map<String, String> params) throws TwilioRestException {
		TwilioRestResponse response = this.getClient().safeRequest(
				this.getResourceLocation(), "POST", params);
		return makeNew(this.getClient(), response.toMap());
	}

}
