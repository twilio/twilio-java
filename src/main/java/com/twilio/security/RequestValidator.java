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

                for (String key : sortedKeys) {
                    builder.append(key);

                    String value = params.get(key);
                    builder.append(value == null ? "" : value);
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

        int length = a.length();
        if (length != b.length()) {
            return false;
        }

        int mismatch = 0;
        for (int i = 0; i < length; ++i) {
            mismatch |= a.charAt(i) ^ b.charAt(i);
        }
        return mismatch == 0;
    }

}
