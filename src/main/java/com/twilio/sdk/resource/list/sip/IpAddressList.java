package com.twilio.sdk.resource.list.sip;

import com.twilio.sdk.TwilioRestClient;
import com.twilio.sdk.TwilioRestException;
import com.twilio.sdk.TwilioRestResponse;
import com.twilio.sdk.resource.ListResource;
import com.twilio.sdk.resource.factory.sip.IpAddressFactory;
import com.twilio.sdk.resource.instance.sip.IpAddress;
import org.apache.http.NameValuePair;

import java.util.List;
import java.util.Map;

public class IpAddressList extends ListResource<IpAddress, TwilioRestClient> implements IpAddressFactory {

    private String ipAccessControlListSid;

	/**
	 * Instantiates a new IpAddress list
	 *
	 * @param client the client
	 */
	public IpAddressList(TwilioRestClient client) {
		super(client);
	}

	/**
	 * Instantiates a new IpAddress list
	 *
	 * @param client the client
	 * @param ipAccessControlListSid ip access control list sid
	 */
	public IpAddressList(TwilioRestClient client, String ipAccessControlListSid) {
		super(client);
        if (ipAccessControlListSid == null) {
            throw new IllegalStateException("The Sid for an ip access control list can not be null");
        }
        this.ipAccessControlListSid = ipAccessControlListSid;
	}

	/**
	 * Instantiates a new IpAddress list
	 *
	 * @param client the client
	 * @param filters the filters
	 */
	public IpAddressList(TwilioRestClient client, Map<String, String> filters) {
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
            + "/IpAddresses"
            + ".json";
	}

	/* (non-Javadoc)
	 * @see com.twilio.sdk.resource.ListResource#makeNew(com.twilio.sdk.TwilioRestClient, java.util.Map)
	 */
	@Override
	protected IpAddress makeNew(TwilioRestClient client, Map<String, Object> params) {
		return new IpAddress(client, params);
	}

	/* (non-Javadoc)
	 * @see com.twilio.sdk.resource.ListResource#getListKey()
	 */
	@Override
	protected String getListKey() {
		return "ip_addresses";
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
	public IpAddress create(Map<String, String> params) throws TwilioRestException {
		TwilioRestResponse response = this.getClient().safeRequest(
				this.getResourceLocation(), "POST", params);
		return makeNew(this.getClient(), response.toMap());
	}

	/* (non-Javadoc)
	 * @see com.twilio.sdk.resource.factory.IpAccessControlListFactory#create(java.util.List)
	 */
	public IpAddress create(List<NameValuePair> params) throws TwilioRestException {
		TwilioRestResponse response = this.getClient().safeRequest(
				this.getResourceLocation(), "POST", params);
		return makeNew(this.getClient(), response.toMap());
	}

}
