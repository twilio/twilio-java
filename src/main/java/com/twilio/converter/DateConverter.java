package com.twilio.converter;

import org.joda.time.DateTime;
import org.joda.time.DateTimeZone;
import org.joda.time.LocalDate;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

import java.util.Locale;

public class DateConverter {

    private static final String DATE_PATTERN = "yyyy-MM-dd";
    private static final String RFC2822_DATE_TIME = "EEE, dd MMM yyyy HH:mm:ss Z";
    private static final String ISO8601_DATE_TIME = "yyyy-MM-dd'T'HH:mm:ssZ";

    private static final DateTimeFormatter DATE_FORMATTER =
        DateTimeFormat.forPattern(DATE_PATTERN).withZone(DateTimeZone.UTC);

    private static final DateTimeFormatter RFC2822_DATE_TIME_FORMATTER =
        DateTimeFormat.forPattern(RFC2822_DATE_TIME).withZone(DateTimeZone.UTC).withLocale(new Locale("en_US"));

    private static final DateTimeFormatter ISO8601_DATE_TIME_FORMATTER =
        DateTimeFormat.forPattern(ISO8601_DATE_TIME).withZone(DateTimeZone.UTC);

    /**
     * Parse a @see org.joda.time.DateTime from a String.
     *
     * @param dateTimeString timestamp to parse
     * @return parsed @see org.joda.time.DateTime if parseable, null otherwise
     */
    public static DateTime rfc2822DateTimeFromString(String dateTimeString) {
        try {
            return DateTime.parse(dateTimeString, RFC2822_DATE_TIME_FORMATTER);
        } catch (Exception e) {
            return null;
        }
    }

    /**
     * Parse a @see org.joda.time.DateTime from a String.
     *
     * @param dateTimeString timestamp to parse
     * @return parsed @see org.joda.time.DateTime if parseable, null otherwise
     */
    public static DateTime iso8601DateTimeFromString(String dateTimeString) {
        try {
            return DateTime.parse(dateTimeString, ISO8601_DATE_TIME_FORMATTER);
        } catch (Exception e) {
            return null;
        }
    }

    /**
     * Parse a @see org.joda.time.LocalDate from a String.
     * 
     * @param dateString the date String
     * @return parsed @see org.joda.time.LocalDate if parseable, null otherwise;
     */
    public static LocalDate localDateFromString(String dateString) {
        try {
            return LocalDate.parse(dateString, DATE_FORMATTER);
        } catch (Exception e) {
            return null;
        }
    }

    /**
     * Format a @see org.joda.time.LocalDate.
     *
     * @param date date to format
     * @return formatted date in YYYY-MM-DD
     */
    public static String dateStringFromLocalDate(LocalDate date) {
        try {
            return date.toString(DATE_PATTERN);
        } catch (Exception e) {
            return null;
        }
    }
}
