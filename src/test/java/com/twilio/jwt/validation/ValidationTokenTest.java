package com.twilio.jwt.validation;

import com.google.common.collect.Lists;
import com.twilio.jwt.Jwt;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import mockit.Expectations;
import mockit.Mocked;
import org.apache.http.Header;
import org.apache.http.HttpRequest;
import org.apache.http.RequestLine;
import org.apache.http.message.BasicHeader;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.util.Date;
import java.util.List;

public class ValidationTokenTest {

    private static final List<String> SIGNED_HEADERS = Lists.newArrayList("host", "authorization");
    private static final String CREDENTIAL_SID = "CR123";
    private static final String SECRET = "secret";

    private Header[] headers;

    @Mocked
    private HttpRequest request;

    @Mocked
    private RequestLine requestLine;

    @Before
    public void setup() {
        headers = new Header[2];
        headers[0] = new BasicHeader("host", "api.twilio.com");
        headers[1] = new BasicHeader("authorization", "foobar");
    }

    @Test
    public void testTokenBuilder() {
        Jwt jwt = new ValidationToken.Builder(CREDENTIAL_SID, SECRET)
            .method("GET")
            .uri("/Messages")
            .queryString("PageSize=5&Limit=10")
            .headers(headers)
            .signedHeaders(SIGNED_HEADERS)
            .requestBody("foobar")
            .build();

        Claims claims =
            Jwts.parser()
                .setSigningKey(SECRET.getBytes())
                .parseClaimsJws(jwt.toJwt())
                .getBody();


        Assert.assertEquals("authorization;host", claims.get("hrh"));
        Assert.assertEquals("bfafc3f201c57e422fd753271b15de4893e20ab1acb41c0c0b5b10c31497b7d6", claims.get("rqh"));
    }

    @Test
    public void testTokenFromHttpRequest() throws IOException {
        new Expectations() {{
            request.getRequestLine().getMethod();
            result = "GET";

            request.getRequestLine().getUri();
            result = "/Messages?PageSize=5&Limit=10";

            request.getAllHeaders();
            result = headers;
        }};

        Jwt jwt = ValidationToken.fromHttpRequest(CREDENTIAL_SID, SECRET, request, SIGNED_HEADERS);
        Claims claims =
            Jwts.parser()
                .setSigningKey(SECRET.getBytes())
                .parseClaimsJws(jwt.toJwt())
                .getBody();


        Assert.assertEquals("authorization;host", claims.get("hrh"));
        Assert.assertEquals("3e84e1e5af4c084e413f9bd103f9f11abb3d5f58c4b0b72a79a986aebe58cd5b", claims.get("rqh"));
    }

    private void validateToken(Claims claims) {
        Assert.assertEquals(CREDENTIAL_SID, claims.getIssuer());

        Assert.assertNotNull(claims.getExpiration());
        Assert.assertNotNull(claims.get("hrh"));
        Assert.assertNotNull(claims.get("rqh"));

        Assert.assertTrue(claims.getExpiration().getTime() > new Date().getTime());
    }

}
