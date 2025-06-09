package com.twilio.converter;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class Converter {

    private static final ObjectMapper MAPPER = new ObjectMapper();

    /**
     * Convert a generic object to a JSON String.
     *
     * @param value object to convert
     * @return converted JSON string
     */
    public static String objectToJson(Object value) {
        if (value == null) {
            return "null";
        }

        // Primitive wrappers
        if (value instanceof String || value instanceof Number || value instanceof Boolean || value instanceof Character) {
            return value.toString();
        }
        // Arrays: handle any arrays (primitive or object)
        if (value.getClass().isArray()) {
            // For primitive arrays, handle differently
            if (value instanceof int[]) return Arrays.toString((int[]) value);
            if (value instanceof long[]) return Arrays.toString((long[]) value);
            if (value instanceof double[]) return Arrays.toString((double[]) value);
            if (value instanceof float[]) return Arrays.toString((float[]) value);
            if (value instanceof boolean[]) return Arrays.toString((boolean[]) value);
            if (value instanceof byte[]) return Arrays.toString((byte[]) value);
            if (value instanceof short[]) return Arrays.toString((short[]) value);
            if (value instanceof char[]) return Arrays.toString((char[]) value);
            // Object array
            return Arrays.deepToString((Object[]) value);
        }

        // Collection (List, Set, etc.)
        if (value instanceof Collection || value instanceof Map) {
            try {
                return MAPPER.writeValueAsString(value);
            } catch (JsonProcessingException e) {
                return value.toString();
            }
        }
        // Fallback: Try JSON, else toString
        try {
            return MAPPER.writeValueAsString(value);
        } catch (JsonProcessingException e) {
            return value.toString();
        }
    }

    /**
     * Convert a map to a JSON String.
     *
     * @param map map to convert
     * @return converted JSON string
     */
    public static String mapToJson(final Map<String, ? extends Object> map) {
        try {
            return MAPPER.writeValueAsString(map);
        } catch (JsonProcessingException e) {
            return null;
        }
    }

    /**
     * Convert a JSON String to a map.
     *
     * @param json string representing the json hash map
     * @return HashMap read
     */
    public static Map<String, Object> jsonToMap(final String json) {
        try {
            return MAPPER.readValue(json, HashMap.class);
        } catch (IOException e) {
            return null;
        }
    }

}
