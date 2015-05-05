package com.twilio.sdk.resource.instance.monitor;

import com.twilio.sdk.resource.instance.BasicRequestTester;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

public class AlertTest extends BasicRequestTester {

	@Before
	public void setup() throws Exception {
		setExpectedServerContentType("application/json");
		setExpectedServerAnswer("/" + getClass().getPackage()
				.getName()
				.replace(".", "/") + "/alert.json");
	}

	@Test
	public void testGetAlert() throws Exception {
		setExpectedServerReturnCode(200);
		Alert alert = monitorClient.getAlert("NO6f58741ed5bc73fc74612eee1c02d2f3");
		assertNotNull(alert);
		assertEquals("NO6f58741ed5bc73fc74612eee1c02d2f3", alert.getSid());
		assertEquals("CA6f58741ed5bc70b2476902a8e9e628cc", alert.getResourceSid());
		assertEquals("AC70839f875fe15a2c74612eee1c02d2f3", alert.getAccountSid());
		assertNotNull(alert.getDateCreated());
		assertNotNull(alert.getDateGenerated());
		assertNotNull(alert.getDateUpdated());
		assertEquals("11200", alert.getErrorCode());
		assertEquals("2008-08-01", alert.getApiVersion());
		assertEquals("https://www.twilio.com/docs/errors/11200", alert.getMoreInfo());
		assertEquals("LogLevel=ERROR&sourceComponent=12000&Msg&httpResponse=404&ErrorCode=11200&url=https%3A%2F%2F674d331e.ngrok.com%2Fdie",
				alert.getAlertText());
		assertEquals("GET", alert.getRequestMethod());
		assertEquals("AccountSid=AC70839f875fe15a2c74612eee1c02d2f3&ToZip=94111&FromState=CA&Called=%2B14154187474&FromCountry=US&CallerCountry=US&CalledZip=94111&Direction=inbound",
				alert.getRequestVariables());
		assertEquals("Date=Tue%2C+17+Mar+2015+22%3A40%3A19+GMT&Content-Length=36&Server=nginx%2F1.6.2",
				alert.getResponseHeaders());
		assertEquals("", alert.getResponseBody());
		assertEquals("https://monitor.twilio.com/v1/Alerts/NO70839f875fe15a2c74612eee1c02d2f3", alert.getUrl());
		assertEquals("error", alert.getLogLevel());
	}
}
