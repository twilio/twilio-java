package com.twilio.sdk.resource.list.conversations;

import static org.junit.Assert.assertNotNull;

import org.junit.Before;
import org.junit.Test;

import com.twilio.sdk.resource.instance.BasicRequestTester;
import com.twilio.sdk.resource.instance.conversations.Conversation;

public class InProgressConversationsListTest extends BasicRequestTester {

	@Before
	public void setup() throws Exception {
		setExpectedServerContentType("application/json");
		setExpectedServerAnswer("/" + getClass().getPackage()
		                                        .getName()
		                                        .replace(".", "/") + "/inprogressconversations.json");
	}

	@Test
	public void testGetInProgressConversations() throws Exception {
		setExpectedServerReturnCode(200);
		InProgressConversationsList inProgressConversations = conversationsClient.getInProgressConversations();
		assertNotNull(inProgressConversations);
		for (final Conversation conversation : inProgressConversations) {
			assertNotNull(conversation.getSid());
			assertNotNull(conversation.getStatus());
			assertNotNull(conversation.getAccountSid());
			assertNotNull(conversation.getDateCreated());
			assertNotNull(conversation.getStartTime());
			assertNotNull(conversation.getEndTime());
			assertNotNull(conversation.getDuration());
			assertNotNull(conversation.getUrl());
			assertNotNull(conversation.getParticipantsUrl());
		}
	}
}
