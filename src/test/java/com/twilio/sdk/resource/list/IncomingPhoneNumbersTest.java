package com.twilio.sdk.resource.list;

import com.twilio.sdk.TwilioRestClient;
import com.twilio.sdk.TwilioRestException;
import com.twilio.sdk.TwilioRestResponse;
import org.junit.Test;

import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;

public class IncomingPhoneNumbersTest {

	final String accountSid = "AC12345678901234567890123456789012";

	TwilioRestClient client = mock(TwilioRestClient.class);
	TwilioRestResponse resp = mock(TwilioRestResponse.class);

	@Test
	public void testGetLocal() throws TwilioRestException {

		IncomingPhoneNumberList numbers = new IncomingPhoneNumberList(client, IncomingPhoneNumberList.TYPE_LOCAL);
		numbers.setRequestAccountSid(accountSid);

		String location = numbers.getResourceLocation();
		String expectedLocal = "/2010-04-01/Accounts/" + accountSid + "/IncomingPhoneNumbers/Local.json";

		assertTrue(numbers.getResourceLocation().equals(expectedLocal));

	}

	@Test
	public void testGetMobile() throws TwilioRestException {

		IncomingPhoneNumberList numbers = new IncomingPhoneNumberList(client, IncomingPhoneNumberList.TYPE_MOBILE);
		numbers.setRequestAccountSid(accountSid);

		String location = numbers.getResourceLocation();
		String expectedMobile = "/2010-04-01/Accounts/" + accountSid + "/IncomingPhoneNumbers/Mobile.json";

		assertTrue(numbers.getResourceLocation().equals(expectedMobile));

	}

	@Test
	public void testGetTollFree() throws TwilioRestException {

		IncomingPhoneNumberList numbers = new IncomingPhoneNumberList(client, IncomingPhoneNumberList.TYPE_TOLLFREE);
		numbers.setRequestAccountSid(accountSid);

		String location = numbers.getResourceLocation();
		String expectedTollFree = "/2010-04-01/Accounts/" + accountSid + "/IncomingPhoneNumbers/TollFree.json";

		assertTrue(numbers.getResourceLocation().equals(expectedTollFree));

	}

}
