package com.twilio.jwt.accesstoken;

import com.twilio.jwt.Jwt;
import io.jsonwebtoken.SignatureAlgorithm;

import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * Access Token use to grant privileges to Twilio resources.
 *
 * <p>For more information see:
 *     <a href="https://www.twilio.com/docs/api/rest/access-tokens">
 *         https://www.twilio.com/docs/api/rest/access-tokens
 *     </a>
 * </p>
 */
public class AccessToken extends Jwt {

    private static final String CTY = "twilio-fpa;v=1";

    private final String id;
    private final String accountSid;
    private final String identity;
    private final Date nbf;
    private final Set<Grant> grants;

    private AccessToken(Builder b) {
        super(
            SignatureAlgorithm.HS256,
            b.secret,
            b.keySid,
            new Date(new Date().getTime() + b.ttl * 1000)
        );

        Date now = new Date();
        this.id = b.keySid + "-" + (int)(Math.floor(now.getTime() / 1000.0f));
        this.accountSid = b.accountSid;
        this.identity = b.identity;
        this.nbf = b.nbf;
        this.grants = Collections.unmodifiableSet(b.grants);
    }

    @Override
    public Date getNbf() {
        return this.nbf;
    }

    @Override
    public String getId() {
        return this.id;
    }

    @Override
    public String getSubject() {
        return this.accountSid;
    }

    @Override
    public Map<String, Object> getHeaders() {
        Map<String, Object> headers = new HashMap<>();
        headers.put("cty", CTY);
        return headers;
    }

    @Override
    public Map<String, Object> getClaims() {
        Map<String, Object> grants = new HashMap<>();
        if (this.identity != null) {
            grants.put("identity", this.identity);
        }

        for (Grant grant : this.grants) {
            grants.put(grant.getGrantKey(), grant.getPayload());
        }


        Map<String, Object> payload = new HashMap<>();
        payload.put("grants", grants);
        return payload;
    }

    /** Builder used to construct a Access Token. */
    public static class Builder {
        private String accountSid;
        private String keySid;
        private String secret;
        private String identity;
        private Date nbf = null;
        private int ttl = 3600;
        private Set<Grant> grants = new HashSet<Grant>();

        /**
         * Create a new builder for a Access Token.
         *
         * @param accountSid account to use
         * @param keySid key to use
         * @param secret secret key
         */
        public Builder(String accountSid, String keySid, String secret) {
            this.accountSid = accountSid;
            this.keySid = keySid;
            this.secret = secret;
        }

        public Builder identity(String identity) {
            this.identity = identity;
            return this;
        }

        public Builder ttl(int ttl) {
            this.ttl = ttl;
            return this;
        }

        public Builder nbf(Date nbf) {
            this.nbf = nbf;
            return this;
        }

        public Builder grant(Grant grant) {
            this.grants.add(grant);
            return this;
        }

        public Builder grants(Collection<Grant> grants) {
            this.grants.addAll(grants);
            return this;
        }

        public AccessToken build() {
            return new AccessToken(this);
        }
    }
}
