package com.twilio.sdk;

import com.twilio.sdk.auth.AccessToken;
import com.twilio.sdk.auth.ConversationGrant;
import com.twilio.sdk.auth.IpMessagingGrant;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import org.junit.Test;

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
		assertTrue(claims.getId().startsWith(claims.getIssuer() + "-"));
		assertNotNull(claims.get("grants"));
	}

	@Test
	public void testEmptyGrants() {
		AccessToken accessToken =
			new AccessToken.Builder(ACCOUNT_SID, SIGNING_KEY_SID, SECRET)
				.build();

		Claims claims =
			Jwts.parser()
				.setSigningKey(SECRET.getBytes())
				.parseClaimsJws(accessToken.toJWT())
				.getBody();

		this.validateClaims(claims);
	}

	@Test
	public void testSingleGrant() {
		IpMessagingGrant grant = new IpMessagingGrant();
		grant.setCredentialSid("credentialSid")
				.setEndpointId("endpointId")
				.setRoleSid("roleSid")
				.setServiceSid("serviceSid");

		AccessToken accessToken =
			new AccessToken.Builder(ACCOUNT_SID, SIGNING_KEY_SID, SECRET)
				.identity("ID@example.com")
				.withGrant(grant)
				.build();

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
		AccessToken accessToken =
			new AccessToken.Builder(ACCOUNT_SID, SIGNING_KEY_SID, SECRET)
				.withGrant(new ConversationGrant())
				.build();

		Claims claims = Jwts.parser()
				.setSigningKey(SECRET.getBytes())
				.parseClaimsJws(accessToken.toJWT())
				.getBody();

		this.validateClaims(claims);
		Map<String, Object> decodedGrants = (Map<String, Object>) claims.get("grants");
		assertEquals(1, decodedGrants.size());

		assertTrue("should be a rtc grant present", decodedGrants.containsKey("rtc"));
	}

	@Test
	public void testIpMessagingGrant() {
		AccessToken accessToken =
			new AccessToken.Builder(ACCOUNT_SID, SIGNING_KEY_SID, SECRET)
				.withGrant(new IpMessagingGrant())
				.build();

		Claims claims = Jwts.parser()
				.setSigningKey(SECRET.getBytes())
				.parseClaimsJws(accessToken.toJWT())
				.getBody();

		this.validateClaims(claims);
		Map<String, Object> decodedGrants = (Map<String, Object>) claims.get("grants");
		assertEquals(1, decodedGrants.size());

		assertTrue("should be a ip_messaging grant present", decodedGrants.containsKey("ip_messaging"));
	}
}
