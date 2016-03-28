package com.twilio.sdk.resource;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.twilio.sdk.exception.ApiConnectionException;

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

    private Page(Builder<T> b) {
        this.records = b.records;
        this.firstPageUri = b.firstPageUri;
        this.nextPageUri = b.nextPageUri;
        this.previousPageUri = b.previousPageUri;
        this.uri = b.uri;
        this.pageSize = b.pageSize;
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
        try {
            List<T> results = new ArrayList<>();
            JsonNode root = mapper.readTree(json);
            JsonNode records = root.get(recordKey);
            for (final JsonNode record : records) {
                results.add(mapper.readValue(record.toString(), recordType));
            }

            JsonNode uriNode = root.get("uri");
            if (uriNode != null) {
                return buildPage(root, results);
            } else {
                return buildNextGenPage(root, results);
            }

        } catch (final IOException e) {
            throw new ApiConnectionException(
                "Unable to deserialize response: " + e.getMessage() + "\nJSON: " + json, e
            );
        }
    }

    private static <T> Page<T> buildPage(JsonNode root, List<T> results) {
        Builder<T> builder = new Builder<T>()
            .uri(root.get("uri").asText());

        JsonNode nextPageNode = root.get("next_page_uri");
        if (nextPageNode != null && !nextPageNode.isNull()) {
            builder.nextPageUri(nextPageNode.asText());
        }

        JsonNode previousPageNode = root.get("previous_page_uri");
        if (previousPageNode != null && !previousPageNode.isNull()) {
            builder.previousPageUri(previousPageNode.asText());
        }

        JsonNode firstPageNode = root.get("first_page_uri");
        if (firstPageNode != null && !firstPageNode.isNull()) {
            builder.firstPageUri(firstPageNode.asText());
        }

        JsonNode pageSizeNode = root.get("page_size");
        if (pageSizeNode != null && !pageSizeNode.isNull()) {
            builder.pageSize(pageSizeNode.asInt());
        } else {
            builder.pageSize(results.size());
        }

        return builder.records(results).build();
    }

    private static <T> Page<T> buildNextGenPage(JsonNode root, List<T> results) {
        JsonNode meta = root.get("meta");
        Builder<T> builder = new Builder<T>()
            .uri(URI.create(meta.get("url").asText()).getPath());

        JsonNode nextPageNode = meta.get("next_page_url");
        if (!nextPageNode.isNull()) {
            builder.nextPageUri(URI.create(nextPageNode.asText()).getPath());
        }

        JsonNode previousPageNode = meta.get("previous_page_url");
        if (!previousPageNode.isNull()) {
            builder.previousPageUri(URI.create(previousPageNode.asText()).getPath());
        }

        JsonNode firstPageNode = meta.get("first_page_url");
        if (!firstPageNode.isNull()) {
            builder.firstPageUri(URI.create(firstPageNode.asText()).getPath());
        }

        JsonNode pageSizeNode = meta.get("page_size");
        if (!pageSizeNode.isNull()) {
            builder.pageSize(pageSizeNode.asInt());
        } else {
            builder.pageSize(results.size());
        }

        return builder.records(results).build();
    }

    private static class Builder<T> {
        private List<T> records;
        private String firstPageUri;
        private String nextPageUri;
        private String previousPageUri;
        private String uri;
        private int pageSize;

        public Builder<T> records(List<T> records) {
            this.records = records;
            return this;
        }

        public Builder<T> firstPageUri(String firstPageUri) {
            this.firstPageUri = firstPageUri;
            return this;
        }

        public Builder<T> nextPageUri(String nextPageUri) {
            this.nextPageUri = nextPageUri;
            return this;
        }

        public Builder<T> previousPageUri(String previousPageUri) {
            this.previousPageUri = previousPageUri;
            return this;
        }

        public Builder<T> uri(String uri) {
            this.uri = uri;
            return this;
        }

        public Builder<T> pageSize(int pageSize) {
            this.pageSize = pageSize;
            return this;
        }

        public Page<T> build() {
            return new Page<>(this);
        }
    }
}
