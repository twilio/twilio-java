package com.twilio.sdk.resource.list.conversations;

import static org.junit.Assert.assertNotNull;

import org.junit.Before;
import org.junit.Test;

import com.twilio.sdk.resource.instance.BasicRequestTester;
import com.twilio.sdk.resource.instance.conversations.Conversation;

public class CompletedConversationsListTest extends BasicRequestTester {

	@Before
	public void setup() throws Exception {
		setExpectedServerContentType("application/json");
		setExpectedServerAnswer("/" + getClass().getPackage()
		                                        .getName()
		                                        .replace(".", "/") + "/completedconversations.json");
	}

	@Test
	public void testGetCompletedConversations() throws Exception {
		setExpectedServerReturnCode(200);
		CompletedConversationsList completedConversations = conversationsClient.getCompletedConversations();
		assertNotNull(completedConversations);
		for (final Conversation conversation : completedConversations) {
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
