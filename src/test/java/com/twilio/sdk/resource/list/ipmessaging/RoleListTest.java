package com.twilio.sdk.resource.list.ipmessaging;

import com.twilio.sdk.resource.instance.BasicRequestTester;
import com.twilio.sdk.resource.instance.ipmessaging.Role;
import com.twilio.sdk.resource.instance.ipmessaging.User;
import org.apache.http.client.methods.HttpDelete;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mockito;

import java.io.File;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertTrue;

public class RoleListTest extends BasicRequestTester {

	@Test
	public void testGetRoles() throws Exception {
		setExpectedServerReturnCode(200);
		setExpectedServerContentType("application/json");
		setExpectedServerAnswer(File.separator + getClass().getPackage()
				.getName().replace(".", File.separator)
				+ File.separator + "role_list.json");

		List<Role> resources = ipMessagingClient
				.getService("ISaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa")
				.getRoles().getPageData();

		assertEquals(1, resources.size());

		Role resource = resources.get(0);
		assertEquals("RLaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa", resource.getSid());
		assertEquals("ACaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa", resource.getAccountSid());
		assertEquals("ISaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa", resource.getServiceSid());
		assertEquals("admin", resource.getFriendlyName());
		assertEquals("deployment", resource.getType());
		assertEquals(Arrays.asList(new String[]{"createChannel", "sendMessage", "joinChannel"}),
				resource.getPermissions());
		assertEquals("https://ip-messaging.stage.twilio.com/v1/Services/ISaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa/Roles/RLaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa",
				resource.getUrl());

		ArgumentCaptor<HttpGet> captor = ArgumentCaptor.forClass(HttpGet.class);
		Mockito.verify(httpClient).execute(captor.capture());

		HttpGet request = captor.getValue();
		assertEquals("https://ip-messaging.twilio.com/v1/Services/ISaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa/Roles",
				request.getURI().toURL().toString());
		assertEquals("GET", request.getMethod());
	}

	@Test
	public void testCreateRol() throws Exception {
		setExpectedServerReturnCode(201);
		setExpectedServerContentType("application/json");
		setExpectedServerAnswer(File.separator + getClass().getPackage()
				.getName().replace(".", File.separator)
				+ File.separator + "role_instance.json");

		Role resource = ipMessagingClient.getService("ISaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa")
				.createRole(new HashMap<String, String>());
		assertEquals("RLaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa", resource.getSid());
		assertEquals("ACaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa", resource.getAccountSid());
		assertEquals("ISaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa", resource.getServiceSid());
		assertEquals("admin", resource.getFriendlyName());
		assertEquals("deployment", resource.getType());
		assertEquals(Arrays.asList(new String[]{"createChannel", "sendMessage", "joinChannel"}),
				resource.getPermissions());
		assertEquals("https://ip-messaging.stage.twilio.com/v1/Services/ISaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa/Roles/RLaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa",
				resource.getUrl());

		ArgumentCaptor<HttpPost> captor = ArgumentCaptor.forClass(HttpPost.class);
		Mockito.verify(httpClient).execute(captor.capture());

		HttpPost request = captor.getValue();
		assertEquals("https://ip-messaging.twilio.com/v1/Services/ISaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa/Roles",
				request.getURI().toURL().toString());
		assertEquals("POST", request.getMethod());
	}

	@Test
	public void testDeleteRole() throws Exception {
		setExpectedServerReturnCode(204);

		boolean success = ipMessagingClient.getService("ISaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa")
				.getRole("RLaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa")
				.delete();
		assertTrue(success);

		ArgumentCaptor<HttpDelete> captor = ArgumentCaptor.forClass(HttpDelete.class);
		Mockito.verify(httpClient).execute(captor.capture());

		HttpDelete request = captor.getValue();
		assertEquals("https://ip-messaging.twilio.com/v1/Services/ISaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa/Roles/RLaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa",
				request.getURI().toURL().toString());
		assertEquals("DELETE", request.getMethod());
	}
}
