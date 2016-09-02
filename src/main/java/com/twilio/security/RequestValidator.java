package com.twilio.security;

import org.apache.commons.codec.binary.Base64;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

public class RequestValidator {

    private static final String HMAC = "HmacSHA1";

    private final SecretKeySpec signingKey;

    public RequestValidator(String authToken) {
        this.signingKey = new SecretKeySpec(authToken.getBytes(), HMAC);
    }

    public boolean validate(String url, Map<String, String> params, String expectedSignature) {
        String signature = getValidationSignature(url, params);
        return secureCompare(signature, expectedSignature);
    }

    private String getValidationSignature(String url, Map<String, String> params) {
        try {

            StringBuilder builder = new StringBuilder(url);
            if (params != null) {
                List<String> sortedKeys = new ArrayList<>(params.keySet());
                Collections.sort(sortedKeys);

                for (String s : sortedKeys) {
                    builder.append(s);

                    String v = params.get(s);
                    builder.append(v == null ? "" : v);
                }
            }

            Mac mac = Mac.getInstance(HMAC);
            mac.init(signingKey);

            byte[] rawHmac = mac.doFinal(builder.toString().getBytes(StandardCharsets.UTF_8));
            return new String(Base64.encodeBase64(rawHmac));

        } catch (Exception e) {
            return null;
        }
    }

    private boolean secureCompare(String a, String b) {
        if (a == null || b == null) {
            return false;
        }

        int n = a.length();
        if (n != b.length()) {
            return false;
        }

        int mismatch = 0;
        for (int i = 0; i < n; ++i) {
            mismatch |= a.charAt(i) ^ b.charAt(i);
        }
        return mismatch == 0;
    }

}
