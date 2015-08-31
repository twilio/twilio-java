package com.twilio.sdk.resource;

import com.twilio.sdk.TwilioClient;
import com.twilio.sdk.TwilioRestException;
import com.twilio.sdk.TwilioRestResponse;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.apache.commons.lang3.time.FastDateFormat;
import org.apache.http.NameValuePair;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TimeZone;

// TODO: Auto-generated Javadoc
/**
 * The Class InstanceResource.
 */
public abstract class InstanceResource<C extends TwilioClient> extends Resource<C> {

	protected static final String DATE_CREATED_PROPERTY = "date_created";

	protected static final String DATE_UPDATED_PROPERTY = "date_updated";

	protected static final FastDateFormat ISO_DATE_FORMAT = FastDateFormat.getInstance("yyyy-MM-dd", TimeZone.getTimeZone("GMT"));

    /** The Constant SID_PROPERTY. */
	protected static final String SID_PROPERTY = "sid";

	/** The properties. */
	private Map<String, Object> properties;

	/**
	 * Instantiates a new instance resource.
	 *
	 * @param client the client
	 */
	public InstanceResource(final C client) {
		super(client);
		properties = new HashMap<String, Object>(0);
		filters = new HashMap<String, String>(0);
	}

	/**
	 * Instantiates a new instance resource.
	 *
	 * @param client the client
	 * @param properties the properties
	 */
	public InstanceResource(final C client, final Map<String, Object> properties) {
		super(client);
		filters = new HashMap<String, String>(0);
		if (properties != null && !properties.isEmpty()) {
			this.properties = new HashMap<String, Object>(properties);
			setLoaded(true);
		} else {
			this.properties = new HashMap<String, Object>(0);
		}
	}

	/**
	 * Instantiates a new instance resource.
	 *
	 * @param client the client
	 * @param properties the properties
	 * @param filters the filters
	 */
	public InstanceResource(final C client, final Map<String, Object> properties, final Map<String, String> filters) {
		super(client);
		this.filters = new HashMap<String, String>(filters);
		if (properties != null && !properties.isEmpty()) {
			this.properties = new HashMap<String, Object>(properties);
			setLoaded(true);
		} else {
			this.properties = new HashMap<String, Object>(0);
		}
	}

	private Object getAndLoadIfNecessary(final String name) {
		Object prop = properties.get(name);

		if (prop == null && !isLoaded()) {
			try {
				load(filters);
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
	 * Gets the property as a Date.
	 * @param name the property name
	 * @return the formatted date object from the string
	 */
	protected Date getDateProperty(String name) {
		return parseDateWithFormat(getProperty(name), DateFormatUtils.SMTP_DATETIME_FORMAT);
	}

	/**
	 * Gets the property as a Date.
	 * @param name the property name
	 * @param format the date format
	 * @return the formatted date object from the string
	 */
	protected Date getDateProperty(String name, FastDateFormat format) {
		return parseDateWithFormat(getProperty(name), format);
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
	 * Given a date string, attempt to parse it using the provided {@link FastDateFormat}
	 * @param inDate the date to parse
	 * @param format the {@link FastDateFormat} to parse the string with
	 * @return the parsed {@link Date}
	 */
	protected Date parseDateWithFormat(String inDate, FastDateFormat format) {
		if (inDate == null) {
			return null;
		}
		if (format == null) {
			throw new IllegalArgumentException("Date format must be supplied");
		}
		try {
			return format.parse(inDate);
		} catch (ParseException e) {
			return null;
		}
	}

}
