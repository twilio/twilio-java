package com.twilio.sdk.resource.list.taskrouter;

import com.twilio.sdk.resource.instance.BasicRequestTester;
import com.twilio.sdk.resource.instance.taskrouter.Reservation;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

public class ReservationListTest extends BasicRequestTester {

	@Before
	public void setup() throws Exception {
		setExpectedServerContentType("application/json");
		setExpectedServerAnswer("/" + getClass().getPackage().getName().replace(".", "/") + "/reservations.json");
	}

	@Test
	public void testGetReservations() throws Exception {
		setExpectedServerReturnCode(200);
		ReservationList reservations = taskRouterClient
				.getReservations("WSaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa", "WTaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa");
		assertNotNull(reservations);
		for (Reservation reservation : reservations) {
			assertNotNull(reservation.getSid());
		}
	}
}
