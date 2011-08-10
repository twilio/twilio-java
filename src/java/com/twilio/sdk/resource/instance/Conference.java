package com.twilio.sdk.resource.instance;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

import com.twilio.sdk.TwilioRestClient;
import com.twilio.sdk.resource.InstanceResource;
import com.twilio.sdk.resource.list.ParticipantList;

// TODO: Auto-generated Javadoc
/**
 * The Class Conference.
 * 
 * For more information see {@see <a href="http://www.twilio.com/docs/api/rest/conference">http://www.twilio.com/docs/api/rest/conference}
 */
public class Conference extends InstanceResource {
	
	/** The Constant SID_PROPERTY. */
	private static final String SID_PROPERTY = "sid";

	/**
	 * Instantiates a new conference.
	 *
	 * @param client the client
	 */
	public Conference(TwilioRestClient client) {
		super(client);
	}

	/**
	 * Instantiates a new conference.
	 *
	 * @param client the client
	 * @param sid the sid
	 */
	public Conference(TwilioRestClient client, String sid) {
		super(client);
		this.setProperty(SID_PROPERTY, sid);
	}

	/**
	 * Instantiates a new conference.
	 *
	 * @param client the client
	 * @param properties the properties
	 */
	public Conference(TwilioRestClient client, Map<String, Object> properties) {
		super(client, properties);
	}

	/* (non-Javadoc)
	 * @see com.twilio.sdk.resource.Resource#getResourceLocation()
	 */
	@Override
	protected String getResourceLocation() {
		return "/" + TwilioRestClient.DEFAULT_VERSION + "/Accounts/"
				+ this.getRequestAccountSid() + "/Conferences/" + this.getSid() + ".json";
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
	 * Gets the friendly name.
	 *
	 * @return the friendly name
	 */
	public String getFriendlyName() {
		return this.getProperty("friendly_name");
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
	 * Get the participant list
	 * 
	 * @return
	 */
	public ParticipantList getParticipants() {
		ParticipantList list = new ParticipantList(this.getClient(), this.getSid());
		list.setRequestAccountSid(this.getRequestAccountSid());
		return list;
	}
	
}
