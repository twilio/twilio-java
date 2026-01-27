package com.twilio.jwt.validation;

import com.twilio.jwt.Jwt;
import io.jsonwebtoken.SignatureAlgorithm;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.io.IOUtils;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.security.PrivateKey;
import java.util.*;
import java.util.function.Function;
import org.apache.hc.client5.http.classic.methods.HttpUriRequestBase;
import org.apache.hc.core5.http.ClassicHttpRequest;
import org.apache.hc.core5.http.Header;
import org.apache.hc.core5.http.HttpEntity;
import org.apache.hc.core5.http.HttpRequest;
import org.apache.hc.core5.http.ParseException;
import org.apache.hc.core5.http.io.entity.EntityUtils;
import org.apache.hc.core5.http.message.BasicClassicHttpRequest;

import static io.jsonwebtoken.SignatureAlgorithm.PS256;
import static io.jsonwebtoken.SignatureAlgorithm.RS256;

public class ValidationToken extends Jwt {

    private static final String CTY = "twilio-pkrv;v=1";

    private final String accountSid;
    private final String credentialSid;
    private final String signingKeySid;
    private final String method;
    private final String uri;
    private final String queryString;
    private final Header[] headers;
    private final List<String> signedHeaders;
    private final String requestBody;

    private static final Set<SignatureAlgorithm> supportedAlgorithms
        = Collections.unmodifiableSet(new HashSet<>(Arrays.asList(PS256, RS256)));

    private ValidationToken(Builder b) {
        super(
            b.algorithm,
            b.privateKey,
            b.credentialSid,
            new Date(new Date().getTime() + b.ttl * 1000)
        );
        this.accountSid = b.accountSid;
        this.credentialSid = b.credentialSid;
        this.signingKeySid = b.signingKeySid;
        this.method = b.method;
        this.uri = b.uri;
        this.queryString = b.queryString;
        this.headers = b.headers;
        this.signedHeaders = b.signedHeaders;
        this.requestBody = b.requestBody;
    }

    @Override
    public Map<String, Object> getHeaders() {
        Map<String, Object> headers = new HashMap<>();
        headers.put("cty", CTY);
        headers.put("kid", this.credentialSid);
        return headers;
    }

    @Override
    public Map<String, Object> getClaims() {
        Map<String, Object> payload = new HashMap<>();

        payload.put("iss", this.signingKeySid);
        payload.put("sub", this.accountSid);

        // Sort the signed headers
        Collections.sort(signedHeaders);
        signedHeaders.replaceAll(String::toLowerCase);
        String includedHeaders = String.join(";", signedHeaders);
        payload.put("hrh", includedHeaders);

        String canonicalRequest =
            new RequestCanonicalizer(method, uri, queryString, requestBody, headers).create(signedHeaders);

        // Hash and hex the canonical request
        String hashedSignature = DigestUtils.sha256Hex(canonicalRequest);
        payload.put("rqh", hashedSignature);

        return payload;
    }

    /**
     * Create a ValidationToken from an HTTP Request.
     *
     * @param accountSid    Twilio Account SID
     * @param credentialSid Twilio Credential SID
     * @param signingKeySid Twilio Signing Key SID
     * @param privateKey    Private Key
     * @param request       HTTP Request
     * @param signedHeaders Headers to sign
     * @return The ValidationToken generated from the HttpRequest
     * @throws IOException when unable to generate
     */
    public static ValidationToken fromHttpRequest(
        String accountSid,
        String credentialSid,
        String signingKeySid,
        PrivateKey privateKey,
        HttpRequest request,
        List<String> signedHeaders
    ) throws IOException, ParseException {

         return fromHttpRequest(accountSid, credentialSid, signingKeySid, privateKey, request, signedHeaders, SignatureAlgorithm.RS256);
    }

    /**
     * Create a ValidationToken from an HTTP Request.
     *
     * @param accountSid    Twilio Account SID
     * @param credentialSid Twilio Credential SID
     * @param signingKeySid Twilio Signing Key SID
     * @param privateKey    Private Key
     * @param request       HTTP Request
     * @param signedHeaders Headers to sign
     * @param algorithm     Client validation algorithm
     * @return The ValidationToken generated from the HttpRequest
     * @throws IOException when unable to generate
     */
    public static ValidationToken fromHttpRequest(
        String accountSid,
        String credentialSid,
        String signingKeySid,
        PrivateKey privateKey,
        HttpRequest request,
        List<String> signedHeaders,
        SignatureAlgorithm algorithm
         ) throws IOException, ParseException {
        Builder builder = new Builder(accountSid, credentialSid, signingKeySid, privateKey);

        String method = request.getMethod();
        builder.method(method);
        builder.algorithm(algorithm);

        String uri = request.getRequestUri();
        if (uri.contains("?")) {
            String[] uriParts = uri.split("\\?");
            builder.uri(uriParts[0]);
            builder.queryString(uriParts[1]);
        } else {
            builder.uri(uri);
        }

        builder.headers(request.getHeaders());
        builder.signedHeaders(signedHeaders);

        /**
         * If the request encloses an "entity", use it for the body. Note that this is dependent on several factors
         * during request building and is not solely based on the specified method.
         *
         * @see org.apache.http.client.methods.RequestBuilder#build
         */
        if (request instanceof BasicClassicHttpRequest && ((BasicClassicHttpRequest) request).getEntity() != null) {
            HttpEntity entity = ((BasicClassicHttpRequest) request).getEntity();
            builder.requestBody(EntityUtils.toString(entity, StandardCharsets.UTF_8));
        }

        return builder.build();
    }

    private static Function<String, String> LOWERCASE_STRING = new Function<String, String>() {
        @Override
        public String apply(String s) {
            return s.toLowerCase();
        }
    };

    public static class Builder {

        private String accountSid;
        private String credentialSid;
        private String signingKeySid;
        private PrivateKey privateKey;
        private String method;
        private String uri;
        private String queryString = "";
        private Header[] headers;
        private List<String> signedHeaders = Collections.emptyList();
        private String requestBody = "";
        private int ttl = 300;

        private SignatureAlgorithm algorithm = SignatureAlgorithm.RS256;

        /**
         * Create a new ValidationToken Builder.
         *
         * @param accountSid    Twilio Account SID
         * @param credentialSid Twilio Crednetial SID
         * @param signingKeySid Twilio Signing Key SID
         * @param privateKey    Private Key
         */
        public Builder(String accountSid, String credentialSid, String signingKeySid, PrivateKey privateKey) {
            this.accountSid = accountSid;
            this.credentialSid = credentialSid;
            this.signingKeySid = signingKeySid;
            this.privateKey = privateKey;
        }

        public Builder method(String method) {
            this.method = method;
            return this;
        }

        public Builder uri(String uri) {
            this.uri = uri;
            return this;
        }

        public Builder queryString(String queryString) {
            this.queryString = queryString;
            return this;
        }

        public Builder headers(Header[] headers) {
            this.headers = headers;
            return this;
        }

        public Builder signedHeaders(List<String> signedHeaders) {
            if (signedHeaders == null) {
                this.signedHeaders = Collections.emptyList();
            } else {
                this.signedHeaders = new ArrayList<>(signedHeaders);
            }

            return this;
        }

        public Builder requestBody(String requestBody) {
            this.requestBody = requestBody;
            return this;
        }

        public Builder ttl(int ttl) {
            this.ttl = ttl;
            return this;
        }

        public Builder algorithm(SignatureAlgorithm algorithm) {
            if (!supportedAlgorithms.contains(algorithm)) {
                throw new IllegalArgumentException("Not supported!");
            }
            this.algorithm = algorithm;
            return this;
        }
        public ValidationToken build() {
            return new ValidationToken(this);
        }
    }

}
