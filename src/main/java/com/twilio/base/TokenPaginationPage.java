package com.twilio.base;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.twilio.exception.ApiConnectionException;
import com.twilio.exception.ApiException;
import lombok.Getter;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class TokenPaginationPage<T> extends Page<T> {
    @Getter
    private final String key;
    private final String nextToken;
    private final String previousToken;

    private TokenPaginationPage(Builder<T> b) {
        super(b);
        this.key = b.key;
        this.nextToken = b.nextToken;
        this.previousToken = b.previousToken;
    }

    // adding custom getter and not using lombok to handle null token
    // when token is null, lombok getter returns "null" not a null object
    public String getNextToken() {
        return nextToken;
    }

    public String getPreviousToken() {
        return previousToken;
    }

    @Override
    public String previousQueryString() {
        return getQueryString(previousToken);
    }

    @Override
    public String nextQueryString() {
        return getQueryString(nextToken);
    }

    private void addQueryOperators(StringBuilder query) {
        if(query.length() == 0) {
            query.append("?");
        } else {
            query.append("&");
        }
    }

    private String getQueryString(String pageToken) {
        StringBuilder query = new StringBuilder();
        if (pageSize > 0) {
            addQueryOperators(query);
            query.append("pageSize=").append(pageSize);
        }
        if(pageToken != null && !pageToken.isEmpty()) {
            addQueryOperators(query);
            query.append("pageToken=").append(pageToken);
        }
        return query.toString();
    }

    /**
     * Checks if there is a next page of records available.
     *
     * @return true if a next page is available, false otherwise
     */
    @Override
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
            try {
                JsonNode meta = root.get("meta");
                String key = meta.get("key").asText();
                JsonNode records = root.get(key);
                for (final JsonNode record : records) {
                    results.add(mapper.readValue(record.toString(), recordType));
                }

                return buildPage(meta, results);
            } catch (NullPointerException e) {
                throw new ApiException("Key not found", e);
            }

        } catch (final IOException e) {
            throw new ApiConnectionException(
                "Unable to deserialize response: " + e.getMessage() + "\nJSON: " + json, e
            );
        }
    }

    private static <T> TokenPaginationPage<T> buildPage(JsonNode meta, List<T> results) {
        try {
            Builder<T> builder = new Builder<T>()
                    .key(meta.get("key").asText());

            JsonNode nextTokenNode = meta.get("nextToken");
            if (nextTokenNode != null && !nextTokenNode.isNull()) {
                builder.nextToken(nextTokenNode.asText());
            }

            JsonNode previousTokenNode = meta.get("previousToken");
            if (previousTokenNode != null && !previousTokenNode.isNull()) {
                builder.previousToken(previousTokenNode.asText());
            }

            JsonNode pageSizeNode = meta.get("pageSize");
            builder.pageSize(pageSizeNode.asInt());

            return builder.records(results).build();
        } catch (NullPointerException e) {
            throw new ApiException("Key not found", e);
        }
    }

    protected static class Builder<T> extends Page.Builder<T> {
        private String key;
        private String nextToken;
        private String previousToken;

        @Override
        public Builder<T> records(List<T> records) {
            super.records(records);
            return this;
        }

        @Override
        public Builder<T> pageSize(int pageSize) {
            super.pageSize(pageSize);
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

        public Builder<T> previousToken(String previousToken) {
            this.previousToken = previousToken;
            return this;
        }

        public TokenPaginationPage<T> build() {
            return new TokenPaginationPage<>(this);
        }
    }
}
