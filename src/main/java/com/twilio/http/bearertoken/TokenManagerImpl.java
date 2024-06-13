package com.twilio.http.bearertoken;

import lombok.Setter;
import lombok.RequiredArgsConstructor;
import com.twilio.rest.previewiam.organizations.Token;

@RequiredArgsConstructor
public class TokenManagerImpl implements TokenManager{

    @Setter
    private final String grantType;
    private final String clientId;
    private final String clientSecret;

    @Override
    public String refreshAccessToken(String refreshToken){
        Token token = Token.creator(grantType, clientId).setClientSecret(refreshToken).create();
        return token.getAccessToken();
    }

    public synchronized String fetchAccessToken(){
        Token token = Token.creator(grantType, clientId).setClientSecret(clientSecret).create();
        return token.getAccessToken();
    }
}