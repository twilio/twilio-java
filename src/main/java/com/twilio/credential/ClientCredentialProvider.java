package com.twilio.credential;


import com.twilio.annotations.Beta;
import com.twilio.auth_strategy.AuthStrategy;
import com.twilio.auth_strategy.TokenAuthStrategy;
import com.twilio.constant.EnumConstants;
import com.twilio.exception.AuthenticationException;

import com.twilio.http.bearertoken.ApiTokenManager;
import com.twilio.http.bearertoken.TokenManager;

import java.util.Objects;

@Beta
public class ClientCredentialProvider extends CredentialProvider {
    private String grantType;
    private String clientId;
    private String clientSecret;
    private TokenManager tokenManager;
    
    public ClientCredentialProvider(String clientId, String clientSecret) {
        super(EnumConstants.AuthType.CLIENT_CREDENTIALS);
        if (clientId == null || clientSecret == null) {
            // TODO: Test with invalid credentials
            throw new AuthenticationException("Invalid credentials passed");
        }
        this.grantType = "client_credentials";
        this.clientId = clientId;
        this.clientSecret = clientSecret;
        this.tokenManager = null;
    }

    public ClientCredentialProvider(String clientId, String clientSecret, TokenManager tokenManager) {
        super(EnumConstants.AuthType.CLIENT_CREDENTIALS);
        // TODO: Null check
        if (clientId == null || clientSecret == null || tokenManager == null) {
            // TODO: Test with invalid credentials
            throw new AuthenticationException("Invalid credentials passed");
        }
        this.grantType = "client_credentials";
        this.clientId = clientId;
        this.clientSecret = clientSecret;
        this.tokenManager = tokenManager;
    }

    @Override
    public AuthStrategy toAuthStrategy() {
        if (tokenManager == null) {
            tokenManager = new ApiTokenManager(grantType, clientId, clientSecret);
        }
        return new TokenAuthStrategy(tokenManager);
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }

        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        ClientCredentialProvider other = (ClientCredentialProvider) o;
        return Objects.equals(clientId, other.clientId) &&
                Objects.equals(clientSecret, other.clientSecret) &&
                Objects.equals(tokenManager, other.tokenManager);
    }
}
