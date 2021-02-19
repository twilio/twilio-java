package com.twilio;

import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.assertEquals;

public class Assert {

    public static void assertQueryStringsEqual(final String expected, final String actual) {
        Map<String, String> expectedMap = queryStringToMap(expected, true);
        Map<String, String> actualMap = queryStringToMap(actual);

        assertEquals(expectedMap, actualMap);
    }

    public static void assertUrlsEqual(final URL expected, final URL actual) {
        assertUrlsEqual(expected.toString(), actual.toString());
    }

    public static void assertUrlsEqual(final URL expected, final String actual) {
        assertUrlsEqual(expected.toString(), actual);
    }

    public static void assertUrlsEqual(final String expected, final URL actual) {
        assertUrlsEqual(expected, actual.toString());
    }

    public static void assertUrlsEqual(final String expected, final String actual) {
        String[] expectedParts = expected.split("[?#]");
        String[] actualParts = actual.split("[?#]");

        assertEquals(expectedParts.length, actualParts.length);
        assertEquals(expectedParts[0], actualParts[0]);

        if (expectedParts.length >= 2) {
            assertQueryStringsEqual(expectedParts[1], actualParts[1]);
        }
        if (expectedParts.length == 3) {
            assertEquals(expectedParts[2], actualParts[2]);
        }
    }

    private static Map<String, String> queryStringToMap(final String queryString) {
        return queryStringToMap(queryString, false);
    }

    private static Map<String, String> queryStringToMap(final String queryString, final boolean encode) {
        Map<String, String> result = new HashMap<>();
        String[] parts = queryString.split("&");
        for (final String part : parts) {
            String[] keyValue = part.split("=");
            if (keyValue.length == 2) {
                String key = keyValue[0];
                String value = keyValue[1];

                if (encode) {
                    try {
                        key = URLEncoder.encode(key, "UTF-8");
                    } catch (final UnsupportedEncodingException e) {
                        // Ignore, just use the non-encoded
                    }

                    try {
                        value = URLEncoder.encode(value, "UTF-8");
                    } catch (final UnsupportedEncodingException e) {
                        // Ignore, just use the non-encoded
                    }
                }

                result.put(key, value);
            }
        }
        return result;
    }
}
