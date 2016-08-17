package com.twilio.converter;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

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

}
