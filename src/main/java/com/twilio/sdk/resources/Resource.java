package com.twilio.sdk.resources;

import com.twilio.sdk.Twilio;
import org.joda.time.DateTime;
import org.joda.time.LocalDate;

import java.io.Serializable;

public abstract class Resource implements Serializable {

    private static final long serialVersionUID = -5898012691404059595L;

    protected DateTime safeDateTimeConvert(String dateTimeString) {
        try {
            return DateTime.parse(dateTimeString, Twilio.DATE_TIME_FORMATTER);
        } catch (Exception e) {
            return null;
        }
    }

    protected LocalDate safeDateConvert(String dateString) {
        try {
            return LocalDate.parse(dateString, Twilio.DATE_FORMATTER);
        } catch (Exception e) {
            return null;
        }
    }
}
