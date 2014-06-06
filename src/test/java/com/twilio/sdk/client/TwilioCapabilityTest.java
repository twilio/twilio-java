package com.twilio.sdk.client;

import org.junit.Test;

import static org.junit.Assert.assertNotNull;

/**
 * @since 2014-06-06
 */
public class TwilioCapabilityTest {

	private static final String ACCOUNT_SID = "AC12345678901234567890123456789012";

	private static final String AUTH_TOKEN = "abcd1234";

	@Test
	public void testGenerateToken() throws TwilioCapability.DomainException {
		TwilioCapability twilioCapability = new TwilioCapability(ACCOUNT_SID, AUTH_TOKEN);
		String token = twilioCapability.generateToken();
		assertNotNull(token);
	}

	@Test
	public void testGenerateTokenLong() throws TwilioCapability.DomainException {
		TwilioCapability twilioCapability = new TwilioCapability(ACCOUNT_SID, AUTH_TOKEN);
		String token = twilioCapability.generateToken(10);
		assertNotNull(token);
	}

}
