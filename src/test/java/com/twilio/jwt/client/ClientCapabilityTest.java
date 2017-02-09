package com.twilio.jwt.client;

import com.google.common.collect.Lists;
import com.twilio.jwt.Jwt;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import org.junit.Assert;
import org.junit.Test;

import java.util.Date;
import java.util.List;

/**
 * Test class for {@link ClientCapability}.
 */
public class ClientCapabilityTest {

    private static final String ACCOUNT_SID = "AC123";
    private static final String SECRET = "secret";

    @Test
    public void testEmptyToken() {
        Jwt jwt = new ClientCapability.Builder(ACCOUNT_SID, SECRET).build();

        Claims claims =
            Jwts.parser()
                .setSigningKey(SECRET.getBytes())
                .parseClaimsJws(jwt.toJwt())
                .getBody();

        Assert.assertEquals(ACCOUNT_SID, claims.getIssuer());
        Assert.assertTrue(claims.getExpiration().getTime() > new Date().getTime());
    }

    @Test
    public void testToken() {
        List<Scope> scopes = Lists.newArrayList(
            new IncomingClientScope("incomingClient"),
            new EventStreamScope.Builder().build(),
            new OutgoingClientScope.Builder("AP123").build()
        );
        Jwt jwt = new ClientCapability.Builder(ACCOUNT_SID, SECRET).scopes(scopes).build();

        Claims claims =
            Jwts.parser()
                .setSigningKey(SECRET.getBytes())
                .parseClaimsJws(jwt.toJwt())
                .getBody();

        Assert.assertEquals(ACCOUNT_SID, claims.getIssuer());
        Assert.assertTrue(claims.getExpiration().getTime() > new Date().getTime());

        Assert.assertEquals(
            "scope:client:incoming?clientName=incomingClient " +
            "scope:stream:subscribe?path=/2010-04-01/Events " +
            "scope:client:outgoing?appSid=AP123",
            claims.get("scope")
        );
    }

}
