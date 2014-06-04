package com.twilio.sdk.resource.instance;

import com.twilio.sdk.TwilioRestClient;
import com.twilio.sdk.resource.list.TranscriptionList;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.mock;

/**
 * @since 2014-06-03
 */
public class RecordingTest {

	private static final TwilioRestClient CLIENT = mock(TwilioRestClient.class);

	private static final String RECORDING_SID = "RE12345678901234567890123456789012";

	@Test
	public void testGetTranscriptions() {
		Recording recording = new Recording(CLIENT, RECORDING_SID);
		TranscriptionList transcriptions = recording.getTranscriptions();
		assertNotNull(transcriptions);
		assertEquals(RECORDING_SID, transcriptions.getRequestRecordingSid());
	}

}
