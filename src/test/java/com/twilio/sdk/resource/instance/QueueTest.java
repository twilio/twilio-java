package com.twilio.sdk.resource.instance;

import com.twilio.sdk.TwilioRestClient;
import com.twilio.sdk.TwilioRestException;
import com.twilio.sdk.TwilioRestResponse;
import com.twilio.sdk.parser.JsonResponseParser;
import com.twilio.sdk.resource.list.MemberList;
import org.junit.Test;
import org.mockito.Matchers;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Locale;
import java.util.Map;

import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.stub;

public class QueueTest {
	final SimpleDateFormat dateFormat = new SimpleDateFormat(
			"EEE, dd MMM yyyy HH:mm:ss Z", Locale.ENGLISH);
	final String callSid = "CA12345678901234567890123456789012";
	final String queueSid = "QU12345678901234567890123456789012";

	final String accountSid = "AC12345678901234567890123456789012";
	TwilioRestClient client = mock(TwilioRestClient.class);
	TwilioRestResponse resp = mock(TwilioRestResponse.class);
	String formattedDate;

	/**
	 * Set up the mocks.
	 */
	private void setupMocks() {
		HashMap<String, Object> map = new HashMap<String, Object>();
		stub(resp.toMap()).toReturn(map);
		formattedDate = dateFormat.format(new Date());

		map.put("average_wait_time", 1);
		map.put("current_size", 2);
		map.put("max_size", 3);
		map.put("friendly_name", "friendly");
		map.put("sid", queueSid);
	}

	/**
	 * This test creates a {@link Member} object by interacting with a {@link TwilioRestClient}.
	 *
	 * @throws TwilioRestException
	 */
	@Test
	public void testCreation() throws TwilioRestException {

		setupMocks();
		stub(
				client.safeRequest(Matchers.eq("/2010-04-01/Accounts/" + accountSid + "/Queues/" + queueSid
						+ ".json"), Matchers.eq("GET"), Matchers.any(Map.class)))
			.toReturn(resp);
		Queue q = new Queue(client, queueSid);
		q.setRequestAccountSid(accountSid);

		assertTrue(q.getAverageWaitTime() == 1);
		assertTrue(q.getCurrentSize() == 2);
		assertTrue(q.getMaxSize() == 3);
		assertTrue(q.getFriendlyName().equals("friendly"));
		assertTrue(q.getSid().equals(queueSid));

	}

	/**
	 * This test creates a {@link Member} object by interacting with a {@link TwilioRestClient}.
	 *
	 * @throws TwilioRestException
	 */
	@Test
	public void testDequeueFront() throws TwilioRestException {

		setupMocks();
		stub(
				client.safeRequest(Matchers.eq("/2010-04-01/Accounts/" + accountSid + "/Queues/" + queueSid
						+ ".json"), Matchers.eq("GET"), Matchers.any(Map.class)))
			.toReturn(resp);

		TwilioRestResponse memberresp = mock(TwilioRestResponse.class);
		HashMap<String, Object> map = new HashMap<String, Object>();
		stub(memberresp.toMap()).toReturn(map);
		formattedDate = dateFormat.format(new Date());
		map.put("call_sid", callSid);
		map.put("queue_sid", queueSid);
		map.put("position", 1);
		map.put("date_enqueued", formattedDate);
		map.put("wait_time", 10);

		stub(
				client.safeRequest(Matchers.eq("/2010-04-01/Accounts/" + accountSid + "/Queues/" + queueSid
						+ "/Members/Front.json"), Matchers.eq("POST"), Matchers.any(Map.class)))
			.toReturn(memberresp);

		Queue q = new Queue(client, queueSid);
		q.setRequestAccountSid(accountSid);
		String method = "GET";
		String url = "http://www.example.com";
		Member m = q.dequeueHeadOfQueue(url, method);

		assertTrue(m.getPosition() == 1);
		assertTrue(dateFormat.format(m.getDateEnqueued()).equals(formattedDate));
		assertTrue(m.getWaitTime() == 10);
	}

	/**
	 * This test creates a {@link Member} object by interacting with a {@link TwilioRestClient}.
	 *
	 * @throws TwilioRestException
	 */
	@Test
	public void testSetFriendly() throws TwilioRestException {

		setupMocks();
		stub(
				client.safeRequest(Matchers.eq("/2010-04-01/Accounts/" + accountSid + "/Queues/" + queueSid
						+ ".json"), Matchers.eq("POST"), Matchers.any(Map.class)))
			.toReturn(resp);
		Queue q = new Queue(client, queueSid);
		q.setRequestAccountSid(accountSid);
		q.setFriendlyName("new friendly");
		assertTrue(q.getFriendlyName().equals("new friendly"));
	}

	/**
	 * This test creates a {@link Member} object by interacting with a {@link TwilioRestClient}.
	 *
	 * @throws TwilioRestException
	 */
	@Test
	public void testSetMaxSize() throws TwilioRestException {

		setupMocks();
		stub(
				client.safeRequest(Matchers.eq("/2010-04-01/Accounts/" + accountSid + "/Queues/" + queueSid
						+ ".json"), Matchers.eq("POST"), Matchers.any(Map.class)))
			.toReturn(resp);
		Queue q = new Queue(client, queueSid);
		q.setRequestAccountSid(accountSid);
		q.setMaxSize(99);
		assertTrue(q.getMaxSize() == 99);
	}

	/**
	 * This test creates a {@link Member} object by interacting with a {@link TwilioRestClient}.
	 *
	 * @throws TwilioRestException
	 */
	@Test
	public void testGetMembers() throws TwilioRestException {

		setupMocks();
		stub(
				client.safeRequest(Matchers.eq("/2010-04-01/Accounts/" + accountSid + "/Queues/" + queueSid
						+ ".json"), Matchers.eq("GET"), Matchers.any(Map.class)))
			.toReturn(resp);

		TwilioRestResponse membersresp = mock(TwilioRestResponse.class);
		HashMap<String, Object> map = new HashMap<String, Object>();
		stub(membersresp.toMap()).toReturn(map);
		stub(membersresp.getParser()).toReturn(new JsonResponseParser());


		formattedDate = dateFormat.format(new Date());
		map.put("next_page_uri", "http://next.page.uri/");
		map.put("start", "1");
		map.put("end", "1");
		map.put("total", "1");
		map.put("page", "1");
		map.put("num_pages", "1");
		stub(
				client.safeRequest(Matchers.eq("/2010-04-01/Accounts/" + accountSid + "/Queues/" + queueSid
						+ "/Members.json"), Matchers.eq("GET"), Matchers.any(Map.class)))
			.toReturn(membersresp);
		Queue q = new Queue(client, queueSid);
		q.setRequestAccountSid(accountSid);
		MemberList m = q.getMembers();
		Iterator<Member> memiter = m.iterator();
		assertTrue(memiter.hasNext());
	}
}
