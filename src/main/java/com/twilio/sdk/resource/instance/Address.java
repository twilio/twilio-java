package com.twilio.sdk.resource.instance;

import com.twilio.sdk.TwilioRestClient;
import com.twilio.sdk.TwilioRestException;
import com.twilio.sdk.TwilioRestResponse;
import com.twilio.sdk.resource.InstanceResource;
import com.twilio.sdk.resource.list.DependentPhoneNumberList;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

// TODO: Auto-generated Javadoc
/**
 * An Address instance resource.
 *
 * An Address instance resource represents your or your customer’s physical location within a country.
 * Around the world, some local authorities require the name and address of
 * the user to be on file with Twilio to purchase and own a phone number.
 *
 * Addresses are represented by the following properties:
 * - FriendlyName: an optional, user-defined string describing this Address. 64 characters maximum.
 * - CustomerName: You or your customer's business name.
 * - Street: The number and street of this address.
 * - City: The city of this address.
 * - Region: The state or region of the address.
 * - PostalCode: The postal code (zip code).
 * - IsoCountry: The country, in ISO-3166-1 alpha-2 (two-character) format, e.g. "CA" for Canada
 *
 * For more information see <a href="https://www.twilio.com/docs/api/rest/address">https://www.twilio.com/docs/api/rest/address</a>
 */
public class Address extends InstanceResource<TwilioRestClient> {

	/** The Constant SID_PROPERTY. */
	private static final String SID_PROPERTY = "sid";

	/**
	 * Instantiates a new Address.
	 *
	 * @param client the client
	 * @param sid the sid
	 */
	public Address(final TwilioRestClient client, final String sid) {
		super(client);
		if (sid == null) {
			throw new IllegalStateException("The Sid for an Address cannot be null");
		}
		setProperty(SID_PROPERTY, sid);
	}

	/**
	 * Instantiates a new address.
	 *
	 * @param client the client
	 * @param properties the properties
	 */
	public Address(final TwilioRestClient client, final Map<String, Object> properties) {
		super(client, properties);
	}

	/* (non-Javadoc)
	 * @see com.twilio.sdk.resource.Resource#getResourceLocation()
	 */
	@Override
	protected String getResourceLocation() {
		return "/" + TwilioRestClient.DEFAULT_VERSION + "/Accounts/"
				+ getRequestAccountSid() + "/Addresses/" + getSid() + ".json";
	}

	/*
	 * Property getters
	 */
	/**
	 * A unique identifier for this Address.
	 *
	 * @return the sid
	 */
	public String getSid() {
		return getProperty(SID_PROPERTY);
	}

	/**
	 * The date/time the Address resource was created.
	 *
	 * @return the date created
	 */
	public Date getDateCreated() {
		SimpleDateFormat format = new SimpleDateFormat(
				"EEE, dd MMM yyyy HH:mm:ss Z");
		try {
			return format.parse(getProperty("date_created"));
		} catch (final ParseException e) {
			return null;
		}
	}

	/**
	 * Date/time this Address was last updated.
	 *
	 * @return the date updated
	 */
	public Date getDateUpdated() {
		SimpleDateFormat format = new SimpleDateFormat(
				"EEE, dd MMM yyyy HH:mm:ss Z");
		try {
			return format.parse(getProperty("date_updated"));
		} catch (final ParseException e) {
			return null;
		}
	}

	/**
	 * Identifier for the account owning this Address.
	 *
	 * @return the account sid
	 */
	public String getAccountSid() {
		return getProperty("account_sid");
	}

	/**
	 * An optional user-defined string describing this Address.
	 *
	 * @return the friendly name
	 */
	public String getFriendlyName() {
		return getProperty("friendly_name");
	}

	/**
	 * You or your customer's business name for this Address.
	 *
	 * @return the customer name
	 */
	public String getCustomerName() {
		return getProperty("customer_name");
	}

	/**
	 * The number and street of this Address.
	 *
	 * @return the street number
	 */
	public String getStreet() {
		return getProperty("street");
	}

	/**
	 * The city for this Address.
	 *
	 * @return the city
	 */
	public String getCity() {
		return getProperty("city");
	}

	/**
	 * The state or region for this Address.
	 *
	 * @return the region
	 */
	public String getRegion() {
		return getProperty("region");
	}

	/**
	 * The postal or zip code for this Address.
	 *
	 * @return the postal code
	 */
	public String getPostalCode() {
		return getProperty("postal_code");
	}

	/**
	 * The country for this address, specified in ISO 3166-1 alpha-2 (two characters) format.
	 *
	 * @return the country code
	 */
	public String getIsoCountry() {
		return getProperty("iso_country");
	}

	/**
	 * A list of phone numbers owned by this Address's account that depend on it
	 * to satisfy legal requirements. If this list is non-empty, the Address cannot
	 * be deleted until another Address(es) satisfying the requirements for these numbers
	 * is created.
	 *
	 */
	public DependentPhoneNumberList getDependentPhoneNumbers() {
		DependentPhoneNumberList list = new DependentPhoneNumberList(getClient(), getSid());
		list.setRequestAccountSid(getRequestAccountSid());
		return list;
	}

    public boolean delete() throws TwilioRestException {
        TwilioRestResponse response = getClient().safeRequest(getResourceLocation(), "DELETE", (Map) null);

        return !response.isError();
    }


}
