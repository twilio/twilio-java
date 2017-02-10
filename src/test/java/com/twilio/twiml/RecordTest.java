package com.twilio.twiml;

import org.junit.Assert;
import org.junit.Test;

/**
 * Test class for {@link Record}.
 */
public class RecordTest {

    @Test
    public void testXml() throws TwiMLException {
        Record record = new Record.Builder()
            .action("/record")
            .finishOnKey("3")
            .maxLength(35)
            .method(Method.GET)
            .recordingStatusCallback("/recording-status")
            .recordingStatusCallbackMethod(Method.POST)
            .playBeep(true)
            .timeout(78)
            .transcribe(true)
            .transcribeCallback("/transcribe")
            .trim(Trim.DO_NOT_TRIM)
            .build();

        Assert.assertEquals("<Record transcribe=\"true\" playBeep=\"true\" timeout=\"78\" maxLength=\"35\" action=\"/record\" method=\"GET\" recordingStatusCallback=\"/recording-status\" recordingStatusCallbackMethod=\"POST\" finishOnKey=\"3\" transcribeCallback=\"/transcribe\" trim=\"do-not-trim\"/>", record.toXml());
    }

    @Test
    public void testUrl() throws TwiMLException {
        Record record = new Record.Builder()
            .action("/record")
            .finishOnKey("3")
            .maxLength(35)
            .method(Method.GET)
            .recordingStatusCallback("/recording-status")
            .recordingStatusCallbackMethod(Method.POST)
            .playBeep(true)
            .timeout(78)
            .transcribe(true)
            .transcribeCallback("/transcribe")
            .trim(Trim.DO_NOT_TRIM)
            .build();

        Assert.assertEquals("%3CRecord+transcribe%3D%22true%22+playBeep%3D%22true%22+timeout%3D%2278%22+maxLength%3D%2235%22+action%3D%22%2Frecord%22+method%3D%22GET%22+recordingStatusCallback%3D%22%2Frecording-status%22+recordingStatusCallbackMethod%3D%22POST%22+finishOnKey%3D%223%22+transcribeCallback%3D%22%2Ftranscribe%22+trim%3D%22do-not-trim%22%2F%3E", record.toUrl());
    }
}
