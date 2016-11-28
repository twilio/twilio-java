package com.twilio.jwt.taskrouter;

import com.google.common.collect.Lists;
import com.twilio.jwt.Jwt;
import io.jsonwebtoken.SignatureAlgorithm;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * JWT builder for TaskRouter Capabilities.
 */
public class TaskRouterCapability extends Jwt {

    private final String accountSid;
    private final String workspaceSid;
    private final String friendlyName;
    private final String channelId;
    private final List<Policy> policies;

    private TaskRouterCapability(Builder b) {
        super(
            SignatureAlgorithm.HS256,
            b.authToken,
            b.accountSid,
            new Date(new Date().getTime() + b.ttl * 1000)
        );

        this.accountSid = b.accountSid;
        this.workspaceSid = b.workspaceSid;
        this.channelId = b.channelId;
        this.friendlyName = b.friendlyName;
        this.policies = b.policies;
    }

    @Override
    public Map<String, Object> getHeaders() {
        Map<String, Object> headers = new HashMap<>();
        headers.put("alg", "HS256");
        return headers;
    }

    @Override
    public Map<String, Object> getClaims() {
        Map<String, Object> payload = new HashMap<>();
        payload.put("version", "v1");
        payload.put("account_sid", this.accountSid);
        payload.put("friendly_name", this.friendlyName);
        payload.put("workspace_sid", this.workspaceSid);
        payload.put("channel", this.channelId);

        if (channelId.startsWith("WK")) {
            payload.put("worker_sid", this.channelId);
        } else if (channelId.startsWith("WQ")) {
            payload.put("taskqueue_sid", this.channelId);
        }

        List<Policy> payloadPolicies = Lists.newArrayList(this.policies);
        payloadPolicies.addAll(PolicyUtils.defaultEventBridgePolicies(accountSid, channelId));
        payload.put("policies", payloadPolicies);
        return payload;
    }

    public static class Builder {

        private String accountSid;
        private String authToken;
        private String workspaceSid;
        private String channelId;
        private String friendlyName;
        private int ttl = 3600;
        private List<Policy> policies = Lists.newArrayList();

        /**
         * Create a new builder for a TaskRouter Capability.
         * 
         * @param  accountSid account to use
         * @param  authToken auth token for account
         * @param  workspaceSid workspace sid to use
         * @param  channelId channel ID to use
         */
        public Builder(String accountSid, String authToken, String workspaceSid, String channelId) {
            this.accountSid = accountSid;
            this.authToken = authToken;
            this.workspaceSid = workspaceSid;
            this.channelId = channelId;
        }

        public Builder friendlyName(String friendlyName) {
            this.friendlyName = friendlyName;
            return this;
        }

        public Builder ttl(int ttl) {
            this.ttl = ttl;
            return this;
        }

        public Builder policies(List<Policy> policies) {
            this.policies = policies;
            return this;
        }

        public TaskRouterCapability build() {
            return new TaskRouterCapability(this);
        }
    }
}
