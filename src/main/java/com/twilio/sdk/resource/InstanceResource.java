package com.twilio.sdk.resource;

import com.twilio.sdk.TwilioRestClient;
import com.twilio.sdk.TwilioRestException;
import com.twilio.sdk.TwilioRestResponse;
import org.apache.http.NameValuePair;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

// TODO: Auto-generated Javadoc
/**
 * The Class InstanceResource.
 */
public abstract class InstanceResource extends Resource {

	/** The properties. */
	private Map<String, Object> properties;

	/**
	 * Instantiates a new instance resource.
	 *
	 * @param client the client
	 */
	public InstanceResource(TwilioRestClient client) {
		super(client);
		this.properties = new HashMap<String, Object>();
	}

	/**
	 * Instantiates a new instance resource.
	 *
	 * @param client the client
	 * @param properties the properties
	 */
	public InstanceResource(TwilioRestClient client,
			Map<String, Object> properties) {
		super(client);
		this.properties = new HashMap<String, Object>(properties);
		this.setLoaded(true);
	}

	private Object getAndLoadIfNecessary(String name) {
		Object prop = properties.get(name);

		if (prop == null && !this.isLoaded()) {
			try {
				this.load(new HashMap<String, String>());
				return properties.get(name);
			} catch (TwilioRestException e) {
				throw new RuntimeException(e);
			}
		}
		return prop;
	}

	/**
	 * Gets the property.
	 *
	 * @param name the name
	 * @return the property,
	 * or null if it doesn't exist or is NULL in the response
	 */
	public String getProperty(String name) {
		Object prop = getAndLoadIfNecessary(name);

		if (prop == null) {
			return null;
		}

		if (prop instanceof String) {
			return (String) prop;
		}

		throw new IllegalArgumentException("Property " + name
				+ " is an object.  Use getObject() instead.");
	}

  /**
   * Gets the property as an Object.
   *
   * @param name the name of the property
   * @return the property as an Object
   */
	public Object getObject(String name) {
		Object prop = getAndLoadIfNecessary(name);

		if (prop == null) {
			throw new IllegalArgumentException("Property " + name
					+ " does not exist");
		}

		return prop;
	}

	/**
	 * Sets the property as an Object
	 *
	 * @param name the name
	 * @param value the value
	 */
	protected void setProperty(String name, Object value) {
		properties.put(name, value);
	}

	/**
	 * Update.
	 *
	 * @param params the params
	 * @throws TwilioRestException the twilio rest exception
	 */
	public void update(Map<String, String> params) throws TwilioRestException {
		this.getClient().safeRequest(this.getResourceLocation(), "POST", params);
	}

	/**
	 * Update.
	 *
	 * @param params the params list
	 * @throws TwilioRestException the twilio rest exception
	 */
	public void update(List<NameValuePair> params) throws TwilioRestException {
		this.getClient().safeRequest(this.getResourceLocation(), "POST", params);
	}

	/* (non-Javadoc)
	 * @see com.twilio.sdk.resource.Resource#parseResponse(com.twilio.sdk.TwilioRestResponse)
	 */
	@Override
		protected void parseResponse(TwilioRestResponse response) {
			Map<String, Object> properties = response.toMap();
			this.properties = new HashMap<String, Object>(properties);
		}

	/**
	 * return a date from the property string
	 *
	 * @return the date value of the input string
	 */
	protected Date parseDate(String inDate) {
		if (inDate==null) {
			return null;
		}
		SimpleDateFormat format = new SimpleDateFormat(
				"EEE, dd MMM yyyy HH:mm:ss Z");
		try {
			return format.parse(inDate);
		} catch (ParseException e) {
			return null;
		}
	}

}
