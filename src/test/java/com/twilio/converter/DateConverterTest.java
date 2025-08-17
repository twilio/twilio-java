package com.twilio.converter;

import java.time.ZonedDateTime;
import java.time.LocalDate;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import org.junit.jupiter.api.Test;

import java.util.Locale;

/**
 * Test Class for {@link DateConverter}.
 */
public class DateConverterTest {

    @Test
    public void testRfc2822() {
        ZonedDateTime dt = DateConverter.rfc2822DateTimeFromString("Tue, 29 Mar 2016 13:00:05 +0000");

        assertEquals(2, dt.getDayOfWeek().getValue());
        assertEquals(29, dt.getDayOfMonth());
        assertEquals(3, dt.getMonthValue());
        assertEquals(2016, dt.getYear());
        assertEquals(13, dt.getHour());
        assertEquals(0, dt.getMinute());
        assertEquals(5, dt.getSecond());
    }

    @Test
    public void testInvalidRfc2822() {
        ZonedDateTime dt = DateConverter.rfc2822DateTimeFromString("gibberish");
        assertNull(dt);
    }

    @Test
    public void testIso8601() {
        ZonedDateTime dt = DateConverter.iso8601DateTimeFromString("2016-01-15T21:49:24Z");

        assertEquals(15, dt.getDayOfMonth());
        assertEquals(1, dt.getMonthValue());
        assertEquals(2016, dt.getYear());
        assertEquals(21, dt.getHour());
        assertEquals(49, dt.getMinute());
        assertEquals(24, dt.getSecond());
    }

    @Test
    public void testInvalidIso8601() {
        ZonedDateTime dt = DateConverter.iso8601DateTimeFromString("blanks");
        assertNull(dt);
    }

    @Test
    public void testLocalDate() {
        LocalDate ld = DateConverter.localDateFromString("2016-11-11");

        assertEquals(2016, ld.getYear());
        assertEquals(11, ld.getMonthValue());
        assertEquals(11, ld.getDayOfMonth());
    }

    @Test
    public void testInvalidLocalDate() {
        LocalDate date = DateConverter.localDateFromString("bad");
        assertNull(date);
    }

    @Test
    public void testLocalDateToString() {
        String date = DateConverter.dateStringFromLocalDate(LocalDate.of(2016, 9, 21));
        assertEquals("2016-09-21", date);
    }

    @Test
    public void testDifferentLocaleRFC2822() {
        Locale.setDefault(new Locale("fr", "CA"));
        ZonedDateTime dateTime = DateConverter.rfc2822DateTimeFromString("Tue, 29 Mar 2016 13:00:05 +0000");
        assertNotNull(dateTime);
    }

    @Test
    public void testDifferentLocaleISO8601() {
        Locale.setDefault(new Locale("fr", "CA"));
        ZonedDateTime dateTime = DateConverter.iso8601DateTimeFromString("2016-01-15T21:49:24Z");
        assertNotNull(dateTime);
    }

    @Test
    public void testISO8601DateTimeConversion() {
        String dateTimeString = "2016-01-15T21:49:00Z";
        ZonedDateTime dateTime = DateConverter.iso8601DateTimeFromString(dateTimeString);
        assertEquals(dateTimeString, dateTime.toInstant().toString());
    }

}
