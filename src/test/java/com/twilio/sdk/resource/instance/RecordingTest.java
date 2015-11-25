package com.twilio.sdk.resource.instance;

import com.twilio.sdk.TwilioRestClient;
import com.twilio.sdk.resource.list.TranscriptionList;

import org.junit.Test;
import org.mockito.Mockito;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

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

	@Test
	public void testGetRecording() {

		String accountSid = "AC11111111111111111111111111111111";
		String recordingsSid = "RE11111111111111111111111111111111";
		String callSid = "CA11111111111111111111111111111111";

		Account mAccount = mock(Account.class);
		Recording mRecording = mock(Recording.class);
		when(CLIENT.getAccount(Mockito.anyString())).thenReturn(mAccount);
		when(mAccount.getRecording(Mockito.anyString())).thenReturn(mRecording);
		when(mRecording.getAccountSid()).thenReturn(accountSid);
		when(mRecording.getCallSid()).thenReturn(callSid);
		when(mRecording.getDuration()).thenReturn(10);
		when(mRecording.getApiVersion()).thenReturn("2010-04-01");
		when(mRecording.getPriceUnit()).thenReturn("USD");

		Recording recording = CLIENT.getAccount(accountSid).getRecording(recordingsSid);

		assertNotNull(recording);
		assertEquals("AC11111111111111111111111111111111", recording.getAccountSid());
		assertEquals("CA11111111111111111111111111111111", recording.getCallSid());
		assertEquals(10, recording.getDuration());
		assertEquals("2010-04-01", recording.getApiVersion());
		assertEquals("USD", recording.getPriceUnit());
	}

}
