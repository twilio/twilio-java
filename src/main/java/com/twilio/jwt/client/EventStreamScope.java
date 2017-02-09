package com.twilio.jwt.client;

import com.google.common.base.Joiner;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Scope token for Event Streams.
 */
public class EventStreamScope implements Scope {

    private static final String SCOPE = Joiner.on(':').join("scope", "stream", "subscribe");

    private final Map<String, String> filters;

    private EventStreamScope(Builder b) {
        this.filters = b.filters;
    }

    @Override
    public String getPayload() throws UnsupportedEncodingException {
        List<String> queryArgs = new ArrayList<>();
        queryArgs.add("path=/2010-04-01/Events");

        if (!this.filters.isEmpty()) {
            queryArgs.add(Joiner.on('=').join(
                URLEncoder.encode("appParams", "UTF-8"),
                URLEncoder.encode(this.getFilterParams(), "UTF-8")
            ));
        }

        String queryString = Joiner.on('&').join(queryArgs);
        return Joiner.on('?').join(SCOPE, queryString);
    }

    private String getFilterParams() throws UnsupportedEncodingException {
        List<String> queryParams = new ArrayList<>();
        for (Map.Entry<String, String> param : filters.entrySet()) {
            queryParams.add(Joiner.on('=').join(
                URLEncoder.encode(param.getKey(), "UTF-8"),
                URLEncoder.encode(param.getValue(), "UTF-8")
            ));
        }
        return Joiner.on('&').join(queryParams);
    }

    public static class Builder {
        private Map<String, String> filters = new HashMap<>();

        public Builder filters(Map<String, String> filters) {
            this.filters.putAll(filters);
            return this;
        }

        public EventStreamScope build() {
            return new EventStreamScope(this);
        }
    }
}
