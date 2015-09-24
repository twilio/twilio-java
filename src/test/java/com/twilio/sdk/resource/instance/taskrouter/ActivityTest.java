package com.twilio.sdk.resource.instance.taskrouter;

import com.twilio.sdk.resource.instance.BasicRequestTester;
import org.junit.Before;
import org.junit.Test;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Map;
import java.util.TimeZone;

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
		Activity activity = taskRouterClient.createActivity("WSaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa", properties);
		assertNotNull(activity);
		assertEquals("New Activity", activity.getFriendlyName());
		assertTrue(activity.isAvailable());
		Calendar c = activity.getDateCreated();
		c.setTimeZone(TimeZone.getTimeZone("UTC"));
		assertEquals(2014, c.get(Calendar.YEAR));
		assertEquals(Calendar.MAY, c.get(Calendar.MONTH));
		assertEquals(14, c.get(Calendar.DAY_OF_MONTH));
		assertEquals(10, c.get(Calendar.HOUR_OF_DAY));
		assertEquals(50, c.get(Calendar.MINUTE));
		assertEquals(2, c.get(Calendar.SECOND));
	}

	@Test
	public void testDeleteActivity() throws Exception {
		setExpectedServerAnswer(null);
		setExpectedServerReturnCode(204);
		assertTrue(taskRouterClient.deleteActivity("WSaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa", "WAaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa"));
	}

	@Test
	public void testGetActivity() throws Exception {
		setExpectedServerReturnCode(200);
		Activity activity = taskRouterClient.getActivity("WSaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa", "WAaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa");
		assertNotNull(activity);
		assertEquals("WAaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa", activity.getSid());
		assertEquals("New Activity", activity.getFriendlyName());
	}

}
