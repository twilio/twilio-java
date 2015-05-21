package com.twilio.sdk.auth;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.apache.commons.lang3.StringUtils;

import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * An access token allow its issuer to restrict the resources and actions a user could perform.
 */
public class AccessToken {

	private static final int DEFAULT_TTL = 3600;

	private final String signingKeySid;
	private final String accountSid;
	private final String secret;
	private final int ttl;
	private List<Grant> grants;

	public static int timestamp(Date date) {
		return (int)(Math.floor(date.getTime() / 1000.0f));
	}

	/**
	 * Instantiate an access token.
	 *
	 * @param signingKeySid the signing key's unique ID
	 * @param accountSid the account's unique ID to which access is scoped
	 * @param secret the secret to use to sign the Access Token
	 */
	public AccessToken(final String signingKeySid, final String accountSid, final String secret) {
		this(signingKeySid, accountSid, secret, DEFAULT_TTL);
	}

	/**
	 * Instantiate an access token.
	 *
	 * @param signingKeySid the signing key's unique ID
	 * @param accountSid the account's unique ID to which access is scoped
	 * @param secret the secret to use to sign the Access Token
	 * @param ttl time to live in seconds
	 */
	public AccessToken(final String signingKeySid, final String accountSid, final String secret, final int ttl) {
		this.signingKeySid = signingKeySid;
		this.accountSid = accountSid;
		this.secret = secret;
		this.ttl = ttl;
		this.grants = new ArrayList<Grant>();
	}

	/**
	 * Add a grant to the AccessToken
	 *
	 * @param grant grant to add
	 * @return this
	 */
	public AccessToken addGrant(final Grant grant) {
		grants.add(grant);
		return this;
	}

	/**
	 * Add an EndpointGrant to the AccessToken
	 *
	 * @param endpointGrant grant to add
	 * @return this
	 */
	public AccessToken addGrant(final EndpointGrant endpointGrant) {
		Grant grant = new Grant("sip:" + endpointGrant.getResource() + "@" + this.accountSid + ".endpoint.twilio.com",
								endpointGrant.getActions());
		return this.addGrant(grant);
	}

	/**
	 * Add a RestGrant to the AccessToken
	 *
	 * @param restGrant grant to add
	 * @return this
	 */
	public AccessToken addGrant(final RestGrant restGrant) {
		Grant grant = new Grant("https://api.twilio.com/2010-04-01/Accounts/" + this.accountSid + "/" + StringUtils.removeStart(restGrant.getResource(), "/"),
								restGrant.getActions());
		return this.addGrant(grant);
	}

	public AccessToken enableNTS() {
		RestGrant grant = new RestGrant("/Tokens.json", Action.POST);
		return this.addGrant(grant);
	}

	/**
	 * Generate a JWT with the provided information and sign it with the given secret.
	 *
	 * @return a JWT
	 */
	public String toJWT() {
		Map<String, Object> headers = new HashMap<String, Object>(2);
		headers.put("typ", "JWT");
		headers.put("cty", "twilio-sat;v=1");

		Date now = new Date();

		return Jwts.builder()
		           .signWith(SignatureAlgorithm.HS256, secret.getBytes(Charset.forName("UTF-8")))
		           .setHeaderParams(headers)
		           .setId(signingKeySid + "-" + timestamp(now))
		           .setIssuer(signingKeySid)
		           .setSubject(accountSid)
		           .setNotBefore(now)
		           .setExpiration(new Date(now.getTime() + ttl * 1000))
		           .claim("grants", grants)
		           .compact();
	}

	@Override
	public String toString() {
		return this.toJWT();
	}
}
