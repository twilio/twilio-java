package com.twilio.auth_strategy;

import com.auth0.jwt.JWT;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.twilio.constant.EnumConstants;
import com.twilio.http.bearertoken.BearerTokenTwilioRestClient;
import com.twilio.http.bearertoken.TokenManager;

import java.util.Date;

public class TokenAuthStrategy extends AuthStrategy {
    private String token;
    
    private TokenManager tokenManager;

    public TokenAuthStrategy(String token) {
        super(EnumConstants.AuthType.TOKEN);
        this.token = token;
    }

    @Override
    public String getAuthString() {
        return "Bearer " + token;
    }

    @Override
    public boolean requiresAuthentication() {
        return true;
    }

    // Token-specific refresh logic
    public void refresh() {
        if (this.token == null || this.token.isEmpty() || isTokenExpired(this.token)) {
            synchronized (BearerTokenTwilioRestClient.class){
                if (this.token == null || this.token.isEmpty() || isTokenExpired(this.token)) {
                    this.token = tokenManager.fetchAccessToken();
                }
            }
        }
        this.token = tokenManager.fetchAccessToken();
    }

    public boolean isTokenExpired(String token) {
        DecodedJWT jwt = JWT.decode(token);
        Date expiresAt = jwt.getExpiresAt();
        // Add a buffer of 30 seconds
        long bufferMilliseconds = 30 * 1000;
        Date bufferExpiresAt = new Date(expiresAt.getTime() - bufferMilliseconds);
        return bufferExpiresAt.before(new Date());
    }
}