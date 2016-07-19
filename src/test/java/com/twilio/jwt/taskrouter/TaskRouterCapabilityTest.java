package com.twilio.jwt.taskrouter;

import com.google.common.collect.Lists;
import com.twilio.jwt.Jwt;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import org.junit.Assert;
import org.junit.Test;

import java.util.Date;
import java.util.List;

/**
 * Test class for {@link TaskRouterCapability}.
 */
public class TaskRouterCapabilityTest {

    private static final String ACCOUNT_SID = "AC123";
    private static final String AUTH_TOKEN = "secret";
    private static final String WORKSPACE_SID = "WK123";
    private static final String CHANNEL_ID = "CH123";

    @Test
    public void testToken() {
        List<Policy> policies = Lists.newArrayList(
            new Policy.Builder().url(UrlUtils.workspaces()).build()
        );
        Jwt jwt =
            new TaskRouterCapability.Builder(ACCOUNT_SID, AUTH_TOKEN, WORKSPACE_SID, CHANNEL_ID)
                .policies(policies)
                .build();
        Claims claims =
            Jwts.parser()
                .setSigningKey(AUTH_TOKEN.getBytes())
                .parseClaimsJws(jwt.toJwt())
                .getBody();

        Assert.assertEquals(WORKSPACE_SID, claims.get("workspace_sid"));
        Assert.assertEquals(CHANNEL_ID, claims.get("channel_id"));
        Assert.assertEquals(ACCOUNT_SID, claims.get("account_sid"));
        Assert.assertEquals(ACCOUNT_SID, claims.getIssuer());
        Assert.assertTrue(claims.getExpiration().getTime() > new Date().getTime());
    }
}
