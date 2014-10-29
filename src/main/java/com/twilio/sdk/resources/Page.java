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

    public String getUri() {
        return uri;
    }

    public void deserialize(String recordKey, String json, Class<T> recordType) {
        ObjectMapper mapper = new ObjectMapper();

        this.records = new ArrayList<T>();

        try {
            JsonNode root = mapper.readTree(json);
            JsonNode records = root.get(recordKey);
            for (JsonNode record : records) {
                this.records.add(mapper.readValue(record.toString(), recordType));
            }
            this.firstPageUri = root.get("first_page_uri").asText();
            this.nextPageUri = root.get("next_page_uri").asText();
            this.previousPageUri = root.get("previous_page_uri").asText();
            this.uri = root.get("uri").asText();
        } catch (IOException e) {
            throw new RuntimeException("Unable to deserialize reponse: " + e.getMessage());
        }
    }


}
