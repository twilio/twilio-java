package com.twilio.sdk.resources;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.twilio.sdk.exceptions.ApiConnectionException;

import java.io.IOException;
import java.net.URI;
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

    public void deserialize(final String recordKey, final String json, final Class<T> recordType,
                            final ObjectMapper objectMapper) {
        records = new ArrayList<>();

        try {
            JsonNode root = objectMapper.readTree(json);
            JsonNode records = root.get(recordKey);
            for (final JsonNode record : records) {
                this.records.add(objectMapper.readValue(record.toString(), recordType));
            }

            JsonNode nextPageNode = root.get("next_page_uri");
            JsonNode previousPageNode = root.get("previous_page_uri");
            JsonNode uriNode = root.get("uri");

            if (uriNode != null) {
                uri = root.get("uri").asText();
                firstPageUri = root.get("first_page_uri").asText();
                pageSize = root.get("page_size").asInt();

                nextPageUri = nextPageNode.isNull() ? null : nextPageNode.asText();
                previousPageUri = previousPageNode.isNull() ? null : previousPageNode.asText();
            } else {
                JsonNode meta = root.get("meta");
                uri = URI.create(meta.get("url").asText()).getPath();
                nextPageNode = meta.get("next_page_url");
                previousPageNode = meta.get("previous_page_url");

                firstPageUri = meta.get("first_page_url").asText();
                pageSize = meta.get("page_size").asInt();

                nextPageUri = nextPageNode.isNull() ? null : URI.create(nextPageNode.asText()).getPath();
                previousPageUri = previousPageNode.isNull() ? null : URI.create(previousPageNode.asText()).getPath();
            }

        } catch (final IOException e) {
            throw new ApiConnectionException("Unable to deserialize response: " + e.getMessage() + "\nJSON: " + json, e);
        }
    }

}
