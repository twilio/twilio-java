package com.twilio.sdk.resource.instance.conversations;

import java.util.Calendar;
import java.util.Map;

import com.twilio.sdk.TwilioConversationsClient;
import com.twilio.sdk.resource.NextGenInstanceResource;

/**
 * Represents Conversations.
 * <p/>
 * See <a href="https://www.twilio.com/docs/api/rest/conversations">the Conversations documentation</a>.
 */
public class Conversations extends NextGenInstanceResource<TwilioConversationsClient> {

	/**
	 * Instantiates Conversations.
	 *
	 * @param client the client
	 */
	public Conversations(final TwilioConversationsClient client) {
		super(client);
	}

	/**
	 * Instantiates Conversations.
	 *
	 * @param client the client
	 * @param properties the properties
	 */
	public Conversations(final TwilioConversationsClient client, final Map<String, Object> properties) {
		super(client, properties);
	}

	/**
	 * Instantiates Conversations.
	 *
	 * @param client the client
	 * @param conversationsSid the conversations sid
	 */
	public Conversations(final TwilioConversationsClient client, final String conversationsSid) {
		super(client);
		if (conversationsSid == null || "".equals(conversationsSid)) {
			throw new IllegalArgumentException("The conversationsSid cannot be null");
		}
		setProperty(SID_PROPERTY, conversationsSid);
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
	 * The {@link com.twilio.sdk.resource.instance.Account} owning this Conversations.
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
	 * URL of this Conversations in Twilio REST API.
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
