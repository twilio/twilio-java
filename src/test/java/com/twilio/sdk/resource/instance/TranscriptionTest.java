package com.twilio.sdk.resource.instance;

import org.junit.Test;

import static org.junit.Assert.assertTrue;
public class TranscriptionTest extends BasicRequestTester {

    @Test
    public void testDeleteTranscription() throws Exception {
        setExpectedServerAnswer(null);
        setExpectedServerReturnCode(204);
        Transcription tr = new Transcription(restClient, "TR5f539674e9b84c2ba39a4156f264a347");
        assertTrue(tr.delete());
    }
}
