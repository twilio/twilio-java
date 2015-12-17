package com.twilio.sdk.resource.list.ipmessaging;

import com.twilio.sdk.resource.instance.BasicRequestTester;
import com.twilio.sdk.resource.instance.ipmessaging.Credential;
import org.apache.http.client.methods.HttpDelete;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mockito;

import java.io.File;
import java.util.HashMap;
import java.util.List;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertTrue;

public class CredentialListTest extends BasicRequestTester {

	@Test
	public void testGetCredentials() throws Exception {
		setExpectedServerReturnCode(200);
		setExpectedServerContentType("application/json");
		setExpectedServerAnswer(File.separator + getClass().getPackage()
				.getName().replace(".", File.separator)
				+ File.separator + "credential_list.json");

		List<Credential> resources = ipMessagingClient.getCredentials().getPageData();

		assertEquals(1, resources.size());

		Credential resource = resources.get(0);
		assertEquals("CRaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa", resource.getSid());
		assertEquals("ACbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbb", resource.getAccountSid());
		assertEquals("friendly_name", resource.getFriendlyName());
		assertEquals("apn", resource.getType());
		assertEquals("https://ip-messaging.stage.twilio.com/v1/Credentials/CRaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa",
				resource.getUrl());

		ArgumentCaptor<HttpGet> captor = ArgumentCaptor.forClass(HttpGet.class);
		Mockito.verify(httpClient).execute(captor.capture());

		HttpGet request = captor.getValue();
		assertEquals("https://ip-messaging.twilio.com/v1/Credentials",
				request.getURI().toURL().toString());
		assertEquals("GET", request.getMethod());
	}

	@Test
	public void testCreateServices() throws Exception {
		setExpectedServerReturnCode(201);
		setExpectedServerContentType("application/json");
		setExpectedServerAnswer(File.separator + getClass().getPackage()
				.getName().replace(".", File.separator)
				+ File.separator + "credential_instance.json");

		Credential resource = ipMessagingClient.createCredential(new HashMap<String, String>());
		assertEquals("CRaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa", resource.getSid());
		assertEquals("ACbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbb", resource.getAccountSid());
		assertEquals("friendly_name", resource.getFriendlyName());
		assertEquals("apn", resource.getType());
		assertEquals("https://ip-messaging.stage.twilio.com/v1/Credentials/CRaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa",
				resource.getUrl());

		ArgumentCaptor<HttpPost> captor = ArgumentCaptor.forClass(HttpPost.class);
		Mockito.verify(httpClient).execute(captor.capture());

		HttpPost request = captor.getValue();
		assertEquals("https://ip-messaging.twilio.com/v1/Credentials",
				request.getURI().toURL().toString());
		assertEquals("POST", request.getMethod());
	}

	@Test
	public void testDeleteService() throws Exception {
		setExpectedServerReturnCode(204);

		boolean success = ipMessagingClient.getCredential("CRaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa").delete();
		assertTrue(success);

		ArgumentCaptor<HttpDelete> captor = ArgumentCaptor.forClass(HttpDelete.class);
		Mockito.verify(httpClient).execute(captor.capture());

		HttpDelete request = captor.getValue();
		assertEquals("https://ip-messaging.twilio.com/v1/Credentials/CRaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa",
				request.getURI().toURL().toString());
		assertEquals("DELETE", request.getMethod());
	}
}
