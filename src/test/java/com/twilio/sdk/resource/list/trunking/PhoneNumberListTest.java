package com.twilio.sdk.resource.list.trunking;

import com.twilio.sdk.resource.instance.BasicRequestTester;
import com.twilio.sdk.resource.instance.trunking.PhoneNumber;
import static junit.framework.Assert.*;

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

public class PhoneNumberListTest extends BasicRequestTester {

	@Before
	public void setup() throws Exception {
		setExpectedServerContentType("application/json");
	}

	@Test
	public void testGetPhoneNumbers() throws Exception {
		setExpectedServerReturnCode(200);
		setExpectedServerAnswer(File.separator + getClass().getPackage()
				.getName().replace(".", File.separator) + "/phonenumberlist.json");

		PhoneNumberList phoneNumberQuery = trunkingClient.getTrunk("sid").getPhoneNumbers();
		List<PhoneNumber> numbers = phoneNumberQuery.getPageData();

		assertEquals(1, numbers.size());

		PhoneNumber number = numbers.get(0);
		assertEquals("PNaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa", number.getSid());
		assertEquals("AC0123456789abcdef0123456789abcdef", number.getAccountSid());
		assertEquals("TK68430bd1ea750bb544d7229e23344889", number.getTrunkSid());
		assertEquals("(808) 925-2454", number.getFriendlyName());
		assertEquals("+18089252454", number.getPhoneNumber());
		assertEquals("https://demo.twilio.com/welcome/voice/", number.getVoiceUrl());
		assertEquals("POST", number.getVoiceMethod());
		assertEquals("", number.getVoiceFallbackUrl());
		assertEquals("POST", number.getVoiceFallbackMethod());
		assertFalse(number.isVoiceCallerIdLookup());
		assertEquals("", number.getSmsUrl());
		assertEquals("POST", number.getSmsMethod());
		assertEquals("", number.getSmsFallbackUrl());
		assertEquals("POST", number.getSmsFallbackMethod());
		assertTrue(number.getCapabilities().isVoice());
		assertTrue(number.getCapabilities().isMms());
		assertFalse(number.getCapabilities().isSms());
		assertEquals("", number.getStatusCallback());
		assertEquals("POST", number.getStatusCallbackMethod());
		assertEquals("2010-04-01", number.getApiVersion());
		assertEquals(null, number.getVoiceApplicationSid());
		assertEquals("none", number.getAddressRequirements());
	}

	@Test
	public void testCreateTrunks() throws Exception {
		setExpectedServerReturnCode(201);
		setExpectedServerAnswer(File.separator + getClass().getPackage()
				.getName().replace(".", File.separator) + "/phonenumber.json");

		Map<String, String> params = new HashMap<String, String>();
		params.put("PhoneNumberSid", "PNaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa");
		PhoneNumber number = trunkingClient.associatePhoneNumber(
				"TK68430bd1ea750bb544d7229e23344889", params);

		assertEquals("PNaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa", number.getSid());
		assertEquals("AC0123456789abcdef0123456789abcdef", number.getAccountSid());
		assertEquals("TK68430bd1ea750bb544d7229e23344889", number.getTrunkSid());
		assertEquals("(808) 925-2454", number.getFriendlyName());
		assertEquals("+18089252454", number.getPhoneNumber());
		assertEquals("https://demo.twilio.com/welcome/voice/", number.getVoiceUrl());
		assertEquals("POST", number.getVoiceMethod());
		assertEquals("", number.getVoiceFallbackUrl());
		assertEquals("POST", number.getVoiceFallbackMethod());
		assertFalse(number.isVoiceCallerIdLookup());
		assertEquals("", number.getSmsUrl());
		assertEquals("POST", number.getSmsMethod());
		assertEquals("", number.getSmsFallbackUrl());
		assertEquals("POST", number.getSmsFallbackMethod());
		assertTrue(number.getCapabilities().isVoice());
		assertTrue(number.getCapabilities().isMms());
		assertFalse(number.getCapabilities().isSms());
		assertEquals("", number.getStatusCallback());
		assertEquals("POST", number.getStatusCallbackMethod());
		assertEquals("2010-04-01", number.getApiVersion());
		assertEquals(null, number.getVoiceApplicationSid());
		assertEquals("none", number.getAddressRequirements());

		ArgumentCaptor<HttpPost> captor = ArgumentCaptor.forClass(HttpPost.class);
		Mockito.verify(httpClient).execute(captor.capture());

		HttpPost request = captor.getValue();
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		request.getEntity().writeTo(baos);
		assertEquals("PhoneNumberSid=PNaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa", baos.toString());

		assertEquals("https://trunking.twilio.com/v1/Trunks/TK68430bd1ea750bb544d7229e23344889/PhoneNumbers",
				request.getURI().toURL().toString());
		assertEquals("POST", request.getMethod());
	}
}
