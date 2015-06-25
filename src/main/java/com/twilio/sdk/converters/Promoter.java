package com.twilio.sdk.converters;

import java.net.URI;
import java.net.URISyntaxException;

public class Promoter {

    public static URI uriFromString(final String url) {
        try {
            return new URI(url);
        } catch (URISyntaxException e) {
            return null;
        }
    }
}
