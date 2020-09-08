package com.twilio.jwt.taskrouter;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.twilio.http.HttpMethod;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Map;
import java.util.Objects;

import lombok.ToString;

@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
@ToString
public class Policy {

    @JsonProperty("url")
    private final String url;

    @JsonProperty("method")
    private final HttpMethod method;

    @JsonProperty("query_filter")
    private final Map<String, FilterRequirement> queryFilter;

    @JsonProperty("post_filter")
    private final Map<String, FilterRequirement> postFilter;

    @JsonProperty("allow")
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

    public HttpMethod getMethod() {
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

    /**
     * Converts a resource to JSON.
     *
     * @return JSON representation of the resource
     * @throws IOException if unable to transform to JSON
     */
    public String toJson() throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        ByteArrayOutputStream out = new ByteArrayOutputStream();

        mapper.writeValue(out, this);
        return out.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }

        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        Policy policy = (Policy) o;
        return allowed == policy.allowed &&
            method == policy.method &&
            Objects.equals(url, policy.url) &&
            Objects.equals(queryFilter, policy.queryFilter) &&
            Objects.equals(postFilter, policy.postFilter);
    }

    @Override
    public int hashCode() {
        return Objects.hash(url, method, queryFilter, postFilter, allowed);
    }

    public static class Builder {
        private String url;
        private HttpMethod method = HttpMethod.GET;
        private Map<String, FilterRequirement> queryFilter;
        private Map<String, FilterRequirement> postFilter;
        private boolean allowed = true;

        public Builder url(String url) {
            this.url = url;
            return this;
        }

        public Builder method(HttpMethod method) {
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
