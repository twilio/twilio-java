package com.twilio.sdk;

import com.twilio.sdk.parser.JsonResponseParser;
import com.twilio.sdk.parser.XmlResponseParser;
import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;


/**
 * Tests for TwilioRestResponse.
 */
public class TwilioRestResponseTest {

	/**
	 * Test content-type logic.
	 */
	@Test
	public void testTwilioRestResponseContentType() {
		TwilioRestResponse response = new TwilioRestResponse("http://example.com/test", "don't care", 200);
		response.setContentType("application/json; charset=utf-8");
		assertTrue(response.isJson());
		assertFalse(response.isXml());

		response.setContentType("application/xml; charset=utf-8");
		assertTrue(response.isXml());
		assertFalse(response.isJson());

		response.setContentType("text/xml; charset=utf-8");
		assertTrue(response.isXml());
		assertFalse(response.isJson());

		// Should work without charsets too
		response.setContentType("application/json");
		assertTrue(response.isJson());
		assertFalse(response.isXml());

		response.setContentType("application/xml");
		assertTrue(response.isXml());
		assertFalse(response.isJson());

		response.setContentType("text/xml");
		assertTrue(response.isXml());
		assertFalse(response.isJson());
	}

	/**
	 * Test response parsers.
	 */
	@Test
	public void testTwilioRestResponseParser() throws UnsupportedOperationException {
		TwilioRestResponse response = new TwilioRestResponse("http://example.com/test", "don't care", 200);

		response.setContentType("application/json; charset=utf-8");
		assertTrue(response.getParser() instanceof JsonResponseParser);

		response.setContentType("application/xml; charset=utf-8");
		assertTrue(response.getParser() instanceof XmlResponseParser);

		response.setContentType("text/html");
	}

}
