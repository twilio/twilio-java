package com.twilio.http;

import com.google.common.collect.Lists;
import com.twilio.jwt.Jwt;
import com.twilio.jwt.validation.ValidationToken;
import org.apache.http.HttpException;
import org.apache.http.HttpRequest;
import org.apache.http.HttpRequestInterceptor;
import org.apache.http.protocol.HttpContext;

import java.io.IOException;
import java.util.List;

public class ValidationInterceptor implements HttpRequestInterceptor {

    private static final List<String> HEADERS = Lists.newArrayList("authorization", "host");

    private final String credentialSid;
    private final String privateKey;

    public ValidationInterceptor(String credentialSid, String privateKey) {
        this.credentialSid = credentialSid;
        this.privateKey = privateKey;
    }

    @Override
    public void process(HttpRequest request, HttpContext context) throws HttpException, IOException {
        Jwt jwt = ValidationToken.fromHttpRequest(credentialSid, privateKey, request, HEADERS);
        request.addHeader("Twilio-Client-Validation", jwt.toJwt());
    }
}