package com.twilio.sdk.resource.list;

import com.twilio.sdk.TwilioRestClient;
import com.twilio.sdk.TwilioRestException;
import org.junit.Test;

import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;

public class AvailablePhoneNumbersTest {

	private static final String ACCOUNT_SID = "AC12345678901234567890123456789012";

	private final TwilioRestClient client = mock(TwilioRestClient.class);

	@Test
	public void testGetLocal() throws TwilioRestException {
		AvailablePhoneNumberList numbers = new AvailablePhoneNumberList(client, "US",
		                                                                AvailablePhoneNumberList.TYPE_LOCAL);
		numbers.setRequestAccountSid(ACCOUNT_SID);

		String expectedLocal = "/2010-04-01/Accounts/" + ACCOUNT_SID + "/AvailablePhoneNumbers/US/Local.json";

		assertTrue(numbers.getResourceLocation().equals(expectedLocal));
	}

	@Test
	public void testGetMobile() throws TwilioRestException {
		AvailablePhoneNumberList numbers = new AvailablePhoneNumberList(client, "US",
		                                                                AvailablePhoneNumberList.TYPE_MOBILE);
		numbers.setRequestAccountSid(ACCOUNT_SID);

		String expectedMobile = "/2010-04-01/Accounts/" + ACCOUNT_SID + "/AvailablePhoneNumbers/US/Mobile.json";

		assertTrue(numbers.getResourceLocation().equals(expectedMobile));
	}

	@Test
	public void testGetTollFree() throws TwilioRestException {
		AvailablePhoneNumberList numbers = new AvailablePhoneNumberList(client, "US",
		                                                                AvailablePhoneNumberList.TYPE_TOLLFREE);
		numbers.setRequestAccountSid(ACCOUNT_SID);

		String expectedTollFree = "/2010-04-01/Accounts/" + ACCOUNT_SID + "/AvailablePhoneNumbers/US/TollFree.json";

		assertTrue(numbers.getResourceLocation().equals(expectedTollFree));
	}

}
