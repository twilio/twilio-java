package com.twilio.sdk.verbs;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class RecordTest {

    @Test
    public void testRecord() {
        final Record record = new Record();
        record.setTranscribe(false);
        record.setAction("http://action.url.com");
        record.setPlayBeep(true);
        assertEquals("<Record transcribe=\"false\" action=\"http://action.url.com\" playBeep=\"true\"></Record>",
                record.toXML());
    }
}
