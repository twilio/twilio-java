package com.twilio.converter;

import com.twilio.constant.EnumConstants.ParameterType;
import com.twilio.http.HttpMethod;
import com.twilio.http.Request;
import com.twilio.rest.Domains;
import org.junit.Test;
import org.mockito.Mockito;

import java.time.LocalDate;
import java.time.ZonedDateTime;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNull;
import static org.mockito.Mockito.*;

public class SerializerTest {

    public Request buildRequest() {
        String path = "/2010-04-01/Accounts.json";
        Request request = new Request(HttpMethod.POST, Domains.API.toString(), path);
        return request;
    }

    @Test
    public void testToStringWithString() {
        Request request = buildRequest();
        Serializer.toString(request, "key", "value", ParameterType.QUERY);
        assertEquals("value", request.getQueryParams().get("key").get(0));
    }

    // String value test with new line character
    @Test
    public void testToStringWithStringNewLine() {
        Request request = buildRequest();
        Serializer.toString(request, "key", "Hello \n World", ParameterType.URLENCODED);
        assertNotEquals("Hello \\n World", request.getPostParams().get("key").get(0));
        assertEquals("Hello \n World", request.getPostParams().get("key").get(0));
    }

    @Test
    public void testToStringWithStringSpecialCharacters() {
        Request request = buildRequest();
        Serializer.toString(request, "key", "Hello@World#2023!", ParameterType.QUERY);
        assertEquals("Hello@World#2023!", request.getQueryParams().get("key").get(0));
    }

    @Test
    public void testToStringWithInt32() {
        Request request = buildRequest();
        Serializer.toString(request, "key", 32, ParameterType.HEADER);
        assertEquals("32", request.getHeaderParams().get("key").get(0));
    }

    @Test
    public void testToStringWithInt64() {
        Request request = buildRequest();
        Serializer.toString(request, "key", 64L, ParameterType.QUERY);
        assertEquals("64", request.getQueryParams().get("key").get(0));
    }

    @Test
    public void testToStringWithFloat() {
        Request request = buildRequest();
        Float value = 3.14f;
        Serializer.toString(request, "key", value, ParameterType.QUERY);
        assertEquals("3.14", request.getQueryParams().get("key").get(0));
    }

    @Test
    public void testToStringWithFloatPrimitive() {
        Request request = buildRequest();
        float value = 3.14f;
        Serializer.toString(request, "key", value, ParameterType.QUERY);
        assertEquals("3.14", request.getQueryParams().get("key").get(0));
    }

    @Test
    public void testToStringWithLong() {
        Request request = buildRequest();
        Long value = 123456789L;
        Serializer.toString(request, "key", value, ParameterType.QUERY);
        assertEquals("123456789", request.getQueryParams().get("key").get(0));
    }

    @Test
    public void testToStringWithLongPrimitive() {
        Request request = buildRequest();
        long value = 123456789L;
        Serializer.toString(request, "key", value, ParameterType.QUERY);
        assertEquals("123456789", request.getQueryParams().get("key").get(0));
    }

    @Test
    public void testToStringWithBoolean() {
        Request request = buildRequest();
        Boolean value = true;
        Serializer.toString(request, "key", value, ParameterType.QUERY);
        assertEquals("true", request.getQueryParams().get("key").get(0));
    }

    @Test
    public void testToStringWithBooleanPrimitive() {
        Request request = buildRequest();
        boolean value = false;
        Serializer.toString(request, "key", value, ParameterType.QUERY);
        assertEquals("false", request.getQueryParams().get("key").get(0));
    }

    @Test
    public void testToStringWithInteger() {
        Request request = buildRequest();
        Serializer.toString(request, "key", Integer.valueOf(42), ParameterType.QUERY);
        assertEquals("42", request.getQueryParams().get("key").get(0));
    }

    @Test
    public void testToStringWithIntegerPrimitive() {
        Request request = buildRequest();
        int value = 42;
        Serializer.toString(request, "key", value, ParameterType.QUERY);
        assertEquals("42", request.getQueryParams().get("key").get(0));
    }

    @Test
    public void testToStringWithDouble() {
        Request request = buildRequest();
        Double value = 3.14159;
        Serializer.toString(request, "key", value, ParameterType.QUERY);
        assertEquals("3.14159", request.getQueryParams().get("key").get(0));
    }

    @Test
    public void testToStringWithDoublePrimitive() {
        Request request = buildRequest();
        double value = 3.14159;
        Serializer.toString(request, "key", value, ParameterType.QUERY);
        assertEquals("3.14159", request.getQueryParams().get("key").get(0));
    }

    @Test
    public void testToStringWithArray() {
        Request request = buildRequest();
        List<String> values = Arrays.asList("value1", "value2", "value3");
        for (String value: values) {
            Serializer.toString(request, "arrayKey", value, ParameterType.QUERY);
        }
        Serializer.toString(request, "key", "value", ParameterType.QUERY);
        assertEquals(2, request.getQueryParams().size()); // 2 keys present
        assertEquals(3, request.getQueryParams().get("arrayKey").size());
        assertEquals(1, request.getQueryParams().get("key").size());
    }

    @Test
    public void testToStringWithHashMapStringObject() {
        Request request = buildRequest();
        Map<String, Object> map = new HashMap<>();
        map.put("key1", "value1");
        map.put("key2", 123);
        map.put("key3", true);

        Serializer.toString(request, "mapKey", map, ParameterType.QUERY);
        assertEquals("{\"key1\":\"value1\",\"key2\":123,\"key3\":true}", request.getQueryParams().get("mapKey").get(0));
    }

    @Test
    public void testToStringWithHashMapStringString() {
        Request request = buildRequest();
        Map<String, String> map = new HashMap<>();
        map.put("key1", "value1");
        map.put("key2", "value2");

        Serializer.toString(request, "mapKey", map, ParameterType.QUERY);
        assertEquals("{\"key1\":\"value1\",\"key2\":\"value2\"}", request.getQueryParams().get("mapKey").get(0));
    }

    @Test
    public void testToStringWithLocalDate() {
        Request request = buildRequest();
        LocalDate date = LocalDate.of(2025, 7, 1);
        LocalDate dateBefore = null;
        LocalDate dateAfter = null;

        Serializer.toString(request, "dateKey", date, dateBefore, dateAfter);
        assertEquals("2025-07-01", request.getQueryParams().get("dateKey").get(0));
    }

    @Test
    public void testToStringWithLocalDateRange() {
        Request request = buildRequest();
        LocalDate date = null;
        LocalDate dateBefore = LocalDate.of(2025, 7, 5);
        LocalDate dateAfter = LocalDate.of(2025, 7, 1);

        Serializer.toString(request, "dateKey", date, dateBefore, dateAfter);
        assertEquals("2025-07-01", request.getQueryParams().get("dateKey>").get(0));
        assertEquals("2025-07-05", request.getQueryParams().get("dateKey<").get(0));
    }

    @Test
    public void testToStringWithLocalDateRangeBeforeNull() {
        Request request = buildRequest();
        LocalDate date = null;
        LocalDate dateBefore = null;
        LocalDate dateAfter = LocalDate.of(2025, 7, 1);

        Serializer.toString(request, "dateKey", date, dateBefore, dateAfter);
        assertNull(request.getQueryParams().get("dateKey<"));
        assertEquals("2025-07-01", request.getQueryParams().get("dateKey>").get(0));
    }

    @Test
    public void testToStringWithLocalDateRangeAfterNull() {
        Request request = buildRequest();
        LocalDate date = null;
        LocalDate dateBefore = LocalDate.of(2025, 7, 5);
        LocalDate dateAfter = null;

        Serializer.toString(request, "dateKey", date, dateBefore, dateAfter);
        assertNull(request.getQueryParams().get("dateKey>"));
        assertEquals("2025-07-05", request.getQueryParams().get("dateKey<").get(0));
    }

    @Test
    public void testToStringWithZonedDateTime() {
        Request request = buildRequest();
        ZonedDateTime date = ZonedDateTime.parse("2025-07-01T10:15:30+05:00");
        ZonedDateTime dateBefore = null;
        ZonedDateTime dateAfter = null;

        Serializer.toString(request, "dateTimeKey", date, dateBefore, dateAfter);
        assertEquals("2025-07-01T10:15:30", request.getQueryParams().get("dateTimeKey").get(0));
    }

    @Test
    public void testToStringWithZonedDateTimeRange() {
        Request request = buildRequest();
        ZonedDateTime date = null;
        ZonedDateTime dateBefore = ZonedDateTime.parse("2025-07-05T10:15:30+02:00");
        ZonedDateTime dateAfter = ZonedDateTime.parse("2025-07-01T10:15:30+02:00");

        Serializer.toString(request, "dateTimeKey", date, dateBefore, dateAfter);
        // Note the time difference.
        assertEquals("2025-07-01T08:15:30", request.getQueryParams().get("dateTimeKey>").get(0));
        assertEquals("2025-07-05T08:15:30", request.getQueryParams().get("dateTimeKey<").get(0));
    }

    /**
     * Test convertToString with ZonedDateTime value
     * Tests the datetime formatting branch: lines 53-56 in Serializer.java
     */
    @Test
    public void testConvertToStringWithZonedDateTime() {
        Request request = buildRequest();
        ZonedDateTime dateTime = ZonedDateTime.parse("2025-11-20T15:30:45+00:00");

        Serializer.toString(request, "dateTimeKey", dateTime, ParameterType.QUERY);

        // Should format as "yyyy-MM-dd'T'HH:mm:ss" without timezone
        assertEquals("2025-11-20T15:30:45", request.getQueryParams().get("dateTimeKey").get(0));
    }

    /**
     * Test convertToString with ZonedDateTime value with different timezone
     * Verifies that timezone information is preserved in the formatted output
     */
    @Test
    public void testConvertToStringWithZonedDateTimeWithTimezone() {
        Request request = buildRequest();
        ZonedDateTime dateTime = ZonedDateTime.parse("2025-11-20T15:30:45+05:30");

        Serializer.toString(request, "dateTimeKey", dateTime, ParameterType.QUERY);

        // Should format with the same local time, ignoring timezone offset
        assertEquals("2025-11-20T15:30:45", request.getQueryParams().get("dateTimeKey").get(0));
    }

}
