package com.twilio.base;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.base.Joiner;
import com.google.common.base.Strings;
import com.twilio.exception.ApiConnectionException;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Page<T> {
    private final List<T> records;
    private final String firstPageUrl;
    private final String firstPageUri;
    private final String nextPageUrl;
    private final String nextPageUri;
    private final String previousPageUrl;
    private final String previousPageUri;
    private final String url;
    private final String uri;
    private final int pageSize;

    private Page(Builder<T> b) {
        this.records = b.records;
        this.firstPageUri = b.firstPageUri;
        this.firstPageUrl = b.firstPageUrl;
        this.nextPageUri = b.nextPageUri;
        this.nextPageUrl = b.nextPageUrl;
        this.previousPageUri = b.previousPageUri;
        this.previousPageUrl = b.previousPageUrl;
        this.uri = b.uri;
        this.url = b.url;
        this.pageSize = b.pageSize;
    }

    private String urlFromUri(String domain, String region, String uri) {
        return "https://" + Joiner.on(".").skipNulls().join(domain, region, "twilio", "com") + uri;
    }

    public List<T> getRecords() {
        return records;
    }

    /**
     * Generate first page url for a list result.
     *
     * @param domain domain to use
     * @param region region to use
     * @return the first page url
     */
    public String getFirstPageUrl(String domain, String region) {
        if (firstPageUrl != null) {
            return firstPageUrl;
        }

        return urlFromUri(domain, region, firstPageUri);
    }

    /**
     * Generate next page url for a list result.
     *
     * @param domain domain to use
     * @param region region to use
     * @return the next page url
     */
    public String getNextPageUrl(String domain, String region) {
        if (nextPageUrl != null) {
            return nextPageUrl;
        }

        return urlFromUri(domain, region, nextPageUri);
    }

    /**
     * Generate previous page url for a list result.
     *
     * @param domain domain to use
     * @param region region to use
     * @return the previous page url
     */
    public String getPreviousPageUrl(String domain, String region) {
        if (previousPageUrl != null) {
            return previousPageUrl;
        }

        return urlFromUri(domain, region, previousPageUri);
    }

    public int getPageSize() {
        return pageSize;
    }

    /**
     * Generate page url for a list result.
     *
     * @param domain domain to use
     * @param region region to use
     * @return the page url
     */
    public String getUrl(String domain, String region) {
        if (url != null) {
            return url;
        }

        return urlFromUri(domain, region, uri);
    }

    public boolean hasNextPage() {
        return !Strings.isNullOrEmpty(nextPageUri) || !Strings.isNullOrEmpty(nextPageUrl);
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
        Builder<T> builder = new Builder<T>().url(meta.get("url").asText());

        JsonNode nextPageNode = meta.get("next_page_url");
        if (!nextPageNode.isNull()) {
            builder.nextPageUrl(nextPageNode.asText());
        }

        JsonNode previousPageNode = meta.get("previous_page_url");
        if (!previousPageNode.isNull()) {
            builder.previousPageUrl(previousPageNode.asText());
        }

        JsonNode firstPageNode = meta.get("first_page_url");
        if (!firstPageNode.isNull()) {
            builder.firstPageUrl(firstPageNode.asText());
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
        private String firstPageUrl;
        private String firstPageUri;
        private String nextPageUrl;
        private String nextPageUri;
        private String previousPageUrl;
        private String previousPageUri;
        private String uri;
        private String url;
        private int pageSize;

        public Builder<T> records(List<T> records) {
            this.records = records;
            return this;
        }

        public Builder<T> firstPageUri(String firstPageUri) {
            this.firstPageUri = firstPageUri;
            return this;
        }

        public Builder<T> firstPageUrl(String firstPageUrl) {
            this.firstPageUrl = firstPageUrl;
            return this;
        }

        public Builder<T> nextPageUri(String nextPageUri) {
            this.nextPageUri = nextPageUri;
            return this;
        }

        public Builder<T> nextPageUrl(String nextPageUrl) {
            this.nextPageUrl = nextPageUrl;
            return this;
        }

        public Builder<T> previousPageUri(String previousPageUri) {
            this.previousPageUri = previousPageUri;
            return this;
        }

        public Builder<T> previousPageUrl(String previousPageUrl) {
            this.previousPageUrl = previousPageUrl;
            return this;
        }

        public Builder<T> uri(String uri) {
            this.uri = uri;
            return this;
        }

        public Builder<T> url(String url) {
            this.url = url;
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
