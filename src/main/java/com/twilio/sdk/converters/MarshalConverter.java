package com.twilio.sdk.converters;

import org.joda.time.DateTime;
import org.joda.time.DateTimeZone;
import org.joda.time.LocalDate;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

public class MarshalConverter {

    private static final String DATE_PATTERN = "yyyy-MM-dd";
    private static final String RFC2822_DATE_TIME = "EEE, dd MMM yyyy HH:mm:ss Z";
    private static final String ISO8601_DATE_TIME = "yyyy-MM-dd'T'HH:mm:ssZ";

    private static final DateTimeFormatter DATE_FORMATTER =
        DateTimeFormat.forPattern(DATE_PATTERN).withZone(DateTimeZone.UTC);

    private static final DateTimeFormatter RFC2822_DATE_TIME_FORMATTER =
        DateTimeFormat.forPattern(RFC2822_DATE_TIME).withZone(DateTimeZone.UTC);

    private static final DateTimeFormatter ISO8601_DATE_TIME_FORMATTER =
        DateTimeFormat.forPattern(ISO8601_DATE_TIME).withZone(DateTimeZone.UTC);

    public static DateTime rfc2822DateTimeFromString(String dateTimeString) {
        try {
            return DateTime.parse(dateTimeString, RFC2822_DATE_TIME_FORMATTER);
        } catch (Exception e) {
            return null;
        }
    }

    public static DateTime iso8601DateTimeFromString(String dateTimeString) {
        try {
            return DateTime.parse(dateTimeString, ISO8601_DATE_TIME_FORMATTER);
        } catch (Exception e) {
            return null;
        }
    }

    public static LocalDate localDateFromString(String dateString) {
        try {
            return LocalDate.parse(dateString, DATE_FORMATTER);
        } catch (Exception e) {
            return null;
        }
    }
}
