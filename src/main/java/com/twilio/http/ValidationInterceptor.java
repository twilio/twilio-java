package com.twilio.http;

import com.google.common.collect.Lists;
import com.twilio.jwt.Jwt;
import com.twilio.jwt.validation.ValidationToken;
import org.apache.http.HttpException;
import org.apache.http.HttpRequest;
import org.apache.http.HttpRequestInterceptor;
import org.apache.http.protocol.HttpContext;

import java.io.IOException;
import java.security.PrivateKey;
import java.util.List;

public class ValidationInterceptor implements HttpRequestInterceptor {

    private static final List<String> HEADERS = Lists.newArrayList("authorization", "host");

    private final String accountSid;
    private final String credentialSid;
    private final String signingKeySid;
    private final PrivateKey privateKey;

    /**
     * Create a new ValidationInterceptor.
     *
     * @param  accountSid Twilio Acocunt SID
     * @param  credentialSid Twilio Credential SID
     * @param  signingKeySid Twilio Signing Key
     * @param  privateKey Private Key
     */
    public ValidationInterceptor(String accountSid, String credentialSid, String signingKeySid, PrivateKey privateKey) {
        this.accountSid = accountSid;
        this.credentialSid = credentialSid;
        this.signingKeySid = signingKeySid;
        this.privateKey = privateKey;
    }

    @Override
    public void process(HttpRequest request, HttpContext context) throws HttpException, IOException {
        Jwt jwt = ValidationToken.fromHttpRequest(
            accountSid,
            credentialSid,
            signingKeySid,
            privateKey,
            request,
            HEADERS
        );
        request.addHeader("Twilio-Client-Validation", jwt.toJwt());
    }
}
