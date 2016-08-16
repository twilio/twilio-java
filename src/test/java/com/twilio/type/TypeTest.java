package com.twilio.type;


import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;

public abstract class TypeTest {

    public <T> T fromJson(String json, Class<T> clazz) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        return mapper.readValue(json, clazz);
    }

}
