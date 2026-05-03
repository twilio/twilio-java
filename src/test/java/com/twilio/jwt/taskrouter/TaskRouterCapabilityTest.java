package com.twilio.jwt.taskrouter;

import io.jsonwebtoken.impl.DefaultClaims;
import io.jsonwebtoken.security.Keys;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import org.junit.Assert;
import org.junit.Test;

import com.twilio.http.HttpMethod;
import com.twilio.jwt.Jwt;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;

/**
 * Test class for {@link TaskRouterCapability}.
 */
public class TaskRouterCapabilityTest {

    private static final String ACCOUNT_SID = "AC123";
    private static byte[] AUTH_TOKEN ;
    private static final String WORKSPACE_SID = "WS123";
    private static final String WORKER_SID = "WK123";

    static {
        KeyGenerator keyGen = null;
        try {
            keyGen = KeyGenerator.getInstance("HmacSHA256");
            keyGen.init(2048); // Use 2048 bits for stronger security
            SecretKey pair = keyGen.generateKey();
            AUTH_TOKEN = pair.getEncoded();
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }


    public Claims getClaims(Jwt token) throws IOException {
        io.jsonwebtoken.Jwt<?, ?> claims = Jwts.parser()
            .verifyWith(Keys.hmacShaKeyFor(AUTH_TOKEN))
            .build()
            .parse(token.toJwt());

        return (DefaultClaims)claims.getPayload();
    }

    @Test
    public void testToken() throws IOException {
        List<Policy> policies = Arrays.asList(
            new Policy.Builder().url(UrlUtils.workspaces()).build()
        );
        final Jwt jwt =
                new TaskRouterCapability.Builder(ACCOUNT_SID, AUTH_TOKEN, WORKSPACE_SID, WORKER_SID)
                .policies(policies)
                .build();
        Claims claims = getClaims(jwt);

        Assert.assertEquals(WORKSPACE_SID, claims.get("workspace_sid"));
        Assert.assertEquals(WORKER_SID, claims.get("channel"));
        Assert.assertEquals(ACCOUNT_SID, claims.get("account_sid"));
        Assert.assertEquals(ACCOUNT_SID, claims.getIssuer());
        Assert.assertTrue(claims.getExpiration().getTime() > new Date().getTime());
    }

    @Test
    public void testWorkerToken() throws IOException {
        final List<Policy> policies = PolicyUtils.defaultWorkerPolicies(WORKSPACE_SID, WORKER_SID);

        final Map<String, FilterRequirement> activityUpdateFilter = new HashMap<>();
        activityUpdateFilter.put("ActivitySid", FilterRequirement.REQUIRED);

        final Policy allowActivityUpdates = new Policy.Builder()
                .url(UrlUtils.worker(WORKSPACE_SID, WORKER_SID))
                .method(HttpMethod.POST)
                .postFilter(activityUpdateFilter).build();

        final Policy allowTasksUpdate = new Policy.Builder()
                .url(UrlUtils.allTasks(WORKER_SID))
                .method(HttpMethod.POST)
                .build();

        final Policy allowReservationUpdate = new Policy.Builder()
                .url(UrlUtils.allReservations(WORKSPACE_SID, WORKER_SID))
                .method(HttpMethod.POST)
                .build();

        policies.add(allowActivityUpdates);
        policies.add(allowTasksUpdate);
        policies.add(allowReservationUpdate);

        final Jwt jwt =
                new TaskRouterCapability.Builder(ACCOUNT_SID, AUTH_TOKEN, WORKSPACE_SID, WORKER_SID)
                        .policies(policies)
                        .build();
        final Claims claims = getClaims(jwt);

        Assert.assertEquals(WORKSPACE_SID, claims.get("workspace_sid"));
        Assert.assertEquals(WORKER_SID, claims.get("channel"));
        Assert.assertEquals(ACCOUNT_SID, claims.get("account_sid"));
        Assert.assertEquals(ACCOUNT_SID, claims.getIssuer());
        Assert.assertTrue(claims.getExpiration().getTime() > new Date().getTime());

        final List<Policy> claimedPolicies = (List<Policy>) claims.get("policies");
        final int connectionPolicies = 2;
        Assert.assertEquals(policies.size() + connectionPolicies, claimedPolicies.size());
    }
}
