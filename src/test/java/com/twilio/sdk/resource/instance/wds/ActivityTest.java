package com.twilio.sdk.resource.instance.wds;

import com.twilio.sdk.resource.instance.BasicRequestTester;
import org.junit.Before;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

public class ActivityTest extends BasicRequestTester {

	@Before
	public void setup() throws Exception {
		setExpectedServerContentType("application/json");
		setExpectedServerAnswer("/" + getClass().getPackage().getName().replace(".", "/") + "/activity.json");
	}

	@Test
	public void testCreateActivity() throws Exception {
		setExpectedServerReturnCode(201);
		Map<String, String> properties = new HashMap<String, String>();
		properties.put("FriendlyName", "New Activity");
		properties.put("Available", "true");
		Activity activity = wdsClient.createActivity("WSaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa", properties);
		assertNotNull(activity);
		assertEquals("New Activity", activity.getFriendlyName());
		assertTrue(activity.isAvailable());
	}

	@Test
	public void testDeleteActivity() throws Exception {
		setExpectedServerAnswer(null);
		setExpectedServerReturnCode(204);
		assertTrue(wdsClient.deleteActivity("WSaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa", "WAaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa"));
	}

	@Test
	public void testGetActivity() throws Exception {
		setExpectedServerReturnCode(200);
		Activity activity = wdsClient.getActivity("WSaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa", "WAaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa");
		assertNotNull(activity);
		assertEquals("WAaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa", activity.getSid());
		assertEquals("New Activity", activity.getFriendlyName());
	}

}
