package com.twilio.sdk.resource.instance;

import com.twilio.sdk.TwilioRestClient;
import com.twilio.sdk.resource.InstanceResource;
import com.twilio.sdk.resource.list.ParticipantList;

import java.util.Date;
import java.util.Map;

// TODO: Auto-generated Javadoc
/**
 * The Class Conference.
 *
 * For more information see <a href="https://www.twilio.com/docs/api/rest/conference">https://www.twilio.com/docs/api/rest/conference</a>
 */
public class Conference extends InstanceResource<TwilioRestClient> {

	/**
	 * Instantiates a new conference.
	 *
	 * @param client the client
	 */
	public Conference(TwilioRestClient client) {
		super(client);

        //Object ac = properties.get("account_sid");
        //if (ac != null && ac instanceof String) {
        //    String accountSid = (String) ac;
        //    this.setRequestAccountSid(accountSid);
        //}
	}

	/**
	 * Instantiates a new conference.
	 *
	 * @param client the client
	 * @param sid the sid
	 */
	public Conference(TwilioRestClient client, String sid) {
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
		return getDateProperty("date_created");
	}

	/**
	 * Gets the date updated.
	 *
	 * @return the date updated
	 */
	public Date getDateUpdated() {
		return getDateProperty("date_updated");
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
	 */
	public ParticipantList getParticipants() {
		ParticipantList list = new ParticipantList(this.getClient(), this.getSid());
		list.setRequestAccountSid(this.getRequestAccountSid());
		return list;
	}

	/**
	 * Retrieve a Participant from a conference
	 */
	public Participant getParticipant(String callSid) {
		Participant participant = new Participant(this.getClient(), this.getSid(), callSid);
		participant.setRequestAccountSid(this.getRequestAccountSid());
		return participant;
	}

}
