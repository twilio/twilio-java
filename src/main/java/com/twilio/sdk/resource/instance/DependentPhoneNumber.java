package com.twilio.sdk.resource.instance;

import com.twilio.sdk.TwilioRestClient;
import com.twilio.sdk.resource.InstanceResource;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

// TODO: Auto-generated Javadoc
/**
 * An incoming phone number that depends on a particular address.
 * Fields match IncomingPhoneNumber.
 *
 *
 * For more information see <a href="https://www.twilio.com/docs/api/rest/address">https://www.twilio.com/docs/api/rest/address</a>
 */
public class DependentPhoneNumber extends InstanceResource {

	/** The Constant SID_PROPERTY. */
	private static final String SID_PROPERTY = "sid";

	/**
	 * Instantiates a new dependent phone number.
	 *
	 * @param client the client
	 */
	public DependentPhoneNumber(TwilioRestClient client) {
		super(client);

	}

	/**
	 * Instantiates a new dependent phone number.
	 *
	 * @param client the client
	 * @param sid the sid
	 */
	public DependentPhoneNumber(TwilioRestClient client, String sid) {
		super(client);
		if (sid == null) {
			throw new IllegalStateException("The Sid for a Conference can not be null");
		}
		this.setProperty(SID_PROPERTY, sid);
	}

	/**
	 * Instantiates a new conference.
	 *
	 * @param client the client
	 * @param properties the properties
	 */
	public DependentPhoneNumber(TwilioRestClient client, Map<String, Object> properties) {
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
	 * Gets the phone number.
	 *
	 * @return the phone number
	 */
	public String getPhoneNumber() {
		return this.getProperty("phone_number");
	}

	/**
	 * Gets the voice application sid.
	 *
	 * @return the voice application sid
	 */
	public String getVoiceApplicationSid() {
		return this.getProperty("voice_application_sid");
	}

	/**
	 * Gets the sms application sid.
	 *
	 * @return the sms application sid
	 */
	public String getSmsApplicationSid() {
		return this.getProperty("sms_application_sid");
	}

	/**
	 * Gets the api version.
	 *
	 * @return the api version
	 */
	public String getApiVersion() {
		return this.getProperty("api_version");
	}

	/**
	 * Gets the voice url.
	 *
	 * @return the voice url
	 */
	public String getVoiceUrl() {
		return this.getProperty("voice_url");
	}

	/**
	 * Gets the voice method.
	 *
	 * @return the voice method
	 */
	public String getVoiceMethod() {
		return this.getProperty("voice_method");
	}

	/**
	 * Gets the voice fallback url.
	 *
	 * @return the voice fallback url
	 */
	public String getVoiceFallbackUrl() {
		return this.getProperty("voice_fallback_url");
	}

	/**
	 * Gets the voice fallback method.
	 *
	 * @return the voice fallback method
	 */
	public String getVoiceFallbackMethod() {
		return this.getProperty("voice_fallback_method");
	}

	/**
	 * Gets the status callback.
	 *
	 * @return the status callback
	 */
	public String getStatusCallback() {
		return this.getProperty("status_callback");
	}

	/**
	 * Gets the status callback method.
	 *
	 * @return the status callback method
	 */
	public String getStatusCallbackMethod() {
		return this.getProperty("status_callback_method");
	}

	/**
	 * Gets the voice caller id lookup.
	 *
	 * @return the voice caller id lookup
	 */
	public String getVoiceCallerIdLookup() {
		return this.getProperty("voice_caller_id_lookup");
	}

	/**
	 * Gets the sms url.
	 *
	 * @return the sms url
	 */
	public String getSmsUrl() {
		return this.getProperty("sms_url");
	}

	/**
	 * Gets the sms method.
	 *
	 * @return the sms method
	 */
	public String getSmsMethod() {
		return this.getProperty("sms_method");
	}

	/**
	 * Gets the sms fallback url.
	 *
	 * @return the sms fallback url
	 */
	public String getSmsFallbackUrl() {
		return this.getProperty("sms_fallback_url");
	}

	/**
	 * Gets the sms fallback method.
	 *
	 * @return the sms fallback method
	 */
	public String getSmsFallbackMethod() {
		return this.getProperty("sms_fallback_method");
	}

	/**
	 * Gets the sms status callback.
	 *
	 * @return the sms status callback
	 */
	public String getSmsStatusCallback() {
		return this.getProperty("sms_status_callback");
	}

	/**
	 * Gets the address requirements.
	 *
	 * @return the address requirements
	 */
	public String getAddressRequirements() {
		return this.getProperty("address_requirements");
	}
}