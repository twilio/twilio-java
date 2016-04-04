package com.twilio.client;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.base.Joiner;
import org.apache.commons.codec.binary.Base64;

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
            throws InvalidKeyException, NoSuchAlgorithmException, UnsupportedEncodingException {

        Map<String, Object> header = new LinkedHashMap<>();
        header.put("typ", "JWT");
        header.put("alg", "HS256");

        List<String> segments = new ArrayList<>();
        segments.add(encodeBase64(jsonEncode(header)));
        segments.add(encodeBase64(jsonEncode(payload)));

        String signingInput = Joiner.on(".").join(segments);
        String signature = sign(signingInput, key);
        segments.add(signature);

        return Joiner.on(".").join(segments);
    }

    private static String jsonEncode(Object object) {
        try {
            String json = new ObjectMapper().writeValueAsString(object);
            return json.replace("\\/", "/");
        } catch (JsonProcessingException e) {
            return null;
        }
    }

    private static String encodeBase64(String data) throws UnsupportedEncodingException {
        return encodeBase64(data.getBytes("UTF-8"));
    }

    private static String encodeBase64(byte[] data) throws UnsupportedEncodingException {
        String encodedString = new String(Base64.encodeBase64(data));
        return encodedString.replace('+', '-').replace('/', '_').replace("=", "");
    }

    // see
    // http://discussion.forum.nokia.com/forum/showthread.php?130974-Help-required-How-to-generate-a-MAC-(HMAC-SHA1)-with-Java
    private static String sign(String data, String key)
        throws NoSuchAlgorithmException, InvalidKeyException, UnsupportedEncodingException {

        SecretKeySpec signingKey = new SecretKeySpec(key.getBytes("UTF-8"), "HmacSHA256");
        Mac mac = Mac.getInstance("HmacSHA256");
        mac.init(signingKey);
        byte[] rawHmac = mac.doFinal(data.getBytes("UTF-8"));
        return encodeBase64(rawHmac);
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
