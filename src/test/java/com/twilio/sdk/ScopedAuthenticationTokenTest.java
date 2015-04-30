package com.twilio.sdk;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import org.junit.Test;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class ScopedAuthenticationTokenTest {

	@Test
	public void testAddGrant() {
		Set<Action> actions = new HashSet<Action>(1);
		actions.add(Action.ALL);
		List<Grant> grants = new ArrayList<Grant>();
		grants.add(new Grant("https://api.twilio.com/**", actions));
		ScopedAuthenticationToken scopedAuthenticationToken = new ScopedAuthenticationToken("SK123", "AC123", "Token1",
		                                                                                    3600, grants);
		Grant newGrant = new Grant("https://taskrouter.twilio.com/**", actions);
		grants = scopedAuthenticationToken.addGrant(newGrant);
		assertNotNull(grants);
	}

	@Test
	public void testGenerateToken() {
		Set<Action> actions = new HashSet<Action>(1);
		actions.add(Action.ALL);
		List<Grant> grants = new ArrayList<Grant>();
		grants.add(new Grant("https://api.twilio.com/**", actions));

		ScopedAuthenticationToken scopedAuthenticationToken = new ScopedAuthenticationToken("SK123", "AC123", "Token1",
		                                                                                    3600, grants);
		String token = scopedAuthenticationToken.generateToken("secret");
		assertNotNull(token);

		Claims decodedToken = Jwts.parser()
		                          .setSigningKey("secret".getBytes())
		                          .parseClaimsJws(token)
		                          .getBody();
		assertNotNull(decodedToken);
		assertEquals("Token1", decodedToken.getId());
		assertEquals("SK123", decodedToken.getIssuer());
		assertEquals("AC123", decodedToken.getSubject());
		assertNotNull(decodedToken.getNotBefore());
		assertNotNull(decodedToken.getExpiration());
		assertEquals(decodedToken.getExpiration()
		                         .getTime(), decodedToken.getNotBefore()
		                                                 .getTime() + 3600 * 1000);
		assertNotNull(decodedToken.get("grants"));
		List<Map<String, Object>> decodedGrants = (List<Map<String, Object>>) decodedToken.get("grants");
		assertEquals(1, decodedGrants.size());
		assertEquals("https://api.twilio.com/**", decodedGrants.get(0).get("res"));
		assertEquals("*", ((List<Action>) decodedGrants.get(0).get("act")).get(0));
	}

	@Test
	public void testGenerateTokenWithoutGrants() {
		ScopedAuthenticationToken scopedAuthenticationToken = new ScopedAuthenticationToken("SK123", "AC123", "Token1");
		String token = scopedAuthenticationToken.generateToken("secret");
		assertNotNull(token);

		Claims decodedToken = Jwts.parser()
		                          .setSigningKey("secret".getBytes())
		                          .parseClaimsJws(token)
		                          .getBody();
		assertNotNull(decodedToken);
		assertEquals("Token1", decodedToken.getId());
		assertEquals("SK123", decodedToken.getIssuer());
		assertEquals("AC123", decodedToken.getSubject());
		assertNotNull(decodedToken.getNotBefore());
		assertNotNull(decodedToken.getExpiration());
		assertEquals(decodedToken.getExpiration()
		                         .getTime(), decodedToken.getNotBefore()
		                                                 .getTime() + 3600 * 1000);
		assertNotNull(decodedToken.get("grants"));
		List<Map<String, Object>> decodedGrants = (List<Map<String, Object>>) decodedToken.get("grants");
		assertEquals(0, decodedGrants.size());
	}

	@Test
	public void testGenerateTokenWithoutTokeId() {
		ScopedAuthenticationToken scopedAuthenticationToken = new ScopedAuthenticationToken("SK123", "AC123");
		String token = scopedAuthenticationToken.generateToken("secret");
		assertNotNull(token);

		Claims decodedToken = Jwts.parser()
		                          .setSigningKey("secret".getBytes())
		                          .parseClaimsJws(token)
		                          .getBody();
		assertNotNull(decodedToken);
		assertNotNull(decodedToken.getId());
		assertEquals("SK123", decodedToken.getIssuer());
		assertEquals("AC123", decodedToken.getSubject());
		assertNotNull(decodedToken.getNotBefore());
		assertNotNull(decodedToken.getExpiration());
		assertEquals(decodedToken.getExpiration()
		                         .getTime(), decodedToken.getNotBefore()
		                                                 .getTime() + 3600 * 1000);
		assertNotNull(decodedToken.get("grants"));
		List<Map<String, Object>> decodedGrants = (List<Map<String, Object>>) decodedToken.get("grants");
		assertEquals(0, decodedGrants.size());
	}

}
