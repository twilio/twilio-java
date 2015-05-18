package com.twilio.sdk.resource.list.conversations;

import static org.junit.Assert.assertNotNull;

import org.junit.Before;
import org.junit.Test;

import com.twilio.sdk.resource.instance.BasicRequestTester;
import com.twilio.sdk.resource.instance.conversations.Participant;

public class ParticipantListTest extends BasicRequestTester {

	@Before
	public void setup() throws Exception {
		setExpectedServerContentType("application/json");
		setExpectedServerAnswer("/" + getClass().getPackage()
		                                        .getName()
		                                        .replace(".", "/") + "/participants.json");
	}

	@Test
	public void testGetParticipants() throws Exception {
		setExpectedServerReturnCode(200);
		ParticipantList participants = conversationsClient.getParticipants("CVbbe46ff1274e283f7e3ac1df0072ab39");
		assertNotNull(participants);
		for (Participant participant : participants) {
			assertNotNull(participant.getSid());
			assertNotNull(participant.getStatus());
			assertNotNull(participant.getConversationSid());
			assertNotNull(participant.getAddress());
			assertNotNull(participant.getAccountSid());
			assertNotNull(participant.getDateCreated());
			assertNotNull(participant.getStartTime());
			assertNotNull(participant.getEndTime());
			assertNotNull(participant.getDuration());
			assertNotNull(participant.getUrl());
		}
	}
}
