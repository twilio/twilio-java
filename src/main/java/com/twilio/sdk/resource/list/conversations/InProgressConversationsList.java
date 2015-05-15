package com.twilio.sdk.resource.list.conversations;

import java.util.Map;

import com.twilio.sdk.TwilioConversationsClient;
import com.twilio.sdk.resource.NextGenListResource;
import com.twilio.sdk.resource.instance.conversations.Conversations;

/**
 * Represents In-Progress Conversations List
 * 
 */
public class InProgressConversationsList extends NextGenListResource<Conversations, TwilioConversationsClient> {

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
	protected Conversations makeNew(TwilioConversationsClient client,
			Map<String, Object> params) {
		return new Conversations(client, params);
	}

	@Override
	protected String getResourceLocation() {
		return "/" + TwilioConversationsClient.DEFAULT_VERSION + "/Conversations/InProgress";
	}

}
