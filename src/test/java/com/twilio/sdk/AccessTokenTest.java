package com.twilio.sdk;

import com.twilio.sdk.auth.AccessToken;
import com.twilio.sdk.auth.EndpointGrant;
import com.twilio.sdk.auth.Grant;
import com.twilio.sdk.auth.RestGrant;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import org.junit.Test;

import java.util.List;
import java.util.Map;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

public class AccessTokenTest {

	public static final String SIGNING_KEY_SID = "SK123";
	public static final String ACCOUNT_SID = "AC123";
	public static final String SECRET = "secret";

	protected void validateClaims(Claims claims) {
		assertEquals(SIGNING_KEY_SID, claims.getIssuer());
		assertEquals(ACCOUNT_SID, claims.getSubject());
		assertNotNull(claims.getNotBefore());
		assertNotNull(claims.getExpiration());
		assertEquals(claims.getNotBefore().getTime() + (3600 * 1000), claims.getExpiration().getTime());
		assertNotNull(claims.getId());
		assertEquals(claims.getIssuer() + "-" + AccessToken.timestamp(claims.getNotBefore()), claims.getId());
		assertNotNull(claims.get("grants"));
	}

	@Test
	public void testEmptyGrants() {
		AccessToken accessToken = new AccessToken(SIGNING_KEY_SID, ACCOUNT_SID, SECRET);
		Claims claims = Jwts.parser()
							.setSigningKey(SECRET.getBytes())
							.parseClaimsJws(accessToken.toJWT())
							.getBody();
		this.validateClaims(claims);
	}

	@Test
	public void testSingleGrant() {
		AccessToken accessToken = new AccessToken(SIGNING_KEY_SID, ACCOUNT_SID, SECRET);
		accessToken.addGrant(new Grant("https://api.twilio.com/**"));

		Claims claims = Jwts.parser()
							.setSigningKey(SECRET.getBytes())
							.parseClaimsJws(accessToken.toJWT())
							.getBody();

		this.validateClaims(claims);

		List<Map<String, Object>> decodedGrants = (List<Map<String, Object>>) claims.get("grants");
		assertEquals(1, decodedGrants.size());
		assertEquals("https://api.twilio.com/**", decodedGrants.get(0).get("res"));
		assertEquals("*", ((List<String>) decodedGrants.get(0).get("act")).get(0));
	}

	@Test
	public void testEndpointGrant() {
		AccessToken accessToken = new AccessToken(SIGNING_KEY_SID, ACCOUNT_SID, SECRET);
		accessToken.addGrant(new EndpointGrant("bob"));

		Claims claims = Jwts.parser()
				.setSigningKey(SECRET.getBytes())
				.parseClaimsJws(accessToken.toJWT())
				.getBody();

		this.validateClaims(claims);

		List<Map<String, Object>> decodedGrants = (List<Map<String, Object>>) claims.get("grants");
		assertEquals(1, decodedGrants.size());
		assertEquals("sip:bob@AC123.endpoint.twilio.com", decodedGrants.get(0).get("res"));
		List<String> actions = (List<String>) decodedGrants.get(0).get("act");
		assertTrue(actions.contains("listen"));
		assertTrue(actions.contains("invite"));
	}

	@Test
	public void testRestGrant() {
		AccessToken accessToken = new AccessToken(SIGNING_KEY_SID, ACCOUNT_SID, SECRET);
		accessToken.addGrant(new RestGrant("/Apps"));

		Claims claims = Jwts.parser()
				.setSigningKey(SECRET.getBytes())
				.parseClaimsJws(accessToken.toJWT())
				.getBody();

		this.validateClaims(claims);

		List<Map<String, Object>> decodedGrants = (List<Map<String, Object>>) claims.get("grants");
		assertEquals(1, decodedGrants.size());
		assertEquals("https://api.twilio.com/2010-04-01/Accounts/AC123/Apps", decodedGrants.get(0).get("res"));
		assertEquals("*", ((List<String>) decodedGrants.get(0).get("act")).get(0));
	}

	@Test
	public void testEnableNTS() {
		AccessToken accessToken = new AccessToken(SIGNING_KEY_SID, ACCOUNT_SID, SECRET);
		accessToken.enableNTS();

		Claims claims = Jwts.parser()
				.setSigningKey(SECRET.getBytes())
				.parseClaimsJws(accessToken.toJWT())
				.getBody();

		this.validateClaims(claims);

		List<Map<String, Object>> decodedGrants = (List<Map<String, Object>>) claims.get("grants");
		assertEquals(1, decodedGrants.size());
		assertEquals("https://api.twilio.com/2010-04-01/Accounts/AC123/Tokens.json", decodedGrants.get(0).get("res"));
		assertEquals("POST", ((List<String>) decodedGrants.get(0).get("act")).get(0));
	}
}
