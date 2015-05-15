package com.twilio.sdk;

import java.util.HashMap;
import java.util.Map;

import com.twilio.sdk.resource.instance.conversations.Conversation;
import com.twilio.sdk.resource.instance.conversations.Participant;
import com.twilio.sdk.resource.list.conversations.CompletedConversationsList;
import com.twilio.sdk.resource.list.conversations.InProgressConversationsList;
import com.twilio.sdk.resource.list.conversations.ParticipantList;

/**
 * Twilio REST client for Conversations end points
 * 
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
	 * Get a {@link com.twilio.sdk.resource.instance.conversations.Conversation
	 * Conversation} instance by sid
	 *
	 * @param conversationSid
	 *            The 34 character sid starting with CV
	 * @return the Conversation
	 */
	public Conversation getConversation(final String conversationSid) {
		Conversation conversation = new Conversation(this, conversationSid);
		return conversation;
	}

	/**
	 * Gets a list
	 * {@link com.twilio.sdk.resource.list.conversations.CompletedConversationsList
	 * Completed Conversations List}
	 * 
	 * @return list of Completed Conversation
	 */
	public CompletedConversationsList getCompletedConversations() {
		return new CompletedConversationsList(this, new HashMap<String, String>(0));
	}

	/**
	 * Gets a list of filtered
	 * {@link com.twilio.sdk.resource.list.conversations.CompletedConversationsList
	 * Completed Conversations List}
	 * 
	 * @param filters
	 *            the query parameters
	 * @return list of Completed Conversations
	 */
	public CompletedConversationsList getCompletedConversations(Map<String, String> filters) {
		return new CompletedConversationsList(this, filters);
	}

	/**
	 * Gets {@link com.twilio.sdk.resource.list.conversations.InProgressConversationsList
	 * InProgress Conversations List}
	 * 
	 * @return list of In Progress Conversations
	 */
	public InProgressConversationsList getInProgressConversations() {
		return new InProgressConversationsList(this, new HashMap<String, String>(0));
	}

	/**
	 * Gets filtered {@link com.twilio.sdk.resource.list.conversations.InProgressConversationsList
	 * InProgress Conversations List}
	 * 
	 * @param filters the query parameters
	 * @return list of In Progress Conversations
	 */
	public InProgressConversationsList getInProgressConversations(Map<String, String> filters) {
		return new InProgressConversationsList(this, filters);
	}

	/**
	 * Get a {@link com.twilio.sdk.resource.instance.conversations.Participant
	 * Participant} instance by conversation sid and participant sid
	 *
	 * @param conversationSid
	 *            the 34 character sid starting with CV
	 * @param participantSid
	 *            the 34 character sid starting with PA
	 * @return the Participant
	 */
	public Participant getParticipant(final String conversationSid, final String participantSid) {
		Participant participant = new Participant(this, conversationSid, participantSid);
		return participant;
	}

	/**
	 * Gets {@link com.twilio.sdk.resource.list.conversations.ParticipantList
	 * Participant List} by Conversation Sid
	 * 
	 * @param conversationSid
	 *            the convesation sid
	 * @return list of Participants
	 */
	public ParticipantList getParticipants(String conversationSid) {
		return new ParticipantList(this, new HashMap<String, String>(), conversationSid);
	}

	/**
	 * Gets filtered
	 * {@link com.twilio.sdk.resource.list.conversations.ParticipantList
	 * Participant List} belonging to a Conversation
	 * 
	 * @param filters
	 *            the query parameters
	 * @param conversationSid
	 *            the conversation sid
	 * @return List of Participants
	 */
	public ParticipantList getParticipants(final Map<String, String> filters, String conversationSid) {
		return new ParticipantList(this, filters, conversationSid);
	}
}
