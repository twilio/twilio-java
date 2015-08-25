package com.twilio.sdk.verbs;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class ClientTest {

	@Test
	/**
	 * Test Client noun with statusCallback attributes.
	 */
	public void testClientStatusCallback() throws TwiMLException {
		Client client = new Client("client:foobar");
		client.setStatusCallback("http://example.com");
		client.setStatusCallbackMethod("POST");
		client.setStatusCallbackEvents("ringing completed");
		assertEquals("<Client statusCallback=\"http://example.com\" statusCallbackMethod=\"POST\" statusCallbackEvent=\"ringing completed\">client:foobar</Client>",
				client.toXML());
	}
}
