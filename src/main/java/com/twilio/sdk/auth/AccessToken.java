package com.twilio.sdk.auth;

import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import java.nio.charset.Charset;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * Access Token use to grant privileges to Twilio resources
 *
 * For more information see:
 * <a href="https://www.twilio.com/docs/api/rest/access-tokens">
 *     https://www.twilio.com/docs/api/rest/access-tokens
 * </a>
 */
public class AccessToken {

	private final String accountSid;
	private final String keySid;
	private final String secret;
	private final int ttl;

	private final Integer nbf;
	private final String identity;
	private final Set<Grant> grants;

	private AccessToken(Builder b) {
		this.accountSid = b.accountSid;
		this.keySid = b.keySid;
		this.secret = b.secret;
		this.ttl = b.ttl;
		this.nbf = b.nbf;
		this.identity = b.identity;
		this.grants = Collections.unmodifiableSet(b.grants);
	}

	public String getIdentity() {
		return this.identity;
	}

	public String toJWT() {
		Map<String, Object> headers = new HashMap<String, Object>();
		headers.put("typ", "JWT");
		headers.put("cty", "twilio-fpa;v=1");

		Date now = new Date();
		int timestamp = (int)(Math.floor(now.getTime() / 1000.0f));
		Map<String, Object> grantPayload = new HashMap<String, Object>();

		if (this.identity != null) {
			grantPayload.put("identity", this.identity);
		}

		for (Grant grant : this.grants) {
			grantPayload.put(grant.getGrantKey(), grant.getPayload());
		}

		JwtBuilder builder =
			Jwts.builder()
				.signWith(SignatureAlgorithm.HS256, secret.getBytes(Charset.forName("UTF-8")))
				.setHeaderParams(headers)
				.setId(this.keySid + "-" + timestamp)
				.setIssuer(this.keySid)
				.setSubject(this.accountSid)
				.setExpiration(new Date(now.getTime() + ttl * 1000))
				.claim("grants", grantPayload);

		if (this.nbf != null) {
			builder.setNotBefore(new Date((long)this.nbf * 1000));
		}

		return builder.compact();
	}

	/** Builder used to construct a Access Token */
	public static class Builder {
		private String accountSid;
		private String keySid;
		private String secret;
		private String identity;
		private Integer nbf = null;
		private int ttl = 3600;
		private Set<Grant> grants = new HashSet<Grant>();

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

		public Builder nbf(int nbf) {
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
