package com.twilio.sdk.taskrouter;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.JsonNodeFactory;

import java.util.HashMap;
import java.util.Map;
import java.util.logging.Filter;

public class Policy {

    protected final String url;
    protected final String method;
    protected Map<String, FilterRequirement> queryFilter;
    protected Map<String, FilterRequirement> postFilter;
    protected final boolean allowed;

    /**
     * Represents permissions for a specific operation against a TaskRouter
     * resource.
     *
     * @param url
     *            The URL of the resource to grant or deny permissions to
     * @param method
     *            The HTTP method
     * @param queryFilter
     *            Allowed or required parameters for GET requests
     * @param postFilter
     *            Allowed or required parameters for POST requests
     * @param allowed
     *            Whether this action is allowed or not
     */
    public Policy(final String url, final String method, final Map<String, FilterRequirement> queryFilter, final Map<String, FilterRequirement> postFilter, final boolean allowed) {
        this.url = url;
        this.method = method;
        this.queryFilter = queryFilter;
        this.postFilter = postFilter;
        this.allowed = allowed;
    }

    public Policy(final String url, final String method, final boolean allowed) {
        this(url, method, new HashMap<String, FilterRequirement>(), new HashMap<String, FilterRequirement>(), allowed);
    }

    public Policy addQueryFilterParam(final String name, final FilterRequirement required) {
        queryFilter.put(name, required);
        return this;
    }

    public Policy addPostFilterParam(final String name, final FilterRequirement required) {
        postFilter.put(name, required);
        return this;
    }

    public Policy setQueryFilter(final Map<String, FilterRequirement> queryFilter) {
        this.queryFilter = queryFilter;
        return this;
    }

    public Policy setPostFilter(final Map<String, FilterRequirement> postFilter) {
        this.postFilter = postFilter;
        return this;
    }

    public String toJSONString() throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        HashMap<String, Object> obj = new HashMap<String, Object>();
        obj.put("url", url);
        obj.put("method", method);
        obj.put("allow", allowed);
        final HashMap<String, FilterRequirement> query = new HashMap<String, FilterRequirement>();
        final HashMap<String, FilterRequirement> post = new HashMap<String, FilterRequirement>();

        if (queryFilter != null) {
            for (final Map.Entry<String, FilterRequirement> e : queryFilter.entrySet()) {
                query.put(e.getKey(), e.getValue());
            }
        }

        if (postFilter != null) {
            for (final Map.Entry<String, FilterRequirement> e : postFilter.entrySet()) {
                post.put(e.getKey(), e.getValue());
            }
        }

        obj.put("query_filter", query);
        obj.put("post_filter", post);
        return mapper.writeValueAsString(obj);
    }

    @Override
    public String toString() {
        return "Policy [url=" + url + ", method=" + method + ", queryFilter=" + queryFilter + ", postFilter=" + postFilter + ", allowed=" + allowed + "]";
    }

}
