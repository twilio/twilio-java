package com.twilio.jwt;

import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import javax.crypto.spec.SecretKeySpec;
import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * JWT builder for Twilio auth tokens.
 */
public abstract class Jwt {

    private final SignatureAlgorithm algorithm;
    private final Key secretKey;
    private final String issuer;
    private final Date expiration;

    /**
     * Create a new JWT.
     *
     * @param algorithm algorithm to use
     * @param secret secret key
     * @param issuer JWT issuer
     * @param expiration expiration Date
     */
    public Jwt(
        SignatureAlgorithm algorithm,
        String secret,
        String issuer,
        Date expiration
    ) {
        this(
            algorithm,
            new SecretKeySpec(secret.getBytes(), algorithm.getJcaName()),
            issuer,
            expiration
        );
    }

    /**
     * Create a new JWT.
     *
     * @param algorithm algorithm to use
     * @param secretKey secret key
     * @param issuer JWT issuer
     * @param expiration expiration Date
     */
    public Jwt(
        SignatureAlgorithm algorithm,
        Key secretKey,
        String issuer,
        Date expiration
    ) {
        this.algorithm = algorithm;
        this.secretKey = secretKey;
        this.issuer = issuer;
        this.expiration = expiration;
    }

    /**
     * Encode a JWT.
     *
     * @return encoded JWT
     */
    public String toJwt() {
        Map<String, Object> headers = new HashMap<>();
        headers.put("typ", "JWT");
        headers.putAll(this.getHeaders());

        JwtBuilder builder =
            Jwts.builder()
                .signWith(this.algorithm, this.secretKey)
                .setHeaderParams(headers)
                .setIssuer(this.issuer)
                .setExpiration(expiration);

        if (this.getClaims() != null) {
            for (Map.Entry<String, Object> entry : this.getClaims().entrySet()) {
                builder.claim(entry.getKey(), entry.getValue());
            }
        }

        if (this.getId() != null) {
            builder.setId(this.getId());
        }

        if (this.getSubject() != null) {
            builder.setSubject(this.getSubject());
        }

        if (this.getNbf() != null) {
            builder.setNotBefore(this.getNbf());
        }

        return builder.compact();
    }

    public String getId() {
        return null;
    }

    public String getSubject() {
        return null;
    }

    public Date getNbf() {
        return null;
    }

    public abstract Map<String, Object> getHeaders();

    public abstract Map<String, Object> getClaims();
}
