package com.twilio.exception;

import java.io.ByteArrayInputStream;
import java.util.Collections;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertSame;

@SuppressWarnings("ThrowableInstanceNeverThrown")
public class ApiExceptionTest {

    private static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();

    private final String anyMessage = "message for test";
    private final Throwable anyCause = new RuntimeException("some root cause");
    private final String anyMoreInfo = "more info";
    private final int anyErrorCode = 123;
    private final int anyHttpStatus = 200;

    @Test
    public void singleArgConstructorShouldPreserveMessage() {
        ApiException error = new ApiException(anyMessage);
        assertEquals(anyMessage, error.getMessage());
    }

    @Test
    public void twoArgConstructorShouldPreserveMessageAndCause() {
        ApiException error = new ApiException(anyMessage, anyCause);
        assertEquals("Message", anyMessage, error.getMessage());
        assertSame("Cause", anyCause, error.getCause());
    }

    @Test
    public void fullConstructorShouldPreserveAllValues() {
        ApiException error = new ApiException(anyMessage, anyErrorCode, anyMoreInfo, anyHttpStatus, anyCause);
        assertEquals("Message", anyMessage, error.getMessage());
        assertSame("Cause", anyCause, error.getCause());
        assertEquals("More info", anyMoreInfo, error.getMoreInfo());
        assertEquals("Error code", anyErrorCode, error.getCode().intValue());
        assertEquals("Status code", anyHttpStatus, error.getStatusCode().intValue());
    }

    @Test
    public void restConstructorShouldPreserveValues() {
        final String errorJson = "{\n" +
                                 "  \"code\": 20001,\n" +
                                 "  \"message\": \"Bad request\",\n" +
                                 "  \"more_info\": \"https://www.twilio.com/docs/errors/20001\",\n" +
                                 "  \"status\": 400,\n" +
                                 "  \"details\": {\n" +
                                 "  \t\"foo\":\"bar\"\n" +
                                 "  }\n" +
                                 "}\n";

        final RestException restException = RestException.fromJson(new ByteArrayInputStream(errorJson.getBytes()),
                                                                   OBJECT_MAPPER);
        ApiException error = new ApiException(restException);
        assertEquals(20001, (int) error.getCode());
        assertEquals(400, (int) error.getStatusCode());
        assertEquals("https://www.twilio.com/docs/errors/20001", error.getMoreInfo());
        assertEquals("Bad request", error.getMessage());
        assertEquals("details", Collections.singletonMap("foo", "bar"), error.getDetails());
    }

    @Test
    public void getCodeShouldNotThrowExceptionWhenCodeIsNull() {
        ApiException error = new ApiException(anyMessage);
        assertEquals(null, error.getCode());
    }

    @Test
    public void getStatusCodeShouldNotThrowExceptionWhenCodeIsNull() {
        ApiException error = new ApiException(anyMessage);
        assertEquals(null, error.getStatusCode());
    }

}
