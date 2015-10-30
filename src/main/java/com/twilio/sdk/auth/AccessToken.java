package com.twilio.sdk.auth;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AccessToken {

	private final String accountSid;
	private final String keySid;
	private final String secret;
	private final int ttl;
	private String identity;
	private final List<Grant> grants = new ArrayList<Grant>();

	public AccessToken(String accountSid, String keySid, String secret) {
		this(accountSid, keySid, secret, 3600);
	}

	public AccessToken(String accountSid, String keySid, String secret, int ttl) {
		this.accountSid = accountSid;
		this.keySid = keySid;
		this.secret = secret;
		this.ttl = ttl;
	}

	public void setIdentity(String identity) {
		this.identity = identity;
	}
	public String getIdentity() {
		return this.identity;
	}

	public void addGrant(Grant grant) {
		this.grants.add(grant);
	}

	public String toJWT() {
		Map<String, Object> headers = new HashMap<String, Object>();
		headers.put("typ", "JWT");
		headers.put("cty", "twilio-sat;v=2");

		Date now = new Date();
		int timestamp = (int)(Math.floor(now.getTime() / 1000.0f));

		Map<String, Object> grantPayload = new HashMap<String, Object>();

		if (this.identity != null) {
			grantPayload.put("identity", this.identity);
		}

		for (Grant grant : this.grants) {
			grantPayload.put(grant.getGrantKey(), grant.getPayload());
		}

		return Jwts.builder()
				.signWith(SignatureAlgorithm.HS256, secret.getBytes(Charset.forName("UTF-8")))
				.setHeaderParams(headers)
				.setId(this.keySid + "-" + timestamp)
				.setIssuer(this.keySid)
				.setSubject(this.accountSid)
				.setNotBefore(now)
				.setExpiration(new Date(now.getTime() + ttl * 1000))
				.claim("grants", grantPayload)
				.compact();
	}


}
