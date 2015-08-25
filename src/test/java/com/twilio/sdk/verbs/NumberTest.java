package com.twilio.sdk.verbs;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class NumberTest {

	@Test
	/**
	 * Test Number noun with statusCallback attributes.
	 */
	public void testNumberStatusCallback() throws TwiMLException {
		Number number = new Number("+15108675309");
		number.setStatusCallback("http://example.com");
		number.setStatusCallbackMethod("POST");
		number.setStatusCallbackEvents("ringing completed");
		assertEquals("<Number statusCallback=\"http://example.com\" statusCallbackMethod=\"POST\" statusCallbackEvent=\"ringing completed\">+15108675309</Number>",
				number.toXML());
	}
}
