package com.twilio.http.bearertoken;

import com.twilio.annotations.Beta;
import com.twilio.exception.ApiException;
import com.twilio.rest.oauth.v2.Token;
import com.twilio.rest.oauth.v2.TokenCreator;
@Beta
public class OrgsTokenManager implements TokenManager{

    private final String grantType;
    private final String clientId;
    private final String clientSecret;
    private String code;
    private String redirectUri;
    private String audience;
    private String refreshToken;
    private String scope;

    public OrgsTokenManager(String grantType, String clientId, String clientSecret){
        this.grantType = grantType;
        this.clientId = clientId;
        this.clientSecret = clientSecret;
    }

    public OrgsTokenManager(String grantType, String clientId, String clientSecret, String code, String redirectUri, String audience, String refreshToken, String scope){
        this.grantType = grantType;
        this.clientId = clientId;
        this.clientSecret = clientSecret;
        this.code = code;
        this.redirectUri = redirectUri;
        this.audience = audience;
        this.refreshToken = refreshToken;
        this.scope = scope;
    }

    public synchronized String fetchAccessToken(){
        TokenCreator tokenCreator = Token.creator().setGrantType(grantType).setClientId(clientId).setClientSecret(clientSecret);
        if (this.code != null) tokenCreator.setCode(code);
        if (this.redirectUri != null) tokenCreator.setRedirectUri(redirectUri);
        if (this.audience != null) tokenCreator.setAudience(audience);
        if (this.refreshToken != null) tokenCreator.setRefreshToken(refreshToken);
        if (this.scope != null) tokenCreator.setScope(scope);
        Token token;
        try {
            token = tokenCreator.create();
            if(token == null || token.getAccessToken() == null){
                throw new ApiException("Token creation failed");
            }
        } catch(Exception e){
            throw new ApiException("Token creation failed");
        }
        return token.getAccessToken();
    }
}
