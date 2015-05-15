package com.twilio.sdk.resource.instance.conversations;

import java.util.Calendar;
import java.util.Map;

import com.twilio.sdk.TwilioConversationsClient;
import com.twilio.sdk.resource.NextGenInstanceResource;

/**
 * Represents a Conversation Participant.
 */
public class Participant extends NextGenInstanceResource<TwilioConversationsClient> {

	private static final String CONVERSATION_SID = "conversation_sid";

	/**
	 * Instantiates a Participant
	 *
	 * @param client the client
	 */
	public Participant(final TwilioConversationsClient client) {
		super(client);
	}

	/**
	 * Instantiates a Participant
	 *
	 * @param client the client
	 * @param properties the properties
	 */
	public Participant(final TwilioConversationsClient client, final Map<String, Object> properties) {
		super(client, properties);
	}

	/**
	 * Instantiates a Participant
	 *
	 * @param client the client
	 * @param conversationSid the conversation sid
	 * @param participantSid the participant sid
	 */
	public Participant(final TwilioConversationsClient client, final String conversationSid, final String participantSid) {
		super(client);
		if (conversationSid == null || "".equals(conversationSid)) {
			throw new IllegalArgumentException("The conversationSid cannot be null");
		}
		setProperty(CONVERSATION_SID, conversationSid);
		setProperty(SID_PROPERTY, participantSid);
	}

	/**
	 * Gets the sid
	 *
	 * @return the sid
	 */
	public String getSid() {
		return getProperty(SID_PROPERTY);
	}

	/**
	 * Gets the Address
	 * 
	 * @return the Address
	 */
	public String getAddress() {
		return getProperty("address");
	}

	/**
	 * Gets the status
	 * 
	 * @return the status
	 */
	public String getStatus() {
		return getProperty("status");
	}

	/**
	 * The {@link com.twilio.sdk.resource.instance.conversations.Conversation} Conversation this Participant belong to
	 *
	 * @return the conversation sid
	 */
	public String getConversationSid() {
		return getProperty("conversation_sid");
	}

	/**
	 * Gets the created date
	 *
	 * @return Calendar representing created date
	 */
	public Calendar getDateCreated() {
		return parseCalendar(getProperty("date_created"));
	}

	/**
	 * Get the start time
	 *
	 * @return Calendar start time
	 */
	public Calendar getStartTime() {
		return parseCalendar(getProperty("start_time"));
	}

	/**
	 * Gets the end time
	 *
	 * @return Calendar representing end time
	 */
	public Calendar getEndTime() {
		return parseCalendar(getProperty("end_time"));
	}

	/**
	 * Gets the Duration
	 *
	 * @return the duration
	 */
	public Integer getDuration() {
		return getPropertyAsInteger("duration");
	}

	/**
	 * The {@link com.twilio.sdk.resource.instance.Account} Account sid of this Participant
	 *
	 * @return the account sid
	 */
	public String getAccountSid() {
		return getProperty("account_sid");
	}

	/**
	 * URL of this Participant
	 *
	 * @return resource URL
	 */
	public String getUrl() {
		return getProperty("url");
	}

	@Override
	protected String getResourceLocation() {
		return "/" + TwilioConversationsClient.DEFAULT_VERSION
				+ "/Conversations/" + getConversationSid() + "/Participants/" + getSid();
	}
}
