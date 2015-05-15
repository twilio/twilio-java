package com.twilio.sdk.resource.instance.conversations;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.TimeZone;

import org.junit.Before;
import org.junit.Test;

import com.twilio.sdk.resource.instance.BasicRequestTester;

public class ConversationTest extends BasicRequestTester {

	protected static final SimpleDateFormat ISO_8601_DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'");

	@Before
	public void setup() throws Exception {
		setExpectedServerContentType("application/json");
		setExpectedServerAnswer("/" + getClass().getPackage()
				.getName()
				.replace(".", "/") + "/conversation.json");
	}

	@Test
	public void testGetAlert() throws Exception {
		setExpectedServerReturnCode(200);
		Conversation conversation = conversationsClient.getConversation("CVb659acc64870d9d54248e14bd8bc01a2");
		assertNotNull(conversation);
		assertEquals("CVb659acc64870d9d54248e14bd8bc01a2", conversation.getSid());
		assertEquals("AC7b659acc64804bd8bc01a2d9d54248e1", conversation.getAccountSid());
		assertEquals("in-progress", conversation.getStatus());
		assertNotNull(conversation.getDateCreated());
		assertNotNull(conversation.getStartTime());
		assertNotNull(conversation.getEndTime());
		assertEquals(getDate("2010-08-18T20:20:10Z"), conversation.getDateCreated());
		assertEquals(getDate("2010-08-18T20:20:10Z"), conversation.getStartTime());
		assertEquals(getDate("2010-08-18T20:20:10Z"), conversation.getEndTime());
		assertEquals(60, conversation.getDuration().intValue());
		assertEquals("https://conversations.twilio.com/v1/Conversations/CVbbe46ff1274e283f7e3ac1df0072ab39", conversation.getUrl());
		assertEquals("https://conversations.twilio.com/v1/Conversations/CVbbe46ff1274e283f7e3ac1df0072ab39/Participants", conversation.getParticipantsUrl());
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
			c.setTimeZone(TimeZone.getTimeZone("UTC"));
			c.setTime(ISO_8601_DATE_FORMAT.parse(date));
			return c;
		} catch (ParseException e) {
			return null;
		}
	}
}
