package com.twilio.sdk.resource.instance;

import com.twilio.sdk.TwilioRestClient;
import com.twilio.sdk.resource.InstanceResource;

import java.util.Map;

// TODO: Auto-generated Javadoc

/**
 * The Class AvailablePhoneNumber.
 * <p/>
 * For more information see <a href="https://www.twilio.com/docs/api/rest/available-phone-numbers">https://www.twilio.com/docs/api/rest/available-phone-numbers</a>
 */
public class AvailablePhoneNumber extends InstanceResource<TwilioRestClient> {

	/**
	 * Instantiates a new available phone number.
	 *
	 * @param client the client
	 */
	public AvailablePhoneNumber(TwilioRestClient client) {
		super(client);
	}

	/**
	 * Instantiates a new available phone number.
	 *
	 * @param client the client
	 * @param properties the properties
	 */
	public AvailablePhoneNumber(TwilioRestClient client, Map<String, Object> properties) {
		super(client, properties);
	}

	/* (non-Javadoc)
	 * @see com.twilio.sdk.resource.Resource#getResourceLocation()
	 */
	@Override
	protected String getResourceLocation() {
		throw new IllegalStateException("AvailablePhoneNumbers do not have an instance resource location");
	}

	/*
	 * Property getters
	 */

	/**
	 * Gets the friendly name.
	 *
	 * @return the friendly name
	 */
	public String getFriendlyName() {
		return this.getProperty("friendly_name");
	}

	/**
	 * Gets the phone number.
	 *
	 * @return the phone number
	 */
	public String getPhoneNumber() {
		return this.getProperty("phone_number");
	}

	/**
	 * Gets the lata.
	 *
	 * @return the lata
	 */
	public String getLata() {
		return this.getProperty("lata");
	}

	/**
	 * Gets the rate center.
	 *
	 * @return the rate center
	 */
	public String getRateCenter() {
		return this.getProperty("rate_center");
	}

	/**
	 * Gets the latitude.
	 *
	 * @return the latitude
	 */
	public String getLatitude() {
		return this.getProperty("latitude");
	}

	/**
	 * Gets the longitude.
	 *
	 * @return the longitude
	 */
	public String getLongitude() {
		return this.getProperty("longitude");
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
	 * Gets the iso country.
	 *
	 * @return the iso country
	 */
	public String getIsoCountry() {
		return this.getProperty("iso_country");
	}

	/**
	 * Whether this number is new to the Twilio platform.
	 * @return Beta status
	 */
	public boolean getBeta() {
		return (Boolean) getObject("beta");
	}

	/**
	 * Indicates whether this number requires an Address to be on file with Twilio.
	 * Potential values are "any", "local", "foreign", or "none".
	 *
	 * @return the address requirements
	 */
	public String getAddressRequirements() {
		return this.getProperty("address_requirements");
	}
}

