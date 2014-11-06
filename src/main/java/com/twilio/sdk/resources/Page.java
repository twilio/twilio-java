package com.twilio.sdk.resources;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Page<T> {
    protected List<T> records;
    protected String firstPageUri;
    protected String nextPageUri;
    protected String previousPageUri;
    protected String uri;
    protected int pageSize;

    public List<T> getRecords() {
        return records;
    }

    public String getFirstPageUri() {
        return firstPageUri;
    }

    public String getNextPageUri() {
        return nextPageUri;
    }

    public String getPreviousPageUri() {
        return previousPageUri;
    }

    public int getPageSize() {
        return pageSize;
    }

    public String getUri() {
        return uri;
    }

    public void deserialize(final String recordKey, final String json, final Class<T> recordType) {
        ObjectMapper mapper = new ObjectMapper();

        records = new ArrayList<>();

        try {
            JsonNode root = mapper.readTree(json);
            JsonNode records = root.get(recordKey);
            for (final JsonNode record : records) {
                this.records.add(mapper.readValue(record.toString(), recordType));
            }
            firstPageUri = root.get("first_page_uri").asText();
            nextPageUri = root.get("next_page_uri").asText();
            previousPageUri = root.get("previous_page_uri").asText();
            uri = root.get("uri").asText();
            pageSize = root.get("page_size").asInt();
        } catch (final IOException e) {
            throw new RuntimeException("Unable to deserialize response: " + e.getMessage());
        }
    }


}
