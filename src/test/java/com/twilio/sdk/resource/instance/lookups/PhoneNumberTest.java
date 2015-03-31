package com.twilio.sdk.resource.instance.lookups;

import com.twilio.sdk.resource.instance.BasicRequestTester;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class PhoneNumberTest extends BasicRequestTester {

	@Before
	public void setup() throws Exception {
		setExpectedServerContentType("application/json");
		setExpectedServerAnswer("/" + getClass().getPackage().getName().replace(".", "/") + "/phone_number.json");
	}

	@Test
	public void testGetPhoneNumber() throws Exception {
		setExpectedServerReturnCode(200);
		PhoneNumber pn = lookupsClient.getPhoneNumber("+15108675309", true);
		assertNotNull(pn);
		assertEquals("+15108675309", pn.getPhoneNumber());
		assertEquals("310", pn.getMobileCountryCode());
		assertEquals("456", pn.getMobileNetworkCode());
		assertEquals(PhoneNumber.Type.MOBILE, pn.getType());
		assertEquals("verizon", pn.getCarrierName());
		assertEquals("(510) 867-5309", pn.getFormattedNumber());
	}
}
