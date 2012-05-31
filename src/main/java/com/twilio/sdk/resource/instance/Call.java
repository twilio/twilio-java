package com.twilio.sdk.resource.instance;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import com.twilio.sdk.TwilioRestClient;
import com.twilio.sdk.TwilioRestException;
import com.twilio.sdk.TwilioRestResponse;
import com.twilio.sdk.resource.InstanceResource;

// TODO: Auto-generated Javadoc
/**
 * The Class Call.
 * 
 * For more information see <a href="http://www.twilio.com/docs/api/rest/call">http://www.twilio.com/docs/api/rest/call</a>
 */
public class Call extends InstanceResource {
	
	/** The Constant SID_PROPERTY. */
	private static final String SID_PROPERTY = "sid";

	/**
	 * Instantiates a new call.
	 *
	 * @param client the client
	 */
	public Call(TwilioRestClient client) {
		super(client);
	}

	/**
	 * Instantiates a new call.
	 *
	 * @param client the client
	 * @param sid the sid
	 */
	public Call(TwilioRestClient client, String sid) {
		super(client);
		if (sid == null) { 
		    throw new IllegalStateException("The Sid for a Call can not be null");
		}
		this.setProperty(SID_PROPERTY, sid);
	}

	/**
	 * Instantiates a new call.
	 *
	 * @param client the client
	 * @param properties the properties
	 */
	public Call(TwilioRestClient client, Map<String, Object> properties) {
		super(client, properties);
	}

	/* (non-Javadoc)
	 * @see com.twilio.sdk.resource.Resource#getResourceLocation()
	 */
	@Override
	protected String getResourceLocation() {
		return "/" + TwilioRestClient.DEFAULT_VERSION + "/Accounts/"
				+ this.getRequestAccountSid() + "/Calls/" + this.getSid()
				+ ".json";
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
	 * Gets the parent call sid.
	 *
	 * @return the parent call sid
	 */
	public String getParentCallSid() {
		return this.getProperty("parent_call_sid");
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
	 * Gets the to.
	 *
	 * @return the to
	 */
	public String getTo() {
		return this.getProperty("to");
	}

	/**
	 * Gets the from.
	 *
	 * @return the from
	 */
	public String getFrom() {
		return this.getProperty("from");
	}

	/**
	 * Gets the phone number sid.
	 *
	 * @return the phone number sid
	 */
	public String getPhoneNumberSid() {
		return this.getProperty("phone_number_sid");
	}

	/**
	 * Gets the status.
	 *
	 * @return the status
	 */
	public String getStatus() {
		return this.getProperty("status");
	}

	/**
	 * Gets the start time.
	 *
	 * @return the start time
	 */
	public String getStartTime() {
		return this.getProperty("start_time");
	}

	/**
	 * Gets the end time.
	 *
	 * @return the end time
	 */
	public String getEndTime() {
		return this.getProperty("end_time");
	}

	/**
	 * Gets the duration.
	 *
	 * @return the duration
	 */
	public String getDuration() {
		return this.getProperty("duration");
	}

	/**
	 * Gets the price.
	 *
	 * @return the price
	 */
	public String getPrice() {
		return this.getProperty("price");
	}

	/**
	 * Gets the direction.
	 *
	 * @return the direction
	 */
	public String getDirection() {
		return this.getProperty("direction");
	}

	/**
	 * Gets the answered by.
	 *
	 * @return the answered by
	 */
	public String getAnsweredBy() {
		return this.getProperty("answered_by");
	}

	/**
	 * Gets the forwarded from.
	 *
	 * @return the forwarded from
	 */
	public String getForwardedFrom() {
		return this.getProperty("forwarded_from");
	}

	/**
	 * Gets the caller name.
	 *
	 * @return the caller name
	 */
	public String getCallerName() {
		return this.getProperty("caller_name");
	}

	/*
	 * 
	 * Useful functions
	 */
	/**
	 * Redirect.
	 *
	 * @param url the url
	 * @param method the method
	 * @return the call
	 * @throws TwilioRestException the twilio rest exception
	 */
	public Call redirect(String url, String method) throws TwilioRestException {
		Map<String, String> vars = new HashMap<String, String>();
		vars.put("Method", method);
		vars.put("Url", url);
		TwilioRestResponse response = this.getClient().safeRequest(
				this.getResourceLocation(), "POST", vars);

		Call c = new Call(this.getClient(), response.toMap());
		c.setRequestAccountSid(this.getRequestAccountSid());
		return c;
	}

	/**
	 * Hangup.
	 *
	 * @return the call
	 * @throws TwilioRestException the twilio rest exception
	 */
	public Call hangup() throws TwilioRestException {
		Map<String, String> vars = new HashMap<String, String>();
		vars.put("Status", "completed");

		TwilioRestResponse response = this.getClient().safeRequest(
				this.getResourceLocation(), "POST", vars);

		Call c = new Call(this.getClient(), response.toMap());
		c.setRequestAccountSid(this.getRequestAccountSid());
		return c;
	}

	/**
	 * Cancel.
	 *
	 * @return the call
	 * @throws TwilioRestException the twilio rest exception
	 */
	public Call cancel() throws TwilioRestException {
		Map<String, String> vars = new HashMap<String, String>();
		vars.put("Status", "canceled");

		TwilioRestResponse response = this.getClient().safeRequest(
				this.getResourceLocation(), "POST", vars);

		Call c = new Call(this.getClient(), response.toMap());
		c.setRequestAccountSid(this.getRequestAccountSid());
		return c;
	}
}
