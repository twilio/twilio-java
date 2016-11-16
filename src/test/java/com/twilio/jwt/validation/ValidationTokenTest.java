package com.twilio.jwt.validation;

import com.google.common.collect.Lists;
import mockit.Mocked;
import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.HttpEntityEnclosingRequest;
import org.apache.http.message.BasicHeader;
import org.junit.Before;

import java.util.List;

public class ValidationTokenTest {

    private static final List<String> SIGNED_HEADERS = Lists.newArrayList("host", "authorization");
//    private static final String CREDENTIAL_SID = "CR123";
    private static final String CREDENTIAL_SID = "CR123";
    private static final String SECRET = "secret";

    private Header[] headers;

    @Mocked
    private HttpEntityEnclosingRequest request;

    @Mocked
    private HttpEntity entity;

    @Before
    public void setup() {
        headers = new Header[2];
        headers[0] = new BasicHeader("host", "api.twilio.com");
        headers[1] = new BasicHeader("authorization", "foobar");
    }

//    @Test
//    public void testTokenBuilder() {
//        Jwt jwt = new ValidationToken.Builder(CREDENTIAL_SID, SECRET)
//            .method("GET")
//            .uri("/Messages")
//            .queryString("PageSize=5&Limit=10")
//            .headers(headers)
//            .signedHeaders(SIGNED_HEADERS)
//            .requestBody("foobar")
//            .build();
//
//        Claims claims =
//            Jwts.parser()
//                .setSigningKey(SECRET.getBytes())
//                .parseClaimsJws(jwt.toJwt())
//                .getBody();
//
//
//        Assert.assertEquals("authorization;host", claims.get("hrh"));
//        Assert.assertEquals("4dc9b67bed579647914587b0e22a1c65c1641d8674797cd82de65e766cce5f80", claims.get("rqh"));
//    }
//
//    @Test
//    public void testTokenFromHttpRequest() throws IOException {
//        new Expectations() {{
//            request.getRequestLine().getMethod();
//            result = "GET";
//
//            request.getRequestLine().getUri();
//            result = "/Messages?PageSize=5&Limit=10";
//
//            request.getAllHeaders();
//            result = headers;
//        }};
//
//        Jwt jwt = ValidationToken.fromHttpRequest(CREDENTIAL_SID, SECRET, request, SIGNED_HEADERS);
//        Claims claims =
//            Jwts.parser()
//                .setSigningKey(SECRET.getBytes())
//                .parseClaimsJws(jwt.toJwt())
//                .getBody();
//
//
//        Assert.assertEquals("authorization;host", claims.get("hrh"));
//        Assert.assertEquals("4b3d2666845a38f00259a5231a08765bb2d12564bc4469fd5b2816204c588967", claims.get("rqh"));
//    }
//
//    @Test
//    public void testTokenFromPostRequest() throws IOException {
//        new Expectations() {{
//            request.getRequestLine().getMethod();
//            result = "POST";
//
//            request.getRequestLine().getUri();
//            result = "/Messages";
//
//            request.getAllHeaders();
//            result = headers;
//
//            request.getEntity();
//            result = entity;
//
//            entity.getContent();
//            result = new ByteArrayInputStream("testbody".getBytes(StandardCharsets.UTF_8));
//        }};
//
//        Jwt jwt = ValidationToken.fromHttpRequest(CREDENTIAL_SID, SECRET, request, SIGNED_HEADERS);
//        Claims claims =
//            Jwts.parser()
//                .setSigningKey(SECRET.getBytes())
//                .parseClaimsJws(jwt.toJwt())
//                .getBody();
//
//
//        Assert.assertEquals("authorization;host", claims.get("hrh"));
//        Assert.assertEquals("bd792c967c20d546c738b94068f5f72758a10d26c12979677501e1eefe58c65a", claims.get("rqh"));
//    }
//
//    private void validateToken(Claims claims) {
//        Assert.assertEquals(CREDENTIAL_SID, claims.getIssuer());
//
//        Assert.assertNotNull(claims.getExpiration());
//        Assert.assertNotNull(claims.get("hrh"));
//        Assert.assertNotNull(claims.get("rqh"));
//
//        Assert.assertTrue(claims.getExpiration().getTime() > new Date().getTime());
//    }

}
