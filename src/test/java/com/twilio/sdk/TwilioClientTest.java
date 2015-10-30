package com.twilio.sdk;

import com.twilio.sdk.auth.AccessToken;
import com.twilio.sdk.auth.IpMessagingGrant;
import org.junit.Test;

import java.util.Map;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * The Class TwilioClientTest.
 */
public class TwilioClientTest {

	private static final String SIGNING_KEY_SID = "SK123";
	private static final String SECRET = "secret";

	@Test(expected = IllegalArgumentException.class)
	public void testInvalidAccountSidAndToken() {
		new TwilioRestClient("fake sid", "fake auth token");
	}

	@Test
	public void testAcceptAccessToken() {
		AccessToken accessToken = new AccessToken(SIGNING_KEY_SID,
				"ACXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX", SECRET);
		accessToken.addGrant(new IpMessagingGrant());

		// should not throw
		new TwilioRestClient("ACXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX", accessToken.toJWT());
	}

	/**
	 * Test twilio rest client string string.
	 */
	@Test
	public void testTwilioRestClientStringString() {
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
