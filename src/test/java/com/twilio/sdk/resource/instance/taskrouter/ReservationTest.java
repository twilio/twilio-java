package com.twilio.sdk.resource.instance.taskrouter;

import com.twilio.sdk.resource.instance.BasicRequestTester;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class ReservationTest extends BasicRequestTester {

	@Before
	public void setup() throws Exception {
		setExpectedServerContentType("application/json");
		setExpectedServerAnswer("/" + getClass().getPackage().getName().replace(".", "/") + "/reservation.json");
	}

	@Test
	public void testGetReservation() throws Exception {
		setExpectedServerReturnCode(200);
		Reservation reservation = taskRouterClient
				.getReservation("WSaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa", "WTaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa",
				                "WRaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa");
		assertNotNull(reservation);
		assertEquals("WRaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa", reservation.getSid());
		assertEquals("reserved", reservation.getReservationStatus());
	}
}
