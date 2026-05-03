package com.twilio.exception;

import java.io.ByteArrayInputStream;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;

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
        Map<String, String> anyParams = Collections.singletonMap("key", "value");
        ApiException error = new ApiException(anyMessage, anyErrorCode, anyMoreInfo, anyHttpStatus, 502, anyParams, true, anyCause);
        assertEquals("Message", anyMessage, error.getMessage());
        assertSame("Cause", anyCause, error.getCause());
        assertEquals("More info", anyMoreInfo, error.getMoreInfo());
        assertEquals("Error code", anyErrorCode, error.getCode().intValue());
        assertEquals("Status code", anyHttpStatus, error.getStatusCode().intValue());
        assertEquals("HTTP status code", 502, error.getHttpStatusCode().intValue());
        assertEquals("Params", anyParams, error.getParams());
        assertTrue("User error", error.getUserError());
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

    @Test
    public void singleArgConstructorShouldHaveNullNewFields() {
        ApiException error = new ApiException(anyMessage);
        assertNull(error.getHttpStatusCode());
        assertNull(error.getParams());
        assertNull(error.getUserError());
    }

    @Test
    public void fullConstructorWithNullNewFields() {
        ApiException error = new ApiException(anyMessage, anyErrorCode, anyMoreInfo, anyHttpStatus, null, null, null, anyCause);
        assertNull("HTTP status code", error.getHttpStatusCode());
        assertNull("Params", error.getParams());
        assertNull("User error", error.getUserError());
    }

    @Test
    public void fullConstructorWithUserErrorFalse() {
        ApiException error = new ApiException(anyMessage, anyErrorCode, anyMoreInfo, anyHttpStatus, 200, "stringParam", false, null);
        assertEquals(200, error.getHttpStatusCode().intValue());
        assertEquals("stringParam", error.getParams());
        assertFalse(error.getUserError());
    }

    @Test
    public void restConstructorShouldPreserveNewFields() {
        final String errorJson = "{\n" +
                                 "  \"code\": 20001,\n" +
                                 "  \"message\": \"Bad request\",\n" +
                                 "  \"more_info\": \"https://www.twilio.com/docs/errors/20001\",\n" +
                                 "  \"status\": 400,\n" +
                                 "  \"details\": {\n" +
                                 "  \t\"foo\":\"bar\"\n" +
                                 "  },\n" +
                                 "  \"httpStatusCode\": 400,\n" +
                                 "  \"params\": {\"param1\": \"value1\"},\n" +
                                 "  \"userError\": true\n" +
                                 "}\n";

        final RestException restException = RestException.fromJson(new ByteArrayInputStream(errorJson.getBytes()),
                                                                   OBJECT_MAPPER);
        ApiException error = new ApiException(restException);
        assertEquals(400, error.getHttpStatusCode().intValue());
        Map<String, String> expectedParams = new HashMap<>();
        expectedParams.put("param1", "value1");
        assertEquals(expectedParams, error.getParams());
        assertTrue(error.getUserError());
    }

    @Test
    public void twoArgStatusConstructorShouldHaveNullNewFields() {
        ApiException error = new ApiException(anyMessage, anyHttpStatus);
        assertEquals(anyHttpStatus, error.getStatusCode().intValue());
        assertNull(error.getHttpStatusCode());
        assertNull(error.getParams());
        assertNull(error.getUserError());
    }

}
