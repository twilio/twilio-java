package com.twilio.sdk.resource.instance;

import java.util.HashMap;
import java.util.Map;

import com.twilio.sdk.TwilioRestClient;
import com.twilio.sdk.TwilioRestException;
import com.twilio.sdk.TwilioRestResponse;
import com.twilio.sdk.resource.InstanceResource;

public class CallerIdValidation extends InstanceResource {

	/** The Constant SID_PROPERTY. */
	private static final String SID_PROPERTY = "sid";

	/** The properties. */
	private Map<String, Object> properties;

	/** The json keys. */
	private boolean jsonKeys = true;

	public CallerIdValidation(TwilioRestClient client) {
		super(client);
	}

	public CallerIdValidation(TwilioRestClient client, String sid) {
		super(client);
		if (sid == null) {
            throw new IllegalStateException("The Sid for a CallerId cannot be null");
        }
		this.setProperty(SID_PROPERTY, sid);
	}

	public CallerIdValidation(TwilioRestClient client, Map<String, Object> properties) {
		super(client, properties);
	}

	/**
	 * Instantiates a new caller id validation.
	 *
	 * @param response the json API response
	 */
	public CallerIdValidation(TwilioRestClient client, TwilioRestResponse response) {
		super(client);
		this.properties = new HashMap<String, Object>(response.toMap());
		this.jsonKeys = response.isJson();
	}

	/* (non-Javadoc)
	 * @see com.twilio.sdk.resource.Resource#getResourceLocation()
	 */
	@Override
	protected String getResourceLocation() {
		return this.getResourceLocation(".json");
	}

	/**
	 * Gets the resource location.
	 *
	 * @param extension the extension
	 * @return the resource location
	 */
	protected String getResourceLocation(String extension) {
		return "/" + TwilioRestClient.DEFAULT_VERSION + "/Accounts/"
				+ this.getRequestAccountSid() + "/OutgoingCallerIds/" +
				this.getSid() + extension;
    }

	/**
	 * Gets the sid
	 *
	 * @return the sid
	 */
	public String getSid() {
		return getProperty("sid");
	}

	/**
	 * Gets the validation code.
	 *
	 * @return the validation code
	 */
	public String getValidationCode() {
		//TODO better parsing here
		if (this.jsonKeys) {
			return getProperty("validation_code");
		}

		return getProperty("ValidationCode");
	}

	/**
	 * Gets the phone number.
	 *
	 * @return the phone number
	 */
	public String getPhoneNumber() {
		//TODO better parsing here
		if (this.jsonKeys) {
			return getProperty("phone_number");
		}

		return getProperty("PhoneNumber");
	}

	/**
	 * Gets the property.
	 *
	 * @param name the name
	 * @return the property
	 */
	public String getProperty(String name) {
		Object prop = properties.get(name);
		prop = properties.get(name);

		if (prop == null) {
			throw new IllegalArgumentException("Property " + name
					+ " does not exist");
		}

		if (prop instanceof String) {
			return (String) prop;
		}

		throw new IllegalArgumentException("Property " + name
				+ " is an object.  Use getOjbect() instead.");
	}

	public boolean delete() throws TwilioRestException {
		TwilioRestResponse response = this.getClient().safeRequest(
				this.getResourceLocation(), "DELETE", null);

		return !response.isError();
	}
}
