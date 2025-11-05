package com.twilio.exception;

import java.io.ByteArrayInputStream;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
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

    @Test
    public void restExceptionV10ConstructorShouldPreserveValues() {
        final String errorJson = "{\n" +
                                "  \"code\": 20001,\n" +
                                "  \"message\": \"Bad request\",\n" +
                                "  \"httpStatusCode\": 400,\n" +
                                "  \"userError\": true,\n" +
                                "  \"params\": {\n" +
                                "    \"twilioErrorCodeUrl\": \"https://www.twilio.com/docs/errors/20001\",\n" +
                                "    \"foo\": \"bar\"\n" +
                                "  }\n" +
                                "}\n";

        final RestExceptionV10 restExceptionV10 = RestExceptionV10.fromJson(new ByteArrayInputStream(errorJson.getBytes()),
                                                                OBJECT_MAPPER);
        ApiException error = new ApiException(restExceptionV10);
        assertEquals("Code should match", 20001, (int) error.getCode());
        assertEquals("Message should match", "Bad request", error.getMessage());
        assertEquals("HTTP status code should match", 400, (int) error.getHttpStatusCode());
        assertTrue("User error flag should match", error.getUserError());

        Map<String, Object> expectedParams = new HashMap<>();
        expectedParams.put("twilioErrorCodeUrl", "https://www.twilio.com/docs/errors/20001");
        expectedParams.put("foo", "bar");
        assertEquals("Params should match", expectedParams, error.getParams());
    }

    @Test
    public void getHttpStatusCodeShouldReturnCorrectValue() {
        final int expectedHttpStatus = 429;
        final String errorJson = "{\n" +
                               "  \"code\": 20001,\n" +
                               "  \"message\": \"Rate limited\",\n" +
                               "  \"httpStatusCode\": " + expectedHttpStatus + ",\n" +
                               "  \"userError\": true,\n" +
                               "  \"params\": {}\n" +
                               "}\n";

        final RestExceptionV10 restExceptionV10 = RestExceptionV10.fromJson(new ByteArrayInputStream(errorJson.getBytes()),
                                                              OBJECT_MAPPER);
        ApiException error = new ApiException(restExceptionV10);
        assertEquals("HTTP status code should match", expectedHttpStatus, (int) error.getHttpStatusCode());
    }

    @Test
    public void getUserErrorShouldReturnCorrectValue() {
        // Test with userError = true
        final String errorJsonTrue = "{\n" +
                                  "  \"code\": 20001,\n" +
                                  "  \"message\": \"Invalid parameter\",\n" +
                                  "  \"httpStatusCode\": 400,\n" +
                                  "  \"userError\": true,\n" +
                                  "  \"params\": {}\n" +
                                  "}\n";

        final RestExceptionV10 restExceptionV10True = RestExceptionV10.fromJson(new ByteArrayInputStream(errorJsonTrue.getBytes()),
                                                                OBJECT_MAPPER);
        ApiException errorTrue = new ApiException(restExceptionV10True);
        assertTrue("UserError should be true", errorTrue.getUserError());

        // Test with userError = false
        final String errorJsonFalse = "{\n" +
                                   "  \"code\": 50001,\n" +
                                   "  \"message\": \"System error\",\n" +
                                   "  \"httpStatusCode\": 500,\n" +
                                   "  \"userError\": false,\n" +
                                   "  \"params\": {}\n" +
                                   "}\n";

        final RestExceptionV10 restExceptionV10False = RestExceptionV10.fromJson(new ByteArrayInputStream(errorJsonFalse.getBytes()),
                                                                 OBJECT_MAPPER);
        ApiException errorFalse = new ApiException(restExceptionV10False);
        assertEquals("UserError should be false", false, errorFalse.getUserError());
    }

    @Test
    public void getParamsShouldReturnCorrectValues() {
        final Map<String, Object> expectedParams = new HashMap<>();
        expectedParams.put("resource", "message");
        expectedParams.put("identifier", "SM123");
        expectedParams.put("twilioErrorCodeUrl", "https://www.twilio.com/docs/errors/20001");

        final String errorJson = "{\n" +
                              "  \"code\": 20001,\n" +
                              "  \"message\": \"Resource not found\",\n" +
                              "  \"httpStatusCode\": 404,\n" +
                              "  \"userError\": true,\n" +
                              "  \"params\": {\n" +
                              "    \"resource\": \"message\",\n" +
                              "    \"identifier\": \"SM123\",\n" +
                              "    \"twilioErrorCodeUrl\": \"https://www.twilio.com/docs/errors/20001\"\n" +
                              "  }\n" +
                              "}\n";

        final RestExceptionV10 restExceptionV10 = RestExceptionV10.fromJson(new ByteArrayInputStream(errorJson.getBytes()),
                                                              OBJECT_MAPPER);
        ApiException error = new ApiException(restExceptionV10);
        assertEquals("Params should match", expectedParams, error.getParams());
    }

    @Test
    public void fromJsonShouldHandleRestExceptionV10Format() {
        final String errorJson = "{\n" +
                              "  \"code\": 20001,\n" +
                              "  \"message\": \"Bad request\",\n" +
                              "  \"httpStatusCode\": 400,\n" +
                              "  \"userError\": true,\n" +
                              "  \"params\": {\n" +
                              "    \"twilioErrorCodeUrl\": \"https://www.twilio.com/docs/errors/20001\"\n" +
                              "  }\n" +
                              "}\n";

        final RestExceptionV10 result = RestExceptionV10.fromJson(new ByteArrayInputStream(errorJson.getBytes()),
                                                             OBJECT_MAPPER);

        assertEquals("Code should match", 20001, (int) result.getCode());
        assertEquals("Message should match", "Bad request", result.getMessage());
        assertEquals("HTTP status code should match", 400, (int) result.getHttpStatusCode());
        assertTrue("User error flag should match", result.getUserError());

        Map<String, Object> expectedParams = new HashMap<>();
        expectedParams.put("twilioErrorCodeUrl", "https://www.twilio.com/docs/errors/20001");
        assertEquals("Params should match", expectedParams, result.getParams());
    }
}
