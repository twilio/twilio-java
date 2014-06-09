package com.twilio.sdk.resource.list;

import com.twilio.sdk.TwilioRestClient;
import org.junit.Test;

import java.util.HashMap;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.mock;

/**
 * @since 2014-06-03
 */
public class TranscriptionListTest {

	private static final String CALL_SID = "CA12345678901234567890123456789012";

	private static final TwilioRestClient CLIENT = mock(TwilioRestClient.class);

	private static final String RECORDING_SID = "RE12345678901234567890123456789012";

	@Test
	public void testCallTranscriptionListTwilioRestClientString() {
		TranscriptionList transcriptions = TranscriptionList.callTranscriptionList(CLIENT, CALL_SID);
		assertNotNull(transcriptions);
		assertEquals(CALL_SID, transcriptions.getRequestCallSid());
	}

	@Test
	public void testCallTranscriptionListTwilioRestClientStringMap() {
		TranscriptionList transcriptions = TranscriptionList
				.callTranscriptionList(CLIENT, CALL_SID, new HashMap<String, String>());
		assertNotNull(transcriptions);
		assertEquals(CALL_SID, transcriptions.getRequestCallSid());
	}

	@Test
	public void testRecordingTranscriptionListTwilioRestClientString() {
		TranscriptionList transcriptions = TranscriptionList.recordingTranscriptionList(CLIENT, RECORDING_SID);
		assertNotNull(transcriptions);
		assertEquals(RECORDING_SID, transcriptions.getRequestRecordingSid());
	}

	@Test
	public void testRecordingTranscriptionListTwilioRestClientStringMap() {
		TranscriptionList transcriptions = TranscriptionList
				.recordingTranscriptionList(CLIENT, RECORDING_SID, new HashMap<String, String>());
		assertNotNull(transcriptions);
		assertEquals(RECORDING_SID, transcriptions.getRequestRecordingSid());
	}

}
