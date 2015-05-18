package com.twilio.sdk.resource.list.conversations;

import java.util.List;
import java.util.Map;

import org.apache.http.NameValuePair;

import com.twilio.sdk.TwilioConversationsClient;
import com.twilio.sdk.TwilioRestException;
import com.twilio.sdk.TwilioRestResponse;
import com.twilio.sdk.resource.NextGenListResource;
import com.twilio.sdk.resource.factory.Factory;
import com.twilio.sdk.resource.instance.conversations.Participant;

/**
 * Represents a list of Participants
 * 
 */
public class ParticipantList extends NextGenListResource<Participant, TwilioConversationsClient> implements Factory<Participant> {

	private String conversationSid;

	/**
	 * Instantiates a Participant list
	 * 
	 * @param client the REST client
	 * @param conversationSid the conversation sid, participants belong to
	 */
	public ParticipantList(TwilioConversationsClient client, String conversationSid) {
		super(client);
		this.conversationSid = conversationSid;
	}

	/**
	 * Instantiates a Participant list
	 * 
	 * @param client the REST client
	 * @param filters query parameters
	 * @param conversationSid the conversation sid, the participants belong to
	 */
	public ParticipantList(TwilioConversationsClient client, final Map<String, String> filters, String conversationSid) {
		super(client, filters);
		this.conversationSid = conversationSid;
	}

	@Override
	public Participant create(Map<String, String> params)
			throws TwilioRestException {
		TwilioRestResponse response = getClient().safeRequest(getResourceLocation(), "POST", params);
		return makeNew(getClient(), response.toMap());
	}

	@Override
	public Participant create(List<NameValuePair> params)
			throws TwilioRestException {
		TwilioRestResponse response = getClient().safeRequest(getResourceLocation(), "POST", params);
		return makeNew(getClient(), response.toMap());
	}

	@Override
	protected Participant makeNew(TwilioConversationsClient client,
			Map<String, Object> params) {
		return new Participant(client, params);
	}

	@Override
	protected String getResourceLocation() {
		return "/" + TwilioConversationsClient.DEFAULT_VERSION + "/Conversations/" + conversationSid + "/Participants";
	}

}
