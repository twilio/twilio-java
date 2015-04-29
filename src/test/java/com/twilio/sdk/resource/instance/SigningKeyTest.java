package com.twilio.sdk.resource.instance;

import com.twilio.sdk.resource.factory.SigningKeyFactory;
import org.junit.Before;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

public class SigningKeyTest extends BasicRequestTester {

	@Before
	public void setup() throws Exception {
		setExpectedServerContentType("application/json");
		setExpectedServerAnswer("signing_key.json");
	}

	@Test
	public void testCreate() throws Exception {
		setExpectedServerReturnCode(201);
		Map<String, String> properties = new HashMap<String, String>();
		properties.put("FriendlyName", "Test Signing Key");
		SigningKeyFactory factory = restClient.getAccount().getSigningKeyFactory();
		SigningKey signingKey = factory.create(properties);
		assertNotNull(signingKey);
		assertEquals("Test Signing Key", signingKey.getFriendlyName());
	}

	@Test
	public void testDelete() throws Exception {
		setExpectedServerAnswer(null);
		setExpectedServerReturnCode(204);
		assertTrue(restClient.getAccount().getSigningKey("SKaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa").delete());
	}

	@Test
	public void testGet() {
		SigningKey signingKey = restClient.getAccount().getSigningKey("SKaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa");
		assertNotNull(signingKey);
		assertEquals("SKaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa", signingKey.getSid());
		assertEquals("Test Signing Key", signingKey.getFriendlyName());
		assertNotNull(signingKey.getDateCreated());
		assertNotNull(signingKey.getDateUpdated());
	}
}
