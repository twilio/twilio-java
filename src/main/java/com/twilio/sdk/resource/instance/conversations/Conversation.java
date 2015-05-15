package com.twilio.sdk.resource.instance.conversations;

import java.util.Calendar;
import java.util.Map;

import com.twilio.sdk.TwilioConversationsClient;
import com.twilio.sdk.resource.NextGenInstanceResource;

/**
 * Represents a Conversation.
 * <p/>
 * See <a href="https://www.twilio.com/docs/conversations">the Conversations documentation</a>.
 */
public class Conversation extends NextGenInstanceResource<TwilioConversationsClient> {

	/**
	 * Instantiates a Conversation
	 *
	 * @param client the client
	 */
	public Conversation(final TwilioConversationsClient client) {
		super(client);
	}

	/**
	 * Instantiates a Conversation
	 *
	 * @param client the client
	 * @param properties the properties
	 */
	public Conversation(final TwilioConversationsClient client, final Map<String, Object> properties) {
		super(client, properties);
	}

	/**
	 * Instantiates a Conversation
	 *
	 * @param client the client
	 * @param conversationSid the conversation sid
	 */
	public Conversation(final TwilioConversationsClient client, final String conversationSid) {
		super(client);
		if (conversationSid == null || "".equals(conversationSid)) {
			throw new IllegalArgumentException("The conversationSid cannot be null");
		}
		setProperty(SID_PROPERTY, conversationSid);
	}

	/**
	 * Gets the sid.
	 *
	 * @return the sid
	 */
	public String getSid() {
		return getProperty(SID_PROPERTY);
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
	 * The {@link com.twilio.sdk.resource.instance.Account} owning this Conversation.
	 *
	 * @return the account sid
	 */
	public String getAccountSid() {
		return getProperty("account_sid");
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
	 * URL of this Conversation in Twilio REST API.
	 *
	 * @return resource URL
	 */
	public String getUrl() {
		return getProperty("url");
	}

	/**
	 * Gets the participants url
	 *
	 * @return the participants url
	 */
	@SuppressWarnings("unchecked")
	public String getParticipantsUrl() {
		Map<String, String> links = (Map<String, String>) getObject("links");
		return links.get("participants");
	}

	@Override
	protected String getResourceLocation() {
		return "/" + TwilioConversationsClient.DEFAULT_VERSION + "/Conversations/" + getSid();
	}
}
