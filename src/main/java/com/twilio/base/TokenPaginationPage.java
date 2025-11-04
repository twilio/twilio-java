package com.twilio.base;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.twilio.exception.ApiConnectionException;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class TokenPaginationPage<T> {
    private final List<T> records;
    private final String key;
    private final String nextToken;
    private final int pageSize;
    private final String previousToken;


    private TokenPaginationPage(Builder<T> b) {
        this.records = b.records;
        this.key = b.key;
        this.nextToken = b.nextToken;
        this.pageSize = b.pageSize;
        this.previousToken = b.previousToken;
    }

    public List<T> getRecords() {
        return records;
    }

    public int getPageSize() {
        return pageSize;
    }

    public boolean hasNextPage() {
        return (nextToken != null && !nextToken.isEmpty());
    }


    /**
     * Create a new page of data from a json blob.
     *
     * @param recordKey  key which holds the records
     * @param json       json blob
     * @param recordType resource type
     * @param mapper     json parser
     * @param <T>        record class type
     * @return a page of records of type T
     */
    public static <T> TokenPaginationPage<T> fromJson(String recordKey, String json, Class<T> recordType, ObjectMapper mapper) {
        try {
            List<T> results = new ArrayList<>();
            JsonNode root = mapper.readTree(json);
            JsonNode records = root.get(recordKey);
            for (final JsonNode record : records) {
                results.add(mapper.readValue(record.toString(), recordType));
            }

            return buildPage(root, results);

        } catch (final IOException e) {
            throw new ApiConnectionException(
                "Unable to deserialize response: " + e.getMessage() + "\nJSON: " + json, e
            );
        }
    }

    private static <T> TokenPaginationPage<T> buildPage(JsonNode root, List<T> results) {
        Builder<T> builder = new Builder<T>()
            .key(root.get("key").asText());

        builder.nextToken(root.get("nextToken").asText());
        builder.previousToken(root.get("previousToken").asText());

        JsonNode pageSizeNode = root.get("pageSize");
        builder.pageSize(pageSizeNode.asInt()); // pageSize is mandatory

        return builder.records(results).build();
    }

    private static class Builder<T> {
        private List<T> records;
        private String key;
        private String nextToken;
        private int pageSize;
        private String previousToken;

        public Builder<T> records(List<T> records) {
            this.records = records;
            return this;
        }

        public Builder<T> key(String key) {
            this.key = key;
            return this;
        }

        public Builder<T> nextToken(String nextToken) {
            this.nextToken = nextToken;
            return this;
        }

        public Builder<T> pageSize(int pageSize) {
            this.pageSize = pageSize;
            return this;
        }

        public Builder<T> previousToken(String previousToken) {
            this.previousToken = previousToken;
            return this;
        }

        public TokenPaginationPage<T> build() {
            return new TokenPaginationPage<>(this);
        }
    }
}
