package com.twilio.credential;

import com.twilio.auth_strategy.AuthStrategy;
import com.twilio.auth_strategy.TokenAuthStrategy;
import com.twilio.constant.EnumConstants;
import com.twilio.http.Request;
import lombok.Getter;

public abstract class CredentialProvider {
    @Getter
    private EnumConstants.AuthType authType;

    public CredentialProvider(EnumConstants.AuthType authType) {
        this.authType = authType;
    }
    
    public abstract AuthStrategy toAuthStrategy();
}
