package com.twilio.sdk.resource.list.conversations;

import java.util.Map;

import com.twilio.sdk.TwilioConversationsClient;
import com.twilio.sdk.resource.NextGenListResource;
import com.twilio.sdk.resource.instance.conversations.Conversations;

/**
 * Represents Completed Conversations List
 * 
 */
public class CompletedConversationsList extends NextGenListResource<Conversations, TwilioConversationsClient> {

	/**
	 * Initializes CompletedConversationsList
	 * 
	 * @param client the rest client
	 */
	public CompletedConversationsList(TwilioConversationsClient client) {
		super(client);
	}

	/**
	 * Initializes CompletedConversationsList
	 * 
	 * @param client the rest client
	 * @param filters query parameters
	 */
	public CompletedConversationsList(TwilioConversationsClient client, final Map<String, String> filters) {
		super(client, filters);
	}

	@Override
	protected Conversations makeNew(TwilioConversationsClient client,
			Map<String, Object> params) {
		return new Conversations(client, params);
	}

	@Override
	protected String getResourceLocation() {
		return "/" + TwilioConversationsClient.DEFAULT_VERSION + "/Conversations/Completed";
	}

}
