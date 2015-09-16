package com.twilio.sdk.resource.list.ipmessaging;

import com.twilio.sdk.resource.instance.BasicRequestTester;
import com.twilio.sdk.resource.instance.ipmessaging.Channel;
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

public class ChannelListTest extends BasicRequestTester {

	@Test
	public void testGetChannels() throws Exception {
		setExpectedServerReturnCode(200);
		setExpectedServerContentType("application/json");
		setExpectedServerAnswer(File.separator + getClass().getPackage()
				.getName().replace(".", File.separator)
				+ File.separator + "channel_list.json");

		List<Channel> resources = ipMessagingClient
				.getService("ISaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa")
				.getChannels().getPageData();

		assertEquals(1, resources.size());

		Channel resource = resources.get(0);
		assertEquals("CHaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa", resource.getSid());
		assertEquals("ACaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa", resource.getAccountSid());
		assertEquals("ISaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa", resource.getServiceSid());
		assertEquals("adsfadsfasdf", resource.getFriendlyName());
		assertEquals("", resource.getAttributes());
		assertEquals("public", resource.getType());
		assertEquals("dfgsdgds", resource.getCreatedBy());
		assertEquals("https://ip-messaging.twilio.com/v1/Services/ISaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa/Channels/CHaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa",
				resource.getUrl());

		ArgumentCaptor<HttpGet> captor = ArgumentCaptor.forClass(HttpGet.class);
		Mockito.verify(httpClient).execute(captor.capture());

		HttpGet request = captor.getValue();
		assertEquals("https://ip-messaging.twilio.com/v1/Services/ISaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa/Channels",
				request.getURI().toURL().toString());
		assertEquals("GET", request.getMethod());
	}

	@Test
	public void testCreateChannel() throws Exception {
		setExpectedServerReturnCode(201);
		setExpectedServerContentType("application/json");
		setExpectedServerAnswer(File.separator + getClass().getPackage()
				.getName().replace(".", File.separator)
				+ File.separator + "channel_instance.json");

		Channel resource = ipMessagingClient.getService("ISaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa")
				.createChannel(new HashMap<String, String>());
		assertEquals("CHaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa", resource.getSid());
		assertEquals("ACaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa", resource.getAccountSid());
		assertEquals("ISaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa", resource.getServiceSid());
		assertEquals("adsfadsfasdf", resource.getFriendlyName());
		assertEquals("", resource.getAttributes());
		assertEquals("public", resource.getType());
		assertEquals("dfgsdgds", resource.getCreatedBy());
		assertEquals("https://ip-messaging.twilio.com/v1/Services/ISaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa/Channels/CHaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa",
				resource.getUrl());

		ArgumentCaptor<HttpPost> captor = ArgumentCaptor.forClass(HttpPost.class);
		Mockito.verify(httpClient).execute(captor.capture());

		HttpPost request = captor.getValue();
		assertEquals("https://ip-messaging.twilio.com/v1/Services/ISaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa/Channels",
				request.getURI().toURL().toString());
		assertEquals("POST", request.getMethod());
	}

	@Test
	public void testDeleteChannel() throws Exception {
		setExpectedServerReturnCode(204);

		boolean success = ipMessagingClient.getService("ISaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa")
				.getChannel("CHaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa")
				.delete();
		assertTrue(success);

		ArgumentCaptor<HttpDelete> captor = ArgumentCaptor.forClass(HttpDelete.class);
		Mockito.verify(httpClient).execute(captor.capture());

		HttpDelete request = captor.getValue();
		assertEquals("https://ip-messaging.twilio.com/v1/Services/ISaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa/Channels/CHaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa",
				request.getURI().toURL().toString());
		assertEquals("DELETE", request.getMethod());
	}
}
