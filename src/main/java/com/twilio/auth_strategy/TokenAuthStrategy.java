package com.twilio.auth_strategy;

import com.auth0.jwt.JWT;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.twilio.constant.EnumConstants;
import com.twilio.http.bearertoken.TokenManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Date;
import java.util.Objects;

public class TokenAuthStrategy extends AuthStrategy {
    private String token;
    private TokenManager tokenManager;
    private static final Logger logger = LoggerFactory.getLogger(TokenAuthStrategy.class);
    public TokenAuthStrategy(TokenManager tokenManager) {
        super(EnumConstants.AuthType.TOKEN);
        this.tokenManager = tokenManager;
    }

    @Override
    public String getAuthString() {
        fetchToken();
        return "Bearer " + token;
    }

    @Override
    public boolean requiresAuthentication() {
        return true;
    }

    // Token-specific refresh logic
    public void fetchToken() {
        if (this.token == null || this.token.isEmpty() || isTokenExpired(this.token)) {
            synchronized (TokenAuthStrategy.class){
                if (this.token == null || this.token.isEmpty() || isTokenExpired(this.token)) {
                    logger.info("Fetching new token for Apis");
                    this.token = tokenManager.fetchAccessToken();
                }
            }
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TokenAuthStrategy that = (TokenAuthStrategy) o;
        return Objects.equals(token, that.token) && 
                Objects.equals(tokenManager, that.tokenManager);
    }
    @Override
    public int hashCode() {
        return Objects.hash(token, tokenManager);
    }

    public boolean isTokenExpired(final String token) {
        DecodedJWT jwt = JWT.decode(token);
        Date expiresAt = jwt.getExpiresAt();
        // Add a buffer of 30 seconds
        long bufferMilliseconds = 30 * 1000;
        Date bufferExpiresAt = new Date(expiresAt.getTime() - bufferMilliseconds);
        return bufferExpiresAt.before(new Date());
    }
}