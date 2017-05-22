package com.twilio.converter;

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
        } catch (URISyntaxException e) {
            return null;
        }
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
     * Convert a string to a enum type
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
