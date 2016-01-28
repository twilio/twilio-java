package com.twilio.sdk.converters;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;

public class Promoter {

    public static URI uriFromString(final String url) {
        try {
            return new URI(url);
        } catch (URISyntaxException e) {
            return null;
        }
    }

    public static <T> List<T> listOfOne(final T one) {
        ArrayList<T> list = new ArrayList<T>();
        list.add(one);
        return list;
    }
}
