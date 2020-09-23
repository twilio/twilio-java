package com.twilio.converter;

import java.time.ZonedDateTime;
import java.time.LocalDate;
import org.junit.Assert;
import org.junit.Test;

import java.util.Locale;

/**
 * Test Class for {@link DateConverter}.
 */
public class DateConverterTest {

    @Test
    public void testRfc2822() {
        ZonedDateTime dt = DateConverter.rfc2822DateTimeFromString("Tue, 29 Mar 2016 13:00:05 +0000");

        Assert.assertEquals(2, dt.getDayOfWeek().getValue());
        Assert.assertEquals(29, dt.getDayOfMonth());
        Assert.assertEquals(3, dt.getMonthValue());
        Assert.assertEquals(2016, dt.getYear());
        Assert.assertEquals(13, dt.getHour());
        Assert.assertEquals(0, dt.getMinute());
        Assert.assertEquals(5, dt.getSecond());
    }

    @Test
    public void testInvalidRfc2822() {
        ZonedDateTime dt = DateConverter.rfc2822DateTimeFromString("gibberish");
        Assert.assertNull(dt);
    }

    @Test
    public void testIso8601() {
        ZonedDateTime dt = DateConverter.iso8601DateTimeFromString("2016-01-15T21:49:24Z");

        Assert.assertEquals(15, dt.getDayOfMonth());
        Assert.assertEquals(1, dt.getMonthValue());
        Assert.assertEquals(2016, dt.getYear());
        Assert.assertEquals(21, dt.getHour());
        Assert.assertEquals(49, dt.getMinute());
        Assert.assertEquals(24, dt.getSecond());
    }

    @Test
    public void testInvalidIso8601() {
        ZonedDateTime dt = DateConverter.iso8601DateTimeFromString("blanks");
        Assert.assertNull(dt);
    }

    @Test
    public void testLocalDate() {
        LocalDate ld = DateConverter.localDateFromString("2016-11-11");

        Assert.assertEquals(2016, ld.getYear());
        Assert.assertEquals(11, ld.getMonthValue());
        Assert.assertEquals(11, ld.getDayOfMonth());
    }

    @Test
    public void testInvalidLocalDate() {
        LocalDate date = DateConverter.localDateFromString("bad");
        Assert.assertNull(date);
    }

    @Test
    public void testLocalDateToString() {
        String date = DateConverter.dateStringFromLocalDate(LocalDate.of(2016, 9, 21));
        Assert.assertEquals("2016-09-21", date);
    }

    @Test
    public void testDifferentLocaleRFC2822() {
        Locale.setDefault(new Locale("fr", "CA"));
        ZonedDateTime dateTime = DateConverter.rfc2822DateTimeFromString("Tue, 29 Mar 2016 13:00:05 +0000");
        Assert.assertNotNull(dateTime);
    }

    @Test
    public void testDifferentLocaleISO8601() {
        Locale.setDefault(new Locale("fr", "CA"));
        ZonedDateTime dateTime = DateConverter.iso8601DateTimeFromString("2016-01-15T21:49:24Z");
        Assert.assertNotNull(dateTime);
    }
}
