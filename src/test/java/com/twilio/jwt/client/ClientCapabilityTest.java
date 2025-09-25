package com.twilio.jwt.client;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import com.twilio.jwt.Jwt;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.impl.DefaultClaims;
import io.jsonwebtoken.security.Keys;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;


import java.util.Arrays;
import java.util.Date;
import java.util.List;
import org.junit.jupiter.api.Test;

/**
 * Test class for {@link ClientCapability}.
 */
public class ClientCapabilityTest {

    private static final String ACCOUNT_SID = "AC123";
    private static byte[] SECRET;

    static {
      KeyGenerator keyGen = null;
      try {
        keyGen = KeyGenerator.getInstance("HmacSHA256");
        keyGen.init(2048); // Use 2048 bits for stronger security
        SecretKey pair = keyGen.generateKey();
        SECRET = pair.getEncoded();
      } catch (NoSuchAlgorithmException e) {
        throw new RuntimeException(e);
      }
    }
    public Claims getClaims(Jwt token) throws IOException {
      io.jsonwebtoken.Jwt<?, ?> claims = Jwts.parser()
          .verifyWith(Keys.hmacShaKeyFor(SECRET))
          .build()
          .parse(token.toJwt());

      return (DefaultClaims)claims.getPayload();
    }

    @Test
    public void testEmptyToken() throws IOException {
        Jwt jwt = new ClientCapability.Builder(ACCOUNT_SID, SECRET).build();

        Claims claims = getClaims(jwt);

        assertEquals(ACCOUNT_SID, claims.getIssuer());
        assertTrue(claims.getExpiration().getTime() > new Date().getTime());
    }

    @Test
    public void testToken() throws IOException {
        List<Scope> scopes = Arrays.asList(
            new IncomingClientScope("incomingClient"),
            new EventStreamScope.Builder().build(),
            new OutgoingClientScope.Builder("AP123").build()
        );
        Jwt jwt = new ClientCapability.Builder(ACCOUNT_SID, SECRET).scopes(scopes).build();

        Claims claims = getClaims(jwt);

        assertEquals(ACCOUNT_SID, claims.getIssuer());
        assertTrue(claims.getExpiration().getTime() > new Date().getTime());

        assertEquals(
            "scope:client:incoming?clientName=incomingClient " +
            "scope:stream:subscribe?path=/2010-04-01/Events " +
            "scope:client:outgoing?appSid=AP123",
            claims.get("scope")
        );
    }

}
