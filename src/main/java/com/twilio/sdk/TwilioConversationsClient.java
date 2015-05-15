package com.twilio.sdk;

import java.util.HashMap;
import java.util.Map;

import com.twilio.sdk.resource.instance.conversations.Conversations;
import com.twilio.sdk.resource.list.conversations.CompletedConversationsList;
import com.twilio.sdk.resource.list.conversations.InProgressConversationsList;

/**
 * Twilio REST client for Conversations end points
 */
public class TwilioConversationsClient extends TwilioClient {

	public static final String DEFAULT_VERSION = "v1";

	public TwilioConversationsClient(final String accountSid, final String authToken) {
		super(accountSid, authToken, "https://conversations.twilio.com");
	}

	public TwilioConversationsClient(final String accountSid, final String authToken, final String endpoint) {
		super(accountSid, authToken, endpoint);
	}

	/**
	 * Get a Conversations instance by sid
	 *
	 * @param conversationsSid The 34 character sid starting with CV
	 */
	public Conversations getConversations(final String conversationsSid) {
		Conversations conversations = new Conversations(this, conversationsSid);
		return conversations;
	}

	/**
	 * Gets Completed Conversations
	 */
	public CompletedConversationsList getCompletedConversations() {
		return new CompletedConversationsList(this, new HashMap<String, String>(0));
	}

	/**
	 * Gets filtered Completed Conversations
	 * 
	 * @param filters
	 */
	public CompletedConversationsList getCompletedConversations(Map<String, String> filters) {
		return new CompletedConversationsList(this, filters);
	}

	/**
	 * Gets InProgress Conversations
	 */
	public InProgressConversationsList getInProgressConversations() {
		return new InProgressConversationsList(this, new HashMap<String, String>(0));
	}

	/**
	 * Gets filtered InProgress Conversations
	 * 
	 * @param filters
	 */
	public InProgressConversationsList getInProgressConversations(Map<String, String> filters) {
		return new InProgressConversationsList(this, filters);
	}
}
