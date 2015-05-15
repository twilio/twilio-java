package com.twilio.sdk.resource.list.conversations;

import static org.junit.Assert.assertNotNull;

import org.junit.Before;
import org.junit.Test;

import com.twilio.sdk.resource.instance.BasicRequestTester;
import com.twilio.sdk.resource.instance.conversations.Conversations;

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
		for (final Conversations conversations : inProgressConversations) {
			assertNotNull(conversations.getSid());
			assertNotNull(conversations.getStatus());
			assertNotNull(conversations.getAccountSid());
			assertNotNull(conversations.getDateCreated());
			assertNotNull(conversations.getStartTime());
			assertNotNull(conversations.getEndTime());
			assertNotNull(conversations.getDuration());
			assertNotNull(conversations.getUrl());
			assertNotNull(conversations.getParticipantsUrl());
		}
	}
}
