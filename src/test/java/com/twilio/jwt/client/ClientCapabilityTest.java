package com.twilio.jwt.client;

import com.twilio.jwt.Jwt;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

/**
 * Test class for {@link ClientCapability}.
 */
public class ClientCapabilityTest {

    private static final String ACCOUNT_SID = "AC123";
    private static final String SECRET = "secretsecretsecretsecretsecretsecretsecret00";

    @Test
    public void testEmptyToken() {
        Jwt jwt = new ClientCapability.Builder(ACCOUNT_SID, SECRET).build();

        Claims claims =
            Jwts.parser()
                .setSigningKey(SECRET.getBytes()).build()
                .parseSignedClaims(jwt.toJwt())
                .getBody();

        Assert.assertEquals(ACCOUNT_SID, claims.getIssuer());
        Assert.assertTrue(claims.getExpiration().getTime() > new Date().getTime());
    }

    @Test
    public void testToken() {
        List<Scope> scopes = Arrays.asList(
            new IncomingClientScope("incomingClient"),
            new EventStreamScope.Builder().build(),
            new OutgoingClientScope.Builder("AP123").build()
        );
        Jwt jwt = new ClientCapability.Builder(ACCOUNT_SID, SECRET).scopes(scopes).build();

        Claims claims =
            Jwts.parser()
                .setSigningKey(SECRET).build()
                .parseSignedClaims(jwt.toJwt())
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
