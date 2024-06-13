package com.twilio.http.bearertoken;

public interface TokenManager {
    public String refreshAccessToken(String refreshToken);
    public synchronized String fetchAccessToken();
}