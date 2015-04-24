package com.twilio.sdk.resource.instance;

import com.twilio.sdk.TwilioRestClient;
import com.twilio.sdk.TwilioRestException;
import com.twilio.sdk.TwilioRestResponse;
import org.junit.Test;
import org.mockito.Matchers;

import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.stub;

public class MessageTest {
	final String messageSid = "MM12345678901234567890123456789012";

	final String accountSid = "AC12345678901234567890123456789012";
	TwilioRestClient client = mock(TwilioRestClient.class);
	TwilioRestResponse resp = mock(TwilioRestResponse.class);

	private void setupMocks() {
		HashMap<String, Object> map = new HashMap<String, Object>();
		stub(resp.toMap()).toReturn(map);

		map.put("to", "+19197404420");
		map.put("from", "+19192386150");
		map.put("body", "This message is a test!");
		map.put("direction", "outbound-api");
		map.put("price", "-0.010000");
		map.put("price_unit", "USD");
		map.put("api_version", "2010-04-01");
		map.put("sid", messageSid);
		map.put("account_sid", accountSid);
		map.put("error_code", 30001);
		map.put("error_message", "Queue overflow");
	}

	@Test
	public void testCreation() throws TwilioRestException {
		setupMocks();
		stub(
				client.safeRequest(Matchers.eq("/2010-04-01/Accounts/" + accountSid + "/Messages/" + messageSid
						+ ".json"), Matchers.eq("GET"), Matchers.any(Map.class)))
			.toReturn(resp);
		Message m = new Message(client, messageSid);
		m.setRequestAccountSid(accountSid);

		assertTrue(m.getTo().equals("+19197404420"));
		assertTrue(m.getFrom().equals("+19192386150"));
		assertTrue(m.getBody().equals("This message is a test!"));
		assertTrue(m.getDirection().equals("outbound-api"));
		assertTrue(m.getPrice().equals("-0.010000"));
		assertTrue(m.getPriceUnit().equals("USD"));
		assertTrue(m.getApiVersion().equals("2010-04-01"));
		assertTrue(m.getSid().equals(messageSid));
		assertTrue(m.getAccountSid().equals(accountSid));
		assertTrue(m.getErrorCode().equals(30001));
		assertTrue(m.getErrorMessage().equals("Queue overflow"));
	}

}
