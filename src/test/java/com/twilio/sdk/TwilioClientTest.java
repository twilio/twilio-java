package com.twilio.sdk;

import org.junit.BeforeClass;
import org.junit.Test;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * The Class TwilioClientTest.
 */
public class TwilioClientTest {

	private static Field authTokenField;
	private static Method setupRequestMethod;

	@BeforeClass
	public static void classSetup() throws NoSuchMethodException, NoSuchFieldException {
		setupRequestMethod = TwilioClient.class.getDeclaredMethod("setupRequest", String.class, String.class,
		                                                          List.class);
		setupRequestMethod.setAccessible(true);

		authTokenField = TwilioClient.class.getDeclaredField("authToken");
		authTokenField.setAccessible(true);
	}

	/**
	 * Test twilio rest client string string.
	 */
	@Test
	public void testTwilioRestClientStringString() {

		// Should fail with invallid auth and token
		try {
			new TwilioRestClient("fake sid", "fake auth token");
		} catch (final IllegalArgumentException e) {
			assertTrue(true);
		}

		// Should construct with valid looking account sid and auth token
		new TwilioRestClient("ACXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX", "XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX");
	}

	/**
	 * Test request.
	 *
	 * @throws TwilioRestException the twilio rest exception
	 */
	@Test
	public void testRequest() throws TwilioRestException {
		TwilioClient client = new TwilioRestClient("ACXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX",
		                                           "XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX");

		// Auth required
		TwilioRestResponse response = client.request("/2010-04-01/Accounts.json", "GET", (Map) null);
		assertEquals(401, response.getHttpStatus());

		// Auth not required
		response = client.request("/2010-04-01", "GET", (Map) null);
		assertEquals(200, response.getHttpStatus());

		// 404'd
		response = client.request("/asfhrhewhwejrkasyrey", "GET", (Map) null);
		assertEquals(404, response.getHttpStatus());
	}

	/**
	 * Test get.
	 *
	 * @throws TwilioRestException the twilio rest exception
	 */
	@Test
	public void testGet() throws TwilioRestException {
		TwilioClient client = new TwilioRestClient("ACXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX",
		                                           "XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX");

		// Auth required
		TwilioRestResponse response = client.get("https://api.twilio.com");
		assertEquals(200, response.getHttpStatus());
	}

}
