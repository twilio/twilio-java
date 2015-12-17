package com.twilio.sdk.resource.list.ipmessaging;

import com.twilio.sdk.resource.instance.BasicRequestTester;
import com.twilio.sdk.resource.instance.ipmessaging.User;
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

public class UserListTest extends BasicRequestTester {

	@Test
	public void testGetUsers() throws Exception {
		setExpectedServerReturnCode(200);
		setExpectedServerContentType("application/json");
		setExpectedServerAnswer(File.separator + getClass().getPackage()
				.getName().replace(".", File.separator)
				+ File.separator + "user_list.json");

		List<User> resources = ipMessagingClient
				.getService("ISaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa")
				.getUsers().getPageData();

		assertEquals(1, resources.size());

		User resource = resources.get(0);
		assertEquals("USaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa", resource.getSid());
		assertEquals("ACaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa", resource.getAccountSid());
		assertEquals("ISaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa", resource.getServiceSid());
		assertEquals("role_sid", resource.getRoleSid());
		assertEquals("aaa", resource.getIdentity());
		assertEquals("https://ip-messaging.twilio.com/v1/Services/ISaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa/Users/USaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa",
				resource.getUrl());

		ArgumentCaptor<HttpGet> captor = ArgumentCaptor.forClass(HttpGet.class);
		Mockito.verify(httpClient).execute(captor.capture());

		HttpGet request = captor.getValue();
		assertEquals("https://ip-messaging.twilio.com/v1/Services/ISaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa/Users",
				request.getURI().toURL().toString());
		assertEquals("GET", request.getMethod());
	}

	@Test
	public void testCreateUser() throws Exception {
		setExpectedServerReturnCode(201);
		setExpectedServerContentType("application/json");
		setExpectedServerAnswer(File.separator + getClass().getPackage()
				.getName().replace(".", File.separator)
				+ File.separator + "user_instance.json");

		User resource = ipMessagingClient.getService("ISaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa")
				.createUser(new HashMap<String, String>());

		assertEquals("USaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa", resource.getSid());
		assertEquals("ACaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa", resource.getAccountSid());
		assertEquals("ISaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa", resource.getServiceSid());
		assertEquals("role_sid", resource.getRoleSid());
		assertEquals("aaa", resource.getIdentity());
		assertEquals("https://ip-messaging.twilio.com/v1/Services/ISaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa/Users/USaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa",
				resource.getUrl());

		ArgumentCaptor<HttpPost> captor = ArgumentCaptor.forClass(HttpPost.class);
		Mockito.verify(httpClient).execute(captor.capture());

		HttpPost request = captor.getValue();
		assertEquals("https://ip-messaging.twilio.com/v1/Services/ISaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa/Users",
				request.getURI().toURL().toString());
		assertEquals("POST", request.getMethod());
	}

	@Test
	public void testDeleteUser() throws Exception {
		setExpectedServerReturnCode(204);

		boolean success = ipMessagingClient.getService("ISaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa")
				.getUser("USaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa")
				.delete();
		assertTrue(success);

		ArgumentCaptor<HttpDelete> captor = ArgumentCaptor.forClass(HttpDelete.class);
		Mockito.verify(httpClient).execute(captor.capture());

		HttpDelete request = captor.getValue();
		assertEquals("https://ip-messaging.twilio.com/v1/Services/ISaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa/Users/USaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa",
				request.getURI().toURL().toString());
		assertEquals("DELETE", request.getMethod());
	}
}
