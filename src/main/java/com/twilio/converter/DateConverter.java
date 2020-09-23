package com.twilio.converter;

import java.time.LocalDate;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

public class DateConverter {

    private static final String DATE_PATTERN = "yyyy-MM-dd";
    private static final String RFC2822_DATE_TIME = "EEE, dd MMM yyyy HH:mm:ss Z";

    private static final DateTimeFormatter DATE_FORMATTER =
            DateTimeFormatter.ofPattern(DATE_PATTERN);

    private static final DateTimeFormatter RFC2822_DATE_TIME_FORMATTER =
            DateTimeFormatter.ofPattern(RFC2822_DATE_TIME).withLocale(new Locale("en_US"));

    private static final DateTimeFormatter ISO8601_DATE_TIME_FORMATTER =DateTimeFormatter.ISO_ZONED_DATE_TIME;

    /**
     * Parse a @see java.time.ZonedDateTime from a String.
     *
     * @param dateTimeString timestamp to parse
     * @return parsed @see java.time.ZonedDateTime if parseable, null otherwise
     */
    public static ZonedDateTime rfc2822DateTimeFromString(String dateTimeString) {
        try {
            return ZonedDateTime.parse(dateTimeString, RFC2822_DATE_TIME_FORMATTER);
        } catch (Exception e) {
            return null;
        }
    }

    /**
     * Parse a @see java.time.ZonedDateTime from a String.
     *
     * @param dateTimeString timestamp to parse
     * @return parsed @see java.time.ZonedDateTime if parseable, null otherwise
     */
    public static ZonedDateTime iso8601DateTimeFromString(String dateTimeString) {
        try {
            return ZonedDateTime.parse(dateTimeString, ISO8601_DATE_TIME_FORMATTER);
        } catch (Exception e) {
            return null;
        }
    }

    /**
     * Parse a @see java.time.LocalDate from a String.
     *
     * @param dateString the date String
     * @return parsed @see java.time.LocalDate if parseable, null otherwise;
     */
    public static LocalDate localDateFromString(String dateString) {
        try {
            return LocalDate.parse(dateString, DATE_FORMATTER);
        } catch (Exception e) {
            return null;
        }
    }

    /**
     * Format a @see java.time.LocalDate.
     *
     * @param date date to format
     * @return formatted date in YYYY-MM-DD
     */
    public static String dateStringFromLocalDate(LocalDate date) {
        try {
            return date.format(DATE_FORMATTER);
        } catch (Exception e) {
            return null;
        }
    }
}
