package com.twilio.http.bearertoken;

import jdk.internal.net.http.common.Pair;

public interface TokenManager {
    public String refreshAccessToken(String refreshToken);
    public String fetchAccessToken();
}