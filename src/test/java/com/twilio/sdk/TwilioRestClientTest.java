package com.twilio.sdk;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Test;


// TODO: Auto-generated Javadoc
/**
 * The Class TwilioRestClientTest.
 */
public class TwilioRestClientTest {

	/**
	 * Test twilio rest client string string.
	 */
	@Test
	public void testTwilioRestClientStringString() {

		// Should fail with invallid auth and token
		try {
			TwilioRestClient bad_client = new TwilioRestClient("fake sid",
					"fake auth token");
		} catch (IllegalArgumentException e) {
			assertTrue(true);
		}

		// Should construct with valid looking account sid and auth token
		TwilioRestClient client = new TwilioRestClient(
				"ACXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX",
				"XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX");
	}

	/**
	 * Test twilio rest client string string string.
	 */
	@Test
	public void testTwilioRestClientStringStringString() {
		// Should fail with invallid auth and token
		try {
			TwilioRestClient bad_client = new TwilioRestClient("fake sid",
					"fake auth token", "some endpoint");
		} catch (IllegalArgumentException e) {
			assertTrue(true);
		}

		// Should construct with valid looking account sid and auth token
		TwilioRestClient client = new TwilioRestClient(
				"ACXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX",
				"XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX", "someendpoint");

	}

	/**
	 * Test request.
	 *
	 * @throws TwilioRestException the twilio rest exception
	 */
	@Test
	public void testRequest() throws TwilioRestException {
		TwilioRestClient client = new TwilioRestClient(
				"ACXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX",
				"XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX");
		
		// Auth required
		TwilioRestResponse response = client.request("/2010-04-01/Accounts.json", "GET", null);	
		assertEquals(401, response.getHttpStatus());
		
		// Auth not required
		response = client.request("/2010-04-01", "GET", null);
		assertEquals(200, response.getHttpStatus());
		
		// 404'd
		response = client.request("/asfhrhewhwejrkasyrey", "GET", null);
		assertEquals(404, response.getHttpStatus());	
	}

	/**
	 * Test get.
	 *
	 * @throws TwilioRestException the twilio rest exception
	 */
	@Test
	public void testGet() throws TwilioRestException {
		TwilioRestClient client = new TwilioRestClient(
				"ACXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX",
				"XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX");
		
		// Auth required
		TwilioRestResponse response = client.get("http://api.twilio.com");	
		assertEquals(200, response.getHttpStatus());
	}

	/**
	 * Test get endpoint.
	 */
	@Test
	public void testGetEndpoint() {
		TwilioRestClient client = new TwilioRestClient(
				"ACXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX",
				"XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX", "someendpoint");
		
		assertEquals(client.getEndpoint(), "someendpoint");
	}

	/**
	 * Test set endpoint.
	 */
	@Test
	public void testSetEndpoint() {
		TwilioRestClient client = new TwilioRestClient(
				"ACXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX",
				"XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX", "someendpoint");
		
		assertEquals(client.getEndpoint(), "someendpoint");
		client.setEndpoint("someendpoint2");
		assertEquals(client.getEndpoint(), "someendpoint2");
	}

}
