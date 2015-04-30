package com.twilio.sdk;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * A scoped authentication token allow its issuer to restrict the resources and actions a user could perform.
 */
public class ScopedAuthenticationToken {

	private static final int DEFAULT_TTL = 3600;

	private String signingKeySid;
	private String accountSid;
	private String tokenId;
	private int ttl;
	private List<Grant> grants;

	/**
	 * Instantiate a scoped authentication token.
	 *
	 * @param signingKeySid
	 * @param accountSid
	 */
	public ScopedAuthenticationToken(final String signingKeySid, final String accountSid) {
		this(signingKeySid, accountSid, null);
	}

	/**
	 * Instantiate a scoped authentication token.
	 *
	 * @param signingKeySid
	 * @param accountSid
	 * @param tokenId
	 */
	public ScopedAuthenticationToken(final String signingKeySid, final String accountSid, final String tokenId) {
		this(signingKeySid, accountSid, tokenId, DEFAULT_TTL, new ArrayList<Grant>(0));
	}

	/**
	 * Instantiate a scoped authentication token.
	 *
	 * @param signingKeySid
	 * @param accountSid
	 * @param tokenId
	 * @param ttl
	 * @param grants
	 */
	public ScopedAuthenticationToken(final String signingKeySid, final String accountSid, final String tokenId,
	                                 final int ttl, final List<Grant> grants) {
		this.signingKeySid = signingKeySid;
		this.accountSid = accountSid;
		if (tokenId == null || tokenId.equals("")) {
			this.tokenId = signingKeySid + "-" + (new Date()).getTime();
		} else {
			this.tokenId = tokenId;
		}
		this.ttl = ttl;
		this.grants = grants;
	}

	/**
	 * Add a grant to this scoped authentication token
	 *
	 * @param grant grant to add
	 * @return list of grants
	 */
	public List<Grant> addGrant(final Grant grant) {
		grants.add(grant);
		return grants;
	}

	/**
	 * Generate a JWT with the provided information and sign it with the given secret.
	 *
	 * @param secret secret to sign the JWT with
	 * @return a JWT
	 */
	public String generateToken(final String secret) {
		if (secret == null) {
			throw new IllegalArgumentException("secret cannot be null");
		}

		Map<String, Object> headers = new HashMap<String, Object>(2);
		headers.put("typ", "JWT");
		headers.put("cty", "twilio-sat;v=1");

		Date notBefore = new Date();

		return Jwts.builder()
		           .signWith(SignatureAlgorithm.HS256, secret.getBytes(Charset.forName("UTF-8")))
		           .setHeaderParams(headers)
		           .setId(tokenId)
		           .setIssuer(signingKeySid)
		           .setSubject(accountSid)
		           .setNotBefore(notBefore)
		           .setExpiration(new Date(notBefore.getTime() + ttl * 1000))
		           .claim("grants", grants)
		           .compact();
	}

}
