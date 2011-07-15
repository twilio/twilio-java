package com.twilio.sdk.resource.instance;

import java.util.HashMap;
import java.util.Map;

import com.twilio.sdk.TwilioRestResponse;

public class CallerIdValidation {
	
	/** The properties. */
	private Map<String, Object> properties;
	
	/** The json keys. */
	private boolean jsonKeys = true;
	
	/**
	 * Instantiates a new caller id validation.
	 *
	 * @param response the response
	 */
	public CallerIdValidation(TwilioRestResponse response) {
		this.properties = new HashMap<String, Object>(response.toMap());
		this.jsonKeys = response.isJson();
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
}
