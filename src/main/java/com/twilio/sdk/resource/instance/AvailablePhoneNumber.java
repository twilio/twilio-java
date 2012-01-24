package com.twilio.sdk.resource.instance;

import java.util.Map;

import com.twilio.sdk.TwilioRestClient;
import com.twilio.sdk.resource.InstanceResource;

// TODO: Auto-generated Javadoc
/**
 * The Class AvailablePhoneNumber.
 * 
 * For more information see {@see <a href="http://www.twilio.com/docs/api/rest/available-phone-numbers">http://www.twilio.com/docs/api/rest/available-phone-numbers}
 */
public class AvailablePhoneNumber extends InstanceResource {
//	private static final String SID_PROPERTY = "sid";

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
		throw new IllegalStateException("AvailablePhoneNumbers do not have an instance reource location");
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
}

