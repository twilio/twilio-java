package com.twilio.type;


import tools.jackson.core.JacksonException;
import tools.jackson.databind.ObjectMapper;

import java.io.IOException;

public abstract class TypeTest {
    final ObjectMapper mapper = new ObjectMapper();

    public <T> T fromJson(String json, Class<T> clazz) throws IOException {
        return mapper.readValue(json, clazz);
    }

    public String toJson(Object object) throws JacksonException {
        return mapper.writeValueAsString(object);
    }

    public <T> boolean convertsToAndFromJson(Object o, Class<T> clazz) throws IOException {
        final String json = toJson(o);
        return fromJson(json, clazz).equals(o);
    }
}
