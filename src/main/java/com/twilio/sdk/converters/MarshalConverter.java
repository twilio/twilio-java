package com.twilio.sdk.converters;

import com.twilio.sdk.Twilio;
import org.joda.time.DateTime;
import org.joda.time.LocalDate;

public class MarshalConverter {

    public static DateTime dateTimeFromString(String dateTimeString) {
        try {
            return DateTime.parse(dateTimeString, Twilio.DATE_TIME_FORMATTER);
        } catch (Exception e) {
            return null;
        }
    }

    public static LocalDate localDateFromString(String dateString) {
        try {
            return LocalDate.parse(dateString, Twilio.DATE_FORMATTER);
        } catch (Exception e) {
            return null;
        }
    }
}
