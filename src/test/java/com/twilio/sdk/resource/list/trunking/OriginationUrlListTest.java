package com.twilio.sdk.resource.list.trunking;

import com.twilio.sdk.resource.instance.BasicRequestTester;
import com.twilio.sdk.resource.instance.trunking.OriginationUrl;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mockito;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class OriginationUrlListTest extends BasicRequestTester {

	@Before
	public void setup() throws Exception {
		setExpectedServerContentType("application/json");
	}

	@Test
	public void testGetOriginationUrls() throws Exception {
		setExpectedServerReturnCode(200);
		setExpectedServerAnswer(File.separator + getClass().getPackage()
				.getName().replace(".", File.separator) + "/originationurllist.json");

		OriginationUrlList originationUrlQuery = trunkingClient.getTrunk("TK68430bd1ea750bb544d7229e23344889").getOriginationUrls();
		List<OriginationUrl> originationUrls = originationUrlQuery.getPageData();

		assertEquals(1, originationUrls.size());

		OriginationUrl url = originationUrls.get(0);
		assertEquals("OUaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa", url.getSid());
		assertEquals("AC0123456789abcdef0123456789abcdef", url.getAccountSid());
		assertEquals("TK68430bd1ea750bb544d7229e23344889", url.getTrunkSid());
		assertEquals(1, url.getWeight().intValue());
		assertTrue(url.isEnabled());
		assertEquals("sip://url", url.getSipUrl());
		assertEquals("name", url.getFriendlyName());
		assertEquals(1, url.getPriority().intValue());

		ArgumentCaptor<HttpGet> captor = ArgumentCaptor.forClass(HttpGet.class);
		Mockito.verify(httpClient).execute(captor.capture());

		HttpGet request = captor.getValue();
		assertEquals("https://trunking.twilio.com/v1/Trunks/TK68430bd1ea750bb544d7229e23344889/OriginationUrls",
				request.getURI().toURL().toString());
		assertEquals("GET", request.getMethod());
	}

	@Test
	public void testCreateOriginationUrls() throws Exception {
		setExpectedServerReturnCode(201);
		setExpectedServerAnswer(File.separator + getClass().getPackage()
				.getName().replace(".", File.separator) + "/originationurl.json");

		Map<String, String> params = new HashMap<String, String>();
		params.put("Weight", "1");
		OriginationUrl url = trunkingClient.createOriginationUrl("TK68430bd1ea750bb544d7229e23344889", params);

		assertEquals("OUaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa", url.getSid());
		assertEquals("AC0123456789abcdef0123456789abcdef", url.getAccountSid());
		assertEquals("TK68430bd1ea750bb544d7229e23344889", url.getTrunkSid());
		assertEquals(1, url.getWeight().intValue());
		assertTrue(url.isEnabled());
		assertEquals("sip://url", url.getSipUrl());
		assertEquals("name", url.getFriendlyName());
		assertEquals(1, url.getPriority().intValue());

		ArgumentCaptor<HttpPost> captor = ArgumentCaptor.forClass(HttpPost.class);
		Mockito.verify(httpClient).execute(captor.capture());

		HttpPost request = captor.getValue();

		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		request.getEntity().writeTo(baos);
		assertEquals("Weight=1", baos.toString());

		assertEquals("https://trunking.twilio.com/v1/Trunks/TK68430bd1ea750bb544d7229e23344889/OriginationUrls",
				request.getURI().toURL().toString());
		assertEquals("POST", request.getMethod());
	}
}
