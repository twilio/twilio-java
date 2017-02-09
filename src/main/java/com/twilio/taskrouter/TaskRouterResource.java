package com.twilio.taskrouter;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

public abstract class TaskRouterResource {

    /**
     * Converts a resource to JSON.
     *
     * @return JSON representation of the resource
     * @throws IOException if unable to transform to JSON
     */
    public String toJson() throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        ByteArrayOutputStream out = new ByteArrayOutputStream();

        mapper.writeValue(out, this);
        return out.toString();
    }

}
