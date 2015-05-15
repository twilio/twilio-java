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

public class ConversationsTest extends BasicRequestTester {

	protected static final SimpleDateFormat ISO_8601_DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'");

	@Before
	public void setup() throws Exception {
		setExpectedServerContentType("application/json");
		setExpectedServerAnswer("/" + getClass().getPackage()
				.getName()
				.replace(".", "/") + "/conversations.json");
	}

	@Test
	public void testGetAlert() throws Exception {
		setExpectedServerReturnCode(200);
		Conversations conversations = conversationsClient.getConversations("CVb659acc64870d9d54248e14bd8bc01a2");
		assertNotNull(conversations);
		assertEquals("CVb659acc64870d9d54248e14bd8bc01a2", conversations.getSid());
		assertEquals("AC7b659acc64804bd8bc01a2d9d54248e1", conversations.getAccountSid());
		assertEquals("in-progress", conversations.getStatus());
		assertNotNull(conversations.getDateCreated());
		assertNotNull(conversations.getStartTime());
		assertNotNull(conversations.getEndTime());
		assertEquals(getDate("2010-08-18T20:20:10Z"), conversations.getDateCreated());
		assertEquals(getDate("2010-08-18T20:20:10Z"), conversations.getStartTime());
		assertEquals(getDate("2010-08-18T20:20:10Z"), conversations.getEndTime());
		assertEquals(60, conversations.getDuration().intValue());
		assertEquals("https://conversations.twilio.com/v1/Conversations/CVbbe46ff1274e283f7e3ac1df0072ab39", conversations.getUrl());
		assertEquals("https://conversations.twilio.com/v1/Conversations/CVbbe46ff1274e283f7e3ac1df0072ab39/Participants", conversations.getParticipantsUrl());
	}

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
