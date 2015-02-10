package com.twilio.sdk.resource.list.taskrouter;

import com.twilio.sdk.resource.instance.BasicRequestTester;
import com.twilio.sdk.resource.instance.taskrouter.Activity;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

public class ActivityListTest extends BasicRequestTester {

	@Before
	public void setup() throws Exception {
		setExpectedServerContentType("application/json");
		setExpectedServerAnswer("/" + getClass().getPackage().getName().replace(".", "/") + "/activities.json");
	}

	@Test
	public void testGetWorkspaces() throws Exception {
		setExpectedServerReturnCode(200);
		ActivityList activities = taskRouterClient.getActivities("WSaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa");
		assertNotNull(activities);
		for (Activity activity : activities) {
			assertNotNull(activity.getSid());
		}
	}

}
