package com.twilio.sdk.resource.list.ipmessaging;

import com.twilio.sdk.resource.instance.BasicRequestTester;
import com.twilio.sdk.resource.instance.ipmessaging.Message;
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

public class MessageListTest extends BasicRequestTester {

	@Test
	public void testGetMessages() throws Exception {
		setExpectedServerReturnCode(200);
		setExpectedServerContentType("application/json");
		setExpectedServerAnswer(File.separator + getClass().getPackage()
				.getName().replace(".", File.separator)
				+ File.separator + "message_list.json");

		List<Message> resources = ipMessagingClient
				.getService("ISaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa")
				.getChannel("CHaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa")
				.getMessages().getPageData();

		assertEquals(1, resources.size());

		Message resource = resources.get(0);
		assertEquals("IMaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa", resource.getSid());
		assertEquals("CHaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa", resource.getTo());
		assertEquals("ACaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa", resource.getAccountSid());
		assertEquals("ISaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa", resource.getServiceSid());
		assertTrue(resource.isWasEdited());
		assertEquals("test", resource.getFrom());
		assertEquals("testetstestestset", resource.getBody());
		assertEquals("https://ip-messaging.twilio.com/v1/Services/ISaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa/Channels/CHaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa/Messages/IMaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa",
				resource.getUrl());

		ArgumentCaptor<HttpGet> captor = ArgumentCaptor.forClass(HttpGet.class);
		Mockito.verify(httpClient).execute(captor.capture());

		HttpGet request = captor.getValue();
		assertEquals("https://ip-messaging.twilio.com/v1/Services/ISaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa/Channels/CHaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa/Messages",
				request.getURI().toURL().toString());
		assertEquals("GET", request.getMethod());
	}

	@Test
	public void testCreateMessage() throws Exception {
		setExpectedServerReturnCode(201);
		setExpectedServerContentType("application/json");
		setExpectedServerAnswer(File.separator + getClass().getPackage()
				.getName().replace(".", File.separator)
				+ File.separator + "message_instance.json");

		Message resource = ipMessagingClient.getService("ISaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa")
				.getChannel("CHaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa")
				.createMessage(new HashMap<String, String>());
		assertEquals("IMaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa", resource.getSid());
		assertEquals("CHaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa", resource.getTo());
		assertEquals("ACaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa", resource.getAccountSid());
		assertEquals("ISaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa", resource.getServiceSid());
		assertFalse(resource.isWasEdited());
		assertEquals("test", resource.getFrom());
		assertEquals("testetstestestset", resource.getBody());
		assertEquals("https://ip-messaging.twilio.com/v1/Services/ISaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa/Channels/CHaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa/Messages/IMaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa",
				resource.getUrl());

		ArgumentCaptor<HttpPost> captor = ArgumentCaptor.forClass(HttpPost.class);
		Mockito.verify(httpClient).execute(captor.capture());

		HttpPost request = captor.getValue();
		assertEquals("https://ip-messaging.twilio.com/v1/Services/ISaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa/Channels/CHaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa/Messages",
				request.getURI().toURL().toString());
		assertEquals("POST", request.getMethod());
	}

	@Test
	public void testDeleteMessage() throws Exception {
		setExpectedServerReturnCode(204);

		boolean success = ipMessagingClient.getService("ISaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa")
				.getChannel("CHaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa")
				.getMessage("IMaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa")
				.delete();
		assertTrue(success);

		ArgumentCaptor<HttpDelete> captor = ArgumentCaptor.forClass(HttpDelete.class);
		Mockito.verify(httpClient).execute(captor.capture());

		HttpDelete request = captor.getValue();
		assertEquals("https://ip-messaging.twilio.com/v1/Services/ISaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa/Channels/CHaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa/Messages/IMaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa",
				request.getURI().toURL().toString());
		assertEquals("DELETE", request.getMethod());
	}
}
