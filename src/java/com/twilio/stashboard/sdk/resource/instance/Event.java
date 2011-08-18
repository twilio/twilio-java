/**
 * 
 */
package com.twilio.stashboard.sdk.resource.instance;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

import com.twilio.stashboard.sdk.TwilioServiceStatusReadRestClient;
import com.twilio.stashboard.sdk.resource.ServicesInstanceResource;

/**
 * 
 * Created Aug 15, 2011 4:58:46 PM
 * @author sdakara
 */
public class Event extends ServicesInstanceResource {

	/** The Constant URL_PROPERTY. */
	private static final String URL_PROPERTY = "url";
	/** The Constant STATUS_PROPERTY . */
	private static final String STATUS_PROPERTY = "status";
	/** The Constant TIMESTAMP_PROPERTY . */
	private static final String TIMESTAMP_PROPERTY = "timestamp";
	/** The Constant SID_PROPERTY . */
	private static final String SID_PROPERTY = "sid";
	/** The Constant MESSAGE_PROPERTY . */
	private static final String MESSAGE_PROPERTY = "message";
	/** The Constant INFORMATIONAL_PROPERTY . */
	private static final String INFORMATIONAL_PROPERTY = "informational";

	private Service service;
	/**
	 * @param client
	 * @param properties
	 */
	public Event(TwilioServiceStatusReadRestClient client, Map<String, Object> properties) {
		super(client, properties);
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param client
	 */
	public Event(TwilioServiceStatusReadRestClient client, Service service) {
		super(client);
		this.service = service;
	}

	public Status getStatus() {
		Map<String, Object> statusMap = (Map<String, Object>) this
				.getPropertyObject(STATUS_PROPERTY);
		return new Status(getClient(), statusMap);
	}

	public Date getTimeStamp() {
		SimpleDateFormat format = new SimpleDateFormat(
				"EEE, dd MMM yyyy HH:mm:ss Z");
		try {
			return format.parse(this.getProperty(TIMESTAMP_PROPERTY));
		} catch (ParseException e) {
			return null;
		}
	}

	public String getSid() {
		return getProperty(SID_PROPERTY);
	}

	public String getMessage() {
		return getProperty(MESSAGE_PROPERTY);
	}

	public boolean isInformational() {
		return "true".equalsIgnoreCase(getProperty(INFORMATIONAL_PROPERTY)) ? true
				: false;
	}

	public String getUrl() {
		return getProperty(URL_PROPERTY);
	}

	/* (non-Javadoc)
	 * @see com.twilio.sdk.resource.Resource#getResourceLocation()
	 */
	@Override
	protected String getResourceLocation() {
		return "/" + TwilioServiceStatusReadRestClient.DEFAULT_VERSION
				+ "/services/" + this.service.getId() + "/events/"
				+ this.getSid();
	}
}
