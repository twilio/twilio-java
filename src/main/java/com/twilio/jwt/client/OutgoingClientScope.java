package com.twilio.jwt.client;

import com.google.common.base.Joiner;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Scope token for Outgoing Clients.
 */
public class OutgoingClientScope implements Scope {

    private static final String SCOPE = Joiner.on(':').join("scope", "client", "outgoing");

    private final String applicationSid;
    private final String clientName;
    private final Map<String, String> params;

    private OutgoingClientScope(Builder b) {
        this.applicationSid = b.applicationSid;
        this.clientName = b.clientName;
        this.params = b.params;
    }

    @Override
    public String getPayload() throws UnsupportedEncodingException {
        List<String> queryArgs = new ArrayList<>();
        queryArgs.add(Joiner.on('=').join("appSid", this.applicationSid));

        if (this.clientName != null) {
            queryArgs.add(Joiner.on('=').join("clientName", this.clientName));
        }

        if (!this.params.isEmpty()) {
            queryArgs.add(Joiner.on('=').join("appParams", this.getAppParams()));
        }

        String queryString = Joiner.on('&').join(queryArgs);
        return Joiner.on('?').join(SCOPE, queryString);
    }

    private String getAppParams() throws UnsupportedEncodingException {
        List<String> queryParams = new ArrayList<>();
        for (Map.Entry<String, String> param : params.entrySet()) {
            queryParams.add(Joiner.on('=').join(
                URLEncoder.encode(param.getKey(), "UTF-8"),
                URLEncoder.encode(param.getValue(), "UTF-8")
            ));
        }
        return Joiner.on('&').join(queryParams);
    }

    public static class Builder {
        private String applicationSid;
        private String clientName;
        private Map<String, String> params = new HashMap<>();

        public Builder(String applicationSid) {
            this.applicationSid = applicationSid;
        }

        public Builder clientName(String clientName) {
            this.clientName = clientName;
            return this;
        }

        public Builder params(Map<String, String> params) {
            this.params.putAll(params);
            return this;
        }

        public OutgoingClientScope build() {
            return new OutgoingClientScope(this);
        }
    }
}
