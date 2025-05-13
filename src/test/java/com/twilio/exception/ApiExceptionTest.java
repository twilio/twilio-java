package com.twilio.exception;

import java.io.ByteArrayInputStream;
import java.util.Collections;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertSame;

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
        assertEquals(anyMessage, error.getMessage());
        assertSame(anyCause, error.getCause());
    }

    @Test
    public void fullConstructorShouldPreserveAllValues() {
        ApiException error = new ApiException(anyMessage, anyErrorCode, anyMoreInfo, anyHttpStatus, anyCause);
        assertEquals(anyMessage, error.getMessage());
        assertSame(anyCause, error.getCause());
        assertEquals(anyMoreInfo, error.getMoreInfo());
        assertEquals(anyErrorCode, error.getCode().intValue());
        assertEquals(anyHttpStatus, error.getStatusCode().intValue());
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
        assertEquals(Collections.singletonMap("foo", "bar"), error.getDetails());
    }

    @Test
    public void getCodeShouldNotThrowExceptionWhenCodeIsNull() {
        ApiException error = new ApiException(anyMessage);
      assertNull(error.getCode());
    }

    @Test
    public void getStatusCodeShouldNotThrowExceptionWhenCodeIsNull() {
        ApiException error = new ApiException(anyMessage);
      assertNull(error.getStatusCode());
    }

}
