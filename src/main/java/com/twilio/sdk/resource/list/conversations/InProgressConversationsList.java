package com.twilio.sdk.resource.list.conversations;

import java.util.Map;

import com.twilio.sdk.TwilioConversationsClient;
import com.twilio.sdk.resource.NextGenListResource;
import com.twilio.sdk.resource.instance.conversations.Conversation;

/**
 * Represents In-Progress Conversations List
 * 
 */
public class InProgressConversationsList extends NextGenListResource<Conversation, TwilioConversationsClient> {

	/**
	 * Initializes InProgressConversationsList
	 * 
	 * @param client the rest client
	 */
	public InProgressConversationsList(TwilioConversationsClient client) {
		super(client);
	}

	/**
	 * Initializes InProgressConversationsList
	 * 
	 * @param client the rest client
	 * @param filters query parameters
	 */
	public InProgressConversationsList(TwilioConversationsClient client, final Map<String, String> filters) {
		super(client, filters);
	}

	@Override
	protected Conversation makeNew(TwilioConversationsClient client,
			Map<String, Object> params) {
		return new Conversation(client, params);
	}

	@Override
	protected String getResourceLocation() {
		return "/" + TwilioConversationsClient.DEFAULT_VERSION + "/Conversations/InProgress";
	}

}
