package com.twilio.credential;


import com.twilio.auth_strategy.AuthStrategy;
import com.twilio.auth_strategy.TokenAuthStrategy;
import com.twilio.constant.EnumConstants;
import com.twilio.http.Request;
import com.twilio.http.bearertoken.ApiTokenManager;
import com.twilio.http.bearertoken.TokenManager;

public class ClientCredentialProvider extends CredentialProvider {
    private String grantType;
    private String clientId;
    private String clientSecret;
    
    public ClientCredentialProvider(String clientId, String clientSecret) {
        super(EnumConstants.AuthType.CLIENT_CREDENTIALS);
        this.grantType = "client_credentials";
        this.clientId = clientId;
        this.clientSecret = clientSecret;
    }

    @Override
    public AuthStrategy toAuthStrategy() {
        TokenManager tokenManager = new ApiTokenManager(grantType, clientId, clientSecret);
        String accessToken = tokenManager.fetchAccessToken();
        return new TokenAuthStrategy(accessToken);
    }

    @Override
    public void request(Request request) {

    }
}
