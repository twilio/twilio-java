package com.twilio.client;

import com.google.common.base.MoreObjects;

import java.util.Map;

public class Policy {

    private final String url;
    private final String method;
    private final Map<String, FilterRequirement> queryFilter;
    private final Map<String, FilterRequirement> postFilter;
    private final boolean allowed;

    private Policy(Builder b) {
        this.url = b.url;
        this.method = b.method;
        this.queryFilter = b.queryFilter;
        this.postFilter = b.postFilter;
        this.allowed = b.allowed;
    }

    public String getUrl() {
        return url;
    }

    public String getMethod() {
        return method;
    }

    public Map<String, FilterRequirement> getQueryFilter() {
        return queryFilter;
    }

    public Map<String, FilterRequirement> getPostFilter() {
        return postFilter;
    }

    public boolean isAllowed() {
        return allowed;
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
            .add("url", url)
            .add("method", method)
            .add("queryFilter", queryFilter)
            .add("postFilter", postFilter)
            .add("allowed", allowed)
            .toString();
    }

    public static class Builder {
        private String url;
        private String method;
        private Map<String, FilterRequirement> queryFilter;
        private Map<String, FilterRequirement> postFilter;
        private boolean allowed;

        public Builder url(String url) {
            this.url = url;
            return this;
        }

        public Builder method(String method) {
            this.method = method;
            return this;
        }

        public Builder queryFilter(Map<String, FilterRequirement> queryFilter) {
            this.queryFilter = queryFilter;
            return this;
        }

        public Builder postFilter(Map<String, FilterRequirement> postFilter) {
            this.postFilter = postFilter;
            return this;
        }

        public Builder allowed(boolean allowed) {
            this.allowed = allowed;
            return this;
        }

        public Policy build() {
            return new Policy(this);
        }
    }
}
