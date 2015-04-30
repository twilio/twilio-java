package com.twilio.sdk;

import org.apache.http.auth.AuthScope;
import org.apache.http.auth.Credentials;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.impl.client.DefaultHttpClient;
import org.junit.BeforeClass;
import org.junit.Test;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

/**
 * The Class TwilioClientTest.
 */
public class TwilioClientTest {

	private static String expiredJwt;
	private static String invalidJwt;
	private static String validJwt;

	private static Field authTokenField;
	private static Method setupRequestMethod;

	@BeforeClass
	public static void classSetup() throws NoSuchMethodException, NoSuchFieldException {
		ScopedAuthenticationToken sat = new ScopedAuthenticationToken("SK123", "ACXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX");
		validJwt = sat.generateToken("secret");
		invalidJwt = validJwt.substring(1);
		sat = new ScopedAuthenticationToken("SK123", "ACXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX", null, -3600,
		                                    new ArrayList<Grant>(0));
		expiredJwt = sat.generateToken("secret");

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

	@Test
	public void testSetupRequestWithExpiredJwt() throws NoSuchFieldException, IllegalAccessException,
	                                                    InvocationTargetException {
		TwilioClient client = new TwilioRestClient("ACXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX", validJwt);
		authTokenField.set(client, expiredJwt);
		HttpUriRequest request = (HttpUriRequest) setupRequestMethod.invoke(client, "/2010-04-01/Accounts.json", "GET",
		                                                                    null);

		assertNotNull(request);
		DefaultHttpClient httpClient = (DefaultHttpClient) client.getHttpClient();
		Credentials credentials = httpClient.getCredentialsProvider()
		                                    .getCredentials(new AuthScope(AuthScope.ANY_HOST, AuthScope.ANY_PORT));
		assertNotNull(credentials);
		assertEquals("Token", credentials.getUserPrincipal()
		                                 .getName());
		assertEquals(expiredJwt, credentials.getPassword());
	}

	@Test
	public void testSetupRequestWithInvalidJwt() throws NoSuchFieldException, IllegalAccessException,
	                                                    InvocationTargetException {
		TwilioClient client = new TwilioRestClient("ACXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX", validJwt);
		authTokenField.set(client, invalidJwt);
		HttpUriRequest request = (HttpUriRequest) setupRequestMethod.invoke(client, "/2010-04-01/Accounts.json", "GET",
		                                                                    null);

		assertNotNull(request);
		DefaultHttpClient httpClient = (DefaultHttpClient) client.getHttpClient();
		Credentials credentials = httpClient.getCredentialsProvider()
		                                    .getCredentials(new AuthScope(AuthScope.ANY_HOST, AuthScope.ANY_PORT));
		assertNotNull(credentials);
		assertEquals("ACXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX", credentials.getUserPrincipal()
		                                                              .getName());
		assertEquals(invalidJwt, credentials.getPassword());
	}

	@Test
	public void testSetupRequestWithJwt() throws NoSuchMethodException, InvocationTargetException,
	                                             IllegalAccessException {
		TwilioClient client = new TwilioRestClient("ACXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX", validJwt);
		HttpUriRequest request = (HttpUriRequest) setupRequestMethod.invoke(client, "/2010-04-01/Accounts.json", "GET",
		                                                                    null);

		assertNotNull(request);
		DefaultHttpClient httpClient = (DefaultHttpClient) client.getHttpClient();
		Credentials credentials = httpClient.getCredentialsProvider()
		                                    .getCredentials(new AuthScope(AuthScope.ANY_HOST, AuthScope.ANY_PORT));
		assertNotNull(credentials);
		assertEquals("Token", credentials.getUserPrincipal()
		                                 .getName());
		assertEquals(validJwt, credentials.getPassword());
	}

	@Test(expected = IllegalArgumentException.class)
	public void testTwilioRestClientWithExpiredJwt() {
		new TwilioRestClient("ACXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX", expiredJwt);
	}

	@Test(expected = IllegalArgumentException.class)
	public void testTwilioRestClientWithInvalidJwt() {
		new TwilioRestClient("ACXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX", invalidJwt);
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
