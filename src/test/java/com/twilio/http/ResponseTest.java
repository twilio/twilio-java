package com.twilio.http;

import com.twilio.exception.ApiConnectionException;
import mockit.Mocked;
import mockit.NonStrictExpectations;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

public class ResponseTest {

    @Test(expected = ApiConnectionException.class)
    public void testGetContentIOException(@Mocked final InputStream stream) throws IOException {
        Response response = new Response(stream, TwilioRestClient.HTTP_STATUS_CODE_OK);

        new NonStrictExpectations() {{
            stream.available();
            result = new IOException();
        }};

        response.getContent();
        fail("ApiConnectionException was expected");
    }

    @Test
    public void testGetStream(@Mocked final InputStream stream) {
        Response response = new Response(stream, TwilioRestClient.HTTP_STATUS_CODE_OK);
        assertEquals(stream, response.getStream());
    }

}
