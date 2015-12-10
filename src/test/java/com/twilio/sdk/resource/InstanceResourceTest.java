package com.twilio.sdk.resource;

import java.util.HashMap;

import org.junit.Assert;
import org.junit.Test;

import com.twilio.sdk.TwilioIPMessagingClient;
import com.twilio.sdk.resource.instance.ipmessaging.Message;

public class InstanceResourceTest {

	@Test
	public void testGetPropertyAsBoolean() {

		Message resource = new Message(new TwilioIPMessagingClient("ACSid",
				"AuthToken"), new HashMap<String, Object>()) {

			public String getProperty(String name) {
				return "true";
			};

		};
		Boolean actual = resource.getPropertyAsBoolean("read_status_enabled");
		Assert.assertTrue(actual);
	}

	@Test
	public void testGetPropertyAsBooleanForNullValue() {

		Message resource = new Message(new TwilioIPMessagingClient("ACSid",
				"AuthToken"), new HashMap<String, Object>()) {

			public String getProperty(String name) {
				return null;
			};

		};
		Boolean actual = resource.getPropertyAsBoolean("read_status_enabled");
		Assert.assertFalse(actual);
	}

	@Test
	public void testGetPropertyAsBooleanForEmptyValue() {

		Message resource = new Message(new TwilioIPMessagingClient("ACSid",
				"AuthToken"), new HashMap<String, Object>()) {

			public String getProperty(String name) {
				return "";
			};

		};
		Boolean actual = resource.getPropertyAsBoolean("read_status_enabled");
		Assert.assertFalse(actual);
	}

	@Test
	public void testGetPropertyAsBooleanForNonBooleanValue() {

		Message resource = new Message(new TwilioIPMessagingClient("ACSid",
				"AuthToken"), new HashMap<String, Object>()) {

			public String getProperty(String name) {
				return "random";
			};

		};
		Boolean actual = resource.getPropertyAsBoolean("read_status_enabled");
		Assert.assertFalse(actual);
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testGetPropertyAsBooleanThrowsException() {

		Message resource = new Message(new TwilioIPMessagingClient("ACSid",
				"AuthToken"), new HashMap<String, Object>()) {

			public String getProperty(String name) {
				throw new IllegalArgumentException();
			};

		};
		resource.getPropertyAsBoolean("read_status_enabled");
	}
}
