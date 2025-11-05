package com.twilio.credential.orgs;

import com.twilio.auth_strategy.AuthStrategy;
import com.twilio.auth_strategy.TokenAuthStrategy;
import com.twilio.constant.EnumConstants;
import com.twilio.credential.CredentialProvider;
import com.twilio.exception.AuthenticationException;
import com.twilio.http.bearertoken.OrgsTokenManager;
import com.twilio.http.bearertoken.TokenManager;

import java.util.Objects;

public class OrgsClientCredentialProvider extends CredentialProvider {
    private String grantType;
    private String clientId;
    private String clientSecret;
    private TokenManager tokenManager;

    public OrgsClientCredentialProvider(String clientId, String clientSecret) {
        super(EnumConstants.AuthType.CLIENT_CREDENTIALS);
        if (clientId == null || clientSecret == null) {
            throw new AuthenticationException("ClientId or ClientSecret can not be null");
        }
        this.grantType = "client_credentials";
        this.clientId = clientId;
        this.clientSecret = clientSecret;
        this.tokenManager = null;
    }

    public OrgsClientCredentialProvider(String clientId, String clientSecret, TokenManager tokenManager) {
        super(EnumConstants.AuthType.CLIENT_CREDENTIALS);
        if (clientId == null || clientSecret == null || tokenManager == null) {
            throw new AuthenticationException("ClientId or ClientSecret or TokenManager can not be null");
        }
        this.grantType = "client_credentials";
        this.clientId = clientId;
        this.clientSecret = clientSecret;
        this.tokenManager = tokenManager;
    }

    @Override
    public AuthStrategy toAuthStrategy() {
        if (tokenManager == null) {
            tokenManager = new OrgsTokenManager(grantType, clientId, clientSecret);
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

        OrgsClientCredentialProvider other = (OrgsClientCredentialProvider) o;
        return Objects.equals(clientId, other.clientId) &&
                Objects.equals(clientSecret, other.clientSecret) &&
                Objects.equals(tokenManager, other.tokenManager);
    }
}
