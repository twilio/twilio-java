package com.twilio.sdk.resource.list.trunking;

import com.twilio.sdk.resource.instance.BasicRequestTester;
import com.twilio.sdk.resource.instance.trunking.IpAccessControlList;
import static junit.framework.Assert.*;

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

public class IpAccessControlListListTest extends BasicRequestTester {

	@Before
	public void setup() throws Exception {
		setExpectedServerContentType("application/json");
	}

	@Test
	public void testGetCredentialLists() throws Exception {
		setExpectedServerReturnCode(200);
		setExpectedServerAnswer(File.separator + getClass().getPackage()
				.getName().replace(".", File.separator) + "/ipaccesscontrollistlist.json");

		IpAccessControlListList query = trunkingClient
				.getTrunk("TK68430bd1ea750bb544d7229e23344889").getIpAccessControlLists();
		List<IpAccessControlList> resources = query.getPageData();

		assertEquals(1, resources.size());

		IpAccessControlList resource = resources.get(0);
		assertEquals(accountSid, resource.getAccountSid());
		assertEquals("IPaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa", resource.getSid());
		assertEquals("TK68430bd1ea750bb544d7229e23344889", resource.getTrunkSid());
		assertEquals("friendly name", resource.getFriendlyName());

		ArgumentCaptor<HttpGet> captor = ArgumentCaptor.forClass(HttpGet.class);
		Mockito.verify(httpClient).execute(captor.capture());

		HttpGet request = captor.getValue();
		assertEquals("https://trunking.twilio.com/v1/Trunks/TK68430bd1ea750bb544d7229e23344889/IpAccessControlLists",
				request.getURI().toURL().toString());
		assertEquals("GET", request.getMethod());
	}

	@Test
	public void testCreateTrunks() throws Exception {
		setExpectedServerReturnCode(201);
		setExpectedServerAnswer(File.separator + getClass().getPackage()
				.getName().replace(".", File.separator) + "/ipaccesscontrollist.json");

		Map<String, String> params = new HashMap<String, String>();
		params.put("IpAccessControlListSid", "IPaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa");
		IpAccessControlList resource = trunkingClient
				.associateIpAccessControlList("TK68430bd1ea750bb544d7229e23344889", params);

		assertEquals(accountSid, resource.getAccountSid());
		assertEquals("IPaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa", resource.getSid());
		assertEquals("TK68430bd1ea750bb544d7229e23344889", resource.getTrunkSid());
		assertEquals("friendly name", resource.getFriendlyName());

		ArgumentCaptor<HttpPost> captor = ArgumentCaptor.forClass(HttpPost.class);
		Mockito.verify(httpClient).execute(captor.capture());

		HttpPost request = captor.getValue();
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		request.getEntity().writeTo(baos);
		assertEquals("IpAccessControlListSid=IPaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa", baos.toString());

		assertEquals("https://trunking.twilio.com/v1/Trunks/TK68430bd1ea750bb544d7229e23344889/IpAccessControlLists",
				request.getURI().toURL().toString());
		assertEquals("POST", request.getMethod());
	}
}

