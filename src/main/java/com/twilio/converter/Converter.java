package com.twilio.converter;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class Converter {

    private static final ObjectMapper MAPPER = new ObjectMapper();

    /**
     * Convert a map to a JSON String.
     *
     * @param map map to convert
     * @return converted JSON string
     */
    public static String mapToJson(final Map<String, Object> map) {
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
