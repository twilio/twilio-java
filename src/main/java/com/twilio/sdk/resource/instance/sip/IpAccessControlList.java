package com.twilio.sdk.resource.instance.sip;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import com.twilio.sdk.TwilioRestClient;
import com.twilio.sdk.TwilioRestException;
import com.twilio.sdk.TwilioRestResponse;
import com.twilio.sdk.resource.InstanceResource;
import com.twilio.sdk.resource.factory.sip.AddressFactory;
import com.twilio.sdk.resource.instance.sip.Address;
import com.twilio.sdk.resource.list.sip.AddressList;

public class IpAccessControlList extends InstanceResource {

	/** The Constant SID_PROPERTY. */
	private static final String SID_PROPERTY = "sid";

	/**
	 * Instantiates a new IpAccessControlList.
	 *
	 * @param client the client
	 */
	public IpAccessControlList(TwilioRestClient client) {
		super(client);
	}

	/**
	 * Instantiates a new IpAccessControlList.
	 *
	 * @param client the client
	 * @param sid the sid
	 */
	public IpAccessControlList(TwilioRestClient client, String sid) {
		super(client);
		if (sid == null) {
			throw new IllegalStateException("The Sid for a IpAccessControlList can not be null");
		}
		this.setProperty(SID_PROPERTY, sid);
	}

	/**
	 * Instantiates a new IpAccessControlList.
	 *
	 * @param client the client
	 * @param properties the properties
	 */
	public IpAccessControlList(TwilioRestClient client, Map<String, Object> properties) {
		super(client, properties);
	}

	/* (non-Javadoc)
	 * @see com.twilio.sdk.resource.Resource#getResourceLocation()
	 */
	@Override
		protected String getResourceLocation() {
			return "/" + TwilioRestClient.DEFAULT_VERSION + "/Accounts/"
				+ this.getRequestAccountSid() + "/SIP/IpAccessControlLists/" + this.getSid()
				+ ".json";
		}

	/*
	 * Property getters
	 */

	/**
	 * Gets the sid.
	 *
	 * @return the sid
	 */
	public String getSid() {
		return this.getProperty(SID_PROPERTY);
	}

	/**
	 * Gets the date created.
	 *
	 * @return the date created
	 */
	public Date getDateCreated() {
		SimpleDateFormat format = new SimpleDateFormat(
				"EEE, dd MMM yyyy HH:mm:ss Z");
		try {
			return format.parse(this.getProperty("date_created"));
		} catch (ParseException e) {
			return null;
		}
	}

	/**
	 * Gets the date updated.
	 *
	 * @return the date updated
	 */
	public Date getDateUpdated() {
		SimpleDateFormat format = new SimpleDateFormat(
				"EEE, dd MMM yyyy HH:mm:ss Z");
		try {
			return format.parse(this.getProperty("date_updated"));
		} catch (ParseException e) {
			return null;
		}
	}

	/**
	 * Gets the account sid.
	 *
	 * @return the account sid
	 */
	public String getAccountSid() {
		return this.getProperty("account_sid");
	}

	/**
	 * Gets the friendly name
	 *
	 * @return the friendly name
	 */
	public String getFriendlyName() {
		return this.getProperty("friendly_name");
	}

	/**
	 * Gets the ip addresses on this list.
	 *
	 * @return an AddressList of the addresses on this list
	 */
	public AddressList getAddresses() {
		AddressList addressList = new AddressList(this.getClient(), this.getSid());
		addressList.setRequestAccountSid(this.getRequestAccountSid());
		return addressList;
	}

	/**
	 * Gets the address factory, which lets you make new addresses.
	 *
	 * @return an AddressList of the addresses on this list
	 */
	public AddressFactory getAddressFactory() {
		return this.getAddresses();
	}

	/**
	 * Gets the ip addresses on this list.
	 *
	 * @return an AddressList of the addresses on this list
	 */
	public Address getAddress(String addressSid) {
		Address address = new Address(this.getClient(), this.getSid(), addressSid);
		address.setRequestAccountSid(this.getRequestAccountSid());
		return address;
	}

	/**
	 * Delete this {@link IpAccessControlList}.
	 * @throws TwilioRestException
	 *             if there is an error in the request
	 * @return true, if successful
	 *
	 */
	public boolean delete() throws TwilioRestException {
		TwilioRestResponse response = this.getClient().safeRequest(
				this.getResourceLocation(), "DELETE", (Map) null);

		return !response.isError();
	}
}
