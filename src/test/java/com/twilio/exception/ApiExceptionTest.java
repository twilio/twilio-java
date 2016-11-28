package com.twilio.exception;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertSame;

@SuppressWarnings("ThrowableInstanceNeverThrown")
public class ApiExceptionTest {

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
