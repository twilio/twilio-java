package com.twilio.http;

import mockit.Mocked;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;

import static org.junit.Assert.assertEquals;

public class ResponseTest {

    @Test
    public void testGetContentInputStream() {
        String testValue = "Test";
        TestInputStream stream = new TestInputStream(testValue);
        Response response = new Response(stream, TwilioRestClient.HTTP_STATUS_CODE_OK);

        assertEquals(testValue, response.getContent());
    }

    @Test
    public void testGetContentString() {
        Response response = new Response("Test", TwilioRestClient.HTTP_STATUS_CODE_OK);
        assertEquals("Test", response.getContent());
    }

    @Test
    public void testGetStream(@Mocked final InputStream stream) {
        Response response = new Response(stream, TwilioRestClient.HTTP_STATUS_CODE_OK);
        assertEquals(stream, response.getStream());
    }
}

class TestInputStream extends InputStream {
    private byte[] string;
    private int count = 0;

    TestInputStream(String testValue) {
        this.string = testValue.getBytes();
    }

    @Override
    public int read() {
        try {
            Thread.sleep(1L); // Sleep so available() can return 0 but there is still more data
        } catch (Exception ignore) { }

        return count++ > string.length - 1 ? -1 : string[count-1];
    }
}
