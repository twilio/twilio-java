package com.twilio.sdk.resource.list.monitor;

import com.twilio.sdk.resource.instance.BasicRequestTester;
import com.twilio.sdk.resource.instance.monitor.Alert;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertNotNull;

public class AlertListTest extends BasicRequestTester {

	@Before
	public void setup() throws Exception {
		setExpectedServerContentType("application/json");
		setExpectedServerAnswer("/" + getClass().getPackage()
		                                        .getName()
		                                        .replace(".", "/") + "/alerts.json");
	}

	@Test
	public void testGetEvents() throws Exception {
		setExpectedServerReturnCode(200);
		AlertList alerts = monitorClient.getAlerts();
		assertNotNull(alerts);
		for (final Alert alert : alerts) {
			assertNotNull(alert.getSid());
			assertNotNull(alert.getDateCreated());
		}
	}
}
