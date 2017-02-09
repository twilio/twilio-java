package com.twilio.jwt;

/**
 * Exception to throw when a JWT cannot be encoded.
 */
public class JwtEncodingException extends RuntimeException {
    public JwtEncodingException(Exception e) {
        super(e);
    }
}
