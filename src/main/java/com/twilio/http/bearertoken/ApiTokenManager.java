package com.twilio.http.bearertoken;

import com.twilio.annotations.Beta;
import com.twilio.rest.oauth.v2.Token;
import com.twilio.rest.oauth.v2.TokenCreator;

import java.util.Objects;

@Beta
public class ApiTokenManager implements TokenManager {

    private final String grantType;
    private final String clientId;
    private final String clientSecret;
    private String code;
    private String redirectUri;
    private String audience;
    private String refreshToken;
    private String scope;

    @Override
    public String fetchAccessToken() {
        TokenCreator tokenCreator = Token.creator().setGrantType(grantType).setClientId(clientId).setClientSecret(clientSecret);
        if (this.code != null) tokenCreator.setCode(code);
        if (this.redirectUri != null) tokenCreator.setRedirectUri(redirectUri);
        if (this.audience != null) tokenCreator.setAudience(audience);
        if (this.refreshToken != null) tokenCreator.setRefreshToken(refreshToken);
        if (this.scope != null) tokenCreator.setScope(scope);
        Token token = tokenCreator.create();
        return token.getAccessToken();
    }

    public ApiTokenManager(String grantType, String clientId, String clientSecret){
        this.grantType = grantType;
        this.clientId = clientId;
        this.clientSecret = clientSecret;
    }

    public ApiTokenManager(String grantType, String clientId, String clientSecret, String code, String redirectUri, String audience, String refreshToken, String scope){
        this.grantType = grantType;
        this.clientId = clientId;
        this.clientSecret = clientSecret;
        this.code = code;
        this.redirectUri = redirectUri;
        this.audience = audience;
        this.refreshToken = refreshToken;
        this.scope = scope;
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }

        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        ApiTokenManager other = (ApiTokenManager) o;
        return Objects.equals(grantType, other.grantType) &&
                Objects.equals(clientId, other.clientId) &&
                Objects.equals(clientSecret, other.clientSecret) &&
                Objects.equals(code, other.code) &&
                Objects.equals(redirectUri, other.redirectUri) &&
                Objects.equals(audience, other.audience) &&
                Objects.equals(refreshToken, other.refreshToken) &&
                Objects.equals(scope, other.scope);
    }
}
