package com.twilio.jwt.validation;

import com.google.common.base.Charsets;
import com.google.common.base.Function;
import com.google.common.base.Joiner;
import com.google.common.hash.HashFunction;
import com.google.common.hash.Hashing;
import com.google.common.io.CharStreams;
import com.twilio.http.HttpMethod;
import com.twilio.jwt.Jwt;
import io.jsonwebtoken.SignatureAlgorithm;
import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.HttpEntityEnclosingRequest;
import org.apache.http.HttpRequest;
import org.apache.http.message.BasicHeader;

import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class ValidationToken extends Jwt {

    private static final HashFunction HASH_FUNCTION = Hashing.sha256();
    private static final String NEW_LINE = "\n";

    private final String method;
    private final String uri;
    private final String queryString;
    private final Header[] headers;
    private final List<String> signedHeaders;
    private final String requestBody;

    private ValidationToken(Builder b) {
        super(
            SignatureAlgorithm.HS256,
            b.privateKey,
            b.credentialSid,
            new Date(new Date().getTime() + b.ttl * 1000)
        );
        this.method = b.method;
        this.uri = b.uri;
        this.queryString = b.queryString;
        this.headers = b.headers;
        this.signedHeaders = b.signedHeaders;
        this.requestBody = b.requestBody;
    }

    @Override
    public Map<String, Object> getHeaders() {
        return Collections.emptyMap();
    }

    @Override
    public Map<String, Object> getClaims() {
        Map<String, Object> payload = new HashMap<>();

        // Sort the signed headers
        Collections.sort(signedHeaders);
        String includedHeaders = Joiner.on(";").join(signedHeaders);
        payload.put("hrh", includedHeaders);

        // Add the method and uri
        StringBuilder signature = new StringBuilder();
        signature.append(method).append(NEW_LINE);
        signature.append(uri).append(NEW_LINE);

        // Get the query args, sort and rejoin
        String[] queryArgs = queryString.split("&");
        Arrays.sort(queryArgs);
        String sortedQueryString = Joiner.on("&").join(queryArgs);
        signature.append(sortedQueryString).append(NEW_LINE);

        // Normalize all the headers
        Header[] lowercaseHeaders = LOWERCASE_KEYS.apply(headers);
        Arrays.sort(lowercaseHeaders, new Comparator<Header>() {
            @Override
            public int compare(Header o1, Header o2) {
                return o1.getName().compareTo(o2.getName());
            }
        });

        // Add the headers that we care about
        for (Header header: lowercaseHeaders) {
            if (signedHeaders.contains(header.getName().toLowerCase())) {
                signature.append(header.getName().toLowerCase().trim())
                    .append(":")
                    .append(header.getValue().trim())
                    .append(NEW_LINE);
            }
        }
        signature.append(NEW_LINE);

        // Mark the headers that we care about
        signature.append(includedHeaders).append(NEW_LINE);

        // Hash and hex the request payload
        String hashedPayload = HASH_FUNCTION.hashString(requestBody, Charsets.UTF_8).toString();
        signature.append(hashedPayload).append(NEW_LINE);

        // Hash and hex the canonical request
        String hashedSignature = HASH_FUNCTION.hashString(signature.toString(), Charsets.UTF_8).toString();
        payload.put("rqh", hashedSignature);

        return payload;
    }

    public static ValidationToken fromHttpRequest(
        String credentialSid,
        String privateKey,
        HttpRequest request,
        List<String> signedHeaders
    ) throws IOException {
        Builder builder = new Builder(credentialSid, privateKey);

        String method = request.getRequestLine().getMethod();
        builder.method(method);

        String uri = request.getRequestLine().getUri();
        if (uri.contains("?")) {
            String[] uriParts = uri.split("\\?");
            builder.uri(uriParts[0]);
            builder.queryString(uriParts[1]);
        } else {
            builder.uri(uri);
        }

        builder.headers(request.getAllHeaders());
        builder.signedHeaders(signedHeaders);

        if (HttpMethod.POST.toString().equals(method.toUpperCase())) {
            HttpEntity entity = ((HttpEntityEnclosingRequest)request).getEntity();
            builder.requestBody(CharStreams.toString(new InputStreamReader(entity.getContent(), Charsets.UTF_8)));
        }

        return builder.build();
    }

    private static Function<Header[], Header[]> LOWERCASE_KEYS = new Function<Header[], Header[]>() {
        @Override
        public Header[] apply(Header[] headers) {
            Header[] lowercaseHeaders = new Header[headers.length];
            for (int i = 0; i < headers.length; i++) {
                lowercaseHeaders[i] = new BasicHeader(headers[i].getName().toLowerCase(), headers[i].getValue());
            }

            return lowercaseHeaders;
        }
    };

    public static class Builder {

        private String credentialSid;
        private String privateKey;
        private String method;
        private String uri;
        private String queryString = "";
        private Header[] headers;
        private List<String> signedHeaders = Collections.emptyList();
        private String requestBody = "";
        private int ttl = 3600;

        public Builder(String credentialSid, String privateKey) {
            this.credentialSid = credentialSid;
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
            this.signedHeaders = signedHeaders;
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

        public ValidationToken build() {
            return new ValidationToken(this);
        }
    }

}
