package com.twilio.sdk.resource.list.ipmessaging;

import com.twilio.sdk.resource.instance.BasicRequestTester;
import com.twilio.sdk.resource.instance.ipmessaging.Member;
import static junit.framework.Assert.*;

import org.apache.http.client.methods.HttpDelete;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mockito;

import java.io.File;
import java.util.HashMap;
import java.util.List;

public class MemberListTest extends BasicRequestTester {

	@Test
	public void testGetMembers() throws Exception {
		setExpectedServerReturnCode(200);
		setExpectedServerContentType("application/json");
		setExpectedServerAnswer(File.separator + getClass().getPackage()
				.getName().replace(".", File.separator)
				+ File.separator + "member_list.json");

		List<Member> resources = ipMessagingClient
				.getService("ISaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa")
				.getChannel("CHaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa")
				.getMembers().getPageData();

		assertEquals(1, resources.size());

		Member resource = resources.get(0);
		assertEquals("MBaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa", resource.getSid());
		assertEquals("CHaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa", resource.getChannelSid());
		assertEquals("ACaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa", resource.getAccountSid());
		assertEquals("ISaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa", resource.getServiceSid());
		assertEquals("USER", resource.getIdentity());
		assertEquals("role", resource.getRoleSid());
		assertEquals("https://ip-messaging.stage.twilio.com/v1/Services/ISaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa/Channels/CHaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa/Members/MBaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa",
				resource.getUrl());

		ArgumentCaptor<HttpGet> captor = ArgumentCaptor.forClass(HttpGet.class);
		Mockito.verify(httpClient).execute(captor.capture());

		HttpGet request = captor.getValue();
		assertEquals("https://ip-messaging.twilio.com/v1/Services/ISaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa/Channels/CHaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa/Members",
				request.getURI().toURL().toString());
		assertEquals("GET", request.getMethod());
	}

	@Test
	public void testCreateMember() throws Exception {
		setExpectedServerReturnCode(201);
		setExpectedServerContentType("application/json");
		setExpectedServerAnswer(File.separator + getClass().getPackage()
				.getName().replace(".", File.separator)
				+ File.separator + "member_instance.json");

		Member resource = ipMessagingClient.getService("ISaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa")
				.getChannel("CHaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa")
				.createMember(new HashMap<String, String>());
		assertEquals("MBaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa", resource.getSid());
		assertEquals("CHaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa", resource.getChannelSid());
		assertEquals("ACaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa", resource.getAccountSid());
		assertEquals("ISaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa", resource.getServiceSid());
		assertEquals("USER", resource.getIdentity());
		assertEquals("role", resource.getRoleSid());
		assertEquals("https://ip-messaging.stage.twilio.com/v1/Services/ISaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa/Channels/CHaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa/Members/MBaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa",
				resource.getUrl());

		ArgumentCaptor<HttpPost> captor = ArgumentCaptor.forClass(HttpPost.class);
		Mockito.verify(httpClient).execute(captor.capture());

		HttpPost request = captor.getValue();
		assertEquals("https://ip-messaging.twilio.com/v1/Services/ISaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa/Channels/CHaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa/Members",
				request.getURI().toURL().toString());
		assertEquals("POST", request.getMethod());
	}

	@Test
	public void testDeleteMember() throws Exception {
		setExpectedServerReturnCode(204);

		boolean success = ipMessagingClient.getService("ISaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa")
				.getChannel("CHaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa")
				.getMember("MBaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa")
				.delete();
		assertTrue(success);

		ArgumentCaptor<HttpDelete> captor = ArgumentCaptor.forClass(HttpDelete.class);
		Mockito.verify(httpClient).execute(captor.capture());

		HttpDelete request = captor.getValue();
		assertEquals("https://ip-messaging.twilio.com/v1/Services/ISaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa/Channels/CHaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa/Members/MBaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa",
				request.getURI().toURL().toString());
		assertEquals("DELETE", request.getMethod());
	}
}
