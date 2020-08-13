package com.twilio.security;

import org.apache.http.NameValuePair;
import org.apache.http.client.utils.URLEncodedUtils;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import javax.xml.bind.DatatypeConverter;

import java.net.URI;
import java.net.URISyntaxException;

import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.*;

public class RequestValidator {

    private static final String HMAC = "HmacSHA1";

    private final SecretKeySpec signingKey;

    public RequestValidator(String authToken) {
        this.signingKey = new SecretKeySpec(authToken.getBytes(), HMAC);
    }

    public boolean validate(String url, Map<String, String> params, String expectedSignature) {
        // check signature of url with and without port, since sig generation on back
        // end is inconsistent
        String signatureWithPort = getValidationSignature(addPort(url), params);
        String signatureWithoutPort = getValidationSignature(removePort(url), params);
        // If either url produces a valid signature, we accept the request as valid
        return secureCompare(signatureWithPort, expectedSignature) || 
            secureCompare(signatureWithoutPort, expectedSignature);
    }

    public boolean validate(String url, String body, String expectedSignature) throws URISyntaxException {
        Map<String, String> empty = new HashMap<>();
        List<NameValuePair> params = URLEncodedUtils.parse(new URI(url), Charset.forName("UTF-8"));

        NameValuePair bodySHA256 = null;
        for (NameValuePair param : params) {
            if (param.getName().equals("bodySHA256")) {
                bodySHA256 = param;
                break;
            }
        }

        if (bodySHA256 != null) {
            return validate(url, empty, expectedSignature) && validateBody(body, bodySHA256.getValue());
        } else {
            return false;
        }
    }

    public boolean validateBody(String body, String expectedSHA) {
        MessageDigest digest;
        try {
            digest = MessageDigest.getInstance("SHA-256");
        } catch (NoSuchAlgorithmException e) {
            return false;
        }

        byte[] hash = digest.digest(body.getBytes(StandardCharsets.UTF_8));
        String hexString = DatatypeConverter.printHexBinary(hash).toLowerCase();

        return secureCompare(expectedSHA, hexString);
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
            return DatatypeConverter.printBase64Binary(rawHmac);

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

    private String removePort(String url) {
        try {
            URI parsedUrl = new URI(url);
            return (parsedUrl.getPort() == -1) ? url : updatePort(parsedUrl, -1);
        } catch (Exception e) {
            return url;
        }
    }
    
    private String addPort(String url) {
        try {
            URI parsedUrl = new URI(url);
            if (parsedUrl.getPort() != -1) {
                return url;
            }
            int port = Objects.equals(parsedUrl.getScheme(), "https") ? 443 : 80;
            return updatePort(parsedUrl, port);
        } catch (Exception e) {
            return url;
        }
    }

    private String updatePort(URI url, int newPort) {
        try {
            return new URI(
                url.getScheme(),
                url.getUserInfo(),
                url.getHost(), 
                newPort,
                url.getPath(),
                url.getQuery(),
                url.getFragment()).toString();
        } catch (Exception e) {
            return url.toString();
        }
    }

}
