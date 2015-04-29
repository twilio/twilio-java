package com.twilio.sdk.resource.list;

import com.twilio.sdk.TwilioRestClient;
import com.twilio.sdk.resource.instance.Call;
import org.junit.Test;

import java.util.HashMap;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.mock;

public class NotificationListTest {

	private static final String CALL_SID = "CA12345678901234567890123456789012";

	private static final TwilioRestClient CLIENT = mock(TwilioRestClient.class);

	@Test
	public void testCallNotificationListTwilioRestClientString() {
		NotificationList notifications = new NotificationList(CLIENT, CALL_SID);
		assertNotNull(notifications);
		assertEquals(CALL_SID, notifications.getRequestCallSid());
	}

	@Test
	public void testCallNotificationListTwilioRestClientStringStringMap() {
		NotificationList notifications = new NotificationList(CLIENT, CALL_SID, new HashMap<String, String>());
		assertNotNull(notifications);
		assertEquals(CALL_SID, notifications.getRequestCallSid());
	}

	@Test
	public void testCallGetNotifications() {
		Call call = new Call(CLIENT, CALL_SID);
		NotificationList notifications = call.getNotifications();
		assertNotNull(notifications);
		assertEquals(CALL_SID, notifications.getRequestCallSid());
	}

}
