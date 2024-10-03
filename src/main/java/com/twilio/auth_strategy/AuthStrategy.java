package com.twilio.auth_strategy;

import com.twilio.constant.EnumConstants;
import lombok.Getter;

public abstract class AuthStrategy {
    @Getter
    private EnumConstants.AuthType authType;

    public AuthStrategy(EnumConstants.AuthType authType) {
        this.authType = authType;
    }
    public abstract String getAuthString();
    
    public abstract boolean requiresAuthentication(); 
    
}
