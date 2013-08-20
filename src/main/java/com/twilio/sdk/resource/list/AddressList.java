package com.twilio.sdk.resource.list;

import java.util.Map;

import com.twilio.sdk.TwilioRestClient;
import com.twilio.sdk.TwilioRestException;
import com.twilio.sdk.TwilioRestResponse;
import com.twilio.sdk.resource.ListResource;
import com.twilio.sdk.resource.factory.AddressFactory;
import com.twilio.sdk.resource.instance.Address;

public class AddressList extends ListResource<Address> implements AddressFactory {

    private String ipAccessControlListSid;

	/**
	 * Instantiates a new address list
	 *
	 * @param client the client
	 */
	public AddressList(TwilioRestClient client) {
		super(client);
	}

	/**
	 * Instantiates a new address list
	 *
	 * @param client the client
	 * @param filters the filters
	 */
	public AddressList(TwilioRestClient client, String ipAccessControlListSid) {
		super(client);
        if (ipAccessControlListSid == null) {
            throw new IllegalStateException("The Sid for an ip access control list can not be null");
        }
        this.ipAccessControlListSid = ipAccessControlListSid;
	}

	/**
	 * Instantiates a new address list
	 *
	 * @param client the client
	 * @param filters the filters
	 */
	public AddressList(TwilioRestClient client, Map<String, String> filters) {
		super(client, filters);
	}

	/* (non-Javadoc)
	 * @see com.twilio.sdk.resource.Resource#getResourceLocation()
	 */
	@Override
	protected String getResourceLocation() {
		return "/" + TwilioRestClient.DEFAULT_VERSION
            + "/Accounts/" + this.getRequestAccountSid()
            + "/SIP/IpAccessControlLists/" + this.getIpAccessControlListSid()
            + ".json";
	}

	/* (non-Javadoc)
	 * @see com.twilio.sdk.resource.ListResource#makeNew(com.twilio.sdk.TwilioRestClient, java.util.Map)
	 */
	@Override
	protected Address makeNew(TwilioRestClient client, Map<String, Object> params) {
		return new Address(client, params);
	}

	/* (non-Javadoc)
	 * @see com.twilio.sdk.resource.ListResource#getListKey()
	 */
	@Override
	protected String getListKey() {
		return "ip_access_control_lists";
	}

	/**
	 * Returns the sid of the containing access control list
     *
     * @return the sid
	 */
	protected String getIpAccessControlListSid() {
		return this.ipAccessControlListSid;
	}

	/* (non-Javadoc)
	 * @see com.twilio.sdk.resource.factory.IpAccessControlListFactory#create(java.util.Map)
	 */
	public Address create(Map<String, String> params) throws TwilioRestException {
		TwilioRestResponse response = this.getClient().safeRequest(
				this.getResourceLocation(), "POST", params);
		return makeNew(this.getClient(), response.toMap());
	}

}
