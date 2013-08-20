package com.twilio.sdk.resource.instance;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import com.twilio.sdk.TwilioRestClient;
import com.twilio.sdk.TwilioRestException;
import com.twilio.sdk.TwilioRestResponse;
import com.twilio.sdk.resource.InstanceResource;
import com.twilio.sdk.resource.Address;
import com.twilio.sdk.list.AddressList;

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
        addressList.setRequestAccountSid(this.getAccountSid());
        return addressList;
    }

    /**
     * Gets the ip addresses on this list.
     *
     * @return an AddressList of the addresses on this list
     */
    public Address getAddress(String addressSid) {
        Address address = new AddressList(this.getClient(), this.getSid(), addressSid);
        address.setRequestAccountSid(this.getAccountSid());
        return address;
    }

    /**
     * Updates this IpAccessControlList.
     *
     * @return true, if successful
     */
    public boolean update() throws TwilioRestException {
        TwilioRestResponse response = this.getClient().safeRequest(
                this.getResourceLocation(), "POST", this.properties);

        return !response.isError();
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
                this.getResourceLocation(), "DELETE", null);

        return !response.isError();
    }
}
