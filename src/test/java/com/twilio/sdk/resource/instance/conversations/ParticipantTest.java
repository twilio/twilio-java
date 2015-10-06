package com.twilio.sdk.resource.instance.conversations;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Map;
import java.util.TimeZone;

import org.junit.Before;
import org.junit.Test;

import com.twilio.sdk.resource.instance.BasicRequestTester;

public class ParticipantTest extends BasicRequestTester {

	protected static final SimpleDateFormat ISO_8601_DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'");

	@Before
	public void setup() throws Exception {
		setExpectedServerContentType("application/json");
		setExpectedServerAnswer("/" + getClass().getPackage()
				.getName()
				.replace(".", "/") + "/participant.json");
	}

	@Test
	public void testGetParticipant() throws Exception {
		setExpectedServerReturnCode(200);
		Participant participant = conversationsClient.getParticipant("CVbbe46ff1274e283f7e3ac1df0072ab39", "PA976ba259ddbf4436ac5e0f3d14eff1e8");
		assertNotNull(participant);
		assertEquals("PA976ba259ddbf4436ac5e0f3d14eff1e8", participant.getSid());
		assertEquals("CVbbe46ff1274e283f7e3ac1df0072ab39", participant.getConversationSid());
		assertEquals("AC7b659acc64804bd8bc01a2d9d54248e1", participant.getAccountSid());
		assertEquals("connected", participant.getStatus());
		assertEquals("client:lewis", participant.getAddress());
		assertNotNull(participant.getDateCreated());
		assertNotNull(participant.getStartTime());
		assertNotNull(participant.getEndTime());
		assertEquals(getDate("2015-05-01T17:32:09Z"), participant.getDateCreated());
		assertEquals(getDate("2010-08-18T20:20:10Z"), participant.getStartTime());
		assertEquals(getDate("2010-08-18T20:24:36Z"), participant.getEndTime());
		assertEquals(60, participant.getDuration().intValue());
		assertEquals("https://conversations.twilio.com/v1/Conversations/CVbbe46ff1274e283f7e3ac1df0072ab39/Participants/PAb659acc64870d9d54248e14bd8bc01a2", participant.getUrl());
	}

	@Test
	public void testCreateParticipant() throws Exception {
		setExpectedServerReturnCode(201);
		Map<String, String> properties = new HashMap<String, String>();
		properties.put("To", "The Twilio SDK endpoint address to invite to the Conversation.");
		properties.put("From", "The Twilio SDK endpoint address where the invite comes from.");
		Participant participant = conversationsClient.createParticipant("CVbbe46ff1274e283f7e3ac1df0072ab39", properties);
		assertNotNull(participant);
		assertEquals("PA976ba259ddbf4436ac5e0f3d14eff1e8", participant.getSid());
		assertEquals("CVbbe46ff1274e283f7e3ac1df0072ab39", participant.getConversationSid());
		assertEquals("AC7b659acc64804bd8bc01a2d9d54248e1", participant.getAccountSid());
		assertEquals("connected", participant.getStatus());
		assertEquals("client:lewis", participant.getAddress());
		assertNotNull(participant.getDateCreated());
		assertNotNull(participant.getStartTime());
		assertNotNull(participant.getEndTime());
		assertEquals(getDate("2015-05-01T17:32:09Z"), participant.getDateCreated());
		assertEquals(getDate("2010-08-18T20:20:10Z"), participant.getStartTime());
		assertEquals(getDate("2010-08-18T20:24:36Z"), participant.getEndTime());
		assertEquals(60, participant.getDuration().intValue());
		assertEquals("https://conversations.twilio.com/v1/Conversations/CVbbe46ff1274e283f7e3ac1df0072ab39/Participants/PAb659acc64870d9d54248e14bd8bc01a2", participant.getUrl());
	}

	@Test
	public void testDisconnectParticipant() throws Exception {
		setExpectedServerReturnCode(201);
		Participant participant = conversationsClient.disconnectParticipant("CVbbe46ff1274e283f7e3ac1df0072ab39", "PA976ba259ddbf4436ac5e0f3d14eff1e8");

		setExpectedServerAnswer("/" + getClass().getPackage()
				.getName()
				.replace(".", "/") + "/participant_disconnected.json");

		assertNotNull(participant);
		assertEquals("PA976ba259ddbf4436ac5e0f3d14eff1e8", participant.getSid());
		assertEquals("CVbbe46ff1274e283f7e3ac1df0072ab39", participant.getConversationSid());
		assertEquals("AC7b659acc64804bd8bc01a2d9d54248e1", participant.getAccountSid());
		assertEquals("disconnected", participant.getStatus());
		assertEquals("client:lewis", participant.getAddress());
		assertNotNull(participant.getDateCreated());
		assertNotNull(participant.getStartTime());
		assertNotNull(participant.getEndTime());
		assertEquals(getDate("2015-05-01T17:32:09Z"), participant.getDateCreated());
		assertEquals(getDate("2010-08-18T20:20:10Z"), participant.getStartTime());
		assertEquals(getDate("2010-08-18T20:24:36Z"), participant.getEndTime());
		assertEquals(60, participant.getDuration().intValue());
		assertEquals("https://conversations.twilio.com/v1/Conversations/CVbbe46ff1274e283f7e3ac1df0072ab39/Participants/PAb659acc64870d9d54248e14bd8bc01a2", participant.getUrl());
	}

	/**
	 * Gets a Calendar representation
	 * 
	 * @param date string date
	 * @return Calendar date
	 */
	private Calendar getDate(String date) {
		try {
			ISO_8601_DATE_FORMAT.getCalendar().setTimeZone(TimeZone.getTimeZone("UTC"));
			GregorianCalendar c = new GregorianCalendar();
			c.setTime(ISO_8601_DATE_FORMAT.parse(date));
			return c;
		} catch (ParseException e) {
			return null;
		}
	}
}
