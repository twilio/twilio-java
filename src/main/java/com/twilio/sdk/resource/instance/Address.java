package com.twilio.sdk.resource.instance;

import com.twilio.sdk.TwilioRestClient;
import com.twilio.sdk.resource.InstanceResource;
import com.twilio.sdk.resource.list.DependentPhoneNumberList;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

// TODO: Auto-generated Javadoc
/**
 * The Class Address.
 *
 * For more information see <a href="https://www.twilio.com/docs/api/rest/address">https://www.twilio.com/docs/api/rest/address</a>
 */
public class Address extends InstanceResource {

	/** The Constant SID_PROPERTY. */
	private static final String SID_PROPERTY = "sid";

	/**
	 * Instantiates a new conference.
	 *
	 * @param client the client
	 * @param sid the sid
	 */
	public Address(TwilioRestClient client, String sid) {
		super(client);
		if (sid == null) {
			throw new IllegalStateException("The Sid for an Address cannot be null");
		}
		this.setProperty(SID_PROPERTY, sid);
	}

	/**
	 * Instantiates a new address.
	 *
	 * @param client the client
	 * @param properties the properties
	 */
	public Address(TwilioRestClient client, Map<String, Object> properties) {
		super(client, properties);
	}

	/* (non-Javadoc)
	 * @see com.twilio.sdk.resource.Resource#getResourceLocation()
	 */
	@Override
	protected String getResourceLocation() {
		return "/" + TwilioRestClient.DEFAULT_VERSION + "/Accounts/"
				+ this.getRequestAccountSid() + "/Addresses/" + this.getSid() + ".json";
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
	 * Gets the friendly name.
	 *
	 * @return the friendly name
	 */
	public String getFriendlyName() {
		return this.getProperty("friendly_name");
	}

	/**
	 * Gets the customer name.
	 *
	 * @return the customer name
	 */
	public String getCustomerName() {
		return this.getProperty("customer_name");
	}

	/**
	 * Gets the street component of the address.
	 *
	 * @return the street number
	 */
	public String getStreet() {
		return this.getProperty("street");
	}

	/**
	 * Gets the city.
	 *
	 * @return the city
	 */
	public String getCity() {
		return this.getProperty("city");
	}

	/**
	 * Gets the region.
	 *
	 * @return the region
	 */
	public String getRegion() {
		return this.getProperty("region");
	}

	/**
	 * Gets the postal code.
	 *
	 * @return the postal code
	 */
	public String getPostalCode() {
		return this.getProperty("postal_code");
	}

	/**
	 * Gets the ISO country code.
	 *
	 * @return the country code
	 */
	public String getIsoCountry() {
		return this.getProperty("iso_country");
	}

	/**
	 * Get the participant list
	 *
	 */
	public DependentPhoneNumberList getDependentPhoneNumbers() {
		DependentPhoneNumberList list = new DependentPhoneNumberList(this.getClient(), this.getSid());
		list.setRequestAccountSid(this.getRequestAccountSid());
		return list;
	}


}
