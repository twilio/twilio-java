package com.twilio.converter;

import com.twilio.type.PhoneNumber;
import com.twilio.type.Twiml;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;

public class Promoter {

    /**
     * Create a @see java.net.URI from a string
     *
     * @param url url to convert
     * @return built @see java.net.URI
     */
    public static URI uriFromString(final String url) {
        try {
            return new URI(url);
        } catch (URISyntaxException | NullPointerException e) {
            return null;
        }
    }

    /**
     * Create a @see com.twilio.types.PhoneNumber from a string
     *
     * @param pn PhoneNumber to convert
     * @return built @see com.twilio.types.PhoneNumber
     */
    public static PhoneNumber phoneNumberFromString(final String pn) {
        return new PhoneNumber(pn);
    }

    /**
     * Create a @see com.twilio.types.Twiml from a string
     *
     * @param twiml Twiml to convert
     * @return built @see com.twilio.types.Twiml
     */
    public static Twiml twimlFromString(final String twiml) {
        return new Twiml(twiml);
    }



    /**
     * Create a list from a single element.
     *
     * @param one the single element
     * @param <T> type of the element
     * @return List containing the single element
     */
    public static <T> List<T> listOfOne(final T one) {
        List<T> list = new ArrayList<>();
        list.add(one);
        return list;
    }

    /**
     * Convert a string to a enum type.
     *
     * @param value string value
     * @param values enum values
     * @param <T> enum type
     * @return converted enum if able to convert; null otherwise
     */
    public static <T extends Enum<?>> T enumFromString(final String value, final T[] values) {
        if (value == null) {
            return null;
        }

        for (T v : values) {
            if (v.toString().equalsIgnoreCase(value)) {
                return v;
            }
        }

        return null;
    }


}
