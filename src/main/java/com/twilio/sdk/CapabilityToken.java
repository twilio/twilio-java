package com.twilio.sdk;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.lang3.StringUtils;
import org.json.simple.JSONValue;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;


public class CapabilityToken {
    protected static String jwtEncode(Map<String, Object> payload, String key)
            throws InvalidKeyException, NoSuchAlgorithmException,
            UnsupportedEncodingException {

        Map<String, Object> header = new LinkedHashMap<String, Object>();
        header.put("typ", "JWT");
        header.put("alg", "HS256");

        List<String> segments = new ArrayList<String>();
        segments.add(encodeBase64(jsonEncode(header)));
        segments.add(encodeBase64(jsonEncode(payload)));

        String signingInput = StringUtils.join(segments, ".");
        String signature = sign(signingInput, key);
        segments.add(signature);

        return StringUtils.join(segments, ".");
    }

    private static String jsonEncode(Object object) {
        String json = JSONValue.toJSONString(object);
        return json.replace("\\/", "/");
    }

    private static String encodeBase64(String data)
            throws UnsupportedEncodingException {
        return encodeBase64(data.getBytes("UTF-8"));
    }

    private static String encodeBase64(byte[] data)
            throws UnsupportedEncodingException {
        String encodedString = new String(Base64.encodeBase64(data));
        String safeString = encodedString.replace('+', '-').replace('/', '_')
                .replace("=", "");
        return safeString;
    }

    // see
    // http://discussion.forum.nokia.com/forum/showthread.php?130974-Help-required-How-to-generate-a-MAC-(HMAC-SHA1)-with-Java
    private static String sign(String data, String key)
            throws NoSuchAlgorithmException, InvalidKeyException,
            UnsupportedEncodingException {
        SecretKeySpec signingKey = new SecretKeySpec(key.getBytes("UTF-8"),
                "HmacSHA256");
        Mac mac = Mac.getInstance("HmacSHA256");
        mac.init(signingKey);
        byte[] rawHmac = mac.doFinal(data.getBytes("UTF-8"));
        String result = encodeBase64(rawHmac);
        return result;
    }

    @SuppressWarnings("serial")
    public static class DomainException extends Exception {
        public DomainException(String message) {
            super(message);
        }

        public DomainException(Exception e) {
            super(e);
        }
    }
}
