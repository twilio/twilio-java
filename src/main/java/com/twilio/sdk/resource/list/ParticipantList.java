package com.twilio.sdk.resource.list;

import java.util.Map;

import com.twilio.sdk.TwilioRestClient;
import com.twilio.sdk.resource.ListResource;
import com.twilio.sdk.resource.instance.Participant;

// TODO: Auto-generated Javadoc
/**
 * The Class ParticipantList.
 * 
 * For more information see {@see <a href="http://www.twilio.com/docs/api/rest/participant">http://www.twilio.com/docs/api/rest/participant}
 */
public class ParticipantList extends ListResource<Participant> {
	
	/** The conference sid. */
	private String conferenceSid;

	/**
	 * Instantiates a new participant list.
	 *
	 * @param client the client
	 */
	public ParticipantList(TwilioRestClient client) {
		super(client);
	}

	/**
	 * Instantiates a new participant list.
	 *
	 * @param client the client
	 * @param filters the filters
	 */
	public ParticipantList(TwilioRestClient client, Map<String, String> filters) {
		super(client, filters);
	}

	/**
	 * Instantiates a new participant list.
	 *
	 * @param client the client
	 * @param conferenceSid the conference sid
	 */
	public ParticipantList(TwilioRestClient client, String conferenceSid) {
		super(client);
		this.conferenceSid = conferenceSid;
	}

	/* (non-Javadoc)
	 * @see com.twilio.sdk.resource.Resource#getResourceLocation()
	 */
	@Override
	protected String getResourceLocation() {
		return "/" + TwilioRestClient.DEFAULT_VERSION + "/Accounts/"
				+ this.getRequestAccountSid() + "/Conferernces/"
				+ this.conferenceSid + "/Participants.json";
	}

	/* (non-Javadoc)
	 * @see com.twilio.sdk.resource.ListResource#makeNew(com.twilio.sdk.TwilioRestClient, java.util.Map)
	 */
	@Override
	protected Participant makeNew(TwilioRestClient client,
			Map<String, Object> params) {
		return new Participant(client, params);
	}

	/* (non-Javadoc)
	 * @see com.twilio.sdk.resource.ListResource#getListKey()
	 */
	@Override
	protected String getListKey() {
		return "participants";
	}
}
