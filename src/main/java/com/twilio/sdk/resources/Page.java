package com.twilio.sdk.resources;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.twilio.sdk.exceptions.ApiConnectionException;

import java.io.IOException;
import java.net.URI;
import java.util.ArrayList;
import java.util.List;

public class Page<T> {
    private final List<T> records;
    private final String firstPageUri;
    private final String nextPageUri;
    private final String previousPageUri;
    private final String uri;
    private final int pageSize;

    private Page(
        List<T> records,
        String firstPageUri,
        String nextPageUri,
        String previousPageUri,
        String uri,
        int pageSize
    ) {
        this.records = records;
        this.firstPageUri = firstPageUri;
        this.nextPageUri = nextPageUri;
        this.previousPageUri = previousPageUri;
        this.uri = uri;
        this.pageSize = pageSize;
    }

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

    /**
     * Create a new page of data from a json blob.
     *
     * @param recordKey key which holds the records
     * @param json json blob
     * @param recordType resource type
     * @param mapper json parser
     * @param <T> record class type
     * @return a page of records of type T
     */
    public static <T> Page<T> fromJson(String recordKey, String json, Class<T> recordType, ObjectMapper mapper) {
        List<T> results = new ArrayList<>();
        String firstPageUri;
        String nextPageUri;
        String previousPageUri;
        String uri;
        int pageSize;

        try {
            JsonNode root = mapper.readTree(json);
            JsonNode records = root.get(recordKey);
            for (final JsonNode record : records) {
                results.add(mapper.readValue(record.toString(), recordType));
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

                firstPageUri = URI.create(meta.get("first_page_url").asText()).getPath();
                pageSize = meta.get("page_size").asInt();

                nextPageUri = nextPageNode.isNull() ? null : URI.create(nextPageNode.asText()).getPath();
                previousPageUri = previousPageNode.isNull() ? null : URI.create(previousPageNode.asText()).getPath();
            }

        } catch (final IOException e) {
            throw new ApiConnectionException(
                "Unable to deserialize response: " + e.getMessage() + "\nJSON: " + json, e
            );
        }

        return new Page<>(results, firstPageUri, nextPageUri, previousPageUri, uri, pageSize);
    }
}
