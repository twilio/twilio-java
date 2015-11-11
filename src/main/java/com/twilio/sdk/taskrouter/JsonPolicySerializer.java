package com.twilio.sdk.taskrouter;

import java.io.IOException;
import java.util.Map;
import java.util.Map.Entry;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

public class JsonPolicySerializer extends JsonSerializer<Policy> {

    @Override
    public void serialize(Policy value, JsonGenerator gen, SerializerProvider serializers) throws IOException, JsonProcessingException {
        gen.writeStartObject();
        if (value.url != null) {
            gen.writeStringField("url", value.url);
        }

        if (value.method != null) {
            gen.writeStringField("method", value.method);
        }

        if (value != null) {
            gen.writeBooleanField("allow", value.allowed);
        }

        gen.writeObjectFieldStart("query_filter");
        if (value.queryFilter != null && !value.queryFilter.isEmpty()) {
            for (Entry<String, FilterRequirement> e : value.queryFilter.entrySet()) {
                gen.writeObjectField(e.getKey(), e.getValue());
            }
        }
        gen.writeEndObject(); // query_filter

        gen.writeObjectFieldStart("post_filter");
        if (value.postFilter != null && !value.postFilter.isEmpty()) {
            for (Map.Entry<String, FilterRequirement> e : value.postFilter.entrySet()) {
                gen.writeObjectField(e.getKey(), e.getValue());
            }
        }
        gen.writeEndObject(); // post_filter

        gen.writeEndObject(); // Entire Object
    }
}
