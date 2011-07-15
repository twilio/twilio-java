package com.twilio.sdk.resource.instance;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

import com.twilio.sdk.TwilioRestClient;
import com.twilio.sdk.TwilioRestException;
import com.twilio.sdk.TwilioRestResponse;
import com.twilio.sdk.resource.InstanceResource;

// TODO: Auto-generated Javadoc
/**
 * The Class Notification.
 * 
 * For more information see {@see <a href="http://www.twilio.com/docs/api/rest/notification">http://www.twilio.com/docs/api/rest/notification}
 */
public class Notification extends InstanceResource {
	
	/** The Constant SID_PROPERTY. */
	private static final String SID_PROPERTY = "sid";

	/**
	 * Instantiates a new notification.
	 *
	 * @param client the client
	 */
	public Notification(TwilioRestClient client) {
		super(client);
	}

	/**
	 * Instantiates a new notification.
	 *
	 * @param client the client
	 * @param sid the sid
	 */
	public Notification(TwilioRestClient client, String sid) {
		super(client);
		this.setProperty(SID_PROPERTY, sid);
	}

	/**
	 * Instantiates a new notification.
	 *
	 * @param client the client
	 * @param properties the properties
	 */
	public Notification(TwilioRestClient client, Map<String, Object> properties) {
		super(client, properties);
	}

	/* (non-Javadoc)
	 * @see com.twilio.sdk.resource.Resource#getResourceLocation()
	 */
	@Override
	protected String getResourceLocation() {
		return "/" + TwilioRestClient.DEFAULT_VERSION + "/Accounts/"
				+ this.getRequestAccountSid() + "/Notifications/" + this.getSid() + ".json";
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
	 * Gets the call sid.
	 *
	 * @return the call sid
	 */
	public String getCallSid() {
		return this.getProperty("call_sid");
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
	 * Gets the log.
	 *
	 * @return the log
	 */
	public String getLog() {
		return this.getProperty("log");
	}
	
	/**
	 * Gets the error code.
	 *
	 * @return the error code
	 */
	public String getErrorCode() {
		return this.getProperty("error_code");
	}
	
	/**
	 * Gets the more info.
	 *
	 * @return the more info
	 */
	public String getMoreInfo() {
		return this.getProperty("more_info");
	}
	
	/**
	 * Gets the message text.
	 *
	 * @return the message text
	 */
	public String getMessageText() {
		return this.getProperty("message_text");
	}
	
	/**
	 * Gets the message date.
	 *
	 * @return the message date
	 */
	public String getMessageDate() {
		return this.getProperty("message_date");
	}
	
	/**
	 * Gets the request url.
	 *
	 * @return the request url
	 */
	public String getRequestUrl() {
		return this.getProperty("request_url");
	}
	
	/**
	 * Gets the request variables.
	 *
	 * @return the request variables
	 */
	public String getRequestVariables() {
		return this.getProperty("request_variables");
	}
	
	/**
	 * Gets the response headers.
	 *
	 * @return the response headers
	 */
	public String getResponseHeaders() {
		return this.getProperty("response_headers");
	}
	
	/**
	 * Gets the response body.
	 *
	 * @return the response body
	 */
	public String getResponseBody() {
		return this.getProperty("response_body");
	}
	
	/**
	 * Delete.
	 *
	 * @return true, if successful
	 * @throws TwilioRestException the twilio rest exception
	 */
	public boolean delete() throws TwilioRestException {
		TwilioRestResponse response = this.getClient().safeRequest(
				this.getResourceLocation(), "DELETE", null);

		return !response.isError();
	}
}