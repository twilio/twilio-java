package com.twilio.jwt.client;

import com.google.common.base.Joiner;
import com.google.common.collect.Lists;
import com.twilio.jwt.Jwt;
import com.twilio.jwt.JwtEncodingException;
import io.jsonwebtoken.SignatureAlgorithm;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * JWT builder for Client Capabilities.
 */
public class ClientCapability extends Jwt {

    private final List<Scope> scopes;

    private ClientCapability(Builder b) {
        super(
            SignatureAlgorithm.HS256,
            b.authToken,
            b.accountSid,
            new Date(new Date().getTime() + b.ttl * 1000)
        );

        this.scopes = b.scopes;
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

        try {
            List<String> scopes = new ArrayList<>();
            for (Scope scope : this.scopes) {
                scopes.add(scope.getPayload());
            }
            payload.put("scope", Joiner.on(' ').join(scopes));
        } catch (UnsupportedEncodingException e) {
            throw new JwtEncodingException(e);
        }

        return payload;
    }

    /** Builder used to construct a Client Capability. */
    public static class Builder {
        private String accountSid;
        private String authToken;
        private int ttl = 3600;
        private List<Scope> scopes = Lists.newArrayList();

        /**
         * Create a new builder for a Client Capability.
         *
         * @param accountSid account to use
         * @param authToken auth token
         */
        public Builder(String accountSid, String authToken) {
            this.accountSid = accountSid;
            this.authToken = authToken;
        }

        public Builder ttl(int ttl) {
            this.ttl = ttl;
            return this;
        }

        public Builder scopes(Collection<Scope> scopes) {
            this.scopes.addAll(scopes);
            return this;
        }

        public ClientCapability build() {
            return new ClientCapability(this);
        }
    }
}
