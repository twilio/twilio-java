package com.twilio.security;

import org.apache.commons.codec.binary.Hex;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;

import java.net.URI;
import java.net.URISyntaxException;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.*;
import org.apache.hc.core5.http.NameValuePair;
import org.apache.hc.core5.net.URIBuilder;

public class RequestValidator {

    /** Header Twilio adds to webhooks signed with a SharedKey: {@code key=<sharedKeyId>,algorithm=<algorithm>}. */
    public static final String SIGNATURE_SETTINGS_HEADER = "X-Twilio-Webhooks-Signature-Settings";
    public static final String HMAC_SHA1 = "HMAC_SHA1";
    public static final String HMAC_SHA256 = "HMAC_SHA256";

    private static final String JCA_HMAC_SHA1 = "HmacSHA1";
    private static final String JCA_HMAC_SHA256 = "HmacSHA256";

    private final byte[] secret;

    /**
     * @param authToken the secret the webhook was signed with: the account Auth Token, or the SharedKey secret
     *                  when the request carries {@link #SIGNATURE_SETTINGS_HEADER}
     */
    public RequestValidator(String authToken) {
        this.secret = authToken.getBytes();
    }

    public boolean validate(String url, Map<String, String> params, String expectedSignature) {
        return validate(url, params, expectedSignature, null);
    }

    /**
     * Validates a webhook signed with the given algorithm.
     *
     * @param algorithm {@link #HMAC_SHA1} or {@link #HMAC_SHA256}; null or empty means {@link #HMAC_SHA1}.
     *                  Any other value fails validation. See {@link #getSignatureAlgorithm(String)}.
     */
    public boolean validate(String url, Map<String, String> params, String expectedSignature, String algorithm) {
        String macAlgorithm = toMacAlgorithm(algorithm);
        if (macAlgorithm == null) {
            return false;
        }
        // check signature of url with and without port, since sig generation on back
        // end is inconsistent
        String signatureWithPort = getValidationSignature(addPort(url), params, macAlgorithm);
        String signatureWithoutPort = getValidationSignature(removePort(url), params, macAlgorithm);
        // If either url produces a valid signature, we accept the request as valid
        return secureCompare(signatureWithPort, expectedSignature) ||
            secureCompare(signatureWithoutPort, expectedSignature);
    }

    public boolean validate(String url, String body, String expectedSignature) throws URISyntaxException {
        return validate(url, body, expectedSignature, null);
    }

    /**
     * Validates a webhook with a JSON body (the URL carries {@code bodySHA256}) signed with the given algorithm.
     *
     * @param algorithm see {@link #validate(String, Map, String, String)}
     */
    public boolean validate(String url, String body, String expectedSignature, String algorithm)
        throws URISyntaxException {
        Map<String, String> empty = new HashMap<>();
        List<NameValuePair> params = new URIBuilder(url).getQueryParams();

        NameValuePair bodySHA256 = null;
        for (NameValuePair param : params) {
            if (param.getName().equals("bodySHA256")) {
                bodySHA256 = param;
                break;
            }
        }

        if (bodySHA256 != null) {
            return validate(url, empty, expectedSignature, algorithm) && validateBody(body, bodySHA256.getValue());
        }
        return false;
    }

    public boolean validateBody(String body, String expectedSHA) {
        MessageDigest digest;
        try {
            digest = MessageDigest.getInstance("SHA-256");
        } catch (NoSuchAlgorithmException e) {
            return false;
        }

        byte[] hash = digest.digest(body.getBytes(StandardCharsets.UTF_8));
        String hexString = Hex.encodeHexString(hash).toLowerCase();

        return secureCompare(expectedSHA, hexString);
    }

    /**
     * Returns the signing algorithm named in the {@link #SIGNATURE_SETTINGS_HEADER} value. A missing or empty
     * header (or one without {@code algorithm=}) means the legacy Auth Token signature, {@link #HMAC_SHA1}.
     *
     * @param signatureSettingsHeader the header value, e.g. {@code key=webhooks_sharedkey_...,algorithm=HMAC_SHA256}
     */
    public static String getSignatureAlgorithm(String signatureSettingsHeader) {
        if (signatureSettingsHeader != null) {
            for (String part : signatureSettingsHeader.split(",")) {
                String[] keyValue = part.split("=", 2);
                if (keyValue.length == 2 && keyValue[0].trim().equals("algorithm") && !keyValue[1].trim().isEmpty()) {
                    return keyValue[1].trim();
                }
            }
        }
        return HMAC_SHA1;
    }

    private static String toMacAlgorithm(String algorithm) {
        if (algorithm == null || algorithm.isEmpty() || algorithm.equalsIgnoreCase(HMAC_SHA1)) {
            return JCA_HMAC_SHA1;
        }
        if (algorithm.equalsIgnoreCase(HMAC_SHA256)) {
            return JCA_HMAC_SHA256;
        }
        return null;
    }

    private String getValidationSignature(String url, Map<String, String> params, String macAlgorithm) {
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

            Mac mac = Mac.getInstance(macAlgorithm);
            mac.init(new SecretKeySpec(secret, macAlgorithm));

            byte[] rawHmac = mac.doFinal(builder.toString().getBytes(StandardCharsets.UTF_8));
            return Base64.getEncoder().encodeToString(rawHmac);

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
