package com.twilio.sdk;

import com.twilio.sdk.auth.AccessToken;
import com.twilio.sdk.auth.ConversationGrant;
import com.twilio.sdk.auth.IpMessagingGrant;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import org.junit.Test;

import java.util.List;
import java.util.Map;

import static org.junit.Assert.*;

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
		assertTrue(claims.getId().startsWith(claims.getIssuer() + "-"));
		assertNotNull(claims.get("grants"));
	}

	@Test
	public void testEmptyGrants() {
		AccessToken accessToken = new AccessToken(ACCOUNT_SID, SIGNING_KEY_SID, SECRET);
		Claims claims = Jwts.parser()
							.setSigningKey(SECRET.getBytes())
							.parseClaimsJws(accessToken.toJWT())
							.getBody();
		this.validateClaims(claims);
	}

	@Test
	public void testSingleGrant() {
		AccessToken accessToken = new AccessToken(ACCOUNT_SID, SIGNING_KEY_SID, SECRET);
		accessToken.setIdentity("ID@example.com");

		IpMessagingGrant grant = new IpMessagingGrant();
		grant.setCredentialSid("credentialSid")
				.setEndpointId("endpointId")
				.setRoleSid("roleSid")
				.setServiceSid("serviceSid");
		accessToken.addGrant(grant);

		Claims claims = Jwts.parser()
							.setSigningKey(SECRET.getBytes())
							.parseClaimsJws(accessToken.toJWT())
							.getBody();

		this.validateClaims(claims);

		Map<String, Object> decodedGrants = (Map<String, Object>) claims.get("grants");
		assertEquals(2, decodedGrants.size());

		Map<String, Object> payload = (Map<String, Object>) decodedGrants.get("ip_messaging");
		assertEquals("serviceSid", payload.get("instance_sid"));
		assertEquals("roleSid", payload.get("deployment_role_sid"));
		assertEquals("endpointId", payload.get("endpoint_id"));
		assertEquals("credentialSid", payload.get("push_credential_sid"));
	}

	@Test
	public void testConversationGrant() {
		AccessToken accessToken = new AccessToken(ACCOUNT_SID, SIGNING_KEY_SID, SECRET);
		accessToken.addGrant(new ConversationGrant());

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
	public void testIpMessagingGrant() {
		AccessToken accessToken = new AccessToken(ACCOUNT_SID, SIGNING_KEY_SID, SECRET);
		accessToken.addGrant(new IpMessagingGrant());

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
}
