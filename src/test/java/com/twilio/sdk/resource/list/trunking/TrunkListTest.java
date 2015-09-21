package com.twilio.sdk.resource.list.trunking;

import com.twilio.sdk.resource.instance.BasicRequestTester;
import com.twilio.sdk.resource.instance.trunking.Trunk;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mockito;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertTrue;

public class TrunkListTest extends BasicRequestTester {

	@Before
	public void setup() throws Exception {
		setExpectedServerContentType("application/json");
	}

	@Test
	public void testGetTrunksWithFilters() throws Exception {
		setExpectedServerReturnCode(200);
		setExpectedServerAnswer(File.separator + getClass().getPackage()
				.getName().replace(".", File.separator) + "/trunklist.json");

		Map<String, String> filters = new HashMap<String, String>();
		filters.put("PageSize", "20");
		TrunkList trunkQuery = trunkingClient.getTrunks(filters);
		List<Trunk> trunks = trunkQuery.getPageData();

		ArgumentCaptor<HttpGet> captor = ArgumentCaptor.forClass(HttpGet.class);
		Mockito.verify(httpClient).execute(captor.capture());

		HttpGet request = captor.getValue();
		assertEquals("https://trunking.twilio.com/v1/Trunks?PageSize=20", request.getURI().toURL().toString());
		assertEquals("GET", request.getMethod());
	}

	@Test
	public void testGetTrunks() throws Exception {
		setExpectedServerReturnCode(200);
		setExpectedServerAnswer(File.separator + getClass().getPackage()
				.getName().replace(".", File.separator) + "/trunklist.json");

		TrunkList trunkQuery = trunkingClient.getTrunks();
		List<Trunk> trunks = trunkQuery.getPageData();

		assertEquals(1, trunks.size());

		Trunk trunk = trunks.get(0);
		assertEquals(accountSid, trunk.getAccountSid());
		assertEquals(Arrays.asList("IP_ACL", "CREDENTIAL_LIST"), trunk.getAuthTypeSet());
		assertEquals("IP_ACL,CREDENTIAL_LIST", trunk.getAuthType());
		assertEquals("GET", trunk.getDisasterRecoveryMethod());
		assertEquals("https://example.com/disaster", trunk.getDisasterRecoveryUrl());
		assertEquals("example.com", trunk.getDomainName());
		assertEquals("friendly_name", trunk.getFriendlyName());
		assertTrue(trunk.isSecure());
		assertEquals("TK68430bd1ea750bb544d7229e23344889", trunk.getSid());
		assertEquals("https://trunking.stage.twilio.com/v1/Trunks/TK68430bd1ea750bb544d7229e23344889", trunk.getUrl());

		Trunk.Recording recording = trunk.getRecording();
		assertEquals("do-not-record", recording.getMode());
		assertEquals("do-not-trim", recording.getTrim());

		ArgumentCaptor<HttpGet> captor = ArgumentCaptor.forClass(HttpGet.class);
		Mockito.verify(httpClient).execute(captor.capture());

		HttpGet request = captor.getValue();
		assertEquals("https://trunking.twilio.com/v1/Trunks",
				request.getURI().toURL().toString());
		assertEquals("GET", request.getMethod());
	}

	@Test
	public void testCreateTrunks() throws Exception {
		setExpectedServerReturnCode(201);
		setExpectedServerAnswer(File.separator + getClass().getPackage()
				.getName().replace(".", File.separator) + "/trunk.json");

		Map<String, String> params = new HashMap<String, String>();
		params.put("FriendlyName", "friendlyname");
		Trunk trunk = trunkingClient.createTrunk(params);

		assertEquals(accountSid, trunk.getAccountSid());
		assertEquals(Arrays.asList("IP_ACL", "CREDENTIAL_LIST"), trunk.getAuthTypeSet());
		assertEquals("IP_ACL,CREDENTIAL_LIST", trunk.getAuthType());
		assertEquals("GET", trunk.getDisasterRecoveryMethod());
		assertEquals("https://example.com/disaster", trunk.getDisasterRecoveryUrl());
		assertEquals("example.com", trunk.getDomainName());
		assertEquals("friendly_name", trunk.getFriendlyName());
		assertTrue(trunk.isSecure());
		assertEquals("TK68430bd1ea750bb544d7229e23344889", trunk.getSid());
		assertEquals("https://trunking.stage.twilio.com/v1/Trunks/TK68430bd1ea750bb544d7229e23344889", trunk.getUrl());

		Trunk.Recording recording = trunk.getRecording();
		assertEquals("do-not-record", recording.getMode());
		assertEquals("do-not-trim", recording.getTrim());

		ArgumentCaptor<HttpPost> captor = ArgumentCaptor.forClass(HttpPost.class);
		Mockito.verify(httpClient).execute(captor.capture());

		HttpPost request = captor.getValue();
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		request.getEntity().writeTo(baos);
		assertEquals("FriendlyName=friendlyname", baos.toString());

		assertEquals("https://trunking.twilio.com/v1/Trunks",
				request.getURI().toURL().toString());
		assertEquals("POST", request.getMethod());
	}
}
