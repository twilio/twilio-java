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
 * The Class Participant.
 * 
 * For more information see {@see <a href="http://www.twilio.com/docs/api/rest/participant">http://www.twilio.com/docs/api/rest/participant}
 */
public class Participant extends InstanceResource {
	// private static final String SID_PROPERTY = "sid";
	/** The Constant CALL_SID_PROPERTY. */
	private static final String CALL_SID_PROPERTY = "call_sid";
	
	/** The Constant CONFERENCE_SID_PROPERTY. */
	private static final String CONFERENCE_SID_PROPERTY = "conference_sid";

	/**
	 * Instantiates a new participant.
	 *
	 * @param client the client
	 */
	public Participant(TwilioRestClient client) {
		super(client);
	}

	/**
	 * Instantiates a new participant.
	 *
	 * @param client the client
	 * @param conferenceSid the conference sid
	 * @param callSid the call sid
	 */
	public Participant(TwilioRestClient client, String conferenceSid,
			String callSid) {
		super(client);
		this.setProperty(CONFERENCE_SID_PROPERTY, conferenceSid);
		this.setProperty(CALL_SID_PROPERTY, callSid);
	}

	/**
	 * Instantiates a new participant.
	 *
	 * @param client the client
	 * @param properties the properties
	 */
	public Participant(TwilioRestClient client, Map<String, Object> properties) {
		super(client, properties);
	}

	/* (non-Javadoc)
	 * @see com.twilio.sdk.resource.Resource#getResourceLocation()
	 */
	@Override
	protected String getResourceLocation() {
		return "/" + TwilioRestClient.DEFAULT_VERSION + "/Accounts/"
				+ this.getRequestAccountSid() + "/Conferences/"
				+ this.getConferenceSid() + "/Participants/"
				+ this.getCallSid() + ".json";
	}

	/*
	 * Property getters
	 */
	// public String getSid() {
	// return this.getProperty(SID_PROPERTY);
	// }

	/**
	 * Gets the conference sid.
	 *
	 * @return the conference sid
	 */
	public String getConferenceSid() {
		return this.getProperty(CONFERENCE_SID_PROPERTY);
	}

	/**
	 * Gets the call sid.
	 *
	 * @return the call sid
	 */
	public String getCallSid() {
		return this.getProperty(CALL_SID_PROPERTY);
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
	 * Checks if is muted.
	 *
	 * @return true, if is muted
	 */
	public boolean isMuted() {
		return Boolean.parseBoolean(this.getProperty("muted"));
	}
	
	/**
	 * Checks if is start conference on enter.
	 *
	 * @return true, if is start conference on enter
	 */
	public boolean isStartConferenceOnEnter() {
		return Boolean.parseBoolean(this.getProperty("start_conference_on_enter"));
	}
	
	/**
	 * Checks if is end conference on exit.
	 *
	 * @return true, if is end conference on exit
	 */
	public boolean isEndConferenceOnExit() {
		return Boolean.parseBoolean(this.getProperty("end_conference_on_exit"));
	}
	
	/*
	 * Helper functions
	 * 
	 * 
	 */
	
	
	/**
	 * Mute.
	 *
	 * @return the participant
	 * @throws TwilioRestException the twilio rest exception
	 */
	public Participant mute() throws TwilioRestException {
		Map<String, String> vars = new HashMap<String, String>();
		vars.put("Muted", "true");

		TwilioRestResponse response = this.getClient().safeRequest(
				this.getResourceLocation(), "POST", vars);

		return new Participant(this.getClient(), response.toMap());
	}

	/**
	 * Unmute.
	 *
	 * @return the participant
	 * @throws TwilioRestException the twilio rest exception
	 */
	public Participant unmute() throws TwilioRestException {
		Map<String, String> vars = new HashMap<String, String>();
		vars.put("Muted", "false");

		TwilioRestResponse response = this.getClient().safeRequest(
				this.getResourceLocation(), "POST", vars);

		return new Participant(this.getClient(), response.toMap());
	}
	
	

	/**
	 * Kick.
	 *
	 * @return true, if successful
	 * @throws TwilioRestException the twilio rest exception
	 */
	public boolean kick() throws TwilioRestException {
		TwilioRestResponse response = this.getClient().safeRequest(
				this.getResourceLocation(), "DELETE", null);

		return !response.isError();
	}
}
