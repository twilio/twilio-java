package com.twilio.sdk.resource.list;

import com.twilio.sdk.TwilioRestClient;
import com.twilio.sdk.TwilioRestException;
import org.junit.Test;

import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;

public class IncomingPhoneNumbersTest {

	private static final String ACCOUNT_SID = "AC12345678901234567890123456789012";

	private final TwilioRestClient client = mock(TwilioRestClient.class);

	@Test
	public void testGetLocal() throws TwilioRestException {
		IncomingPhoneNumberList numbers = new IncomingPhoneNumberList(client, IncomingPhoneNumberList.TYPE_LOCAL);
		numbers.setRequestAccountSid(ACCOUNT_SID);

		String expectedLocal = "/2010-04-01/Accounts/" + ACCOUNT_SID + "/IncomingPhoneNumbers/Local.json";

		assertTrue(numbers.getResourceLocation().equals(expectedLocal));
	}

	@Test
	public void testGetMobile() throws TwilioRestException {
		IncomingPhoneNumberList numbers = new IncomingPhoneNumberList(client, IncomingPhoneNumberList.TYPE_MOBILE);
		numbers.setRequestAccountSid(ACCOUNT_SID);

		String expectedMobile = "/2010-04-01/Accounts/" + ACCOUNT_SID + "/IncomingPhoneNumbers/Mobile.json";

		assertTrue(numbers.getResourceLocation().equals(expectedMobile));
	}

	@Test
	public void testGetTollFree() throws TwilioRestException {
		IncomingPhoneNumberList numbers = new IncomingPhoneNumberList(client, IncomingPhoneNumberList.TYPE_TOLLFREE);
		numbers.setRequestAccountSid(ACCOUNT_SID);

		String expectedTollFree = "/2010-04-01/Accounts/" + ACCOUNT_SID + "/IncomingPhoneNumbers/TollFree.json";

		assertTrue(numbers.getResourceLocation().equals(expectedTollFree));
	}

}
