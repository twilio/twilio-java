package com.twilio.exception;

import java.io.ByteArrayInputStream;

import tools.jackson.databind.ObjectMapper;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class RestStandardExceptionTest {

    private static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();

    @Test
    public void fromJsonWithAllFields() {
        final String errorJson = "{\n" +
                "  \"type\": \"https://www.twilio.com/docs/api/errors/20001\",\n" +
                "  \"title\": \"Validation error\",\n" +
                "  \"status\": 400,\n" +
                "  \"detail\": \"The request contains invalid parameter values.\",\n" +
                "  \"instance\": \"/v1/Accounts/AC123/Calls\",\n" +
                "  \"code\": 20001,\n" +
                "  \"errors\": [\n" +
                "    {\n" +
                "      \"detail\": \"The 'From' parameter is required but was not provided.\",\n" +
                "      \"pointer\": \"#/From\"\n" +
                "    },\n" +
                "    {\n" +
                "      \"detail\": \"must be a positive integer\",\n" +
                "      \"pointer\": \"#/age\"\n" +
                "    }\n" +
                "  ]\n" +
                "}";

        final RestStandardException restStandardException = RestStandardException.fromJson(
                new ByteArrayInputStream(errorJson.getBytes()), OBJECT_MAPPER);

        assertEquals("https://www.twilio.com/docs/api/errors/20001", restStandardException.getType());
        assertEquals("Validation error", restStandardException.getTitle());
        assertEquals(400, (int) restStandardException.getStatus());
        assertEquals("The request contains invalid parameter values.", restStandardException.getDetail());
        assertEquals("/v1/Accounts/AC123/Calls", restStandardException.getInstance());
        assertEquals(20001, (int) restStandardException.getCode());

        assertNotNull(restStandardException.getErrors());
        assertEquals(2, restStandardException.getErrors().size());

        assertEquals("The 'From' parameter is required but was not provided.",
                restStandardException.getErrors().get(0).getDetail());
        assertEquals("#/From", restStandardException.getErrors().get(0).getPointer());

        assertEquals("must be a positive integer", restStandardException.getErrors().get(1).getDetail());
        assertEquals("#/age", restStandardException.getErrors().get(1).getPointer());
    }

    @Test
    public void fromJsonWithRequiredFieldsOnly() {
        final String errorJson = "{\n" +
                "  \"type\": \"https://www.twilio.com/docs/api/errors/20003\",\n" +
                "  \"title\": \"Permission denied\",\n" +
                "  \"status\": 403,\n" +
                "  \"code\": 20003\n" +
                "}";

        final RestStandardException restStandardException = RestStandardException.fromJson(
                new ByteArrayInputStream(errorJson.getBytes()), OBJECT_MAPPER);

        assertEquals("https://www.twilio.com/docs/api/errors/20003", restStandardException.getType());
        assertEquals("Permission denied", restStandardException.getTitle());
        assertEquals(403, (int) restStandardException.getStatus());
        assertEquals(20003, (int) restStandardException.getCode());

        assertEquals("", restStandardException.getDetail());
        assertEquals("", restStandardException.getInstance());

        assertNotNull(restStandardException.getErrors());
        assertEquals(0, restStandardException.getErrors().size());
    }
}
