package com.twilio.sdk.resource.instance;

import com.twilio.sdk.TwilioRestClient;
import com.twilio.sdk.TwilioRestException;
import com.twilio.sdk.TwilioRestResponse;
import com.twilio.sdk.resource.list.RecordingList;
import com.twilio.sdk.resource.list.TranscriptionList;
import org.junit.Test;
import org.mockito.Matchers;

import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.stub;

public class CallTest {
	final String callSid = "CA12345678901234567890123456789012";
	final String accountSid = "AC12345678901234567890123456789012";

	TwilioRestClient client = mock(TwilioRestClient.class);
	TwilioRestResponse resp = mock(TwilioRestResponse.class);

	public void setupMocks() {
		HashMap<String, Object> map = new HashMap<String, Object>();
		stub(resp.toMap()).toReturn(map);
		map.put("to", "+19197404420");
		map.put("from", "+19192386150");
		map.put("duration", "3");
		map.put("sid", callSid);
		map.put("account_sid", accountSid);
		map.put("api_version", "2010-04-01");
	}

	@Test
	public void testCreation() throws TwilioRestException {
		setupMocks();
		stub(
				client.safeRequest(Matchers.eq("/2010-04-01/Accounts/" + accountSid + "/Calls/" + callSid
						+ ".json"), Matchers.eq("GET"), Matchers.any(Map.class)))
			.toReturn(resp);
		Call call = new Call(client, callSid);
		call.setRequestAccountSid(accountSid);

		assertTrue(call.getTo().equals("+19197404420"));
		assertTrue(call.getFrom().equals("+19192386150"));

		RecordingList recordings = call.getRecordings();
		assertTrue(recordings.getRequestCallSid().equals(callSid));

		TranscriptionList transcriptions = call.getTranscriptions();
		assertTrue(transcriptions.getRequestCallSid().equals(callSid));

		assertEquals("2010-04-01", call.getApiVersion());

	}
}
