package com.twilio.sdk.resource.instance;

import com.twilio.sdk.TwilioRestClient;
import com.twilio.sdk.TwilioRestResponse;
import org.junit.Test;
import org.mockito.Matchers;

import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.stub;

public class MediaTest extends BasicRequestTester {

	final String mediaSid = "ME557ce644e5ab84fa21cc21112e22c485";
	final String accountSid = "AC12345678901234567890123456789012";
	final String messageSid = "SM8dfedb55c129dd4d6bd1f59af9d11080";

	TwilioRestClient client = mock(TwilioRestClient.class);
	TwilioRestResponse resp = mock(TwilioRestResponse.class);

	private void setupMocks() {
		HashMap<String, Object> map = new HashMap<String, Object>();
		stub(resp.toMap()).toReturn(map);

		map.put("content_type", "image/jpeg");
		map.put("sid", mediaSid);
		map.put("api_version", "2010-04-01");
		map.put("parent_sid", messageSid);
		map.put("account_sid", accountSid);
	}

	@Test
	public void testCreation() throws Exception {
		setupMocks();
		stub(
				client.safeRequest(Matchers.eq("/2010-04-01/Accounts/" + accountSid + "/Media/" + mediaSid
						+ ".json"), Matchers.eq("GET"), Matchers.any(Map.class)))
			.toReturn(resp);
		Media media = new Media(client, mediaSid);
		media.setRequestAccountSid(accountSid);

		assertTrue(media.getParentSid().equals("SM8dfedb55c129dd4d6bd1f59af9d11080"));
		assertTrue(media.getContentType().equals("image/jpeg"));
		assertTrue(media.getApiVersion().equals("2010-04-01"));
		assertTrue(media.getSid().equals("ME557ce644e5ab84fa21cc21112e22c485"));
		assertTrue(media.getAccountSid().equals("AC12345678901234567890123456789012"));
	}

}
