package com.twilio.jwt.validation;

import com.google.common.collect.Lists;
import com.twilio.http.ValidationInterceptor;
import com.twilio.jwt.Jwt;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import mockit.Expectations;
import mockit.Mocked;
import org.apache.http.*;
import org.apache.http.client.protocol.HttpClientContext;
import org.apache.http.message.BasicHeader;
import org.apache.http.message.BasicHttpRequest;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.PrivateKey;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class ValidationTokenTest {

    private static final List<String> SIGNED_HEADERS = Lists.newArrayList("host", "authorization");
    private static final String ACCOUNT_SID = "AC123";
    private static final String CREDENTIAL_SID = "CR123";
    private static final String SIGNING_KEY_SID = "SK123";

    private Header[] headers;
    private PrivateKey privateKey;

    @Mocked
    private HttpRequest request;

    @Mocked
    private HttpEntityEnclosingRequest requestWithEntity;

    @Mocked
    private HttpEntity entity;

    @Before
    public void setup() throws Exception {
        headers = new Header[2];
        headers[0] = new BasicHeader("host", "api.twilio.com");
        headers[1] = new BasicHeader("authorization", "foobar");

        KeyPairGenerator keyGen = KeyPairGenerator.getInstance("RSA");
        keyGen.initialize(2048);
        KeyPair pair = keyGen.generateKeyPair();
        privateKey = pair.getPrivate();
    }

    @Test
    public void testTokenBuilder() {
        Jwt jwt = new ValidationToken.Builder(ACCOUNT_SID, CREDENTIAL_SID, SIGNING_KEY_SID, privateKey)
            .method("GET")
            .uri("/Messages")
            .queryString("PageSize=5&Limit=10")
            .headers(headers)
            .signedHeaders(SIGNED_HEADERS)
            .requestBody("foobar")
            .build();

        Claims claims =
            Jwts.parser()
                .setSigningKey(privateKey)
                .parseClaimsJws(jwt.toJwt())
                .getBody();


        this.validateToken(claims);
        Assert.assertEquals("authorization;host", claims.get("hrh"));
        Assert.assertEquals("4dc9b67bed579647914587b0e22a1c65c1641d8674797cd82de65e766cce5f80", claims.get("rqh"));
    }

    @Test
    public void testTokenFromHttpRequest() throws IOException {
        new Expectations() {{
            request.getRequestLine().getMethod();
            result = "POST";

            request.getRequestLine().getUri();
            result = "/Messages?PageSize=5&Limit=10";

            request.getAllHeaders();
            result = headers;
        }};

        Jwt jwt = ValidationToken.fromHttpRequest(ACCOUNT_SID, CREDENTIAL_SID, SIGNING_KEY_SID, privateKey, request, SIGNED_HEADERS);
        Claims claims =
            Jwts.parser()
                .setSigningKey(privateKey)
                .parseClaimsJws(jwt.toJwt())
                .getBody();


        this.validateToken(claims);
        Assert.assertEquals("authorization;host", claims.get("hrh"));
        Assert.assertEquals("712fbbec9dcb4fe58ed8caecf925d2fe10889f5d3f4b48e748157a4a1113697d", claims.get("rqh"));
    }

    @Test
    public void testTokenFromEntityRequest() throws IOException {
        new Expectations() {{
            requestWithEntity.getRequestLine().getMethod();
            result = "POST";

            requestWithEntity.getRequestLine().getUri();
            result = "/Messages";

            requestWithEntity.getAllHeaders();
            result = headers;

            requestWithEntity.getEntity();
            result = entity;

            entity.getContent();
            result = new ByteArrayInputStream("testbody".getBytes(StandardCharsets.UTF_8));
        }};

        Jwt jwt = ValidationToken.fromHttpRequest(ACCOUNT_SID, CREDENTIAL_SID, SIGNING_KEY_SID, privateKey, requestWithEntity, SIGNED_HEADERS);
        Claims claims =
            Jwts.parser()
                .setSigningKey(privateKey)
                .parseClaimsJws(jwt.toJwt())
                .getBody();


        this.validateToken(claims);
        Assert.assertEquals("authorization;host", claims.get("hrh"));
        Assert.assertEquals("bd792c967c20d546c738b94068f5f72758a10d26c12979677501e1eefe58c65a", claims.get("rqh"));
    }

    @Test
    public void testTokenFromHttpRequestWithHostPort() throws IOException {
        headers[0] = new BasicHeader("host", "api.twilio.com:443");
        new Expectations() {{
            request.getRequestLine().getMethod();
            result = "GET";

            request.getRequestLine().getUri();
            result = "/Messages?PageSize=5&Limit=10";

            request.getAllHeaders();
            result = headers;
        }};

        Jwt jwt = ValidationToken.fromHttpRequest(ACCOUNT_SID, CREDENTIAL_SID, SIGNING_KEY_SID, privateKey, request, SIGNED_HEADERS);
        Claims claims =
                Jwts.parser()
                    .setSigningKey(privateKey)
                    .parseClaimsJws(jwt.toJwt())
                    .getBody();


        this.validateToken(claims);
        Assert.assertEquals("authorization;host", claims.get("hrh"));
        Assert.assertEquals("4b3d2666845a38f00259a5231a08765bb2d12564bc4469fd5b2816204c588967", claims.get("rqh"));
    }

    @Test
    public void testMultithreadedValidations() throws InterruptedException, ExecutionException {
        int numThreads = 10;

        final ValidationInterceptor i = new ValidationInterceptor(ACCOUNT_SID, CREDENTIAL_SID, SIGNING_KEY_SID, privateKey);
        ExecutorService service = Executors.newFixedThreadPool(numThreads);

        Collection<Future<?>> futures = new ArrayList<>(numThreads);
        for (int t = 0; t < numThreads; t++) {
            futures.add(service.submit(new Runnable() {
                public void run() {
                    try {
                        i.process(getBasicRequest(), new HttpClientContext());
                    } catch (Exception e) {
                        e.printStackTrace();
                        Assert.fail(e.getMessage());
                    }
                }
            }));
        }

        for (Future<?> f : futures) {
            f.get();
        }
    }

    private BasicHttpRequest getBasicRequest() {
        return new BasicHttpRequest(
                "GET",
                "/some-url?with=params",
                new ProtocolVersion("HTTP", 1, 1)
        );
    }

    private void validateToken(Claims claims) {
        Assert.assertEquals(SIGNING_KEY_SID, claims.getIssuer());
        Assert.assertEquals(ACCOUNT_SID, claims.getSubject());

        Assert.assertNotNull(claims.getExpiration());
        Assert.assertNotNull(claims.get("hrh"));
        Assert.assertNotNull(claims.get("rqh"));

        Assert.assertTrue(claims.getExpiration().getTime() > new Date().getTime());
    }

}
