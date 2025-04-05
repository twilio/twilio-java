package com.twilio.jwt.accesstoken;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.twilio.jwt.Jwt;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.JwsHeader;
import io.jsonwebtoken.JwtParser;
import io.jsonwebtoken.JwtParserBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import java.io.IOException;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import org.junit.Assert;
import org.junit.Test;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * Test class for {@link AccessToken}.
 */
public class AccessTokenTest {

    private static final String ACCOUNT_SID = "AC123";
    private static final String SIGNING_KEY_SID = "SK123";
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

    private void validateToken(Claims claims) {
        Assert.assertEquals(SIGNING_KEY_SID, claims.getIssuer());
        Assert.assertEquals(ACCOUNT_SID, claims.getSubject());

        Assert.assertNotNull(claims.getExpiration());
        Assert.assertNotNull(claims.getId());
        Assert.assertNotNull(claims.get("grants"));

        Assert.assertTrue(claims.getId().startsWith(claims.getIssuer() + "-"));
        Assert.assertTrue(claims.getExpiration().getTime() > new Date().getTime());
    }

    private Claims getClaimFromJwtToken(Jwt token) throws IOException {
        io.jsonwebtoken.Jwt<?, ?> claims = Jwts.parser()
                                        .verifyWith(Keys.hmacShaKeyFor(SECRET))
                                        .build()
                                        .parse(token.toJwt());
        ObjectMapper objectMapper = new ObjectMapper();
        Map<String,?> map = (Map<String,?>)objectMapper.readValue((byte[])claims.getPayload(), Map.class);
        return Jwts.claims().add(map).build();
    }

    private void testVoiceToken(Boolean allow) throws IOException {
        Map<String, Object> params = new HashMap<>();
        params.put("foo", "bar");

        VoiceGrant pvg = new VoiceGrant()
            .setOutgoingApplication("AP123", params)
            .setIncomingAllow(allow);

        Jwt token =
            new AccessToken.Builder(ACCOUNT_SID, SIGNING_KEY_SID, SECRET)
                .grant(pvg)
                .build();

        Claims claims = getClaimFromJwtToken(token);

        validateToken(claims);
        Map<String, Object> decodedGrants = (Map<String, Object>) claims.get("grants");
        Assert.assertEquals(1, decodedGrants.size());

        Map<String, Object> pvgGrant = (Map<String, Object>) decodedGrants.get("voice");

        Map<String, Object> incoming = (Map<String, Object>) pvgGrant.get("incoming");
        Assert.assertEquals(allow, incoming.get("allow"));

        Map<String, Object> outgoing = (Map<String, Object>) pvgGrant.get("outgoing");
        Map<String, Object> outgoingParams = (Map<String, Object>) outgoing.get("params");
        Assert.assertEquals("AP123", outgoing.get("application_sid"));
        Assert.assertEquals("bar", outgoingParams.get("foo"));
    }

    @Test
    public void testEmptyToken() throws IOException {
        Jwt token =
            new AccessToken.Builder(ACCOUNT_SID, SIGNING_KEY_SID, SECRET)
                .build();

        Claims claims = getClaimFromJwtToken(token);

        validateToken(claims);
    }

    @Test
    public void testOptionalValues() throws IOException {
        Jwt token =
            new AccessToken.Builder(ACCOUNT_SID, SIGNING_KEY_SID, SECRET)
                .identity(ACCOUNT_SID)
                .nbf(new Date())
                .build();

        Claims claims = getClaimFromJwtToken(token);

        validateToken(claims);
        Assert.assertTrue(claims.getNotBefore().getTime() <= new Date().getTime());
    }

    @Test
    public void testRegion() {
        Jwt token = new AccessToken.Builder(ACCOUNT_SID, SIGNING_KEY_SID, SECRET)
          .region("foo")
          .build();

        io.jsonwebtoken.Jwt<?, ?> jwts = Jwts.parser()
            .verifyWith(Keys.hmacShaKeyFor(SECRET))
            .build()
            .parse(token.toJwt());

        Assert.assertEquals("foo", jwts.getHeader().get("twr"));
    }

    @Test
    public void testEmptyRegion() {
        Jwt token = new AccessToken.Builder(ACCOUNT_SID, SIGNING_KEY_SID, SECRET).build();

        io.jsonwebtoken.Jwt<?, ?> jwts = Jwts.parser()
            .verifyWith(Keys.hmacShaKeyFor(SECRET))
            .build()
            .parse(token.toJwt());

        Assert.assertEquals(null, jwts.getHeader().get("twr"));
    }

    @Test
    public void testVideoGrant() throws IOException {
        VideoGrant cg = new VideoGrant().setRoom("RM123");
        Jwt token =
            new AccessToken.Builder(ACCOUNT_SID, SIGNING_KEY_SID, SECRET)
                .grant(cg)
                .build();

        Claims claims = getClaimFromJwtToken(token);

        validateToken(claims);

        Map<String, Object> decodedGrants = (Map<String, Object>) claims.get("grants");
        Assert.assertEquals(1, decodedGrants.size());

        Map<String, Object> grant = (Map<String, Object>) decodedGrants.get("video");
        Assert.assertEquals("RM123", grant.get("room"));
    }

    @Test
    public void testChatGrant() throws IOException {
        ChatGrant cg = new ChatGrant()
            .setDeploymentRoleSid("RL123")
            .setEndpointId("foobar")
            .setPushCredentialSid("CR123")
            .setServiceSid("IS123");
        Jwt token =
            new AccessToken.Builder(ACCOUNT_SID, SIGNING_KEY_SID, SECRET)
                .grant(cg)
                .build();

        Claims claims = getClaimFromJwtToken(token);

        validateToken(claims);

        Map<String, Object> decodedGrants = (Map<String, Object>) claims.get("grants");
        Assert.assertEquals(1, decodedGrants.size());

        Map<String, Object> grant = (Map<String, Object>) decodedGrants.get("chat");
        Assert.assertEquals("RL123", grant.get("deployment_role_sid"));
        Assert.assertEquals("foobar", grant.get("endpoint_id"));
        Assert.assertEquals("CR123", grant.get("push_credential_sid"));
        Assert.assertEquals("IS123", grant.get("service_sid"));
    }

    @Test
    public void testSyncGrant() throws IOException {
        SyncGrant sg = new SyncGrant()
                .setEndpointId("foobar")
                .setServiceSid("IS123");
        Jwt token =
                new AccessToken.Builder(ACCOUNT_SID, SIGNING_KEY_SID, SECRET)
                        .grant(sg)
                        .build();

        Claims claims = getClaimFromJwtToken(token);

        validateToken(claims);

        Map<String, Object> decodedGrants = (Map<String, Object>) claims.get("grants");
        Assert.assertEquals(1, decodedGrants.size());

        Map<String, Object> grant = (Map<String, Object>) decodedGrants.get("data_sync");
        Assert.assertEquals("foobar", grant.get("endpoint_id"));
        Assert.assertEquals("IS123", grant.get("service_sid"));
    }

    @Test
    public void testPlaybackGrant() throws IOException {
        Map<String, Object> grantPayload = new HashMap<>();
        grantPayload.put("requestCredentials", null);
        grantPayload.put("playbackUrl", "https://000.us-east-1.playback.live-video.net/api/video/v1/us-east-000.channel.000?token=xxxxx");
        grantPayload.put("playerStreamerSid", "VJXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX");

        PlaybackGrant pg = new PlaybackGrant().setGrant(grantPayload);
        Jwt token =
            new AccessToken.Builder(ACCOUNT_SID, SIGNING_KEY_SID, SECRET)
                .grant(pg)
                .build();

        Claims claims = getClaimFromJwtToken(token);

        validateToken(claims);

        Map<String, Object> decodedGrants = (Map<String, Object>) claims.get("grants");
        Assert.assertEquals(1, decodedGrants.size());

        Map<String, Object> grant = (Map<String, Object>) decodedGrants.get("player");
        Assert.assertNull(grant.get("requestCredentials"));
        Assert.assertEquals("https://000.us-east-1.playback.live-video.net/api/video/v1/us-east-000.channel.000?token=xxxxx", grant.get("playbackUrl"));
        Assert.assertEquals("VJXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX", grant.get("playerStreamerSid"));
    }

    @Test
    public void testTaskRouterGrant() throws IOException {
        TaskRouterGrant trg = new TaskRouterGrant()
                .setWorkspaceSid("WS123")
                .setWorkerSid("WK123")
                .setRole("worker");

        Jwt token =
                new AccessToken.Builder(ACCOUNT_SID, SIGNING_KEY_SID, SECRET)
                        .grant(trg)
                        .build();

        Claims claims = getClaimFromJwtToken(token);

        validateToken(claims);

        Map<String, Object> decodedGrants = (Map<String, Object>) claims.get("grants");
        Assert.assertEquals(1, decodedGrants.size());

        Map<String, Object> grant = (Map<String, Object>) decodedGrants.get("task_router");
        Assert.assertEquals("WS123", grant.get("workspace_sid"));
        Assert.assertEquals("WK123", grant.get("worker_sid"));
        Assert.assertEquals("worker", grant.get("role"));
    }

    @Test
    public void testVoiceTokenWithIncoming() throws IOException {
      testVoiceToken(true);
      testVoiceToken(false);
    }

    @Test
    public void testVoiceTokenWithoutIncoming() throws IOException {
        Map<String, Object> params = new HashMap<>();
        params.put("foo", "bar");

        VoiceGrant pvg = new VoiceGrant()
            .setOutgoingApplication("AP123", params);

        Jwt token =
            new AccessToken.Builder(ACCOUNT_SID, SIGNING_KEY_SID, SECRET)
                .grant(pvg)
                .build();

        Claims claims = getClaimFromJwtToken(token);

        validateToken(claims);
        Map<String, Object> decodedGrants = (Map<String, Object>) claims.get("grants");
        Assert.assertEquals(1, decodedGrants.size());

        Map<String, Object> pvgGrant = (Map<String, Object>) decodedGrants.get("voice");

        Assert.assertEquals(null, pvgGrant.get("incoming"));

        Map<String, Object> outgoing = (Map<String, Object>) pvgGrant.get("outgoing");
        Map<String, Object> outgoingParams = (Map<String, Object>) outgoing.get("params");
        Assert.assertEquals("AP123", outgoing.get("application_sid"));
        Assert.assertEquals("bar", outgoingParams.get("foo"));
    }

    @Test()
    public void testNullValues() throws IOException {
        ChatGrant cg = new ChatGrant().setDeploymentRoleSid("RL123");
        Jwt token =
            new AccessToken.Builder(ACCOUNT_SID, SIGNING_KEY_SID, SECRET)
                .grant(cg)
                .build();

        Claims claims = getClaimFromJwtToken(token);

        validateToken(claims);

        Map<String, Object> decodedGrants = (Map<String, Object>) claims.get("grants");
        Map<String, Object> grant = (Map<String, Object>) decodedGrants.get("chat");

        Assert.assertEquals("RL123", grant.get("deployment_role_sid"));
        Assert.assertFalse(grant.containsKey("endpoint_id"));
    }
}
