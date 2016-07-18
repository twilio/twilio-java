package com.twilio.jwt.taskrouter;

import com.google.common.collect.Lists;
import com.twilio.jwt.Jwt;
import com.twilio.jwt.JwtEncodingException;
import io.jsonwebtoken.SignatureAlgorithm;

import java.io.IOException;
import java.util.ArrayList;
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
        payload.put("channel_id", this.channelId);

        if (channelId.startsWith("WK")) {
            payload.put("worker_sid", this.channelId);
        } else if (channelId.startsWith("WQ")) {
            payload.put("taskqueue_sid", this.channelId);
        }

        try {

            List<String> jsonPolicies = new ArrayList<>();
            for (Policy policy : policies) {
                jsonPolicies.add(policy.toJson());
            }
            for (Policy policy : PolicyUtils.defaultEventBridgePolicies(accountSid, channelId)) {
                jsonPolicies.add(policy.toJson());
            }

            payload.put("policies", jsonPolicies);

        } catch (IOException e) {
            throw new JwtEncodingException(e);
        }

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

    }

}
