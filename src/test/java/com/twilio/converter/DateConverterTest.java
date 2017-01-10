package com.twilio.converter;

import org.joda.time.DateTime;
import org.joda.time.LocalDate;
import org.junit.Assert;
import org.junit.Test;

import java.util.Locale;

/**
 * Test Class for {@link DateConverter}.
 */
public class DateConverterTest {

    @Test
    public void testRfc2822() {
        DateTime dt = DateConverter.rfc2822DateTimeFromString("Tue, 29 Mar 2016 13:00:05 +0000");

        Assert.assertEquals(2, dt.getDayOfWeek());
        Assert.assertEquals(29, dt.getDayOfMonth());
        Assert.assertEquals(3, dt.getMonthOfYear());
        Assert.assertEquals(2016, dt.getYear());
        Assert.assertEquals(13, dt.getHourOfDay());
        Assert.assertEquals(0, dt.getMinuteOfHour());
        Assert.assertEquals(5, dt.getSecondOfMinute());
    }

    @Test
    public void testInvalidRfc2822() {
        DateTime dt = DateConverter.rfc2822DateTimeFromString("gibberish");
        Assert.assertNull(dt);
    }

    @Test
    public void testIso8601() {
        DateTime dt = DateConverter.iso8601DateTimeFromString("2016-01-15T21:49:24Z");

        Assert.assertEquals(15, dt.getDayOfMonth());
        Assert.assertEquals(1, dt.getMonthOfYear());
        Assert.assertEquals(2016, dt.getYear());
        Assert.assertEquals(21, dt.getHourOfDay());
        Assert.assertEquals(49, dt.getMinuteOfHour());
        Assert.assertEquals(24, dt.getSecondOfMinute());
    }

    @Test
    public void testInvalidIso8601() {
        DateTime dt = DateConverter.iso8601DateTimeFromString("blanks");
        Assert.assertNull(dt);
    }

    @Test
    public void testLocalDate() {
        LocalDate ld = DateConverter.localDateFromString("2016-11-11");

        Assert.assertEquals(2016, ld.getYear());
        Assert.assertEquals(11, ld.getMonthOfYear());
        Assert.assertEquals(11, ld.getDayOfMonth());
    }

    @Test
    public void testInvalidLocalDate() {
        LocalDate date = DateConverter.localDateFromString("bad");
        Assert.assertNull(date);
    }

    @Test
    public void testLocalDateToString() {
        String date = DateConverter.dateStringFromLocalDate(new LocalDate(2016, 9, 21));
        Assert.assertEquals("2016-09-21", date);
    }

    @Test
    public void testDifferentLocaleRFC2822() {
        Locale.setDefault(new Locale("fr", "CA"));
        DateTime dateTime = DateConverter.rfc2822DateTimeFromString("Tue, 29 Mar 2016 13:00:05 +0000");
        Assert.assertNotNull(dateTime);
    }

    @Test
    public void testDifferentLocaleISO8601() {
        Locale.setDefault(new Locale("fr", "CA"));
        DateTime dateTime = DateConverter.iso8601DateTimeFromString("2016-01-15T21:49:24Z");
        Assert.assertNotNull(dateTime);
    }
}
